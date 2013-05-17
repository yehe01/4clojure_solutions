(ns quux.no124)

(defn candidates [board mark]
  (set (for [x (range (count board))
             y (range (count (first board)))
             :let [cand [x y]]
             :when (= (get-in board cand) mark)]
         cand)))

(defn transpose [b]
  (partition (count b)
             (for [i (range (count b))
                   j (range (count (first b)))]
               (get-in b [j i]))))

(defn legal? [board s e]
  (let [color (get-in board s)
        check-row (fn [b r c1 c2]
                    (let* [small (min c1 c2)
                           big (max c1 c2)
                           line (drop (inc small) (take big (nth b r)))]
                          (and (not (empty? line)) (every? #(and (not= % color) (not= % 'e)) line))))]
    (cond (not= (get-in board e) 'e) false
          (= (first s) (first e)) (check-row board (first s) (second s) (second e))
          (= (second s) (second e)) (check-row (transpose board) (second s) (first s) (first e))
          :else false)))

(defn legal-moves [board start]
  (set (filter #(legal? board start %) (candidates board 'e))))

(defn flipped-moves [board start]
  (let [moves (legal-moves board start)
        flip-row (fn [r c1 c2]
                   (let [small (min c1 c2)
                         big (max c1 c2)]
                     (set (map #(vector r %) (range (inc small) big)))))
        flip-colum (fn [c r1 r2]
                     (let [small (min r1 r2)
                           big (max r1 r2)]
                       (set (map #(vector % c) (range (inc small) big)))))
        flip-move (fn [start end]
                    (if (= (first start) (first end))
                      (flip-row (first start) (second start) (second end))
                      (flip-colum (second start) (first start) (first end))))]
    (reduce #(assoc % %2 (flip-move start %2)) {} moves)))

(fn [board mark]
  (let [transpose (fn  [b]
                    (partition (count b)
                               (for [i (range (count b))
                                     j (range (count (first b)))]
                                 (get-in b [j i]))))
        candidates (fn [board mark]
                     (set (for [x (range (count board))
                                y (range (count (first board)))
                                :let [cand [x y]]
                                :when (= (get-in board cand) mark)]
                            cand)))

        legal? (fn [board s e]
                 (let [color (get-in board s)
                       check-row (fn [b r c1 c2]
                                   (let* [small (min c1 c2)
                                          big (max c1 c2)
                                          line (drop (inc small) (take big (nth b r)))]
                                         (and (not (empty? line)) (every? #(and (not= % color) (not= % 'e)) line))))]
                   (cond (not= (get-in board e) 'e) false
                         (= (first s) (first e)) (check-row board (first s) (second s) (second e))
                         (= (second s) (second e)) (check-row (transpose board) (second s) (first s) (first e))
                         :else false)))

        legal-moves (fn [board start]
                      (set (filter #(legal? board start %) (candidates board 'e))))

        flip-row (fn [r c1 c2]
                   (let [small (min c1 c2)
                         big (max c1 c2)]
                     (set (map #(vector r %) (range (inc small) big)))))
        flip-colum (fn [c r1 r2]
                     (let [small (min r1 r2)
                           big (max r1 r2)]
                       (set (map #(vector % c) (range (inc small) big)))))
        flip-move (fn [start end]
                    (if (= (first start) (first end))
                      (flip-row (first start) (second start) (second end))
                      (flip-colum (second start) (first start) (first end))))]
    (into {} (mapcat (fn [start]
                       (reduce #(assoc % %2 (flip-move start %2)) {} (legal-moves board start)))
                     (candidates board mark)))))
