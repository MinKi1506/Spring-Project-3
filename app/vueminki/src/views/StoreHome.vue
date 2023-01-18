<template>
  <div>
    <SideNav/>
    <v-row class="py-4">
      <v-col> 맛집 게시판 </v-col>
    </v-row>
    <v-divider class="pt-8"></v-divider>

    <v-row class="d-flex justify-center align-center" style="height: 50px">
      <v-col class="ml-16" cols="6">
        <v-form>
          <v-text-field
            height=""
            v-model="message4"
            label="검색 키워드를 입력하세요"
            outlined
            clearable
          ></v-text-field>
        </v-form>
      </v-col>
      <v-col cols="1">
        <v-btn class="mb-8">🔍검색하기</v-btn>
      </v-col>
    </v-row>

    <v-row>
      <v-col class="d-flex justify-end mb-4">
        <v-btn type="submit" class="mr-16" @click="getMaxStoreId"
          >포스팅 하기</v-btn
        >
        <v-btn type="submit" class="mr-16" link: to="/storeDetail"
          >임시 상세페이지</v-btn
        >
      </v-col>
    </v-row>

    <div class="px-16 d-flex flex-column justify-center">
      <v-card class="d-flex flex-column justify-center px-14  py-3" height="870">
        <v-row>
          <v-col>
            <v-card height="400" class="px-4 py-3">
            <v-row>
              <v-col class="d-flex justify-center">
                <h2>포스팅 제목</h2>
              </v-col>
            </v-row>

            <v-row>
              <v-col>
                <v-img src="../assets/food.jpg" max-height="250" max-width="320">
                </v-img></v-col>
              <v-col class="d-flex flex-column">
                  <p class="align-self-center">포스팅 작성 날짜</p>
                  <h4>본문내용 블라블라</h4>
              </v-col>
            </v-row>

            <v-divider class="my-4"></v-divider>

            <v-row class="d-flex align-center">
              <v-col cols="4" class="font-weight-medium">
               <div class="d-flex align-center">
                <h5 class="">'회사로부터 걸어서 &nbsp;</h5>  
                <h3 class="red--text">8 </h3>
                <h5 class="">분 거리에 있어요!'</h5>  
               </div></v-col>
              <v-col cols="4" class="d-flex align-center justify-center">
                <v-img src="../assets/storeLike.png" max-width="60px" max-height="40px"></v-img>
                <h2>5</h2>
              </v-col>
              <v-col cols="4" class="d-flex align-center">
                <v-img src="../assets/comment.png" max-width="50px" max-height="60px"></v-img>
                <h2>12</h2>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
          <v-col>    <v-card height="400" class="px-4 py-3">
            <v-row>
              <v-col class="d-flex justify-center">
                <h2>포스팅 제목</h2>
              </v-col>
            </v-row>

            <v-row>
              <v-col>
                <v-img src="../assets/food.jpg" max-height="250" max-width="320">
                </v-img>
              </v-col>
              <v-col class="d-flex flex-column">
                  <p class="align-self-center">포스팅 작성 날짜</p>
                  <h4>본문내용 블라블라</h4>
              </v-col>
            </v-row>
          </v-card></v-col>
        </v-row>
        <v-row>
          <v-col>    <v-card height="400" class="px-4 py-3">
            <v-row>
              <v-col class="d-flex justify-center">
                <h2>포스팅 제목</h2>
              </v-col>
            </v-row>

            <v-row>
              <v-col>
                <v-img src="../assets/food.jpg" max-height="250" max-width="320">
                </v-img>
              </v-col>
              <v-col class="d-flex flex-column ">
                  <p class="align-self-center">포스팅 작성 날짜</p>
                  <h4>본문내용 블라블라</h4>
              </v-col>
            </v-row>
          </v-card></v-col>
          <v-col>    <v-card height="400" class="px-4 py-3">
            <v-row>
              <v-col class="d-flex justify-center">
                <h2>포스팅 제목</h2>
              </v-col>
            </v-row>

            <v-row>
              <v-col>
                <v-img src="../assets/food.jpg" max-height="250" max-width="320">
                </v-img>
              </v-col>
              <v-col class="d-flex flex-colum">
                  <p class="align-self-center">포스팅 작성 날짜</p>
                  <h4>본문내용 블라블라</h4>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
        </v-row>
      </v-card>
      <div class="d-flex justify-center mt-5">
        <v-btn class="mr-2" width="10">◀︎</v-btn>
         <v-btn class="ml-2" width="10">▶</v-btn>
      </div>
      
    </div>



  </div>
</template>

<script>
import axios from 'axios';
import router from '@/router';
import SideNav from "../components/SideNav.vue";

export default {
  components: {
    SideNav
  },
  methods: {
      async getMaxStoreId() {
    try {
      const response = await axios.get("http://localhost:8085/getMaxStoreId");
      console.log("최대값은"+response.data);
      router.push("/storeAdd");
    }
    catch(error){
      console.log("에러"+error);
    }
  } 
  }
};
</script>

<style></style>
