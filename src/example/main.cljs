(ns example.main
  (:require [cljs.nodejs :as nodejs]
            ["http" :as http]
            ["request" :as request]
            ["highland" :as highland]))

(nodejs/enable-util-print!)

(defn ^:export main []
  (-> (request "https://httpbin.org/ip")
      (.pipe (highland))
      (.each (fn [output]
                (print (.toString output "utf8"))))))

#_(http-post {:url "http://67.216.200.53/add-s-exp-history"
              :formdata {:in_put "1" :out_put "1" :buffer_name "test"}
              :callback (fn [status error body] (prn status)) })
(defn http-post
  [{:keys [url formdata callback]}]
  (request/post
   (clj->js
    {:url url
     :form formdata})
   (fn [error response body]
     (callback (.-statusCode response) error body))))
