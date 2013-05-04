(ns quux.no82)

(defn make-graph [words]
  (into {}
        (for [s words]
          [s (filter #(similar? % s) words)])))

(defn find-paths [graph start seen]
  (if (seen start)
    seen
    (for [n (graph start)]
      (find-paths graph n (conj seen start)))))

(fact "find the right path"
  (find-paths (make-graph #{"hot" "cot"}) "cot" #{}) => '(#{"cot"} (#{"cot" "hot"} #{"cot" "hot"}))
  (provided (make-graph #{"cot" "hot"}) => {"cot" '("cot" "hot"), "hot" '("cot" "hot")}))

(fact "make a graph based on similar words"
  (make-graph #{"cot" "hot"}) => {"cot" '("cot" "hot"), "hot" '("cot" "hot")})

(defn similar? [w1 w2]
  (if (= (count w1) (count w2))
    (<= (count (filter (fn [[a b]] (not= a b)) (map vector w1 w2))) 1)
    (let [longerw (if (< (count w1) (count w2)) w2 w1)
          shorterw (if (< (count w1) (count w2)) w1 w2)]
      (if (some #{shorterw}
                (for [i (range (count longerw))]
                  (str (subs longerw 0 i) (subs longerw (inc i)))))
        true
        false))))

(fact "return true if two words's difference is one"
  (similar? "hat" "hot") => truthy
  (similar? "abs" "asb") => falsey
  (similar? "bc" "abc") => truthy
  (similar? "def" "df") => truthy
  (similar? "share" "shares") => truthy)

(fn word-chain? [words]
  (letfn [(similar? [w1 w2]
            (if (= (count w1) (count w2))
              (<= (count (filter (fn [[a b]] (not= a b)) (map vector w1 w2))) 1)
              (let [longerw (if (< (count w1) (count w2)) w2 w1)
                    shorterw (if (< (count w1) (count w2)) w1 w2)]
                (if (some #{shorterw}
                          (for [i (range (count longerw))]
                            (str (subs longerw 0 i) (subs longerw (inc i)))))
                  true
                  false))))

          (make-graph [words]
            (into {}
                  (for [s words]
                    [s (filter #(similar? % s) words)])))

          (find-paths [graph start seen]
            (if (seen start)
              seen
              (for [n (graph start)]
                (find-paths graph n (conj seen start)))))]
    (not (nil? (some (fn [start] (some #(= words %)
                                      (flatten (find-paths (make-graph words) start #{}))))
                     words)))))
