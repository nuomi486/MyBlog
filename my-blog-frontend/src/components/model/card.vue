<template>
  <div class="card_box">
    <div class="header">
      <slot name="header"></slot>
    </div>
    <div class="context">
      <slot name="context"></slot>
    </div>
    <div class="footer">
      <slot name="footer"></slot>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped lang="less">
</style>