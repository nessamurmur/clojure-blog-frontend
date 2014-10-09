(ns blog.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(enable-console-print!)

(def app-state
  (atom
    {:posts
     [{:title "Hello world" :body "Hello there!"
       :category "clojure" :url "http://google.com"
       :created-at "10/10/2014" :updated-at "10/10/2014"}
      {:title "Hello world" :body "Hello there!"
       :category "clojure" :url "http://google.com"
       :created-at "10/10/2014" :updated-at "10/10/2014"}
      {:title "Hello world" :body "Hello there!"
       :category "clojure" :url "http://google.com"
       :created-at "10/10/2014" :updated-at "10/10/2014"}]}))

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
              (dom/a #js {:href "category.html"} (:category post))
              (dom/i #js {:className "link-spacer"})
              (dom/i #js {:className "entypo-bookmark"} (str " " (:created-at post))))))))))

(defn posts-view [app owner]
  (reify
    om/IRender
    (render [this]
      (apply dom/ul nil
        (om/build-all post-view (:posts app))))))

(om/root posts-view app-state
         {:target (. js/document (getElementById "posts"))})
