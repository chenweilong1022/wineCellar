<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="优惠券id" prop="couponId">
      <el-input v-model="dataForm.couponId" placeholder="优惠券id"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="使用状态" prop="usingState">
      <el-input v-model="dataForm.usingState" placeholder="使用状态"></el-input>
    </el-form-item>
    <el-form-item label="使用时间" prop="useTime">
      <el-input v-model="dataForm.useTime" placeholder="使用时间"></el-input>
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
          memberCouponId: 0,
          memberId: '',
          couponId: '',
          state: '',
          createTime: '',
          usingState: '',
          useTime: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          couponId: [
            { required: true, message: '优惠券id不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          usingState: [
            { required: true, message: '使用状态不能为空', trigger: 'blur' }
          ],
          useTime: [
            { required: true, message: '使用时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberCouponId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberCouponId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmembercoupondb/info/${this.dataForm.memberCouponId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.cellarmembercoupondb.memberId
                this.dataForm.couponId = data.cellarmembercoupondb.couponId
                this.dataForm.state = data.cellarmembercoupondb.state
                this.dataForm.createTime = data.cellarmembercoupondb.createTime
                this.dataForm.usingState = data.cellarmembercoupondb.usingState
                this.dataForm.useTime = data.cellarmembercoupondb.useTime
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
              url: this.$http.adornUrl(`/cellar/cellarmembercoupondb/${!this.dataForm.memberCouponId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberCouponId': this.dataForm.memberCouponId || undefined,
                'memberId': this.dataForm.memberId,
                'couponId': this.dataForm.couponId,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'usingState': this.dataForm.usingState,
                'useTime': this.dataForm.useTime
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
