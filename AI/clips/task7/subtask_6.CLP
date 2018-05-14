(deffunction str-rev (?str) 
    (bind ?n (str-length ?str))
    (bind ?s "")	
    (loop-for-count (?i 1 ?n) do  
	(bind ?s1 (sub-string (+ (- ?n ?i) 1) (+ (- ?n ?i) 1) ?str))   
	(bind ?s (str-cat ?s ?s1))                                    
    )
    (printout t "Зеркальное отражение = " ?s crlf)
)

(defrule substr_zamena
    =>
	(printout t "Строчка = ")
	(bind ?str (read))    
	(bind ?str (str-rev ?str))
)
