<template>
  <div>
    <SideNav />
    <v-row class="py-4">
      <v-col>맛집 게시판 - 포스팅 상세 </v-col>
      <v-col>
        <h3>
          파일 업로드 결과:
          {{ this.response === "" ? "waiting" : this.response }}
        </h3>
      </v-col>
    </v-row>
    <v-divider class="pt-8"></v-divider>

    <v-row class="d-flex justify-center align-center">
      <v-col>
        <v-row class="pl-4">
          <v-col cols="4"><v-btn @click="goBack">↩︎뒤로가기</v-btn></v-col>
          <v-col cols="8"
            ><h3>{{ item.title }}</h3>
          </v-col>
        </v-row>
      </v-col>

      <v-col>
        <v-row>
          <v-col class="d-flex justify-end px-10">
            <v-dialog v-model="storeEditDialog" persistent width="500">
              <template v-slot:activator="{ on, attrs }">
                <v-btn class="mt-3 mr-4" v-bind="attrs" v-on="on">
                  수정하기
                </v-btn>
              </template>
              <v-card class="px-4">
                <v-card-title class="text-h5">
                  해당 포스팅을 수정하시겠습니까?
                </v-card-title>

                <div class="d-flex justify-space-around pb-5">
                  <v-btn
                    rounded
                    color="#E6EE9C"
                    class="pa-2"
                    @click="[(storeEditDialog = false), goStoreEdit()]"
                  >
                    네, 수정할래요.
                  </v-btn>
                  <v-btn
                    rounded
                    class="pa-2"
                    color="grey lighten-3"
                    @click="storeEditDialog = false"
                  >
                    아니요(창닫기)
                  </v-btn>
                </div>
              </v-card>
            </v-dialog>
            <v-dialog v-model="storeDeleteDialog" persistent width="600">
              <template v-slot:activator="{ on, attrs }">
                <v-btn class="mt-3" v-bind="attrs" v-on="on"> 삭제하기 </v-btn>
              </template>
              <v-card class="px-4">
                <v-card-title class="text-h5">
                  해당 포스팅을 삭제 하시겠습니까?
                </v-card-title>
                <div class="d-flex justify-space-around pb-5">
                  <v-btn
                    rounded
                    class="pa-2"
                    color="#E6EE9C"
                    @click="deleteStore()"
                  >
                    네, 삭제할게요.
                  </v-btn>
                  <v-btn
                    rounded
                    class="pa-2"
                    color="grey lighten-3"
                    @click="storeDeleteDialog = false"
                  >
                    아니요, 삭제하지 않을래요.
                  </v-btn>
                </div>
              </v-card>
            </v-dialog>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <v-card class="px-2 py-2 mx-4 my-2" height="480">
          <v-row>
            <v-col
              cols="7"
              class="d-flex flex-column justify-center align-center"
            >
              <v-card height="440" width="800">
                <v-row>
                  <v-col>
                    작성자: <span>{{ writerName }}</span>
                  </v-col>
                  <v-col>
                    <h6>{{ item.createdDateTime }}</h6></v-col
                  >
                </v-row>
                <v-divider></v-divider>
                <v-row>
                  <v-col> {{ item.content }} </v-col>
                </v-row>
                <v-row>
                  <v-col class="d-flex justify-end">
                    <h5>
                      <span class="red--text">{{ item.likeCount }}</span
                      >명이 좋아한 글이에요!
                    </h5>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>

            <v-divider class="py-6" vertical></v-divider>

            <v-col cols="5">
              <v-row>
                <v-col>
                  <v-card
                    height="440"
                    class="d-flex flex-column justify-center align-center"
                  >
                    <v-row>
                      <v-col
                        cols="12"
                        class="d-flex justify-space-between my-2"
                      >
                        <v-btn
                          class="mr-2"
                          large
                          color="#81D4FA"
                          @click="uploadFileInDto(thisStoreId)"
                          >📷 사진 추가하기
                        </v-btn>
                        <v-btn
                          large
                          color="#FFAB91"
                          class="ml-2"
                          @click="deleteStoreFile()"
                        >
                          ❌ 사진 삭제하기
                        </v-btn>
                      </v-col>
                    </v-row>

                    <v-row>
                      <v-card max-height="320">
                        <v-img
                          max-width="600"
                          max-height="320"
                          :src="`http://localhost:8085/image/${files[0]}`"
                          alt="이미지"
                        />
                      </v-card>
                    </v-row>
                  </v-card>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="10">
        <v-row>
          <v-col>
            <v-card class="px-4 py-2 mx-4" height="300">
              <v-row>
                <v-col>
                  <v-row>
                    <!-- 댓글 전체 확인 모달-->
                    <v-dialog v-model="commentDialog" persistent width="800">
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn
                          class="mt-8"
                          type="submit"
                          v-bind="attrs"
                          v-on="on"
                        >
                          {{ item.commentCount }}개의 모든 댓글 확인하기
                        </v-btn>
                      </template>
                      <v-card class="px-4">
                        <v-card-title class="text-h5">
                          이 게시글의 모든 댓글
                        </v-card-title>
                        <v-card
                          v-for="(commentItem, i) in commentItems"
                          :key="i"
                          class="mx-2 mb-5"
                        >
                          <v-row>
                            <v-col cols="9">
                              <p>{{ commentItem.commentContent }}</p>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col cols="2">
                              <p>{{ commentItem.commentWriter }}</p></v-col
                            >
                          </v-row>
                        </v-card>

                        <v-card-actions>
                          <v-btn
                            rounded
                            color="gray lighten-3"
                            @click="commentDialog = false"
                          >
                            닫기
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                    <!-- 이까지 모든 댓글 확인 모달 -->
                  </v-row>
                </v-col>

                <v-divider class="my-5 py-3" vertical></v-divider>

                <v-col>
                  <v-form ref="form" v-on:submit.prevent="addComment">
                    <v-row>
                      <v-col>
                        <v-card class="" height="200">
                          <v-text-field
                            height="200"
                            placeholder="댓글을 입력하세요"
                            outlined
                            v-model="commentAddText"
                          ></v-text-field>
                        </v-card>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col>
                        <div class="d-flex justify-space-around">
                          <v-btn type="submit"> 작성 하기 </v-btn>
                          <v-btn @click="reset">초기화</v-btn>
                        </div>
                      </v-col>
                    </v-row>
                  </v-form>
                </v-col>
              </v-row>
            </v-card>
          </v-col>
        </v-row>
      </v-col>

      <v-divider vertical></v-divider>

      <v-col cols="2">
        <v-col class="d-flex flex-column justify-center align-center">
          <h4 class="mb-5">좋아요 도장주기</h4>
          <v-btn
            @click="storeLike()"
            plain="true"
            class="mt-6"
            width="200"
            height="200"
          >
            <v-img
              src="../assets/storeLike.png"
              max-width="150"
              max-height="250"
            />
          </v-btn>
        </v-col>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import SideNav from "@/components/SideNav.vue";
