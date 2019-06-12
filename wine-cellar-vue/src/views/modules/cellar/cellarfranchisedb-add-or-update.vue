<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="公司名称" prop="companyName">
      <el-input readonly v-model="dataForm.companyName" placeholder="公司名称"></el-input>
    </el-form-item>
    <el-form-item label="产品品牌" prop="commodityBrand">
      <el-input readonly v-model="dataForm.commodityBrand" placeholder="产品品牌"></el-input>
    </el-form-item>
    <el-form-item label="联系人" prop="contact">
      <el-input readonly v-model="dataForm.contact" placeholder="联系人"></el-input>
    </el-form-item>
    <el-form-item label="联系号码" prop="contactMobilePhone">
      <el-input readonly v-model="dataForm.contactMobilePhone" placeholder="联系号码"></el-input>
    </el-form-item>
    <el-form-item label="店铺门面照片" prop="pic">
      <el-upload
        disabled
        class="avatar-uploader"
        action="/file/upload">
        <img v-for="value,index in dataForm.shopFrontPhotoList" :src="value" class="avatar">
        <!--<i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
      </el-upload>
    </el-form-item>
    <el-form-item label="店铺面积照片" prop="pic">
      <el-upload
        disabled
        class="avatar-uploader"
        action="/file/upload">
        <img v-for="value,index in dataForm.shopSizePhotoList" :src="value" class="avatar">
        <!--<i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
      </el-upload>
    </el-form-item>
    <el-form-item label="店铺装修照片" prop="pic">
      <el-upload
        disabled
        class="avatar-uploader"
        action="/file/upload">
        <img v-for="value,index in dataForm.shopDecorationPhotosList" :src="value" class="avatar">
        <!--<i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
      </el-upload>
    </el-form-item>
    <el-form-item label="审核状态" prop="reviewStatusStr">
      <el-input readonly v-model="dataForm.reviewStatusStr" placeholder="审核状态"></el-input>
    </el-form-item>
    <el-form-item label="提交会员id" prop="nickname">
      <el-input readonly v-model="dataForm.nickname" placeholder="提交会员id"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="stateStr">
      <el-input readonly v-model="dataForm.stateStr" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input readonly v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <!--<el-button type="primary" @click="dataFormSubmit()">确定</el-button>-->
    </span>
  </el-dialog>
</template>
<style>
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
    /*display: block;*/
  }
</style>
<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          franchiseId: 0,
          state: '',
          stateStr: '',
          createTime: '',
          companyName: '',
          commodityBrand: '',
          contact: '',
          contactMobilePhone: '',
          shopFrontPhoto: '',
          shopSizePhoto: '',
          shopDecorationPhotos: '',
          shopFrontPhotoList: '',
          shopSizePhotoList: '',
          shopDecorationPhotosList: '',
          reviewStatus: '',
          reviewStatusStr: '',
          memberId: '',
          nickname: ''
        },
        dataRule: {
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          companyName: [
            { required: true, message: '公司名称不能为空', trigger: 'blur' }
          ],
          commodityBrand: [
            { required: true, message: '产品品牌不能为空', trigger: 'blur' }
          ],
          contact: [
            { required: true, message: '联系人不能为空', trigger: 'blur' }
          ],
          contactMobilePhone: [
            { required: true, message: '联系号码不能为空', trigger: 'blur' }
          ],
          shopFrontPhoto: [
            { required: true, message: '店铺门面照片不能为空', trigger: 'blur' }
          ],
          shopSizePhoto: [
            { required: true, message: '店铺面积照片不能为空', trigger: 'blur' }
          ],
          shopDecorationPhotos: [
            { required: true, message: '店铺装修照片不能为空', trigger: 'blur' }
          ],
          reviewStatus: [
            { required: true, message: '审核状态不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '提交会员id不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.franchiseId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.franchiseId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarfranchisedb/info/${this.dataForm.franchiseId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.state = data.cellarFranchiseDb.state
                this.dataForm.stateStr = data.cellarFranchiseDb.stateStr
                this.dataForm.createTime = data.cellarFranchiseDb.createTime
                this.dataForm.companyName = data.cellarFranchiseDb.companyName
                this.dataForm.commodityBrand = data.cellarFranchiseDb.commodityBrand
                this.dataForm.contact = data.cellarFranchiseDb.contact
                this.dataForm.contactMobilePhone = data.cellarFranchiseDb.contactMobilePhone
                this.dataForm.shopFrontPhoto = data.cellarFranchiseDb.shopFrontPhoto
                this.dataForm.shopSizePhoto = data.cellarFranchiseDb.shopSizePhoto
                this.dataForm.shopDecorationPhotos = data.cellarFranchiseDb.shopDecorationPhotos
                this.dataForm.shopFrontPhotoList = data.cellarFranchiseDb.shopFrontPhotoList
                this.dataForm.shopSizePhotoList = data.cellarFranchiseDb.shopSizePhotoList
                this.dataForm.shopDecorationPhotosList = data.cellarFranchiseDb.shopDecorationPhotosList
                this.dataForm.reviewStatus = data.cellarFranchiseDb.reviewStatus
                this.dataForm.reviewStatusStr = data.cellarFranchiseDb.reviewStatusStr
                this.dataForm.memberId = data.cellarFranchiseDb.memberId
                this.dataForm.nickname = data.cellarFranchiseDb.cellarMemberDbEntity.nickname
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
              url: this.$http.adornUrl(`/cellar/cellarfranchisedb/${!this.dataForm.franchiseId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'franchiseId': this.dataForm.franchiseId || undefined,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'companyName': this.dataForm.companyName,
                'commodityBrand': this.dataForm.commodityBrand,
                'contact': this.dataForm.contact,
                'contactMobilePhone': this.dataForm.contactMobilePhone,
                'shopFrontPhoto': this.dataForm.shopFrontPhoto,
                'shopSizePhoto': this.dataForm.shopSizePhoto,
                'shopDecorationPhotos': this.dataForm.shopDecorationPhotos,
                'reviewStatus': this.dataForm.reviewStatus,
                'memberId': this.dataForm.memberId
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
