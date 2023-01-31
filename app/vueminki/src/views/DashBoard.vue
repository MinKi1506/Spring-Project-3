<template>
  <div class="d-flex">
    <SideNav />
    <div style="width: 100%">
      <v-row class="py-4">
        <v-col> 대시보드 </v-col>
      </v-row>
      <v-divider class="pt-8"></v-divider>

      <v-row>
        <v-col class="d-flex justify-space-around pb-4 px-4">
          <v-card width="330px" height="120px" class="pa-3">
            <v-row>
              <v-col> 내가 좋아요한 게시물 </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-end">
                "
                <h4 class="" style="color: #ffe082">{{ myLikeCount }}</h4>
                " 개
              </v-col>
            </v-row>
          </v-card>

          <v-card width="330px" height="120px" class="pa-3">
            <v-row>
              <v-col> 최다 추천 맛집</v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-end">
                "
                <h4 class="" style="color: #aed581">
                  {{ bestStore }}
                </h4>
                "
              </v-col>
            </v-row>
          </v-card>

          <v-card width="330px" height="120px" class="pa-3">
            <v-row>
              <v-col> 나의 맛집 포스팅 </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-end">
                "
                <h4 class="" style="color: #ba68c8">{{ myStoreCount }}</h4>
                " 개
              </v-col>
            </v-row>
          </v-card>

          <v-card width="330px" height="120px" class="pa-3">
            <v-row>
              <v-col> 나의 다이어트 포스팅</v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-end">
                "
                <h4 class="" style="color: #42a5f5">
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
          <v-card class="px-6 py-4" color="#FFE0B2">
            <v-row>
              <v-col>
                <h3 class="pb-2">이달의 Best 포스팅</h3>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-start">
                <h5>Best 맛집 포스팅</h5>
              </v-col>

              <v-divider vertical></v-divider>

              <v-col class="d-flex justify-start">
                <h5>Best 다이어트 포스팅</h5>
              </v-col>
            </v-row>
            <!-- 최다 추천 맛집 포스팅 -->
            <v-row class="d-flex">
              <v-col>
                <v-card
                  height="560"
                  class="px-3 my-3"
                  @click="goDetail(firstItem.storeId)"
                >
                  <v-row>
                    <v-col class="d-flex justify-center">
                      <h5 class="smallFont">{{ firstItem.title }}</h5>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col>
                      <v-img
                        src="../assets/food.jpg"
                        max-height="300"
                        max-width="280"
                      >
                      </v-img>
                    </v-col>
                    <v-col class="d-flex flex-column">
                      <p class="align-self-start" style="font-size: 25px">
                        {{ firstItem.createdDateTime }}
                      </p>
                      <p style="font-size: 20px">{{ firstItem.content }}</p>
                    </v-col>
                  </v-row>

                  <v-divider class="my-4"></v-divider>

                  <v-row class="d-flex justify-start align-start">
                    <v-col cols="6" class="">
                      <h6 class="">
                        '회사로부터 걸어서 &nbsp;<span class="red--text">{{
                          firstItem.travelTime
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
                      <p class="">{{ firstItem.likeCount }}</p>
                    </v-col>
                    <v-col cols="3" class="d-flex align-start">
                      <v-img
                        src="../assets/comment.png"
                        max-width="40px"
                        max-height="60px"
                      ></v-img>
                      <p>{{ firstItem.commentCount }}</p>
                    </v-col>
                  </v-row>
                </v-card>
              </v-col>

              <v-divider vertical></v-divider>
              <!-- 베스트 다이어트 포스팅으로 수정 예정 -->
              <v-col>
                <v-card
                  height="560"
                  class="px-3 my-3"
                  @click="goDetail(firstItem.storeId)"
                >
                  <v-row>
                    <v-col class="d-flex justify-center">
                      <h5 class="smallFont">{{ firstItem.title }}</h5>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col>
                      <v-img
                        src="../assets/food.jpg"
                        max-height="300"
                        max-width="280"
                      >
                      </v-img>
                    </v-col>
                    <v-col class="d-flex flex-column">
                      <p class="align-self-start" style="font-size: 25px">
                        {{ firstItem.createdDateTime }}
                      </p>
                      <p style="font-size: 20px">{{ firstItem.content }}</p>
                    </v-col>
                  </v-row>

                  <v-divider class="my-4"></v-divider>

                  <v-row class="d-flex justify-start align-start">
                    <v-col cols="6" class="">
                      <h6 class="">
                        '회사로부터 걸어서 &nbsp;<span class="red--text">{{
                          firstItem.travelTime
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
                      <p class="">{{ firstItem.likeCount }}</p>
                    </v-col>
                    <v-col cols="3" class="d-flex align-start">
                      <v-img
                        src="../assets/comment.png"
                        max-width="40px"
                        max-height="60px"
                      ></v-img>
                      <p>{{ firstItem.commentCount }}</p>
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

      myLikeCount: "",
      bestStore: "",
      myStoreCount: "",
      myDietCount: "0",

      firstItemStoreId: "",
      firstItem: {
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

      secondItemDietId: "",
      secondItem: {
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
    };
  },
  created() {
    this.getSession();
    this.getInfo();
    console.log("store의 userId값: " + this.$store.state.userId);
  },

  components: { SideNav },
  methods: {
    //현재 이용자의 userId 조회
    // getUserId() {
    //   console.log("현재 사용자의 userId: " + this.$store.state.userId);
    // },

    //각종 대쉬보드 정보 가져오기
    getInfo() {
      this.getMyLikeCount();
      this.getBestStore();
      this.getMyStoreCount();
      // this.getMyDietCount();
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

    //최다 추천맛집 정보 가져오기
    async getBestStore() {
      try {
        const response = await axios.get("/getBestStore");
        this.bestStore = response.data.storeName;
        this.firstItem.storeId = response.data.id;
        this.firstItem.title = response.data.title;
        this.firstItem.content = response.data.content;
        this.firstItem.storeName = response.data.storeName;
        this.firstItem.address = response.data.address;
        this.firstItem.menu = response.data.menu;
        this.firstItem.travelTime = response.data.travelTime;
        this.firstItem.likeCount = response.data.likeCount;
        this.firstItem.commentCount = response.data.commentCount;

        let year = response.data.createdDateTime.substring(0, 4);
        let month = response.data.createdDateTime.substring(5, 7);
        let day = response.data.createdDateTime.substring(8, 10);

        this.firstItem.createdDateTime =
          year + "년 " + month + "월 " + day + "일에 작성된 글입니다.";
      } catch (error) {
        console.log("최다 추천맛집 불러오기 에러!!:" + error);
      }
    },

    //최다 응원 다이어트 정보 가져오기

    //사용자의 맛집 포스팅 갯수 가져오기
    async getMyStoreCount() {
      try {
        const response = await axios.get("/getCountStore/" + this.loginedId);
        this.myStoreCount = response.data;
      } catch (error) {
        console.log("좋아요 갯수 불러오기 에러!!:" + error);
      }
    },

    //사용자의 다이어트 포스팅 갯수 가져오기
    // async getMyDietCount() {
    //   try {
    //     const response = await axios.get(
    //       "http://localhost:8083/like/" + loginedId
    //     );
    //   } catch (error) {
    //     console.log("좋아요 갯수 불러오기 에러!!:" + error);
    //   }
    // },

    getSession() {
      this.loginedId = parseInt(sessionStorage.getItem("loginedId"));
      console.log("현재 로그인된 사용자의 id session:" + this.loginedId);
    },

    //클릭 시, 데이터 전달하여 상세페이지로 가기
    goDetail(storeId) {
      this.$router.push({
        name: "storeDetail",
        path: "/storeDetail/:i",
        params: { storeId: storeId },
      });
      console.log("보내는 storeId값" + storeId);
    },
  },
};
</script>

<style></style>
