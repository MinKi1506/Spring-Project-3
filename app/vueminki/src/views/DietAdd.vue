<template>
  <div style="height: 100%">
    <SideNav />
    <v-img
      src="https://t3.ftcdn.net/jpg/02/33/61/10/360_F_233611043_CeH5NZOBm9RghKTxv6QaRgpSImppD6IG.jpg"
    >
      <v-row class="py-4">
        <v-col class="ml-4 pl-4">
          다이어트 게시판 - 포스팅 <span class="purple--text">작성</span>
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
            <h4>목표</h4>
            <v-text-field
              v-model="item.goal"
              solo
              clearable
              class=""
              height="30"
              placeholder="다이어트 목표를 입력해 주세요"
            ></v-text-field>
            <h3>도전 기간</h3>
            <v-text-field
              v-model="item.period"
              solo
              clearable
              class=""
              height="30"
              placeholder="도전 기간을 입력해 주세요"
            ></v-text-field>
            <h3>도전실패 시, 패널티</h3>
            <v-text-field
              v-model="item.penalty"
              solo
              clearable
              class=""
              height="30"
              placeholder="도전에 실패했을 때 받게 될 벌칙을 정해주세요"
            ></v-text-field>

            <v-row class="d-flex justify-around">
              <v-col class="d-flex flex-column">
                <h3>본문(각오 한 마디))</h3>
                <v-text-field
                  v-model="item.content"
                  solo
                  clearable
                  class=""
                  height="180"
                  placeholder="본문 내용/ 각오를 다져주세요!"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex justify-center">
                <p>
                  📌 주기적으로 포스팅 상세페이지에서 도전 과정을
                  <span color="#EF9A9A">사진으로</span> 남겨주세요!
                </p>
              </v-col>
            </v-row>
          </v-card>
        </v-col>

        <v-col cols="1" class="mr-4">
          <div class="d-flex flex-column justify-center" style="height: 800px">
            <!-- 작성확인 모달 -->
            <v-dialog v-model="dietWriteConfirmDialog" persistent width="550">
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
                  <v-btn color="#E6EE9C" @click="uploadDiet()">
                    네, 이대로 등록할래요.
                  </v-btn>
                  <v-btn
                    color="grey lighten-3"
                    @click="dietWriteConfirmDialog = false"
                  >
                    아니요, 다시 확인해볼게요.
                  </v-btn>
                </div>
              </v-card>
            </v-dialog>

            <!-- 작성취소 모달 -->
            <v-dialog v-model="dietWriteCancelDialog" persistent width="550">
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
                    @click="[(dietWriteCancelDialog = false), goBack()]"
                  >
                    네, 포스팅을 멈출게요.
                  </v-btn>
                  <v-btn
                    color="grey lighten-3"
                    @click="dietWriteCancelDialog = false"
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
      dietWriteCancelDialog: false,
      dietWriteConfirmDialog: false,

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
        writerId: "",
      },
      thisDietId: "",
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

    //다이어트 게시판 메인화면 이동 매서드
    goDietHome() {
      router.push("/dietHome");
    },

    async uploadDiet() {
      try {
        await axios.post("/addDiet", this.item);
      } catch (error) {
        console.log("다이어트 포스팅 등록 에러:" + error);
      }
      alert("성공적으로 다이어트포스팅을 등록했습니다.");
      this.goDietHome();
    },
  },
  components: { SideNav },
};
</script>

<style></style>
