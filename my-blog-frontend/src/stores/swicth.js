import { ref } from 'vue'
import { defineStore } from 'pinia'
export const useSwitchStores = defineStore("switch",()=>{

    const adminMemuSwitch = ref(false);

    function switchTarget(name){
        switch (name){
            case "adminMemuSwitch":
                adminMemuSwitch.value = !adminMemuSwitch.value;
                console.log("检索到输入的内容是：", name);
                break;
        }
    }
    return {
        adminMemuSwitch,
        switchTarget,
    }
})