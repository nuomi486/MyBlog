<script setup>

import Card from "@/components/model/card.vue";
</script>

<template>
  <card>
    <template #header>
      <div class="users" >
        <img style="width: 80px;height: 80px;border-radius: 50%;" src="https://ts1.cn.mm.bing.net/th?id=OIP-C.0kSGVdYW_FVz2hDaE6rnvwAAAA&w=150&h=150&c=8&rs=1&qlt=90&o=6&dpr=1.2&pid=3.1&rm=2" alt="头像">
        <p>StickyR</p>
        <span style="font-size: 12px;">这个人很懒，没有留下个性签！！</span>
      </div>
    </template>
  </card>
</template>

<style scoped lang="less">
.users{
    margin: 2rem auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: left;
    line-height: 1.2rem;
}
</style>