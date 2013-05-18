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

(defn legal-moves [board start]
  (into {}  (filter (fn [[k v]] (not (empty? v))) (map #(vector % (legal-path board start %)) (candidates board 'e)))))

(def bla
  (fn [board mark]
    (let [transpose (fn [b]
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
                                     (let [small (min c1 c2)
                                           big (max c1 c2)
                                           line (drop (inc small) (take big (nth b r)))]
                                       (and (not (empty? line)) (every? #(and (not= % color) (not= % 'e)) line))))
                         check-diagnal (fn [x1 y1 xr yr]
                                         (let [r (map (fn [[xi yi]] (get-in board (vector (+ x1 xi) (+ y1 yi)))) (partition 2 (interleave xr yr)))]
                                           (and (not (empty? r)) (every? #(and (not= % color) (not= % 'e))
                                                                         r))))
                         x1 (first s)
                         x2 (first e)
                         y1 (second s)
                         y2 (second e)
                         xr (if (> (- x2 x1) 0) (drop 1 (range (- x2 x1))) (sort > (drop 1 (range (- x2 x1) 0))))
                         yr (if (> (- y2 y1) 0) (drop 1 (range (- y2 y1))) (sort > (drop 1 (range (- y2 y1) 0))))]
                     (cond (= x1 x2) (check-row board x1 y1 y2)
                           (= y1 y2) (check-row (transpose board) y1 x1 x2)
                           (or (= -1 (/ (- x1 x2) (- y1 y2))) (= 1 (/ (- x1 x2) (- y1 y2)))) (check-diagnal x1 y1 xr yr)
                           :else false)))

          legal-moves (fn [board start]
                        (set (filter #(legal? board start %) (candidates board 'e))))

          flip-move (fn [s e]
                      (let [flip-row (fn [r c1 c2]
                                       (let [small (min c1 c2)
                                             big (max c1 c2)]
                                         (set (map #(vector r %) (range (inc small) big)))))
                            flip-colum (fn [c r1 r2]
                                         (let [small (min r1 r2)
                                               big (max r1 r2)]
                                           (set (map #(vector % c) (range (inc small) big)))))
                            flip-diagnal (fn [x1 y1 xr yr]
                                           (set (map (fn [[xi yi]] (vector (+ x1 xi) (+ y1 yi))) (partition 2 (interleave xr yr)))))
                            x1 (first s)
                            x2 (first e)
                            y1 (second s)
                            y2 (second e)
                            xr (if (> (- x2 x1) 0) (drop 1 (range (- x2 x1))) (sort > (drop 1 (range (- x2 x1) 0))))
                            yr (if (> (- y2 y1) 0) (drop 1 (range (- y2 y1))) (sort > (drop 1 (range (- y2 y1) 0))))]
                        (cond (= x1 x2) (flip-row x1 y1 y2)
                              (= y1 y2) (flip-colum y1 x1 x2)
                              :else (flip-diagnal x1 y1 xr yr))))]
      (into {} (mapcat (fn [start]
                         (reduce #(assoc % %2 (flip-move start %2)) {} (legal-moves board start)))
                       (candidates board mark))))))

(defn legal-path [board s e]
  (let [color (get-in board s)
        check-row (fn [b r c1 c2]
                    (let [small (min c1 c2)
                          big (max c1 c2)
                          line (drop (inc small) (take big (nth b r)))]
                      (and (not (empty? line)) (every? #(and (not= % color) (not= % 'e)) line))))
        check-diagnal (fn [x1 y1 xr yr]
                        (let [r (map (fn [[xi yi]] (get-in board (vector (+ x1 xi) (+ y1 yi)))) (partition 2 (interleave xr yr)))]
                          (and (not (empty? r)) (every? #(and (not= % color) (not= % 'e)) r))))
        x1 (first s)
        x2 (first e)
        y1 (second s)
        y2 (second e)
        xr (if (> (- x2 x1) 0) (drop 1 (range (- x2 x1))) (sort > (drop 1 (range (- x2 x1) 0))))
        yr (if (> (- y2 y1) 0) (drop 1 (range (- y2 y1))) (sort > (drop 1 (range (- y2 y1) 0))))
        flip-row (fn [r c1 c2]
                   (let [small (min c1 c2)
                         big (max c1 c2)]
                     (set (map #(vector r %) (range (inc small) big)))))
        flip-colum (fn [c r1 r2]
                     (let [small (min r1 r2)
                           big (max r1 r2)]
                       (set (map #(vector % c) (range (inc small) big)))))
        flip-diagnal (fn [x1 y1 xr yr]
                       (set (map (fn [[xi yi]] (vector (+ x1 xi) (+ y1 yi))) (partition 2 (interleave xr yr)))))]
    (cond (and (= x1 x2) (check-row board x1 y1 y2)) (flip-row x1 y1 y2)
          (and (= y1 y2) (check-row (transpose board) y1 x1 x2)) (flip-colum y1 x1 x2)
          (and (not= x1 x2) (not= y1 y2) (or (= -1 (/ (- x1 x2) (- y1 y2))) (= 1 (/ (- x1 x2) (- y1 y2)))) (check-diagnal x1 y1 xr yr)) (flip-diagnal x1 y1 xr yr)
          :else #{})))
