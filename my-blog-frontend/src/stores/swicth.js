import { ref } from 'vue'
import { defineStore } from 'pinia'
export const useSwitchStores = defineStore("switch",()=>{

    const adminMemuSwitch = ref(false);

    function switchTarget(){
        adminMemuSwitch.value = !adminMemuSwitch.value;
    }

    return {
        adminMemuSwitch,
        switchTarget,
    }
})