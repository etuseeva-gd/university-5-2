;;;******************************************************************** 
;;; Вспомогательные функции 
;;; ******************************************************************** 
;;; Функция ask-question задает пользователю вопрос, полученный 
;;; в переменной ?question, и получает от пользователя ответ, 
;;; принадлежащий списку допустимых ответов, заданному в $?allowed-values 

(deffunction ask-question (?question $?allowed-values) 
(printout t ?question) 
(bind ?answer (read)) 
(if (lexemep ?answer) 
then (bind ?answer (lowcase ?answer))) 
(while (not (member$ ?answer ?allowed-values)) do
(printout t ?question) 
(bind ?answer (read)) 
(if (lexemep ?answer) 
then (bind ?answer (lowcase ?answer)))) 
?answer) 

;;; Функция yes-or-no-p задает пользователю вопрос, полученный 
;;; в переменной ?question, и получает от пользователя ответ yes(у)или 
;;; no(n). В случае положительного ответа функция возвращает значение TRUE, 
;;; иначе - FALSE 

(deffunction yes-or-no-p (?question) 
(bind ?response (ask-question ?question yes no y n)) 
(if (or (eq ?response yes) (eq ?response y)) 
then TRUE 
else FALSE)) 


;;;****************************************************************** 
;;; Диагностические правила 
;;;***************************************************************** 
;;; Правило determine-printer-state определяет текущее состояние принтера 
;;; по ответам, получаемым от пользователя. принтер может 
;;; находиться в одном из трех состояний: работать нормально 
;;; (working-state printer normal), работать неудовлетворительно 
;;; (working-state printer unsatisfactory) и не работать 
;;; (working-state printer does-not-start). 


;;; Правило determine-print определяет печатает ли 
;;; принтер. 

