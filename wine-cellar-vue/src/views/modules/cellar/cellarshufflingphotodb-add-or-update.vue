<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="图片" prop="shufflingFigure">
      <el-upload
        class="avatar-uploader"
        :action="url"
        :show-file-list="false"
        naem="file"
        :on-success="handleAvatarSuccessShufflingFigure">
        <img v-if="dataForm.shufflingFigure" :src="dataForm.shufflingFigure" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item  label="类型" prop="shufflingType">
      <el-select @change="changeShufflingType" clearable v-model="dataForm.shufflingType" placeholder="类型">
        <el-option
          v-for="item in shufflingtypelist"
          :key="item.key"
          :label="item.value"
          :value="item.key">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item v-if="h5PathVisible" label="h5路径" prop="h5Path">
      <el-input v-model="dataForm.h5Path" placeholder="h5路径"></el-input>
    </el-form-item>
    <el-form-item v-if="storeVisible" label="店铺id" prop="storeId">
      <el-select
        remote
        clearable
        filterable
        reserve-keyword
        :remote-method="storeRemoteMethod"
        :loading="storeLoading"
        v-model="dataForm.storeId" filterable placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.storeId"
          :label="item.storeName"
          :value="item.storeId">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item v-if="commodityVisible" label="商品id" prop="commodityId">
      <el-select
        remote
        clearable
        filterable
        reserve-keyword
        :remote-method="remoteMethod"
        :loading="loading"
        @change="changeCommodity" style="width: 100%" v-model="dataForm.commodityId" placeholder="请选择">
        <el-option
          v-for="item in commodityOptions"
          :key="item.commodityId"
          :label="item.commodityName"
          :value="item.commodityId">
          <span style="float: left">{{ item.commodityName }}</span>
          <span style="float: right; color: #8492a6; font-size: 13px">{{ item.storeName }}</span>
        </el-option>
      </el-select>
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
        commodityVisible: false,
        storeVisible: false,
        h5PathVisible: false,
        loading: false,
        storeLoading: false,
        options: [],
        commodityOptions: [],
        shufflingtypelist: [],
        url: '',
        visible: false,
        dataForm: {
          shufflingPhotoId: 0,
          shufflingFigure: '',
          state: '',
          createTime: '',
          shufflingType: '',
          h5Path: '',
          storeId: '',
          commodityId: '',
          commodityName: ''
        },
        dataRule: {
          shufflingFigure: [
            { required: true, message: '轮播图不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          shufflingType: [
            { required: true, message: '类型不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      changeShufflingType (value) {
        this.commodityVisible = false
        this.storeVisible = false
        this.h5PathVisible = false
        this.dataForm.h5Path = ''
        this.dataForm.storeId = ''
        this.dataForm.commodityId = ''
        if (value === 1) {
          this.h5PathVisible = true
        } else if (value === 2) {
          this.storeVisible = true
        } else if (value === 3) {
          this.commodityVisible = true
        }
      },
      remoteMethod (key) {
        if (key !== '') {
          this.loading = true
          setTimeout(() => {
            this.loading = false
            this.commodityList(key)
          }, 200)
        } else {
          this.commodityList('')
        }
      },
      storeRemoteMethod (key) {
        if (key !== '') {
          this.storeLoading = true
          setTimeout(() => {
            this.storeLoading = false
            this.cellarStoreList(key)
          }, 200)
        } else {
          this.cellarStoreList('')
        }
      },
      changeCommodity (commodityId) {
        this.dataForm.commodityId = commodityId
        let obj = {}
        obj = this.commodityOptions.find((item) => {
          return item.commodityId === commodityId
        })
        this.dataForm.storeId = obj.storeId
      },
      shufflingTypeList () {
        this.$http({
          url: this.$http.adornUrl('/sys/constants/shufflingtypelist'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.shufflingtypelist = data.data
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      cellarStoreList (key) {
        this.$http({
          url: this.$http.adornUrl('/cellar/cellarstoredb/list'),
          method: 'get',
          params: this.$http.adornParams({
            'key': key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.options = data.page.list
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      commodityList (key) {
        this.$http({
          url: this.$http.adornUrl('/cellar/cellarcommoditydb/list'),
          method: 'get',
          params: this.$http.adornParams({
            'key': key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.commodityOptions = data.page.list
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      handleAvatarSuccessShufflingFigure (res, file) {
        this.dataForm.shufflingFigure = res.data
      },
      init (id) {
        this.commodityList()
        this.cellarStoreList()
        this.shufflingTypeList()
        this.url = this.$http.adornUrl(`/app/file/upload`)
        this.dataForm.shufflingPhotoId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.shufflingPhotoId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarshufflingphotodb/info/${this.dataForm.shufflingPhotoId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.shufflingFigure = data.cellarShufflingPhotoDb.shufflingFigure
                this.dataForm.state = data.cellarShufflingPhotoDb.state
                this.dataForm.createTime = data.cellarShufflingPhotoDb.createTime
                this.dataForm.shufflingType = data.cellarShufflingPhotoDb.shufflingType
                this.changeShufflingType(this.dataForm.shufflingType)
                this.dataForm.h5Path = data.cellarShufflingPhotoDb.h5Path
                this.dataForm.storeId = data.cellarShufflingPhotoDb.storeId
                this.dataForm.commodityId = data.cellarShufflingPhotoDb.commodityId
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
              url: this.$http.adornUrl(`/cellar/cellarshufflingphotodb/${!this.dataForm.shufflingPhotoId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'shufflingPhotoId': this.dataForm.shufflingPhotoId || undefined,
                'shufflingFigure': this.dataForm.shufflingFigure,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'shufflingType': this.dataForm.shufflingType,
                'h5Path': this.dataForm.h5Path,
                'storeId': this.dataForm.storeId,
                'commodityId': this.dataForm.commodityId
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
