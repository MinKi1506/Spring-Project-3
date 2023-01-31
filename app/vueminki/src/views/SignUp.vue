<template>
   <section class="signInBackground">
    <div class="bg"></div>

    <div class="pa-8">
      <div style="height: 200px"></div>
      <v-row class="">
        <v-col class="d-flex flex-column align-center justify-center">
          <p class="middleFont">'오케스트로 식구들을 위한 일상공유 플랫폼'</p>
          <div class="d-flex align-center">
            <p class="green--text" style="font-size: 40px">잇</p>
            <p class="" style="font-size: 30px">(Eat)</p>
            <p class="green--text" style="font-size: 40px">케스트로</p>
          </div>
        </v-col>
      </v-row>
      <v-divider></v-divider>

      <v-row>
        <v-col class="d-flex justify-center">
          <v-form
            style="width: 20%"
            ref="form"
            v-on:submit.prevent="signUp"
            v-model="valid"
            lazy-validation
          >
            <v-text-field
              placeholder="사용하실 이메일을 입력하세요."
              v-model="email"
              :rules="emailRules"
              label="이메일"
              required
            ></v-text-field>

            <v-text-field
              placeholder="사용하실 패스워드를 입력하세요."
              v-model="password"
              :rules="passwordRules"
              label="패스워드"
              required
            ></v-text-field>

            <v-text-field
              placeholder="이름을 입력하세요."
              v-model="name"
              :rules="passwordRules"
              label="이름"
              required
            ></v-text-field>

            <v-text-field
              placeholder="소속 부서를 입력하세요."
              :rules="passwordRules"
              label="소속 부서"
              required
            ></v-text-field>

            <v-text-field
              placeholder="나이를 입력하세요."
              :rules="passwordRules"
              label="나이"
              required
            ></v-text-field>
            
            <v-text-field
              placeholder="성별을 선택해 주세요."
              :rules="passwordRules"
              label="성별"
              required
            ></v-text-field>

            <v-checkbox
              v-model="checkbox"
              :rules="[
                (v) =>
                  !!v || '타인의 정보로 가입 시, 불이익을 받을 수 있습니다.',
              ]"
              label="작성하신 내용으로 가입하시겠습니까?"
              required
            ></v-checkbox>
            <div>
              <v-btn
                :disabled="!valid"
                color="#9ccc65"
                class="mr-4"
                type="submit"
                @click="validate"
                >회원가입
              </v-btn>

              <v-btn color="#B0BEC5" class="mr-4" @click="reset">초기화</v-btn>

              <v-btn color="#B0BEC5" class="mr-4" link: to="/"
                >취소</v-btn
              >
            </div>
          </v-form>
        </v-col>
      </v-row>
    </div>
  </section>

</template>

<script>
import axios from "axios";
import router from "@/router";

export default {

  data() {
    return {
      valid: true,
      passwordRules: [(v) => !!v || "패스워드는 필수 입력 사항입니다!"],
      // 이메일 형식검증
      emailRules: [
        (v) => !!v || "이메일은 필수 입력 사항입니다.",
        (v) => /.+@.+/.test(v) || "이메일 형식을 지켜서 입력해 주세요.",
      ],
      checkbox: false,
      email: "",
      password: "",
      name: ""
    };
  },

  methods: {

    //회원가입 매서드
    async signUp() {

        var vm = this;

      await axios({
        url: "/signUp",
        method: "POST",
        data: {
          name: this.name,
          email: this.email,
          password: this.password,
        },
      })
        .then(function (response) {
          if (response.data != 0) {
            // store Vuex에 userId 지정
            alert("회원가입이 완료되었습니다.");
        vm.$store.commit("setUserId", response.data); //store.userId값 갱신
        vm.saveSession();
            console.log("가입한 유저의 userId: " + response.data);
            router.push("/dashBoard");
          } else if (response.data == 0) {
            alert(
              "중복된 이메일입니다. 가입정보를 새로 입력해 주세요."
            );
          }
        })
        .catch(function (err) {
          console.log("axios 통신에러" + err);
        });
      (this.email = ""), (this.password = ""), (this.name = ""), this.reset();
    },


      //로그인한 userId를 세션 스토리지에 저장
      saveSession() {
      sessionStorage.setItem("loginedId",String(this.$store.state.userId));
    },

  
    validate() {
      this.$refs.form.validate();
    },

    reset() {
      this.$refs.form.reset();
    },
  },

};
</script>

<style></style>
