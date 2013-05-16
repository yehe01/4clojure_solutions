(ns quux.no91)

(defn make-graph [edges]
  (reduce (fn [m [a b]]
            (assoc m
              a (conj (m a []) b)
              b (conj (m b []) a))) {} edges))

(defn bfs [start m]
  (loop [visited #{}
         cands #{start}]
    (if (empty? cands)
      visited
      (let [cur (first cands)
            new-cands (concat (remove #(= % cur) cands)
                              (clojure.set/difference (set (get m cur)) visited))]
        (recur (conj visited cur) new-cands)))))

(defn connected? [edges]
  (let [g (make-graph edges)]
    (= (count (bfs (nth (keys g) 0) g)) (count (set (flatten (vals g)))))))
