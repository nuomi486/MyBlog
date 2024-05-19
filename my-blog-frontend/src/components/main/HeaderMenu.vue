<template>
  <div class="header">
    <h1>StickyR_</h1>
    <div class="routerTitle">
      <h2 class="routerName" v-for="(childrenName, index) in children" :key="index">
        <router-link :to="childrenName.path">{{childrenName.name}}</router-link>
      </h2>
    </div>
  </div>
</template>

<script setup>
import router from "@/router";
import {reactive} from "vue";

const routes = router.getRoutes();
let children = reactive([]);
routes.map((value)=>{
  if (value.name === 'main')
    children = value.children;
});


</script>

<style lang="less">
.header{
  display: flex;
  justify-content: space-between;
  line-height: 50px;
  .routerTitle{
    display: flex;
    flex-direction: row;
    text-align: center;
    .routerName{
      margin: 0 0.4rem;
      a{
        cursor: pointer;
      }//鼠标进入时变成手的状态
    }
  }
}
</style>
