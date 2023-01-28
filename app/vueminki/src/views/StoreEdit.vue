<template>
  <div style="height: 100%">
    <SideNav />
    <v-row class="py-1">
      <v-col>
        맛집 게시판 - 포스팅 <span class="orange--text">수정</span>
      </v-col>
    </v-row>
    <v-divider class="pt-5"></v-divider>

    <v-row class="d-flex justify-around">
      <v-col cols="10" class="ml-4">
        <v-card class="px-4 py-4" height="950">
          <h4>포스팅 제목</h4>
          <v-text-field
            v-model="updateItem.title"
            solo
            clearable
            class=""
            height="30"
            :placeholder="item.title"
          ></v-text-field>
          <h4>상호명</h4>
          <v-text-field
            v-model="updateItem.storeName"
            solo
            clearable
            class=""
            height="30"
            :placeholder="item.storeName"
          ></v-text-field>
          <h3>메뉴</h3>
          <v-text-field
            v-model="updateItem.menu"
            solo
            clearable
            class=""
            height="30"
            :placeholder="item.menu"
          ></v-text-field>
          <h3>상세주소</h3>
          <v-text-field
            v-model="updateItem.address"
            solo
            clearable
            class=""
            height="30"
            :placeholder="item.address"
          ></v-text-field>
          <h3>이동 시간 <span class="ml-2">(분)</span></h3>
          <v-text-field
            v-model="updateItem.travelTime"
            solo
            clearable
            class=""
            height="30"
            :placeholder="item.travelTime"
          ></v-text-field>

          <v-row class="d-flex justify-around">
            <v-col cols="7" class="d-flex flex-column">
              <h3>본문</h3>
              <v-text-field
                v-model="updateItem.content"
                solo
                clearable
                class=""
                height="200"
                :placeholder="item.content"
              ></v-text-field>
            </v-col>
            <v-col
              cols="5"
              class="d-flex flex-column justify-center align-center"
            >
              <h3>대표 이미지</h3>
              <div class="d-flex align-center">
                <v-img
                  src="../assets/food.jpg"
                  max-height="200"
                  max-width="320"
                ></v-img>
                <v-btn class="ml-3" rounded>사진 초기화</v-btn>
              </div>
              <!-- 사진 업로드 file input -->
              <v-file-input
                v-model="files"
                style="width: 500px"
                height="60"
                small-chips
                counter
                multiple
                label="사진 첨부"
                prepend-icon="mdi-camera"
              ></v-file-input>
              <p>파일 네임: {{ files.name }}</p>
            </v-col>
          </v-row>
        </v-card>
      </v-col>

      <v-col cols="1" class="mr-4">
        <div class="d-flex flex-column justify-center" style="height: 800px">
          <!-- 작성확인 모달 -->
          <v-dialog v-model="storeWriteConfirmDialog" persistent width="550">
            <template v-slot:activator="{ on, attrs }">
              <v-btn class="mt-3" type="submit" v-bind="attrs" v-on="on">
                작성 완료
              </v-btn>
            </template>
            <v-card class="px-2">
              <v-card-title class="text-h5">
                입력하신 내용으로 게시물을 수정하시겠습니까?
              </v-card-title>

              <div class="d-flex justify-space-around pb-5">
                <v-btn rounded color="#E6EE9C" @click="updateStore()">
                  네, 이대로 수정할게요.
                </v-btn>
                <v-btn
                  rounded
                  color="grey lighten-3"
                  @click="storeWriteConfirmDialog = false"
                >
                  아니요, 다시 검토할게요.
                </v-btn>
              </div>
            </v-card>
          </v-dialog>

          <!-- 작성취소 모달 -->
          <v-dialog v-model="storeWriteCancelDialog" persistent width="600">
            <template v-slot:activator="{ on, attrs }">
              <v-btn class="mt-3" type="submit" v-bind="attrs" v-on="on">
                수정 취소
              </v-btn>
            </template>
            <v-card class="px-2">
              <v-card-title class="text-h5">
                수정 작업을 중단하시겠어요?
              </v-card-title>
              <h5 class="px-3">입력하신 내용은 삭제됩니다.</h5>

              <div class="d-flex justify-space-around pb-5">
                <v-btn
                  rounded
                  color="#E6EE9C"
                  @click="[(storeWriteCancelDialog = false), goBack()]"
                >
                  네, 이전화면으로 돌아갈래요.
                </v-btn>
                <v-btn
                  rounded
                  color="grey lighten-3"
                  @click="storeWriteCancelDialog = false"
                >
                  아니요, 계속 수정할게요.
                </v-btn>
              </div>
            </v-card>
          </v-dialog>
        </div>
      </v-col>
    </v-row>
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
      files: [],

      thisStoreId: "",

      item: {
        title: "",
        content: "",
        storeName: "",
        address: "",
        menu: "",
        travelTime: "",
      },

      updateItem: {
        title: "",
        content: "",
        storeName: "",
        address: "",
        menu: "",
        travelTime: "",
      },
    };
  },

  created() {
    this.getSession();
    this.thisStoreId = this.$route.params.storeId;
    console.log(
      "맛집 포스팅 수정페이지에서의 thisStoreId: " + this.thisStoreId
    );
    this.getStore();
  },

  methods: {
    //현재 로그인된 사용자 userId조회 -> writerId로 설정
    getSession() {
      const loginedId = parseInt(sessionStorage.getItem("loginedId"));
      console.log("현재 로그인된 사용자의 id session:" + loginedId);
      this.item.writerId = loginedId;
    },

    //받아온 storeId로 포스팅 정보 placeholder로 기본 입력시키기
    async getStore() {
      try {
        const response = await axios.get(
          // "http://localhost:8083/getStore/" + this.$route.params.storeId
          "http://localhost:8083/getStore/" + this.thisStoreId
        );
        this.item.title = response.data.title;
        this.item.content = response.data.content;
        this.item.storeName = response.data.storeName;
        this.item.address = response.data.address;
        this.item.menu = response.data.menu;
        this.item.travelTime = response.data.travelTime;
      } catch (error) {
        console.log("포스팅 정보 가져와서 수정페이지에 뿌리기 에러!!:" + error);
      }
    },

    //포스팅 수정 메서드
    async updateStore() {
      try {
        await axios.put(
          "http://localhost:8083/updateStore/" + this.$route.params.storeId,
          this.updateItem
        );
      } catch (error) {
        console.log("맛집 포스팅 수정 에러:" + error);
      }
      alert("성공적으로 포스팅을 수정했습니다.");
      console.log("파일name:::::" + this.files.name);
      this.goBack();
    },

    //뒤로가기 매서드
    goBack() {
      router.go(-1);
    },

    //맛집 게시판 메인화면 이동 매서드
    goStoreHome() {
      router.push("/storeHome");
    },
  },
  components: { SideNav },
};
</script>

<style></style>
