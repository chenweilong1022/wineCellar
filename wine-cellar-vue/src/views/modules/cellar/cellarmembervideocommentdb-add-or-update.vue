<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="评论人" prop="commentMemberId">
      <el-input v-model="dataForm.commentMemberId" placeholder="评论人"></el-input>
    </el-form-item>
    <el-form-item label="评论内容" prop="commentContent">
      <el-input v-model="dataForm.commentContent" placeholder="评论内容"></el-input>
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
    <el-form-item label="点赞数量" prop="commentThumbNumber">
      <el-input v-model="dataForm.commentThumbNumber" placeholder="点赞数量"></el-input>
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
          memberVideoCommentId: 0,
          commentMemberId: '',
          commentContent: '',
          memberVideoId: '',
          state: '',
          createTime: '',
          commentThumbNumber: ''
        },
        dataRule: {
          commentMemberId: [
            { required: true, message: '评论人不能为空', trigger: 'blur' }
          ],
          commentContent: [
            { required: true, message: '评论内容不能为空', trigger: 'blur' }
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
          commentThumbNumber: [
            { required: true, message: '点赞数量不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberVideoCommentId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberVideoCommentId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmembervideocommentdb/info/${this.dataForm.memberVideoCommentId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.commentMemberId = data.cellarmembervideocommentdb.commentMemberId
                this.dataForm.commentContent = data.cellarmembervideocommentdb.commentContent
                this.dataForm.memberVideoId = data.cellarmembervideocommentdb.memberVideoId
                this.dataForm.state = data.cellarmembervideocommentdb.state
                this.dataForm.createTime = data.cellarmembervideocommentdb.createTime
                this.dataForm.commentThumbNumber = data.cellarmembervideocommentdb.commentThumbNumber
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
              url: this.$http.adornUrl(`/cellar/cellarmembervideocommentdb/${!this.dataForm.memberVideoCommentId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberVideoCommentId': this.dataForm.memberVideoCommentId || undefined,
                'commentMemberId': this.dataForm.commentMemberId,
                'commentContent': this.dataForm.commentContent,
                'memberVideoId': this.dataForm.memberVideoId,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'commentThumbNumber': this.dataForm.commentThumbNumber
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
