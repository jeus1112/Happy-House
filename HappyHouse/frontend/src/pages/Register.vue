<template>
  <div class="page-header clear-filter" filter-color="sky-blue">
    <div class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')"></div>
    <div class="content">
      <div class="container">
        <h1 class="h1-seo" style="font-weight: bold;">Happy House</h1>
        <div class="col-md-5 ml-auto mr-auto">
          <card type="register" plain>
            <form
              action
              method="post"
              id="_frmForm"
              name="frmForm"
              @submit.prevent="registryMyself"
            >
              <fg-input
                v-model="customer.id"
                label="아이디"
                addon-left-icon="nc-icon nc-single-02"
                placeholder="id"
              />
              <fg-input
                v-model="customer.name"
                label="이름"
                addon-left-icon="nc-icon nc-single-02"
                placeholder="Name"
              />
              <fg-input
                v-model="customer.email"
                label="이메일"
                addon-left-icon="nc-icon nc-email-85"
                placeholder="Email"
              />
              <fg-input
                v-model="customer.password"
                label="비밀번호"
                addon-left-icon="nc-icon nc-key-25"
                placeholder="Password"
                type="password"
              />
              <fg-input
                v-model="customer.phone"
                label="phone"
                addon-left-icon="nc-icon nc-email-85"
                placeholder="phone"
              />
              <fg-input
                v-model="customer.address"
                label="주소"
                addon-left-icon="nc-icon nc-email-85"
                placeholder="address"
              />
              <fg-input
                v-model="customer.favorite_place1"
                label="선호지역"
                addon-left-icon="nc-icon nc-email-85"
                placeholder="자주가는 장소를 입력하세요"
              />
              <fg-input
                v-model="customer.favorite_place2"
                label="선호지역2"
                addon-left-icon="nc-icon nc-email-85"
                placeholder="자주가는 장소를 입력하세요"
              />
              <div style="display:flex">
                <button class="btn btn-info btn-round btn-lg btn-block" type="submit" name="button">
                  회원가입
                </button>
                &nbsp;&nbsp;
                <button class="btn btn-round btn-lg btn-block" type="button">
                  로그인으로 가기
                </button>
              </div>
            </form>
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

export default {
  name: 'login-page',
  data() {
    return {
      customer: {
        id: '',
        name: '',
        email: '',
        password: '',
        phone: '',
        address: '',
        favorite_place1: '',
        favorite_place2: '',
      },
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
    registryMyself() {
      if (this.customer.username == '') {
        alert('아이디용이름 입력하세요.');
        return;
      }
      if (this.customer.cname == '') {
        alert('이름을 입력하세요.');
        return;
      }
      if (this.customer.email == '') {
        alert('이메일을 입력하세요.');
        return;
      }
      if (this.customer.password == '') {
        alert('패스워드를 입력하세요.');
        return;
      }
      http
        .post('/addUser', {
          userid: this.customer.id,
          name: this.customer.name,
          email: this.customer.email,
          phone: this.customer.phone,
          address: this.customer.address,
          favorite_place_1: this.customer.favorite_place1,
          favorite_place_2: this.customer.favorite_place2,
          pass: this.customer.password,
        })
        .then((response) => {
          console.log(response.data.state);
          if (response.data.state != null || response.data.state == 'succ') {
            alert('성공적으로 가입 하였습니다. 로그인 하십시오.');
            this.$router.push('/');
          } else {
            alert('a가입을 하지 못했습니다.');
            this.visablelogin = false;
            this.submitted = false;
            this.newCustomer();
          }
        });
    },
    newCustomer() {
      this.customer = {
        id: '',
        name: '',
        email: '',
        password: '',
        phone: '',
        address: '',
        favorite_place1: '',
        favorite_place2: '',
      };
    },
  },
};
</script>
<style></style>
