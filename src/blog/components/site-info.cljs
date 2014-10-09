(ns blog.components.site-info
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(defn site-info-view [app owner]
  (let [primary-info (:primary-info app)
        secondary-info (:secondary-info app)]
    (reify
      om/IRender
      (render [this]
        (dom/div nil
          (dom/div #js {:className "primary-info"}
            (dom/h1 nil (:site-title primary-info))
            (dom/p nil (:site-description primary-info)))
          (dom/div #js {:className "secondary-info"}
            (dom/p nil (:body secondary-info))))))))
