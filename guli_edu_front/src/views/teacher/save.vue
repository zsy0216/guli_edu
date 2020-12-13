<template>
  <div class="app-container">
    讲师添加
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right"
                         min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <!--
          数据类型一定要和取出的json中的一致，否则没法回填
          因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>
      <!-- 讲师头像：TODO -->
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary"
                   @click="saveOrUpdate">保存
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import teacher from '@/api/teacher/index'

export default {
  data() {
    return {
      teacher: {
        name: '',
        sort: 0,
        level: 1,
        career: '',
        intro: '',
        avatar: ''
      },
      saveBtnDisabled: false
    }
  },
  created() {
    // 判断vue路由中是否有参数名为id的参数
    if (this.$route.params && this.$route.params.id) {
      const id = this.$route.params.id;
      this.getInfo(id);
    }
  },
  methods: {
    getInfo(id) {
      teacher.getTeacher(id)
        .then(response => {
          this.teacher = response.data.teacher;
        })
    },
    saveOrUpdate() {
      // 判断是修改还是添加(修改时会回显id)
      if (this.teacher.id) {
        this.update();
      } else {
        this.save();
      }
    },
    save() {
      teacher.addTeacher(this.teacher)
        .then(response => {
          this.$message({
            type: 'success',
            message: '添加成功!'
          });
          // 路由跳转-返回列表页面
          this.$router.push({path: '/teacher/table'})
        });
      // element-ui 已经封装过catch
    },
    update() {
      teacher.updateTeacher(this.teacher)
        .then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          });
          // 路由跳转-返回列表页面
          this.$router.push({path: '/teacher/table'})
        });
      // element-ui 已经封装过catch
    }
  }

}
</script>
