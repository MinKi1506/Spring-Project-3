<template>
  <div>
    <v-navigation-drawer app dark :src="require('../assets/food.jpg')">
      <template v-slot:img="props">
        <v-img :gradient="gradient" v-bind="props" />
      </template>

      <div class="d-flex flex-column">
        <v-list-item>
          <v-list-item-content>
            <v-list-item-subtitle>
              오케스트로 식구들의 일상 공유 플랫폼
            </v-list-item-subtitle>
            <v-list-item-title class="text-h6">
              '잇(Eat)케스트로'
            </v-list-item-title>
            <v-list-item-subtitle> Ver.{{ version }} </v-list-item-subtitle>
            <v-list-item-subtitle> 지역: {{ region }} </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-divider></v-divider>

        <v-list dense nav>
          <v-list-item
            v-for="item in items"
            :key="item.title"
            link
            :to="item.to"
          >
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <div class="w-500px"></div>

        <!-- <div class="white align-self-end">
          <v-img
            :src="require('../assets/okestro.png')"
            width="200px"
            height="60px"
          ></v-img>
        </div> -->
      </div>
    </v-navigation-drawer>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    gradient: "rgba(0, 0, 0, .5), rgba(0, 0, 0, .5)",

    version: "",
    region: "",
    items: [
      { title: "대쉬보드", icon: "mdi-view-dashboard", to: "/dashBoard" },
      {
        title: "맛집 게시판",
        icon: "mdi-food",
        to: "/storeHome",
      },
      {
        title: "다이어트 게시판",
        icon: "mdi-weight-lifter",
        to: "/dietHome",
      },
    ],
    right: null,
  }),

  mounted() {
    this.getVersion();
    this.getRegion();
  },

  methods: {
    async getVersion() {
      const response = await axios.get("/apiConfigTest");
      this.version = response.data;
      console.log("받아온 버젼 값: " + response.data);
    },

    async getRegion() {
      const response = await axios.get("/appConfigTest");
      this.region = response.data;
      console.log("받아온 지역 값: " + response.data);
    },
  },
};
</script>

<style></style>
