<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>
    <div class="container">
      <h3 class="title" style="color:steelblue; margin-left:20px">글 수정</h3>
      <form action method="post" @submit.prevent="update_board">
        <table
          class="content_table"
          style="color:dimgray; margin:20px;margin-left:50px; width: 90%"
        >
          <colgroup>
            <col style="width: 20%;" />
            <col style="width: 60%;" />
          </colgroup>
          <tr>
            <th>No.</th>
            <td>{{ cboard.no }}</td>
          </tr>
          <tr>
            <th>사용자 이름</th>
            <td>{{ cboard.uid }}</td>
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
                v-model="cboard.tit"
                style="width: 100%;"
                value="dt"
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
                v-model="cboard.cont"
                style="width: 100%;"
              />
            </td>
          </tr>
          <b-button size="sm" class="btn btn-info btn-round" type="submit" name="button"
            >공지사항 수정</b-button
          >
        </table>
      </form>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import http from '../http-common';

export default {
  name: 'updatenotice',
  props: ['notice'],
  data() {
    return {
      cboard: {
        no: this.$route.params.no,
        uid: this.$route.params.userid,
        tit: this.$route.params.title,
        cont: this.$route.params.content,
      },
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
  bodyClass: 'profile-page',

  mounted() {},
  methods: {
    go_list: function() {
      alert('게시판으로 돌아갑니다');
      this.$router.push('/notice');
    },
    update_board() {
      if (this.cboard.tit == '') {
        alert('제목을 입력하세요.');
        return;
      }
      if (this.cboard.cont == '') {
        alert('내용을 입력하세요.');
        return;
      }

      http
        .post('/modifyNotice', {
          no: this.cboard.no,
          userid: this.cboard.uid,
          title: this.cboard.tit,
          content: this.cboard.cont,
        })
        .then((response) => {
          if (response.data.state == 'succ') {
            alert('수정 하였습니다.');
            this.$router.push('/');
          } else {
            alert('수정하지 못했습니다.');
          }
        });
      this.submitted = true;
    },
    newBoard() {
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
