(ns quux.no74)

(defn win? [board k]
            (some #(every? (conj #{} k) %)
                  (concat board
                          (apply map vector board)
                          (conj []
                                (for [idx (range 3)]
                                  (get-in board [idx idx]))
                                (for [idx (range 3)]
                                  (get-in board [idx (- 2 idx)]))))))

(defn set-place [board co-ord mark]
  (assoc-in board co-ord mark))

(defn find-empty-places [board]
  (set (for [ncol (range 3) nrow (range 3)
             :when (= (get-in board [nrow ncol]) :e)]
         [nrow ncol])))

(defn find-immediate-win [mark board]
  (set (filter #(win? (set-place board % mark) mark) (find-empty-places board))))
