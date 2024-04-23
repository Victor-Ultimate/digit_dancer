<template>
  <div class="main">
    <div class="container">
      <div class="player">
        <video-player  class="video-player vjs-custom-skin"
                       ref="videoPlayer"
                       :playsinline="true"
                       :options="playerOptions"
                       @play="onPlayerPlay($event)"
                       @pause="onPlayerPause($event)"
        >
        </video-player>
      </div>
    </div>
    <div class="introduction">
      <h3>超写实数字人筱竹初舞台《端“舞”安康》</h3>
      <p>农历五月初五端午节，是中国民间传统节日，也是中华传统文化中驱毒避邪养生的重要日子，饱含着两千多年来国人对生存环境和身体健康由来已久的关切和博大精深的智慧。新华网首个超写实数字人——筱竹以“中华文化推荐官”的身份首秀。她用自己喜欢的古诗词、中国鼓和一段创意国风舞蹈，表达了对端午安康的美好祝愿。</p>
    </div>
    <div class="comment">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm" :inline="true">
        <el-form-item  prop="desc" label="评论">
          <el-input type="textarea" v-model="ruleForm.desc" style="width: 500px" placeholder="请输入评论内容..."></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">发表</el-button>
          <el-button @click="resetForm('ruleForm')">清空</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="showcomments">
    </div>
  </div>
</template>

<script>

import { videoPlayer } from 'vue-video-player';

export default {
  name: "Videodetails",
  data () {
    return {
      playerOptions: {
        playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [{
          type: "video/mp4",
          src: "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4" //你的视频地址（必填）
        }],
        poster: "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2022%2F0607%2F62ef396cj00rd3i63002fc000xc00k0g.jpg&thumbnail=660x2147483647&quality=80&type=jpg", //你的封面地址
        width: document.documentElement.clientWidth,
        notSupportedMessage: '此视频暂无法播放，请稍后再试', //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true  //全屏按钮
        }
      },
      ruleForm: {
        desc: ''
      },
      rules: {
        desc: [
          { message: '请填写活动形式', trigger: 'blur' }
        ]
      },
    };
  },
  components: {
    videoPlayer
  },
  methods: {
    onPlayerPlay(player) {
      //alert("play");
    },
    onPlayerPause(player){
      //alert("pause");
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  computed: {
    player() {
      return this.$refs.videoPlayer.player
    }
  },
  submitForm(formName) {
    this.$refs[formName].validate((valid) => {
      if (valid) {
        alert('submit!');
      } else {
        console.log('error submit!!');
        return false;
      }
    });
  },
  resetForm(formName) {
    this.$refs[formName].resetFields();
  }
}
</script>

<style scoped>
.main{
  width: 800px;
  margin: auto;
}
.container {
  background-color: #efefef;
  width: 800px;
  margin: auto;
}
.introduction{
  text-align: left;
}
.showcomments{
  height: 200px;
  border:1px solid #333333;
}
.comment{
  width: 900px;
  margin: auto;
}
</style>