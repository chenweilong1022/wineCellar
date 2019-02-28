<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类别名称" prop="categoryName">
      <el-input v-model="dataForm.categoryName" placeholder="类别名称"></el-input>
    </el-form-item>
    <el-form-item label="上级类别" prop="supCategoryId">
      <el-cascader
        placeholder="上级类别"
        :options="options"
        :props="defaultParams"
        v-model="selectedOptions3"
        expand-trigger="hover"
        filterable
        change-on-select
      ></el-cascader>
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
        selectedOptions3: [0],
        defaultParams: {
          value: 'categoryId',
          label: 'categoryName',
          children: 'cellarCategoryDbEntities'
        },
        options: [],
        visible: false,
        dataForm: {
          categoryId: 0,
          categoryName: '',
          supCategoryId: '',
          state: '',
          createTime: '',
          storeId: '',
          level: ''
        },
        dataRule: {
          categoryName: [
            { required: true, message: '类别名称不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.categoryId = id || 0
        this.listOneLevel()
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.categoryId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarcategorydb/info/${this.dataForm.categoryId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.categoryName = data.cellarCategoryDb.categoryName
                this.dataForm.supCategoryId = data.cellarCategoryDb.supCategoryId
                this.dataForm.state = data.cellarCategoryDb.state
                this.dataForm.createTime = data.cellarCategoryDb.createTime
                this.dataForm.storeId = data.cellarCategoryDb.storeId
                this.dataForm.level = data.cellarCategoryDb.level
                this.selectedOptions3 = data.cellarCategoryDb.supCategoryIdList
              }
            })
          }
        })
      },
      listOneLevel () {
        this.$http({
          url: this.$http.adornUrl(`/cellar/cellarcategorydb/listOneLevel`),
          method: 'post',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.options = data.data
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarcategorydb/${!this.dataForm.categoryId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'categoryId': this.dataForm.categoryId || undefined,
                'categoryName': this.dataForm.categoryName,
                'supCategoryId': this.dataForm.supCategoryId,
                'supCategoryIdList': this.selectedOptions3,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'storeId': this.dataForm.storeId,
                'level': this.dataForm.level
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
