<template>
<!--  列表-->
  <el-table :data="tableData" style="width: 99%">
    <el-table-column fixed prop="createdAt" label="createdAt" width="150" />
    <el-table-column prop="articleId" label="articleId" width="120" />
    <el-table-column prop="title" label="title" width="120" />
    <el-table-column prop="categoryId" label="categoryId" width="120" />
    <el-table-column prop="authorId" label="authorId" width="120" />
    <el-table-column prop="content" label="content" width="600" :show-overflow-tooltip="true" />
    <el-table-column fixed="right" label="Operations" width="120">
      <template #default="scope">
        <el-button link type="primary" size="small">Delete</el-button>
        <el-button link type="primary" size="small" @click="dialogClick(scope.row)">Edit</el-button>
      </template>
    </el-table-column>
  </el-table>
<!--  弹窗-->
  <el-dialog
      v-model="dialogTableVisible"
      title="Edit"
      width="900" top="8vh"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false">
    <el-scrollbar height="55vh">
      <el-form :model="dialogDetail" label-position="top" class="demo-form-inline">
        <el-form-item label="Essay Title" prop="title">
          <el-input
              v-model="dialogDetail.title"
              style="width: 240px;"
              placeholder="请输入文章标题！"
          />
        </el-form-item>
        <el-form-item label="Essay Type">
          <el-radio-group v-model="activeTag">
            <el-radio-button v-for="item in Tags" :label="item.name" @click="essayTageId = item.categoryId"/>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Essay Content">
          <MdEditor v-model="dialogDetail.content"
                    @onUploadImg="onUploadImg"/>
        </el-form-item>
      </el-form>
    </el-scrollbar>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogTableVisible = false">Cancel</el-button>
        <el-button type="primary" @click="onSubmit">
          Confirm
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {ref, reactive} from "vue";
import 'md-editor-v3/lib/style.css';
import { MdEditor } from "md-editor-v3";
import {Get, Post} from "@/net";
import {ElMessage} from "element-plus";

//弹窗
const dialogTableVisible = ref(false);
const dialogDetail = ref({})

//展示的数据列表
const activeTag = ref('科技');
const Tags = ref([]);
const essayTageId = ref(1);
const tableData = ref([]);

//获得表单内的一个数据
 function dialogClick(item){
   //获得标签
   Get("api/auth/getTags", (data)=>{
     if(Tags.value.length === 0){
       data.map((item)=>{
         Tags.value.push(item)
       })
     }
     dialogTableVisible.value = !dialogTableVisible.value;
     dialogDetail.value = item;
     // 在这里进行后续操作，确保数据已经返回
     activeTag.value = Tags.value[item.categoryId - 1].name;
   })
 }
 //行的提交操作
 function onSubmit(){
   dialogTableVisible.value = !dialogTableVisible.value;//弹窗是否显示
   dialogDetail.value.createdAt = new Date(dialogDetail.value.createdAt);//将时间转回原来的格式
   dialogDetail.value.categoryId = essayTageId.value;//将标签id替换成新的值
   if (dialogDetail.value.title !== '')
     Post("articles/upDateEssay",{
       "Content-Type": "application/json"
     }, dialogDetail.value, (data)=>{
       ElMessage.success(data);
     });
   else ElMessage.warning("标题不能为空！");
   dialogDetail.value = null;//回空免得干扰其他值
 }

//获取列表
Get("api/auth/getList", (data) => {
  data.map((item)=>{
    item.createdAt = formatDateTime(item.createdAt)
  });
  tableData.value = data; // 将结果传递给 Promise 的 resolve 函数
});
 //上传图片
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

//格式化时间
const formatDateTime = (time) => {
  const date = new Date(time);
  const year = date.getFullYear();
  const month = ('0' + (date.getMonth() + 1)).slice(-2);
  const days = date.getDate();
  const hours = ('0' + date.getHours()).slice(-2);
  const minutes = ('0' + date.getMinutes()).slice(-2);

  return year + '-' + month + '-' + days + ' ' + hours + ':' + minutes;
}
</script>

<style scoped lang="less">
.el-overlay-dialog{
  overflow: hidden;
}
</style>