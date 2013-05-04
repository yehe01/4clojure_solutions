(ns quux.t-no73
  (:use midje.sweet quux.no73))

(fact "x win vertiacally"
  (win? [[:x :e :o]
         [:x :e :e]
         [:x :e :o]] :x) => truthy)

(fact "x not win vertiacally"
  (win? [[:e :e :o]
         [:x :e :o]
         [:x :e :e]] :x) => falsey)

(fact "o win horizontally"
  (win? [[:o :o :o]
         [:x :e :e]
         [:x :e :o]] :o) => truthy)
