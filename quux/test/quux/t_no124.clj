(ns quux.t-no124
  (:use midje.sweet quux.no124))

(fact "return possible start coordinates of white color"
  (candidates '[[e e e e]
                [e w b e]
                [e b w e]
                [e e e e]] 'w) => #{[1 1] [2 2]}
  (candidates '[[e b e b]
                [b w b b]
                [b b w b]
                [e b b b]] 'e) => #{[0 0] [0 2] [3 0]})

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
            [e e e e]] [1 1] [3 1]) => falsey
  (legal? '[[e e e e]
            [e w b e]
            [e b w e]
            [e e e e]] [2 2] [0 2]) => truthy)

(fact "return legal moves from one candidate"
  (legal-moves '[[e e e e]
                 [e w b e]
                 [e b w e]
                 [e e e e]] [1 1]) => #{[1 3] [3 1]}
  (legal-moves '[[e e e e]
                 [e w b e]
                 [e b w e]
                 [e e e e]] [2 2]) => #{[0 2] [2 0]})

(fact "returns a map of start. its value a set of the coordinates of the pieces flipped by that move."
  (flipped-moves '[[e e e e]
                   [e w b e]
                   [e b w e]
                   [e e e e]] [1 1]) => {[1 3] #{[1 2]}, [3 1] #{[2 1]}}
  (flipped-moves '[[e e e e]
                   [e w b e]
                   [e b w e]
                   [e e e e]] [2 2]) => {[0 2] #{[1 2]}, [2 0] #{[2 1]}})
