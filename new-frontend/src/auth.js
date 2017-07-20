import router from './router'
import axios from 'axios'

const API_URL = 'http://localhost:8081/api'
const LOGIN_URL = API_URL + '/user/login'
const SIGNUP_URL = API_URL + '/user/signUp'

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

export { API_URL }

export default {
    user: {
        authenticated: false
    },

    login(caller, creds, redirect) {
        console.log(creds);

        axios.post(LOGIN_URL, creds)

        .then(response => {
            localStorage.setItem('id_token', response.headers.authorization);

            this.user.authenticated = true;

            if (redirect) {
                router.push(redirect)
            }
        })

        .catch(function(error) {
            caller.error = error.message
        })
    },

    signUp(caller, creds, redirect) {
        axios.post(SIGNUP_URL, creds)

        .then(function(response) {
            localStorage.setItem('id_token', response.headers.authorization);

            this.user.authenticated = true;

            if (redirect) {
                router.go(redirect)
            }
        })

        .catch(function(error) {
            caller.error = error.message
        })
    },

    logout() {
        localStorage.removeItem('id_token');
        this.user.authenticated = false
    },

    checkAuth() {
        const jwt = localStorage.getItem('id_token');
        this.user.authenticated = !!jwt
    },

    getAuthHeader() {
        return {
            'Authorization': 'Bearer ' + localStorage.getItem('id_token')
        }
    }
}