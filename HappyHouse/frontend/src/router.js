import Vue from 'vue';
import Router from 'vue-router';
import Index from './pages/Index.vue';
import Landing from './pages/Landing.vue';
import Login from './pages/Login.vue';
import Profile from './pages/Profile.vue';
import MainNavbar from './layout/MainNavbar.vue';
import MainFooter from './layout/MainFooter.vue';
import Register from './pages/Register.vue';
import Notice from './pages/Notice.vue';
import Community from './pages/Community.vue';
import Apart from './pages/Apart.vue';
import Dong from './pages/Dong.vue';
import ProductRegi from './pages/ProductRegi.vue';
import ProductList from './pages/ProductList.vue';
import DetailCommu from './pages/DetailCommu.vue';
import Detailproduct from './pages/Detailproduct.vue';
import DetailNotice from './pages/DetailNotice.vue';
import DetailApt from './pages/DetailApt.vue';
import UpdateCustomer from './pages/UpdateCustomer.vue';
import AddNotice from './pages/AddNotice';
import AddBoard from './pages/AddCommu';
import UpdateCommu from './pages/UpdateCommu';
import UpdateNotice from './pages/UpdateNotice';
import CustomerList from './pages/customerlist';
import Interest from './pages/InterestArea';
import RecommendChart from './pages/RecommendChart.vue';
import UpdateProduct from './pages/UpdateProduct.vue';
Vue.use(Router);

export default new Router({
  linkExactActiveClass: 'active',
  routes: [
    {
      path: '/',
      name: 'index',
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/landing',
      name: 'landing',
      components: { default: Landing, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/login',
      name: 'login',
      components: { default: Login, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: '/register',
      name: 'register',
      components: { default: Register, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: '/profile',
      name: 'profile',
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/notice',
      name: 'notice',
      components: { default: Notice, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/community',
      name: 'community',
      components: { default: Community, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/apart',
      name: 'apart',
      components: { default: Apart, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/dong',
      name: 'dong',
      components: { default: Dong, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/productregi',
      name: 'productregi',
      components: { default: ProductRegi, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/productlist',
      name: 'productlist',
      components: { default: ProductList, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/detailcommu/:no',
      name: 'detailcommu',
      components: { default: DetailCommu, header: MainNavbar, footer: MainFooter },
      props: {
        default: true,
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/detailnotice/:no',
      name: 'detailnotice',
      components: { default: DetailNotice, header: MainNavbar, footer: MainFooter },
      props: {
        default: true,
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/detailproduct/:no',
      name: 'detailproduct',
      components: { default: Detailproduct, header: MainNavbar, footer: MainFooter },
      props: {
        default: true,
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/detailapt/:no',
      name: 'detailapt',
      components: { default: DetailApt, header: MainNavbar, footer: MainFooter },
      props: {
        default: true,
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/updatecustomer/:user',
      name: 'updatecustomer',
      components: { default: UpdateCustomer, header: MainNavbar, footer: MainFooter },
      props: {
        default: true,
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/addnotice',
      name: 'addnotice',
      components: { default: AddNotice, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/addboard',
      name: 'addboard',
      components: { default: AddBoard, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/updatecommu',
      name: 'updatecommu',
      components: { default: UpdateCommu, header: MainNavbar, footer: MainFooter },
      props: {
        default: true,
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/updatenotice',
      name: 'updatenotice',
      components: { default: UpdateNotice, header: MainNavbar, footer: MainFooter },
      props: {
        default: true,
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/customerlist',
      name: 'customerlist',
      components: { default: CustomerList, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/interest',
      name: 'interest',
      components: { default: Interest, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/recommendchart',
      name: 'recommendchart',
      components: { default: RecommendChart, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/updateproduct/:no',
      name: 'updateproduct',
      components: { default: UpdateProduct, header: MainNavbar, footer: MainFooter },
      props: {
        default: true,
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
