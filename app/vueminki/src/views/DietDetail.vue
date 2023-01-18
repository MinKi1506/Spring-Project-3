<template>
  <div>
    <v-row class="py-4">
      <v-col>다이어트 게시판 - 포스팅 상세 </v-col>
    </v-row>
    <v-divider class="pt-8"></v-divider>

    <v-row>
      <v-col><v-btn>뒤로가기 버튼</v-btn></v-col>
      <v-col><h3>다이어트 포스팅 타이틀</h3></v-col>
      <v-col><v-btn link: to="/dietEdit">수정하기</v-btn></v-col>
      <v-col><v-btn link: to="/dietEditt">삭제하기</v-btn></v-col>
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
                    <v-col class="d-flex justify-center">
                      <v-btn>모든 댓글 보기</v-btn>
                    </v-col>
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
                        <v-btn>작성</v-btn>
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
          <h3>응원해요 도장주기</h3>
          <v-btn class="align-self-center">응원해요 버튼</v-btn>
        </v-col>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
