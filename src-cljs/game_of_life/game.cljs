(ns game-of-life.game)

(defn neighbors [[x y]]
  "Returns the set of neighbors within one unit
  of a given x y point"
  (set
    (for [dx [-1 0 1] dy [-1 0 1]
          :when (not= 0 dx dy)]
      [(+ dx x) (+ dy y)])))

(defn should-live?
  "Given a set of living cells and a frequency map of neighbors,
  return the set of cells that survived."
  [living-cells [location occurrence-count]]
  (or (= occurrence-count 3)
      (and (= occurrence-count 2)
           (living-cells location))))

(defn advance [living-cells]
  "Given a set of living cells, advance one generation, returning
  the set of cells that are now alive."
  (->>
    living-cells
    (mapcat neighbors)
    (frequencies)
    (filter (partial should-live? living-cells))
    (map first)
    set))
