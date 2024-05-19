<template>
  <el-scrollbar height="90vh">
    <el-form :model="formInline" label-position="top" class="demo-form-inline">
      <el-form-item label="Essay Title">
        <el-input
            v-model="formInline.titleName"
            style="width: 240px;margin: 5px;"
            placeholder="请输入文章标题！"
        />
      </el-form-item>
      <el-form-item label="Essay Content">
        <MdEditor v-model="formInline.date"  @onUploadImg="onUploadImg"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Query</el-button>
      </el-form-item>
    </el-form>
  </el-scrollbar>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'
import {MdEditor} from "md-editor-v3";
import 'md-editor-v3/lib/style.css';
import {Post} from "@/net";

const formInline = reactive({
  titleName: '',
  region: '',
  date: '## Hello Editor!',
})

const onUploadImg = async (files, callback) => {
  const res = await Promise.all(
      files.map((file) => {
        return new Promise((rev, rej) => {
          const form = new FormData();
                form.append('file', file);
          Post('/articles/img/upload',{
            'Content-Type': 'multipart/form-data',
          }, form, (data)=>{
              rev(data)
            })
        });
      })
  );

  callback(res.map((item) => item));
};

const onSubmit = () => {
  console.log('submit!')
}
</script>
<style scoped lang="less">

</style>