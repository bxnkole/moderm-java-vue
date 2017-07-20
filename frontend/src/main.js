import 'bootstrap/css/bootstrap.min.css'
import 'bootstrap/css/bootstrap-theme.min.css'
import 'assets/css/public.css'

import Vue from 'vue'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'

import app from 'src/app'
import auth from 'src/auth'
import routes from 'src/routes'

Vue.use(VueResource);
Vue.use(VueRouter);

Vue.http.options.emulateJSON = true;
Vue.http.options.emulateHTTP = true;

auth.checkAuth();

export const router = new VueRouter({
    hashbang: false,
    history: true,
    mode: 'html5',
    routes: routes
});

router.beforeEach(function(transition) {
    if (transition.to.path === '/forbidden') {
        router.app.authenticating = true;
        setTimeout(() => {
            router.app.authenticating = false;
            alert('this route is forbidden by a global before hook');
            transition.abort()
        }, 3000)
    } else {
        transition.next()
    }
})

new Vue({
    router,
    render: function(createElement) {
        createElement(app);
    }
}).$mount('#app');