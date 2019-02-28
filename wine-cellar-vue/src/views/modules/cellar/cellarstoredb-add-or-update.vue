<template>
  <el-dialog
    :title="!dataForm.storeId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
    <el-form-item label="店铺名称" prop="storeName">
      <el-input v-model="dataForm.storeName" placeholder="店铺名称"></el-input>
    </el-form-item>
    <el-form-item label="店铺背景图" prop="storeBackgroundMap">
      <el-upload
        class="avatar-uploader"
        :action="url"
        :show-file-list="false"
        naem="file"
        :on-success="handleAvatarSuccessStoreBackgroundMap">
        <img v-if="dataForm.storeBackgroundMap" :src="dataForm.storeBackgroundMap" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item label="店铺头像图" prop="storeHeadPicture">
      <el-upload
        class="avatar-uploader"
        :action="url"
        :show-file-list="false"
        naem="file"
        :on-success="handleAvatarSuccessStoreHeadPicture">
        <img v-if="dataForm.storeHeadPicture" :src="dataForm.storeHeadPicture" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item label="地址" prop="areaId">
      <el-cascader
        placeholder="地址"
        :options="options"
        :props="defaultParams"
        v-model="selectedOptions3"
        expand-trigger="hover"
        @change="areasChange"
        filterable
      ></el-cascader>
    </el-form-item>
    <el-form-item label="详细地址" prop="detailedAddress">
      <el-input v-model="dataForm.detailedAddress" placeholder="详细地址"></el-input>
    </el-form-item>
    <el-form-item label="店铺折扣" prop="storeDiscounts">
      <div style="width: 30%">
        <el-input v-model="dataForm.storeDiscounts" placeholder="店铺折扣(0-100)">
          <template slot="append">%</template>
        </el-input>
      </div>
    </el-form-item>
    <el-form-item label="支持自取" prop="supportTheirOwn">
      <template>
        <el-radio-group v-model="dataForm.supportTheirOwn">
          <el-radio-button label="0">是</el-radio-button>
          <el-radio-button label="1">否</el-radio-button>
        </el-radio-group>
      </template>
    </el-form-item>
    <el-form-item label="满">
      <el-col :span="3">
        <el-input placeholder="满" v-model="dataForm.full" style="width: 80%;"></el-input>
      </el-col>
      <el-col class="line" :span="1">减</el-col>
      <el-col :span="3">
        <el-input placeholder="减" v-model="dataForm.reductionOf" style="width: 80%;"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item label="经度" prop="longitude">
      <el-input v-model="dataForm.longitude" placeholder="经度"></el-input>
    </el-form-item>
    <el-form-item label="纬度" prop="latitude">
      <el-input v-model="dataForm.latitude" placeholder="纬度"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
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
    display: block;
  }
</style>

<script>
  export default {
    data () {
      return {
        selectedOptions3: [],
        defaultParams: {
          value: 'id',
          label: 'areaName',
          children: 'sysAreaEntities'
        },
        options: [],
        visible: false,
        url: '',
        dataForm: {
          storeId: 0,
          storeName: '',
          provinceId: '',
          cityId: '',
          areaId: '',
          detailedAddress: '',
          storeDiscounts: '',
          supportTheirOwn: '0',
          full: '',
          reductionOf: '',
          storeBackgroundMap: '',
          storeHeadPicture: '',
          longitude: '',
          latitude: '',
          createTime: '',
          state: ''
        },
        dataRule: {
          storeName: [
            { required: true, message: '店铺名称不能为空', trigger: 'blur' }
          ],
          areaId: [
            { required: true, message: '地址不能为空', trigger: 'blur' }
          ],
          detailedAddress: [
            { required: true, message: '详细地址不能为空', trigger: 'blur' }
          ],
          storeDiscounts: [
            { required: true, message: '店铺折扣不能为空', trigger: 'blur' }
          ],
          supportTheirOwn: [
            { required: true, message: '支持自取不能为空', trigger: 'blur' }
          ],
          full: [
            { required: true, message: '满不能为空', trigger: 'blur' }
          ],
          reductionOf: [
            { required: true, message: '减不能为空', trigger: 'blur' }
          ],
          storeBackgroundMap: [
            { required: true, message: '店铺背景图不能为空', trigger: 'blur' }
          ],
          storeHeadPicture: [
            { required: true, message: '店铺头像图不能为空', trigger: 'blur' }
          ],
          longitude: [
            { required: true, message: '经度不能为空', trigger: 'blur' }
          ],
          latitude: [
            { required: true, message: '纬度不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.url = this.$http.adornUrl(`/app/file/upload`)
        this.selectedOptions3 = []
        this.dataForm.full = ''
        this.dataForm.reductionOf = ''
        this.listThreeLevel()
        this.dataForm.storeId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.storeId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarstoredb/info/${this.dataForm.storeId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.storeName = data.cellarStoreDb.storeName
                this.dataForm.provinceId = data.cellarStoreDb.provinceId
                this.dataForm.cityId = data.cellarStoreDb.cityId
                this.dataForm.areaId = data.cellarStoreDb.areaId
                this.dataForm.detailedAddress = data.cellarStoreDb.detailedAddress
                this.dataForm.storeDiscounts = data.cellarStoreDb.storeDiscounts
                this.dataForm.supportTheirOwn = data.cellarStoreDb.supportTheirOwn
                this.dataForm.full = data.cellarStoreDb.full
                this.dataForm.reductionOf = data.cellarStoreDb.reductionOf
                this.dataForm.storeBackgroundMap = data.cellarStoreDb.storeBackgroundMap
                this.dataForm.storeHeadPicture = data.cellarStoreDb.storeHeadPicture
                this.dataForm.longitude = data.cellarStoreDb.longitude
                this.dataForm.latitude = data.cellarStoreDb.latitude
                this.dataForm.createTime = data.cellarStoreDb.createTime
                this.dataForm.state = data.cellarStoreDb.state
                this.selectedOptions3[0] = this.dataForm.provinceId
                this.selectedOptions3[1] = this.dataForm.cityId
                this.selectedOptions3[2] = this.dataForm.areaId
              }
            })
          }
        })
      },
      // 给省市区赋值
      areasChange () {
        this.dataForm.provinceId = this.selectedOptions3[0]
        this.dataForm.cityId = this.selectedOptions3[1]
        this.dataForm.areaId = this.selectedOptions3[2]
      },
      // 店铺背景图
      handleAvatarSuccessStoreBackgroundMap (res, file) {
        this.dataForm.storeBackgroundMap = res.data
      },
      // 店铺头像图
      handleAvatarSuccessStoreHeadPicture (res, file) {
        this.dataForm.storeHeadPicture = res.data
      },
      listThreeLevel () {
        this.$http({
          url: this.$http.adornUrl(`/cellar/sysarea/listThreeLevel`),
          method: 'post',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.options = data.data
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarstoredb/${!this.dataForm.storeId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'storeId': this.dataForm.storeId || undefined,
                'storeName': this.dataForm.storeName,
                'provinceId': this.dataForm.provinceId,
                'cityId': this.dataForm.cityId,
                'areaId': this.dataForm.areaId,
                'detailedAddress': this.dataForm.detailedAddress,
                'storeDiscounts': this.dataForm.storeDiscounts,
                'supportTheirOwn': this.dataForm.supportTheirOwn,
                'full': this.dataForm.full,
                'reductionOf': this.dataForm.reductionOf,
                'storeBackgroundMap': this.dataForm.storeBackgroundMap,
                'storeHeadPicture': this.dataForm.storeHeadPicture,
                'longitude': this.dataForm.longitude,
                'latitude': this.dataForm.latitude,
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
