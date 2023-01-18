<template>
  <v-row>
    <v-col>
      <div class="information">
        <p>이미지를 업로드하세요.</p>
        <v-btn>선택</v-btn>
        <input
          type="file"
          @change="selectFile"
          multiple
          accept="image/*"
          ref="fileRef"
        />
      </div>
      <div class="images" v-if="files.length > 0">
        <v-card
          width="350"
          height="350"
          v-for="fileName in files"
          :key="fileName"
          class="image"
        >
          <v-img
            :src="`http://localhost:8085/image/${fileName}`"
            alt="이미지"
          />
        </v-card>
      </div>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      files: [],
    };
  },
  computed: {},
  mounted() {
    this.fetchFiles();
  },
  methods: {
    async fetchFiles() {
      const response = await axios.get("http://localhost:8085/files/0");
      this.files = response.data;
    },
    selectFile(event) {
      const formData = new FormData();
      for (const file of event.target.files) {
        formData.append("files", file);
      }
      axios
        .post("http://localhost:8085/files", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(() => {
          this.fetchFiles();
        })
        .catch((error) => {
          alert(error.message);
        });
    },
  },
};
</script>

<style></style>
