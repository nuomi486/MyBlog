<template>
  <el-form
      label-position="top"
      :rules="rules"
      :model="formDetails"
      ref="formRef"
  >
    <el-form-item label="User" prop="username">
      <el-input v-model="formDetails.username" maxlength="10" type="text"  placeholder="请输入用户名/邮箱">
        <template v-slot:prefix>
          <el-icon class="el-input__icon">
            <svg x="1692002384024" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
                 width="32" height="32"><path d="M492.642454 544.492238c138.576752 0 250.922119-112.345367 250.922119-250.924119 0-138.576752-112.344367-250.922119-250.922119-250.922119-138.578752 0-250.924119 112.345367-250.924119 250.923119 0 138.577752 112.345367 250.923119 250.924119 250.923119m329.666273 152.226433C763.454107 634.645126 743.657571 613.336625 635.847097 607.485762H349.42681c-107.810473 5.851863-127.607009 27.159363-186.461629 89.231909-62.510535 64.169496-112.295368 145.404592-88.694922 246.144231 26.184386 65.876456 96.935728 81.138098 163.83616 81.138098h509.062069c66.899432 0 137.699773-15.261642 163.885159-81.138098 23.599447-100.739639-26.185386-181.974735-88.74492-246.144231" fill="#2c2c2c" p-id="4008"></path></svg>
          </el-icon>
        </template>
      </el-input>
    </el-form-item>
    <el-form-item label="Password" prop="password">
      <el-input maxlength="20" v-model="formDetails.password"  :type="isShowPassword ? 'password':'text'" placeholder="请输入密码！" >
        <template v-slot:prefix>
          <el-icon class="el-input__icon">
            <svg x="1692002458789" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5165" width="32" height="32"><path d="M780.8 354.58H665.6v-42.89c0-72.31-19.85-193.3-153.6-193.3-138.87 0-153.6 135.05-153.6 193.3v42.89H243.2v-42.89C243.2 122.25 348.79 0 512 0s268.8 122.25 268.8 311.69v42.89z m-192 314.84c0-43.52-34.58-78.65-76.8-78.65s-76.8 35.13-76.8 78.65c0 29.46 15.4 54.47 38.44 67.82v89.64c0 21.74 17.25 39.7 38.4 39.7s38.4-17.96 38.4-39.7v-89.64c23-13.35 38.36-38.36 38.36-67.82zM896 512v393.61c0 65.26-51.87 118.39-115.2 118.39H243.2c-63.291 0-115.2-53.13-115.2-118.39V512c0-65.22 51.87-118.39 115.2-118.39h537.6c63.33 0 115.2 53.17 115.2 118.39z" p-id="5166" fill="#2c2c2c"></path></svg>
          </el-icon>
        </template>
        <template v-slot:suffix>
          <el-icon class="el-input__icon" style="cursor: pointer" @click="isShowPassword = !isShowPassword">
            <svg v-if="!isShowPassword" x="1692006904238" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="10912" width="32" height="32"><path d="M512 298.666667c-162.133333 0-285.866667 68.266667-375.466667 213.333333 89.6 145.066667 213.333333 213.333333 375.466667 213.333333s285.866667-68.266667 375.466667-213.333333c-89.6-145.066667-213.333333-213.333333-375.466667-213.333333z m0 469.333333c-183.466667 0-328.533333-85.333333-426.666667-256 98.133333-170.666667 243.2-256 426.666667-256s328.533333 85.333333 426.666667 256c-98.133333 170.666667-243.2 256-426.666667 256z m0-170.666667c46.933333 0 85.333333-38.4 85.333333-85.333333s-38.4-85.333333-85.333333-85.333333-85.333333 38.4-85.333333 85.333333 38.4 85.333333 85.333333 85.333333z m0 42.666667c-72.533333 0-128-55.466667-128-128s55.466667-128 128-128 128 55.466667 128 128-55.466667 128-128 128z" fill="#2c2c2c" p-id="10913"></path></svg>
            <svg v-else-if="isShowPassword" x="1692007173963" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="11163" width="32" height="32"><path d="M332.8 729.6l34.133333-34.133333c42.666667 12.8 93.866667 21.333333 145.066667 21.333333 162.133333 0 285.866667-68.266667 375.466667-213.333333-46.933333-72.533333-102.4-128-166.4-162.133334l29.866666-29.866666c72.533333 42.666667 132.266667 106.666667 183.466667 192-98.133333 170.666667-243.2 256-426.666667 256-59.733333 4.266667-119.466667-8.533333-174.933333-29.866667z m-115.2-64c-51.2-38.4-93.866667-93.866667-132.266667-157.866667 98.133333-170.666667 243.2-256 426.666667-256 38.4 0 76.8 4.266667 110.933333 12.8l-34.133333 34.133334c-25.6-4.266667-46.933333-4.266667-76.8-4.266667-162.133333 0-285.866667 68.266667-375.466667 213.333333 34.133333 51.2 72.533333 93.866667 115.2 128l-34.133333 29.866667z m230.4-46.933333l29.866667-29.866667c8.533333 4.266667 21.333333 4.266667 29.866666 4.266667 46.933333 0 85.333333-38.4 85.333334-85.333334 0-12.8 0-21.333333-4.266667-29.866666l29.866667-29.866667c12.8 17.066667 17.066667 38.4 17.066666 64 0 72.533333-55.466667 128-128 128-17.066667-4.266667-38.4-12.8-59.733333-21.333333zM384 499.2c4.266667-68.266667 55.466667-119.466667 123.733333-123.733333 0 4.266667-123.733333 123.733333-123.733333 123.733333zM733.866667 213.333333l29.866666 29.866667-512 512-34.133333-29.866667L733.866667 213.333333z" fill="#2c2c2c" p-id="11164"></path></svg>
          </el-icon>
        </template>
      </el-input>
    </el-form-item>
    <el-form-item>
      <span style="width: 100%;display: flex;justify-content: space-between;">
        <el-checkbox size="large" label="Remember Me" name="type" v-model="formDetails.isRememberMe" />
      </span>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="userLogin()">Login</el-button>
      <span>还没有账号？</span><router-link to="/register">去注册</router-link>
    </el-form-item>
  </el-form>
</template>

<script setup>
import {ref,reactive} from "vue";
import {login} from "@/net";
import router from "@/router";
import {ElMessage} from "element-plus";

const formRef = ref();
const formDetails = reactive({
  username:'',
  password:'',
  isRememberMe: false
});
const isShowPassword = ref(true);
const rules = {
  username: [
    { required: true, message: '请输入用户名' }
  ],
  password: [
    { required: true, message: '请输入密码'}
  ]
}

function userLogin() {
  formRef.value.validate((isValid) => {
    if(isValid) {
      login(formDetails.username, formDetails.password, formDetails.isRememberMe, () => router.push({name:'admin'}))
    }else{
      ElMessage.warning("请输入正确账号密码！");
    }
  });
}

</script>

<style scoped lang="less">
  #toR{
    height: 25px;
    text-align: center;
    &:hover{
      color: blue;
      border-bottom: 1px solid blue;
    }

  }
</style>