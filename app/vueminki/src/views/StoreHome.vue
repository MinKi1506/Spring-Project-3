<template>
  <div>
    <SideNav />
    <v-img src="https://wallpaperaccess.com/full/6670365.jpg">
      <v-row class="py-4">
        <v-col class="ml-4 pl-4"> 맛집 게시판 </v-col>
      </v-row>
      <v-divider class="pt-8"></v-divider>

      <v-row class="d-flex justify-center align-center" style="height: 50px">
        <v-col>
          <v-form v-on:submit.prevent="searchStore">
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
          <v-btn @click="goStoreAdd()" class="mr-16" color="blue lighten-3"
            >📝 포스팅 하기</v-btn
          >
        </v-col>
      </v-row>

      <v-row class="px-16 d-flex flex-column justify-center align-center">
        <v-col cols="5">
          <v-card
            color="#EF5350"
            class="px-8 py-3 overflow-y-auto"
            height="800"
            max-height="800"
            v-scroll:#scroll-target="onScroll"
          >
            <v-card class="px-4 py-2" color="#FFCDD2">
              <!-- 여기서부터 -->
              <v-card
                height="370"
                class="px-3 my-4 smallSmallFont"
                v-for="(item, i) in items"
                :key="i"
                @click="goDetail(item.storeId)"
              >
                <v-row>
                  <v-col class="d-flex justify-start">
                    <p class="smallSmallFont">
                      {{ item.storeId }}번 째 맛집:
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
                      상호명:
                      <span class="smallSmallFont" style="color: #757575">{{
                        item.storeName
                      }}</span>
                    </p>
                    <p>
                      주소:
                      <span class="smallSmallFont" style="color: #757575">{{
                        item.address
                      }}</span>
                    </p>
                    <p>
                      주 메뉴:
                      <span class="smallSmallFont" style="color: #757575">{{
                        item.menu
                      }}</span>
                    </p>
                  </v-col>
                </v-row>

                <v-divider class="my-4"></v-divider>

                <v-row class="d-flex justify-start align-start">
                  <v-col cols="6" class="">
                    <h6 class="">
                      🏢 회사로부터 걸어서 &nbsp;<span class="red--text">{{
                        item.travelTime
                      }}</span>
                      분 거리에 있어요!
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
              <!-- 여기까지 1개 -->
            </v-card>
          </v-card>
        </v-col>
      </v-row>
    </v-img>
  </div>
</template>

<script>
import axios from "axios";
import SideNav from "../components/SideNav.vue";

export default {
  data() {
    return {
      searchKeyword: "",
      keywordCategory: [
        "제목으로 검색",
        "맛집 상호명으로 검색",
        "본문 내용으로 검색",
      ],
      selectedCategory: "제목으로 검색",

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
      searchedItems: [],
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
    //전체 맛집 포스팅 리스트 조회
    async getStoreList() {
      var vm = this;
      try {
        const response = await axios.get("/getStoreList");
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

          vm.items.push(vm.item);
          vm.item = {};
        }
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

    searchStore() {
      if (this.selectedCategory == "제목으로 검색") {
        this.searchStoreByTitle();
      } else if (this.selectedCategory == "맛집 상호명으로 검색") {
        this.searchStoreByStoreName();
      } else if (this.selectedCategory == "본문 내용으로 검색") {
        this.searchStoreByContent();
      }
    },

    //포스팅 제목으로 검색
    async searchStoreByTitle() {
      var vm = this;
      vm.items = [];
      try {
        const response = await axios.get(
          "/searchStoreByTitle/" + this.searchKeyword
        );
        this.storeList = response.data;
        console.log("제목 검색으로 받아온 맛집 포스팅 리스트" + response.data);
        for (let i = 0; i < response.data.length; i++) {
          vm.item.storeId = this.storeList[i].id;
          vm.item.title = this.storeList[i].title;
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

          vm.items.push(vm.item); //비워준 items에 item (=store data)을 하나씩 추가
          vm.item = {};

          console.log("검색해서 변경된 items 리스트:" + vm.items);
        }
      } catch (error) {
        console.log("제목으로 검색하기 에러!!:" + error);
      }
    },

    //가게 상호명으로 검색
    async searchStoreByStoreName() {
      var vm = this;
      vm.items = [];
      try {
        const response = await axios.get(
          "/searchStoreByStoreName/" + this.searchKeyword
        );
        this.storeList = response.data;
        console.log(
          "상호명 검색으로 받아온 맛집 포스팅 리스트" + response.data
        );
        for (let i = 0; i < response.data.length; i++) {
          vm.item.storeId = this.storeList[i].id;
          vm.item.title = this.storeList[i].title;
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

          vm.items.push(vm.item); //비워준 items에 item (=store data)을 하나씩 추가
          vm.item = {};

          console.log("검색해서 변경된 items 리스트:" + vm.items);
        }
      } catch (error) {
        console.log("제목으로 검색하기 에러!!:" + error);
      }
    },
    //포스팅 본문 내용으로 검색
    async searchStoreByContent() {
      var vm = this;
      vm.items = [];
      try {
        const response = await axios.get(
          "/searchStoreByContent/" + this.searchKeyword
        );
        this.storeList = response.data;
        console.log("본문 검색으로 받아온 맛집 포스팅 리스트" + response.data);
        for (let i = 0; i < response.data.length; i++) {
          vm.item.storeId = this.storeList[i].id;
          vm.item.title = this.storeList[i].title;
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

          vm.items.push(vm.item); //비워준 items에 item (=store data)을 하나씩 추가
          vm.item = {};

          console.log("검색해서 변경된 items 리스트:" + vm.items);
        }
      } catch (error) {
        console.log("제목으로 검색하기 에러!!:" + error);
      }
    },
  },
};
</script>

<style></style>
