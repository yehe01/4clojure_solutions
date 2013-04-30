(ns quux.t-4clojure
  (:use midje.sweet))

(defn palindromic-numbers [n]
  (letfn [(new-p [c newn]
            (if (odd? c)
              (read-string (str newn (clojure.string/reverse (subs newn 0 (dec (count newn))))))
              (read-string (str newn (clojure.string/reverse newn)))))
          (next-p [n]
            (let [s (str n)
                  c (count s)
                  half-p (subs s 0 (quot (inc c) 2))
                  can1 (new-p c half-p)
                  can2 (new-p c (str (inc (read-string half-p))))]
              (if (every? #(= \9 %) s)
                (+ n 2)
                (if (> can1 n) can1 can2))))]
    (drop-while #(not= (str %) (clojure.string/reverse (str %))) (iterate next-p n))))
