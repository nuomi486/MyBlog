import axios from "axios";
import {ElMessage} from "element-plus";

axios.defaults.baseURL = "http://localhost:8080";

const authItemName = "authorize";

const accessHeader = () => {
    return {
        'Authorization': `Bearer ${takeAccessToken()}`
    };
}

const defaultError = (error) => {
    console.error(error);
    ElMessage.error('发生了一些错误，请联系管理员');
}

const defaultFailure = (Message, status, url) => {
    console.warn(`请求地址: ${url}, 状态码: ${status}, 错误信息: ${Message}`);
    ElMessage.warning(Message);
}

function takeAccessToken() {
    const str = localStorage.getItem(authItemName) || sessionStorage.getItem(authItemName);
    if(!str) return null;
    const authObj = JSON.parse(str);
    if(authObj.expire <= new Date()) {
        deleteAccessToken();
        ElMessage.warning("登录状态已过期，请重新登录！");
        return null;
    }
    return authObj.token;
}

function storeAccessToken(remember, token, expire, userName){
    const authObj = {
        token: token,
        expire: expire,
        userName: userName
    };
    const str = JSON.stringify(authObj);
    if(remember)
        localStorage.setItem(authItemName, str);
    else
        sessionStorage.setItem(authItemName, str);
}

function deleteAccessToken() {
    localStorage.removeItem(authItemName);
    sessionStorage.removeItem(authItemName);
}

function internalPost(url, data, headers, success, failure, error = defaultError){
    axios.post(url, data, { headers: headers })
        .then(({data}) => {
            if(data.code === 200)
                success(data.data);
            else
                failure(data.message, data.code, url);
    }).catch(err => error(err));
}

function internalGet(url, success, error = defaultError){
    axios.get(url)
        .then(({data}) => {
            if(data.code === 200)
                success(data.data);
            else
                failure(data.message, data.code, url);
    }).catch(err => error(err));
}

function login(username, password, remember, success, failure = defaultFailure){
    internalPost('/login', {
        username: username,
        password: password
    }, {
        'Content-Type': 'application/x-www-form-urlencoded'
    }, (data) => {
        storeAccessToken(remember, data.token, data.expire, data.username);
        ElMessage.success(`登录成功，欢迎 ${data.username} 回来！`);
        success(data);
    }, failure);
}

function logout(failure = defaultFailure){
    Get('/logout', () => {
        deleteAccessToken();
        ElMessage.success(`退出登录成功，欢迎您再次使用`);
    });
}

function Post(url, header, data, success, failure = defaultFailure) {
    const access = accessHeader();
    const config = {
        ...header,
        ...access
    };
    internalPost(url, data, config, success, failure);
}

function Get(url, success) {
   internalGet(url, success);
}

function unauthorized() {
    return takeAccessToken().isEntry;
}

export { Post, Get, login, logout, unauthorized }
