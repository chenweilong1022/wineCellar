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
    <el-form-item label="秒杀开始时间" prop="groupStartTime">
      <div class="block">
        <el-date-picker
          v-model="dataForm.groupStartTime"
          type="datetime"
          default-time="12:00:00"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择日期时间">
        </el-date-picker>
      </div>
    </el-form-item>
    <el-form-item label="秒杀开始时间" prop="groupEndTime">
      <div class="block">
        <el-date-picker
          v-model="dataForm.groupEndTime"
          type="datetime"
          default-time="12:00:00"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择日期时间">
        </el-date-picker>
      </div>
    </el-form-item>
    <el-form-item label="拼团价" prop="groupPrice">
      <el-input v-model="dataForm.groupPrice" placeholder="拼团价"></el-input>
    </el-form-item>
    <el-form-item label="拼团总人数" prop="groupTotalNumber">
      <el-input v-model="dataForm.groupTotalNumber" placeholder="拼团总人数"></el-input>
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
        loading: false,
        commodityOptions: [],
        visible: false,
        dataForm: {
          groupActivityId: 0,
          commodityId: '',
          groupStartTime: '',
          groupEndTime: '',
          groupPrice: '',
          groupTotalNumber: '',
          hasGroupNumber: '',
          stayGroupNumber: '',
          state: '',
          createTime: '',
          storeId: ''
        },
        dataRule: {
          commodityId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          groupStartTime: [
            { required: true, message: '拼团开始时间不能为空', trigger: 'blur' }
          ],
          groupEndTime: [
            { required: true, message: '拼团结束时间不能为空', trigger: 'blur' }
          ],
          groupPrice: [
            { required: true, message: '拼团价不能为空', trigger: 'blur' }
          ],
          groupTotalNumber: [
            { required: true, message: '拼团总人数不能为空', trigger: 'blur' }
          ],
          hasGroupNumber: [
            { required: true, message: '已拼团人数不能为空', trigger: 'blur' }
          ],
          stayGroupNumber: [
            { required: true, message: '待拼团人数不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          storeId: [
            { required: true, message: '店铺id不能为空', trigger: 'blur' }
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
        this.dataForm.groupActivityId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.groupActivityId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellargroupactivitydb/info/${this.dataForm.groupActivityId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                // this.remoteMethod(data.cellarKillActivityDb.cellarCommodityDbEntity.commodityName)
                this.dataForm.commodityId = data.cellarGroupActivityDb.commodityId
                this.dataForm.groupStartTime = data.cellarGroupActivityDb.groupStartTime
                this.dataForm.groupEndTime = data.cellarGroupActivityDb.groupEndTime
                this.dataForm.groupPrice = data.cellarGroupActivityDb.groupPrice
                this.dataForm.groupTotalNumber = data.cellarGroupActivityDb.groupTotalNumber
                this.dataForm.hasGroupNumber = data.cellarGroupActivityDb.hasGroupNumber
                this.dataForm.stayGroupNumber = data.cellarGroupActivityDb.stayGroupNumber
                this.dataForm.state = data.cellarGroupActivityDb.state
                this.dataForm.createTime = data.cellarGroupActivityDb.createTime
                this.dataForm.storeId = data.cellarGroupActivityDb.storeId
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
              url: this.$http.adornUrl(`/cellar/cellargroupactivitydb/${!this.dataForm.groupActivityId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'groupActivityId': this.dataForm.groupActivityId || undefined,
                'commodityId': this.dataForm.commodityId,
                'groupStartTime': this.dataForm.groupStartTime,
                'groupEndTime': this.dataForm.groupEndTime,
                'groupPrice': this.dataForm.groupPrice,
                'groupTotalNumber': this.dataForm.groupTotalNumber,
                'hasGroupNumber': this.dataForm.hasGroupNumber,
                'stayGroupNumber': this.dataForm.stayGroupNumber,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'storeId': this.dataForm.storeId
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
