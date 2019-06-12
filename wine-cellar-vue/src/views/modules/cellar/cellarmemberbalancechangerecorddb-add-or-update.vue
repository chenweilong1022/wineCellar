<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="变动金额" prop="changeBalance">
      <el-input v-model="dataForm.changeBalance" placeholder="变动金额"></el-input>
    </el-form-item>
    <el-form-item label="变动前金额" prop="beforeBalance">
      <el-input v-model="dataForm.beforeBalance" placeholder="变动前金额"></el-input>
    </el-form-item>
    <el-form-item label="变动后金额" prop="afterBalance">
      <el-input v-model="dataForm.afterBalance" placeholder="变动后金额"></el-input>
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
          memberBalanceChangeRecordId: 0,
          memberId: '',
          changeBalance: '',
          beforeBalance: '',
          afterBalance: '',
          changeType: '',
          changeDesc: '',
          orderId: '',
          orderNo: '',
          state: '',
          createTime: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          changeBalance: [
            { required: true, message: '变动金额不能为空', trigger: 'blur' }
          ],
          beforeBalance: [
            { required: true, message: '变动前金额不能为空', trigger: 'blur' }
          ],
          afterBalance: [
            { required: true, message: '变动后金额不能为空', trigger: 'blur' }
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
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberBalanceChangeRecordId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberBalanceChangeRecordId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmemberbalancechangerecorddb/info/${this.dataForm.memberBalanceChangeRecordId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.cellarmemberbalancechangerecorddb.memberId
                this.dataForm.changeBalance = data.cellarmemberbalancechangerecorddb.changeBalance
                this.dataForm.beforeBalance = data.cellarmemberbalancechangerecorddb.beforeBalance
                this.dataForm.afterBalance = data.cellarmemberbalancechangerecorddb.afterBalance
                this.dataForm.changeType = data.cellarmemberbalancechangerecorddb.changeType
                this.dataForm.changeDesc = data.cellarmemberbalancechangerecorddb.changeDesc
                this.dataForm.orderId = data.cellarmemberbalancechangerecorddb.orderId
                this.dataForm.orderNo = data.cellarmemberbalancechangerecorddb.orderNo
                this.dataForm.state = data.cellarmemberbalancechangerecorddb.state
                this.dataForm.createTime = data.cellarmemberbalancechangerecorddb.createTime
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
              url: this.$http.adornUrl(`/cellar/cellarmemberbalancechangerecorddb/${!this.dataForm.memberBalanceChangeRecordId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberBalanceChangeRecordId': this.dataForm.memberBalanceChangeRecordId || undefined,
                'memberId': this.dataForm.memberId,
                'changeBalance': this.dataForm.changeBalance,
                'beforeBalance': this.dataForm.beforeBalance,
                'afterBalance': this.dataForm.afterBalance,
                'changeType': this.dataForm.changeType,
                'changeDesc': this.dataForm.changeDesc,
                'orderId': this.dataForm.orderId,
                'orderNo': this.dataForm.orderNo,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime
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
