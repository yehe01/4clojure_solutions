(ns quux.t-4clojure
  (:use midje.sweet))

(fn [expression]
  (let [ops {(quote /) /
             (quote +) +
             (quote -) -
             (quote *) *}]
    (fn eval
      ([m] (eval m expression))
      ([m exp]
         (let [[op & args] exp
               args (map #(or (m %) %) args)]
           (apply (ops op)
                  (map #(if (seq? %) (eval m %) %) args)))))))
