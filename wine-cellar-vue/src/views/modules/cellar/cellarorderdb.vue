<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-select clearable v-model="dataForm.orderStatus" placeholder="订单状态">
          <el-option
            v-for="item in orderstatuslist"
            :key="item.key"
            :label="item.value"
            :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-select clearable v-model="dataForm.orderType" placeholder="订单类型">
          <el-option
            v-for="item in carttypelist"
            :key="item.key"
            :label="item.value"
            :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-select clearable v-model="dataForm.methodPayment" placeholder="支付方式">
          <el-option
            v-for="item in methodpaymentlist"
            :key="item.key"
            :label="item.value"
            :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <!--<el-button v-if="isAuth('cellar:cellarorderdb:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>-->
        <el-button v-if="isAuth('cellar:cellarorderdb:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
        <el-button v-if="isAuth('cellar:cellarorderdb:delivery')" type="danger" @click="delivery()" :disabled="dataListSelections.length <= 0">批量发货</el-button>
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
        prop="orderId"
        header-align="center"
        align="center"
        label="订单id">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间">
      </el-table-column>
      <el-table-column
        prop="methodPaymentStr"
        header-align="center"
        align="center"
        label="支付方式">
      </el-table-column>
      <el-table-column
        prop="paymentTime"
        header-align="center"
        align="center"
        label="支付时间">
      </el-table-column>
      <el-table-column
        prop="orderNote"
        header-align="center"
        align="center"
        label="订单备注">
      </el-table-column>
      <el-table-column
        prop="totalOrderAmount"
        header-align="center"
        align="center"
        label="订单总金额">
      </el-table-column>
      <el-table-column
        prop="actualOrderAmount"
        header-align="center"
        align="center"
        label="订单实际金额">
      </el-table-column>
      <el-table-column
        prop="orderStatusStr"
        header-align="center"
        align="center"
        label="订单状态">
      </el-table-column>
      <el-table-column
        prop="orderTypeStr"
        header-align="center"
        align="center"
        label="订单类型">
      </el-table-column>
      <el-table-column
        prop="memberId"
        header-align="center"
        align="center"
        label="会员id">
      </el-table-column>
      <el-table-column
        prop="storeName"
        header-align="center"
        align="center"
        label="店铺">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.orderType == 0 && scope.row.orderStatus == 0" type="text" size="small" @click="delivery(scope.row.orderId)">发货</el-button>

          <el-button v-if="scope.row.orderStatus == -2" type="text" size="small" @click="afterSalesHandle(scope.row.orderId)">售后</el-button>
          <el-button type="text" size="small" @click="orderDetailHandle(scope.row.orderId)">订单详情</el-button>
          <!--<el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.orderId)">修改</el-button>-->
          <el-button type="text" size="small" @click="deleteHandle(scope.row.orderId)">删除</el-button>
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
    <after-sales v-if="afterSalesVisible" ref="afterSales" @refreshDataList="getDataList"></after-sales>
    <order-detail v-if="afterSalesVisible" ref="orderDetail" @refreshDataList="getDataList"></order-detail>
  </div>
</template>

<script>
  import AddOrUpdate from './cellarorderdb-add-or-update'
  import AfterSales from './cellarorderdb-aftersales'
  export default {
    data () {
      return {
        orderstatuslist: [],
        carttypelist: [],
        methodpaymentlist: [],
        dataForm: {
          key: '',
          orderStatus: '',
          orderType: '',
          methodPayment: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        afterSalesVisible: false
      }
    },
    components: {
      AddOrUpdate,
      AfterSales
    },
    activated () {
      this.getDataList()
      this.orderStatusList()
      this.carttypeList()
      this.methodpaymentList()
    },
    methods: {
      orderStatusList () {
        this.$http({
          url: this.$http.adornUrl('/sys/constants/orderstatuslist'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.orderstatuslist = data.data
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      carttypeList () {
        this.$http({
          url: this.$http.adornUrl('/sys/constants/carttypelist'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.carttypelist = data.data
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      methodpaymentList () {
        this.$http({
          url: this.$http.adornUrl('/sys/constants/methodpaymentlist'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.methodpaymentlist = data.data
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/cellar/cellarorderdb/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key,
            'orderStatus': this.dataForm.orderStatus,
            'orderType': this.dataForm.orderType,
            'methodPayment': this.dataForm.methodPayment
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
      afterSalesHandle (id) {
        this.afterSalesVisible = true
        this.$nextTick(() => {
          this.$refs.afterSales.init(id)
        })
      },
      // 新增 / 修改
      orderDetailHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 发货
      delivery (orderId) {
        var orderIds = orderId ? [orderId] : this.dataListSelections.map(item => {
          return item.orderId
        })
        this.$confirm(`确定对[id=${orderIds.join(',')}]进行[${orderIds ? '发货' : '批量发货'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/cellar/cellarorderdb/delivery'),
            method: 'post',
            data: this.$http.adornData(orderIds, false)
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
          return item.orderId
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/cellar/cellarorderdb/delete'),
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
