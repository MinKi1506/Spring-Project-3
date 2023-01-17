<template>
  <div>
    <v-row class="py-4">
      <v-col>맛집 게시판 - 포스팅 상세 </v-col>
    </v-row>
    <v-divider class="pt-8"></v-divider>

    <v-row class="d-flex justify-center align-center">
      <v-col>
        <v-row class="pl-4">
          <v-col cols="4"><v-btn @click="goBack">뒤로가기 버튼</v-btn></v-col>
          <v-col cols="8"><h1>맛집 포스팅 타이틀</h1></v-col>
        </v-row>
      </v-col>

      <v-col>
        <v-row>
          <v-col class="d-flex justify-end px-10">
            <v-dialog v-model="storeEditDialog" persistent width="800">
              <template v-slot:activator="{ on, attrs }">
                <v-btn class="mt-3 mr-4" type="submit" v-bind="attrs" v-on="on">
                  수정하기
                </v-btn>
              </template>
              <v-card class="px-4">
                <v-card-title class="text-h5">
                  해당 포스팅을 수정하시겠습니까?
                </v-card-title>

                <v-card-actions>
                  <v-btn
                    rounded
                    color="gray lighten-3"
                    @click="storeEditDialog = false"
                  >
                    닫기
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-dialog v-model="storeDeleteDialog" persistent width="800">
              <template v-slot:activator="{ on, attrs }">
                <v-btn class="mt-3" type="submit" v-bind="attrs" v-on="on">
                  삭제하기
                </v-btn>
              </template>
              <v-card class="px-4">
                <v-card-title class="text-h5">
                  해당 포스팅을 삭제하시겠습니까?
                </v-card-title>

                <v-card-actions>
                  <v-btn
                    rounded
                    color="gray lighten-3"
                    @click="storeDeleteDialog = false"
                  >
                    닫기
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <v-card class="px-4 py-2 mx-4 my-2" height="600">
          <v-row>
            <v-col class="d-flex flex-column justify-center">
              <v-card height="550">
                <v-row>
                  <v-col> 글쓴이 </v-col>
                  <v-col> 작성날짜 </v-col>
                </v-row>
                <v-divider></v-divider>
                <v-row>
                  <v-col> 본문내용 </v-col>
                </v-row>
                <v-row>
                  <v-col class="d-flex justify-end">
                    n명이 좋아한 글이에요!
                  </v-col>
                </v-row>
              </v-card>
            </v-col>

            <v-divider vertical></v-divider>

            <v-col>
              <v-row>
                <v-col>
                  <v-card height="270">사진1</v-card>
                </v-col>
                <v-col>
                  <v-card height="270">사진2</v-card>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-card height="270">사진3</v-card>
                </v-col>
                <v-col>
                  <v-card height="270">사진4</v-card>
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
          <v-col>이 게시물의 댓글</v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-card class="px-4 py-2 mx-4">
              <v-row>
                <v-col>
                  <v-row>
                    <v-col>
                      <v-card class="mx-2 my-2">
                        <v-row>
                          <v-col cols="9">댓글1</v-col>
                          <v-divider vertical></v-divider>
                          <v-col cols="2">유저1</v-col>
                        </v-row>
                      </v-card>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>
                      <v-card class="mx-2 my-2">
                        <v-row>
                          <v-col cols="9">댓글1</v-col>
                          <v-divider vertical></v-divider>
                          <v-col cols="2">유저1</v-col>
                        </v-row>
                      </v-card>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>
                      <v-card class="mx-2 my-2">
                        <v-row>
                          <v-col cols="9">댓글1</v-col>
                          <v-divider vertical></v-divider>
                          <v-col cols="2">유저1</v-col>
                        </v-row>
                      </v-card>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>
                      <v-card class="mx-2 my-2">
                        <v-row>
                          <v-col cols="9">댓글1</v-col>
                          <v-divider vertical></v-divider>
                          <v-col cols="2">유저1</v-col>
                        </v-row>
                      </v-card>
                    </v-col>
                  </v-row>
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
                          모든 댓글 확인하기
                        </v-btn>
                      </template>
                      <v-card class="px-4">
                        <v-card-title class="text-h5">
                          '포스팅 제목'의 모든 댓글
                        </v-card-title>
                        <v-card class="mx-2 mb-5">
                          <v-row>
                            <v-col cols="9">댓글1</v-col>
                            <v-divider vertical></v-divider>
                            <v-col cols="2">유저1</v-col>
                          </v-row>
                        </v-card>
                        <v-card class="mx-2 mb-5">
                          <v-row>
                            <v-col cols="9">댓글1</v-col>
                            <v-divider vertical></v-divider>
                            <v-col cols="2">유저1</v-col>
                          </v-row>
                        </v-card>
                        <v-card class="mx-2 mb-5">
                          <v-row>
                            <v-col cols="9">댓글1</v-col>
                            <v-divider vertical></v-divider>
                            <v-col cols="2">유저1</v-col>
                          </v-row>
                        </v-card>
                        <!-- 여기에 사진 리스트 -->
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
                    <!-- <v-col class="d-flex justify-center">
                      <v-btn
                        color="#00b0ff"
                        rounded
                        class="mt-8"
                        type="submit"
                        v-bind="attrs"
                        v-on="on"
                        >모든 댓글 보기</v-btn
                      >
                    </v-col> -->
                  </v-row>
                </v-col>

                <v-divider vertical></v-divider>

                <v-col>
                  <v-row>
                    <v-col>
                      <v-card class="" height="250">
                        <v-textarea
                          height="250"
                          placeholder="댓글을 입력하세요"
                          outlined
                        ></v-textarea>
                      </v-card>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>
                      <div class="d-flex justify-space-around">
                        <!-- 댓글 전체 확인 -->
                        <v-dialog
                          v-model="commentAddDialog"
                          persistent
                          width="800"
                        >
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn
                              class="mt-8"
                              type="submit"
                              v-bind="attrs"
                              v-on="on"
                            >
                              작성
                            </v-btn>
                          </template>
                          <v-card class="px-4">
                            <v-card-title class="text-h5">
                              위 내용으로 댓글을 등록하시겠습니까?
                            </v-card-title>

                            <v-card-actions>
                              <v-btn
                                rounded
                                color="gray lighten-3"
                                @click="commentAddDialog = false"
                              >
                                닫기
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                        <v-btn>초기화</v-btn>
                      </div>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-card>
          </v-col>
        </v-row>
      </v-col>

      <v-divider vertical></v-divider>

      <v-col cols="2">
        <v-col class="d-flex flex-column">
          <h3>좋아요 도장주기</h3>
          <v-btn class="align-self-center">좋아요 버튼</v-btn>
        </v-col>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      modalOn: false,
      storeEditDialog: false,
      storeDeleteDialog: false,
      commentDialog: false,
      commentAddDialog: false,
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style></style>
