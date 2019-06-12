<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">

    <!--<el-form-item label="优惠券类型" prop="couponType">-->
      <!--<el-input v-model="dataForm.couponType" placeholder="优惠券类型"></el-input>-->
    <!--</el-form-item>-->

    <el-form-item>
      <el-select clearable v-model="dataForm.couponType" placeholder="优惠券类型">
        <el-option
          v-for="item in coupontypelist"
          :key="item.key"
          :label="item.value"
          :value="item.key">
        </el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="优惠券数量" prop="couponNumbers">
      <el-input v-model="dataForm.couponNumbers" placeholder="优惠券数量"></el-input>
    </el-form-item>

    <el-form-item label="有效期">
      <div class="block">
        <el-date-picker
          :editable="false"
          v-model="timeInterval"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="datetimerange"
          format="yyyy-MM-dd HH:mm:ss"
          @change="timeIntervalHandler"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
      </div>
    </el-form-item>
    <el-form-item label="满">
      <el-col :span="5">
        <el-input placeholder="满" v-model="dataForm.full" style="width: 80%;"></el-input>
      </el-col>
      <el-col class="line" :span="1">减</el-col>
      <el-col :span="5">
        <el-input placeholder="减" v-model="dataForm.reductionOf" style="width: 80%;"></el-input>
      </el-col>
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
        coupontypelist: [],
        timeInterval: [],
        visible: false,
        dataForm: {
          couponId: 0,
          storeId: '',
          createTime: '',
          state: '',
          couponNumbers: '',
          couponType: '',
          effectiveStartTime: '',
          effectiveEndTime: '',
          full: '',
          reductionOf: ''
        },
        dataRule: {
          couponNumbers: [
            { required: true, message: '优惠券数量不能为空', trigger: 'blur' }
          ],
          couponType: [
            { required: true, message: '优惠券类型不能为空', trigger: 'blur' }
          ],
          full: [
            { required: true, message: '满不能为空', trigger: 'blur' }
          ],
          reductionOf: [
            { required: true, message: '减不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      getUTCTime (str) {
        str = new Date(str)
        var utc = str.toUTCString()
        return utc
      },
      //
      couponTypeList () {
        this.$http({
          url: this.$http.adornUrl('/sys/constants/coupontypelist'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.coupontypelist = data.data
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      // 给有效期赋值
      timeIntervalHandler (timeInterval) {
        if (timeInterval) {
          this.dataForm.effectiveStartTime = timeInterval[0]
          this.dataForm.effectiveEndTime = timeInterval[1]
        } else {
          this.dataForm.effectiveStartTime = ''
          this.dataForm.effectiveEndTime = ''
        }
      },
      init (id) {
        this.couponTypeList()
        this.dataForm.couponId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.couponId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarcoupondb/info/${this.dataForm.couponId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.storeId = data.cellarCouponDb.storeId
                this.dataForm.createTime = data.cellarCouponDb.createTime
                this.dataForm.state = data.cellarCouponDb.state
                this.dataForm.couponNumbers = data.cellarCouponDb.couponNumbers
                this.dataForm.couponType = data.cellarCouponDb.couponType
                this.dataForm.effectiveStartTime = data.cellarCouponDb.effectiveStartTime
                this.dataForm.effectiveEndTime = data.cellarCouponDb.effectiveEndTime
                this.dataForm.full = data.cellarCouponDb.full
                this.dataForm.reductionOf = data.cellarCouponDb.reductionOf
                this.timeInterval[0] = new Date(2000, 10, 10, 10, 10)
                this.timeInterval[1] = new Date(2000, 10, 11, 10, 10)
                console.log(this.timeInterval)
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
              url: this.$http.adornUrl(`/cellar/cellarcoupondb/${!this.dataForm.couponId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'couponId': this.dataForm.couponId || undefined,
                'storeId': this.dataForm.storeId,
                'createTime': this.dataForm.createTime,
                'state': this.dataForm.state,
                'couponNumbers': this.dataForm.couponNumbers,
                'couponType': this.dataForm.couponType,
                'effectiveStartTime': this.dataForm.effectiveStartTime,
                'effectiveEndTime': this.dataForm.effectiveEndTime,
                'full': this.dataForm.full,
                'reductionOf': this.dataForm.reductionOf
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
