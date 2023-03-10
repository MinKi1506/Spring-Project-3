const { defineConfig } = require("@vue/cli-service");

module.exports = defineConfig({
  outputDir: "../src/main/resources/static/",
  // indexPath: "../static/index.html",

  devServer: {
    proxy: {
      //접근이 "/api'"로 들어오면 8083(app단의 스프링서버)로 보낸다
      "/api": {
        target: "http://192.168.50.147:8083",
        // target: "http://localhost:8083",
        changeOrigin: true,
      },
    },
  },

  transpileDependencies: ["vuetify"],
});
