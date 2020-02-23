<template>
  <div class="manage">
    <el-dialog
      :title="operateType === 'add' ? '新增组' : '更新组'"
      :visible.sync="isShow"
    >
      <common-form
        :formLabel="operateFormLabel"
        :form="operateForm"
        ref="form"
      ></common-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="isShow = false">取 消</el-button>
        <el-button type="primary" @click="confirm">确 定</el-button>
      </div>
    </el-dialog>
    <div class="manage-header">
      <el-button type="primary" @click="addUser">+ 新增</el-button>
      <common-form inline :formLabel="formLabel" :form="searchFrom">
        <el-button type="primary" @click="getList(searchFrom.keyword)"
          >搜索</el-button
        >
      </common-form>
    </div>
    <group-table
      :tableData="tableData"
      :tableLabel="tableLabel"
      :config="config"
      @changePage="getList()"
      @job="queryJob"
      @edit="editUser"
      @del="delUser"
    ></group-table>
  </div>
</template>

<script>
import CommonForm from "../../components/CommonForm";
import GroupTable from "./GroupTable";
export default {
  components: {
    CommonForm,
    GroupTable
  },
  data() {
    return {
      operateType: "add",
      isShow: false,
      tableData: [],
      tableLabel: [
        {
          prop: "name",
          label: "组名"
        },
        {
          prop: "type",
          label: "组类型",
          width: "100%"
        }
      ],
      config: {
        page: 1,
        total: 30,
        loading: false
      },
      operateForm: {
        name: "",
        type: ""
      },
      operateFormLabel: [
        {
          model: "name",
          label: "姓名"
        },
        {
          model: "type",
          label: "组类型",
          type: "select",
          opts: [
            {
              label: "实时任务组",
              value: "REALTIME"
            },
            {
              label: "调度任务组",
              value: "SCHEDULED"
            }
          ]
        }
      ],
      searchFrom: {
        keyword: ""
      },
      formLabel: [
        {
          model: "keyword",
          label: ""
        }
      ]
    };
  },
  methods: {
    getList() {
      this.config.loading = true;
      this.$http
        .get(`/api/define-group/v1/query`, {
          params: {
            pageIndex: this.config.page,
            pageSize: 10
          }
        })
        .then(res => {
          this.tableData = res.data.dataList.map(item => {
            if (item.type === "REALTIME") {
              item.type = "实时任务组";
            } else if (item.type === "SCHEDULED") {
              item.type = "调度任务组";
            }
            return item;
          });
          this.config.total = res.data.totalCount;
          this.config.loading = false;
        });
    },
    queryJob() {},
    addUser() {
      this.operateForm = {};
      this.operateType = "add";
      this.isShow = true;
    },
    editUser(row) {
      this.operateType = "edit";
      this.isShow = true;
      this.operateForm = row;
    },
    confirm() {
      console.log(this.operateForm);
      if (this.operateType === "edit") {
        this.$http
          .put(`/api/define-group/v1/group`, this.operateForm)
          .then(res => {
            console.log(res.data);
            this.isShow = false;
            this.getList();
          });
      } else if (this.operateType === "add") {
        this.$http
          .post(`/api/define-group/v1/group`, this.operateForm)
          .then(res => {
            console.log(res.data);
            this.isShow = false;
            this.getList();
          });
      }
    },
    delUser(row) {
      this.$confirm("此操作将永久删除该组, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          let id = row.id;
          this.$http
            .delete(`/api/define-group/v1/group/${id}`, {
              params: {}
            })
            .then(res => {
              console.log(res.data);
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              this.getList();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    }
  },
  created() {
    this.getList();
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/scss/header";
</style>
