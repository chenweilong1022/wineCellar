<template>
  <div class="mod-config">
    <el-form :model="dataForm" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="运费配置" prop="freight">
        <el-input v-model="dataForm.freight" placeholder="运费配置"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        dataForm: {
          configId: 1,
          freight: ''
        }
      }
    },
    activated () {
      this.init()
    },
    methods: {
      init () {
        // this.dataForm.configId = id || 0
        // this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.configId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarconfigdb/info/${this.dataForm.configId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.freight = data.cellarConfigDb.freight
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
              url: this.$http.adornUrl(`/cellar/cellarconfigdb/${!this.dataForm.configId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'configId': this.dataForm.configId || undefined,
                'freight': this.dataForm.freight
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
      // 获取数据列表
      // getDataList () {
      //   this.dataListLoading = true
      //   this.$http({
      //     url: this.$http.adornUrl('/cellar/cellarconfigdb/list'),
      //     method: 'get',
      //     params: this.$http.adornParams({
      //       'page': this.pageIndex,
      //       'limit': this.pageSize,
      //       'key': this.dataForm.key
      //     })
      //   }).then(({data}) => {
      //     if (data && data.code === 0) {
      //       this.dataList = data.page.list
      //       this.totalPage = data.page.totalCount
      //     } else {
      //       this.dataList = []
      //       this.totalPage = 0
      //     }
      //     this.dataListLoading = false
      //   })
      // },
      // 每页数
      // sizeChangeHandle (val) {
      //   this.pageSize = val
      //   this.pageIndex = 1
      //   this.getDataList()
      // },
      // // 当前页
      // currentChangeHandle (val) {
      //   this.pageIndex = val
      //   this.getDataList()
      // },
      // // 多选
      // selectionChangeHandle (val) {
      //   this.dataListSelections = val
      // },
      // // 新增 / 修改
      // addOrUpdateHandle (id) {
      //   this.addOrUpdateVisible = true
      //   this.$nextTick(() => {
      //     this.$refs.addOrUpdate.init(id)
      //   })
      // },
      // // 删除
      // deleteHandle (id) {
      //   var ids = id ? [id] : this.dataListSelections.map(item => {
      //     return item.configId
      //   })
      //   this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
      //     confirmButtonText: '确定',
      //     cancelButtonText: '取消',
      //     type: 'warning'
      //   }).then(() => {
      //     this.$http({
      //       url: this.$http.adornUrl('/cellar/cellarconfigdb/delete'),
      //       method: 'post',
      //       data: this.$http.adornData(ids, false)
      //     }).then(({data}) => {
      //       if (data && data.code === 0) {
      //         this.$message({
      //           message: '操作成功',
      //           type: 'success',
      //           duration: 1500,
      //           onClose: () => {
      //             this.getDataList()
      //           }
      //         })
      //       } else {
      //         this.$message.error(data.msg)
      //       }
      //     })
      //   })
      // }
    }
  }
</script>
