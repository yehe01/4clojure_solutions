(ns quux.t-4clojure
  (:use midje.sweet))

(defn intervals [s]
  (loop [result []
         cur []
         left (sort s)]
    (if (empty? left)
      (if (empty? cur)
        []
        (conj result (vector (first cur) (last cur))))
      (let [n (first left)]
        (if (or (empty? cur) (<= (- n (last cur)) 1))
          (recur result (conj cur n) (rest left))
          (recur (conj result (vector (first cur) (last cur))) [n] (rest left)))))))

(intervals [])
