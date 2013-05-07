(ns quux.no106)

(defn gen-children [s e]
  (let [tmp (conj #{} (vector (* s 2) e) (vector (+ s 2) e))]
    (if (even? s) (conj tmp (vector (/ s 2) e))
        tmp)))

(defn new-candidates [candidates]
  (set
   (mapcat (fn [[a b]] (gen-children a b)) candidates)))

(defn equal-pairs-exist? [candidates]
  (not (empty? (filter (fn [[a b]] (= a b)) candidates))))

(defn number-maze [s e]
  (loop [d 1
         candidates (conj #{} (vector s e))]
    (if (equal-pairs-exist? candidates)
      (do (println (filter (fn [[a b]] (= a b)) candidates))
          d)
      (recur (inc d) (new-candidates candidates)))))
