<template>
  <el-scrollbar max-height="91vh">
    <img class="img" src="../assets/images/light-bg.png" alt="">
    <div>这是介绍组件，尚未填入</div>
    <el-button @click="logout()">
      logout
    </el-button>
  </el-scrollbar>
</template>
<script setup>
import {logout} from "@/net";
</script>

<style lang="less" scoped>
</style>