import axios from "axios";
export default {
  components: { SideNav },

  // mounted() {
  //   // this.item = this.$route.params.item;
  //   // console.log("받아온 데이터" + this.$props.i);
  //   // console.log("받아온 데이터" + this.$props[0]);
  //   console.log("받아온데이터" + this.$route.params.storeId);
  //   console.log("받아온데이터" + this.$route.params("storeId"));
  // },

  props: ["storeId"],

  data() {
    return {
      modalOn: false,
      storeEditDialog: false,
      storeDeleteDialog: false,
      commentDialog: false,
      commentAddDialog: false,
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

      writerName: "",
      commentAddText: "",
      commentItem: {
        commentContent: "",
        commentWriter: "",
      },
      commentList: [],
      commentItems: [],

      response: "",
      files: [],
    };
  },

  created() {
    this.thisStoreId = this.$route.params.storeId;
    console.log("포스팅 수정페이지에서의 thisStoreId: " + this.thisStoreId);
  },

  mounted() {
    this.getStore();
    this.getCommentList();
    this.fetchFiles();
  },

  methods: {
    //받아온 storeId로 게시글 조회
    async getStore() {
      try {
        const response = await axios.get(
          // "http://localhost:8083/getStore/" + this.$route.params.storeId
          "/getStore/" + this.thisStoreId
        );
        this.item.title = response.data.title;
        this.item.content = response.data.content;
        this.item.storeName = response.data.storeName;
        this.item.address = response.data.address;
        this.item.menu = response.data.menu;
        this.item.travelTime = response.data.travelTime;
        this.item.likeCount = response.data.likeCount;
        this.item.commentCount = response.data.commentCount;
        this.item.writerId = response.data.writerId;
        // this.item.createdDateTime = response.data.createdDateTime;
        let year = response.data.createdDateTime.substring(0, 4);
        let month = response.data.createdDateTime.substring(5, 7);
        let day = response.data.createdDateTime.substring(8, 10);
        this.item.createdDateTime =
          year + "년 " + month + "월 " + day + "일에 작성된 글입니다.";

        this.getWriterName();
      } catch (error) {
        console.log("포스팅 정보 가져오기 에러!!:" + error);
      }
      console.log("받아온 맛집 데이터" + this.item.title);
    },

    //유저 정보 가져오기(게시글 글쓴이)
    async getWriterName() {
      try {
        const response = await axios.get("/getUserName/" + this.item.writerId);
        this.writerName = response.data;
        console.log(response.data);
        console.log(response.data.name);
      } catch (error) {
        console.log("글쓴이 이름 가져오기 에러!!:" + error);
      }
      console.log("현재 포스팅의 글쓴이 닉네임:" + this.writerName);
    },

    //유저 정보 가져오기(댓글 글쓴이)
    async getCommentWriterName() {
      try {
        const response = await axios.get("/getUserName/" + this.item.writerId);
        this.writerName = response.data;
        console.log(response.data);
        console.log(response.data.name);
      } catch (error) {
        console.log("댓글 이름 가져오기 에러!!:" + error);
      }
      console.log("현재 댓글의 글쓴이 닉네임:" + this.writerName);
    },

    //댓글 등록하기
    async addComment() {
      try {
        await axios.post(`/store/${this.thisStoreId}/comment`, {
          content: this.commentAddText,
          writerId: this.$store.state.userId,
        });
      } catch (error) {
        console.log("댓글 입력하기 에러!!:" + error);
      }
      alert("댓글이 성공적으로 등록되었습니다.");
      (this.commentAddText = ""), this.reset();
      this.$router.go(0);
    },

    //댓글 조회하기
    async getCommentList() {
      var vm = this;
      try {
        const response = await axios.get(`/store/${this.thisStoreId}/comment`);
        this.commentList = response.data;
        console.log("받아온 모든 댓글리스트" + response.data);
        for (let i = 0; i < response.data.length; i++) {
          vm.commentItem.commentContent = this.commentList[i].content;
          vm.commentItem.commentWriter = this.commentList[i].writerId;

          vm.commentItems.push(vm.commentItem);
          vm.commentItem = {};
        }
      } catch (error) {
        console.log("댓글 리스트 가져오기 에러!!:" + error);
      }
    },

    //맛집 포스팅 좋아요
    async storeLike() {
      try {
        const response = await axios.post("/like", {
          userId: parseInt(sessionStorage.getItem("loginedId")),
          storeId: this.thisStoreId,
        });
        if (response.data == 1) {
          alert("성공적으로 좋아요를 눌렀어요!");
        } else {
          alert("좋아요를 취소했어요.");
        }
        this.reload(); //강제로 새로고침하여 좋아요 정보 반영
      } catch (error) {
        console.log("댓글 리스트 가져오기 에러!!:" + error);
      }
    },

    //클릭 시, 데이터 전달하여 수정페이지로 가기
    goStoreEdit() {
      this.$router.push({
        name: "storeEdit",
        path: "/storeEdit/:storeId",
        params: { storeId: this.storeId },
      });
      console.log("게시글 수정으로 보내는 storeId값" + this.storeId);
    },

    //게시글 삭제 메서드
    deleteStore() {
      try {
        axios.delete(`/deleteStore/${this.thisStoreId}`);
      } catch (error) {
        console.log("게시글 삭제 에러!!:" + error);
      }
      this.$router.push("/storeHome");
      this.reload(); //강제로 새로고침하여 삭제된 정보 반영
    },

    //뒤로가기
    goBack() {
      this.$router.go(-1);
    },

    reset() {
      this.$refs.form.reset();
    },

    //여기서부터 파일 업로드 매서드

    //해당 storeId를 가진 사진 data 가져와서 띄우기
    async fetchFiles() {
      const response = await axios.get(`/files/${this.thisStoreId}`);
      this.files = response.data;
    },

    responseCallback(response) {
      this.response = response.data;
    },

    errorCallback(error) {
      this.response = error.message;
    },

    getImageSelectElement(multiple) {
      let element = document.createElement("input");
      element.id = "image";
      element.type = "file";
      element.accept = "image/*";
      element.multiple = multiple;
      return element;
    },

    //해당 storeId에 사진 data 업로드
    async uploadFileInDto(storeId) {
      var context = this;
      let element = this.getImageSelectElement(false);
      element.click();
      element.onchange = async function () {
        const formdata = new FormData();
        formdata.append("storeFile", this.files[0]);
        formdata.append("storeId", storeId);
        await axios
          .post("http://localhost:8085/dto", formdata, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then(context.responseCallback)
          .catch(context.errorCallback);
      };
    },

    reload() {
      this.$router.go(this.$router.currentRoute); //강제 새로고침
    },

    //사진 파일 삭제
    async deleteStoreFile() {
      console.log("현재 게시글의 사진 file name: " + this.files[0]);
      try {
        let response = await axios.delete(
          "http://localhost:8085/deleteFile/" + this.files[0]
        );
        if (response.data == 1) {
          alert("성공적으로 사진파일을 삭제했습니다!");
          this.reload();
        } else {
          alert("사진파일 삭제에 실패했습니다.");
        }
      } catch (error) {
        console.log("사진파일 삭제 에러!!:" + error);
      }
    },
  },
};
</script>

<style></style>
