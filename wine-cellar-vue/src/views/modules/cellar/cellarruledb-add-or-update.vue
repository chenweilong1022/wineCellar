<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="规则描述" prop="ruleType">
      <el-select clearable v-model="dataForm.ruleType" placeholder="规则类型">
        <el-option
          v-for="item in ruletypelist"
          :key="item.key"
          :label="item.value"
          :value="item.key">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="规则描述" prop="ruleDescriptionStr">
      <ueditor ref="ueditor"></ueditor>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import ueditor from '@/views/demo/ueditor'
  export default {
    components: {
      ueditor
    },
    data () {
      return {
        ruletypelist: [],
        visible: false,
        dataForm: {
          ruleId: 0,
          ruleType: '',
          ruleDescription: '',
          ruleDescriptionStr: '',
          createTime: '',
          state: ''
        },
        dataRule: {
          ruleType: [
            { required: true, message: '规则类型不能为空', trigger: 'blur' }
          ],
          ruleDescription: [
            { required: true, message: '规则描述不能为空', trigger: 'blur' }
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
      ruleTypeList () {
        this.$http({
          url: this.$http.adornUrl('/sys/constants/ruletypelist'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.ruletypelist = data.data
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      init (id) {
        this.ruleTypeList()
        this.dataForm.ruleId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.ruleId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarruledb/info/${this.dataForm.ruleId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.ruleType = data.cellarRuleDb.ruleType
                this.dataForm.ruleDescriptionStr = data.cellarRuleDb.ruleDescriptionStr
                this.$refs.ueditor.init(this.dataForm.ruleDescriptionStr)
                this.dataForm.createTime = data.cellarRuleDb.createTime
                this.dataForm.state = data.cellarRuleDb.state
              }
            })
          } else {
            this.$refs.ueditor.init('')
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarruledb/${!this.dataForm.ruleId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'ruleId': this.dataForm.ruleId || undefined,
                'ruleType': this.dataForm.ruleType,
                'ruleDescription': this.dataForm.ruleDescription,
                'ruleDescriptionStr': this.$refs.ueditor.getContent(),
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
