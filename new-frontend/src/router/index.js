import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home'
import SecretQuote from '../components/SecretQuote'
import SignIn from '../components/SignIn'
import SignUp from '../components/SignUp'
import About from '../components/About'
import PageNotFound from '../components/PageNotFound'

Vue.use(Router)

const routes = [
    { name: "home", path: "/", component: Home },
    { name: "secretQuote", path: "/secretQuote", component: SecretQuote },
    { name: "login", path: "/login", component: SignIn },
    { name: "signUp", path: "/signUp", component: SignUp },
    { name: "about", path: "/about", component: About },
    { path: "*", component: PageNotFound }
]

const router = new Router({
    routes: routes,
    mode: "history"
})

router.beforeEach(function(to, from, next) {
    if (to.path === '/forbidden') {
        router.app.authenticating = true;
        setTimeout(() => {
            router.app.authenticating = false;
            alert('this route is forbidden by a global before hook');
            next(false)
        }, 3000)
    } else {
        next()
    }
})

export default router