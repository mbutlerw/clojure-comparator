(ns clojure-comparator.core
  (require [clojure.string :as str])
  (:gen-class))

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
(defn in-both []
  (filter (fn [x]
    (some #{x} (collect-hiring-partners))) (collect-companies)))

(defn -main []
  (print (in-both)))
