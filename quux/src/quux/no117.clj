(ns quux.no117)

(defn pos [m c]
  (first
   (for [x (range (count m))
         y (range (count (first m)))
         :when (= (get-in m [x y]) c)]
     [x y])))

(defn steps [m [r c]]
  (let [nrows (count m)
        ncoloums (count (first m))]
    (set
     (for [x [-1 0 1]
           y [-1 0 1]
           :let [r2 (+ r x)
                 c2 (+ c y)]
           :when (and (>= r2 0) (< r2 nrows)
                      (>= c2 0) (< c2 ncoloums)
                      (or (and (= x 0) (not= y 0))
                          (and (not= x 0) (= y 0)))
                      (not= (get-in m [r2 c2]) \#))]
       [r2 c2]))))


(defn walk [m]
  (letfn [(reachable? [m s e walked]
            (if (= s e)
              true
              (let [ss (clojure.set/difference (steps m s) walked)]
                (if (empty? ss)
                  false
                  (if (some #{true}
                            (for [n ss]
                              (reachable? m n e (conj walked s))))
                    true false)))))]
    (reachable? m (pos m \M) (pos m \C) #{(pos m \M)})))
