<template>
  <div id="app">
    <nav-header v-if="isLogin"></nav-header>
    <router-view/>
  </div>
</template>

<script>
import NavHeader from '@/components/header.vue'
export default {
  name: 'App',
  components: {
    NavHeader
  },
  computed: {
    isLogin() {
      return this.$store.state.login
    }
  },
}
</script>

<style scoped>
  #app {
    width: 100%;
    height: 100%;
  }
</style>

