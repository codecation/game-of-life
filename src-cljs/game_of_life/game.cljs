(ns game-of-life.game)

(defn neighbors [[x y]]
  (set
    (for [dx [-1 0 1] dy [-1 0 1]
          :when (not= 0 dx dy)]
      [(+ dx x) (+ dy y)])))

(defn should-live?
  [living-cells [location occurrence-count]]
  (or (= occurrence-count 3)
      (and (= occurrence-count 2)
           (contains? living-cells location))))

(defn advance [living-cells]
  (->>
    living-cells
    (mapcat neighbors)
    (frequencies)
    (filter (partial should-live? living-cells))
    (map first)
    set))
