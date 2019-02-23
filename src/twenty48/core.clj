(ns twenty48.core
  (:gen-class))

(def add (partial map (partial apply +)))
(defn add-zeros-at-last [coll] (take 4 (flatten (conj coll (repeat 4 0)))))
(defn add-zeros-at-start [coll] (take-last 4 (flatten (conj [(repeat 4 0)] coll))))

(def remove-zeros&-partition-by-identity (comp (partial partition-by identity) (partial remove zero?)))
(def group-into-pair (comp 
                      (partial apply concat)
                      (partial map (partial partition-all 2))
                      (partial remove-zeros&-partition-by-identity)))

(def add-all (comp (partial add) (partial group-into-pair)))

(def get-final-set (comp 
                    (partial add-zeros-at-last) 
                    (partial into []) 
                    (partial add-all)))
(def get-final-for-right-shift (comp 
                                (partial add-zeros-at-start) 
                                (partial into []) 
                                (partial add-all)))

(def move-grid-right
  "Moves an entire grid to the right"
  (partial map get-final-for-right-shift))

(def move-grid-left
  "Moves an entire grid to the left"
  (partial map get-final-set))

(def move-grid-down
  "Moves an entire grid down"
  (comp (partial apply map vector) 
        (partial map get-final-for-right-shift) 
        (partial apply map vector)))

(def move-grid-up
  "Moves an entire grid up"
  (comp (partial apply map vector) 
        (partial map get-final-set) 
        (partial apply map vector)))
