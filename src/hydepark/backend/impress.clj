(ns hydepark.backend.impress
  [:use [hiccup core]]
  [:use [hydepark.core]])

(deftype ImpressBackend []
  PresentationBackend
  )

(def backend (ImpressBackend.))
