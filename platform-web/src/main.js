import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "@/assets/scss/reset.scss";
import "element-ui/lib/theme-chalk/index.css";
import axios from "@/api/axiosconfig";
import ElementUI from "element-ui";

Vue.use(ElementUI);
Vue.prototype.$http = axios;
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
