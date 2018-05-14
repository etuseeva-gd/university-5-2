(deffunction str-zam (?str ?symb1 ?symb2) 
    (bind ?n1 (str-length ?str))                         
    (bind ?n2 (str-length ?symb1))                           
    (bind ?k (str-index ?symb1 ?str))      
    (if  (> ?k 0) then                      
          (bind ?st1  (sub-string 1 (- ?k 1) ?str))
          (bind ?st2  (sub-string (+ ?k ?n2) ?n1 ?str))
	  (bind ?str1 (str-cat ?st1 ?symb2))
    	  (bind ?str (str-cat ?str1 ?st2))                      
    )
)

(defrule substr_zamena
    =>
	(printout t "str=")
	(bind ?str (read))
	(printout t "symb1=")
	(bind ?symb1 (read))
	(printout t "symb2=")
	(bind ?symb2 (read))
	(bind ?str (str-zam ?str ?symb1 ?symb2))
	(printout t "Результат = " ?str crlf)
)