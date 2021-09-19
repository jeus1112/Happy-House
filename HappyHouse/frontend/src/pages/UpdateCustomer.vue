<template>
  <div class="page-header clear-filter" filter-color="sky-blue">
    <div class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')"></div>
    <div class="content">
      <div class="container">
        <h1 class="h1-seo" style="font-weight: bold;">회원 수정</h1>
        <div class="col-md-5 ml-auto mr-auto">
          <card type="register" plain>
            <form action method="post" id="_frmForm" name="frmForm" @submit.prevent="updateyMyself">
              <fg-input
                v-model="customer.name"
                label="이름"
                addon-left-icon="nc-icon nc-single-02"
                placeholder="name"
              />
              <fg-input
                v-model="customer.email"
                label="이메일"
                addon-left-icon="nc-icon nc-email-85"
                placeholder="Email"
              />
              <fg-input
                v-model="customer.pass"
                label="비밀번호"
                addon-left-icon="nc-icon nc-key-25"
                placeholder="Pass"
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
                v-model="customer.favorite_place_1"
                label="선호지역"
                addon-left-icon="nc-icon nc-email-85"
                placeholder="favorite_place_1"
              />
              <fg-input
                v-model="customer.favorite_place_2"
                label="선호지역2"
                addon-left-icon="nc-icon nc-email-85"
                placeholder="favorite_place_2"
              />
              <div style="display:flex">
                <button class="btn btn-info btn-round btn-lg btn-block" type="submit" name="button">
                  회원수정
                </button>
                &nbsp;&nbsp;
                <button
                  class="btn btn-round btn-lg btn-block"
                  @click="del(customer.userid)"
                  type="button"
                >
                  탈퇴
                </button>
              </div>
            </form>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Card, Button, FormGroupInput } from '@/components';
import http from '@/http-common';
export default {
  name: 'login-page',
  props: ['user'],
  data() {
    return {
      customer: {
        userid: '',
        name: '',
        email: '',
        pass: '',
        phone: '',
        address: '',
        favorite_place_1: '',
        favorite_place_2: '',
      },
    };
  },
  bodyClass: 'login-page',
  components: {
    Card,

    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
  mounted() {
    console.log(this.user);
    http
      .get('/selectuser/' + this.user)
      .then((response) => {
        console.log(response.data);
        this.customer = response.data;
      })
      .catch(() => {
        this.errored = true;
      })
      .finally(() => (this.loading = false));
  },
  methods: {
    updateyMyself() {
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
      console.log(this.customer.id + 'sdf');
      console.log(this.customer.name + 'name');
      http
        .post('/updateUser', {
          userid: this.user,
          name: this.customer.name,
          email: this.customer.email,
          phone: this.customer.phone,
          address: this.customer.address,
          favorite_place_1: this.customer.favorite_place_1,
          favorite_place_2: this.customer.favorite_place_2,
          pass: this.customer.pass,
        })
        .then((response) => {
          alert('성공적으로 수정 하였습니다.');
          this.$router.push('/');
        })
        .catch(() => {
          alert('수정을 하지 못했습니다.');
          this.visablelogin = false;
          this.submitted = false;
          this.newCustomer();
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
        favorite_place_1: '',
        favorite_place_2: '',
      };
    },
    del(cid) {
      if (cid == 'admin') {
        alert('관리자는 탈퇴가 불가능합니다.');
        return;
      }
      console.log('this' + cid);
      http
        .post('/deleteUser/' + cid)
        .then((response) => {
          console.log(response.data + 'aaaa');

          alert('탈퇴합니다.');
          localStorage.removeItem('userid');
          localStorage.removeItem('pass');
          localStorage.removeItem('name');
          this.$router.push('/');
          this.$router.go('/');
        })
        .catch(() => {
          alert('탈퇴 안됨');
        });
    },
  },
};
</script>
<style></style>
