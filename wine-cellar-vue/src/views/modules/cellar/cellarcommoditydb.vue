<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('cellar:cellarcommoditydb:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('cellar:cellarcommoditydb:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="commodityId"
        header-align="center"
        align="center"
        label="商品id">
      </el-table-column>
      <el-table-column
        prop="commodityName"
        header-align="center"
        align="center"
        label="商品名称">
      </el-table-column>
      <el-table-column
        prop="originalPrice"
        header-align="center"
        align="center"
        label="原价">
      </el-table-column>
      <el-table-column
        prop="presentPrice"
        header-align="center"
        align="center"
        label="现价">
      </el-table-column>
      <el-table-column
        prop="creationTime"
        header-align="center"
        align="center"
        label="创建时间">
      </el-table-column>
      <el-table-column
        prop="stateStr"
        header-align="center"
        align="center"
        label="状态">
        <template slot-scope="scope">
          <div @click="updateState(scope.row.commodityId,scope.row.state)" slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.stateStr }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="monthSales"
        header-align="center"
        align="center"
        label="月销量">
      </el-table-column>
      <el-table-column
        prop="totalSales"
        header-align="center"
        align="center"
        label="总销量">
      </el-table-column>
      <el-table-column
        prop="inventory"
        header-align="center"
        align="center"
        label="库存量">
      </el-table-column>
      <el-table-column
        prop="highPraise"
        header-align="center"
        align="center"
        label="好评数">
      </el-table-column>
      <el-table-column
        prop="storeName"
        header-align="center"
        align="center"
        label="店铺">
      </el-table-column>
      <el-table-column
        prop="haveHandpickStr"
        header-align="center"
        align="center"
        label="是否精选">
      </el-table-column>
      <el-table-column
        prop="haveCategoryActivityStr"
        header-align="center"
        align="center"
        label="是否分类活动">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.haveHandpick == 1 || scope.row.haveHandpick == null" type="text" size="small" @click="handpickHandle(scope.row.commodityId)">申请精选商品</el-button>
          <el-button v-if="scope.row.haveCategoryActivity == 1  || scope.row.haveCategoryActivity == null" type="text" size="small" @click="categoryOrActivityHandle(scope.row.commodityId)">参加分类活动</el-button>
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.commodityId)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.commodityId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <category-or-activity v-if="categoryOrActivityVisible" ref="categoryOrActivity" @refreshDataList="getDataList"></category-or-activity>
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './cellarcommoditydb-add-or-update'
  import CategoryOrActivity from './cellarcommoditydb-category-or-activity'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        categoryOrActivityVisible: false
      }
    },
    components: {
      AddOrUpdate,
      CategoryOrActivity
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/cellar/cellarcommoditydb/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      updateState (id, state) {
        var stateStr
        var statePost
        if (state === 0) {
          stateStr = '禁用'
          statePost = -1
        } else if (state === -1) {
          stateStr = '启用'
          statePost = 0
        }
        this.$confirm(`确定对[id=${id}]进行[${stateStr}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/cellar/cellarcommoditydb/update'),
            method: 'post',
            data: this.$http.adornData({
              'commodityId': id,
              'state': statePost
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 分类活动
      categoryOrActivityHandle (id) {
        this.categoryOrActivityVisible = true
        this.$nextTick(() => {
          this.$refs.categoryOrActivity.init(id)
        })
      },
      handpickHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.commodityId
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '精选商品审核' : '批量精选商品审核'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/cellar/cellarhandpickcommodityreviewdb/handpick'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.commodityId
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/cellar/cellarcommoditydb/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
