<template>
  <el-scrollbar max-height="91vh">
    <img class="img" src="../assets/images/light-bg.png" alt="展示图">
    <div @click="count.increment()">这是介绍组件{{count.count}}，尚未填入</div>
  </el-scrollbar>
</template>
<script setup>
import {useCounterStore} from "@/stores/counter";

const count = useCounterStore();
console.log(count.count)
</script>

<style lang="less" scoped>
</style>
