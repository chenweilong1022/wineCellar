<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="消息内容" prop="messageContent">
      <el-input v-model="dataForm.messageContent" placeholder="消息内容"></el-input>
    </el-form-item>
    <el-form-item label="消息标题" prop="messageHead">
      <el-input v-model="dataForm.messageHead" placeholder="消息标题"></el-input>
    </el-form-item>
    <el-form-item label="消息类型" prop="messageType">
      <el-input v-model="dataForm.messageType" placeholder="消息类型"></el-input>
    </el-form-item>
    <el-form-item label="是否已读" prop="haveRead">
      <el-input v-model="dataForm.haveRead" placeholder="是否已读"></el-input>
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
          memberMessageId: 0,
          state: '',
          createTime: '',
          memberId: '',
          messageContent: '',
          messageHead: '',
          messageType: '',
          haveRead: ''
        },
        dataRule: {
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          messageContent: [
            { required: true, message: '消息内容不能为空', trigger: 'blur' }
          ],
          messageHead: [
            { required: true, message: '消息标题不能为空', trigger: 'blur' }
          ],
          messageType: [
            { required: true, message: '消息类型不能为空', trigger: 'blur' }
          ],
          haveRead: [
            { required: true, message: '是否已读不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberMessageId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberMessageId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmembermessagedb/info/${this.dataForm.memberMessageId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.state = data.cellarmembermessagedb.state
                this.dataForm.createTime = data.cellarmembermessagedb.createTime
                this.dataForm.memberId = data.cellarmembermessagedb.memberId
                this.dataForm.messageContent = data.cellarmembermessagedb.messageContent
                this.dataForm.messageHead = data.cellarmembermessagedb.messageHead
                this.dataForm.messageType = data.cellarmembermessagedb.messageType
                this.dataForm.haveRead = data.cellarmembermessagedb.haveRead
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
              url: this.$http.adornUrl(`/cellar/cellarmembermessagedb/${!this.dataForm.memberMessageId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberMessageId': this.dataForm.memberMessageId || undefined,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'memberId': this.dataForm.memberId,
                'messageContent': this.dataForm.messageContent,
                'messageHead': this.dataForm.messageHead,
                'messageType': this.dataForm.messageType,
                'haveRead': this.dataForm.haveRead
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
