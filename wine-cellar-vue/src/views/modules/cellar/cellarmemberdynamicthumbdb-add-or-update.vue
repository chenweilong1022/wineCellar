<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="会员动态id" prop="memberDynamicId">
      <el-input v-model="dataForm.memberDynamicId" placeholder="会员动态id"></el-input>
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
          memberDynamicThumbId: 0,
          memberId: '',
          memberDynamicId: '',
          state: '',
          createTime: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          memberDynamicId: [
            { required: true, message: '会员动态id不能为空', trigger: 'blur' }
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
        this.dataForm.memberDynamicThumbId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberDynamicThumbId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmemberdynamicthumbdb/info/${this.dataForm.memberDynamicThumbId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.cellarmemberdynamicthumbdb.memberId
                this.dataForm.memberDynamicId = data.cellarmemberdynamicthumbdb.memberDynamicId
                this.dataForm.state = data.cellarmemberdynamicthumbdb.state
                this.dataForm.createTime = data.cellarmemberdynamicthumbdb.createTime
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
              url: this.$http.adornUrl(`/cellar/cellarmemberdynamicthumbdb/${!this.dataForm.memberDynamicThumbId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberDynamicThumbId': this.dataForm.memberDynamicThumbId || undefined,
                'memberId': this.dataForm.memberId,
                'memberDynamicId': this.dataForm.memberDynamicId,
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
