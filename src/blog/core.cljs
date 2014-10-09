(ns blog.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [blog.components.posts :as post-component]))

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

(om/root post-component/posts-view app-state
         {:target (. js/document (getElementById "posts"))})
