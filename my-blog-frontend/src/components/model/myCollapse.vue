<script setup>
import {ArrowDownBold, ArrowRightBold,} from '@element-plus/icons-vue'
import {ref} from "vue";

let onShow = ref(false);
let activeNub = ref("");
const title = defineProps({
  title:String,
  items: Array,
  itemNub: String,
});

</script>

<template>
  <div class="collapse-box">
    <div class="collapse-header" @click="onShow = !onShow">
      <div class="title">{{title.title}}</div>
      <div class="icon" v-if="items.length > 0">
        <el-icon v-if="!onShow"><ArrowRightBold /></el-icon>
        <el-icon v-else><ArrowDownBold /></el-icon>
      </div>
    </div>
    <div class="collapse-content"
         v-if="items.length > 0"
         v-for="(item,index) in items"
         :key="itemNub + '-' + index"
         @click="activeNub = itemNub + '-' + index"
         :style="activeNub ? 'color: red;':''"
         v-show="onShow">
      {{item.title}}
    </div>
  </div>
</template>

<style scoped lang="less">
.collapse-box{
  text-align: center;
  line-height: 1.5rem;
  /* 标题样式 */
  .collapse-header {
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    .icon{
      margin-top: 0.3rem;
    }
  }
  .collapse-content{
    text-align: center;
    line-height: 1rem;
  }
}
</style>