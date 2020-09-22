(ns aoc-clj.day3
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

;; Calculate Manhattan Distance - point closest to origin where two lines intersect
;; Input given as D####, where D can be U[p] D[own] L[eft] R[ight]


(defn parse-dir-code
  "Parse a D#### code and number, and return vector"
  [code point]
  (let [dir (re-find #"\p{Alpha}" code)
        dgt (Integer/parseInt (re-find #"\d+" code))]
    (cond (= dir "U") [point dgt]
          (= dir "R") [dgt point]
          (= dir "D") [point (- dgt)]
          (= dir "L") [(- dgt) point])))

(parse-dir-code "U1231" 0)

(defn format-input
  "Takes string of D#### codes and returns formatted list"
  [x]
  (let [vals (str/split x #",")]
    (loop [vals vals
           x (first vals)
           prev 0]
      )))

(defn read-input
  "reads input file, returns 2 vectors with coordinates of each path"
  [filename]
  (with-open [rdr (io/reader filename)]
    (doall (map format-input (line-seq rdr)))))

(read-input "./Programming/aoc-clj/src/aoc_clj/day3-input")

(format-input "D123,U123,L123")
