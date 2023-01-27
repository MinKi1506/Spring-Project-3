<template>
  <div class="d-flex">
    <SideNav />
    <div>
      <v-row class="py-4">
        <v-col> 대시보드 </v-col>
      </v-row>
      <v-divider class="pt-8"></v-divider>
      <h2>{{ this.$store.state.userId }}</h2>

      <v-row class="d-flex justify-space-around pb-4 px-4">
        <v-card width="250px" height="120px" class="">좋아요한 게시물</v-card>
        <v-card width="250px" height="120px">최다 추천 맛집</v-card>
        <v-card width="250px" height="120px">나의 맛집 포스팅</v-card>
        <v-card width="250px" height="120px">나의 다이어트 포스팅</v-card>
      </v-row>
      <v-row>
        <v-col cols="8" class="pl-8">
          <v-card class="d-flex flex-column px-6 py-4">
            <h3 class="pb-2">오늘의 Best 포스팅</h3>
            <v-card width="full" height="270" class="mb-4">맛집소개 1</v-card>
            <v-card width="full" height="270">맛집소개 2</v-card>
          </v-card>
        </v-col>
        <v-col cols="4" class="pr-8">
          <v-card width="full" height="630" class="d-flex flex-column py-4">
            <h4>오늘의 점심메뉴 룰렛 돌리기</h4>
            <h4>룰렛</h4>
            <v-btn class="mx-16">돌리기</v-btn>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import SideNav from "../components/SideNav.vue";

export default {
  created() {
    this.getSession();
  },

  components: { SideNav },
  methods: {
    //현재 이용자의 userId 조회
    // getUserId() {
    //   console.log("현재 사용자의 userId: " + this.$store.state.userId);
    // },

    getSession() {
      const loginedId = parseInt(sessionStorage.getItem("loginedId"));
      console.log("현재 로그인된 사용자의 id session:" + loginedId);
    },
  },
};
</script>

<style></style>
