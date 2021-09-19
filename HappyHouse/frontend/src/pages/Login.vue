<template>
  <div class="page-header clear-filter" filter-color="sky-blue">
    <div class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')"></div>
    <div class="content">
      <div class="container">
        <h1 class="h1-seo" style="font-weight: bold;">Happy House</h1>
        <div class="col-md-5 ml-auto mr-auto">
          <card type="login" plain>
            <fg-input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons users_circle-08"
              placeholder="id"
              v-model="userid"
            >
            </fg-input>
            <fg-input
              type="password"
              class="no-border input-lg"
              addon-left-icon="now-ui-icons text_caps-small"
              placeholder="password"
              v-model="pass"
            >
            </fg-input>
            <template slot="raw-content">
              <div class="card-footer text-center">
                <a class="btn btn-primary btn-round btn-lg btn-block" @click="loginsuccess"
                  >Get Started</a
                >
              </div>
              <div class="pull-left">
                <h6>
                  <a href="/register#/register" class="link footer-link">Create Account</a>
                </h6>
              </div>
              <div class="pull-right">
                <h6>
                  <a href="#pablo" class="link footer-link">Need Help?</a>
                </h6>
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
    <main-footer></main-footer>
  </div>
</template>
<script>
import { Card, Button, FormGroupInput } from '@/components';
import MainFooter from '@/layout/MainFooter';
import http from '@/http-common';
import { mapGetters, mapState } from 'vuex';

export default {
  name: 'login-page',
  data() {
    return {
      userid: '',
      pass: '',
    };
  },
  bodyClass: 'login-page',
  components: {
    Card,
    MainFooter,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
  methods: {
    loginsuccess() {
      http
        .post('/login', { userid: this.userid, pass: this.pass })
        .then((response) => {
          if (response.data.userid != null) {
            const userid = response.data.userid;
            const pass = response.data.pass;
            localStorage.setItem('userid', userid);
            localStorage.setItem('pass', pass);
            this.$router.push('/');
            this.$router.go('/');
          } else {
            alert('로그인 불가');
          }
        })
        .catch(() => {
          alert(response.data);
        });
    },
  },
  computed: {
    ...mapGetters(['isAuthenticated', 'isProfileLoaded', 'getProfile']),
    ...mapState({
      authLoading: (state) => state.auth.status === 'loading',
    }),
    loading: function() {
      return this.authStatus === 'loading' && !this.isAuthenticated;
    },
  },
};
</script>
<style></style>
