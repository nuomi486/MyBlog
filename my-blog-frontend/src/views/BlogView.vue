<script setup>
import UserInfo from "@/components/blog/UserInfo.vue";
import Essay from "@/components/blog/essay.vue";
</script>

<template>
  <div class="common-layout">
    <el-row :gutter="10">
<!--      左侧文章内容展示-->
      <el-col :span="20">
        <div class="grid-content">
          <essay/>
        </div>
      </el-col>
<!--      右侧用户简讯展示-->
      <el-col :span="4">
        <div class="grid-content">
          <user-info/>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped lang="less">
.common-layout{
  width: 100%;
  padding: 0 0.8rem;

}

</style>