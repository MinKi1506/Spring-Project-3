<template>
  <div style="height: 100%">
    <v-row class="py-4">
      <v-col> 다이어트 게시판 - 포스팅 작성 </v-col>
    </v-row>
    <v-divider class="pt-8"></v-divider>

    <v-row class="d-flex justify-around">
      <v-col cols="10" class="ml-4">
        <v-card class="px-4 py-4" height="800">
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
              <v-img
                src="../assets/food.jpg"
                max-height="250"
                max-width="380"
              ></v-img>
              <v-btn>사진 초기화</v-btn>
            </v-col>
          </v-row>

          <v-card> </v-card>
        </v-card>
      </v-col>

      <v-col cols="1" class="mr-4">
        <div class="d-flex flex-column justify-center" style="height: 800px">
          <v-btn width="150" height="60" class="mb-12">사진 첨부</v-btn>
          <v-btn width="150" height="60" class="mb-12">작성 완료</v-btn>
          <v-btn width="150" height="60">작성 취소</v-btn>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
