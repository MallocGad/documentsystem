<template>
  <div class="up-page">
    <div class="content">
      <input type="file" @change="getFile" class="file-inp item-inp" ref="fileInput">
      
      <a-input placeholder="部门id？" size="large" v-model="depart_id" class="inp item-inp">
        <a-icon slot="prefix" type="idcard" />
      </a-input>
      <a-input placeholder="文件等级？" size="large" v-model="readclass" class="middle-inp item-inp">
        <a-icon slot="prefix" type="sound" />
      </a-input>
      <a-textarea placeholder="备注信息？" :rows="4" v-model="remarks" class=" item-inp"/>
      
      <a-button type="primary" @click="submit" size="large">上传</a-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      file: "",
      depart_id: 1,
      readclass: "123",
      remarks: "123",
      select: false
    };
  },
  methods: {
    getFile(event) {
      this.file = event.target.files[0];
      this.select = true
    },
    async submit(event) {
      if (!this.select) {
        return this.$message.error('还没有选择文件');
      }
      let formData = new FormData();
      formData.append("file", this.file);
      formData.append("depart_id", this.depart_id);
      formData.append("readclass", this.readclass);
      formData.append("remarks", this.remarks);
      let {data, status} = await this.axios.fileupload(formData);
      if (status === 200 && data.code === 0) {
        this.$message.success('上传成功');
      } else {
        this.$message.error('上传失败');
      }
      this.$refs.fileInput.value = ''
    }
  }
};
</script>

<style scoped>
.content {
  width: 500px;
  height: 500px;
  padding: 20px;
  /* border: 1px solid red; */
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.item-inp {
  margin: 10px 0;
}
</style>

