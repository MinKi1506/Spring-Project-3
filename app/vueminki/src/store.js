import Vue from "vue";
import Vuex from "vuex";
import { createVuexPersistedState } from "vue-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createVuexPersistedState()],

  state: {
    userId: 3,
    maxStoreId: 0,
  },
  mutations: {
    setUserId(state, data) {
      state.userId = data;
    },

    setMaxStoreId(state, data) {
      state.maxStoreId = data;
    },
  },
});
