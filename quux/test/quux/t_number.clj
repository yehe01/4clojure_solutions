(ns quux.t-number
  (:use midje.sweet quux.number))

(fact "split parcels into separate ones"
  (digit-parcels ["    _ "
                  "  | _|"
                  "  ||_ "]) => [["   "
                                  "  |"
                                  "  |"] [" _ "
                                          " _|"
                                          "|_ "]])


(fact "convert 1-parcel to an interger 1"
  (digit ["   "
          "  |"
          "  |"]) => 1)


(fact "convert 2-parcel to an interger 2"
  (digit [" _ "
          " _|"
          "|_ "]) => 2)


(fact "an account number is constructed from character parcels"
  (account-number ..parcels..) => "01"
  (provided
    (digit-parcels ..parcels..) => [..0-parcel.. ..1-parcel..]
    (digit ..0-parcel..) => 0
    (digit ..1-parcel..) => 1))
