(ns blog.components.posts
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(defn post-view [post owner]
  (reify
    om/IRender
    (render [this]
      (dom/section nil
        (dom/div #js {:className "post-preview col-xs-10  no-gutter"}
          (dom/h2 nil
            (dom/a #js {:href "post.html"} (:title post)))
          (dom/p nil (:body post))
          (dom/p #js {:className "meta"}
            (dom/a #js {:href "category.html"} (:category post))
            (dom/i #js {:className "link-spacer"})
            (dom/i #js {:className "entypo-bookmark"} (str " " (:created-at post)))))))))

(defn posts-view [app owner]
  (reify
    om/IRender
    (render [this]
      (apply dom/div #js {:className "post"}
        (om/build-all post-view (:posts app))))))
