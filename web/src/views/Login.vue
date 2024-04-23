<template>
  <div>
    <el-container>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="login_ruleForm">
          <el-form-item label="昵称" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item label="验证" prop="slidevalid" required="true">
            <slide-verify :l="42"
                          :r="10"
                          :w="310"
                          :h="155"
                          slider-text="请向右滑动验证"
                          @success="onSuccess"
                          @fail="onFail"
                          @refresh="onRefresh"
            ></slide-verify>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
          <el-form-item>
            <router-link to="/register">去注册</router-link>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    const validateusername = (rule, value, callback) => {
      const reg = new RegExp(/^[\u4e00-\u9fa5_a-zA-Z0-9]{4,20}?/);
      if (value === '') {
        callback(new Error('请输入昵称'));
      } else if (!reg.test(this.ruleForm.username)) {
        callback(new Error('该账号未注册！'));
      } else {
        var having = false;
        this.$axios.get('http://localhost:8081/checkusername', this.ruleForm.username).then(res => {
              console.log('res=>', res);
              having = res.data.data
            }
        )
        if (!having) {
          callback(new Error('该账号未注册！'));
        }else{
          callback();
        }
      }
    }
    const validatepassword = (rule, value, callback) => {
      const reg = new RegExp(/^(?=.*[0-9])(?=.*[a-zA-Z]).{9,16}$/);
      if (value === '') {
        callback(new Error('请输入密码'));
      } else if (!reg.test(this.ruleForm.password)) {
        callback(new Error('密码或昵称错误，请重新输入！'));
      } else {
        var match = false;
        this.$axios.get('http://localhost:8081/checklogin', this.ruleForm).then(res => {
              console.log('res=>', res);
          match = res.data.data
            }
        )
        if (!match) {
          callback(new Error('密码或昵称错误，请重新输入！'));
        }else{
          callback();
        }
      }
    }

    return {
      ruleForm: {
        username: '',
        password: ''
      },
      slideflag: 1,
      timer: null,
      count: '',
      show: '',

      rules: {
        username: [
          {required: true, validator: validateusername, trigger: 'blur'},
        ],
        password: [
          {required: true, validator: validatepassword, trigger: 'blur'},
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.slideflag === 2) {
            //发起post请求，url是后端的端口号，提交表单数据，从then得到数据
            this.$axios.post('http://localhost:3000/user/account/token/', this.ruleForm).then(res =>{
                  //const jwt = res.headers['authorization']//里面有用户的昵称和密码
                  const userInfo = res.data;
                  console.log(userInfo);
                  //保存用户信息
                  localStorage.setItem('username',userInfo.username);
                  localStorage.setItem('password',userInfo.password);
                  localStorage.setItem('email',userInfo.email);

                  this.$store.commit('handleUserName', userInfo.username);
                  this.$store.commit('handlePassword', userInfo.password);
                  this.$store.commit('handleEmail', userInfo.email);
                  //跳转到首页
                  if (res.data.message === 'success') {
                    alert('登录成功！');
                    const timejump = 1;
                    if (!this.timer) {
                      this.count = timejump;
                      this.show = false;
                      this.timer = setInterval(() => {
                        if (this.count > 0 && this.count <= timejump) {
                          this.count--;
                        } else {
                          this.show = true;
                          clearInterval(this.timer);
                          this.timer = null;
                          //跳转的页面写在此处
                          this.$router.push({path: '/videoshow'});
                        }
                      }, 100)
                    }
                  }
                  else{
                    alert('登录失败！')
                  }
                }
            )
          }
          else{
            alert('请先进行验证！');
          }
        } else {
          alert('登录失败!');
          console.log('登录失败!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.slideflag = 1
    },
    onSuccess(){
      this.slideflag = 2;
      alert('验证通过！')
    },
    onFail(){
      this.slideflag = 3;
      alert('验证失败！')
    },
    onRefresh(){
      this.slideflag = 4
    }
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}
body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.login_ruleForm{
  max-width: 415px;
  margin:0 auto;
}

</style>