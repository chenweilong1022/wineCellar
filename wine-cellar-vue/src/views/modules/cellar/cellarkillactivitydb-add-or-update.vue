<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
    <!--<el-form-item label="商品id" prop="commodityId">-->
      <!--<el-input v-model="dataForm.commodityId" placeholder="商品id"></el-input>-->
    <!--</el-form-item>-->

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
    <el-form-item label="秒杀开始时间" prop="killStartTime">
      <div class="block">
        <el-date-picker
          v-model="dataForm.killStartTime"
          type="datetime"
          default-time="12:00:00"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择日期时间">
        </el-date-picker>
      </div>
    </el-form-item>
    <el-form-item label="秒杀开始时间" prop="killStartTime">
      <div class="block">
        <el-date-picker
          v-model="dataForm.killEndTime"
          type="datetime"
          default-time="12:00:00"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择日期时间">
        </el-date-picker>
      </div>
    </el-form-item>
    <el-form-item label="秒杀价格" prop="killPrice">
      <el-input v-model="dataForm.killPrice" placeholder="秒杀价格"></el-input>
    </el-form-item>
    <!--<el-form-item label="店铺id" prop="storeId">-->
      <!--<el-input v-model="dataForm.storeId" placeholder="店铺id"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="状态" prop="state">-->
      <!--<el-input v-model="dataForm.state" placeholder="状态"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="创建时间" prop="createTime">-->
      <!--<el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>-->
    <!--</el-form-item>-->
    <el-form-item label="秒杀数量" prop="killNumber">
      <el-input v-model="dataForm.killNumber" placeholder="秒杀数量"></el-input>
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
        loading: false,
        commodityOptions: [],
        dataForm: {
          killActivityId: 0,
          commodityId: '',
          killStartTime: '',
          killEndTime: '',
          killPrice: '',
          storeId: '',
          state: '',
          createTime: '',
          killNumber: ''
        },
        dataRule: {
          commodityId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          killStartTime: [
            { required: true, message: '秒杀开始时间不能为空', trigger: 'blur' }
          ],
          killEndTime: [
            { required: true, message: '秒杀结束时间不能为空', trigger: 'blur' }
          ],
          killPrice: [
            { required: true, message: '秒杀价格不能为空', trigger: 'blur' }
          ],
          storeId: [
            { required: true, message: '店铺id不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          killNumber: [
            { required: true, message: '秒杀数量不能为空', trigger: 'blur' }
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
        this.dataForm.killActivityId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.killActivityId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarkillactivitydb/info/${this.dataForm.killActivityId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.remoteMethod(data.cellarKillActivityDb.cellarCommodityDbEntity.commodityName)
                this.dataForm.commodityId = data.cellarKillActivityDb.commodityId
                this.dataForm.killStartTime = data.cellarKillActivityDb.killStartTime
                this.dataForm.killEndTime = data.cellarKillActivityDb.killEndTime
                this.dataForm.killPrice = data.cellarKillActivityDb.killPrice
                this.dataForm.storeId = data.cellarKillActivityDb.storeId
                this.dataForm.state = data.cellarKillActivityDb.state
                this.dataForm.createTime = data.cellarKillActivityDb.createTime
                this.dataForm.killNumber = data.cellarKillActivityDb.killNumber
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
              url: this.$http.adornUrl(`/cellar/cellarkillactivitydb/${!this.dataForm.killActivityId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'killActivityId': this.dataForm.killActivityId || undefined,
                'commodityId': this.dataForm.commodityId,
                'killStartTime': this.dataForm.killStartTime,
                'killEndTime': this.dataForm.killEndTime,
                'killPrice': this.dataForm.killPrice,
                'storeId': this.dataForm.storeId,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'killNumber': this.dataForm.killNumber
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
