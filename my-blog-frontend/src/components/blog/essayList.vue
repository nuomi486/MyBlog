<script setup>
import Card from "@/components/model/card.vue";
import router from "@/router";
import {ref} from "vue";
import {Get} from "@/net";

let arr = ref();
Get("api/auth/getList", (data) => {
  arr.value = data; // 将结果传递给 Promise 的 resolve 函数
});
function clickItems(eid){
  router.push({ name:'Articles', params: { eid }});
}

//格式化时间
const formatDateTime = (time) => {
  const date = new Date(time);
  const year = date.getFullYear();
  const month = ('0' + (date.getMonth() + 1)).slice(-2);
  const hours = ('0' + date.getHours()).slice(-2);
  const minutes = ('0' + date.getMinutes()).slice(-2);

  return year + '-' + month + ' ' + hours + ':' + minutes;
}
</script>

<template>
  <el-scrollbar height="85vh">
    <!--  列表-->
    <card class="card_box_items" v-for="(item,index) in arr" :key="index" @click="clickItems(item.articleId)">
      <template #head>
        <div class="imageBox">
          <img class="img" src="https://tse4-mm.cn.bing.net/th/id/OIP-C.pfw3xRtoTQGeRhwu0Yx26AHaEK?w=311&h=180&c=7&r=0&o=5&dpr=1.2&pid=1.7" alt="测试图">
        </div>
      </template>
      <template #context>
        <div class="essay-items">
          <h3>{{ item.title }}</h3>
          <div class="info">
            <span>{{item.userName}}</span>
            <span>{{formatDateTime(item.createdAt)}}</span>
          </div>
        </div>
      </template>
    </card>
  </el-scrollbar>
</template>

<style scoped lang="less">
.card_box_items{
  margin: 10px;
  display: flex;
  line-height: 1rem;
  overflow: hidden;
  cursor: pointer;
  position: relative;

  .head{
    .imageBox{
      max-width: 10vw;
      .img{
        width: 100%;
        height: 100%;
      }
    }
  }

  .context{
    position: relative;
    max-width: 70vw;

    .essay-items{
      min-width: 70vw;

      .info{
        display: flex;
        justify-content: space-between;
      }
    }
  }
}
</style>