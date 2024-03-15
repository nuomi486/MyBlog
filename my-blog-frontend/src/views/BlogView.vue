<script setup>
import UserInfo from "@/components/account/UserInfo.vue";
import Essay from "@/components/blog/essay.vue";
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="80vw">
        <essay/>
      </el-aside>
      <el-main>
        <user-info/>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped lang="less">
.common-layout{
  padding: 0 0.8rem;
}
</style>