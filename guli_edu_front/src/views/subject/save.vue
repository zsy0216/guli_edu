<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="信息描述">
        <el-tag type="info">excel模版示例</el-tag>
        <el-tag>
          <i class="el-icon-download"/>
          <a :href="'guli_edu_front/public/excel/example.xlsx'">点击下载模版</a>
        </el-tag>
      </el-form-item>
      <el-form-item label="选择Excel">
        <el-upload
          ref="upload"
          :auto-upload="false"
          :on-success="fileUploadSuccess"
          :on-error="fileUploadError"
          :disabled="importBtnDisabled"
          :limit="1"
          :action="BASE_API+'/edu/service/subjects'"
          name="file"
          accept="application/vnd.ms-excel">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button
            :loading="loading"
            style="margin-left: 10px;"
            size="small"
            type="success"
            @click="submitUpload">{{ fileUploadBtnText }}
          </el-button>
        </el-upload>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>

export default {
  data() {
    return {
      BASE_API: process.env.VUE_APP_BASE_API, // 接口API地址
      fileUploadBtnText: '上传添加课程分类', // 按钮文字
      importBtnDisabled: false, // 按钮是否禁用,
      loading: false
    }
  },
  created() {

  },
  methods: {
    // 点击按钮上传文件
    submitUpload() {
      this.fileUploadBtnText = '正在上传';
      this.importBtnDisabled = true;
      this.loading = true;
      this.$refs.upload.submit();
    },
    // 上传成功
    fileUploadSuccess() {
      this.fileUploadBtnText = '添加成功';
      this.loading = false;
      this.$message({
        type: 'success',
        message: '添加成功'
      });
    },
    // 上传失败
    fileUploadError() {
      this.fileUploadBtnText = '添加失败';
      this.loading = false;
      this.$message({
        type: 'error',
        message: '添加失败'
      });
    }
  }

}
</script>
