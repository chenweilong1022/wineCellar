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
    <el-form-item label="审核状态" prop="reviewStatus">
      <el-input v-model="dataForm.reviewStatus" placeholder="审核状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="分类活动id" prop="categoryActivityId">
      <el-input v-model="dataForm.categoryActivityId" placeholder="分类活动id"></el-input>
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
          categoryActivityCommodityReviewId: 0,
          commodityId: '',
          state: '',
          reviewStatus: '',
          createTime: '',
          categoryActivityId: ''
        },
        dataRule: {
          commodityId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          reviewStatus: [
            { required: true, message: '审核状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          categoryActivityId: [
            { required: true, message: '分类活动id不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.categoryActivityCommodityReviewId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.categoryActivityCommodityReviewId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarcategoryactivitycommodityreviewdb/info/${this.dataForm.categoryActivityCommodityReviewId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.commodityId = data.cellarcategoryactivitycommodityreviewdb.commodityId
                this.dataForm.state = data.cellarcategoryactivitycommodityreviewdb.state
                this.dataForm.reviewStatus = data.cellarcategoryactivitycommodityreviewdb.reviewStatus
                this.dataForm.createTime = data.cellarcategoryactivitycommodityreviewdb.createTime
                this.dataForm.categoryActivityId = data.cellarcategoryactivitycommodityreviewdb.categoryActivityId
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
              url: this.$http.adornUrl(`/cellar/cellarcategoryactivitycommodityreviewdb/${!this.dataForm.categoryActivityCommodityReviewId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'categoryActivityCommodityReviewId': this.dataForm.categoryActivityCommodityReviewId || undefined,
                'commodityId': this.dataForm.commodityId,
                'state': this.dataForm.state,
                'reviewStatus': this.dataForm.reviewStatus,
                'createTime': this.dataForm.createTime,
                'categoryActivityId': this.dataForm.categoryActivityId
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
