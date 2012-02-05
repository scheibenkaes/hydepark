(ns hydepark.core)

(def empty-presentation
  {:slides [] :info {}})

(def ^:dynamic *presentation* (atom empty-presentation))

(def ^:dynamic *backend* nil)

(defn add-slide [slide]
  (swap! *presentation* update-in [:slides] conj slide))

(defprotocol PresentationBackend
  (render [this presentation]))

(defn defpresentation
  [& info]
  (swap! *presentation* update-in [:info] merge (apply hash-map info)))

(defn slide
  "Define a single slide. body must consist of key-val-pairs for slide info and a last element which is used as the content of the slide."
  [& body]
  [:div (apply hash-map (butlast body)) (last body)])

(defn defslide [& body]
  (-> (apply slide body) add-slide))

