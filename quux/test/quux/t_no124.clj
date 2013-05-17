(ns quux.t-no124
  (:use midje.sweet quux.no124))

(fact "return possible start coordinates of white color"
  (candidates '[[e e e e]
                [e w b e]
                [e b w e]
                [e e e e]] 'w) => #{[1 1] [2 2]})

(fact "return if e is a legal move"
  (legal? '[[e e e e]
            [e w b e]
            [e b w e]
            [e e e e]] [1 1] [1 3]) => truthy
  (legal? '[[e e e e]
            [e w b e]
            [e b w e]
            [e e e e]] [1 1] [1 2]) => falsey
  (legal? '[[e e e e]
            [e w b e]
            [e b w e]
            [e e e e]] [1 1] [1 0]) => falsey
  (legal? '[[e e e e]
            [e w b e]
            [e w w e]
            [e e e e]] [1 1] [3 1]) => falsey)

(fact "return legal moves from one candidate"
  (legal-moves '[[e e e e]
                 [e w b e]
                 [e b w e]
                 [e e e e]] [1 1]) => #{[1 3] [3 1]})
