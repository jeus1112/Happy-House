import axios from "axios";

export default axios.create({
  //baseURL: "http://172.30.1.10:8397/api/auth",
  baseURL: "http://localhost:8397/api/",
  headers: {
    "Content-type": "application/json"
  }
});