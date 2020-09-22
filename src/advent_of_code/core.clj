(ns advent-of-code.core
  (:require [clojure.string :refer [split-lines]]
            [clojure.java.io :as io])
  (:gen-class))

(defn parse-int
  "Returns integer from string."
  [s]
  (Integer/parseInt s 10)) ;; 10 signifies decimal format

(defn read-lines
  "Returns all lines from a file in ./resource"
  [filename]
  (->> filename
       (io/resource)
       (slurp)
       (split-lines)))
