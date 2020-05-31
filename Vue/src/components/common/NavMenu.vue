<!-- 开启 router 模式表示以 /router/index.js 作为 path 进行跳转 -->

<template>
    <el-menu 
    :default-active="this.$route.path"
    router 
    mode="horizontal" 
    text-color="#222" 
    active-text-color="red" 
    background-color="white"
    style="min-width: 1300px"
    >
    <el-menu-item v-for="(item, i) in navList" :key="i" :index="item.name">
        {{item.navItem}}
    </el-menu-item>
    <a href="#nowhere" style="color: #222;float: right;padding: 20px;">更多功能</a>
    <i class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px"></i>
    <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">自修室</span>
</el-menu>
</template>

<script>
  export default {
      name: 'NavMenu',
      data(){
          return {
              navList: [
                  {name: '/index', navItem: "首页"},
                  {name: '/notebook', navItem: "笔记本"},
                  {name: '/library', navItem: "图书馆"},
                  {name: '/admin', navItem: "个人中心"}
              ]
          }
      }
  }
</script>

<style scoped>
a{
    text-decoration: none;
}
span{
    pointer-events: none;
}
</style>