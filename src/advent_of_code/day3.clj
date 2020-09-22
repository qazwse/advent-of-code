(ns advent-of-code.day3
  (:require [advent-of-code.core :refer [read-lines parse-int]]
            [clojure.string :as str]))

;; Calculate Manhattan Distance - point closest to origin where two lines intersect
;; Input given as D####, where D can be U[p] D[own] L[eft] R[ight]

(defn get-point
  "creates new point from direction code and previous point"
  [code [x y]]
  (let [dir (re-find #"\p{Alpha}" code)
        dgt (parse-int (re-find #"\d+" code))]
    (cond (= dir "U") [x (+ dgt y)]
          (= dir "R") [(+ dgt x) y]
          (= dir "D") [x (- y dgt)]
          (= dir "L") [(- x dgt) y])))

(defn to-instructions
  "converts vector of direction codes to vector of points"
  [codes]
  (loop [points [[0 0]]
         codes  codes]
    (if (empty? codes)
      points
      (let [prev (last points)
            next (get-point (first codes) prev)]
        (recur (conj points next) (rest codes))))))

(defn get-input
  [filename]
  (->> filename
       (read-lines)
       (map #(str/split % #","))
       (map to-instructions)))

(get-input "day3-input")
