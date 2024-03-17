<template>
  <div class="main-layout light">
    <el-container class="header-container">
      <HeaderMenu/>
      <el-container class="main-container">
        <router-view/>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import HeaderMenu from "@/components/main/HeaderMenu.vue";
</script>

<style scoped>
.main-layout {
  min-height: 100vh;
  max-height: 100vh;
  padding: 0 0.4rem;
  height: 100dvh;


  .header-container {
    display: block;
  }

  .main-container {
    min-height: 91vh;
    max-height: 91vh;
    //box-shadow: 12px 12px 12px rgba(0, 0, 0, 0.07),
    //8px 8px 12px rgba(0, 0, 0, 0.05) inset,
    //100px 100px 80px rgba(0, 0, 0, 0.035);
    overflow: hidden;
    border-radius: 8px;
  }
}
</style>