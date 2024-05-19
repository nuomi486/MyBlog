<script setup>

import router from "@/router";
</script>

<template>
  <div>
    <h1>这是注册页面</h1>
    <el-button @click="router.push('login')">返回登陆</el-button>
  </div>
</template>

<style scoped lang="less">

</style>