(defrule determine-print "" 
(not (print ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Принтер печатает (yes/no)? ") 
then (assert (print work)) ; принтер печатает 
Else (assert (print does-not-work))) ; принтер не печатает 
) 


;;; Правило determine-print определяет печатает 
;;; состояние принтера. 

(defrule determine-printer-state "" 
(not (working-state printer ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Принтер включается (yes/no)? ") 
then 
(if (yes-or-no-p "Принтер работает нормально (yes/no)? ") 
then (assert (working-state printer normal)) 
else (assert (working-state printer unsatisfactory))) 
else 
(assert (working-state printer does-not-start)))) 

;;; Правило determine-switchOn-printer определяет включается ли 
;;; принтер. 

(defrule determine-switchOn-printer "" 
(working-state printer does-not-start) 
(not (switchOn ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Принтер включается (yes/no)? ") 
then (assert (switchOn work)) ; принтер включается 
Else (assert (switchOn does-not-work))) ; принтер не включается 
) 

;;; Правило determine-power-printer определяет включен ли 
;;; принтер в сеть. 

(defrule determine-power-printer "" 
(switchOn does-not-work) 
(not (power ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Принтер включен в сеть (yes/no)? ") 
then (assert (power work)) ; принтер включен в сеть 
Else (assert (repair "Включите принтер в сеть."))) ; принтер не включен в сеть 
) 

;;; Правило determine-driver определяет установлены ли 
;;; дрова. 

(defrule determine-driver "" 
(switchOn work) 
(print does-not-work) 
(not (driver ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "На ЭВМ установлен драйвер принтера (yes/no)? ") 
then (assert (driver work)) ; драйвер установлен 
else (assert (repair "Установите драйвер принтера."))) ; драйвер не установлен 
) 

;;; Правило determine-computer-communication определяет есть ли связь ЭВМ с принтером 

(defrule determine-computer-communication "" 
(switchOn work) 
(drive work) 
(print does-not-work) 
(not (computer-communication ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Есть связь ЭВМ с принтером (yes/no)? ") 
then (assert (computer-communication work)) 
Else (assert (repair "Подключите принтер к компьютеру."))) 
) 


;;; Правило determine-availability-ink определяет есть ли 
;;; чернила в
катридже. 

(defrule determine-availability-ink "" 
(switchOn work) 
(not (ink ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "При печати выводятся пустые листы (yes/no)? ") 
then (assert (repair "Заправьте чернила.")) 
Else (assert (ink work))) 
) 


;;; Правило determine-paper определяет есть ли 
;;; бумага. 

(defrule determine-paper "" 
(switchOn work) 
(print does-not-work) 
(computer-communication work) 
(not (paper ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Есть ли бумага в принтере (yes/no)? ") 
then (assert (paper work)) 
Else (assert (repair "Заправьте бумагу в принтер."))) 
) 


;;; Правило determine-cover определяет закрыта ли 
;;; крышка отсека. 

(defrule determine-cover "" 
(switchOn work) 
(print does-not-work) 
(computer-communication work) 
(not (cover ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Закрыта ли крышка отсека с катриджем (yes/no)? ") 
then (assert (cover work)) 
Else (assert (repair "Закройте крышку отсека с катриджем."))) 
) 


;;; Правило determine-crush-paper определяет замялась ли 
;;; бумага. 

(defrule determine-crush-paper "" 
(switchOn work) 
(print work) 
(not (crush-paper ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Печать приостановилась (yes/no)? ") 
then (if (yes-or-no-p "Закончилась бумага (yes/no)? ") 
then (assert (repair "Заправьте бумагу в принтер.")) 
else (assert (repair "Выньте замятую бумагу из механизма."))) 
Else (assert (crush-paper does-not-work))) 
) 


;;; Правило determine-clear определяет нужно ли 
;;; почистить катридж. 

(defrule determine-clear "" 
(switchOn work) 
(print work) 
(not (clear ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "При печати появились черные полосы (yes/no)? ") 
then (assert (repair "Почистите катридж.")) 
Else (assert (clear does-not-need))) 
) 



;;;******************************************************************** 
;;; Правила, определяющие состояние некоторых подсистем 
;;;******************************************************************** 
;;; Правило normal-printer-state-conclusions 

(defrule normal-printer-state-conclusions "" 
(declare (salience 10)) 
(working-state printer normal) ; Если принтер работает нормально 
=> 
(assert (repair "Ремонт не нужен.")) ; ремонт не нужен 
(assert (power work)) ; на принтер подано питание 
(assert (drive work)) ; установлены дрова 
(assert (print work)) ; печатает 
(assert (computer-communication work)) ; подключен к компу 
(assert (paper work)) ; есть бумага 
(assert (ink work))) ; есть чернила 

;;; Правило unsatisfactory-engine-state-conclusions 

(defrule unsatisfactory-printer-state-conclusions "" 
(declare (salience 10)) 
; Если принтер работает неудовлетворительно 
(working-state printer unsatisfactory) 
(switchOn work) 
=> 
(assert (power work)) ; на принтер подано питание 
) 


;;;******************************************************************** 
;;; Запуск и завершение программы 
;;;******************************************************************** 
;;; Правило no-repairs запускается в случае, если ни одно из 
;;; диагностических правил не способно определить неисправность. 
;;; Правило корректно прерывает выполнение экспертной системы и 
;;; предлагает пройти более тщательную проверку. 

(defrule no-repairs "" 
(declare (salience -10)) 
(not (repair ?)) 
=> 
(assert (repair "Обратитесь в ремонт."))) 

;;; Правило print-repair выводит на экран диагностическое сообщение 
;;; по устранению найденной неисправности. 
(defrule print-repair "" 
(declare (salience 10)) 
(repair ?item) 
=> 
(printout t crlf crlf) 
(printout t "Рекомендации по ремонту:") 
(printout t crlf crlf) 
(format t " %s%n%n%n" ?item)) 

;;; Правило system-banner выводит на экран название экспертной системы 
;;; при каждом новом запуске. 
(defrule system-banner "" 
(declare (salience 10)) 
=> 
(printout t crlf crlf) 
(printout t "ЭКСПЕРТНАЯ СИСТЕМА PrinterExpert") 
(printout t crlf crlf) 
)