<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="变动积分" prop="changeIntegral">
      <el-input v-model="dataForm.changeIntegral" placeholder="变动积分"></el-input>
    </el-form-item>
    <el-form-item label="变动前积分" prop="beforeChange">
      <el-input v-model="dataForm.beforeChange" placeholder="变动前积分"></el-input>
    </el-form-item>
    <el-form-item label="变动后积分" prop="afterIntegral">
      <el-input v-model="dataForm.afterIntegral" placeholder="变动后积分"></el-input>
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
          memberIntegralChangeRecordId: 0,
          memberId: '',
          changeIntegral: '',
          beforeChange: '',
          afterIntegral: '',
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
          changeIntegral: [
            { required: true, message: '变动积分不能为空', trigger: 'blur' }
          ],
          beforeChange: [
            { required: true, message: '变动前积分不能为空', trigger: 'blur' }
          ],
          afterIntegral: [
            { required: true, message: '变动后积分不能为空', trigger: 'blur' }
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
        this.dataForm.memberIntegralChangeRecordId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberIntegralChangeRecordId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmemberintegralchangerecorddb/info/${this.dataForm.memberIntegralChangeRecordId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.cellarmemberintegralchangerecorddb.memberId
                this.dataForm.changeIntegral = data.cellarmemberintegralchangerecorddb.changeIntegral
                this.dataForm.beforeChange = data.cellarmemberintegralchangerecorddb.beforeChange
                this.dataForm.afterIntegral = data.cellarmemberintegralchangerecorddb.afterIntegral
                this.dataForm.changeType = data.cellarmemberintegralchangerecorddb.changeType
                this.dataForm.changeDesc = data.cellarmemberintegralchangerecorddb.changeDesc
                this.dataForm.orderId = data.cellarmemberintegralchangerecorddb.orderId
                this.dataForm.orderNo = data.cellarmemberintegralchangerecorddb.orderNo
                this.dataForm.state = data.cellarmemberintegralchangerecorddb.state
                this.dataForm.createTime = data.cellarmemberintegralchangerecorddb.createTime
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
              url: this.$http.adornUrl(`/cellar/cellarmemberintegralchangerecorddb/${!this.dataForm.memberIntegralChangeRecordId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberIntegralChangeRecordId': this.dataForm.memberIntegralChangeRecordId || undefined,
                'memberId': this.dataForm.memberId,
                'changeIntegral': this.dataForm.changeIntegral,
                'beforeChange': this.dataForm.beforeChange,
                'afterIntegral': this.dataForm.afterIntegral,
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
