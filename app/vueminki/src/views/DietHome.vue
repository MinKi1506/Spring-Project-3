<template>
  <div>
    <SideNav />
    <v-img
      src="https://img.freepik.com/free-vector/watercolor-with-blue-detailed-background_1409-1170.jpg"
    >
      <v-row class="py-4">
        <v-col class="ml-4 pl-4"> 다이어트 게시판 </v-col>
      </v-row>
      <v-divider class="pt-8"></v-divider>

      <v-row class="d-flex justify-center align-center" style="height: 50px">
        <v-col>
          <v-form v-on:submit.prevent="searchDiet">
            <v-row class="d-flex justify-center align-center">
              <v-col cols="2">
                <v-select
                  v-model="selectedCategory"
                  :items="keywordCategory"
                  label="검색 종류"
                  solo
                ></v-select>
              </v-col>
              <v-col class="ml-16" cols="4">
                <v-text-field
                  v-model="searchKeyword"
                  height=""
                  label="검색 키워드를 입력하세요"
                  outlined
                  clearable
                ></v-text-field>
              </v-col>
              <v-col class="d-flex justify-center align-center" cols="1">
                <v-btn type="submit" class="mb-8">🔍검색하기</v-btn>
              </v-col>
            </v-row>
          </v-form>
        </v-col>
      </v-row>

      <v-row>
        <v-col class="d-flex justify-end mb-3">
          <v-btn @click="goDietAdd()" class="mr-16" color="blue lighten-3"
            >📝 포스팅 하기</v-btn
          >
        </v-col>
      </v-row>

      <v-row class="px-16 d-flex flex-column justify-center align-center">
        <v-col cols="5">
          <v-card
            color="#29B6F6"
            class="px-8 py-3 overflow-y-auto"
            height="800"
            max-height="800"
            v-scroll:#scroll-target="onScroll"
          >
            <v-card class="px-4 py-2" color="#B3E5FC">
              <!-- 여기서부터 -->
              <v-card
                height="370"
                class="px-3 my-4 smallSmallFont"
                v-for="(item, i) in items"
                :key="i"
                @click="goDetail(item.dietId)"
              >
                <v-row>
                  <v-col class="d-flex justify-start">
                    <p class="smallSmallFont">
                      {{ item.dietId }}번 째 다이어트 포스팅:
                      <span class="smallFont">"{{ item.title }}"</span>
                    </p>
                  </v-col>
                </v-row>

                <v-row>
                  <v-col class="d-flex flex-column">
                    <p class="align-self-start smallSmallFont">
                      <span class="smallSmallFont" style="color: #757575">
                        "{{ item.createdDateTime }}"
                      </span>
                    </p>
                    <p>
                      목표:
                      <span class="smallSmallFont" style="color: #757575">{{
                        item.goal
                      }}</span>
                    </p>
                    <p>
                      도전 기간:
                      <span class="smallSmallFont" style="color: #757575">{{
                        item.period
                      }}</span>
                    </p>
                    <p>
                      도전 실패 시, 패널티: &nbsp;<span class="red--text">{{
                        item.penalty
                      }}</span>
                    </p>
                  </v-col>
                </v-row>

                <v-divider class="my-4"></v-divider>

                <v-row class="d-flex justify-start align-start">
                  <v-col cols="6" class=""> </v-col>
                  <v-col cols="3" class="d-flex align-start justify-center">
                    <v-img
                      src="../assets/cheerUp.png"
                      max-width="50px"
                      max-height="60px"
                    ></v-img>
                    <p class="">{{ item.cheerCount }}</p>
                  </v-col>
                  <v-col cols="3" class="d-flex align-start">
                    <v-img
                      src="../assets/comment.png"
                      max-width="40px"
                      max-height="60px"
                    ></v-img>
                    <p>{{ item.dietCommentCount }}</p>
                  </v-col>
                </v-row>
              </v-card>
            </v-card>
            <!-- 여기까지 1개 -->
          </v-card>
        </v-col>
      </v-row>
    </v-img>
  </div>
</template>

<script>
import axios from "axios";
import SideNav from "@/components/SideNav.vue";

