<template>
  <div class="tabs">
    <el-tag
      :key="tag.name"
      size="small"
      v-for="tag in tags"
      :closable="tag.name !== 'home'"
      :disable-transitions="false"
      @close="handleClose(tag)"
      @click="changeMenu(tag)"
      :effect="$route.name === tag.name ? 'dark' : 'plain'"
    >
      {{ tag.label }}
    </el-tag>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  computed: {
    ...mapState({
      tags: state => state.tab.tabList
    })
  },
  methods: {
    ...mapMutations({
      close: "closeTab"
    }),
    handleClose(tag) {
      this.close(tag);
    },
    changeMenu(item) {
      this.$router.push({ name: item.name });
      this.$store.commit("selectMenu", item);
    }
  }
};
</script>

<style lang="scss" scoped>
.tabs {
  padding: 20px;
  .el-tag {
    margin-right: 15px;
    cursor: pointer;
  }
}
</style>
