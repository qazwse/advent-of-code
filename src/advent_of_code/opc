(ns aoc-clj.opc
  (:require [clojure.string  :as str]
            [clojure.edn     :as edn]
            [aoc-clj.core    :refer [read-lines]]))


(defn parse-int [s] (Integer/parseInt s))

(defn read-program
  "Reads the program into a vector"
  [filename]
  (mapv edn/read-string (mapv #(str/trim %) (str/split (read-lines filename) #","))))

(read-program "day2-input")


(defn get-op-code
  "Returns a symbol of the op-code"
  [oc]
  (cond (= oc 1)  :add
        (= oc 2)  :multiply
        (= oc 99) :halt
        :else     :halt))

(defn inc-ip
  "Returns the amount of steps to increase the instruction pointer"
  [ip oc]
  (cond (= oc :add) (+ ip 4)
        (= oc :multiply) (+ ip 4)))

(defn run-oc
  "Looks at given oc and calculates result"
  [oc a b]
  (cond
    (= oc :add) (+ a b)
    (= oc :multiply) (* a b)))

(defn execute-instruction
  "Returns a new memory array based on the instruction provided"
  [memory ip oc]
  (let [[p1 p2 store] (subvec memory (+ 1 ip) (+ 4 ip))
        arg1 (get memory p1)
        arg2 (get memory p2)
        result (run-oc oc arg1 arg2)]
    (assoc memory store result)))

(defn execute-computer
  "Where the magic happens"
  [memory]
  (loop [memory memory
         ip     0]
    (let [oc (get-op-code (get memory ip))]
      (if (= oc :halt)
        memory
        (recur (execute-instruction memory ip oc)
               (inc-ip ip oc))))))

(execute-computer [2 0 0 3])

(defn run-computer
  "Entryway into our opc simulator. Argument is file to memory, use absolute path."
  [filename]
  (execute-computer (read-program filename)))

;; (run-computer "./Programming/aoc-clj/src/aoc_clj/day2-input")
