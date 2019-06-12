<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="商品id" prop="commodityId">
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
    <el-form-item label="砍价初始价格" prop="bargainingInitialPrice">
      <el-input v-model="dataForm.bargainingInitialPrice" placeholder="砍价初始价格"></el-input>
    </el-form-item>
    <el-form-item label="砍价最低价格" prop="bargainingLowestPrice">
      <el-input v-model="dataForm.bargainingLowestPrice" placeholder="砍价最低价格"></el-input>
    </el-form-item>
    <el-form-item label="砍价参与人数" prop="bargainingParticipation">
      <el-input v-model="dataForm.bargainingParticipation" placeholder="砍价参与人数"></el-input>
    </el-form-item>
    <!--<el-form-item label="砍价区间" prop="bargainingInterval">-->
      <!--<el-input v-model="dataForm.bargainingInterval" placeholder="砍价区间"></el-input>-->
    <!--</el-form-item>-->
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
        loading: false,
        commodityOptions: [],
        visible: false,
        dataForm: {
          bargainingActivityId: 0,
          state: '',
          createTime: '',
          commodityId: '',
          bargainingInitialPrice: '',
          bargainingLowestPrice: '',
          bargainingParticipation: '',
          bargainingInterval: '',
          storeId: ''
        },
        dataRule: {
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          commodityId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          bargainingInitialPrice: [
            { required: true, message: '砍价初始价格不能为空', trigger: 'blur' }
          ],
          bargainingLowestPrice: [
            { required: true, message: '砍价最低价格不能为空', trigger: 'blur' }
          ],
          bargainingParticipation: [
            { required: true, message: '砍价参与人数不能为空', trigger: 'blur' }
          ],
          bargainingInterval: [
            { required: true, message: '砍价区间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
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
      changeCommodity (commodityId) {
        this.dataForm.commodityId = commodityId
        let obj = {}
        obj = this.commodityOptions.find((item) => {
          return item.commodityId === commodityId
        })
        this.dataForm.storeId = obj.storeId
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
      init (id) {
        this.commodityList()
        this.dataForm.bargainingActivityId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.bargainingActivityId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarbargainingactivitydb/info/${this.dataForm.bargainingActivityId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.state = data.cellarBargainingActivityDb.state
                this.dataForm.createTime = data.cellarBargainingActivityDb.createTime
                this.dataForm.commodityId = data.cellarBargainingActivityDb.commodityId
                this.dataForm.storeId = data.cellarBargainingActivityDb.storeId
                this.dataForm.bargainingInitialPrice = data.cellarBargainingActivityDb.bargainingInitialPrice
                this.dataForm.bargainingLowestPrice = data.cellarBargainingActivityDb.bargainingLowestPrice
                this.dataForm.bargainingParticipation = data.cellarBargainingActivityDb.bargainingParticipation
                this.dataForm.bargainingInterval = data.cellarBargainingActivityDb.bargainingInterval
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
              url: this.$http.adornUrl(`/cellar/cellarbargainingactivitydb/${!this.dataForm.bargainingActivityId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'bargainingActivityId': this.dataForm.bargainingActivityId || undefined,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'commodityId': this.dataForm.commodityId,
                'storeId': this.dataForm.storeId,
                'bargainingInitialPrice': this.dataForm.bargainingInitialPrice,
                'bargainingLowestPrice': this.dataForm.bargainingLowestPrice,
                'bargainingParticipation': this.dataForm.bargainingParticipation,
                'bargainingInterval': this.dataForm.bargainingInterval
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
