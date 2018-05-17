(deffunction func(?x ?c ?d)
	(if (< ?x ?c) then
		(- 1 (** ?x 2))
	else (if (< ?x ?d) then
		(- ?x ?x)
	else if (>= ?x ?d) then
		(+ 1 (** ?x 2))))
)

(defrule ans
=>
(printout t "x = ")
(bind ?x (read))
(printout t "c = ")
(bind ?c (read))
(printout t "d = ")
(bind ?d (read))
(bind ?y (func ?x ?c ?d)) 
(printout t "y = " ?y crlf)
)