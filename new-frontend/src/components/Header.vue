<template>
    <nav class="navbar navbar-default">
        <div class="container">
            <ul class="nav navbar-nav">
                <li><router-link to="/">Home</router-link></li>
                <li v-if="!user.authenticated"><router-link to="login">Login</router-link></li>
                <li v-if="!user.authenticated"><router-link to="signUp">Sign Up</router-link></li>
                <li v-if="!user.authenticated"><router-link to="about">About</router-link></li>
                <li v-if="user.authenticated"><router-link to="secretQuote">Secret Quote</router-link></li>
                <li v-if="user.authenticated"><a @click="logout()">Logout</a></li>
            </ul>
        </div>
    </nav>
</template>

<script>
    import auth from '../auth'

    export default {
        data() {
            return {
                user: auth.user
            }
        },
        methods: {
            logout() {
                auth.logout()
            }
        }
    };
</script>