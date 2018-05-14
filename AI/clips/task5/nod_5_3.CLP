(defrule nod 
  =>
   (printout t "a=")   
   (bind ?a (read)) 
   (printout t "b=")   
   (bind ?b (read))                             
   (while (<> ?a ?b) do
	(if (> ?a ?b) then
		(bind ?a (- ?a ?b))
	else (bind ?b (- ?b ?a))
	)                              
   )
  (printout t "nod=" ?a crlf)                 
)
