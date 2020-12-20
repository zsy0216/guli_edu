<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;"/>

    <el-tree
      ref="tree2"
      :data="data"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />

  </div>
</template>

<script>
import subject from '@/api/subject/index';

export default {

  data() {
    return {
      filterText: '',
      data: [], //树形结构数据
      defaultProps: {
        children: 'children',
        label: 'title'
      }
    }
  },

  created() {
    this.getTreeSubjects();
  },

  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val);
    }
  },

  methods: {
    getTreeSubjects() {
      subject.getTreeSubjects()
        .then(response => {
          this.data = response.data.subjects;
        });
    },

    filterNode(value, data) {
      if (!value) return true;
      return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1;
    }
  }
}
</script>
