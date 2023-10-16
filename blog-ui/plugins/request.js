import axios from "axios";

export function request(config) {
    const instance = axios.create({
        baseURL: 'http://yangliu233.com:8080',
        timeout: 10000
    })

    //请求拦截器
    instance.interceptors.request.use(config => {
        config.headers.satoken = localStorage.getItem("token")
        return config
    },error => {
        console.log(error);
    })

    //响应拦截器
    instance.interceptors.response.use(res => {
        return res.data
        // return res.data
    },error => {
        console.log(error);
    })

    //发送真正的网络请求
    return instance(config)

}
