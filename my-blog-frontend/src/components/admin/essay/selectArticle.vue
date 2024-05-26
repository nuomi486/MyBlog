<template>
<!--  列表-->
  <el-table :data="tableData" style="width: 99%">
    <el-table-column fixed prop="createdAt" label="createdAt" width="150" />
    <el-table-column prop="title" label="title" width="120" />
    <el-table-column prop="categoryId" label="categoryId" width="120" />
    <el-table-column prop="authorId" label="authorId" width="120" />
    <el-table-column prop="content" label="content" width="600" />
    <el-table-column fixed="right" label="Operations" width="120">
      <template #default="scope">
        <el-button link type="primary" size="small">
          Delete
        </el-button>
        <el-button link type="primary" size="small" @click="dialogClick(scope.row)">Edit</el-button>
      </template>
    </el-table-column>
  </el-table>
<!--  弹窗-->
  <el-dialog
      v-model="dialogTableVisible"
      title="修改"
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
          <MdEditor :toolbars="toolbars"
                    v-model="dialogDetail.content"
                    @onUploadImg="onUploadImg">
            <template #defToolbars>
              <Emoji />
            </template>
          </MdEditor>
        </el-form-item>
      </el-form>
    </el-scrollbar>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogTableVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogTableVisible = false">
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

const dialogTableVisible = ref(false);
const dialogDetail = ref({})
 function dialogClick(item){
   dialogTableVisible.value = !dialogTableVisible.value;
   dialogDetail.value = item;
 }

const tableData =reactive([
  {
    "title": "测试使用",
    "categoryId": 1,
    "authorId": 1,
    "content": "## Hello Editor!",
    "createdAt": "2024-05-05 13:02"
  },
  {
    "title": "测试使用",
    "categoryId": 1,
    "authorId": 1,
    "content": "## Hello Editor!",
    "createdAt": "2024-05-05 13:02"
  },
  {
    "title": "测试使用",
    "categoryId": 1,
    "authorId": 1,
    "content": "## Hello Editor!",
    "createdAt": "2024-05-05 13:02"
  },
  {
    "title": "123",
    "categoryId": 1,
    "authorId": 1,
    "content": "## Hello Editor!\n123123",
    "createdAt": "2024-05-05 13:49"
  },
  {
    "title": "测试时间",
    "categoryId": 2,
    "authorId": 1,
    "content": "## Hello Editor!\n\n你好",
    "createdAt": "2024-05-24T15:22:39.487Z"
  }
])
</script>

<style scoped lang="less">
.el-overlay-dialog{
  overflow: hidden;
}
</style>