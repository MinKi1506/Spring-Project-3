import Vue from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import router from "./router/index.js";
import store from "./store.js";
import axios from "axios";

Vue.config.productionTip = false;

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;

// axios.defaults.baseURL = "http://100.0.0.157:28083";
axios.defaults.baseURL = "http://localhost:8083";
axios.defaults.headers.post["Content-Type"] = "application/json";

new Vue({
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
