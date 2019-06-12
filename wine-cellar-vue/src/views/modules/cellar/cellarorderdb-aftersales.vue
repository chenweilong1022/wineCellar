<template>
  <el-dialog
    :title="!dataForm.orderId ? '售后' : '售后'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <!--<el-form-item label="状态" prop="state">-->
      <!--<el-input v-model="dataForm.state" placeholder="状态"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="创建时间" prop="createTime">-->
      <!--<el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="地址id" prop="addressId">-->
      <!--<el-input v-model="dataForm.addressId" placeholder="地址id"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="送达时间" prop="deliveryTime">-->
      <!--<el-input v-model="dataForm.deliveryTime" placeholder="送达时间"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="支付方式" prop="methodPayment">-->
      <!--<el-input v-model="dataForm.methodPayment" placeholder="支付方式"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="支付时间" prop="paymentTime">-->
      <!--<el-input v-model="dataForm.paymentTime" placeholder="支付时间"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="订单备注" prop="orderNote">-->
      <!--<el-input v-model="dataForm.orderNote" placeholder="订单备注"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="订单总金额" prop="totalOrderAmount">-->
      <!--<el-input v-model="dataForm.totalOrderAmount" placeholder="订单总金额"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="订单状态" prop="orderStatus">-->
      <!--<el-input v-model="dataForm.orderStatus" placeholder="订单状态"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="订单类型" prop="orderType">-->
      <!--<el-input v-model="dataForm.orderType" placeholder="订单类型"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="店铺满减金额" prop="storeFullAmount">-->
      <!--<el-input v-model="dataForm.storeFullAmount" placeholder="店铺满减金额"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="配送金额" prop="distributionAmount">-->
      <!--<el-input v-model="dataForm.distributionAmount" placeholder="配送金额"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="优惠金额" prop="discountAmount">-->
      <!--<el-input v-model="dataForm.discountAmount" placeholder="优惠金额"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="订单编号" prop="orderNo">-->
      <!--<el-input v-model="dataForm.orderNo" placeholder="订单编号"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="会员id" prop="memberId">-->
      <!--<el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="店铺id" prop="storeId">-->
      <!--<el-input v-model="dataForm.storeId" placeholder="店铺id"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="订单实际金额" prop="actualOrderAmount">-->
      <!--<el-input v-model="dataForm.actualOrderAmount" placeholder="订单实际金额"></el-input>-->
    <!--</el-form-item>-->
      <el-form-item label="退货原因" prop="actualOrderAmount">
        <el-input v-model="dataForm.returnReason" placeholder="退货原因" readonly></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <!--<el-button @click="visible = false">取消</el-button>-->
      <el-button type="primary" @click="dataFormSubmit('-3')">同意</el-button>
      <el-button type="primary" @click="dataFormSubmit('-4')">拒绝</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          orderId: 0,
          state: '',
          createTime: '',
          addressId: '',
          deliveryTime: '',
          methodPayment: '',
          paymentTime: '',
          orderNote: '',
          totalOrderAmount: '',
          orderStatus: '',
          orderType: '',
          storeFullAmount: '',
          distributionAmount: '',
          discountAmount: '',
          orderNo: '',
          memberId: '',
          storeId: '',
          actualOrderAmount: '',
          returnReason: ''
        },
        dataRule: {}
      }
    },
    methods: {
      init (id) {
        this.dataForm.orderId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.orderId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarorderdb/info/${this.dataForm.orderId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.state = data.cellarOrderDb.state
                this.dataForm.createTime = data.cellarOrderDb.createTime
                this.dataForm.addressId = data.cellarOrderDb.addressId
                this.dataForm.deliveryTime = data.cellarOrderDb.deliveryTime
                this.dataForm.methodPayment = data.cellarOrderDb.methodPayment
                this.dataForm.paymentTime = data.cellarOrderDb.paymentTime
                this.dataForm.orderNote = data.cellarOrderDb.orderNote
                this.dataForm.totalOrderAmount = data.cellarOrderDb.totalOrderAmount
                this.dataForm.orderStatus = data.cellarOrderDb.orderStatus
                this.dataForm.orderType = data.cellarOrderDb.orderType
                this.dataForm.storeFullAmount = data.cellarOrderDb.storeFullAmount
                this.dataForm.distributionAmount = data.cellarOrderDb.distributionAmount
                this.dataForm.discountAmount = data.cellarOrderDb.discountAmount
                this.dataForm.orderNo = data.cellarOrderDb.orderNo
                this.dataForm.memberId = data.cellarOrderDb.memberId
                this.dataForm.storeId = data.cellarOrderDb.storeId
                this.dataForm.actualOrderAmount = data.cellarOrderDb.actualOrderAmount
                this.dataForm.returnReason = data.cellarOrderDb.returnReason
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit (orderStatus) {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarorderdb/update`),
              method: 'post',
              data: this.$http.adornData({
                'orderId': this.dataForm.orderId || undefined,
                'orderStatus': orderStatus
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
