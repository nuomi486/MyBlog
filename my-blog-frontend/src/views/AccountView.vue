<template>
<!--  未登录状态的显示-->
  <div  class="account-box">
    <div class="context">
      <div class="crad-box">
        <div class="context-box">
          <div class="img">
            <img src="../assets/images/auth-img1.png" alt="">
          </div>
          <div>
            <h2><span class="primary">Hello</span>,Welcome!</h2>
            <p>Please Enter Your Details Below to Continue</p>
            <div>
              <router-view v-slot="{ Component }">
                <transition mode="out-in" appear>
                  <component :is="Component"></component>
                </transition>
              </router-view>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
</script>
<style scoped lang="less">
.account-box{
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  //background-color: cornflowerblue;
  overflow: hidden;

  &:after{
    background-image: url('src/assets/images/auth-bg.png');
    filter: drop-shadow(3px 3px 5px rgba(0,0,0,0.8));
    background-size: cover;
    background-repeat: no-repeat;
    content: "";
    position: absolute;
    right: 0;
    left: 0;
    top: 0;
    bottom: 0;
  }

  .context {
    z-index: 1;
    .crad-box{
      padding: 15px;
      border: 1px solid #f2f2f2;
      border-radius: 10px;
      box-shadow: 5px 5px 10px rgba(0,0,0,0.08),
                  -5px -5px 10px rgba(255,255,255,0.1);
      opacity: 0.8;

      .context-box {
        display: flex;
        justify-content: space-between;

      }
    }
  }
}

</style>