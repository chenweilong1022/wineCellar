<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="商品id" prop="commodityId">
      <el-input v-model="dataForm.commodityId" placeholder="商品id"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="审核状态" prop="reviewStatus">
      <el-input v-model="dataForm.reviewStatus" placeholder="审核状态"></el-input>
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
          handpickCommodityReviewId: 0,
          commodityId: '',
          state: '',
          createTime: '',
          reviewStatus: ''
        },
        dataRule: {
          commodityId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          reviewStatus: [
            { required: true, message: '审核状态不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.handpickCommodityReviewId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.handpickCommodityReviewId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarhandpickcommodityreviewdb/info/${this.dataForm.handpickCommodityReviewId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.commodityId = data.cellarhandpickcommodityreviewdb.commodityId
                this.dataForm.state = data.cellarhandpickcommodityreviewdb.state
                this.dataForm.createTime = data.cellarhandpickcommodityreviewdb.createTime
                this.dataForm.reviewStatus = data.cellarhandpickcommodityreviewdb.reviewStatus
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
              url: this.$http.adornUrl(`/cellar/cellarhandpickcommodityreviewdb/${!this.dataForm.handpickCommodityReviewId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'handpickCommodityReviewId': this.dataForm.handpickCommodityReviewId || undefined,
                'commodityId': this.dataForm.commodityId,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'reviewStatus': this.dataForm.reviewStatus
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
