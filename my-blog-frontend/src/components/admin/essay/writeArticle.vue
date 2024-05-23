<template>
  <el-scrollbar height="90vh">
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
        <el-button @click="">存草稿</el-button>
      </el-form-item>
    </el-form>
  </el-scrollbar>
</template>

<script setup>
import { reactive,ref } from 'vue'
import 'md-editor-v3/lib/style.css';
import { MdEditor } from "md-editor-v3";
import '@vavt/v3-extension/lib/asset/style.css';
import { Emoji, ExportPDF } from '@vavt/v3-extension';
import {Get, Post} from "@/net";
import {ElMessage} from "element-plus";

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
//校验数据体
const rules = {
  title: [
    { required: true, message: '请输入标题' }
  ]
}

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
              rev(data)
            })
        });
      })
  );

  callback(res.map((item) => item));
};

Get("api/auth/getTags", (data)=>{
  Tags.value = data;
})
const onSubmit = () => {
  if (formInline.title !== '')
    Post("articles/pushEssay",{
      "Content-Type": "application/json"
    }, formInline, (data)=>{
      ElMessage.warning(data)
    });
  else ElMessage.warning("标题不能为空！");
}
</script>
<style scoped lang="less">

</style>