(ns blog.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(enable-console-print!)

(def app-state
  (atom
    {:posts
     [{:title "Hello world" :body "Hello there!" :tags ["hello-word" "clojure"] :url "http://google.com"}
      {:title "Hello world" :body "Hello there!" :tags ["hello-word" "clojure"] :url "http://google.com"}
      {:title "Hello world" :body "Hello there!" :tags ["hello-word" "clojure"] :url "http://google.com"}]}))

(defn post-view [post owner]
  (reify
    om/IRenderState
    (render-state [this {:keys [delete]}]
      (dom/article nil
        (dom/h2 {:className "post-title"}
                (dom/a #js {:href (:url post)} (:title post)))
        (dom/p nil (:body post))))))

(defn posts-view [app owner]
  (reify
    om/IRender
    (render [this]
      (dom/div nil
        (dom/h2 nil "Blog")
        (apply dom/ul nil
           (om/build-all post-view (:posts app)))))))

(om/root posts-view app-state
         {:target (. js/document (getElementById "posts"))})
