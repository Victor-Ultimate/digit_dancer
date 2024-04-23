<template>
  <div>
    <el-container>
      <!--      <el-header>Header</el-header>-->
      <el-main>
        <el-input placeholder="请输入关键字..." v-model="inputkeywords" style="width: 30%">
          <el-button slot="append" icon="el-icon-search">搜索</el-button>
        </el-input>

        <el-table :data="tablelist" style="width: 50%; margin: auto; border: none" :show-header=false>
          <el-table-column label="封面" width='240' prop="imagesrc">
            <template v-slot="scope">
              <el-image :src="scope.row.imagesrc"></el-image>
            </template>
          </el-table-column>

          <el-table-column prop="otherlist">
            <template v-slot="scope">
              <!-- 分行显示 -->
              <el-table border :data='scope.row.otherlist' :show-header=false style="border: none">
                <el-table-column prop="videoname">
                  <template v-slot="scope">
                    <!--                    <router-link v-bind:to="'/abc/' + scope.row.videoname">{{scope.row.videoname}}</router-link>-->
                    <router-link to="/videoshow/videodetails">{{ scope.row.videoname }}</router-link>
                  </template>
                </el-table-column>
              </el-table>
              <el-table border :data='scope.row.otherlist' :show-header=false style="border: none">
                <el-table-column prop="publishman"></el-table-column>
              </el-table>
              <el-table border :data='scope.row.otherlist' :show-header=false style="border: none">
                <el-table-column prop="publishtime"></el-table-column>
              </el-table>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="2"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </el-main>
      <!--      <el-footer>Footer</el-footer>-->
    </el-container>

  </div>
</template>

<script>
import axios from "axios";

export default {
  //初始化的时候自动触发
  mounted() {
    //_this表示当前的vue对象
    const _this = this;
    //0是代表起始页，10代表每页的条数
    axios.get('http://localhost:8081/getallvideo').then(res => {
      console.log(res);
      _this.total = res.data.content.totalElements;
      _this.tablelist.imagesrc = res.data.content.imagesrc;
      _this.tablelist.otherlist.videoname = res.data.content.videoname;
      _this.tablelist.otherlist.publishman = res.data.content.publishman;
      _this.tablelist.otherlist.publishtime = res.data.content.publishtime;
    })
  },
  data() {
    return {
      currentPage: 1,
      total: null,
      inputkeywords: '',
      search: '',
      tablelist: [{
        imagesrc: 'https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2022%2F0607%2F62ef396cj00rd3i63002fc000xc00k0g.jpg&thumbnail=660x2147483647&quality=80&type=jpg',
        otherlist: [
          {
            videoname: '超写实数字人筱竹初舞台《端“舞”安康》',
            publishman: '王灰灰',
            publishtime: '2022-06-03'
          }
        ]
      }, {
        imagesrc: 'https://n1image.hjfile.cn/qa/2022/06/06/f72a147d828d5747ff23359062e67917.png',
        otherlist: [
          {
            videoname: '韩首位AI男偶像公开!"车银优+门面综合体" ',
            publishman: 'JYP',
            publishtime: '2022-06-01'
          }
        ]
      }, {
        imagesrc: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.zitisheji.com%2Fstatic%2Fkindeditor%2Fattached%2Fimage%2F20200212%2F20200212134031_11622.jpg&refer=http%3A%2F%2Fwww.zitisheji.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1661654508&t=17ce463d9be4486d1770bb6fb6802f54',
        otherlist: [
          {
            videoname: '可提供智能交互体验的数字人',
            publishman: '凉生',
            publishtime: '2019-11-21',
          }
        ]
      }]
      // 原文链接：https://blog.csdn.net/weixin_45115895/article/details/109310889
    }
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      //this.username = this.$store.state.username;
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
  }
}
</script>


<style scoped>
/*由a来控制router-link*/
a {
  text-decoration: none;
  color: #0000FF;
  font-family: sans-serif;
  font-size: 20px;
}
</style>