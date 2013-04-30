(ns quux.t-4clojure
  (:use midje.sweet))

(unfinished )

(def edges [[:a :b] [:a :b] [:a :c] [:c :a]
            [:a :d] [:b :d] [:c :d]])

(defn allnodes [edges]
  (set (reduce (fn [result [a b]] (conj result a b)) #{} edges)))

(defn neighbours [start edges]
  (filter #(and (not (nil? %)) (not= start %))
          (map (fn [[a b]]
                 (cond (= a start) b
                       (= b start) a
                       :else nil)) edges)))

(defn delete-one-route [route edges]
  (let [cnt (count (filter #{route} edges))
        tmp (remove #{route} edges)]
    (concat tmp (repeat (dec cnt) route))))

(defn accessable-paths [edges start seen]
  (if (empty? edges)
    (conj seen start)
    (for [n (neighbours start edges)]
      (flatten
       (accessable-paths (if (some #{[start n]} edges)
                           (delete-one-route [start n] edges)
                           (delete-one-route [n start] edges)) n (conj seen start))))))

(fact "return accessable paths start from one node"
  (accessable-paths edges 1 []) => (just #{[1 4 3 2] [1 2 3 4]}))

(defn ok? [edges]
  (let [allnodes (set (reduce (fn [result [a b]] (conj result a b)) #{} edges))]
    (letfn [(accessable-paths [edges start seen]
              (if (empty? edges)
                (conj seen start)
                (for [n (neighbours start edges)]
                  (flatten
                   (accessable-paths (if (some #{[start n]} edges)
                                       (delete-one-route [start n] edges)
                                       (delete-one-route [n start] edges)) n (conj seen start))))))
            (neighbours [start edges]
              (filter #(and (not (nil? %)) (not= start %))
                      (map (fn [[a b]]
                             (cond (= a start) b
                                   (= b start) a
                                   :else nil)) edges)))
            (delete-one-route [route edges]
              (let [cnt (count (filter #{route} edges))
                    tmp (remove #{route} edges)]
                (concat tmp (repeat (dec cnt) route))))]
      (if (some (fn [node]
                  (if (some #{allnodes}
                            (map set (accessable-paths edges node []))) (do (println node) true) false)) allnodes) true false))))

(ok? [[:a :b] [:a :b] [:a :c] [:c :a]
               [:a :d] [:b :d] [:c :d]])
