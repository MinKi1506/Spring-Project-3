<template>
  <div>
    <SideNav />

    <v-img src="../assets/stroeBG.jpeg">
      <v-row class="py-4">
        <v-col class="ml-4 pl-4"
          >맛집 게시판 - 포스팅 <span style="color: #f8bbd0">상세</span></v-col
        >
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
                  <v-btn
                    color="#FFE0B2"
                    class="mt-3 mr-4"
                    v-bind="attrs"
                    v-on="on"
                  >
                    🩹 수정하기
                  </v-btn>
                </template>
                <v-card color="#FFE0B2" class="pa-6">
                  <v-card>
                    <v-card-title class="text-h5">
                      해당 포스팅을 수정하시겠습니까?
                    </v-card-title>

                    <div class="d-flex justify-space-around pb-3">
                      <v-btn
                        color="#FFAB40"
                        class="pa-2"
                        @click="[(storeEditDialog = false), goStoreEdit()]"
                      >
                        네, 수정할래요.
                      </v-btn>
                      <v-btn
                        class="pa-2"
                        color="#CFD8DC"
                        @click="storeEditDialog = false"
                      >
                        아니요(창닫기)
                      </v-btn>
                    </div>
                  </v-card>
                </v-card>
              </v-dialog>
              <v-dialog v-model="storeDeleteDialog" persistent width="600">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn color="#FFCCBC" class="mt-3" v-bind="attrs" v-on="on">
                    🗑 삭제하기
                  </v-btn>
                </template>
                <v-card color="#FFCCBC" class="pa-6">
                  <v-card>
                    <v-card-title class="text-h5">
                      해당 포스팅을 삭제 하시겠습니까?
                    </v-card-title>
                    <div class="d-flex justify-space-around pb-3">
                      <v-btn
                        class="pa-2"
                        color="#FF8A65"
                        @click="deleteStore()"
                      >
                        네, 삭제할게요.
                      </v-btn>
                      <v-btn
                        class="pa-2"
                        color="#CFD8DC"
                        @click="storeDeleteDialog = false"
                      >
                        아니요, 삭제하지 않을래요.
                      </v-btn>
                    </div>
                  </v-card>
                </v-card>
              </v-dialog>
            </v-col>
          </v-row>
        </v-col>
      </v-row>

      <v-row>
        <v-col>
          <v-card class="px-2 py-2 mx-4 my-2" height="510" color="#DCEDC8">
            <v-row>
              <v-col
                cols="7"
                class="d-flex flex-column justify-center align-center"
              >
                <v-card height="480" width="800" class="pa-3">
                  <v-row>
                    <v-col class="smallSmallFont">
                      <p class="smallSmallFont">
                        작성자:
                        <span class="smallFont">{{ writerName }}</span>
                      </p>
                    </v-col>
                    <v-col>
                      <v-row class="d-flex flex-column">
                        <v-col>
                          <p class="smallSmallFont">
                            "{{ item.createdDateTime }}"
                          </p>
                        </v-col>
                        <v-col>
                          <p class="smallSmallFont">
                            <span class="red--text smallSmallFont">{{
                              item.likeCount
                            }}</span
                            >명이 좋아한 글이에요!
                          </p>
                        </v-col>
                      </v-row>
                    </v-col>
                  </v-row>
                  <v-divider class="py-4 px-3"></v-divider>

                  <v-row>
                    <v-col class="pa-2 smallSmallFont">
                      <p>
                        상호명:
                        <span style="color: #757575">{{ item.storeName }}</span>
                      </p>
                      <p>
                        주소:
                        <span style="color: #757575">{{ item.address }}</span>
                      </p>
                      <p>
                        주 메뉴:
                        <span style="color: #757575">{{ item.menu }}</span>
                      </p>
                      <p cstyle="color: #757575">
                        🏢 회사로부터 걸어서 &nbsp;<span class="red--text">{{
                          item.travelTime
                        }}</span>
                        분 거리에 있어요!
                      </p>
                    </v-col>
                  </v-row>

                  <v-divider class="py-4 px-3"></v-divider>
                  <v-row>
                    <v-col class="pa-4 smallSmallFont">
                      {{ item.content }}
                    </v-col>
                  </v-row>
                </v-card>
              </v-col>

              <v-col cols="5">
                <v-row>
                  <v-col>
                    <v-card
                      height="480"
                      class="d-flex flex-column justify-center align-center"
                    >
                      <v-row>
                        <v-col
                          cols="12"
                          class="d-flex justify-space-between mt-2"
                        >
                          <v-btn
                            class="mr-2"
                            large
                            color="#BBDEFB"
                            @click="uploadFileInDto(thisStoreId)"
                            >📷 사진 추가하기
                          </v-btn>
                          <v-btn
                            large
                            color="#FFCCBC"
                            class="ml-2"
                            @click="deleteStoreFile()"
                          >
                            🗑 사진 삭제하기
                          </v-btn>
                        </v-col>
                      </v-row>
                      <v-divider class="px-4"></v-divider>
                      <v-row>
                        <span class="smallSmallFont">
                          파일 업로드:
                          {{ this.response === "" ? "대기중" : this.response }}
                        </span>
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
                        <!-- <v-card max-height="320">
                        <v-img
                          max-width="600"
                          max-height="320"
                          :src="`http://100.0.0.157:28085/image/${files[0]}`"
                          alt="이미지"
                        />
                      </v-card> -->
                      </v-row>
                    </v-card>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
      </v-row>

      <v-row class="d-flex px-4 justify-center">
        <v-col cols="6">
          <v-row>
            <v-col>
              <v-card class="px-4 py-2 mx-4" height="280" color="#D7CCC8">
                <v-row>
                  <v-col>
                    <v-row class="d-flex justify-center align-center">
                      <!-- 댓글 전체 확인 모달-->
                      <v-dialog v-model="commentDialog" persistent width="800">
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn
                            color="#8D6E63"
                            dark
                            class="mt-8"
                            type="submit"
                            v-bind="attrs"
                            v-on="on"
                          >
                            '{{ item.commentCount }}'개의 모든 댓글 확인하기
                          </v-btn>
                        </template>
                        <v-card class="pa-5" color="#FFF3E0">
                          <v-card-title class="middleFont mb-4">
                            이 게시글의 모든 댓글
                          </v-card-title>
                          <v-card
                            v-for="(commentItem, i) in commentItems"
                            :key="i"
                            class="mx-2 mb-5 px-4 py-3"
                            color="#FFE0B2"
                          >
                            <v-row>
                              <v-col class="px-3" cols="9">
                                <p>{{ commentItem.commentContent }}</p>
                              </v-col>
                              <v-divider class="py-4" vertical></v-divider>
                              <v-col class="px-3" cols="2">
                                <p>
                                  작성자: {{ commentItem.commentWriterName }}
                                </p>
                              </v-col>
                              <v-divider class="py-4" vertical></v-divider>
                              <v-col cols="1">
                                <v-btn
                                  plain="true"
                                  @click="
                                    deleteComment(
                                      commentItem.commentWriter,
                                      commentItem.commentId
                                    )
                                  "
                                  >❌</v-btn
                                >
                              </v-col>
                            </v-row>
                          </v-card>

                          <v-card-actions>
                            <v-btn
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
              class="mt-5"
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
    </v-img>
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
      loginedId: "",

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
        commentId: "",
        commentContent: "",
        commentWriter: "",
        commentWriterName: "",
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
    this.getSession();
    this.getStore();
    this.getCommentList();
    this.fetchFiles();
  },

  methods: {
    getSession() {
      this.loginedId = parseInt(sessionStorage.getItem("loginedId"));
      console.log(
        "상세페이지)현재 로그인된 사용자의 id session:" + this.loginedId
      );
    },

    //받아온 storeId로 게시글 조회
    async getStore() {
      try {
        const response = await axios.get("/getStore/" + this.thisStoreId);
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
    async getCommentWriterName(commentWriter) {
      try {
        const response = await axios.get("/getUserName/" + commentWriter);
        this.commentItem.commentWriterName = response.data;
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
          vm.commentItem.commentId = this.commentList[i].id;
          vm.commentItem.commentContent = this.commentList[i].content;
          vm.commentItem.commentWriter = this.commentList[i].writerId;

          const responseWriterName = await axios.get(
            "/getUserName/" + this.commentList[i].writerId
          );
          vm.commentItem.commentWriterName = responseWriterName.data;
          console.log(
            "가져온 댓글 글쓴이닉네임 :" + vm.commentItem.commentWriterName
          );

          vm.commentItems.push(vm.commentItem);
          vm.commentItem = {};
        }
        console.log("commentItems::::::::" + vm.commentItems[0].id);
        console.log("commentItems::::::::" + vm.commentItems[1].id);
        console.log("commentItems::::::::" + vm.commentItems[2].id);
      } catch (error) {
        console.log("댓글 리스트 가져오기 에러!!:" + error);
      }
    },

    //댓글 삭제하기
    deleteComment(commentWriter, commentId) {
      console.log("선택한 댓글의commentId : " + commentId);
      console.log("선택한 댓글의writerId : " + this.commentItem.commentWriter);
      console.log("현재 로그인한 유저의 id : " + this.loginedId);
      if (commentWriter == this.loginedId) {
        axios.delete("/store/" + this.thisStoreId + "/comment/" + commentId);
        this.reload(); //강제로 새로고침하여 삭제 정보 반영
      } else {
        alert("자신이 작성한 댓글만 지울 수 있습니다!");
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
      if (this.item.writerId == this.loginedId) {
        this.$router.push({
          name: "storeEdit",
          path: "/storeEdit/:storeId",
          params: { storeId: this.storeId },
        });
        console.log("게시글 수정으로 보내는 storeId값" + this.storeId);
      } else {
        alert("자신이 작성한 게시글만 수정할 수 있습니다!");
      }
    },

    //게시글 삭제 메서드
    deleteStore() {
      if (this.item.writerId == this.loginedId) {
        try {
          axios.delete(`/deleteStore/${this.thisStoreId}`);
        } catch (error) {
          console.log("게시글 삭제 에러!!:" + error);
        }
        this.$router.push("/storeHome");
        this.reload(); //강제로 새로고침하여 삭제된 정보 반영
      } else {
        alert("자신이 작성한 게시글만 삭제할 수 있습니다!");
        this.storeDeleteDialog = false;
      }
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
      const response = await axios.get(
        `http://localhost:8085/files/${this.thisStoreId}`
        // `http://100.0.0.157:28085/files/${this.thisStoreId}`
      );
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
      if (this.item.writerId == this.loginedId) {
        var context = this;
        let element = this.getImageSelectElement(false);
        element.click();
        element.onchange = async function () {
          const formdata = new FormData();
          formdata.append("storeFile", this.files[0]);
          formdata.append("storeId", storeId);
          await axios
            .post("http://localhost:8085/dto", formdata, {
              // .post("http://100.0.0.157:28085/dto", formdata, {
              headers: {
                "Content-Type": "multipart/form-data",
              },
            })
            .then(context.responseCallback)
            .catch(context.errorCallback);
        };
      } else {
        alert("자신이 작성한 게시글만 사진을 등록 할 수 있습니다!");
      }
    },

    reload() {
      this.$router.go(this.$router.currentRoute); //강제 새로고침
    },

    //사진 파일 삭제
    async deleteStoreFile() {
      if (this.item.writerId == this.loginedId) {
        console.log("현재 게시글의 사진 file name: " + this.files[0]);
        try {
          let response = await axios.delete(
            "http://localhost:8085/deleteFile/" + this.files[0]
            // "http://100.0.0.157:28085/deleteFile/" + this.files[0]
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
      } else {
        alert("자신이 작성한 게시글만 사진을 삭제 할 수 있습니다!");
      }
    },
  },
};
</script>

<style></style>
