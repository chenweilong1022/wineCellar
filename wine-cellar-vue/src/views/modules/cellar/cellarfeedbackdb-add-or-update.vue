<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="反馈内容" prop="feedbackContent">
      <el-input v-model="dataForm.feedbackContent" placeholder="反馈内容"></el-input>
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
          feedbackId: 0,
          memberId: '',
          state: '',
          createTime: '',
          feedbackContent: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          feedbackContent: [
            { required: true, message: '反馈内容不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.feedbackId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.feedbackId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarfeedbackdb/info/${this.dataForm.feedbackId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.cellarfeedbackdb.memberId
                this.dataForm.state = data.cellarfeedbackdb.state
                this.dataForm.createTime = data.cellarfeedbackdb.createTime
                this.dataForm.feedbackContent = data.cellarfeedbackdb.feedbackContent
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
              url: this.$http.adornUrl(`/cellar/cellarfeedbackdb/${!this.dataForm.feedbackId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'feedbackId': this.dataForm.feedbackId || undefined,
                'memberId': this.dataForm.memberId,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'feedbackContent': this.dataForm.feedbackContent
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
