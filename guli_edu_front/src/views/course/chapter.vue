<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>

    <ul class="chapterList">
      <li v-for="chapter in chapterVideoList" :key="chapter.id">
        {{ chapter.title }}
        <ul class="chapterList videoList">
          <li v-for="video in chapter.children" :key="video.id">
            {{ video.title }}
          </li>
        </ul>
      </li>
    </ul>

    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import chapter from '@/api/chapter'

export default {
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      chapterVideoList: []
    }
  },
  created() {
    // 获取路由中的id
    if (this.$route.params && this.$route.params.id) {
      const courseId = this.$route.params.id;
      this.getChapterVideoList(courseId);
    }
  },
  methods: {
    getChapterVideoList(id) {
      chapter.getChapterVideoList(id)
        .then(response => {
          this.chapterVideoList = response.data.allChapterVideo;
        })
    },

    previous() {
      this.$router.push({path: '/course/info/1'});

    },
    next() {
      this.$router.push({path: '/course/publish/1'});

    }
  }
}
</script>
<style scoped>
.chapterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}

.chapterList li {
  position: relative;
}

.chapterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}

.chapterList .acts {
  float: right;
  font-size: 14px;
}

.videoList {
  padding-left: 50px;
}

.videoList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}
</style>
