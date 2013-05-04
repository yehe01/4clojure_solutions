(ns quux.no131
  (:use midje.sweet))

(unfinished )

(defn common-sum? [ss]
  (letfn [(power-set [s]
            (set
             (filter #(not= #{} %)
                     (loop [ret #{#{}}
                            left s]
                       (if (empty? left)
                         ret
                         (recur (concat (map #(conj % (first left)) ret)
                                        ret)
                                (rest left)))))))]
    (not (empty?
          (reduce clojure.set/intersection
                  (map (fn [pset]
                         (set (map #(reduce + %) pset)))
                       (map power-set ss)))))))

(fact "return true if subsets of all sets have a equivalent sum"
  (common-sum? test-data) => truthy)

(def test-data
  [#{-1 1}
   #{-2 2}
   #{-3 3}])
