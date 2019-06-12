<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="收藏类型" prop="collectionType">
      <el-input v-model="dataForm.collectionType" placeholder="收藏类型"></el-input>
    </el-form-item>
    <el-form-item label="商品id" prop="commodityId">
      <el-input v-model="dataForm.commodityId" placeholder="商品id"></el-input>
    </el-form-item>
    <el-form-item label="店铺id" prop="storeId">
      <el-input v-model="dataForm.storeId" placeholder="店铺id"></el-input>
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
          memberCollectionId: 0,
          memberId: '',
          state: '',
          createTime: '',
          collectionType: '',
          commodityId: '',
          storeId: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          collectionType: [
            { required: true, message: '收藏类型不能为空', trigger: 'blur' }
          ],
          commodityId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          storeId: [
            { required: true, message: '店铺id不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberCollectionId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberCollectionId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmembercollectiondb/info/${this.dataForm.memberCollectionId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.cellarmembercollectiondb.memberId
                this.dataForm.state = data.cellarmembercollectiondb.state
                this.dataForm.createTime = data.cellarmembercollectiondb.createTime
                this.dataForm.collectionType = data.cellarmembercollectiondb.collectionType
                this.dataForm.commodityId = data.cellarmembercollectiondb.commodityId
                this.dataForm.storeId = data.cellarmembercollectiondb.storeId
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
              url: this.$http.adornUrl(`/cellar/cellarmembercollectiondb/${!this.dataForm.memberCollectionId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberCollectionId': this.dataForm.memberCollectionId || undefined,
                'memberId': this.dataForm.memberId,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'collectionType': this.dataForm.collectionType,
                'commodityId': this.dataForm.commodityId,
                'storeId': this.dataForm.storeId
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
