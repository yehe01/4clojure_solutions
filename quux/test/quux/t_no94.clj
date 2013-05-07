(ns quux.t-no106
  (:use midje.sweet quux.no94))

(fact "Return the number of neighbors around"
  (num-of-neignbors 2 2 board) => 4
  (num-of-neignbors 1 1 board) => 3
  (num-of-neignbors 0 0 board) => 1)

(def board ["     #"
            " ##   "
            " ##   "
            "   ## "
            "   ## "
            "      "])

(def board2 ["     "
             "     "
             " ### "
             "     "
             "     "])

(facts "Return next generation of cell based on neighbors"
  (fact "Cells with more than three neighbors die"
    (cell-of-next-gen 2 2 board) => " "
    (cell-of-next-gen 3 2 board) => " ")
  (fact "Cells with less than two neighbors die"
    (cell-of-next-gen 0 0 board) => " "
    (cell-of-next-gen 1 5 board) => " ")
  (fact "Cells with exactly three neighbors live"
    (cell-of-next-gen 1 2 board) => "#"
    (cell-of-next-gen 1 2 board2) => "#")
  (fact "Live cells with two neighbors remain live"
    (cell-of-next-gen 2 2 board2) => "#"
    (cell-of-next-gen 1 1 board2) => " "))

(fact "Return the new board representing the next generation of cells"
  (new-board ["  "
              "  "]) => [" #"
                         "# "]
  (provided
    (cell-of-next-gen 0 0 anything) => " "
    (cell-of-next-gen 0 1 anything) => "#"
    (cell-of-next-gen 1 0 anything) => "#"
    (cell-of-next-gen 1 1 anything) => " "))

(fact "test 1"
  (new-board board) => ["      "
                        " ##   "
                        " #    "
                        "    # "
                        "   ## "
                        "      "])

(fact "test 2"
  (new-board board2) => ["     "
                         "  #  "
                         "  #  "
                         "  #  "
                         "     "])
