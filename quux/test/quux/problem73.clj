(ns quux.t-4clojure
  (:use midje.sweet))

(unfinished )

(fn [s]
  (letfn [(win? [board k]
            (some #(every? (conj #{} k) %)
                  (concat board
                          (apply map vector board)
                          (conj []
                                (for [idx (range 3)]
                                  (get-in board [idx idx]))
                                (for [idx (range 3)]
                                  (get-in board [idx (- 2 idx)]))))))]
    (cond
     (win? s :x) :x
     (win? s :o) :o
     :else nil)))

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

(defn win? [board k]
  (some #(every? (conj #{} k) %)
        (concat board
                (apply map vector board)
                (conj []
                      (for [idx (range 3)]
                        (get-in board [idx idx]))
                      (for [idx (range 3)]
                        (get-in board [idx (- 2 idx)]))))))
