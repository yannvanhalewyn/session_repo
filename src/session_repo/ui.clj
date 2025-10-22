(ns session-repo.ui
  (:require [io.github.humbleui.ui :as ui]
            [io.github.humbleui.paint :as paint]))

(def ^:private padding 20)

(defn toolbar [state]
  (ui/row
    (ui/button
      (fn [] (println "Open project"))
      (ui/label "Open Project"))
    (ui/gap padding 0)
    (ui/button
      (fn [] (println "Commit"))
      (ui/label "Commit"))
    (ui/gap padding 0)
    (ui/button
      (fn [] (println "Checkout"))
      (ui/label "Checkout"))))

(defn commit-list [state]
  (ui/column
    (ui/label "Commits")
    (ui/gap 0 10)
    (if (empty? (:commits state))
      (ui/label "No commits yet")
      (ui/column
        (for [commit (:commits state)]
          (ui/row
            (ui/label (:hash commit))
            (ui/gap 10 0)
            (ui/label (:message commit))))))))

(defn project-info [state]
  (ui/column
    (ui/label "Project Info")
    (ui/gap 0 10)
    (if-let [project (:current-project state)]
      (ui/column
        (ui/label (str "Name: " (:name project)))
        (ui/gap 0 5)
        (ui/label (str "Path: " (:path project))))
      (ui/label "No project loaded"))))

(defn main-view [state]
  (ui/padding padding padding
    (ui/column
      (ui/label "Session Repo - Ableton Project Manager")
      (ui/gap 0 padding)
      (toolbar state)
      (ui/gap 0 padding)
      (ui/row
        (ui/width 400
          (project-info state))
        (ui/gap padding 0)
        (ui/halign 0
          (commit-list state))))))
