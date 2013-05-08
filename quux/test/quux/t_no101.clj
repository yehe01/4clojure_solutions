(ns quux.t-no101
  (:use midje.sweet quux.no101))

(fact
  (ld "kitten" "sitting")               => 3
  (ld "closure" "clojure")              => 1
  (ld "clojure" "closure")              => 1
  (ld "xyx" "xyyyx")                    => 2
  (ld "" "123456")                      => 6
  (ld "Clojure" "Clojure")              => 0
  (ld "" "")                            => 0)
