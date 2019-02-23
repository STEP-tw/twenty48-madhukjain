(ns twenty48.core
  (:gen-class))

(def partition-as-set-of-two (partial partition-all 2))

(def add-two-numbers (partial apply +))
(def add-set-of-two (partial map add-two-numbers))
(defn make-set-of-four [coll] (take 4 (flatten (conj coll (repeat 4 0)))))

(def remove-zeros&-partition-by-identity (comp (partial partition-by identity) (partial remove zero?)))
(def partition-as-set-of-two-in-one-list (comp (partial apply concat) (partial map partition-as-set-of-two) (partial remove-zeros&-partition-by-identity)))
(def add-numbers (comp (partial add-set-of-two) (partial partition-as-set-of-two-in-one-list)))
(def get-final-set (comp (partial make-set-of-four) (partial into []) (partial add-numbers)))

(defn add-zeros [coll] (take-last 4 (flatten (conj [(repeat 4 0)] coll))))
(def get-final-for-right-shift (comp (partial add-zeros) (partial into []) (partial add-numbers)))

(def move-grid-right
  "Moves an entire grid to the right"
  (partial map get-final-for-right-shift)
  )

(def move-grid-left
  "Moves an entire grid to the left"
  (partial map get-final-set))

(def move-grid-down
  "Moves an entire grid down"
  (comp (partial apply map vector) (partial map get-final-for-right-shift) (partial apply map vector)))

(def move-grid-up
  "Moves an entire grid up"
  (comp (partial apply map vector) (partial map get-final-set) (partial apply map vector)))