export default {
  components: { SideNav },
  created() {},

  mounted() {
    this.getDietList();
  },

  data() {
    return {
      searchKeyword: "",
      keywordCategory: ["제목으로 검색", "목표로 검색", "본문 내용으로 검색"],
      selectedCategory: "제목으로 검색",

      dietList: [],
      item: {
        dietId: "",
        title: "",
        createdDateTime: "",
        content: "",
        goal: "",
        period: "",
        penalty: "",
        cheerCount: 0,
        dietCommentCount: 0,
      },
      items: [],
      searchedItems: [],
    };
  },

  methods: {
    //전체 다이어트 포스팅 리스트 조회
    async getDietList() {
      var vm = this;
      try {
        const response = await axios.get("/getDietList");
        this.dietList = response.data;
        console.log("받아온 모든 다이어트 포스팅" + response.data);
        for (let i = 0; i < response.data.length; i++) {
          vm.item.dietId = this.dietList[i].id;
          vm.item.title = this.dietList[i].title;
          vm.item.content = this.dietList[i].content;
          vm.item.goal = this.dietList[i].goal;
          vm.item.period = this.dietList[i].period;
          vm.item.penalty = this.dietList[i].penalty;
          vm.item.cheerCount = this.dietList[i].cheerCount;
          vm.item.dietCommentCount = this.dietList[i].dietCommentCount;

          let year = this.dietList[i].createdDateTime.substring(0, 4);
          let month = this.dietList[i].createdDateTime.substring(5, 7);
          let day = this.dietList[i].createdDateTime.substring(8, 10);
          vm.item.createdDateTime =
            year + "년 " + month + "월 " + day + "일에 시작된 도전입니다.";

          vm.items.push(vm.item);
          vm.item = {};
        }
      } catch (error) {
        console.log("전체 리스트 가져오기 에러!!:" + error);
      }
    },

    //클릭 시, 데이터 전달하여 상세페이지로 가기
    goDetail(dietId) {
      this.$router.push({
        name: "dietDetail",
        path: "/dietDetail/:i",
        params: { dietId: dietId },
      });
      console.log("보내는 dietId값" + dietId);
    },

    //클릭 시, 다이어트 등록 페이지로 가기
    goDietAdd() {
      this.$router.push("/dietAdd");
    },

    searchDiet() {
      if (this.selectedCategory == "제목으로 검색") {
        this.searchDietByTitle();
      } else if (this.selectedCategory == "목표로 검색") {
        this.searchDietByGoal();
      } else if (this.selectedCategory == "본문 내용으로 검색") {
        this.searchDietByContent();
      }
    },

    //포스팅 제목으로 검색
    async searchDietByTitle() {
      var vm = this;
      vm.items = [];
      try {
        const response = await axios.get(
          "/searchDietByTitle/" + this.searchKeyword
        );
        this.dietList = response.data;
        console.log(
          "제목 검색으로 받아온 다이어트 포스팅 리스트" + response.data
        );
        for (let i = 0; i < response.data.length; i++) {
          vm.item.dietId = this.dietList[i].id;
          vm.item.title = this.dietList[i].title;
          vm.item.content = this.dietList[i].content;
          vm.item.goal = this.dietList[i].goal;
          vm.item.period = this.dietList[i].period;
          vm.item.penalty = this.dietList[i].penalty;
          vm.item.cheerCount = this.dietList[i].cheerCount;
          vm.item.dietCommentCount = this.dietList[i].dietCommentCount;

          let year = this.dietList[i].createdDateTime.substring(0, 4);
          let month = this.dietList[i].createdDateTime.substring(5, 7);
          let day = this.dietList[i].createdDateTime.substring(8, 10);
          vm.item.createdDateTime =
            year + "년 " + month + "월 " + day + "일에 시작된 도전입니다.";

          vm.items.push(vm.item);
          vm.item = {}; //비워준 items에 item (=diet data)을 하나씩 추가

          console.log("검색해서 변경된 items 리스트:" + vm.items);
        }
      } catch (error) {
        console.log("제목으로 검색하기 에러!!:" + error);
      }
    },

    //목표로 검색
    async searchDietByGoal() {
      var vm = this;
      vm.items = [];
      try {
        const response = await axios.get(
          "/searchDietByGoal/" + this.searchKeyword
        );
        this.dietList = response.data;
        console.log("목표 검색으로 받아온 맛집 포스팅 리스트" + response.data);
        for (let i = 0; i < response.data.length; i++) {
          vm.item.dietId = this.dietList[i].id;
          vm.item.title = this.dietList[i].title;
          vm.item.content = this.dietList[i].content;
          vm.item.goal = this.dietList[i].goal;
          vm.item.period = this.dietList[i].period;
          vm.item.penalty = this.dietList[i].penalty;
          vm.item.cheerCount = this.dietList[i].cheerCount;
          vm.item.dietCommentCount = this.dietList[i].dietCommentCount;

          let year = this.dietList[i].createdDateTime.substring(0, 4);
          let month = this.dietList[i].createdDateTime.substring(5, 7);
          let day = this.dietList[i].createdDateTime.substring(8, 10);
          vm.item.createdDateTime =
            year + "년 " + month + "월 " + day + "일에 시작된 도전입니다.";

          vm.items.push(vm.item);
          vm.item = {}; //비워준 items에 item (=diet data)을 하나씩 추가

          console.log("검색해서 변경된 items 리스트:" + vm.items);
        }
      } catch (error) {
        console.log("목표로 검색하기 에러!!:" + error);
      }
    },
    //포스팅 본문 내용으로 검색
    async searchDietByContent() {
      var vm = this;
      vm.items = [];
      try {
        const response = await axios.get(
          "/searchDietByContent/" + this.searchKeyword
        );
        this.dietList = response.data;
        console.log("본문 검색으로 받아온 맛집 포스팅 리스트" + response.data);
        for (let i = 0; i < response.data.length; i++) {
          vm.item.dietId = this.dietList[i].id;
          vm.item.title = this.dietList[i].title;
          vm.item.content = this.dietList[i].content;
          vm.item.goal = this.dietList[i].goal;
          vm.item.period = this.dietList[i].period;
          vm.item.penalty = this.dietList[i].penalty;
          vm.item.cheerCount = this.dietList[i].cheerCount;
          vm.item.dietCommentCount = this.dietList[i].dietCommentCount;

          let year = this.dietList[i].createdDateTime.substring(0, 4);
          let month = this.dietList[i].createdDateTime.substring(5, 7);
          let day = this.dietList[i].createdDateTime.substring(8, 10);
          vm.item.createdDateTime =
            year + "년 " + month + "월 " + day + "일에 시작된 도전입니다.";

          vm.items.push(vm.item);
          vm.item = {}; //비워준 items에 item (=diet data)을 하나씩 추가

          console.log("검색해서 변경된 items 리스트:" + vm.items);
        }
      } catch (error) {
        console.log("본문으로 검색하기 에러!!:" + error);
      }
    },
  },
};
</script>

<style></style>
