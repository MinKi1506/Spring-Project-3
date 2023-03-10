import Vue from "vue";
import Router from "vue-router";
import SignIn from "../views/SignIn";
import SignUp from "../views/SignUp";
import DashBoard from "../views/DashBoard";
import StoreHome from "../views/StoreHome";
import StoreAdd from "../views/StoreAdd";
import StoreEdit from "../views/StoreEdit";
import StoreDetail from "../views/StoreDetail";
import DietHome from "../views/DietHome";
import DietAdd from "../views/DietAdd";
import DietEdit from "../views/DietEdit";
import DietDetail from "../views/DietDetail";

Vue.use(Router);

export default new Router({
  mode: "history", // 해쉬값 제거 방식
  routes: [
    {
      path: "/",
      name: "signIn",
      component: SignIn
    },
    {
      path: "/signUp",
      name: "signUp",
      component: SignUp
    },
    {
      path: "/dashBoard",
      name: "dashBoard",
      component: DashBoard
    },
    {
      path: "/storeHome",
      name: "storeHome",
      component: StoreHome
    },
    {
      path: "/storeAdd",
      name: "storeAdd",
      component: StoreAdd
    },
    {
      path: "/storeEdit",
      name: "storeEdit",
      component: StoreEdit
    },
    {
      path: "/storeDetail",
      name: "storeDetail",
      component: StoreDetail
    },
    {
      path: "/dietHome",
      name: "dietHome",
      component: DietHome
    },
    {
      path: "/dietAdd",
      name: "dietAdd",
      component: DietAdd
    },
    {
      path: "/dietEdit",
      name: "dietEdit",
      component: DietEdit
    },
    {
      path: "/dietDetail",
      name: "dietDetail",
      component: DietDetail
    }
  ]
});
