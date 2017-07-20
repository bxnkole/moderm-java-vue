
<template>
    <div class="col-sm-6 col-sm-offset-3">
        <h1>Get a Secret Chuck Norris Quote!</h1>
        <button class="btn btn-warning" @click="getQuote()">Get a Quote</button>
        <div class="quote-area" v-if="quote">
            <h2>
                <blockquote>{{ quote }}</blockquote>
            </h2>
        </div>
    </div>
</template>

<script>
import auth from '../auth'
import {API_URL} from '../auth'
import axios from 'axios'

export default {
    data() {
        return {
            quote: ''
        }
    },
    methods: {
        getQuote() {
            axios.get(API_URL + '/user/secretQuote', {
                headers: auth.getAuthHeader()
            })
            .then(response => {
                this.quote = response.data;
            })
            .catch(function (err) {
                console.log(err)
            })
        }
    },
    route: {
        canActivate() {
            return auth.user.authenticated
        }
    }
}
</script>