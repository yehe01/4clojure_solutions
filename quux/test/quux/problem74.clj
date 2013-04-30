(ns quux.t-4clojure
  (:use midje.sweet))

(unfinished )

(defn find-immediate-win [mark board]
  (set (filter #(win? (set-place board % mark) mark) (find-empty-places board))))

(fact "return co-ordinates of places which can result in a immediate win"
  (find-immediate-win :o [[:x :x :o]
                          [:o :e :o]
                          [:x :e :e]]) => #{[2 2] [1 1]})

(defn set-place [board co-ord mark]
  (assoc-in board co-ord mark))

(fact "set mark :x or :o on given co-ordinates"
  (set-place [[:o :e :e]
              [:o :x :o]
              [:x :x :e]] [2 2] :x) => [[:o :e :e]
                                        [:o :x :o]
                                        [:x :x :x]])

(defn find-empty-places [board]
  (set (for [ncol (range 3) nrow (range 3)
             :when (= (get-in board [nrow ncol]) :e)]
         [nrow ncol])))

(fact "return co-ordinates of all empty places as a set of pairs"
  (find-empty-places [[:o :e :e]
                      [:o :x :o]
                      [:x :x :e]]) => #{[2 2] [0 1] [0 2]})

(defn win? [board k]
            (some #(every? (conj #{} k) %)
                  (concat board
                          (apply map vector board)
                          (conj []
                                (for [idx (range 3)]
                                  (get-in board [idx idx]))
                                (for [idx (range 3)]
                                  (get-in board [idx (- 2 idx)]))))))

(fn [mark board]
  (letfn [(win? [board k]
            (some #(every? (conj #{} k) %)
                  (concat board
                          (apply map vector board)
                          (conj []
                                (for [idx (range 3)]
                                  (get-in board [idx idx]))
                                (for [idx (range 3)]
                                  (get-in board [idx (- 2 idx)]))))))]
    (set
     (filter #(win? (assoc-in board % mark) mark)
             (set (for [ncol (range 3) nrow (range 3)
                        :when (= (get-in board [nrow ncol]) :e)]
                    [nrow ncol]))))))
