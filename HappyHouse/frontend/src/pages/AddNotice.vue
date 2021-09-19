<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>
    <div class="container">
      <h3 class="title" style="color:steelblue; margin-left:20px">공지사항 작성</h3>

      <div v-if="!submitted">
        <form action method="post" @submit.prevent="registrynotice">
          <table class="content_table">
            <colgroup>
              <col style="width: 30%;" />
              <col style="width: 70%;" />
            </colgroup>
            <tr>
              <th>사용자 아이디</th>
              <td>admin</td>
            </tr>
            <tr>
              <th>제목</th>
              <td>
                <input
                  data-msg="제목"
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
              <th>내용</th>
              <td>
                <input
                  data-msg="내용"
                  type="text"
                  name="content"
                  id="_content"
                  size="100"
                  v-model="ccontent"
                  style="width: 100%;"
                />
              </td>
            </tr>
            <div v-if="adm">
              <b-button size="sm" class="btn btn-info btn-round" type="submit" name="button"
                >공지사항 등록</b-button
              >
            </div>
          </table>
        </form>
      </div>

      <div v-else>
        <h4>성공적으로 공지사항을 입력하였습니다!</h4>
        <button class="btn btn-success" v-on:click="newNotice">
          새로 공지사항 추가하기
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
      alert('목록으로 돌아갑니다');
      this.$router.push('/notice');
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
        .post('/addNotice', {
          userid: this.user,
          title: this.ctitle,
          content: this.ccontent,
        })
        .then((response) => {
          if (response.data.state == 'succ') {
            alert('공지사항을 등록 하였습니다.');
          } else {
            alert('공지사항을 등록하지 못했습니다.');
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
