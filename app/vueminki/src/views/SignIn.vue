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
      <div>{{ this.$store.state.userId }}</div>

      <v-row>
        <v-col class="d-flex justify-center">
          <v-form
            style="width: 20%"
            ref="form"
            v-on:submit.prevent="signIn"
            v-model="valid"
            lazy-validation
          >
            <v-text-field
              placeholder="이메일을 입력하세요"
              v-model="email"
              :rules="emailRules"
              label="이메일"
              required
            ></v-text-field>

            <v-text-field
              placeholder="패스워드를 입력하세요"
              v-model="password"
              :rules="passwordRules"
              label="패스워드"
              required
            ></v-text-field>

            <!-- <v-checkbox
              v-model="checkbox"
              :rules="[
                (v) =>
                  !!v || '본인의 계정이 아닐 시, 불이익을 받을 수 있습니다.',
              ]"
              label="본인의 사내 계정이 맞습니까?"
              required
            ></v-checkbox> -->
            <div>
              <v-btn
                :disabled="!valid"
                color="#9ccc65"
                class="mr-4"
                type="submit"
                @click="validate"
                >로그인
              </v-btn>

              <v-btn color="#B0BEC5" class="mr-4" @click="reset">초기화</v-btn>

              <v-btn color="#B0BEC5" class="mr-4" link: to="/signUp"
                >회원가입</v-btn
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
      //이메일 형식검증
      // emailRules: [
      //   (v) => !!v || "이메일은 필수 입력 사항입니다.",
      //   (v) => /.+@.+/.test(v) || "이메일 형식을 지켜서 입력해 주세요.",
      // ],
      email: "",
      password: "",
    };
  },

  created() {
    this.getUserId();

  },

  methods: {
    //현재 이용자의 userId 조회
    getUserId() {
      console.log("현재 사용자의 userId: " + this.$store.state.userId);
    },

    //로그인 매서드
    async signIn() {

      var vm = this;

      await axios({
        url: "http://localhost:8083/signIn",
        method: "POST",
        data: {
          email: this.email,
          password: this.password,
        },
      })
        .then(function (response) {
          if (response.data != 0) {
            alert("로그인에 성공했습니다!");
            vm.$store.commit("setUserId", response.data);
            // this.$store.commit("setUserId", response.data);
            vm.saveSession();
            console.log("로그인한 유저의 userId: " + response.data);

            router.push("/dashBoard");
          } else if (response.data == 0) {
            alert(
              "로그인에 실패했습니다. 이메일과 패스워드를 다시 확인해 주세요."
            );
          }
        })
        .catch(function (err) {
          console.log("axios 통신에러" + err);
        });


      (this.email = ""), (this.password = ""),this.reset();
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

<style>
@font-face {
  font-family: "jua";
  src: url("../../public/BmJua.ttf");
}

.fontJua {
  font-family: "jua";
}

.signInBackground {
  background: url("../assets/signInPhoto.jpg");
  background-size: cover;
  min-width: 100%;
  min-height: 100%;
}

/* 살짝 투명한 검정으로 배경색 */
.bg {
  background-color: rgba(0, 0, 0, 0.1);
  min-width: 100%;
  min-height: 100%;
  position: absolute; /*다른 요소들 위로 겹쳐질 수 있게*/
}

.bg2 {
  background-color: rgba(210, 235, 182, 0.4);
  min-width: 100%;
  position: absolute;
}
</style>
