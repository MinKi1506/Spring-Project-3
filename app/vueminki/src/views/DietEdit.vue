<template>
  <div>
    <h3>
      파일 업로드 결과: { { this.response === '' ? 'waiting' : this.response } }
    </h3>
    <div>
      <v-btn @click="uploadFileInDto()">Multipart in DTO Upload</v-btn>
      <v-btn @click="uploadFileListInDto()">Images List in DTO Upload</v-btn>
      <v-btn @click="uploadFileMapInDto()">Images Map in DTO Upload</v-btn>
      <v-btn @click="uploadFileMapListInDto()">
        Images Map-List in DTO Upload
      </v-btn>
    </div>

    <v-card
      width="350"
      height="350"
      v-for="fileName in files"
      :key="fileName"
      class="image"
    >
      <v-img :src="`http://localhost:8085/image/${fileName}`" alt="이미지" />
    </v-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      response: "",
      files: [],
    };
  },

  mounted() {
    this.fetchFiles();
  },

  methods: {
    async fetchFiles() {
      const response = await axios.get("http://localhost:8085/files/3");
      this.files = response.data;
    },

    responseCallback(response) {
      this.response = response.data;
    },

    errorCallback(error) {
      this.response = error.message;
    },

    getImageSelectElement(multiple) {
      let element = document.createElement("input");
      element.id = "image";
      element.type = "file";
      element.accept = "image/*";
      element.multiple = multiple;
      return element;
    },

    uploadFileInDto() {
      var context = this;
      let element = this.getImageSelectElement(false);
      element.click();
      element.onchange = function () {
        const formdata = new FormData();
        formdata.append("storeFile", this.files[0]);
        formdata.append("storeId", 1);
        axios
          .post("http://localhost:8085/dto", formdata, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then(context.responseCallback)
          .catch(context.errorCallback);
      };
    },
  },
};
</script>

<style></style>
