<template>
  <div class="d-flex">
    <SideNav />
    <div style="width: 100%">
      <v-row class="py-4">
        <v-col class="ml-4 pl-4"> 대시보드 </v-col>
      </v-row>
      <v-divider class="pt-8"></v-divider>

      <v-row>
        <v-col class="d-flex justify-space-around pb-4 px-4">
          <v-card width="330px" height="120px" class="pa-3" color="#FFE0B2">
            <v-row>
              <v-col> 내가 좋아요한 게시물 </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-end">
                "
                <h4 class="" style="color: #90a4ae">{{ myLikeCount }}</h4>
                " 개
              </v-col>
            </v-row>
          </v-card>

          <v-card width="330px" height="120px" class="pa-3" color="#FFCC80">
            <v-row>
              <v-col> 최다 추천 맛집</v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-end">
                "
                <h4 class="" style="color: #90a4ae">
                  {{ bestStoreName }}
                </h4>
                "
              </v-col>
            </v-row>
          </v-card>

          <v-card width="330px" height="120px" class="pa-3" color="#FFB74D">
            <v-row>
              <v-col> 나의 맛집 포스팅 </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-end">
                "
                <h4 class="" style="color: #90a4ae">{{ myStoreCount }}</h4>
                " 개
              </v-col>
            </v-row>
          </v-card>

          <v-card width="330px" height="120px" class="pa-3" color="#FF9100">
            <v-row>
              <v-col> 나의 다이어트 포스팅</v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-end">
                "
                <h4 class="" style="color: #90a4ae">
                  {{ myDietCount }}
                </h4>
                " 개
              </v-col>
            </v-row>
          </v-card>
        </v-col>
      </v-row>
      <v-row class="px-4">
        <v-col>
          <v-card class="px-6 py-3" color="#FFE0B2">
            <v-row>
              <v-col>
                <h3 class="p1-2">이달의 Best 포스팅</h3>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-start">
                <h5>
                  Best
                  <span class="smallSmallFont" style="color: #ef5350"
                    >맛집</span
                  >
                  포스팅
                </h5>
              </v-col>

              <v-divider vertical></v-divider>

              <v-col class="d-flex justify-start">
                <h5>
                  Best
                  <span class="smallSmallFont" style="color: #42a5f5"
                    >다이어트</span
                  >
                  포스팅
                </h5>
              </v-col>
            </v-row>

            <!-- 최다 추천 맛집 포스팅 -->

            <v-row class="d-flex">
              <v-col>
                <v-card
                  height="560"
                  class="d-flex flex-column px-3 my-2"
                  @click="goStoreDetail(bestStore.storeId)"
                >
                  <v-row>
                    <v-col class="d-flex justify-center">
                      <h5 class="smallFont">{{ bestStore.title }}</h5>
                    </v-col>
                  </v-row>

                  <!-- <v-row>
                    <v-col>
                      <v-card max-height="270">
                        <v-img
                          max-width="400"
                          max-height="270"
                          height="270"
                          :src="`http://localhost:8085/image/${bestStoreFile}`"
                          alt="이미지"
                        />
                      </v-card>
                    </v-col>
                    <v-col class="d-flex flex-column">
                      <p class="align-self-start smallSmallFont">
                        "{{ bestStore.createdDateTime }}"
                      </p>
                      <p>
                        상호명:
                        <span class="smallSmallFont">{{
                          bestStore.storeName
                        }}</span>
                      </p>
                      <p>
                        주소:
                        <span class="smallSmallFont">{{
                          bestStore.address
                        }}</span>
                      </p>
                      <p>
                        주 메뉴:
                        <span class="smallSmallFont">{{ bestStore.menu }}</span>
                      </p>
                    </v-col>
                  </v-row> -->

                  <v-row>
                    <v-col>
                      <v-card max-height="270">
                        <v-img
                          max-width="400"
                          max-height="270"
                          height="270"
                          :src="`http://100.0.0.157:28085/image/${bestStoreFile}`"
                          alt="이미지"
                        />
                      </v-card>
                    </v-col>
                    <v-col class="d-flex flex-column">
                      <p class="align-self-start smallSmallFont">
                        "{{ bestStore.createdDateTime }}"
                      </p>
                      <p>
                        상호명:
                        <span class="smallSmallFont">{{
                          bestStore.storeName
                        }}</span>
                      </p>
                      <p>
                        주소:
                        <span class="smallSmallFont">{{
                          bestStore.address
                        }}</span>
                      </p>
                      <p>
                        주 메뉴:
                        <span class="smallSmallFont">{{ bestStore.menu }}</span>
                      </p>
                    </v-col>
                  </v-row>

                  <v-divider class="my-2"></v-divider>

                  <v-row>
                    <v-col class="d-flex align-center">
                      <p>{{ bestStore.content }}</p>
                    </v-col>
                  </v-row>

                  <v-divider class="my-2"></v-divider>

                  <v-row class="d-flex justify-start align-start">
                    <v-col cols="6" class="">
                      <h6 class="">
                        '회사로부터 걸어서 &nbsp;<span class="red--text">{{
                          bestStore.travelTime
                        }}</span>
                        분 거리에 있어요!'
                      </h6>
                    </v-col>
                    <v-col cols="3" class="d-flex align-start justify-center">
                      <v-img
                        src="../assets/storeLike.png"
                        max-width="50px"
                        max-height="60px"
                      ></v-img>
                      <p class="">{{ bestStore.likeCount }}</p>
                    </v-col>
                    <v-col cols="3" class="d-flex align-start">
                      <v-img
                        src="../assets/comment.png"
                        max-width="40px"
                        max-height="60px"
                      ></v-img>
                      <p>{{ bestStore.commentCount }}</p>
                    </v-col>
                  </v-row>
                </v-card>
              </v-col>

              <v-divider vertical></v-divider>

              <!-- 베스트 다이어트 포스팅 -->

              <v-col>
                <v-card
                  height="560"
                  class="d-flex flex-column px-3 my-2"
                  @click="goDietDetail(bestDiet.dietId)"
                >
                  <v-row>
                    <v-col class="d-flex justify-center">
                      <h5 class="smallFont">{{ bestDiet.title }}</h5>
                    </v-col>
                  </v-row>

                  <!-- <v-row>
                    <v-col>
                      <v-card max-height="270">
                        <v-img
                          max-width="400"
                          max-height="270"
                          height="270"
                          :src="`http://localhost:8085/dietImage/${bestDietFile}`"
                          alt="이미지"
                        />
                      </v-card>
                    </v-col>
                    <v-col class="d-flex flex-column">
                      <p class="align-self-start smallSmallFont">
                        "{{ bestDiet.createdDateTime }}"
                      </p>
                      <p>
                        목표:
                        <span class="smallSmallFont">{{ bestDiet.goal }}</span>
                      </p>
                      <p>
                        기간:
                        <span class="smallSmallFont">{{
                          bestDiet.period
                        }}</span>
                      </p>
                    </v-col>
                  </v-row> -->

                  <v-row>
                    <v-col>
                      <v-card max-height="270">
                        <v-img
                          max-width="400"
                          max-height="270"
                          height="270"
                          :src="`http://100.0.0.157:28085/dietImage/${bestDietFile}`"
                          alt="이미지"
                        />
                      </v-card>
                    </v-col>
                    <v-col class="d-flex flex-column">
                      <p class="align-self-start smallSmallFont">
                        "{{ bestDiet.createdDateTime }}"
                      </p>
                      <p>
                        목표:
                        <span class="smallSmallFont">{{ bestDiet.goal }}</span>
                      </p>
                      <p>
                        기간:
                        <span class="smallSmallFont">{{
                          bestDiet.period
                        }}</span>
                      </p>
                    </v-col>
                  </v-row>

                  <v-divider class="my-2"></v-divider>

                  <v-row>
                    <v-col class="d-flex align-center">
                      <p>{{ bestDiet.content }}</p>
                    </v-col>
                  </v-row>

                  <v-divider class="my-2"></v-divider>

                  <v-row class="d-flex justify-start align-start">
                    <v-col
                      >패널티:
                      <span class="red--text smallSmallFont">{{
                        bestDiet.penalty
                      }}</span>
                    </v-col>

                    <v-col cols="3" class="d-flex align-start justify-center">
                      <v-img
                        src="../assets/cheerUp.png"
                        max-width="50px"
                        max-height="60px"
                      ></v-img>
                      <p class="">{{ bestDiet.cheerCount }}</p>
                    </v-col>
                    <v-col cols="3" class="d-flex align-start">
                      <v-img
                        src="../assets/comment.png"
                        max-width="40px"
                        max-height="60px"
                      ></v-img>
                      <p>{{ bestDiet.dietCommentCount }}</p>
                    </v-col>
                  </v-row>
                </v-card>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import SideNav from "../components/SideNav.vue";

