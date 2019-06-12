<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="cellarMemberCardDb">
    <!--<el-form-item label="状态" prop="state">-->
      <!--<el-input v-model="dataForm.state" placeholder="状态"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="创建时间" prop="createTime">-->
      <!--<el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>-->
    <!--</el-form-item>-->
    <el-form-item label="储值卡金额" prop="cardBalance">
      <el-input v-model="dataForm.cardBalance" placeholder="储值卡金额"></el-input>
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
          memberCardId: 0,
          state: '',
          createTime: '',
          cardBalance: ''
        },
        dataRule: {
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          cardBalance: [
            { required: true, message: '储值卡金额不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberCardId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberCardId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmembercarddb/info/${this.dataForm.memberCardId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.state = data.cellarMemberCardDb.state
                this.dataForm.createTime = data.cellarMemberCardDb.createTime
                this.dataForm.cardBalance = data.cellarMemberCardDb.cardBalance
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
              url: this.$http.adornUrl(`/cellar/cellarmembercarddb/${!this.dataForm.memberCardId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberCardId': this.dataForm.memberCardId || undefined,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'cardBalance': this.dataForm.cardBalance
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
