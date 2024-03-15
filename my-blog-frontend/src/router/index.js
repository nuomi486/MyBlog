import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Main from "@/views/Main.vue";
import Account from "@/views/AccountView.vue";
import Login from "@/components/account/Login.vue";
import Register from "@/components/account/Register.vue";
import BlogView from "@/views/BlogView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: Main,
      children:[
        {
          path: '/',
          name: 'Home',
          component: HomeView
        },
        {
          path: '/blog',
          name: 'Blog',
          component: BlogView
        },
        {
          path: '/about',
          name: 'About',
          component: () => import('../views/AboutView.vue')
        },

      ]
    },
    {
      path: '/login',
      name: 'account',
      component: Account,
      children:[
        {
          path: '/login',
          name: 'account-login',
          component: Login
        },
        {
          path: '/register',
          name: 'account-register',
          component: Register
        }
      ]
    },
  ]
})

export default router
