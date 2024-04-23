<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import $ from 'jquery';


export default{
  setup(){
    $.ajax({
      url:"http://127.0.0.1:3000/user/account/token/",
      type:"post",
      data: {
        name: "RammusLeo",
        password: "123456",
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    })

    $.ajax({
      url:"http://127.0.0.1:3000/video/player",
      type:"get",
      headers: {
        Authorization:"Bearer "+ "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwZTQwNjBiZGQ5OWM0NDQ5ODY1ZGJjYzk1ZmMzNjc5MiIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY1OTMzOTA4OCwiZXhwIjoxNjYwNTQ4Njg4fQ.5U0N-dlWt9kpXqpOUg4VvUDY_fQjOrsY86Dl2I5Qs1k",
      },      
      success(resp){
        console.log("success");
      },
      error(resp){
        console.log("error");
      }
    })
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

.bg-purple {
  background: #d3dce6;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

</style>
