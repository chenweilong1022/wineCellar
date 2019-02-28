<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="父级节点" prop="pid">
      <el-input v-model="dataForm.pid" placeholder="父级节点"></el-input>
    </el-form-item>
    <el-form-item label="地域路由" prop="treePath">
      <el-input v-model="dataForm.treePath" placeholder="地域路由"></el-input>
    </el-form-item>
    <el-form-item label="地域编码" prop="areaCode">
      <el-input v-model="dataForm.areaCode" placeholder="地域编码"></el-input>
    </el-form-item>
    <el-form-item label="地域名称" prop="areaName">
      <el-input v-model="dataForm.areaName" placeholder="地域名称"></el-input>
    </el-form-item>
    <el-form-item label="地区名称拼音" prop="areaPinyin">
      <el-input v-model="dataForm.areaPinyin" placeholder="地区名称拼音"></el-input>
    </el-form-item>
    <el-form-item label="银行地域支付编码" prop="bankCode">
      <el-input v-model="dataForm.bankCode" placeholder="银行地域支付编码"></el-input>
    </el-form-item>
    <el-form-item label="地区邮编" prop="zipcode">
      <el-input v-model="dataForm.zipcode" placeholder="地区邮编"></el-input>
    </el-form-item>
    <el-form-item label="电话区号" prop="telCode">
      <el-input v-model="dataForm.telCode" placeholder="电话区号"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
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
          id: 0,
          pid: '',
          treePath: '',
          areaCode: '',
          areaName: '',
          areaPinyin: '',
          bankCode: '',
          zipcode: '',
          telCode: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          pid: [
            { required: true, message: '父级节点不能为空', trigger: 'blur' }
          ],
          treePath: [
            { required: true, message: '地域路由不能为空', trigger: 'blur' }
          ],
          areaCode: [
            { required: true, message: '地域编码不能为空', trigger: 'blur' }
          ],
          areaName: [
            { required: true, message: '地域名称不能为空', trigger: 'blur' }
          ],
          areaPinyin: [
            { required: true, message: '地区名称拼音不能为空', trigger: 'blur' }
          ],
          bankCode: [
            { required: true, message: '银行地域支付编码不能为空', trigger: 'blur' }
          ],
          zipcode: [
            { required: true, message: '地区邮编不能为空', trigger: 'blur' }
          ],
          telCode: [
            { required: true, message: '电话区号不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/sysarea/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.pid = data.sysarea.pid
                this.dataForm.treePath = data.sysarea.treePath
                this.dataForm.areaCode = data.sysarea.areaCode
                this.dataForm.areaName = data.sysarea.areaName
                this.dataForm.areaPinyin = data.sysarea.areaPinyin
                this.dataForm.bankCode = data.sysarea.bankCode
                this.dataForm.zipcode = data.sysarea.zipcode
                this.dataForm.telCode = data.sysarea.telCode
                this.dataForm.createTime = data.sysarea.createTime
                this.dataForm.updateTime = data.sysarea.updateTime
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
              url: this.$http.adornUrl(`/cellar/sysarea/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'pid': this.dataForm.pid,
                'treePath': this.dataForm.treePath,
                'areaCode': this.dataForm.areaCode,
                'areaName': this.dataForm.areaName,
                'areaPinyin': this.dataForm.areaPinyin,
                'bankCode': this.dataForm.bankCode,
                'zipcode': this.dataForm.zipcode,
                'telCode': this.dataForm.telCode,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
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
