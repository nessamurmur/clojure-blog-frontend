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
       :tags ["hello-word" "clojure"] :url "http://google.com"
       :created-at "10/10/2014" :updated-at "10/10/2014"}
      {:title "Hello world" :body "Hello there!"
       :tags ["hello-word" "clojure"] :url "http://google.com"
       :created-at "10/10/2014" :updated-at "10/10/2014"}
      {:title "Hello world" :body "Hello there!"
       :tags ["hello-word" "clojure"] :url "http://google.com"
       :created-at "10/10/2014" :updated-at "10/10/2014"}]}))

(defn post-view [post owner]
  (reify
    om/IRenderState
    (render-state [this {:keys [delete]}]
      (dom/article nil
        (dom/span #js {:className "pull-right"} (:created-at post))
        (dom/h2 #js {:className "post-title"}
                (dom/a #js {:href (:url post)} (:title post)))
        (dom/p nil (:body post))))))

(defn posts-view [app owner]
  (reify
    om/IRender
    (render [this]
      (dom/div #js {:className "container"}
        (dom/h1 #js {:className "blog-title"} "Blog")
        (apply dom/ul nil
           (om/build-all post-view (:posts app)))))))

(om/root posts-view app-state
         {:target (. js/document (getElementById "posts"))})
