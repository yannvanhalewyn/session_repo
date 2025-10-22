(ns session-repo.core
  (:require [io.github.humbleui.app :as app]
            [io.github.humbleui.window :as window]
            [io.github.humbleui.ui :as ui]
            [io.github.humbleui.canvas :as canvas]
            [session-repo.ui :as sui])
  (:gen-class))

(defonce *state (atom {:projects []
                       :current-project nil
                       :commits []
                       :current-commit nil}))

(defonce *window (atom nil))

(defn make-app []
  #_
  (ui/dynamic _ [state @*state]
    (sui/main-view state)))

(defn -main [& args]
  (ui/start-app!
    (reset! *window
      (ui/window
        {:title "Session Repo - Ableton Project Manager"
         :width 1200
         :height 800}
        (make-app))))
  (println "Session Repo started"))
