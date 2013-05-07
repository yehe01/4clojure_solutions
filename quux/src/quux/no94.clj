(ns quux.no94)


(defn num-of-neignbors [r c b]
  (let [n (count b)]
    (count (filter #(= % \#)
            (for [x [-1 0 1]
                  y [-1 0 1]
                  :let [r2 (+ r x)
                        c2 (+ c y)]
                  :when (and (>= r2 0) (< r2 n)
                             (>= c2 0) (< c2 n)
                             (not (and (= x 0)
                                       (= y 0))))]
              (get-in b [r2 c2]))))))


(defn cell-of-next-gen [r c b]
  (let [nb (num-of-neignbors r c b)]
    (cond
     (or (< nb 2) (> nb 3)) " "
     (= (get-in b [r c]) \#) "#"
     (= nb 3) "#"
     :else " ")))


(defn new-board [board]
  (let [n (range (count board))]
    (for [r n]
      (apply str
             (for [c n]
               (cell-of-next-gen r c board))))))
