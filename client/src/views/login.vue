<template>
  <div class="login-page">
    <div class="content">
      <div class="info">
        <a-select v-model="userName" style="width: 100%">
          <a-icon slot="suffixIcon" type="user"/>
          <a-select-option
            v-for="item in departmentList"
            :value="item.name"
            :key="item.id"
          >{{item.name}}</a-select-option>
        </a-select>
        <a-input placeholder="密码？" size="large" v-model="passWord" class="inp" type="password">
          <a-icon slot="prefix" type="lock"/>
        </a-input>
      </div>

      <div class="type">
        <a-radio-group defaultValue="user" buttonStyle="solid" v-model="type">
          <a-radio-button value="user">用户</a-radio-button>
          <a-radio-button value="root">管理员</a-radio-button>
        </a-radio-group>
        <a-button type="primary" @click="login">Login</a-button>
      </div>

      <!-- <a-button type="danger" size="large" @click="login">Login</a-button> -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      departmentList: [],
      userName: "研发部",
      passWord: "7777777",
      type: "user"
    };
  },
  methods: {
    async login() {
      if (this.userName == "" || this.passWord == "") {
        this.$message.error("输入的信息不完整！！！");
        return;
      }
      let params = { userName: this.userName, passWord: this.passWord };
      let { data, status } = await this.axios.login(params);
      // console.log(data);
      if (status === 200 && data.code == 0) {
        this.$message.success("登录成功！");
        this.$store.commit("add", { userName: this.userName });
        this.$route.query.redirect
          ? this.$router.replace({
              path: this.$route.query.redirect
            })
          : this.$router.replace({
              path: "/home"
            });
      } else {
        this.$message.error("用户名或密码错误");
      }
    },
    async getDepartment() {
      let { data, status } = await this.axios.getDepartmentList();
      if (status === 200 && data.code == 0) {
        this.departmentList = data.result;
      }
    }
  },
  created() {
    this.getDepartment();
  }
};
</script>

<style scoped>
.login-page {
  width: 100%;
  height: 100%;
  background-image: url("./../../static/image/2.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}
.content {
  width: 350px;
  height: 300px;
  padding: 20px;
  position: absolute;
  top: 50%;
  left: 50%;
  /* border: 1px solid #2e2e2e; */
  border-radius: 10px;
  transform: translate(-50%, -50%);
}
.info .inp {
  margin: 10px 0;
}
.type {
  margin: 10px 0;
  display: flex;
  justify-content: space-between;
}
</style>

