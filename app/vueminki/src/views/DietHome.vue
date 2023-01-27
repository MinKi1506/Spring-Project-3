<template>
  <div>
    <SideNav />
    <v-row class="py-4">
      <v-col> 다이어트 게시판 </v-col>
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
        <v-btn type="submit" class="mr-16" link: to="/dietAdd">포스팅 하기</v-btn>
        <v-btn type="submit" class="mr-16" link: to="/dietDetail"
          >임시 상세페이지</v-btn
        >
      </v-col>
    </v-row>

    <div class="px-16">
      <v-card class="d-flex flex-column justify-center px-14" height="700">
        <v-card width="full" height="210" class="mb-3">다이어트 1</v-card>
        <v-card width="full" height="210" class="mb-3">다이어트 2</v-card>
        <v-card width="full" height="210">다이어트 3</v-card>
      </v-card>
    </div>
  </div>
</template>

<script>
import SideNav from '@/components/SideNav.vue';

export default {
  components: { SideNav },
};
</script>

<style></style>
