(ns game-of-life.game-test
  (:require-macros [cemerick.cljs.test
                    :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t] 
            [game-of-life.game :as game]))

(deftest neighbors-test
  (is (= #{[2 3] [3 3] [3 2] [3 1] [2 1] [1 1] [1 2] [1 3]}
         (game/neighbors [2 2]))))

(def block-still-life #{[1 1] [1 2] [2 2] [2 1]})
(def blinker #{[1 2] [2 2] [3 2]})

(deftest advance-test
  (is (= #{}
         (game/advance #{[3 3]})))
  (is (= #{}
         (game/advance #{[0 0] [8 8]})))
  (is (= block-still-life (game/advance block-still-life)))
  (is (= blinker (game/advance (game/advance blinker)))))

(deftest should-live?-test
  (is (game/should-live? #{} [[2 2] 3]))
  (is (game/should-live? #{[2 2] [2 1]} [[2 2] 2]))
  (is (not (game/should-live? #{} [[2 2] 2])))
  (is (not (game/should-live? #{[1 1] [1 2] [3 2] [3 3]} [[2 2] 4]))))
