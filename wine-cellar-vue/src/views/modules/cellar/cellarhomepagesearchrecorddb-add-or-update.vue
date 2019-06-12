<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="搜索关键字" prop="searchKeywords">
      <el-input v-model="dataForm.searchKeywords" placeholder="搜索关键字"></el-input>
    </el-form-item>
    <el-form-item label="搜索次数" prop="searchNumber">
      <el-input v-model="dataForm.searchNumber" placeholder="搜索次数"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          homePageSearchRecordId: 0,
          searchKeywords: '',
          searchNumber: '',
          state: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          searchKeywords: [
            { required: true, message: '搜索关键字不能为空', trigger: 'blur' }
          ],
          searchNumber: [
            { required: true, message: '搜索次数不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.homePageSearchRecordId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.homePageSearchRecordId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarhomepagesearchrecorddb/info/${this.dataForm.homePageSearchRecordId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.searchKeywords = data.cellarhomepagesearchrecorddb.searchKeywords
                this.dataForm.searchNumber = data.cellarhomepagesearchrecorddb.searchNumber
                this.dataForm.state = data.cellarhomepagesearchrecorddb.state
                this.dataForm.createTime = data.cellarhomepagesearchrecorddb.createTime
                this.dataForm.updateTime = data.cellarhomepagesearchrecorddb.updateTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarhomepagesearchrecorddb/${!this.dataForm.homePageSearchRecordId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'homePageSearchRecordId': this.dataForm.homePageSearchRecordId || undefined,
                'searchKeywords': this.dataForm.searchKeywords,
                'searchNumber': this.dataForm.searchNumber,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
