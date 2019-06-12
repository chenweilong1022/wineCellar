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
    <el-form-item label="标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="标题"></el-input>
    </el-form-item>
    <el-form-item label="简介" prop="introduction">
      <el-input v-model="dataForm.introduction" placeholder="简介"></el-input>
    </el-form-item>
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="会员头像" prop="headPortrait">
      <el-input v-model="dataForm.headPortrait" placeholder="会员头像"></el-input>
    </el-form-item>
    <el-form-item label="点赞数量" prop="thumbNumber">
      <el-input v-model="dataForm.thumbNumber" placeholder="点赞数量"></el-input>
    </el-form-item>
    <el-form-item label="评论数量" prop="commentNumber">
      <el-input v-model="dataForm.commentNumber" placeholder="评论数量"></el-input>
    </el-form-item>
    <el-form-item label="分享数量" prop="shareNumber">
      <el-input v-model="dataForm.shareNumber" placeholder="分享数量"></el-input>
    </el-form-item>
    <el-form-item label="阿里云视频id" prop="videoId">
      <el-input v-model="dataForm.videoId" placeholder="阿里云视频id"></el-input>
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
          memberVideoId: 0,
          state: '',
          createTime: '',
          title: '',
          introduction: '',
          memberId: '',
          headPortrait: '',
          thumbNumber: '',
          commentNumber: '',
          shareNumber: '',
          videoId: ''
        },
        dataRule: {
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '标题不能为空', trigger: 'blur' }
          ],
          introduction: [
            { required: true, message: '简介不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          headPortrait: [
            { required: true, message: '会员头像不能为空', trigger: 'blur' }
          ],
          thumbNumber: [
            { required: true, message: '点赞数量不能为空', trigger: 'blur' }
          ],
          commentNumber: [
            { required: true, message: '评论数量不能为空', trigger: 'blur' }
          ],
          shareNumber: [
            { required: true, message: '分享数量不能为空', trigger: 'blur' }
          ],
          videoId: [
            { required: true, message: '阿里云视频id不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberVideoId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberVideoId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmembervideodb/info/${this.dataForm.memberVideoId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.state = data.cellarmembervideodb.state
                this.dataForm.createTime = data.cellarmembervideodb.createTime
                this.dataForm.title = data.cellarmembervideodb.title
                this.dataForm.introduction = data.cellarmembervideodb.introduction
                this.dataForm.memberId = data.cellarmembervideodb.memberId
                this.dataForm.headPortrait = data.cellarmembervideodb.headPortrait
                this.dataForm.thumbNumber = data.cellarmembervideodb.thumbNumber
                this.dataForm.commentNumber = data.cellarmembervideodb.commentNumber
                this.dataForm.shareNumber = data.cellarmembervideodb.shareNumber
                this.dataForm.videoId = data.cellarmembervideodb.videoId
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
              url: this.$http.adornUrl(`/cellar/cellarmembervideodb/${!this.dataForm.memberVideoId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberVideoId': this.dataForm.memberVideoId || undefined,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'title': this.dataForm.title,
                'introduction': this.dataForm.introduction,
                'memberId': this.dataForm.memberId,
                'headPortrait': this.dataForm.headPortrait,
                'thumbNumber': this.dataForm.thumbNumber,
                'commentNumber': this.dataForm.commentNumber,
                'shareNumber': this.dataForm.shareNumber,
                'videoId': this.dataForm.videoId
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
