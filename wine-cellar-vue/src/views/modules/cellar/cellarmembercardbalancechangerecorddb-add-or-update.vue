<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="储值卡变动金额" prop="changeCardBalance">
      <el-input v-model="dataForm.changeCardBalance" placeholder="储值卡变动金额"></el-input>
    </el-form-item>
    <el-form-item label="储值卡变动前金额" prop="beforeCardBalance">
      <el-input v-model="dataForm.beforeCardBalance" placeholder="储值卡变动前金额"></el-input>
    </el-form-item>
    <el-form-item label="储值卡变动后金额" prop="afterCardBalance">
      <el-input v-model="dataForm.afterCardBalance" placeholder="储值卡变动后金额"></el-input>
    </el-form-item>
    <el-form-item label="变动类型" prop="changeType">
      <el-input v-model="dataForm.changeType" placeholder="变动类型"></el-input>
    </el-form-item>
    <el-form-item label="变动描述" prop="changeDesc">
      <el-input v-model="dataForm.changeDesc" placeholder="变动描述"></el-input>
    </el-form-item>
    <el-form-item label="订单id" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder="订单id"></el-input>
    </el-form-item>
    <el-form-item label="订单编号" prop="orderNo">
      <el-input v-model="dataForm.orderNo" placeholder="订单编号"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="支付方式" prop="methodPayment">
      <el-input v-model="dataForm.methodPayment" placeholder="支付方式"></el-input>
    </el-form-item>
    <el-form-item label="支付时间" prop="paymentTime">
      <el-input v-model="dataForm.paymentTime" placeholder="支付时间"></el-input>
    </el-form-item>
    <el-form-item label="支付状态" prop="recordStatus">
      <el-input v-model="dataForm.recordStatus" placeholder="支付状态"></el-input>
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
          memberCardBalanceChangeRecordId: 0,
          memberId: '',
          changeCardBalance: '',
          beforeCardBalance: '',
          afterCardBalance: '',
          changeType: '',
          changeDesc: '',
          orderId: '',
          orderNo: '',
          state: '',
          createTime: '',
          methodPayment: '',
          paymentTime: '',
          recordStatus: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          changeCardBalance: [
            { required: true, message: '储值卡变动金额不能为空', trigger: 'blur' }
          ],
          beforeCardBalance: [
            { required: true, message: '储值卡变动前金额不能为空', trigger: 'blur' }
          ],
          afterCardBalance: [
            { required: true, message: '储值卡变动后金额不能为空', trigger: 'blur' }
          ],
          changeType: [
            { required: true, message: '变动类型不能为空', trigger: 'blur' }
          ],
          changeDesc: [
            { required: true, message: '变动描述不能为空', trigger: 'blur' }
          ],
          orderId: [
            { required: true, message: '订单id不能为空', trigger: 'blur' }
          ],
          orderNo: [
            { required: true, message: '订单编号不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          methodPayment: [
            { required: true, message: '支付方式不能为空', trigger: 'blur' }
          ],
          paymentTime: [
            { required: true, message: '支付时间不能为空', trigger: 'blur' }
          ],
          recordStatus: [
            { required: true, message: '支付状态不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberCardBalanceChangeRecordId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberCardBalanceChangeRecordId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmembercardbalancechangerecorddb/info/${this.dataForm.memberCardBalanceChangeRecordId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.cellarmembercardbalancechangerecorddb.memberId
                this.dataForm.changeCardBalance = data.cellarmembercardbalancechangerecorddb.changeCardBalance
                this.dataForm.beforeCardBalance = data.cellarmembercardbalancechangerecorddb.beforeCardBalance
                this.dataForm.afterCardBalance = data.cellarmembercardbalancechangerecorddb.afterCardBalance
                this.dataForm.changeType = data.cellarmembercardbalancechangerecorddb.changeType
                this.dataForm.changeDesc = data.cellarmembercardbalancechangerecorddb.changeDesc
                this.dataForm.orderId = data.cellarmembercardbalancechangerecorddb.orderId
                this.dataForm.orderNo = data.cellarmembercardbalancechangerecorddb.orderNo
                this.dataForm.state = data.cellarmembercardbalancechangerecorddb.state
                this.dataForm.createTime = data.cellarmembercardbalancechangerecorddb.createTime
                this.dataForm.methodPayment = data.cellarmembercardbalancechangerecorddb.methodPayment
                this.dataForm.paymentTime = data.cellarmembercardbalancechangerecorddb.paymentTime
                this.dataForm.recordStatus = data.cellarmembercardbalancechangerecorddb.recordStatus
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
              url: this.$http.adornUrl(`/cellar/cellarmembercardbalancechangerecorddb/${!this.dataForm.memberCardBalanceChangeRecordId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberCardBalanceChangeRecordId': this.dataForm.memberCardBalanceChangeRecordId || undefined,
                'memberId': this.dataForm.memberId,
                'changeCardBalance': this.dataForm.changeCardBalance,
                'beforeCardBalance': this.dataForm.beforeCardBalance,
                'afterCardBalance': this.dataForm.afterCardBalance,
                'changeType': this.dataForm.changeType,
                'changeDesc': this.dataForm.changeDesc,
                'orderId': this.dataForm.orderId,
                'orderNo': this.dataForm.orderNo,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'methodPayment': this.dataForm.methodPayment,
                'paymentTime': this.dataForm.paymentTime,
                'recordStatus': this.dataForm.recordStatus
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
