import Home from 'views/home'
import SecretQuote from 'views/secret_quote'
import SignIn from 'views/sign_in'
import SignUp from 'views/sign_up'
import About from 'views/about'
import PageNotFound from 'views/not_found'

const routes = [
    { path: "/", component: Home },
    { path: "/secretQuote", component: SecretQuote },
    { path: "/login", component: SignIn },
    { path: "/signUp", component: SignUp },
    { path: "/about", component: About },
    { path: "*", component: PageNotFound }
]

export default routes