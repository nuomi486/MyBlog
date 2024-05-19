<script setup>
import Card from "@/components/model/card.vue";
</script>

<template>
  <!--    分类导航-->
  <card class="card_box">
    <template #head>
      <div>返回，列表（进入blog详细的时候展示） | 分类标签（在列表中展示）</div>
    </template>
    <template #context>
      <router-view/>
    </template>
  </card>

</template>

<style scoped lang="less">
.card_box{
  margin-top: 10px;
}
</style>