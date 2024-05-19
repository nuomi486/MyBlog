<template>
  <el-menu
      :default-active="defaultActive"
      class="el-menu-vertical"
      :collapse="isCollapse"
      :default-openeds="['/essay']"
      router
      background-color="#EEFCFD"
  >
    <el-sub-menu
        v-for="item in children"
        :index="item.path"
        v-show="item.children.length > 0">
      <template #title>
        <el-icon><icon-menu /></el-icon>
        <span>{{item.name}}</span>
      </template>
      <el-menu-item v-for="(items, indexes) in subs" :key="indexes" :index="items.path">
        {{ items.name }}
      </el-menu-item>
    </el-sub-menu>
    <el-menu-item
        v-for="item in children"
        :index="item.path"
        v-show="item.children.length === 0">
      <el-icon><Setting /></el-icon>
      <template #title>{{item.name}}</template>
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import {computed, reactive, ref} from 'vue'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import {useRoute, useRouter} from "vue-router";
import {useSwitchStores} from "@/stores/swicth";

const isCollapse = computed( ()=>{
  return useSwitchStores().adminMemuSwitch;
});//是否只展示图标
//根据导航的path来点亮某个item，作用：防止刷新时被冲没
const defaultActive = useRoute().fullPath;
//获取这个admin页面的路由并用于渲染菜单
const routers = useRouter().getRoutes();
let children = reactive([]);
let subs = reactive([]);
routers.map((item)=>{
  if (item.name === 'admin'){
      children = item.children;
      //再发现子选项时就写入subs
      item.children.map((itemChildren)=>{
        if (itemChildren.children.length > 0){
          subs = itemChildren.children;
        }
      })
  }
});

</script>

<style lang="less" scoped>
.el-menu-vertical:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
