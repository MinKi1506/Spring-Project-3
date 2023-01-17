<template>
  <v-container>
    <v-row>
      여긴 이미지
      <v-col cols="3"> <b-img src="" /> </v-col>
      <v-col>
        <v-row>
          <v-col>
            잘먹어야 일도 잘한다
            <div class="mt-12">이건?</div>
          </v-col>
        </v-row>
        <v-row>
          <v-col> 잇케스트로 </v-col>
        </v-row>
        <v-row>
          <v-col> 로그인 </v-col>
        </v-row>
        <v-row>
          <v-col>
            <div class="d-flex flex-column">
              <input type="text" placeholder="이메일을 입력하세요" />
              <input type="password" placeholder="비밀번호를 입력하세요" />
            </div>
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            <div class="d-flex">
              <v-btn>로그인</v-btn>
              <v-btn>회원가입</v-btn>
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {};
</script>

<style></style>
