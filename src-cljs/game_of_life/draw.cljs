(ns game-of-life.draw
  (:require [game-of-life.game :as game]))

(def cell-size 10)
(def board-size (* cell-size 50))
(def glider #{[1 1] [1 3] [2 3] [2 2] [3 2]})

(defn- make-context []
  "Creates the native js context object"
  (let [canvas (.getElementById js/document "canvas")
        context (.getContext canvas "2d")]
    (set! (.-width canvas) board-size)
    (set! (.-height canvas) board-size)
    (set! (.-fillStyle context) "rgb(0, 0, 0)")
    context))

(defn- clear-board [context]
  (.clearRect context 0 0 board-size board-size))

(defn- draw-living-cell [x y context]
  (.fillRect context (* cell-size x) (* cell-size y) cell-size cell-size))

(defn- draw [living-cells context]
  (clear-board context)
  (doseq [[x y] living-cells]
    (draw-living-cell x y context)))

(defn- run [board context]
  "Draws the board, sleeps, and recurs"
  (draw board context)
  (js/setTimeout (fn [] (run (game/advance board) context)) 100))

(defn start []
  "Start the simulation"
  (run glider (make-context)))
