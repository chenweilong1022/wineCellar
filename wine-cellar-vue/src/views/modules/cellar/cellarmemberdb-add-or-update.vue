<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="等级" prop="level">
      <el-input v-model="dataForm.level" placeholder="等级"></el-input>
    </el-form-item>
    <el-form-item label="头像" prop="headPortrait">
      <el-input v-model="dataForm.headPortrait" placeholder="头像"></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder="昵称"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="gender">
      <el-input v-model="dataForm.gender" placeholder="性别"></el-input>
    </el-form-item>
    <el-form-item label="生日" prop="birthday">
      <el-input v-model="dataForm.birthday" placeholder="生日"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="mobilePhone">
      <el-input v-model="dataForm.mobilePhone" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item label="登录密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="登录密码"></el-input>
    </el-form-item>
    <el-form-item label="支付密码" prop="payPassword">
      <el-input v-model="dataForm.payPassword" placeholder="支付密码"></el-input>
    </el-form-item>
    <el-form-item label="登录token" prop="loginToken">
      <el-input v-model="dataForm.loginToken" placeholder="登录token"></el-input>
    </el-form-item>
    <el-form-item label="微信openid" prop="openid">
      <el-input v-model="dataForm.openid" placeholder="微信openid"></el-input>
    </el-form-item>
    <el-form-item label="余额" prop="balance">
      <el-input v-model="dataForm.balance" placeholder="余额"></el-input>
    </el-form-item>
    <el-form-item label="积分" prop="integral">
      <el-input v-model="dataForm.integral" placeholder="积分"></el-input>
    </el-form-item>
    <el-form-item label="个性签名" prop="individualitySignature">
      <el-input v-model="dataForm.individualitySignature" placeholder="个性签名"></el-input>
    </el-form-item>
    <el-form-item label="储值卡余额" prop="cardBalance">
      <el-input v-model="dataForm.cardBalance" placeholder="储值卡余额"></el-input>
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
          memberId: 0,
          createTime: '',
          state: '',
          level: '',
          headPortrait: '',
          nickname: '',
          gender: '',
          birthday: '',
          mobilePhone: '',
          password: '',
          payPassword: '',
          loginToken: '',
          openid: '',
          balance: '',
          integral: '',
          individualitySignature: '',
          cardBalance: ''
        },
        dataRule: {
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          level: [
            { required: true, message: '等级不能为空', trigger: 'blur' }
          ],
          headPortrait: [
            { required: true, message: '头像不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '昵称不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: '性别不能为空', trigger: 'blur' }
          ],
          birthday: [
            { required: true, message: '生日不能为空', trigger: 'blur' }
          ],
          mobilePhone: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '登录密码不能为空', trigger: 'blur' }
          ],
          payPassword: [
            { required: true, message: '支付密码不能为空', trigger: 'blur' }
          ],
          loginToken: [
            { required: true, message: '登录token不能为空', trigger: 'blur' }
          ],
          openid: [
            { required: true, message: '微信openid不能为空', trigger: 'blur' }
          ],
          balance: [
            { required: true, message: '余额不能为空', trigger: 'blur' }
          ],
          integral: [
            { required: true, message: '积分不能为空', trigger: 'blur' }
          ],
          individualitySignature: [
            { required: true, message: '个性签名不能为空', trigger: 'blur' }
          ],
          cardBalance: [
            { required: true, message: '储值卡余额不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.memberId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.memberId) {
            this.$http({
              url: this.$http.adornUrl(`/cellar/cellarmemberdb/info/${this.dataForm.memberId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.createTime = data.cellarMemberDb.createTime
                this.dataForm.state = data.cellarMemberDb.state
                this.dataForm.level = data.cellarMemberDb.level
                this.dataForm.headPortrait = data.cellarMemberDb.headPortrait
                this.dataForm.nickname = data.cellarMemberDb.nickname
                this.dataForm.gender = data.cellarMemberDb.gender
                this.dataForm.birthday = data.cellarMemberDb.birthday
                this.dataForm.mobilePhone = data.cellarMemberDb.mobilePhone
                this.dataForm.password = data.cellarMemberDb.password
                this.dataForm.payPassword = data.cellarMemberDb.payPassword
                this.dataForm.loginToken = data.cellarMemberDb.loginToken
                this.dataForm.openid = data.cellarMemberDb.openid
                this.dataForm.balance = data.cellarMemberDb.balance
                this.dataForm.integral = data.cellarMemberDb.integral
                this.dataForm.individualitySignature = data.cellarMemberDb.individualitySignature
                this.dataForm.cardBalance = data.cellarMemberDb.cardBalance
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
              url: this.$http.adornUrl(`/cellar/cellarmemberdb/${!this.dataForm.memberId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'memberId': this.dataForm.memberId || undefined,
                'createTime': this.dataForm.createTime,
                'state': this.dataForm.state,
                'level': this.dataForm.level,
                'headPortrait': this.dataForm.headPortrait,
                'nickname': this.dataForm.nickname,
                'gender': this.dataForm.gender,
                'birthday': this.dataForm.birthday,
                'mobilePhone': this.dataForm.mobilePhone,
                'password': this.dataForm.password,
                'payPassword': this.dataForm.payPassword,
                'loginToken': this.dataForm.loginToken,
                'openid': this.dataForm.openid,
                'balance': this.dataForm.balance,
                'integral': this.dataForm.integral,
                'individualitySignature': this.dataForm.individualitySignature,
                'cardBalance': this.dataForm.cardBalance
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
