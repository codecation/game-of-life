(ns game-of-life.draw
  (:require [game-of-life.game :as game]))

(def cell-size-in-pixels 10)
(def board-width-in-cells 50)
(def board-size-in-pixels (* cell-size-in-pixels board-width-in-cells))

(def current-run-id (atom 0))
(def board-update-delay 100)

(def glider #{[1 1] [1 3] [2 3] [2 2] [3 2]})

(defn- make-context []
  "Creates the native js context object"
  (let [canvas (.getElementById js/document "canvas")
        context (.getContext canvas "2d")]
    (set! (.-width canvas) board-size-in-pixels)
    (set! (.-height canvas) board-size-in-pixels)
    (set! (.-fillStyle context) "rgb(0, 0, 0)")
    context))

(defn- clear-board [context]
  (.clearRect context 0 0 board-size-in-pixels board-size-in-pixels))

(defn- draw-living-cell [x y context]
  (.fillRect context (* cell-size-in-pixels x) (* cell-size-in-pixels y) cell-size-in-pixels cell-size-in-pixels))

(defn- draw [living-cells context]
  (clear-board context)
  (doseq [[x y] living-cells]
    (draw-living-cell x y context)))

(defn- run [board context this-run-id]
  "Draws the board, sleeps, and recurs"
  (when (= this-run-id (deref current-run-id))
    (draw board context)
    (js/setTimeout (fn [] (run (game/advance board) context this-run-id)) board-update-delay)))

(defn random-cells []
  "Returns a set of randomly-chosen cells within the board dimensions"
  (let [number-of-cells (* 0.2 board-width-in-cells board-width-in-cells)
        random-cell-generator (fn [] (repeatedly 2 (fn [] (rand-int board-width-in-cells))))]
    (into #{} (repeatedly number-of-cells random-cell-generator))))

(defn start [living-cells]
  "Start the simulation"
  (swap! current-run-id inc)
  (run living-cells (make-context) (deref current-run-id)))

(defn start-glider []
  "Starts simulation with a glider placed in the board"
  (start glider))

(defn start-random []
  "Starts simulation with a randomly generated set of live cells"
  (start (random-cells)))
