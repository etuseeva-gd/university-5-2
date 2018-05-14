(defrule zachisl
  =>
     (printout t "n =" )
     (bind ?n (read))                                     
     (loop-for-count ?n do                           
           (printout t "Second name: " )
           (bind ?name (readline))                   
           (assert (student ?name 1))           
      )         
)
