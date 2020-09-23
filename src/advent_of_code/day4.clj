(ns advent-of-code.day4
  (:require [advent-of-code.core :refer [parse-int]]
            [clojure.string :as str]))

;; Has a double
;; Numbers always increase


(defn digits-ascend?
  "Ensures that digits are in ascending order"
  [n]
  (let [l (map #(Character/getNumericValue %) n)]
    (loop [fst (first l) scd (second l) rem (rest l)]
      (cond (> fst scd) false
            (>= (count rem) 1) (recur scd (first rem) (rest rem))
            :else true))))

(defn contains-double?
  "Ensures that digits have exactly 2 adjacent digits"
  [n]
  (let [matcher (re-matcher #"(\d)\1+" n)
        matches (re-find matcher)
        pairs   (filter #(= 2 (count %)) matches)]
    (if (> (count pairs) 0) true
        false )))

(defn meets-criteria?
  "Returns true if number meets criteria, false otherwise.
  Criteria: 2 same adjacent digits, always increase L -> R"
  [n]
  (let [s (str n)]
    (cond (false? (digits-ascend? s)) false
          (false? (contains-double? s)) false
          :else true)))

;;(= (meets-criteria? 112233) true)
;;(= (meets-criteria? 223450) false)
;;(= (meets-criteria? 123789) false)
(def input (range 134564 585159))

;; from https://github.com/fdlk/advent-2019/blob/master/src/advent_2019/day04.clj
;; Much better way, think more functional!
(def digit-counts
  (->> input
       (map str)
       (filter (fn is-monotonous [password] (= password (str/join (sort password)))))
       (map frequencies)
       (map vals)
       (map set)))

(def part1 (count (filter (fn [counts] (not= counts #{1})) digit-counts)))
(def part2 (count (filter (fn [counts] (contains? counts 2)) digit-counts)))

;; My naive + slow way ;=;
;; Also has a bug...
;; (count (filter meets-criteria? input))
