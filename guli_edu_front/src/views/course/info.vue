<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>
    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>
    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"></el-input>
      </el-form-item>
      <!-- 所属分类 -->
      <el-form-item label="课程分类">
        <el-select v-model="courseInfo.subjectParentId" placeholder="一级分类" @change="subjectLevelOneChanged">
          <el-option v-for="subject in subjectOneList"
                     :key="subject.id"
                     :label="subject.title"
                     :value="subject.id"/>
        </el-select>

        <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
          <el-option v-for="subject in subjectTwoList"
                     :key="subject.value"
                     :label="subject.title"
                     :value="subject.id"/>
        </el-select>
      </el-form-item>

      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option v-for="teacher in teacherList"
                     :key="teacher.id"
                     :label="teacher.name"
                     :value="teacher.id"/>
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder=""/>
      </el-form-item>

      <!-- 课程简介-->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description"/>
      </el-form-item>

      <!-- 课程封面-->
      <el-form-item label="课程封面">
        <el-upload :show-file-list="false"
                   :on-success="handleAvatarSuccess"
                   :before-upload="beforeAvatarUpload"
                   :action="BASE_API+'/edu/oss/avatar'"
                   class="avatar-uploader">
          <img :src="courseInfo.cover" height="150px">
        </el-upload>
      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder=""/>
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import course from '@/api/course';
import subject from '@/api/subject';
import Tinymce from '@/components/Tinymce';

export default {
  components: {Tinymce},
  data() {
    return {
      saveBtnDisabled: false,
      courseInfo: {
        title: '',
        subjectId: '',       // 二级分类
        subjectParentId: '', // 一级分类
        teacherId: '',
        lessonNum: 0,
        description: '',
        cover: 'course-cover.jpg',
        price: 0
      },
      teacherList: [],
      subjectOneList: [], //一级分类
      subjectTwoList: [], //二级分类
      BASE_API: process.env.VUE_APP_BASE_API
    }
  },
  created() {
    this.getTeacherList();
    this.getOneSubjects();
  },
  methods: {
    // 上传封面成功调用
    handleAvatarSuccess(res, file) {
      this.courseInfo.cover = res.data.url;
    },
    // 上传封面之前调用-限制文件类型或文件大小
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },

    // 点击一级分类触发change 获得二级分类,会自动将id作为参数
    subjectLevelOneChanged(oneSubjectId) {
      for (let i = 0; i < this.subjectOneList.length; i++) {
        let oneSubject = this.subjectOneList[i];
        if (oneSubjectId === oneSubject.id) {
          this.subjectTwoList = oneSubject.children;
          this.courseInfo.subjectId = '';
        }
      }
    },

    // 查询所有一级分类
    getOneSubjects() {
      subject.getTreeSubjects()
        .then(response => {
          this.subjectOneList = response.data.subjects;
        })
    },

    // 查询所有讲师-下拉框
    getTeacherList() {
      course.getTeacherList()
        .then(response => {
          this.teacherList = response.data.items;
        })
    },

    saveOrUpdate() {
      course.addCourseInfo(this.courseInfo)
        .then(response => {
          this.$message({
            type: 'success',
            message: '添加信息成功!'
          });
          // 下一步
          this.$router.push({path: '/course/chapter/' + response.data.courseId});
        });
    }
  }

}
</script>
<style scoped>
.tinymce-container {
  line-height: 29px;
}
</style>
