<template>
  <div>
    <v-file-input
      v-model="files"
      multiple
      show-size
      label="File input"
    ></v-file-input>
    <v-btn @click="upload" color="primary">Upload</v-btn>
    <p>File Name : {{ files.name }}</p>

    <v-divider></v-divider>

    <form
      name="form"
      method="post"
      action="http://localhost:8080/board"
      enctype="multipart/form-data"
    >
      <input name="user" value="Pyo" />
      <input name="content" value="Content" />
      <input type="file" name="files" multiple="multiple" />
      <input type="submit" id="submit" value="전송" />
    </form>

    <v-divider></v-divider>

    <form action="post" enctype="multipart/form-data">
      <input type="file" id="File" name="files" multiple="multiple" />
      <button @click="upload" id="Button">전송</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
const FileElement = document.querySelector("#File");

export default {
  data: () => ({
    files: [],
  }),

  methods: {
    // upload() {
    //   console.log("Hello, Upload");
    //   console.log(this.files.name);
    // },

    async upload() {
      try {
        const formData = new FormData();
        formData.append("user", "minki");
        formData.append("content", "minki's content");
        for (let i = 0; i < FileElement.files.length; i++) {
          formData.append("files", FileElement.files[i]);
        }
        const url = "http://localhost:8083/uploadFile";
        const response = await axios.post(url, formData);
        console.log(response);
      } catch (error) {
        console.error(error);
      }
    },

    // async upload() {
    //   var fd = new FormData();
    //   fd.append("files", this.files);

    //   await axios
    //     .post("http://localhost:1337/upload", fd, {
    //       headers: {
    //         "Content-Type": "multipart/form-data",
    //       },
    //     })
    //     .then((response) => {
    //       console.log("SUCCESS!!");
    //       console.log(response.data);
    //     })
    //     .catch(function () {
    //       console.log("FAILURE!!");
    //     });
    // },
  },
};
</script>

<style></style>
