<template>
  <div class="mod-demo-ueditor">
    <script :id="ueId" class="ueditor-box" type="text/plain" style="width: 100%; height: 260px;"></script>
  </div>
</template>

<script>
  import ueditor from 'ueditor'
  export default {
    props: ['pp'],
    data () {
      return {
        ue: null,
        ueId: `J_ueditorBox_${new Date().getTime()}`,
        ueContent: '',
        dialogVisible: false
      }
    },
    mounted () {
      this.ue = ueditor.getEditor(this.ueId, {
        serverUrl: this.$http.adornUrl(`/app/file/config`), // 服务器统一请求接口路径
        zIndex: 3000
      })
    },
    methods: {
      init (graphicDetailsStr) {
        this.ue.setContent(graphicDetailsStr)
      },
      getContent () {
        this.dialogVisible = true
        this.ue.ready(() => {
          this.ueContent = this.ue.getContent()
        })
        return this.ueContent
      }
    }
  }
</script>

<style lang="scss">
  .mod-demo-ueditor {
    position: relative;
    z-index: 510;
    > .el-alert {
      margin-bottom: 10px;
    }
  }
</style>
