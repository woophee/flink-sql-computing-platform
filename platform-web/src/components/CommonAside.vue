<template>
  <el-menu
    :collapse="isCollapse"
    default-active="2"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
  >
    <h3 v-show="isCollapse">Flink</h3>
    <h3 v-show="!isCollapse">Flink SQL计算平台</h3>
    <el-menu-item
      index="item.path"
      v-for="item in noChildren"
      :key="item.path"
      @click="clickMenu(item)"
    >
      <i :class="'el-icon-' + item.icon"></i>
      <span slot="title">{{ item.label }}</span>
    </el-menu-item>
    <el-submenu index="index" v-for="(item, index) in hasChildren" :key="index">
      <template slot="title">
        <i :class="'el-icon-' + item.icon"></i>
        <span slot="title">{{ item.label }}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item
          :index="subItem.path"
          v-for="(subItem, subIndex) in item.children"
          :key="subIndex"
          @click="clickMenu(subItem)"
        >
          <i :class="'el-icon-' + subItem.icon"></i>
          <span slot="title">{{ subItem.label }}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  computed: {
    noChildren() {
      return this.asideMenu.filter(item => !item.children);
    },
    hasChildren() {
      return this.asideMenu.filter(item => item.children);
    },
    isCollapse() {
      return this.$store.state.tab.isCollapse;
    }
  },
  data() {
    return {
      asideMenu: [
        {
          path: "/",
          name: "home",
          label: "首页",
          icon: "s-home"
        },
        {
          label: "任务配置",
          icon: "s-tools",
          children: [
            {
              path: "/group",
              name: "group",
              label: "任务组",
              icon: "goods"
            },
            {
              path: "/realtime-job",
              name: "realtime-job",
              label: "实时任务",
              icon: "star-off"
            },
            {
              path: "/scheduled-job",
              name: "scheduled-job",
              label: "调度任务",
              icon: "star-off"
            }
          ]
        },
        {
          label: "任务状态",
          icon: "s-promotion",
          children: [
            {
              path: "/realtime-status",
              name: "realtime-status",
              label: "实时任务状态",
              icon: "monitor"
            },
            {
              path: "/scheduled-status",
              name: "scheduled-status",
              label: "定时任务状态",
              icon: "monitor"
            },
            {
              path: "/status-bycluster",
              name: "status-bycluster",
              label: "按集群查看任务状态",
              icon: "monitor"
            }
          ]
        },
        {
          label: "集群管理",
          icon: "menu",
          children: [
            {
              path: "/cluster-list",
              name: "cluster-list",
              label: "集群列表",
              icon: "s-order"
            },
            {
              path: "/cluster-manage",
              name: "cluster-manage",
              label: "集群管理",
              icon: "s-operation"
            }
          ]
        }
      ]
    };
  },
  methods: {
    clickMenu(item) {
      this.$router.push({ name: item.name });
      this.$store.commit("selectMenu", item);
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
};
</script>

<style lang="scss" scoped>
.el-menu {
  height: 100%;
  border: none;
  h3 {
    color: #ffffff;
    text-align: center;
    line-height: 48px;
  }
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
