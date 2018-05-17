(deffacts list
      (sotrudnik Ivanov 15 2 25000)
      (sotrudnik Smirmov 7 1 17000)
      (sotrudnik Stepanov 9 3 21500)
      (sotrudnik Petrov 30 1 35000)
      (sotrudnik Sidorov 25 0 24500)
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
      (printout t "Все сотрудники получили премию!" crlf)
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
      (printout t "Ветераны труда есть!" crlf)
)