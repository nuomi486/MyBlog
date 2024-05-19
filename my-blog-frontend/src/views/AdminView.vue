<script setup>
import AdminMemu from "@/components/admin/adminMemu.vue";
import AdminContent from "@/components/admin/adminContent.vue";
</script>

<template>
    <div class="admin-layout">
      <AdminMemu/>
      <AdminContent/>
    </div>
</template>

<style scoped lang="less">
.admin-layout{
  min-height: 100vh;
  max-width: 100vw;
  max-height: 100vh;
  display: flex;
  justify-content: left;
}
</style>