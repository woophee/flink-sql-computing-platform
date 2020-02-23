<template>
  <el-form :inline="inline" :model="form" ref="form" label-width="100px">
    <el-form-item
      v-for="item in formLabel"
      :key="item.model"
      :label="item.label"
    >
      <el-input
        v-model="form[item.model]"
        :placeholder="'请输入' + item.label"
        v-if="!item.type && item.label != 'SQL'"
      ></el-input>
      <el-input
        v-model="form[item.model]"
        type="textarea"
        :autosize="{ minRows: 10, maxRows: 26 }"
        v-if="item.label === 'SQL'"
      ></el-input>
      <el-select
        v-model="form[item.model]"
        placeholder="请选择"
        v-if="item.type === 'select'"
      >
        <el-option
          v-for="item in item.opts"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item><slot></slot></el-form-item>
  </el-form>
</template>

<script>
export default {
  props: {
    inline: Boolean,
    form: Object,
    formLabel: Array
  }
};
</script>

<style lang="scss" scoped></style>
