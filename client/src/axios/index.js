import axios from 'axios'
const api = axios.create()

export default {
  // 
  getFileList(data) {
    let {size, page} = data
    return api.get(`/api/document/getpage?size=${size}&page=${page}`)
  },
  // 
  login(data) {
    let {userName, passWord} = data
    return api.get(`/api/login?type=1&name=${userName}&pass=${passWord}`) // 
  },
  // fileupload
  fileupload(data) {
    return api.post(`/api/fileupload`, data)
  },
  filedownload(id) {
    return api.get(`/api/download/${id}`)
  },
  getDepartmentList() {
    return api.get(`/api/department/getall`)
  }
}