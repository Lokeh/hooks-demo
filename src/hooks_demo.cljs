(ns hooks-demo
  (:require ["react-dom" :as react-dom]
            [hooks-demo.hooks :as hooks :refer [<-state <-deref <-!state]]
            [hx.react :as hx :include-macros true]))

;;
;; Example using new useState hook
;;

(hx/defnc UseStateHook [_]
  (let [[count set-count] (<-state 0)]
    (hx/c [:div
           [:strong "useState Hook:"]
           " "
           [:button {:on-click #(set-count (inc count))}
            count]])))

(defonce my-state (atom 0))

(hx/defnc DerefHook [_]
  (let [count (<-deref my-state)]
    (hx/c [:div
           [:strong "<-deref Hook:"]
           " "
           [:button {:on-click #(swap! my-state inc)}
            count]])))

(hx/defnc StateAtomHook [_]
  (let [count (<-!state 0)]
    (hx/c [:div
           [:strong "<-!state Hook:"]
           " "
           [:button {:on-click #(swap! count inc)}
            @count]])))

(hx/defnc App [_]
  (hx/c [:div
         [UseStateHook]
         [DerefHook]
         [StateAtomHook]]))

(react-dom/render (hx/c [App]) (js/document.getElementById "app"))
