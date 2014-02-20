(ns game-of-life.core)

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

(def cell-size 10)
(def board-size (* cell-size 50))

(defn draw [living-cells]
  (let [canvas (.getElementById js/document "canvas")
        context (.getContext canvas "2d")]
    (set! (.-width canvas) board-size)
    (set! (.-height canvas) board-size)
    (set! (.-fillStyle context) "rgb(0, 0, 0)")
    (.clearRect context 0 0 board-size board-size)
    (doseq [[x y] living-cells]
      (.fillRect context (* cell-size x) (* cell-size y) cell-size cell-size))))

(defn run [board]
  (draw board)
  (js/setTimeout (fn [] (run (advance board))) 100))

(def glider #{[1 1] [1 3] [2 3] [2 2] [3 2]})

(defn start []
  (run glider))

