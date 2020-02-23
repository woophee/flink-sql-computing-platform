<template>
  <div class="manage">
    <el-dialog
      :title="operateType === 'add' ? '新增实时任务' : '更新实时任务'"
      :visible.sync="isShow"
    >
      <common-form
        :formLabel="operateFormLabel"
        :form="operateForm"
        ref="form"
      ></common-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
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
    <job-table
      :tableData="tableData"
      :tableLabel="tableLabel"
      :config="config"
      @changePage="getList()"
      @edit="editUser"
      @del="delUser"
      @submit="submitJob"
    ></job-table>
  </div>
</template>

<script>
import CommonForm from "../../components/CommonForm";
import JobTable from "./JobTable";
export default {
  components: {
    CommonForm,
    JobTable
  },
  data() {
    return {
      operateType: "add",
      isShow: false,
      tableData: [],
      tableLabel: [
        {
          prop: "name",
          label: "实时任务名称"
        },
        {
          prop: "groupId",
          label: "任务组"
        },
        {
          prop: "parallelism",
          label: "并行度"
        },
        {
          prop: "sqlText",
          label: "SQL",
          width: "300%"
        }
      ],
      config: {
        page: 1,
        total: 30,
        loading: false
      },
      operateForm: {
        groupId: "",
        name: "",
        parallelism: "",
        sqlText: ""
      },
      operateFormLabel: [
        {
          model: "groupId",
          label: "任务组",
          type: "select",
          opts: []
        },
        {
          model: "name",
          label: "实时任务名称"
        },
        {
          model: "parallelism",
          label: "并行度"
        },
        {
          model: "sqlText",
          label: "SQL"
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
        .get(`/api/define-realtime-job/v1/web-query`, {
          params: {
            pageIndex: this.config.page,
            pageSize: 10
          }
        })
        .then(res => {
          console.log(res.data.dataList);
          this.tableData = res.data.dataList.map(item => {
            return item;
          });
          this.config.total = res.data.totalCount;
          this.config.loading = false;
        });
    },
    addUser() {
      this.$http
        .get(`/api/define-group/v1/query`, {
          params: { pageIndex: 1, pageSize: 10 }
        })
        .then(res => {
          res.data.dataList.map(item => {
            this.operateFormLabel[0].opts.push({
              label: item.name,
              value: item.id
            });
          });
          this.config.total = res.data.totalCount;
          this.config.loading = false;
        });
      this.operateForm = {};
      this.operateType = "add";
      this.isShow = true;
    },
    editUser(row) {
      this.operateType = "edit";
      this.isShow = true;
      this.operateForm = row;
    },
    submitJob(row) {
      this.$confirm("是否提交此实时任务?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        let id = row.id;
        this.$http
          .post(`/api/define-realtime-job/v1/submit/${id}`, id)
          .then(res => {
            console.log(res.data);
            this.getList();
          });
      });
    },
    confirm() {
      this.operateFormLabel[0].opts = [];
      console.log(this.operateForm);
      if (this.operateType === "edit") {
        this.$http
          .put(`/api/define-realtime-job/v1/job`, this.operateForm)
          .then(res => {
            console.log(res.data);
            this.isShow = false;
            this.getList();
          });
      } else if (this.operateType === "add") {
        this.$http
          .post(`/api/define-realtime-job/v1/job`, this.operateForm)
          .then(res => {
            console.log(res.data);
            this.isShow = false;
            this.getList();
          });
      }
    },
    cancel() {
      this.operateFormLabel[0].opts = [];
      this.isShow = false;
    },
    delUser(row) {
      this.$confirm("此操作将永久删除该实时任务, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          let id = row.id;
          this.$http
            .delete(`/api/define-realtime-job/v1/job/${id}`, {
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
