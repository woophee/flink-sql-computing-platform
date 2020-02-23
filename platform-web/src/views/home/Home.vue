<template>
  <el-row class="home" :gutter="20">
    <el-col>
      <el-card shadow="hover">
        <div class="user">
          <img :src="userImg" />
          <div class="userinfo">
            <p class="name">admin</p>
            <p class="access">超级管理员</p>
          </div>
        </div>
        <div class="login-info">
          <p>上次登录时间：<span>2000-1-1</span></p>
          <p>上次登录地点：<span>杭州</span></p>
        </div>
      </el-card>
      <div class="num" style="margin-top: 30px">
        <el-card
          shadow="hover"
          v-for="item in countData"
          :key="item.name"
          :body-style="{ display: 'flex', padding: 0 }"
        >
          <i
            class="icon"
            :class="`el-icon-${item.icon}`"
            :style="{ background: item.color }"
          ></i>
          <div class="detail">
            <p class="num">{{ item.value }}</p>
            <p class="txt">{{ item.name }}</p>
          </div>
        </el-card>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      userImg: require("../../assets/images/user.jpg"),
      countData: [
        {
          name: "持有的集群",
          value: 6,
          icon: "burger",
          color: "#409EEf"
        },
        {
          name: "持有的任务",
          value: 3,
          icon: "tableware",
          color: "#ffb980"
        },
        {
          name: "正在运行的任务",
          value: 3,
          icon: "sugar",
          color: "#67C23A"
        },
        {
          name: "持有的任务组",
          value: 1,
          icon: "dessert",
          color: "#409EEf"
        },
        {
          name: "取消的任务",
          value: 0,
          icon: "ice-cream",
          color: "#909399"
        },
        {
          name: "失败的任务",
          value: 0,
          icon: "hot-water",
          color: "#F56C6C"
        }
      ]
    };
  }
};
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/home";
</style>
