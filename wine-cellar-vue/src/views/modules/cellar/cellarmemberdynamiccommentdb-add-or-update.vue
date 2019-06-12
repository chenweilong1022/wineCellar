<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员动态id" prop="memberDynamicId">
      <el-input v-model="dataForm.memberDynamicId" placeholder="会员动态id"></el-input>
    </el-form-item>
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="会员名称" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder="会员名称"></el-input>
    </el-form-item>
    <el-form-item label="会员头像" prop="headPortrait">
      <el-input v-model="dataForm.headPortrait" placeholder="会员头像"></el-input>
    </el-form-item>
    <el-form-item label="评论内容" prop="commentContent">
      <el-input v-model="dataForm.commentContent" placeholder="评论内容"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
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
          memberDynamicCommentId: 0,
          memberDynamicId: '',
          memberId: '',
          nickname: '',
          headPortrait: '',
          commentContent: '',
          createTime: '',
          state: ''
        },
        dataRule: {
          memberDynamicId: [
            { required: true, message: '会员动态id不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '会员名称不能为空', trigger: 'blur' }
          ],
          headPortrait: [
            { required: true, message: '会员头像不能为空', trigger: 'blur' }
          ],
          commentContent: [
            { required: true, message: '评论内容不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberDynamicCommentId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberDynamicCommentId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmemberdynamiccommentdb/info/${this.dataForm.memberDynamicCommentId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberDynamicId = data.cellarmemberdynamiccommentdb.memberDynamicId
                this.dataForm.memberId = data.cellarmemberdynamiccommentdb.memberId
                this.dataForm.nickname = data.cellarmemberdynamiccommentdb.nickname
                this.dataForm.headPortrait = data.cellarmemberdynamiccommentdb.headPortrait
                this.dataForm.commentContent = data.cellarmemberdynamiccommentdb.commentContent
                this.dataForm.createTime = data.cellarmemberdynamiccommentdb.createTime
                this.dataForm.state = data.cellarmemberdynamiccommentdb.state
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
              url: this.$http.adornUrl(`/cellar/cellarmemberdynamiccommentdb/${!this.dataForm.memberDynamicCommentId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberDynamicCommentId': this.dataForm.memberDynamicCommentId || undefined,
                'memberDynamicId': this.dataForm.memberDynamicId,
                'memberId': this.dataForm.memberId,
                'nickname': this.dataForm.nickname,
                'headPortrait': this.dataForm.headPortrait,
                'commentContent': this.dataForm.commentContent,
                'createTime': this.dataForm.createTime,
                'state': this.dataForm.state
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
