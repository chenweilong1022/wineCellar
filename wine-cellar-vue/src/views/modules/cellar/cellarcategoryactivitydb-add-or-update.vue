<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="活动标题" prop="activityTitle">
      <el-input v-model="dataForm.activityTitle" placeholder="活动标题"></el-input>
    </el-form-item>
    <el-form-item label="活动描述" prop="activityDescription">
      <el-input v-model="dataForm.activityDescription" placeholder="活动描述"></el-input>
    </el-form-item>
    <el-form-item label="活动图片" prop="activityPhotos">
      <el-upload
        class="avatar-uploader"
        :action="url"
        :show-file-list="false"
        naem="file"
        :on-success="handleAvatarSuccessActivityActivityPhotos">
        <img v-if="dataForm.activityPhotos" :src="dataForm.activityPhotos" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item label="活动背景图" prop="cellarCategoryActivityDb">
      <el-upload
        class="avatar-uploader"
        :action="url"
        :show-file-list="false"
        naem="file"
        :on-success="handleAvatarSuccessActivityBackgroundMap">
        <img v-if="dataForm.activityBackgroundMap" :src="dataForm.activityBackgroundMap" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>
<style>
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
<script>
  export default {
    data () {
      return {
        url: '',
        visible: false,
        dataForm: {
          categoryActivityId: 0,
          activityTitle: '',
          activityDescription: '',
          activityPhotos: '',
          activityBackgroundMap: '',
          state: '',
          createTime: ''
        },
        dataRule: {
          activityTitle: [
            { required: true, message: '活动标题不能为空', trigger: 'blur' }
          ],
          activityDescription: [
            { required: true, message: '活动描述不能为空', trigger: 'blur' }
          ],
          activityPhotos: [
            { required: true, message: '活动图片不能为空', trigger: 'blur' }
          ],
          activityBackgroundMap: [
            { required: true, message: '活动背景图不能为空', trigger: 'blur' }
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
      handleAvatarSuccessActivityActivityPhotos (res) {
        this.dataForm.activityPhotos = res.data
      },
      handleAvatarSuccessActivityBackgroundMap (res) {
        this.dataForm.activityBackgroundMap = res.data
      },
      init (id) {
        this.url = this.$http.adornUrl(`/app/file/upload`)
        this.dataForm.categoryActivityId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.categoryActivityId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarcategoryactivitydb/info/${this.dataForm.categoryActivityId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.activityTitle = data.cellarCategoryActivityDb.activityTitle
                this.dataForm.activityDescription = data.cellarCategoryActivityDb.activityDescription
                this.dataForm.activityPhotos = data.cellarCategoryActivityDb.activityPhotos
                this.dataForm.activityBackgroundMap = data.cellarCategoryActivityDb.activityBackgroundMap
                this.dataForm.state = data.cellarCategoryActivityDb.state
                this.dataForm.createTime = data.cellarCategoryActivityDb.createTime
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
              url: this.$http.adornUrl(`/cellar/cellarcategoryactivitydb/${!this.dataForm.categoryActivityId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'categoryActivityId': this.dataForm.categoryActivityId || undefined,
                'activityTitle': this.dataForm.activityTitle,
                'activityDescription': this.dataForm.activityDescription,
                'activityPhotos': this.dataForm.activityPhotos,
                'activityBackgroundMap': this.dataForm.activityBackgroundMap,
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
