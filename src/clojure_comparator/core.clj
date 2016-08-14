(ns clojure-comparator.core
  (:gen-class))

(require '[clojure.string :as str])

(defn -main
  [args]
  (map string-trimmer
    (re-seq  #">[\w| |.]+</a></p>"
      (get
        (str/split
          (slurp "http://clojure.org/community/companies") #"ulist") 1))))

(defn string-trimmer [string]
  (str/replace
    (str/replace string "</a></p>" "") ">" ""))

(defn collect-companies [args]

)
