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
    <el-form-item label="店铺id" prop="storeId">
      <el-input v-model="dataForm.storeId" placeholder="店铺id"></el-input>
    </el-form-item>
    <el-form-item label="店铺名称" prop="storeName">
      <el-input v-model="dataForm.storeName" placeholder="店铺名称"></el-input>
    </el-form-item>
    <el-form-item label="商品id" prop="commodityId">
      <el-input v-model="dataForm.commodityId" placeholder="商品id"></el-input>
    </el-form-item>
    <el-form-item label="商品名称" prop="commodityName">
      <el-input v-model="dataForm.commodityName" placeholder="商品名称"></el-input>
    </el-form-item>
    <el-form-item label="商品图片" prop="picture">
      <el-input v-model="dataForm.picture" placeholder="商品图片"></el-input>
    </el-form-item>
    <el-form-item label="商品价格" prop="prices">
      <el-input v-model="dataForm.prices" placeholder="商品价格"></el-input>
    </el-form-item>
    <el-form-item label="商品数量" prop="number">
      <el-input v-model="dataForm.number" placeholder="商品数量"></el-input>
    </el-form-item>
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="类型" prop="type">
      <el-input v-model="dataForm.type" placeholder="类型"></el-input>
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
          cartId: 0,
          state: '',
          createTime: '',
          storeId: '',
          storeName: '',
          commodityId: '',
          commodityName: '',
          picture: '',
          prices: '',
          number: '',
          memberId: '',
          type: ''
        },
        dataRule: {
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          storeId: [
            { required: true, message: '店铺id不能为空', trigger: 'blur' }
          ],
          storeName: [
            { required: true, message: '店铺名称不能为空', trigger: 'blur' }
          ],
          commodityId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          commodityName: [
            { required: true, message: '商品名称不能为空', trigger: 'blur' }
          ],
          picture: [
            { required: true, message: '商品图片不能为空', trigger: 'blur' }
          ],
          prices: [
            { required: true, message: '商品价格不能为空', trigger: 'blur' }
          ],
          number: [
            { required: true, message: '商品数量不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '类型不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.cartId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.cartId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarcartdb/info/${this.dataForm.cartId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.state = data.cellarcartdb.state
                this.dataForm.createTime = data.cellarcartdb.createTime
                this.dataForm.storeId = data.cellarcartdb.storeId
                this.dataForm.storeName = data.cellarcartdb.storeName
                this.dataForm.commodityId = data.cellarcartdb.commodityId
                this.dataForm.commodityName = data.cellarcartdb.commodityName
                this.dataForm.picture = data.cellarcartdb.picture
                this.dataForm.prices = data.cellarcartdb.prices
                this.dataForm.number = data.cellarcartdb.number
                this.dataForm.memberId = data.cellarcartdb.memberId
                this.dataForm.type = data.cellarcartdb.type
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
              url: this.$http.adornUrl(`/cellar/cellarcartdb/${!this.dataForm.cartId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'cartId': this.dataForm.cartId || undefined,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'storeId': this.dataForm.storeId,
                'storeName': this.dataForm.storeName,
                'commodityId': this.dataForm.commodityId,
                'commodityName': this.dataForm.commodityName,
                'picture': this.dataForm.picture,
                'prices': this.dataForm.prices,
                'number': this.dataForm.number,
                'memberId': this.dataForm.memberId,
                'type': this.dataForm.type
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
