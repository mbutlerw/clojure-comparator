(ns clojure-comparator.core
  (:gen-class))

(require '[clojure.string :as str])

(defn -main
  [args]
  (collect-companies 4))

(defn string-trimmer [string]
  (str/replace
    (str/replace string "</a></p>" "") ">" ""))

(defn collect-companies [arg]
  (map string-trimmer
    (re-seq  #">[\w| |.]+</a></p>"
      (get
        (str/split
          (slurp "http://clojure.org/community/companies") #"ulist") 1)))
)

(collect-companies 4)
