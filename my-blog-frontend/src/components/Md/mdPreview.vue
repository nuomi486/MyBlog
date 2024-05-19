<script setup>
// preview.css相比style.css少了编辑器那部分样式
import 'md-editor-v3/lib/preview.css';
import { reactive, ref} from 'vue';
import {MdCatalog, MdPreview} from "md-editor-v3";
import {useRoute} from "vue-router";
import {Get} from "@/net";

const eid = useRoute().params.eid;
//数据初始化
let state = reactive({
  theme:'light',
  text: "## 正在加载",
  id: 'md-editor'
});
//得到滚动条，用在目录导航时跟踪
const scrollElement = ref('.el-scrollbar__wrap');
//md目录点击定位事件
const toTarget = (e, t) => {
  // 下面这个阻止默认事件可以不需要，但e必须在，如果警告没使用的话，可以用_e: MouseEvent,也有可能是我方式不太对
  e.preventDefault();
  const el = document.getElementById(t.text);
  if (el) {
    scrollElement.value.scrollTo({
      top: el.offsetTop - 20,
      behavior: 'smooth',
    });
    // el.scrollIntoView({
    //   behavior: 'auto'
    // })
  }
}


const catalogScroll = ref(null);
let count = ref(0);
function catalogTarget(h){
  //TODO 还没实现blog页面滚动时带动目录一起滚动
  // count.value++;
  // if (count.value > 5){
  //   catalogScroll.value.scrollTop += 20;
  // }
}

//异步请求数据分区
Get("api/auth/getEssay?eid=" + eid, (data) => {
  state.text = data.content; // 将结果传递给 state
});
</script>

<template>
<div>
  <div class="catalogScroll" ref="catalogScroll">
    <MdCatalog :editorId="state.id"
               :onClick="toTarget"
               :scrollElement="scrollElement"
               :theme="state.theme"
               :scrollElementOffsetTop="80"
               :offsetTop="20"
               :onActive="catalogTarget" />
  </div>
  <el-scrollbar height="85vh" ref="scrollElement">
    <MdPreview :modelValue="state.text"
               :editorId="state.id"
               :theme="state.theme"
               :show-code-row-number="true"
               codeTheme="github"/>
  </el-scrollbar>
</div>
</template>
<style lang="less" scoped>
.catalogScroll{
  width: 10vw;
  height: 70vh;
  float: left;
  overflow: auto;
  /* 隐藏滚动条 */
  &::-webkit-scrollbar {
    width: 6px;
    display: none; /* 默认隐藏滚动条 */
  }
  /* 鼠标进入时显示滚动条 */
  &:hover::-webkit-scrollbar {
    display: block;
  }
  /* 自定义滚动条滑块 */
  &::-webkit-scrollbar-thumb {
    background-color: #1e1e1e1e;
    border-radius: 5px;
    &:hover{
      background: #3e3e3e3e;
    }
  }
}
</style>