(ns quux.no111)

(defn filled? [answer blank]
  (if (not= (count answer) (count blank))
    false
    (every? (fn [[a b]]
              (or (= a b)
                  (= b \_))) (map vector answer blank))))

(defn blanks [puzzle]
  (let [clean-puzzle (map #(clojure.string/replace % " " "") puzzle)]
    (set (mapcat #(clojure.string/split % #"#")
                 (map #(apply str %)
                      (concat (map vec clean-puzzle)
                              (apply map vector clean-puzzle)))))))

(defn solve? [answer puzzle]
  (not (empty? (filter #(filled? answer %) (blanks puzzle)))))


(fn [answer puzzle]
  (letfn [(filled? [answer blank]
            (if (not= (count answer) (count blank))
              false
              (every? (fn [[a b]]
                        (or (= a b)
                            (= b \_))) (map vector answer blank))))
          (blanks [puzzle]
            (let [clean-puzzle (map #(clojure.string/replace % " " "") puzzle)]
              (set (mapcat #(clojure.string/split % #"#")
                           (map #(apply str %)
                                (concat (map vec clean-puzzle)
                                        (apply map vector clean-puzzle)))))))]
    (not (empty? (filter #(filled? answer %) (blanks puzzle))))))
