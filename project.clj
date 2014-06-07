(defproject game-of-life "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:plugins [[com.cemerick/clojurescript.test "0.2.3-SNAPSHOT"]]}}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2156"]]
  :hooks [leiningen.cljsbuild]
  :source-paths ["src-cljs" "test"]
  :plugins [[lein-cljsbuild "1.0.2"]
            [com.cemerick/clojurescript.test "0.2.2"]
            [com.cemerick/austin "0.1.4"]]
  :cljsbuild {:builds [{:source-paths ["src-cljs" "test"]
                        :compiler {:output-to "target/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}
                       {:id "test"
                        :source-paths ["src-cljs" "test"]
                        :notify-command ["phantomjs" :cljs.test/runner "target/main.js"]
                        :compiler {:output-to "target/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]
              :test-commands {"unit-tests" ["phantomjs" :runner "target/main.js"]}})
