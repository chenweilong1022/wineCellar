<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
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
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('cellar:cellarcategorydb:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('cellar:cellarcategorydb:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
        prop="categoryId"
        header-align="center"
        align="center"
        label="类别id">
      </el-table-column>
      <el-table-column
        prop="categoryName"
        header-align="center"
        align="center"
        label="类别名称">
      </el-table-column>
      <el-table-column
        prop="supCategoryName"
        header-align="center"
        align="center"
        label="上级类别">
      </el-table-column>
      <el-table-column
        prop="stateStr"
        header-align="center"
        align="center"
        label="状态">
        <template slot-scope="scope">
          <div @click="updateState(scope.row.categoryId,scope.row.state)" slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.stateStr }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间">
      </el-table-column>
      <el-table-column
        prop="storeName"
        header-align="center"
        align="center"
        label="店铺">
      </el-table-column>
      <el-table-column
        prop="level"
        header-align="center"
        align="center"
        label="级别">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.categoryId)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.categoryId)">删除</el-button>
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
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './cellarcategorydb-add-or-update'
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
        dataForm: {
          key: '',
          supCategoryIdList: []
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
      this.listOneLevel()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/cellar/cellarcategorydb/list'),
          method: 'post',
          // params: this.$http.adornParams({
          //   'page': this.pageIndex,
          //   'limit': this.pageSize,
          //   'key': this.dataForm.key,
          //   'supCategoryIdList': this.selectedOptions3
          // })
          data: this.$http.adornData({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key,
            'supCategoryIdList': this.selectedOptions3
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
            url: this.$http.adornUrl('/cellar/cellarcategorydb/update'),
            method: 'post',
            data: this.$http.adornData({
              'categoryId': id,
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
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.categoryId
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/cellar/cellarcategorydb/delete'),
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
