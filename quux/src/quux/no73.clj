(ns quux.no73)

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

(defn win? [board k]
  (some #(every? (conj #{} k) %)
        (concat board
                (apply map vector board)
                (conj []
                      (for [idx (range 3)]
                        (get-in board [idx idx]))
                      (for [idx (range 3)]
                        (get-in board [idx (- 2 idx)]))))))
