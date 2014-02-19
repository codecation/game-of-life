(ns game-of-life.core-test
  (:require-macros [cemerick.cljs.test
                    :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t]))

(deftest is-working
  (is (= 2 2)))

(deftest other
  (is (= 3 3)))
