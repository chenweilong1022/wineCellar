<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="商品名称" prop="commodityName">
      <el-input v-model="dataForm.commodityName" placeholder="商品名称"></el-input>
    </el-form-item>
    <el-form-item label="图片" prop="picture">
      <el-upload
        class="avatar-uploader"
        :action="url"
        :show-file-list="false"
        naem="file"
        :on-success="handleAvatarSuccessPicture">
        <img v-if="dataForm.picture" :src="dataForm.picture" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
      <el-form-item>
        <el-upload
          :action="url"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
          :on-success="handleSuccess"
          :file-list="editFiles"
          list-type="picture-card">
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </el-form-item>
    <el-form-item label="分类id" prop="categoryId">
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
    <el-form-item label="标签" prop="label">
      <el-tag
        :key="tag"
        v-for="tag in dynamicTags"
        closable
        :disable-transitions="false"
        @close="handleClose(tag)">
        {{tag}}
      </el-tag>
      <el-input
        class="input-new-tag"
        v-if="inputVisible"
        v-model="inputValue"
        ref="saveTagInput"
        size="small"
        @keyup.enter.native="handleInputConfirm"
        @blur="handleInputConfirm">
      </el-input>
      <el-button v-else class="button-new-tag" size="small" @click="showInput">+ 新增标签</el-button>
  </el-form-item>
    <el-form-item label="库存量" prop="inventory">
      <el-input v-model="dataForm.inventory" placeholder="库存量"></el-input>
    </el-form-item>
    <el-form-item label="商品规格" prop="productSpecifications">
      <el-input v-model="dataForm.productSpecifications" placeholder="商品规格"></el-input>
    </el-form-item>
    <el-form-item label="原价" prop="originalPrice">
      <el-input v-model="dataForm.originalPrice" placeholder="原价"></el-input>
    </el-form-item>
    <el-form-item label="现价" prop="presentPrice">
      <el-input v-model="dataForm.presentPrice" placeholder="现价"></el-input>
    </el-form-item>
    <el-form-item label="酒币" prop="integral">
      <el-input v-model="dataForm.integral" placeholder="酒币"></el-input>
    </el-form-item>
    <el-form-item label="预售时间" prop="presellTime">
        <el-date-picker
          v-model="dataForm.presellTime"
          type="date"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="预售商品请填写预售时间">
        </el-date-picker>
    </el-form-item>
    <el-form-item label="图文详情" prop="graphicDetails">
      <el-input v-model="dataForm.graphicDetailsStr" placeholder="图文详情"></el-input>
    </el-form-item>
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
        editForm: {
          photo: ''
        },
        editFiles: [],
        dialogImageUrl: '',
        dialogVisible: false,
        selectedOptions3: [0],
        defaultParams: {
          value: 'categoryId',
          label: 'categoryName',
          children: 'cellarCategoryDbEntities'
        },
        options: [],
        commodityRotationChartList: [],
        dynamicTags: [],
        inputVisible: false,
        inputValue: '',
        url: '',
        visible: false,
        dataForm: {
          commodityId: 0,
          commodityName: '',
          originalPrice: '',
          presentPrice: '',
          graphicDetailsStr: '',
          picture: '',
          label: '',
          labelList: [],
          creationTime: '',
          state: '',
          monthSales: '',
          totalSales: '',
          inventory: '',
          integral: '',
          highPraise: '',
          commodityRotationChart: '',
          productSpecifications: '',
          storeId: '',
          categoryId: '',
          presellTime: ''
        },
        dataRule: {
          commodityName: [
            { required: true, message: '商品名称不能为空', trigger: 'blur' }
          ],
          originalPrice: [
            { required: true, message: '原价不能为空', trigger: 'blur' }
          ],
          presentPrice: [
            { required: true, message: '现价不能为空', trigger: 'blur' }
          ],
          graphicDetailsStr: [
            { required: true, message: '图文详情不能为空', trigger: 'blur' }
          ],
          picture: [
            { required: true, message: '图片不能为空', trigger: 'blur' }
          ],
          creationTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          monthSales: [
            { required: true, message: '月销量不能为空', trigger: 'blur' }
          ],
          totalSales: [
            { required: true, message: '总销量不能为空', trigger: 'blur' }
          ],
          inventory: [
            { required: true, message: '库存量不能为空', trigger: 'blur' }
          ],
          integral: [
            { required: true, message: '酒币不能为空', trigger: 'blur' }
          ],
          highPraise: [
            { required: true, message: '好评数不能为空', trigger: 'blur' }
          ],
          commodityRotationChart: [
            { required: true, message: '商品轮播图不能为空', trigger: 'blur' }
          ],
          productSpecifications: [
            { required: true, message: '商品规格不能为空', trigger: 'blur' }
          ],
          storeId: [
            { required: true, message: '店铺id不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      handleSuccess (response, file, fileList) {
        let inputValues = response.data
        if (inputValues) {
          this.commodityRotationChartList.push(inputValues)
        }
      },
      handleRemove (file, fileList) {
        console.log(file, fileList)
      },
      handlePictureCardPreview (file) {
        this.dialogImageUrl = file.url
        this.dialogVisible = true
      },
      handleClose (tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1)
      },
      showInput () {
        this.inputVisible = true
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus()
        })
      },
      handleInputConfirm () {
        let inputValue = this.inputValue
        if (inputValue) {
          this.dynamicTags.push(inputValue)
        }
        this.inputVisible = false
        this.inputValue = ''
      },
      init (id) {
        this.editFiles = []
        this.listOneLevel()
        this.commodityRotationChartList = []
        this.dynamicTags = []
        this.dataForm.commodityId = id || 0
        this.url = this.$http.adornUrl(`/app/file/upload`)
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
                this.dataForm.commodityName = data.cellarCommodityDb.commodityName
                this.dataForm.originalPrice = data.cellarCommodityDb.originalPrice
                this.dataForm.presentPrice = data.cellarCommodityDb.presentPrice
                this.dataForm.graphicDetailsStr = data.cellarCommodityDb.graphicDetailsStr
                this.dataForm.picture = data.cellarCommodityDb.picture
                this.dataForm.label = data.cellarCommodityDb.label
                this.dataForm.creationTime = data.cellarCommodityDb.creationTime
                this.dataForm.state = data.cellarCommodityDb.state
                this.dataForm.monthSales = data.cellarCommodityDb.monthSales
                this.dataForm.totalSales = data.cellarCommodityDb.totalSales
                this.dataForm.inventory = data.cellarCommodityDb.inventory
                this.dataForm.highPraise = data.cellarCommodityDb.highPraise
                this.dataForm.integral = data.cellarCommodityDb.integral
                this.dataForm.commodityRotationChart = data.cellarCommodityDb.commodityRotationChart
                this.dataForm.commodityRotationChartList = data.cellarCommodityDb.commodityRotationChartList
                this.dataForm.productSpecifications = data.cellarCommodityDb.productSpecifications
                this.dataForm.storeId = data.cellarCommodityDb.storeId
                this.dataForm.presellTime = data.cellarCommodityDb.presellTime
                this.dataForm.categoryId = data.cellarCommodityDb.categoryId
                this.dynamicTags = data.cellarCommodityDb.labelList
                this.selectedOptions3 = data.cellarCommodityDb.categoryPathList
                if (data.cellarCommodityDb.commodityRotationChartList) {
                  this.commodityRotationChartList = data.cellarCommodityDb.commodityRotationChartList
                  if (this.commodityRotationChartList.length > 0) {
                    for (let t = 0; t < this.commodityRotationChartList.length; t++) {
                      this.editFiles.push({name: 'name' + t, url: this.commodityRotationChartList[t]})
                      if (t === 0) {
                        this.editForm.photo += this.commodityRotationChartList[t]
                      } else {
                        this.editForm.photo += ',' + this.commodityRotationChartList[t]
                      }
                    }
                  }
                } else {
                  this.commodityRotationChartList = []
                }
              }
            })
          }
        })
      },
      listOneLevel () {
        this.$http({
          url: this.$http.adornUrl(`/cellar/cellarcategorydb/listAllLevel`),
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
      // 商品图片
      handleAvatarSuccessPicture (res, file) {
        this.dataForm.picture = res.data
      },
      // 店铺头像图
      handleAvatarSuccessCommodityRotationChart (res, file) {
        this.dataForm.commodityRotationChart = res.data
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarcommoditydb/${!this.dataForm.commodityId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'commodityId': this.dataForm.commodityId || undefined,
                'commodityName': this.dataForm.commodityName,
                'originalPrice': this.dataForm.originalPrice,
                'presentPrice': this.dataForm.presentPrice,
                'graphicDetailsStr': this.dataForm.graphicDetailsStr,
                'picture': this.dataForm.picture,
                'creationTime': this.dataForm.creationTime,
                'state': this.dataForm.state,
                'monthSales': this.dataForm.monthSales,
                'totalSales': this.dataForm.totalSales,
                'inventory': this.dataForm.inventory,
                'highPraise': this.dataForm.highPraise,
                'integral': this.dataForm.integral,
                'commodityRotationChart': this.dataForm.commodityRotationChart,
                'productSpecifications': this.dataForm.productSpecifications,
                'storeId': this.dataForm.storeId,
                'presellTime': this.dataForm.presellTime,
                'categoryId': this.dataForm.categoryId,
                'labelList': this.dynamicTags,
                'categoryPathList': this.selectedOptions3,
                'commodityRotationChartList': this.commodityRotationChartList
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
