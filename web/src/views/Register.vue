<template>
  <div>
    <el-container>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="register_ruleForm">
          <el-form-item label="昵称" prop="username">
            <el-input v-model="ruleForm.username" placeholder="请输入昵称"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="ruleForm.email" placeholder="请输入邮箱地址"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password" autocomplete="off" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="repassword">
            <el-input type="password" v-model="ruleForm.repassword" autocomplete="off" placeholder="请再次输入密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
          <el-form-item>
            <router-link to="/">去登录</router-link>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    var validateusername = (rule, value, callback) => {
      var reg = new RegExp(/^[\u4e00-\u9fa5_a-zA-Z0-9]{4,20}?/);
      if (value === '') {
        callback(new Error('请输入昵称'));
      } else if (!reg.test(this.ruleForm.username)) {
        callback(new Error('昵称长度为4-20个字节的中英文和数字组合'));
      } else {
        var having = false;
        this.$axios.get('http://localhost:8081/checkregister', this.ruleForm.username).then(res => {
              console.log('res=>', res);
              having = res.data.data
            }
        )
        if (having) {
          callback(new Error('该昵称已被使用'));
        } else {
          callback();
        }
      }
    }

    var validateemail = (rule, value, callback) => {
      var reg = new RegExp(/^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
      if (value === '') {
        callback(new Error('请输入邮箱号'));
      }else if(!reg.test(this.ruleForm.email)) {
        callback(new Error('邮箱格式错误'));
      }else{
        var having = false;
        this.$axios.get('http://localhost:8081/checkremail', this.ruleForm.email).then(res => {
              console.log('res=>', res);
              having = res.data.data
            }
        )
        if (having) {
          callback(new Error('该邮箱号已被使用'))
        } else {
          callback()
        }
      }
    }

    var validatepassword = (rule, value, callback) => {
      var reg = new RegExp(/^(?=.*[0-9])(?=.*[a-zA-Z]).{9,16}$/);
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (!reg.test(this.ruleForm.password)) {
        callback(new Error('密码为8-16个字节的英文字母和数字的组合'))
      } else {
        callback()
      }
    }

    var validateRepass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        username: '',
        email: '',
        password: '',
        repassword: '',
      },
      timer: null,
      count: '',
      show: '',

      rules: {
        username: [
          {required: true, validator: validateusername, trigger: 'blur'},
        ],
        email: [
          {required: true, validator: validateemail, trigger: 'blur'},
        ],
        password: [
          {required: true, validator: validatepassword, trigger: 'blur'},
        ],
        repassword: [
          {required: true, validator: validateRepass, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
            if (valid) {
              //发起post请求
              this.$axios.post('http://localhost:8081/register', this.ruleForm).then(res => {
                    if (res.data.message === '') {
                      alert('注册成功!即将跳转到登录页面！');
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
                            this.$router.push({path: '/'});
                          }
                        }, 100)
                      }
                    } else {
                      alert('注册失败!');
                      console.log('注册失败!!');
                      return false;
                    }
                  }
              )
            } else {
              alert('注册失败!');
              console.log('注册失败!!');
              return false;
            }
          }
      )
      ;
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
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

.register_ruleForm {
  max-width: 500px;
  margin: 0 auto;
}

</style>