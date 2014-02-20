(ns game-of-life.core-test
  (:require-macros [cemerick.cljs.test
                    :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t] 
            [game-of-life.core :as core]))

(deftest neighbors-test
  (is (= #{[2 3] [3 3] [3 2] [3 1] [2 1] [1 1] [1 2] [1 3]}
         (core/neighbors [2 2]))))

(def block-still-life #{[1 1] [1 2] [2 2] [2 1]})
(def blinker #{[1 2] [2 2] [3 2]})

(deftest advance-test
  (is (= #{}
         (core/advance #{[3 3]})))
  (is (= #{}
         (core/advance #{[0 0] [8 8]})))
  (is (= block-still-life (core/advance block-still-life)))
  (is (= blinker (core/advance (core/advance blinker)))))

(deftest should-live?-test
  (is (core/should-live? #{} [[2 2] 3]))
  (is (core/should-live? #{[2 2] [2 1]} [[2 2] 2]))
  (is (not (core/should-live? #{} [[2 2] 2])))
  (is (not (core/should-live? #{[1 1] [1 2] [3 2] [3 3]} [[2 2] 4]))))