export default {
  data() {
    return {
      loginedId: 1,

      myLikeCount: "0",
      bestStoreName: "미분당",
      myStoreCount: "0",
      myDietCount: "0",

      bestStore: {
        storeId: "",
        title: "",
        createdDateTime: "",
        content: "",
        storeName: "",
        address: "",
        menu: "",
        travelTime: "",
        likeCount: 0,
        commentCount: 0,
      },

      bestDiet: {
        dietId: "",
        title: "",
        createdDateTime: "",
        content: "",
        goal: "",
        period: "",
        penalty: "",
        cheerCount: 0,
        dietCommentCount: 0,
        bestStoreFile: "",
        bestDietFile: "",
      },
    };
  },
  created() {
    this.getSession();
    this.getInfo();
    console.log("store의 userId값: " + this.$store.state.userId);
  },

  components: { SideNav },
  methods: {
    //각종 대쉬보드 정보 가져오기
    getInfo() {
      this.getMyLikeCount();
      this.getBestStore();
      this.getMyStoreCount();
      this.getBestDiet();
      this.getMyDietCount();
      this.fetchStoreFiles();
      this.fetchDietFiles();
    },

    //사용자가 좋아요한 게시물 가져오기
    async getMyLikeCount() {
      try {
        const response = await axios.get("/like/" + this.loginedId);
        this.myLikeCount = response.data;
      } catch (error) {
        console.log("좋아요 갯수 불러오기 에러!!:" + error);
      }
    },

    //최다 추천 맛집 포스팅 정보 가져오기
    async getBestStore() {
      try {
        const response = await axios.get("/getBestStore");
        this.bestStore.storeName = response.data.storeName;
        this.bestStore.storeId = response.data.id;
        this.bestStore.title = response.data.title;
        this.bestStore.content = response.data.content;
        this.bestStore.storeName = response.data.storeName;
        this.bestStore.address = response.data.address;
        this.bestStore.menu = response.data.menu;
        this.bestStore.travelTime = response.data.travelTime;
        this.bestStore.likeCount = response.data.likeCount;
        this.bestStore.commentCount = response.data.commentCount;

        let year = response.data.createdDateTime.substring(0, 4);
        let month = response.data.createdDateTime.substring(5, 7);
        let day = response.data.createdDateTime.substring(8, 10);

        this.bestStore.createdDateTime =
          year + "년 " + month + "월 " + day + "일에 작성된 글입니다.";
      } catch (error) {
        console.log("최다 추천맛집 불러오기 에러!!:" + error);
      }
    },

    //최다 추천 다이어트 포스팅 정보 가져오기
    async getBestDiet() {
      try {
        const response = await axios.get("/getBestDiet");
        this.bestDiet.dietId = response.data.id;
        this.bestDiet.title = response.data.title;
        this.bestDiet.content = response.data.content;
        this.bestDiet.goal = response.data.goal;
        this.bestDiet.period = response.data.period;
        this.bestDiet.penalty = response.data.penalty;
        this.bestDiet.cheerCount = response.data.cheerCount;
        this.bestDiet.dietCommentCount = response.data.dietCommentCount;

        let year = response.data.createdDateTime.substring(0, 4);
        let month = response.data.createdDateTime.substring(5, 7);
        let day = response.data.createdDateTime.substring(8, 10);

        this.bestDiet.createdDateTime =
          year + "년 " + month + "월 " + day + "일에 작성된 글입니다.";
      } catch (error) {
        console.log("최다 추천맛집 불러오기 에러!!:" + error);
      }
    },

    //사용자의 맛집 포스팅 갯수 가져오기
    async getMyStoreCount() {
      try {
        const response = await axios.get("/getCountStore/" + this.loginedId);
        this.myStoreCount = response.data;
      } catch (error) {
        console.log("유저 총 맛집 포스팅 갯수 조회 실패:" + error);
      }
    },

    // 사용자의 다이어트 포스팅 갯수 가져오기
    async getMyDietCount() {
      try {
        const response = await axios.get("/getCountDiet/" + this.loginedId);
        this.myDietCount = response.data;
      } catch (error) {
        console.log("유저 총 다이어트 포스팅 갯수 조회 실패:" + error);
      }
    },

    getSession() {
      this.loginedId = parseInt(sessionStorage.getItem("loginedId"));
      console.log("현재 로그인된 사용자의 id session:" + this.loginedId);
    },

    //클릭 시, 데이터 전달하여 해당 맛집 상세페이지로 가기
    goStoreDetail(storeId) {
      this.$router.push({
        name: "storeDetail",
        path: "/storeDetail/:i",
        params: { storeId: storeId },
      });
      console.log("보내는 storeId값" + storeId);
    },

    //클릭 시, 데이터 전달하여 해당 다이어트 상세페이지로 가기
    goDietDetail(dietId) {
      this.$router.push({
        name: "dietDetail",
        path: "/dietDetail/:i",
        params: { dietId: dietId },
      });
      console.log("보내는 dietId값" + dietId);
    },

    //해당 storeId를 가진 사진 data 가져와서 띄우기
    async fetchStoreFiles() {
      const response = await axios.get(
        // `http://localhost:8085/files/${this.bestStore.storeId}`
        `http://100.0.0.157:28085/files/${this.bestStore.storeId}`
      );
      this.bestStoreFile = response.data[0];
    },

    //해당 dietId를 가진 사진 data 가져와서 띄우기
    async fetchDietFiles() {
      const response = await axios.get(
        // `http://localhost:8085/dietFiles/${this.bestDiet.dietId}`
        `http://100.0.0.157:28085/dietFiles/${this.bestDiet.dietId}`
      );
      this.bestDietFile = response.data[0];
    },
  },
};
</script>

<style></style>
