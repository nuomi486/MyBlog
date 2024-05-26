<template>
  <el-form :model="formInline" label-position="top" class="demo-form-inline">
    <el-form-item label="Essay Title" prop="title">
      <el-input
          v-model="formInline.title"
          style="width: 240px;margin: 5px;"
          placeholder="请输入文章标题！"
      />
    </el-form-item>
    <el-form-item label="Essay Type">
        <el-radio-group v-model="activeTag">
          <el-radio-button v-for="item in Tags" :label="item.name" @click="essayTageId = item.categoryId"/>
        </el-radio-group>
    </el-form-item>
    <el-form-item label="Essay Content">
      <MdEditor :toolbars="toolbars"
                v-model="formInline.content"
                @onUploadImg="onUploadImg">
        <template #defToolbars>
          <Emoji />
          <ExportPDF :modelValue="formInline.content" height="700px" />
        </template>
      </MdEditor>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">Submit</el-button>
      <el-button @click="pushLocalStorage">存草稿</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { reactive,ref} from 'vue'
import 'md-editor-v3/lib/style.css';
import { MdEditor } from "md-editor-v3";
import '@vavt/v3-extension/lib/asset/style.css';
import { Emoji, ExportPDF } from '@vavt/v3-extension';
import {Get, Post} from "@/net";
import {ElMessage} from "element-plus";
//md编辑器的工具导航栏
const toolbars = [
  'bold',
  'underline',
  'italic',
  'strikeThrough',
  '-',
  'title',
  'sub',
  'sup',
  'quote',
  'unorderedList',
  'orderedList',
  'task',
  '-',
  'codeRow',
  'code',
  'link',
  'image',
  'table',
  'mermaid',
  'katex',
  0,
  1,
  2,
  3,
  '-',
  'revoke',
  'next',
  'save',
  '=',
  'prettier',
  'pageFullscreen',
  'fullscreen',
  'preview',
  'previewOnly',
  'htmlPreview',
  'catalog',
  'github'
];

const activeTag = ref('科技');
const Tags = ref([]);
const essayTageId = ref(1);
const str = JSON.parse(localStorage.getItem("authorize") || sessionStorage.getItem("authorize"));
const formInline = reactive({
  title: '',
  categoryId: essayTageId,
  authorId: str.uid,
  content: '## Hello Editor!',
  createdAt: new Date(),
})

const onUploadImg = async (files, callback) => {
  const res = await Promise.all(
      files.map((file) => {
        return new Promise((rev) => {
          const form = new FormData();
                form.append('file', file);
          Post('/articles/upload/img', {
                'Content-Type': 'multipart/form-data',
              }, form,
              (data)=>{
              rev(data);
            })
        });
      })
  );

  callback(res.map((item) => item));
};

//获得标签
Get("api/auth/getTags", (data)=>{
  Tags.value = data;
})
//提交到数据库
const onSubmit = () => {
  if (formInline.title !== '')
     Post("articles/pushEssay",{
      "Content-Type": "application/json"
    }, formInline, (data)=>{
      ElMessage.success(data);
    });
  else ElMessage.warning("标题不能为空！");
}
//提交到浏览器的本地存储
const pushLocalStorage = ()=>{
  if (formInline.title !== '')  {
    let getLS = JSON.parse(localStorage.getItem("LS_push"));
    // 如果LS_push项目不存在或者不是数组，初始化一个空数组
    if (!Array.isArray(getLS)) {
      getLS = [];
    }
// 将formInline添加到数组中
    getLS.push(formInline);
    // 将更新后的数组重新存储到本地存储中
    localStorage.setItem("LS_push", JSON.stringify(getLS));
    ElMessage.success("成功存草稿！");
  }
  else ElMessage.warning("标题不能为空！");
}
</script>
<style scoped lang="less">
.el-overlay-dialog{
  overflow: hidden;
}
</style>