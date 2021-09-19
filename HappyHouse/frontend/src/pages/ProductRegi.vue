<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>
    <div class="container">
      <h3 class="title" style="color:steelblue; margin-left:20px">매물 등록</h3>

      <div v-if="!submitted">
        <form action method="post" @submit.prevent="registrynotice">
          <table class="content_table">
            <colgroup>
              <col style="width: 30%;" />
              <col style="width: 70%;" />
            </colgroup>
            <tr>
              <th>아이디</th>
              <td>{{ this.user }}</td>
            </tr>
            <tr>
              <th>매물 이름</th>
              <td>
                <input
                  data-msg="이름"
                  type="text"
                  name="title"
                  id="_title"
                  size="50"
                  v-model="ctitle"
                  style="width: 100%;"
                  placeholder=""
                />
              </td>
            </tr>
            <tr>
              <th>종류</th>
              <td>
                <input
                  data-msg="종류"
                  type="text"
                  name="kind"
                  id="_kind"
                  size="100"
                  v-model="ckind"
                  style="width: 100%;"
                />
              </td>
            </tr>
            <tr>
              <th>주소</th>
              <td>
                <input
                  data-msg="내용"
                  type="text"
                  name="address"
                  id="_address"
                  size="100"
                  v-model="caddress"
                  style="width: 100%;"
                />
              </td>
            </tr>
            <tr>
              <th>가격</th>
              <td>
                <input
                  data-msg="가격"
                  type="text"
                  name="price"
                  id="_price"
                  size="100"
                  v-model="cprice"
                  style="width: 100%;"
                />
              </td>
            </tr>
            <tr>
              <th>상세정보</th>
              <td>
                <input
                  data-msg="정보"
                  type="text"
                  name="content"
                  id="_content"
                  size="100"
                  v-model="ccontent"
                  style="width: 100%;"
                />
              </td>
            </tr>
            <b-button size="sm" class="btn btn-info btn-round" type="submit" name="button"
              >매물 등록</b-button
            >
          </table>
        </form>
      </div>

      <div v-else>
        <h4>성공적으로 매물을 입력하였습니다!</h4>
        <button class="btn btn-success" v-on:click="newNotice">
          새로 매물 추가하기
        </button>
        <button class="btn btn-info" v-on:click="go_list()">
          목록으로 돌아가기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import http from '../http-common';

export default {
  name: 'add-notice',
  bodyClass: 'profile-page',

  data() {
    return {
      user: localStorage.getItem('userid'),
      adm: false,
      info: null,
      loading: true,
      errored: false,
      deps: null,
      titls: null,
      ctitle: '',
      cprice: '',
      caddress: '',
      ckind: '',
      ccontent: '',
      submitted: false,
    };
  },
  mounted() {
    if (this.user == 'admin') {
      this.adm = true;
    }
  },
  methods: {
    go_list: function() {
      alert('매물 게시판으로 돌아갑니다');
      this.$router.push('/productlist');
    },
    registrynotice() {
      if (this.ctitle == '') {
        alert('제목을 입력하세요.');
        return;
      }
      if (this.ccontent == '') {
        alert('내용을 입력하세요.');
        return;
      }

      http
        .post('/addProduct', {
          userid: this.user,
          title: this.ctitle,
          content: this.ccontent,
          address: this.caddress,
          price: this.cprice,
          sale_type: this.ckind,
        })
        .then((response) => {
          console.log(response.data);
          if (response.data.state == 'succ') {
            alert('매물을 등록 하였습니다.');
          } else {
            alert('매물을 등록하지 못했습니다.');
          }
        });
      this.submitted = true;
    },
    newNotice() {
      this.submitted = false;
      this.info = null;
      this.loading = true;
      this.errored = false;
      this.deps = null;
      this.titls = null;
      this.ctitle = '';
      this.ccontent = '';
    },
  },
  computed: {
    ...mapGetters(['getProfile']),
  },
};
</script>

<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>
