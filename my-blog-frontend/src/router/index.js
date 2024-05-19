import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Main from "@/views/Main.vue";
import Account from "@/views/AccountView.vue";
import Login from "@/components/account/Login.vue";
import Register from "@/components/account/Register.vue";
import BlogView from "@/views/BlogView.vue";
import essayList from "@/components/blog/essayList.vue";
import AdminView from "@/views/AdminView.vue";
import mdEditor from '@/components/Md/mdPreview.vue'
import WelcomeAdmin from "@/components/admin/welcomeAdmin.vue";

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
          component: BlogView,
          children:[
            {
              path: '/blog',
              name: 'ArticleList',
              component: essayList
            },
            {
              path: '/blog/articles/:eid',
              name: 'Articles',
              component: mdEditor
            },
          ]
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
    {
      path: '/admin',
      name: 'admin',
      component: AdminView,
      children:[
        {
          path: '/admin',
          name: 'WelcomeAdmin',
          component: WelcomeAdmin,
          children: []
        },
        {
          path: '/essay',
          name: 'Essays',
          children: [
            {
              path: '/admin/write',
              name: 'writeEssay',
              component: () => import('../components/admin/essay/writeArticle.vue'),
            },
            {
              path: '/admin/select',
              name: 'selectEssay',
              component: () => import('../components/admin/essay/selectArticle.vue'),
            }
          ]
        },
        {
          path: '/admin/user',
          name: 'AdminUser',
          component: () => import('../components/admin/adminUsersInfo.vue'),
          children: []
        }
      ]
    },
  ]
})

export default router
