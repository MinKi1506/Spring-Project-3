<template>
  <div style="height: 100%">
    <SideNav />
    <v-img
      src="https://t3.ftcdn.net/jpg/02/33/61/10/360_F_233611043_CeH5NZOBm9RghKTxv6QaRgpSImppD6IG.jpg"
    >
      <v-row class="py-4">
        <v-col class="ml-4 pl-4">
          맛집 게시판 - 포스팅 <span class="purple--text">작성</span>
        </v-col>
      </v-row>
      <v-divider class="pt-8"></v-divider>

      <v-row class="d-flex justify-center">
        <v-col cols="6" class="ml-4">
          <v-card class="px-4 py-2" height="900" color="#FFF9C4">
            <h4>포스팅 제목</h4>
            <v-text-field
              v-model="item.title"
              solo
              clearable
              class=""
              height="30"
              placeholder="포스팅 제목을 입력해 주세요"
            ></v-text-field>
            <h4>상호명</h4>
            <v-text-field
              v-model="item.storeName"
              solo
              clearable
              class=""
              height="30"
              placeholder="맛집 상호명을 입력해 주세요"
            ></v-text-field>
            <h3>메뉴</h3>
            <v-text-field
              v-model="item.menu"
              solo
              clearable
              class=""
              height="30"
              placeholder="드셨던 메뉴, 혹은 대표 메뉴를 입력해 주세요"
            ></v-text-field>
            <h3>상세주소</h3>
            <v-text-field
              v-model="item.address"
              solo
              clearable
              class=""
              height="30"
              placeholder="맛집의 상세 주소를 입력해 주세요"
            ></v-text-field>
            <h3>이동 시간</h3>
            <v-text-field
              v-model="item.travelTime"
              solo
              clearable
              class=""
              height="30"
              placeholder="회사로부터 걸어서 몇 분 걸리나요?"
            ></v-text-field>

            <v-row class="d-flex justify-around">
              <v-col class="d-flex flex-column">
                <h3>본문</h3>
                <v-text-field
                  v-model="item.content"
                  solo
                  clearable
                  class=""
                  height="180"
                  placeholder="본문 내용을 입력해 주세요"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-card>
        </v-col>

        <v-col cols="1" class="mr-4">
          <div class="d-flex flex-column justify-center" style="height: 800px">
            <!-- 작성확인 모달 -->
            <v-dialog v-model="storeWriteConfirmDialog" persistent width="550">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  class="mt-3"
                  type="submit"
                  color="#C5CAE9"
                  v-bind="attrs"
                  v-on="on"
                >
                  📝 작성 완료
                </v-btn>
              </template>
              <v-card class="px-4">
                <v-card-title class="text-h5">
                  입력하신 내용으로 게시물을 등록하시겠습니까?
                </v-card-title>
                <div class="d-flex justify-space-around pb-5">
                  <v-btn color="#E6EE9C" @click="uploadStore()">
                    네, 이대로 등록할래요.
                  </v-btn>
                  <v-btn
                    color="grey lighten-3"
                    @click="storeWriteConfirmDialog = false"
                  >
                    아니요, 다시 확인해볼게요.
                  </v-btn>
                </div>
              </v-card>
            </v-dialog>

            <!-- 작성취소 모달 -->
            <v-dialog v-model="storeWriteCancelDialog" persistent width="550">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  class="mt-3"
                  type="submit"
                  color="#CFD8DC"
                  v-bind="attrs"
                  v-on="on"
                >
                  ❌ 작성 취소
                </v-btn>
              </template>
              <v-card class="px-4">
                <v-card-title class="text-h5">
                  게시판 목록화면으로 돌아가시겠습니까?
                </v-card-title>
                <h3>입력하신 내용은 삭제 됩니다.</h3>
                <div class="d-flex justify-space-around pb-5">
                  <v-btn
                    color="#E6EE9C"
                    @click="[(storeWriteCancelDialog = false), goBack()]"
                  >
                    네, 포스팅을 멈출게요.
                  </v-btn>
                  <v-btn
                    color="grey lighten-3"
                    @click="storeWriteCancelDialog = false"
                  >
                    아니요, 계속 작성하겠습니다.
                  </v-btn>
                </div>
              </v-card>
            </v-dialog>
          </div>
        </v-col>
      </v-row>
    </v-img>
  </div>
</template>

<script>
import SideNav from "@/components/SideNav.vue";
import router from "@/router";
import axios from "axios";
export default {
  data() {
    return {
      storeWriteCancelDialog: false,
      storeWriteConfirmDialog: false,

      item: {
        title: "",
        createdDateTime: "",
        content: "",
        storeName: "",
        address: "",
        menu: "",
        travelTime: "",
        likeCount: 0,
        commentCount: 0,
        writerId: "",
      },
      thisStoreId: "",
    };
  },

  created() {
    this.getSession();
  },

  methods: {
    //현재 로그인된 사용자 userId조회 -> writerId로 설정
    getSession() {
      const loginedId = parseInt(sessionStorage.getItem("loginedId"));
      console.log("현재 로그인된 사용자의 id session:" + loginedId);
      this.item.writerId = loginedId;
    },

    //뒤로가기 매서드
    goBack() {
      router.go(-1);
    },

    //맛집 게시판 메인화면 이동 매서드
    goStoreHome() {
      router.push("/storeHome");
    },

    async uploadStore() {
      try {
        await axios.post("/addStore", this.item);
      } catch (error) {
        console.log("맛집 포스팅 등록 에러:" + error);
      }
      alert("성공적으로 맛집포스팅을 등록했습니다.");
      this.goStoreHome();
    },
  },
  components: { SideNav },
};
</script>

<style></style>
