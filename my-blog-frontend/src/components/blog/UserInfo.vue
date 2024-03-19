<template>
  <card>
    <template #header>
      <div class="users" >
        <img style="width: 80px;height: 80px;border-radius: 50%;" :src="userInfo.img" alt="头像">
        <p>{{ userInfo.username }}</p>
        <span style="font-size: 12px;">这个人很懒，没有留下个性签！！</span>
      </div>
    </template>
  </card>
</template>

<script setup>
import Card from "@/components/model/card.vue";
import {get} from "@/net";
import { ref } from "vue";

let userInfo = ref({});

function getInfo() {
  get('/api/auth/account/getDetail', (data)=>{
      userInfo.value = data;
  })
}

getInfo();
</script>

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