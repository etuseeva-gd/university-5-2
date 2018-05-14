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
?answer 
) 

(deffunction yes-or-no-p (?question) 
(bind ?response (ask-question ?question yes no y n)) 
(if (or (eq ?response yes) (eq ?response y)) 
then TRUE 
else FALSE) 
) 

(defrule determine-printer-state "" 
(not (working-state printer ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Принтер работает? (yes/no)? ") 
then (if (yes-or-no-p "Принтер работает нормально (yes/no)? ") 
then (assert (working-state printer normal)) 
else (assert (working-state printer unsatisfactory))) 
else (assert (working-state printer does-not-start))) 
) 

(defrule determine-switchOn-printer "" 
(working-state printer unsatisfactory) 
(not (switchOn ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Принтер включается (yes/no)? ") 
then (assert (switchOn work)) ; принтер включается 
else (assert (switchOn does-not-work))) ; принтер не включается 
) 

(defrule determine-power-printer "" 
(switchOn does-not-work) 
(not (power ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Принтер включен в сеть (yes/no)? ") 
then (assert (power work)) ; принтер включен в сеть 
else (assert (repair "Включите принтер в сеть.")) 
(assert (power does-not-work))) ; принтер не включен в сеть 
) 

(defrule determine-print "" 
(switchOn work) 
(not (print ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Принтер печатает (yes/no)? ") 
then (assert (print work)) ; принтер печатает 
else (assert (print does-not-work))) ; принтер не печатает 
) 
(defrule determine-driver "" 
(switchOn work) 
(print does-not-work) 
(not (driver ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "На ЭВМ установлен драйвер принтера (yes/no)? ") 
then (assert (driver work)) ; драйвер установлен 
else (assert (repair "Установите драйвер принтера.")) 
(assert (driver does-not-work))) ; драйвер не установлен 
) 

(defrule determine-computer-communication "" 
(switchOn work) 
(drive work) 
(print does-not-work) 
(not (computer-communication ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Есть связь ЭВМ с принтером (yes/no)? ") 
then (assert (computer-communication work)) 
else (assert (repair "Подключите принтер к компьютеру.")) 
(assert (computer-communication does-not-work))) 
) 

(defrule determine-availability-ink "" 
(switchOn work) 
(print work) 
(not (ink ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "При печати выводятся пустые листы (yes/no)? ") 
then (assert (repair "Заправьте чернила.")) 
(assert (ink does-not-work)) 
else (assert (ink work))) 
) 

(defrule determine-paper "" 
(switchOn work) 
(print does-not-work) 
(computer-communication work) 
(not (paper ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Есть ли бумага в принтере (yes/no)? ") 
then (assert (paper work)) 
else (assert (repair "Заправьте бумагу в принтер.")) 
(assert (paper does-not-work))) 
) 

(defrule determine-cover "" 
(switchOn work) 
(print does-not-work) 
(computer-communication work) 
(not (cover ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Закрыта ли крышка отсека с картриджем (yes/no)? ") 
then (assert (cover work)) 
else (assert (repair "Закройте крышку отсека с картриджем.")) 
(assert (cover does-not-work))) 
) 

(defrule determine-crush-paper "" 
(switchOn work) 
(print work) 
(not (crush-paper ?)) 
(not (repair ?)) 
=> 
(if (yes-or-no-p "Печать приостановилась (yes/no)? ") 
then (if (yes-or-no-p "Закончилась бумага (yes/no)? ") 
then (assert (repair "Заправьте бумагу в принтер.")) 
(assert (paper does-not-work)) 
else (assert (repair "Выньте замятую бумагу из механизма.")) 
(assert (crush-paper work))) 
else (assert (crush-paper does-not-work))) 
) 

(defrule determine-clear "" 
(switchOn work) 
(print work) 
(not (clear ?)) 
(not
(repair ?)) 
=> 
(if (yes-or-no-p "При печати появились черные полосы (yes/no)? ") 
then (assert (repair "Почистите картридж.")) 
(assert (clear need)) 
else (assert (clear does-not-need))) 
) 

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

(defrule unsatisfactory-printer-state-conclusions "" 
(declare (salience 10)) 
; Если принтер работает неудовлетворительно 
(working-state printer unsatisfactory) 
(switchOn work) 
=> 
(assert (power work)) ; на принтер подано питание 
) 

(defrule no-repairs "" 
(declare (salience -10)) 
(not (repair ?)) 
=> 
(assert (repair "Обратитесь в ремонт."))) 


(defrule print-repair "" 
(declare (salience 10)) 
(repair ?item) 
=> 
(printout t crlf crlf) 
(printout t "Рекомендации по ремонту:") 
(printout t crlf crlf) 
(format t " %s%n%n%n" ?item)) 


(defrule system-banner "" 
(declare (salience 10)) 
=> 
(printout t crlf crlf) 
(printout t "ЭКСПЕРТНАЯ СИСТЕМА PrinterExpert") 
(printout t crlf crlf) 
)