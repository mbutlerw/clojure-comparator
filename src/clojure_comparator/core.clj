(ns clojure-comparator.core
  (:gen-class))

(require '[clojure.string :as str])

(defn -main
  [args]
  (collect-companies))

(defn string-trimmer [string]
    (str/replace
      (str/replace string "</a></p>" "") ">" ""))

(defn collect-companies []
  (map str/lower-case
    (into []
      (map string-trimmer
        (re-seq  #">[\w| |.]+</a></p>"
          (get
            (str/split
              (slurp "http://clojure.org/community/companies") #"ulist") 1))))))

(defn collect-hiring-partners []
  (map str/lower-case
    (str/split
      (slurp "doc/hiring_partners.txt") #"\n")))

(filter (fn [x]
  (= x "8th light")) (collect-companies))
