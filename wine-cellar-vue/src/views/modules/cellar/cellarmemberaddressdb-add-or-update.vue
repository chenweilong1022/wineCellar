<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="联系人" prop="contact">
      <el-input v-model="dataForm.contact" placeholder="联系人"></el-input>
    </el-form-item>
    <el-form-item label="联系电话" prop="contactPhone">
      <el-input v-model="dataForm.contactPhone" placeholder="联系电话"></el-input>
    </el-form-item>
    <el-form-item label="省id" prop="provinceId">
      <el-input v-model="dataForm.provinceId" placeholder="省id"></el-input>
    </el-form-item>
    <el-form-item label="省名称" prop="provinceName">
      <el-input v-model="dataForm.provinceName" placeholder="省名称"></el-input>
    </el-form-item>
    <el-form-item label="市id" prop="cityId">
      <el-input v-model="dataForm.cityId" placeholder="市id"></el-input>
    </el-form-item>
    <el-form-item label="市名称" prop="cityName">
      <el-input v-model="dataForm.cityName" placeholder="市名称"></el-input>
    </el-form-item>
    <el-form-item label="县id" prop="countyId">
      <el-input v-model="dataForm.countyId" placeholder="县id"></el-input>
    </el-form-item>
    <el-form-item label="县名称" prop="countyName">
      <el-input v-model="dataForm.countyName" placeholder="县名称"></el-input>
    </el-form-item>
    <el-form-item label="详细地址" prop="detailedAddress">
      <el-input v-model="dataForm.detailedAddress" placeholder="详细地址"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="是否默认 1 默认 0 非默认" prop="idDefault">
      <el-input v-model="dataForm.idDefault" placeholder="是否默认 1 默认 0 非默认"></el-input>
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
          addressId: 0,
          memberId: '',
          contact: '',
          contactPhone: '',
          provinceId: '',
          provinceName: '',
          cityId: '',
          cityName: '',
          countyId: '',
          countyName: '',
          detailedAddress: '',
          createTime: '',
          state: '',
          idDefault: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          contact: [
            { required: true, message: '联系人不能为空', trigger: 'blur' }
          ],
          contactPhone: [
            { required: true, message: '联系电话不能为空', trigger: 'blur' }
          ],
          provinceId: [
            { required: true, message: '省id不能为空', trigger: 'blur' }
          ],
          provinceName: [
            { required: true, message: '省名称不能为空', trigger: 'blur' }
          ],
          cityId: [
            { required: true, message: '市id不能为空', trigger: 'blur' }
          ],
          cityName: [
            { required: true, message: '市名称不能为空', trigger: 'blur' }
          ],
          countyId: [
            { required: true, message: '县id不能为空', trigger: 'blur' }
          ],
          countyName: [
            { required: true, message: '县名称不能为空', trigger: 'blur' }
          ],
          detailedAddress: [
            { required: true, message: '详细地址不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          idDefault: [
            { required: true, message: '是否默认 1 默认 0 非默认不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.addressId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.addressId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmemberaddressdb/info/${this.dataForm.addressId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.cellarmemberaddressdb.memberId
                this.dataForm.contact = data.cellarmemberaddressdb.contact
                this.dataForm.contactPhone = data.cellarmemberaddressdb.contactPhone
                this.dataForm.provinceId = data.cellarmemberaddressdb.provinceId
                this.dataForm.provinceName = data.cellarmemberaddressdb.provinceName
                this.dataForm.cityId = data.cellarmemberaddressdb.cityId
                this.dataForm.cityName = data.cellarmemberaddressdb.cityName
                this.dataForm.countyId = data.cellarmemberaddressdb.countyId
                this.dataForm.countyName = data.cellarmemberaddressdb.countyName
                this.dataForm.detailedAddress = data.cellarmemberaddressdb.detailedAddress
                this.dataForm.createTime = data.cellarmemberaddressdb.createTime
                this.dataForm.state = data.cellarmemberaddressdb.state
                this.dataForm.idDefault = data.cellarmemberaddressdb.idDefault
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
              url: this.$http.adornUrl(`/cellar/cellarmemberaddressdb/${!this.dataForm.addressId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'addressId': this.dataForm.addressId || undefined,
                'memberId': this.dataForm.memberId,
                'contact': this.dataForm.contact,
                'contactPhone': this.dataForm.contactPhone,
                'provinceId': this.dataForm.provinceId,
                'provinceName': this.dataForm.provinceName,
                'cityId': this.dataForm.cityId,
                'cityName': this.dataForm.cityName,
                'countyId': this.dataForm.countyId,
                'countyName': this.dataForm.countyName,
                'detailedAddress': this.dataForm.detailedAddress,
                'createTime': this.dataForm.createTime,
                'state': this.dataForm.state,
                'idDefault': this.dataForm.idDefault
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
