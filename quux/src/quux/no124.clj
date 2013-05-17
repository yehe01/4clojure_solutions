(ns quux.no124)

(defn candidates [board color]
  (set (for [x (range (count board))
             y (range (count (first board)))
             :let [cand [x y]]
             :when (= (get-in board cand) color)]
         cand)))

(defn legal? [board s e]
  (let [color (get-in board s)
        check-row (fn [b r c1 c2]
                    (let [c1 (min c1 c2)
                          c2 (max c1 c2)
                          line (drop (inc c1) (take c2 (nth b r)))]
                      (and (not (empty? line)) (every? #(and (not= % color) (not= % 'e)) line))))
        transpose (fn [b]
                   (partition (count b)
                              (for [i (range (count b))
                                    j (range (count (first b)))]
                                (get-in b [j i]))))]
    (if (not= (get-in board e) 'e)
      false
      (if (= (first s) (first e))
        (check-row board (first s) (second s) (second e))
        (check-row (transpose board) (second s) (first s) (first e))))))

(defn legal-moves [board start]
  (let [color (get-in bases start)
        [r c] start]
      (for [x [-1 0 1]
            y [-1 0 1]
            :when (or (and (= x 0) (not= y 0))
                      (and (= y 0) (not= x 0)))
            :let [r2 (+ r x)
                  c2 (+ c y)]
            :when ()
            ])))
