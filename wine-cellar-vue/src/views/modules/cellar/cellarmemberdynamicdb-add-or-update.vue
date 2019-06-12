<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="文字内容" prop="writtenContent">
      <el-input v-model="dataForm.writtenContent" placeholder="文字内容"></el-input>
    </el-form-item>
    <el-form-item label="图片内容" prop="imageContent">
      <el-input v-model="dataForm.imageContent" placeholder="图片内容"></el-input>
    </el-form-item>
    <el-form-item label="点赞数量" prop="thumbNumber">
      <el-input v-model="dataForm.thumbNumber" placeholder="点赞数量"></el-input>
    </el-form-item>
    <el-form-item label="评论数量" prop="commentNumber">
      <el-input v-model="dataForm.commentNumber" placeholder="评论数量"></el-input>
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
          memberDynamicId: 0,
          createTime: '',
          state: '',
          writtenContent: '',
          imageContent: '',
          thumbNumber: '',
          commentNumber: '',
          memberId: '',
          nickname: '',
          headPortrait: ''
        },
        dataRule: {
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          writtenContent: [
            { required: true, message: '文字内容不能为空', trigger: 'blur' }
          ],
          imageContent: [
            { required: true, message: '图片内容不能为空', trigger: 'blur' }
          ],
          thumbNumber: [
            { required: true, message: '点赞数量不能为空', trigger: 'blur' }
          ],
          commentNumber: [
            { required: true, message: '评论数量不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '会员名称不能为空', trigger: 'blur' }
          ],
          headPortrait: [
            { required: true, message: '会员头像不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberDynamicId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberDynamicId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmemberdynamicdb/info/${this.dataForm.memberDynamicId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.createTime = data.cellarmemberdynamicdb.createTime
                this.dataForm.state = data.cellarmemberdynamicdb.state
                this.dataForm.writtenContent = data.cellarmemberdynamicdb.writtenContent
                this.dataForm.imageContent = data.cellarmemberdynamicdb.imageContent
                this.dataForm.thumbNumber = data.cellarmemberdynamicdb.thumbNumber
                this.dataForm.commentNumber = data.cellarmemberdynamicdb.commentNumber
                this.dataForm.memberId = data.cellarmemberdynamicdb.memberId
                this.dataForm.nickname = data.cellarmemberdynamicdb.nickname
                this.dataForm.headPortrait = data.cellarmemberdynamicdb.headPortrait
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
              url: this.$http.adornUrl(`/cellar/cellarmemberdynamicdb/${!this.dataForm.memberDynamicId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberDynamicId': this.dataForm.memberDynamicId || undefined,
                'createTime': this.dataForm.createTime,
                'state': this.dataForm.state,
                'writtenContent': this.dataForm.writtenContent,
                'imageContent': this.dataForm.imageContent,
                'thumbNumber': this.dataForm.thumbNumber,
                'commentNumber': this.dataForm.commentNumber,
                'memberId': this.dataForm.memberId,
                'nickname': this.dataForm.nickname,
                'headPortrait': this.dataForm.headPortrait
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
