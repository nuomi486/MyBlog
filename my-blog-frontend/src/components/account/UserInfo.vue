<script setup>

import Card from "@/components/model/card.vue";
</script>

<template>
  <div>
    <card>
      <template #header>
        <div class="radius" style="{ border-radius: var(--el-border-radius-round);}">
            <img src="https://ts1.cn.mm.bing.net/th?id=OIP-C.0kSGVdYW_FVz2hDaE6rnvwAAAA&w=150&h=150&c=8&rs=1&qlt=90&o=6&dpr=1.2&pid=3.1&rm=2" alt="头像">
            <h2>StickyR</h2>
            <span>这个人很懒，没有留下个性签！！</span>
        </div>
      </template>
      <template #context>
        这是一个简介区域
      </template>
    </card>
  </div>
</template>

<style scoped lang="less">

</style>