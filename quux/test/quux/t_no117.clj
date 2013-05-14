(ns quux.t-no117
  (:use midje.sweet quux.no117))

(fact "Return a collection of next possible steps from given position"
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
          "#     "
          "      "
          "      "
          "    ##"
          "    #C"] [0 0]) => #{}
  (steps ["M#    "
          "      "
          "      "
          "      "
          "    ##"
          "    #C"] [2 2]) => #{[1 2] [3 2] [2 1] [2 3]}
  (steps ["M   "
          "  ##"
          "  #C"] [0 0]))

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

(fact "Return visited nodes in bfs search"
  (bfs ["M   "
        "  ##"
        "  #C"] [0 0] #{}) => #{[2 1] [1 0] [0 0] [1 1] [0 1] [0 2] [0 3] [2 0]})

(fact "Should return true if there exists a path from M to C"
  (search ["M   "
           "  ##"
           "  #C"]) => falsey
  (search  ["#######"
            "#     #"
            "#  #  #"
            "#M # C#"
            "#######"]) => truthy
  (search  ["M   C"]) => truthy)
