(ns quux.t-4clojure
  (:use midje.sweet))

(unfinished )

(defn digit-parcels [parcels]
  (apply map vector
         (map (fn [s]
                (map #(apply str %)
                     (partition 3 s))) parcels)))

(fact "split parcels into separate ones"
  (digit-parcels ["    _ "
                  "  | _|"
                  "  ||_ "]) => [["   "
                                  "  |"
                                  "  |"] [" _ "
                                          " _|"
                                          "|_ "]])

(def one "     |  |")
(def two " _  _||_ ")

(def digits
  {one 1 two 2})

(defn digit [parcel]
  (get digits (apply str parcel)))

(fact "convert 1-parcel to an interger 1"
  (digit ["   "
          "  |"
          "  |"]) => 1)

(fact "convert 2-parcel to an interger 2"
  (digit [" _ "
          " _|"
          "|_ "]) => 2)

(defn account-number [parcels]
  (apply str (map digit (digit-parcels parcels))))

(fact
  (account-number ["    _  _     _  _  _  _  _ "
                   "  | _| _||_||_ |_   ||_||_|"
                   "  ||_  _|  | _||_|  ||_| _|"])
  => "123456789")

(fact "an account number is constructed from character parcels"
  (account-number ..parcels..) => "01"
  (provided
    (digit-parcels ..parcels..) => [..0-parcel.. ..1-parcel..]
    (digit ..0-parcel..) => 0
    (digit ..1-parcel..) => 1))
