<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员砍价信息id" prop="memberBargainingInformationId">
      <el-input v-model="dataForm.memberBargainingInformationId" placeholder="会员砍价信息id"></el-input>
    </el-form-item>
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="砍价金额" prop="cutPrice">
      <el-input v-model="dataForm.cutPrice" placeholder="砍价金额"></el-input>
    </el-form-item>
    <el-form-item label="砍价之前金额" prop="beforeCutPrice">
      <el-input v-model="dataForm.beforeCutPrice" placeholder="砍价之前金额"></el-input>
    </el-form-item>
    <el-form-item label="砍价之后金额" prop="afterCutPrice">
      <el-input v-model="dataForm.afterCutPrice" placeholder="砍价之后金额"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
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
          memberBargainingRecordId: 0,
          memberBargainingInformationId: '',
          memberId: '',
          cutPrice: '',
          beforeCutPrice: '',
          afterCutPrice: '',
          createTime: '',
          state: ''
        },
        dataRule: {
          memberBargainingInformationId: [
            { required: true, message: '会员砍价信息id不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          cutPrice: [
            { required: true, message: '砍价金额不能为空', trigger: 'blur' }
          ],
          beforeCutPrice: [
            { required: true, message: '砍价之前金额不能为空', trigger: 'blur' }
          ],
          afterCutPrice: [
            { required: true, message: '砍价之后金额不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberBargainingRecordId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberBargainingRecordId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmemberbargainingrecorddb/info/${this.dataForm.memberBargainingRecordId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberBargainingInformationId = data.cellarmemberbargainingrecorddb.memberBargainingInformationId
                this.dataForm.memberId = data.cellarmemberbargainingrecorddb.memberId
                this.dataForm.cutPrice = data.cellarmemberbargainingrecorddb.cutPrice
                this.dataForm.beforeCutPrice = data.cellarmemberbargainingrecorddb.beforeCutPrice
                this.dataForm.afterCutPrice = data.cellarmemberbargainingrecorddb.afterCutPrice
                this.dataForm.createTime = data.cellarmemberbargainingrecorddb.createTime
                this.dataForm.state = data.cellarmemberbargainingrecorddb.state
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
              url: this.$http.adornUrl(`/cellar/cellarmemberbargainingrecorddb/${!this.dataForm.memberBargainingRecordId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberBargainingRecordId': this.dataForm.memberBargainingRecordId || undefined,
                'memberBargainingInformationId': this.dataForm.memberBargainingInformationId,
                'memberId': this.dataForm.memberId,
                'cutPrice': this.dataForm.cutPrice,
                'beforeCutPrice': this.dataForm.beforeCutPrice,
                'afterCutPrice': this.dataForm.afterCutPrice,
                'createTime': this.dataForm.createTime,
                'state': this.dataForm.state
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
