<template>
  <div>
    <SideNav />
    <v-row class="py-4">
      <v-col> 맛집 게시판 </v-col>
    </v-row>
    <v-divider class="pt-8"></v-divider>

    <v-row class="d-flex justify-center align-center" style="height: 50px">
      <v-col class="ml-16" cols="6">
        <v-form>
          <v-text-field
            height=""
            label="검색 키워드를 입력하세요"
            outlined
            clearable
          ></v-text-field>
        </v-form>
      </v-col>
      <v-col cols="1">
        <v-btn class="mb-8">🔍검색하기</v-btn>
      </v-col>
    </v-row>

    <v-row>
      <v-col class="d-flex justify-end mb-4">
        <v-btn
          type="submit"
          class="mr-16"
          color="blue lighten-3"
          @click="goStoreAdd()"
          >포스팅 하기</v-btn
        >
      </v-col>
    </v-row>

    <div class="px-16 d-flex flex-column justify-center">
      <v-card
        class="d-flex flex-column justify-center px-14 py-3 scroll"
        height="770"
      >
        <v-row>
          <!-- 여기서부터 -->
          <v-col>
            <v-card
              height="350"
              class="px-3 pt-1"
              v-for="(item, i) in items"
              :key="i"
              @click="goDetail(item.storeId)"
            >
              <v-row>
                <v-col class="d-flex justify-center">
                  <h5 class="smallFont">{{ item.title }}</h5>
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
                    {{ item.createdDateTime }}
                  </p>
                  <p style="font-size: 20px">{{ item.content }}</p>
                </v-col>
              </v-row>

              <v-divider class="my-4"></v-divider>

              <v-row class="d-flex justify-start align-start">
                <v-col cols="6" class="">
                  <h6 class="">
                    '회사로부터 걸어서 &nbsp;<span class="red--text">{{
                      item.travelTime
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
                  <p class="">{{ item.likeCount }}</p>
                </v-col>
                <v-col cols="3" class="d-flex align-start">
                  <v-img
                    src="../assets/comment.png"
                    max-width="40px"
                    max-height="60px"
                  ></v-img>
                  <p>{{ item.commentCount }}</p>
                </v-col>
              </v-row>
            </v-card>
          </v-col>
          <!-- 여기까지 1개 -->
        </v-row>
      </v-card>
      <div class="d-flex justify-center mt-5">
        <v-btn class="mr-2" width="10">◀︎</v-btn>
        <v-btn class="ml-2" width="10">▶</v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import SideNav from "../components/SideNav.vue";

export default {
  data() {
    return {
      maxStoreId: 0,
      storeList: [],
      item: {
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
      items: [],
    };
  },

  components: {
    SideNav,
  },
  created() {},

  mounted() {
    this.getStoreList();
  },
  methods: {
    // //현재 포스팅의 최대값 조회
    // async getMaxStoreId() {
    //   // var vm = this;
    //   try {
    //     const response = await axios.get("http://localhost:8083/getMaxStoreId");
    //     console.log("맛집 포스팅 최대값은" + response.data);
    //     this.maxStoreId = response.data;

    //     this.$store.commit("setMaxStoreId", response.data);
    //     console.log(
    //       "store에 저장된 맛집 포스팅 최대값" + this.$store.state.maxStoreId
    //     );
    //   } catch (error) {
    //     console.log("에러" + error);
    //   }
    // },

    //전체 맛집 포스팅 리스트 조회
    async getStoreList() {
      var vm = this;
      try {
        const response = await axios.get("http://localhost:8083/getStoreList");
        this.storeList = response.data;
        console.log("받아온 모든 맛집 포스팅" + response.data);
        for (let i = 0; i < response.data.length; i++) {
          vm.item.storeId = this.storeList[i].id;
          vm.item.title = this.storeList[i].title;
          // vm.item.createdDateTime = this.storeList[i].createdDateTime;
          vm.item.content = this.storeList[i].content;
          vm.item.storeName = this.storeList[i].storeName;
          vm.item.address = this.storeList[i].address;
          vm.item.menu = this.storeList[i].menu;
          vm.item.travelTime = this.storeList[i].travelTime;
          vm.item.likeCount = this.storeList[i].likeCount;
          vm.item.commentCount = this.storeList[i].commentCount;

          let year = this.storeList[i].createdDateTime.substring(0, 4);
          let month = this.storeList[i].createdDateTime.substring(5, 7);
          let day = this.storeList[i].createdDateTime.substring(8, 10);
          vm.item.createdDateTime =
            year + "년 " + month + "월 " + day + "일에 작성된 글입니다.";

          // vm.items.push(vm.storeList);
          vm.items.push(vm.item);
          vm.item = {};
        }
        console.log(this.storeList);
      } catch (error) {
        console.log("전체 리스트 가져오기 에러!!:" + error);
      }
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

    //클릭 시, 게시글 등록 페이지로 가기
    goStoreAdd() {
      this.$router.push("/storeAdd");
    },
  },
};
</script>

<style></style>
