<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="地址id" prop="addressId">
      <el-input v-model="dataForm.addressId" placeholder="地址id"></el-input>
    </el-form-item>
    <el-form-item label="送达时间" prop="deliveryTime">
      <el-input v-model="dataForm.deliveryTime" placeholder="送达时间"></el-input>
    </el-form-item>
    <el-form-item label="支付方式" prop="methodPayment">
      <el-input v-model="dataForm.methodPayment" placeholder="支付方式"></el-input>
    </el-form-item>
    <el-form-item label="支付时间" prop="paymentTime">
      <el-input v-model="dataForm.paymentTime" placeholder="支付时间"></el-input>
    </el-form-item>
    <el-form-item label="订单备注" prop="orderNote">
      <el-input v-model="dataForm.orderNote" placeholder="订单备注"></el-input>
    </el-form-item>
    <el-form-item label="订单总金额" prop="totalOrderAmount">
      <el-input v-model="dataForm.totalOrderAmount" placeholder="订单总金额"></el-input>
    </el-form-item>
    <el-form-item label="订单状态" prop="orderStatus">
      <el-input v-model="dataForm.orderStatus" placeholder="订单状态"></el-input>
    </el-form-item>
    <el-form-item label="订单类型" prop="orderType">
      <el-input v-model="dataForm.orderType" placeholder="订单类型"></el-input>
    </el-form-item>
    <el-form-item label="店铺满减金额" prop="storeFullAmount">
      <el-input v-model="dataForm.storeFullAmount" placeholder="店铺满减金额"></el-input>
    </el-form-item>
    <el-form-item label="配送金额" prop="distributionAmount">
      <el-input v-model="dataForm.distributionAmount" placeholder="配送金额"></el-input>
    </el-form-item>
    <el-form-item label="优惠金额" prop="discountAmount">
      <el-input v-model="dataForm.discountAmount" placeholder="优惠金额"></el-input>
    </el-form-item>
    <el-form-item label="订单编号" prop="orderNo">
      <el-input v-model="dataForm.orderNo" placeholder="订单编号"></el-input>
    </el-form-item>
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="店铺id" prop="storeId">
      <el-input v-model="dataForm.storeId" placeholder="店铺id"></el-input>
    </el-form-item>
    <el-form-item label="订单实际金额" prop="actualOrderAmount">
      <el-input v-model="dataForm.actualOrderAmount" placeholder="订单实际金额"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <!--<el-button type="primary" @click="dataFormSubmit()">确定</el-button>-->
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
          actualOrderAmount: ''
        },
        dataRule: {
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          addressId: [
            { required: true, message: '地址id不能为空', trigger: 'blur' }
          ],
          deliveryTime: [
            { required: true, message: '送达时间不能为空', trigger: 'blur' }
          ],
          methodPayment: [
            { required: true, message: '支付方式不能为空', trigger: 'blur' }
          ],
          paymentTime: [
            { required: true, message: '支付时间不能为空', trigger: 'blur' }
          ],
          orderNote: [
            { required: true, message: '订单备注不能为空', trigger: 'blur' }
          ],
          totalOrderAmount: [
            { required: true, message: '订单总金额不能为空', trigger: 'blur' }
          ],
          orderStatus: [
            { required: true, message: '订单状态不能为空', trigger: 'blur' }
          ],
          orderType: [
            { required: true, message: '订单类型不能为空', trigger: 'blur' }
          ],
          storeFullAmount: [
            { required: true, message: '店铺满减金额不能为空', trigger: 'blur' }
          ],
          distributionAmount: [
            { required: true, message: '配送金额不能为空', trigger: 'blur' }
          ],
          discountAmount: [
            { required: true, message: '优惠金额不能为空', trigger: 'blur' }
          ],
          orderNo: [
            { required: true, message: '订单编号不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          storeId: [
            { required: true, message: '店铺id不能为空', trigger: 'blur' }
          ],
          actualOrderAmount: [
            { required: true, message: '订单实际金额不能为空', trigger: 'blur' }
          ]
        }
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
              url: this.$http.adornUrl(`/cellar/cellarorderdb/${!this.dataForm.orderId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'orderId': this.dataForm.orderId || undefined,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'addressId': this.dataForm.addressId,
                'deliveryTime': this.dataForm.deliveryTime,
                'methodPayment': this.dataForm.methodPayment,
                'paymentTime': this.dataForm.paymentTime,
                'orderNote': this.dataForm.orderNote,
                'totalOrderAmount': this.dataForm.totalOrderAmount,
                'orderStatus': this.dataForm.orderStatus,
                'orderType': this.dataForm.orderType,
                'storeFullAmount': this.dataForm.storeFullAmount,
                'distributionAmount': this.dataForm.distributionAmount,
                'discountAmount': this.dataForm.discountAmount,
                'orderNo': this.dataForm.orderNo,
                'memberId': this.dataForm.memberId,
                'storeId': this.dataForm.storeId,
                'actualOrderAmount': this.dataForm.actualOrderAmount
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
