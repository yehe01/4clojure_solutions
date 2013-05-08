(ns quux.no89)

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
