(ns hydepark.backend.impress
  [:use [hiccup page-helpers]]
  [:use [hydepark.core]])

(defn render-slide [[tag attrs content]]
  [tag (assoc attrs :class "slide") content])

(defn render-html [info slides]
  (html5
   [:head
    [:title (:title info)]
    (include-js "impress.js")]
   [:body
    [:div.container (map render-slide slides)]]))

(deftype ImpressBackend []
  hydepark.core.PresentationBackend
  (render [this presentation]
    (render-html (:info presentation) (:slides presentation))))

(def backend (hydepark.backend.impress.ImpressBackend.))

(defn ->str []
  (binding [*backend* backend]
    (render *backend* @*presentation*)))