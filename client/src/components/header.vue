<template>
  <a-layout>
    <a-layout-header class="header">
        <a-menu v-model="current" mode="horizontal" class="menu">
          <a-menu-item key="index">
            <router-link to="/home"><a-icon type="appstore"/>首页</router-link>
          </a-menu-item>
          <a-menu-item key="recycling">
            <router-link to="/recycle"><a-icon type="delete"/>回收站</router-link>
          </a-menu-item>
          <a-menu-item key="app">
            <router-link to="/upload"><a-icon type="upload"/>上传文件</router-link>
          </a-menu-item>
        </a-menu>
        <div v-if="isLogin">
          <span>{{userInfo.userName}}</span>
          <a-button type="danger" @click="logout">退出登录</a-button>
        </div>
    </a-layout-header>
  </a-layout>
</template>
<script>
const colorList = ['#f56a00', '#7265e6', '#ffbf00', '#00a2ae']
export default {
  data() {
    return {
      current: ["index"],
      colorList: ['#f56a00', '#7265e6', '#ffbf00', '#00a2ae'],
      // color: colorList[0]
    };
  },
  methods: {
    changeAvatarColor() {
      this.colorList.sort((a,b) => {
        return Math.random() > 0.5 ? 1 : -1
      })
    },
    logout() {
      this.$store.commit("remove")
      this.$router.push('/login')
    }
  },
  computed: {
    isLogin() {
      return this.$store.state.login
    },
    userInfo() {
      return this.$store.state.user
    }
  },
  created() {

  }
};
</script>

<style scoped>
.header {
  background-color: #fff;
  padding: 0 100px;
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
}
.menu {
  height: 64px;
  line-height: 64px;
}
.avatar {
  user-select: none;
  cursor: pointer;
}
</style>

