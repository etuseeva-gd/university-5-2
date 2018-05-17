(deffunction sum (?n)
	(if (= ?n 1) then 1
	else (+ (/ 1 ?n) (sum (- ?n  1)))
	)		
)
(defrule func_sum
 =>  
  (printout t "n = ")
  (bind ?n (read))                             
  (bind ?s (sum ?n))                       
  (printout t "Сумма = " ?s crlf)
)
