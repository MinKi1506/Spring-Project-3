<template>
  <v-app>
    <!-- <div>
      <v-navigation-drawer app dark :src="require('./assets/food.jpg')">
        <template v-slot:img="props">
          <v-img :gradient="gradient" v-bind="props" />
        </template>

        <div class="b-flex w-100">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-subtitle>
                오케스트로 식구들의 일상 공유 플랫폼
              </v-list-item-subtitle>
              <v-list-item-title class="text-h6">
                '잇(Eat)케스트로'
              </v-list-item-title>
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

          <div class="white align-self-bottom">
            <v-img
              :src="require('./assets/okestro.png')"
              width="200px"
              height="60px"
            ></v-img>
          </div>
        </div>
      </v-navigation-drawer>
    </div> -->

    <v-main class="fontJua smallFont">
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",

  components: {},

  data: () => ({
    gradient: "rgba(0, 0, 0, .5), rgba(0, 0, 0, .5)",
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
};
</script>
<style>
@font-face {
  font-family: "jua";
  src: url("../public/BmJua.ttf");
}

.fontJua {
  font-family: "jua";
}

.smallSmallFont {
  font-size: 20px;
}

.smallFont {
  font-size: 30px;
}

.middleFont {
  font-size: 40px;
}

.bigFont {
  font-size: 50px;
}

.bigBigFont {
  font-size: 60px;
}
</style>
