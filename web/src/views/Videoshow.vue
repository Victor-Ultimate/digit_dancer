<template>
  <div>
    <el-table
        :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
      <el-table-column label="视频" prop="video"></el-table-column>
      <el-table-column label="名称" prop="name"></el-table-column>
      <el-table-column label="发布日期" prop="updatetime"></el-table-column>
      <el-table-column align="right">
        <template slot="header">
          <el-input v-model="search" size="mini" placeholder="请输入关键字..."></el-input>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-sizes="[20, 50, 100]"
        :page-size="2"
        layout="total, sizes, prev, pager, next, jumper"
        :total="100">
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      tableData: [{
        video: '1',
        updatetime: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        video: '2',
        updatetime: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        video: '1',
        updatetime: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        video: '1',
        updatetime: '2016-05-03',
        name: '于荣煌',
        address: '上海市普陀区金沙江路 1516 弄'
      }],
      search: '',
      currentPage1: 5,
      currentPage2: 5,
      currentPage3: 5,
      currentPage4: 4
    }
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.username = this.$store.state.username;
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  }
}
</script>


<style scoped>

</style>