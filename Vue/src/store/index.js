import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 设置需要的状态变量和方法
// 使用对象来方便以后扩展

export default new Vuex.Store({
    state:{
        user:{
            // 打开项目先检查本地存储中是否存在 user
            username: window.localStorage.getItem('user' || '[]') == null ? '' :JSON.parse(window.localStorage.getItem('user' || '[]')).username
        }
    },
    mutations: {
        login (state, user){
            state.user = user
            window.localStorage.setItem('user', JSON.stringify(user));
        }
    }
})