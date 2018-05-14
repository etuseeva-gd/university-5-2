(defrule prostoe
  =>
   (printout t "n = ")
   (bind ?n (read)) 
   (bind ?s 1)                                      
   (loop-for-count (?i 1 ?n) do                     
   	(bind ?s (* ?s ?i))   
    )   
  (printout t "n! = " ?s crlf)
)
