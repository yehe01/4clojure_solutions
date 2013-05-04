(ns quux.t-no106
  (:use midje.sweet quux.no106))


(fact "generate new candidates"
  (new-candidates #{[3 12]}) => #{[6 12] [3 24] [5 12] [3 14] [3 6]})


(fact "tell if there are pairs whose two items are qual"
  (equal-pairs-exist? #{[6 12] [3 24] [5 12] [3 14] [3 6] [3 3]}) => truthy
  (equal-pairs-exist? #{[6 12] [3 24] [5 12] [3 14] [3 6]}) => falsey
  (equal-pairs-exist? #{[11 12] [9 14] [9 24] [18 12] [9 6]}) => falsey)


(fact "find shortest path of number pairs"
  (number-maze 3 12) => 3
  (number-maze 9 12) => 5
  (number-maze 1 1) => 1
  (number-maze 9 2) => 9
  (number-maze 5 9) => 3)


(fact "generate children of current pair"
  (gen-children 3 12) => #{[6 12] [5 12]}
  (gen-children 12 13) => #{[24 13] [6 13] [14 13]})
