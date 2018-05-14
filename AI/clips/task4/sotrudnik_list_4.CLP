(deffacts list
      (sotrudnik Shafeeva 15 2 25000)
      (sotrudnik Bogomolova 7 1 17000)
      (sotrudnik Tuseeva 9 3 21500)
      (sotrudnik Rybalko 30 1 35000)
      (sotrudnik Frolov 25 0 24500)
)

(defrule take
	(sotrudnik ?name ?years ?child ?z)
	(test (or (> ?years 4) (> ?child 1))) 
=>
	(assert(premia ?name (* 0.2 ?z)))
)

(defrule premia_numb
      (sotrudnik ?name ?years ?child ?z)
      (test (or (> ?years 4) (> ?child 1)))               
=>
      (assert (poluchil ?name))
) 

(defrule all_premia
      (forall (sotrudnik ?name ?years ?child ?z)
      	(poluchil ?name))               
=>
      (printout t " vse sotrudniki poluchili premiu!" crlf)
)

(defrule v
	(sotrudnik ?name ?years ?child ?z)
	(test (> ?years 20)) 
=>
	(assert(veteran ?name))
)

(defrule ex_v
      (exists (veteran ?name))           
=>
      (printout t " veterany truda est!" crlf)
)