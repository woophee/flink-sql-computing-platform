module.exports = {
  devServer: {
    port: 3333,
    open: true,
    proxy: {
      "/api": {
        target: "http://localhost:9001",
        pathRewrite: {
          "/api": ""
        }
      }
    }
  }
};
