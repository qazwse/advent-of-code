(ns advent-of-code.day1)

(defn fuel-calc
  "Calculate the fuel needed for a given mass"
  [mass]
  (-
   (Math/floor (/ mass 3))
   2))

(defn fuel-counter-upper
  "Calculate the fuel needed for a module + the fuel for it's fuel. When fuel required is <= 0 we're done."
  [mass]
  (let [weight (fuel-calc mass)]
    (if (<= weight 0)
      0
      (+ weight (fuel-counter-upper weight)))))
