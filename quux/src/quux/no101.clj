(ns quux.no101)

(defn ld [x y]
  (letfn [(max-len [i j]
            (let [newi (- i 1)
                  newj (- j 1)]
              (cond (= 0 i) j
                    (= 0 j) i
                    (= (nth x newi) (nth y newj)) (max-len newi newj)
                    :else (+ 1 (min (max-len i newj)
                                    (max-len newi j)
                                    (max-len newi newj))))))]
    (max-len (count x) (count y))))
