(ns game-of-life.core)

(defn neighbors [[x y]]
  (set
    (for [dx [-1 0 1] dy [-1 0 1]
          :when (not= 0 dx dy)]
      [(+ dx x) (+ dy y)])))

;; collect all neighbors of live cells
;; count their frequencies
;; if frequency == 2 then add to living set ONLY if alive
;; if frequency == 3 then add to living set
;;
(defn should-live?
  [living-cells [location occurrence-count]]
  (or (= occurrence-count 3)
      (and (= occurrence-count 2)
           (contains? living-cells location))))

(defn advance [living-cells]
  (set
    (map first
      (filter (partial should-live? living-cells)
              (frequencies (mapcat neighbors living-cells))))))
