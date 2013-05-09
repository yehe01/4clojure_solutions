(ns quux.t-no117
  (:use midje.sweet quux.no117))

(fact "Return a collection of next possible steps from M"
  (steps ["M     "
          "      "
          "      "
          "      "
          "    ##"
          "    #C"] [0 0]) => #{[0 1] [1 0]}
  (steps ["M#    "
          "      "
          "      "
          "      "
          "    ##"
          "    #C"] [0 0]) => #{[1 0]}
  (steps ["M#    "
          "      "
          "      "
          "      "
          "    ##"
          "    #C"] [1 0]) =>)

(fact "Return the position of M"
  (pos ["M     "
        "      "
        "      "
        "      "
        "    ##"
        "    #C"] \M) => [0 0]
  (pos ["      "
        " M    "
        "      "
        "      "
        "    ##"
        "    #C"] \M) => [1 1])

(fact "Return the position of C"
  (pos ["M     "
        "      "
        "      "
        "      "
        "    ##"
        "    #C"] \C) => [5 5]
  (pos ["      "
        " M    "
        "      "
        "      "
        "    ##"
        "   C #"] \C) => [5 3])

;.;. The right word may be effective, but no word was ever as effective as a
;.;. rightly timed pause. -- Twain
(fact "Should return true if C is reachable from M"
  (walk ["M   "
         "  ##"
         "  #C"]) => falsey
  (walk ["M     "
         "      "
         "    ##"
         "    #C"]) => falsey)
