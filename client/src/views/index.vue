<template>
  <div class="index-page">
    <a-row>
      <a-col span="20">
        <a-table :columns="columns" :dataSource="resData" :bordered="showBorder" :pagination="showPagination">
          <span slot="tags" slot-scope="tags">
            <a-tag v-for="tag in tags" color="blue" :key="tag">{{tag}}</a-tag>
          </span>
          <span slot="action" slot-scope="text"> <!-- slot-scope="text, record" -->
            <a-button type="primary" icon="download" size="small">
              <a style="color: white" :href='"/api/download/"+text.id'>下载</a>
            </a-button>
          </span>
        </a-table>
        <div class="loadmore">
          <a-icon type="loading" v-if="loading" />
          <a-button type="primary" v-else @click="getList">{{loadMessage}}</a-button>
        </div>
      </a-col>
      <a-col span="4">
        <hot-tag></hot-tag>
        <hot-file></hot-file>
      </a-col>
    </a-row>
  </div>
</template>
<script>
import HotTag from "@/components/HotTag";
import HotFile from "@/components/HotFile";
const columns = [
  {
    title: "文件名",
    dataIndex: "name",
    key: "name",
    slots: { title: "customTitle" },
    scopedSlots: { customRender: "date" }
  },
  {
    title: "部门",
    dataIndex: "depart_id",
    key: "depart_id"
  },
  // {
  //   title: "标签",
  //   key: "tags",
  //   dataIndex: "tags",
  //   scopedSlots: { customRender: "tags" }
  // },
  {
    title: "时间",
    key: "date",
    dataIndex: "date",
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  },
  {
    title: "备注",
    key: "remarks",
    dataIndex: "remarks"
  }
];

export default {
  data() {
    return {
      data: [],
      columns,
      showBorder: true, // 显示边框
      showPagination: false, // 显示分页
      loading: false,
      loadMessage: "加载更多...",
      page: 0,
      size: 8,
      hasMore: true
    };
  },
  methods: {
    async downLoad(id) {
      let data = await this.axios.filedownload(id)
    },
    async getList() {
      if (!this.hasMore) {
        return
      }
      this.loading = true
      this.page++
      let {data, status} = await this.axios.getFileList({size: this.size, page: this.page})
      if (status === 200 && data.code == 0) {
        data.result.pop()
        this.data = data.result.concat(this.data)
        this.loading = false
        if (data.result.length < 8) {
          this.loadMessage = "没有更多了..."
          this.hasMore = false
        }
      }
    }
  },
  computed: {
    resData() {
      let res = []
      let data = this.data
      for (let i = 0; i < data.length; i++) {
        let key = data[i].id
        data[i].key = key
        let item = data[i]
        res.push(item)
      }
      return res
    }
  },
  components: {
    HotTag,
    HotFile
  },
  async created() {
    this.getList()
  },
};
</script>

<style scoped>
.index-page {
  margin: 0 80px;
}
.loadmore {
  text-align: center;
  color: red; 
  font-size: 30px;
  margin: 20px 0;
}
</style>

