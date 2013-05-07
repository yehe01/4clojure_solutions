(ns quux.number)

(defn digit-parcels [parcels]
  (apply map vector
         (map (fn [s]
                (map #(apply str %)
                     (partition 3 s))) parcels)))

(def one "     |  |")
(def two " _  _||_ ")
;;TODO: Complete digits

(def digits
  {one 1 two 2})

(defn digit [parcel]
  (get digits (apply str parcel)))

(defn account-number [parcels]
  (apply str (map digit (digit-parcels parcels))))
