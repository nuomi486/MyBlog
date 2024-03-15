import { defineStore } from 'pinia'
import {computed, ref} from "vue";

export const useScroll = defineStore('useScroll', ()=>{

    const scrollTop = ref(0)

    const greater = computed(()=>{
        if (scrollTop.value >= 300){
            return true;
        }else{
            return false;
        }
    })

    function getScrollTop(event){
        scrollTop.value = event.target.scrollTop;
    }

    return{ scrollTop, greater, getScrollTop }
})