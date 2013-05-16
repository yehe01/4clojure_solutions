(ns quux.t-no91
  (:use midje.sweet quux.no91))

(fact "should return a graph represented as an adjacent list"
  (make-graph #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4]}) => {1 [2 3], 6 [5 4], 5 [4 6], 4 [5 6], 3 [2 1], 2 [3 1]})

(fact "dfs"
  (bfs :a {:a [:b :c] :b [:e :f :a] :e [:g]}) => #{:b :e :g :f :a :c}
  (bfs :a {:a [:b :c] :b [:e :f :a]}) => #{:b :e :f :a :c})

(fact "final tests"
  (connected? #{[:a :a]}) => true
  (connected? #{[:a :b]}) => true
  (connected? #{[1 2] [2 3] [3 1] [4 5] [5 6 ] [6 4]}) => false
  (connected? #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4] [3 4]}) => true
  (connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e]}) => false
  (connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e] [:x :a]}) => true)
