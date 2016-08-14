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
  (str/lower-case
    (into []
      (map string-trimmer
        (re-seq  #">[\w| |.]+</a></p>"
          (get
            (str/split
              (slurp "http://clojure.org/community/companies") #"ulist") 1))))))

(defn collect-hiring-partners []
  (str/lower-case
    (str/split
      (slurp "doc/hiring_partners.txt") #"\n")))

(filter #(= (count %) 1)
(collect-companies)
(collect-hiring-partners)
