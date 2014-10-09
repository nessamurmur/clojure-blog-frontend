(ns blog.components.posts
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [clojure.string :as string]))

(defn get-category [post]
  (string/capitalize (str (:category post) " ")))

(defn get-timestamp [post]
  (str " " (:created-at post)))

(defn post-view [post owner]
  (reify
    om/IRender
    (render [this]
      (dom/div #js {:className "post"}
        (dom/section nil
          (dom/div #js {:className "post-preview col-xs-10  no-gutter"}
            (dom/h2 nil
              (dom/a #js {:href "post.html"} (:title post)))
            (dom/p nil (:body post))
            (dom/p #js {:className "meta"}
              (dom/a #js {:href "category.html"} (get-category post))
              (dom/i #js {:className "entypo-bookmark"} (get-timestamp post)))))))))

(defn posts-view [app owner]
  (reify
    om/IRender
    (render [this]
      (apply dom/div nil
        (om/build-all post-view (:posts app))))))
