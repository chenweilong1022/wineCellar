<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="商品名称" prop="commodityName">
      <el-input v-model="dataForm.commodityName" placeholder="商品名称"></el-input>
    </el-form-item>

    <el-form-item>
      <el-select clearable v-model="dataForm.categoryActivityId" placeholder="订单类型">
        <el-option
          v-for="item in options"
          :key="item.categoryActivityId"
          :label="item.activityTitle"
          :value="item.categoryActivityId">
        </el-option>
      </el-select>
    </el-form-item>
    <!--<el-form-item label="图片" prop="picture">-->
      <!--<el-upload-->
        <!--class="avatar-uploader"-->
        <!--:action="url"-->
        <!--:show-file-list="false"-->
        <!--naem="file"-->
        <!--:on-success="handleAvatarSuccessPicture">-->
        <!--<img v-if="dataForm.picture" :src="dataForm.picture" class="avatar">-->
        <!--<i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
      <!--</el-upload>-->
    <!--</el-form-item>-->
      <!--<el-form-item>-->
        <!--<el-upload-->
          <!--:action="url"-->
          <!--:on-preview="handlePictureCardPreview"-->
          <!--:on-remove="handleRemove"-->
          <!--:on-success="handleSuccess"-->
          <!--:file-list="editFiles"-->
          <!--list-type="picture-card">-->
          <!--<i class="el-icon-plus"></i>-->
        <!--</el-upload>-->
        <!--<el-dialog :visible.sync="dialogVisible">-->
          <!--<img width="100%" :src="dialogImageUrl" alt="">-->
        <!--</el-dialog>-->
      <!--</el-form-item>-->
    <!--<el-form-item label="分类id" prop="categoryId">-->
      <!--<el-cascader-->
        <!--placeholder="上级类别"-->
        <!--:options="options"-->
        <!--:props="defaultParams"-->
        <!--v-model="selectedOptions3"-->
        <!--expand-trigger="hover"-->
        <!--filterable-->
        <!--change-on-select-->
      <!--&gt;</el-cascader>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="标签" prop="label">-->
      <!--<el-tag-->
        <!--:key="tag"-->
        <!--v-for="tag in dynamicTags"-->
        <!--closable-->
        <!--:disable-transitions="false"-->
        <!--@close="handleClose(tag)">-->
        <!--{{tag}}-->
      <!--</el-tag>-->
      <!--<el-input-->
        <!--class="input-new-tag"-->
        <!--v-if="inputVisible"-->
        <!--v-model="inputValue"-->
        <!--ref="saveTagInput"-->
        <!--size="small"-->
        <!--@keyup.enter.native="handleInputConfirm"-->
        <!--@blur="handleInputConfirm">-->
      <!--</el-input>-->
      <!--<el-button v-else class="button-new-tag" size="small" @click="showInput">+ 新增标签</el-button>-->
  <!--</el-form-item>-->
    <!--<el-form-item label="库存量" prop="inventory">-->
      <!--<el-input v-model="dataForm.inventory" placeholder="库存量"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="商品规格" prop="productSpecifications">-->
      <!--<el-input v-model="dataForm.productSpecifications" placeholder="商品规格"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="原价" prop="originalPrice">-->
      <!--<el-input v-model="dataForm.originalPrice" placeholder="原价"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="现价" prop="presentPrice">-->
      <!--<el-input v-model="dataForm.presentPrice" placeholder="现价"></el-input>-->
    <!--</el-form-item>-->
    <!--<el-form-item label="图文详情" prop="graphicDetails">-->
      <!--<el-input v-model="dataForm.graphicDetailsStr" placeholder="图文详情"></el-input>-->
    <!--</el-form-item>-->
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<style>
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
<script>
  export default {
    data () {
      return {
        options: [],
        visible: false,
        dataForm: {
          commodityId: 0,
          categoryActivityId: '',
          commodityName: ''
        },
        dataRule: {
        }
      }
    },
    methods: {
      init (id) {
        this.categoryActivityList()
        this.dataForm.commodityId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.commodityId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarcommoditydb/info/${this.dataForm.commodityId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                console.log(data)
                this.dataForm.commodityName = data.cellarCommodityDb.commodityName
              }
            })
          }
        })
      },
      categoryActivityList () {
        this.$http({
          url: this.$http.adornUrl(`/cellar/cellarcategoryactivitydb/list`),
          method: 'post',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.options = data.page.list
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
              url: this.$http.adornUrl(`/cellar/cellarcategoryactivitycommodityreviewdb/save`),
              method: 'post',
              data: this.$http.adornData({
                'commodityId': this.dataForm.commodityId || undefined,
                'categoryActivityId': this.dataForm.categoryActivityId
                // 'commodityName': this.dataForm.commodityName,
                // 'originalPrice': this.dataForm.originalPrice,
                // 'presentPrice': this.dataForm.presentPrice,
                // 'graphicDetailsStr': this.dataForm.graphicDetailsStr,
                // 'picture': this.dataForm.picture,
                // 'creationTime': this.dataForm.creationTime,
                // 'state': this.dataForm.state,
                // 'monthSales': this.dataForm.monthSales,
                // 'totalSales': this.dataForm.totalSales,
                // 'inventory': this.dataForm.inventory,
                // 'highPraise': this.dataForm.highPraise,
                // 'commodityRotationChart': this.dataForm.commodityRotationChart,
                // 'productSpecifications': this.dataForm.productSpecifications,
                // 'storeId': this.dataForm.storeId,
                // 'categoryId': this.dataForm.categoryId,
                // 'labelList': this.dynamicTags,
                // 'categoryPathList': this.selectedOptions3,
                // 'commodityRotationChartList': this.commodityRotationChartList
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
