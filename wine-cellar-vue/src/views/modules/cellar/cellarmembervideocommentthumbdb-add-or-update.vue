<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="点赞人" prop="thumbMemberId">
      <el-input v-model="dataForm.thumbMemberId" placeholder="点赞人"></el-input>
    </el-form-item>
    <el-form-item label="会员交友视频评论id" prop="memberVideoCommentId">
      <el-input v-model="dataForm.memberVideoCommentId" placeholder="会员交友视频评论id"></el-input>
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
          memberVideoCommentThumbId: 0,
          thumbMemberId: '',
          memberVideoCommentId: '',
          memberVideoId: '',
          state: '',
          createTime: ''
        },
        dataRule: {
          thumbMemberId: [
            { required: true, message: '点赞人不能为空', trigger: 'blur' }
          ],
          memberVideoCommentId: [
            { required: true, message: '会员交友视频评论id不能为空', trigger: 'blur' }
          ],
          memberVideoId: [
            { required: true, message: '会员交友视频id不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberVideoCommentThumbId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberVideoCommentThumbId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmembervideocommentthumbdb/info/${this.dataForm.memberVideoCommentThumbId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.thumbMemberId = data.cellarmembervideocommentthumbdb.thumbMemberId
                this.dataForm.memberVideoCommentId = data.cellarmembervideocommentthumbdb.memberVideoCommentId
                this.dataForm.memberVideoId = data.cellarmembervideocommentthumbdb.memberVideoId
                this.dataForm.state = data.cellarmembervideocommentthumbdb.state
                this.dataForm.createTime = data.cellarmembervideocommentthumbdb.createTime
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
              url: this.$http.adornUrl(`/cellar/cellarmembervideocommentthumbdb/${!this.dataForm.memberVideoCommentThumbId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberVideoCommentThumbId': this.dataForm.memberVideoCommentThumbId || undefined,
                'thumbMemberId': this.dataForm.thumbMemberId,
                'memberVideoCommentId': this.dataForm.memberVideoCommentId,
                'memberVideoId': this.dataForm.memberVideoId,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime
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
