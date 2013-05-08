(ns quux.t-no111
  (:use midje.sweet quux.no111))

(def puzzle ["c o n j"
             "_ _ y _"
             "r _ _ #"])

(def puzzle2 ["c _ _ _"
              "d _ # e"
              "r y _ _"])

(fact "Should return true if given word can be exactly filled in to at least one consequent blank"
  (solve? ..answer.. ..puzzle..) => truthy
  (provided
    (blanks ..puzzle..) => [..b1.. ..b2..]
    (filled? ..answer.. ..b1..) => true
    (filled? ..answer.. ..b2..) => false))

(fact "Should return blanks include filled words"
  (blanks puzzle) => #{"__y_" "conj" "o__" "r__" "ny_" "j_" "c_r"}
  (blanks puzzle2) => #{"e" "ry__" "cdr" "__y" "_e_" "d_" "c___" "_"})

(fact "Should return true if the answer can be filled in to the blank"
  (filled? "conj" "conj") => truthy
  (filled? "conj" "cond") => falsey
  (filled? "conj" "con_") => truthy
  (filled? "conj" "c_n_") => truthy
  (filled? "conj" "____") => truthy
  (filled? "conj" "___") => falsey)
