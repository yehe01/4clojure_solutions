(ns quux.t-4clojure
  (:use midje.sweet))

(unfinished )

(defn new-candidates [candidates]
  (set
   (mapcat (fn [[a b]] (gen-children a b)) candidates)))

(fact "generate new candidates"
  (new-candidates #{[3 12]}) => #{[6 12] [3 24] [5 12] [3 14] [3 6]})

(defn equal-pairs-exist? [candidates]
  (not (empty? (filter (fn [[a b]] (= a b)) candidates))))

(fact "tell if there are pairs whose two items are qual"
  (equal-pairs-exist? #{[6 12] [3 24] [5 12] [3 14] [3 6] [3 3]}) => truthy
  (equal-pairs-exist? #{[6 12] [3 24] [5 12] [3 14] [3 6]}) => falsey
  (equal-pairs-exist? #{[11 12] [9 14] [9 24] [18 12] [9 6]}) => falsey)

(defn number-maze [s e]
  (loop [d 1
         candidates (conj #{} (vector s e))]
    (if (equal-pairs-exist? candidates)
      (do (println (filter (fn [[a b]] (= a b)) candidates))
          d)
      (recur (inc d) (new-candidates candidates)))))

(fact "find shortest path of number pairs"
  (number-maze 3 12) => 3
  (number-maze 9 12) => 5
  (number-maze 1 1) => 1
  (number-maze 9 2) => 9
  (number-maze 5 9) => 3)

(defn gen-children [s e]
  (let [tmp (conj #{} (vector (* s 2) e) (vector (+ s 2) e))]
    (if (even? s) (conj tmp (vector (/ s 2) e))
        tmp)))

(fact "generate children of current pair"
  (gen-children 3 12) => #{[6 12] [5 12]}
  (gen-children 12 13) => #{[24 13] [6 13] [14 13]})


(fn number-maze [s e]
  (letfn [(gen-children [s e]
            (let [tmp (conj #{} (vector (* s 2) e) (vector (+ s 2) e))]
              (if (even? s) (conj tmp (vector (/ s 2) e))
                  tmp)))]
    (loop [d 1
           candidates (conj #{} (vector s e))]
      (if (not (empty? (filter (fn [[a b]] (= a b)) candidates)))
        d
        (recur (inc d) (set
                        (mapcat (fn [[a b]] (gen-children a b)) candidates)))))))
