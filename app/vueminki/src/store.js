import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userId: 3,
  },
  mutations: {
    setUserId(state, data) {
      state.userId = data;
    },
  },
});
