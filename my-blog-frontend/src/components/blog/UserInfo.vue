<template>
  <card id="userInfo">
    <template #head>
      <div class="blogger" >
        <el-avatar :size="82" fit="fit"
                   :src="userInfo.img ? userInfo.img:'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
        />
<!--        <img class="headshot" :src="userInfo.img" alt="头像">-->
        <p>{{ userInfo.username }}</p>
        <span>{{userInfo.motto}}</span>
      </div>
    </template>
  </card>
</template>

<script setup>
import Card from "@/components/model/card.vue";
import { Get } from "@/net";
import { ref } from "vue";

let userInfo = ref({});

function getInfo() {
  Get('/api/auth/getDetail', (data)=>{
      userInfo.value = data;
  })
}

getInfo();
</script>

<style scoped lang="less">
.blogger{
    margin: 2rem auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: left;
    line-height: 1.2rem;
    font-size: 14px;
  .headshot{
    width: 80px;
    height: 80px;
    border-radius: 50%;
  }
}
</style>