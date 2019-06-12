<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="订单id" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder="订单id"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createtime">
      <el-input v-model="dataForm.createtime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="店铺id" prop="storeId">
      <el-input v-model="dataForm.storeId" placeholder="店铺id"></el-input>
    </el-form-item>
    <el-form-item label="商品id" prop="commodityId">
      <el-input v-model="dataForm.commodityId" placeholder="商品id"></el-input>
    </el-form-item>
    <el-form-item label="商品数量" prop="number">
      <el-input v-model="dataForm.number" placeholder="商品数量"></el-input>
    </el-form-item>
    <el-form-item label="商品金额" prop="amountGoods">
      <el-input v-model="dataForm.amountGoods" placeholder="商品金额"></el-input>
    </el-form-item>
    <el-form-item label="商品总金额" prop="totalAmountGoods">
      <el-input v-model="dataForm.totalAmountGoods" placeholder="商品总金额"></el-input>
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
          orderDetailsId: 0,
          orderId: '',
          state: '',
          createtime: '',
          storeId: '',
          commodityId: '',
          number: '',
          amountGoods: '',
          totalAmountGoods: ''
        },
        dataRule: {
          orderId: [
            { required: true, message: '订单id不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createtime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          storeId: [
            { required: true, message: '店铺id不能为空', trigger: 'blur' }
          ],
          commodityId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          number: [
            { required: true, message: '商品数量不能为空', trigger: 'blur' }
          ],
          amountGoods: [
            { required: true, message: '商品金额不能为空', trigger: 'blur' }
          ],
          totalAmountGoods: [
            { required: true, message: '商品总金额不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.orderDetailsId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.orderDetailsId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarorderdetailsdb/info/${this.dataForm.orderDetailsId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderId = data.cellarorderdetailsdb.orderId
                this.dataForm.state = data.cellarorderdetailsdb.state
                this.dataForm.createtime = data.cellarorderdetailsdb.createtime
                this.dataForm.storeId = data.cellarorderdetailsdb.storeId
                this.dataForm.commodityId = data.cellarorderdetailsdb.commodityId
                this.dataForm.number = data.cellarorderdetailsdb.number
                this.dataForm.amountGoods = data.cellarorderdetailsdb.amountGoods
                this.dataForm.totalAmountGoods = data.cellarorderdetailsdb.totalAmountGoods
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
              url: this.$http.adornUrl(`/cellar/cellarorderdetailsdb/${!this.dataForm.orderDetailsId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'orderDetailsId': this.dataForm.orderDetailsId || undefined,
                'orderId': this.dataForm.orderId,
                'state': this.dataForm.state,
                'createtime': this.dataForm.createtime,
                'storeId': this.dataForm.storeId,
                'commodityId': this.dataForm.commodityId,
                'number': this.dataForm.number,
                'amountGoods': this.dataForm.amountGoods,
                'totalAmountGoods': this.dataForm.totalAmountGoods
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
