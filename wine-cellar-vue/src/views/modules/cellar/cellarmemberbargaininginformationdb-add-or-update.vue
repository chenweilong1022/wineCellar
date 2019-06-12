<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="砍价活动id" prop="bargainingActivityId">
      <el-input v-model="dataForm.bargainingActivityId" placeholder="砍价活动id"></el-input>
    </el-form-item>
    <el-form-item label="商品id" prop="commodityId">
      <el-input v-model="dataForm.commodityId" placeholder="商品id"></el-input>
    </el-form-item>
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="被砍次数" prop="cuttingDownNumber">
      <el-input v-model="dataForm.cuttingDownNumber" placeholder="被砍次数"></el-input>
    </el-form-item>
    <el-form-item label="最终价格" prop="finalPrice">
      <el-input v-model="dataForm.finalPrice" placeholder="最终价格"></el-input>
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
          memberBargainingInformationId: 0,
          bargainingActivityId: '',
          commodityId: '',
          memberId: '',
          cuttingDownNumber: '',
          finalPrice: '',
          state: '',
          createTime: ''
        },
        dataRule: {
          bargainingActivityId: [
            { required: true, message: '砍价活动id不能为空', trigger: 'blur' }
          ],
          commodityId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          cuttingDownNumber: [
            { required: true, message: '被砍次数不能为空', trigger: 'blur' }
          ],
          finalPrice: [
            { required: true, message: '最终价格不能为空', trigger: 'blur' }
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
        this.dataForm.memberBargainingInformationId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberBargainingInformationId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmemberbargaininginformationdb/info/${this.dataForm.memberBargainingInformationId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.bargainingActivityId = data.cellarmemberbargaininginformationdb.bargainingActivityId
                this.dataForm.commodityId = data.cellarmemberbargaininginformationdb.commodityId
                this.dataForm.memberId = data.cellarmemberbargaininginformationdb.memberId
                this.dataForm.cuttingDownNumber = data.cellarmemberbargaininginformationdb.cuttingDownNumber
                this.dataForm.finalPrice = data.cellarmemberbargaininginformationdb.finalPrice
                this.dataForm.state = data.cellarmemberbargaininginformationdb.state
                this.dataForm.createTime = data.cellarmemberbargaininginformationdb.createTime
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
              url: this.$http.adornUrl(`/cellar/cellarmemberbargaininginformationdb/${!this.dataForm.memberBargainingInformationId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberBargainingInformationId': this.dataForm.memberBargainingInformationId || undefined,
                'bargainingActivityId': this.dataForm.bargainingActivityId,
                'commodityId': this.dataForm.commodityId,
                'memberId': this.dataForm.memberId,
                'cuttingDownNumber': this.dataForm.cuttingDownNumber,
                'finalPrice': this.dataForm.finalPrice,
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
