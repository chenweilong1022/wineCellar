<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="分享人" prop="shareMemberId">
      <el-input v-model="dataForm.shareMemberId" placeholder="分享人"></el-input>
    </el-form-item>
    <el-form-item label="会员交友视频id" prop="memberVideoId">
      <el-input v-model="dataForm.memberVideoId" placeholder="会员交友视频id"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="分享类型" prop="shareType">
      <el-input v-model="dataForm.shareType" placeholder="分享类型"></el-input>
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
          memberVideoShareId: 0,
          shareMemberId: '',
          memberVideoId: '',
          state: '',
          createTime: '',
          shareType: ''
        },
        dataRule: {
          shareMemberId: [
            { required: true, message: '分享人不能为空', trigger: 'blur' }
          ],
          memberVideoId: [
            { required: true, message: '会员交友视频id不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          shareType: [
            { required: true, message: '分享类型不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberVideoShareId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberVideoShareId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmembervideosharedb/info/${this.dataForm.memberVideoShareId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.shareMemberId = data.cellarmembervideosharedb.shareMemberId
                this.dataForm.memberVideoId = data.cellarmembervideosharedb.memberVideoId
                this.dataForm.state = data.cellarmembervideosharedb.state
                this.dataForm.createTime = data.cellarmembervideosharedb.createTime
                this.dataForm.shareType = data.cellarmembervideosharedb.shareType
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
              url: this.$http.adornUrl(`/cellar/cellarmembervideosharedb/${!this.dataForm.memberVideoShareId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberVideoShareId': this.dataForm.memberVideoShareId || undefined,
                'shareMemberId': this.dataForm.shareMemberId,
                'memberVideoId': this.dataForm.memberVideoId,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'shareType': this.dataForm.shareType
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
