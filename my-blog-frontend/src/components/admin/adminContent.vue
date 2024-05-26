<script setup>
import {useRoute} from "vue-router";
import {Expand, Fold} from "@element-plus/icons-vue";
import {useSwitchStores} from "@/stores/swicth";
import {computed} from "vue";

const memuSwitch = computed( ()=>{
  return useSwitchStores().adminMemuSwitch;
});

function target(){
  useSwitchStores().switchTarget()
}


</script>

<template>
  <div class="layout-admin">
    <div class="nav-title">
      <el-page-header @back="target()">
        <template #icon>
          <el-icon v-if="memuSwitch"><Expand /></el-icon>
          <el-icon v-else><Fold /></el-icon>
        </template>
        <template #title>
          Memu
        </template>
        <template #content>
          <span class="text-large font-600 mr-3"> {{ useRoute().name }} </span>
        </template>
        <template #extra>
          <div class="items-center">
            <el-avatar
                :size="32"
                class="mr-3"
                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            />
          </div>
        </template>
      </el-page-header>
    </div>
    <div class="context-view">
      <el-scrollbar height="90vh">
        <router-view/>
      </el-scrollbar>
    </div>
  </div>
</template>

<style scoped lang="less">
.layout-admin{
  flex-grow: 1;
  overflow: hidden;
  .nav-title{
    padding: 8px;
    background-color: #EEFCFD;
    box-shadow:
        8px 8px 8px rgba(0, 0, 0, 0.1);
  }

  .context-view{
    padding: 10px;
  }
}
</style>