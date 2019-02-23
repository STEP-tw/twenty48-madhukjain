(ns twenty48.core
  (:gen-class))

(def add (partial map (partial apply +)))
(defn add-zeros [coll] (take 4 (flatten (conj coll (repeat 4 0)))))
(def group-by-identity (partial partition-by identity))
(def group-into-pair (comp (partial apply concat) 
                           (partial map (partial partition-all 2)) 
                           (partial group-by-identity)))
(def add-all (comp (partial add) (partial group-into-pair)))
(def move-grid (comp 
                    (partial add-zeros) 
                    (partial into []) 
                    (partial add-all) (partial remove zero?)))

(def move-grid-left
  "Moves an entire grid to the left"
  (partial map move-grid))

(def move-grid-right
  "Moves an entire grid to the right"
  (comp (partial map reverse)
        (partial move-grid-left)
        (partial map reverse)))

(def move-grid-down
  "Moves an entire grid down"
  (comp (partial apply map vector) 
        (partial move-grid-right) 
        (partial apply map vector)))

(def move-grid-up
  "Moves an entire grid up"
  (comp (partial apply map vector) 
        (partial move-grid-left) 
        (partial apply map vector)))
