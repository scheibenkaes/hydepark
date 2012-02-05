(ns hydepark.core)

(def empty-presentation
  {:slides [] :info {}})

(defonce ^:dynamic *presentation* (atom empty-presentation))

(defn add-slide [slide]
  (swap! *presentation* update-in [:slides] conj slide))

(defprotocol PresentationBackend
  (slide-attributes [this] "")
  (render [this slides])
  (render-slide [this slide]))

(defn defpresentation
  [& info]
  (swap! *presentation* update-in [:info] merge (hash-map ~@info)))

(defn defslide [& body]
  (-> (apply slide body) add-slide))

(defn slide
  "Define a single slide. body must consist of key-val-pairs for slide info and a last element which is used as the content of the slide."
  [& body]
  [:div (apply hash-map (butlast body)) (last body)])
