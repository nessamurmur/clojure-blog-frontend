(ns blog.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [blog.components.posts :as post-component]
            [blog.components.site-info :as site-info-component]))

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
       :created-at "10/10/2014" :updated-at "10/10/2014"}]
     :primary-info
     {:site-title "Nate West" :site-description "A developers blog"}
     :secondary-info
     {:body "Remember to add social links later"}}))

(om/root post-component/posts-view app-state
         {:target (. js/document (getElementById "posts"))})

(om/root site-info-component/site-info-view app-state
         {:target (. js/document (getElementById "site-info"))})
