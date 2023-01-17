<template>
  <div style="height: 100%">
    <v-row class="py-4">
      <v-col> 맛집 게시판 - 포스팅 작성 </v-col>
    </v-row>
    <v-divider class="pt-8"></v-divider>

    <v-row class="d-flex justify-around">
      <v-col cols="10" class="ml-4">
        <v-card class="px-4 py-4" height="1000">
          <h3>상호명</h3>
          <v-text-field
            v-model="message2"
            solo
            clearable
            class=""
            height="50"
            placeholder="맛집 상호명을 입력해 주세요"
          ></v-text-field>
          <h3>메뉴</h3>
          <v-text-field
            v-model="message2"
            solo
            clearable
            class=""
            height="50"
            placeholder="드셨던 메뉴, 혹은 대표 메뉴를 입력해 주세요"
          ></v-text-field>
          <h3>상세주소</h3>

          <v-text-field
            v-model="message2"
            solo
            clearable
            class=""
            height="50"
            placeholder="맛집의 상세 주소를 입력해 주세요"
          ></v-text-field>
          <h3>이동 시간</h3>

          <v-text-field
            v-model="message2"
            solo
            clearable
            class=""
            height="50"
            placeholder="회사로부터 걸어서 몇 분 걸리나요?"
          ></v-text-field>
          <v-row class="d-flex justify-around">
            <v-col cols="7" class="d-flex flex-column">
              <h3>본문</h3>
              <v-text-field
                v-model="message2"
                solo
                clearable
                class=""
                height="300"
                placeholder="본문 내용을 입력해 주세요"
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
                  max-height="250"
                  max-width="380"
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
          <v-dialog v-model="storeWriteConfirmDialog" persistent width="400">
            <template v-slot:activator="{ on, attrs }">
              <v-btn class="mt-3" type="submit" v-bind="attrs" v-on="on">
                작성 완료
              </v-btn>
            </template>
            <v-card class="px-4">
              <v-card-title class="text-h5">
                입력하신 내용으로 게시물을 등록하시겠습니까?
              </v-card-title>

              <v-card-actions>
                <v-btn rounded color="gray lighten-3" @click="upload()">
                  등록
                </v-btn>
                <v-btn
                  rounded
                  color="gray lighten-3"
                  @click="storeWriteConfirmDialog = false"
                >
                  취소
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <!-- 작성취소 모달 -->
          <v-dialog v-model="storeWriteCancelDialog" persistent width="400">
            <template v-slot:activator="{ on, attrs }">
              <v-btn class="mt-3" type="submit" v-bind="attrs" v-on="on">
                작성 취소
              </v-btn>
            </template>
            <v-card class="px-4">
              <v-card-title class="text-h5">
                게시판 목록화면으로 돌아가시겠습니까?
              </v-card-title>
              <h3>입력하신 내용은 삭제됩니다.</h3>

              <v-card-actions>
                <v-btn
                  rounded
                  color="gray lighten-3"
                  @click="[(storeWriteCancelDialog = false), goBack()]"
                >
                  닫기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import router from "@/router";
export default {
  data() {
    return {
      storeWriteCancelDialog: false,
      storeWriteConfirmDialog: false,
      files: [],
    };
  },
  methods: {
    goBack() {
      router.go(-1);
    },
    upload() {
      console.log(":::::" + this.files.name);
    },
  },
};
</script>

<style></style>
