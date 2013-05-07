(ns quux.t-no74
  (:use midje.sweet quux.no74))

(fact "return co-ordinates of places which can result in a immediate win"
  (find-immediate-win :o [[:x :x :o]
                          [:o :e :o]
                          [:x :e :e]]) => #{[2 2] [1 1]})

(fact "set mark :x or :o on given co-ordinates"
  (set-place [[:o :e :e]
              [:o :x :o]
              [:x :x :e]] [2 2] :x) => [[:o :e :e]
                                        [:o :x :o]
                                        [:x :x :x]])


(fact "return co-ordinates of all empty places as a set of pairs"
  (find-empty-places [[:o :e :e]
                      [:o :x :o]
                      [:x :x :e]]) => #{[2 2] [0 1] [0 2]})
