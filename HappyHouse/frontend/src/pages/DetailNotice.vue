<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>
    <div class="container">
      <div style="margin-top:20px">
        <div v-if="adm">
          <n-button @click="update_notice()" style=" color:steelblue; margin-left:900px; "
            >수정하기</n-button
          >
          <n-button @click="go_list()" style=" color:steelblue; margin-left:30px; "
            >목록보기</n-button
          >
        </div>
      </div>
      <table class="list_table" style="color:dimgray; margin:20px;margin-left:50px; width: 90%">
        <colgroup>
          <col style="width: 20%;" />
          <col style="width: 60%;" />
        </colgroup>
        <tr>
          <th>No.</th>
          <td>{{ notice.no }}</td>
        </tr>
        <tr>
          <th>아이디</th>
          <td>{{ notice.userid }}</td>
        </tr>
        <tr>
          <th>제목</th>
          <td>{{ notice.title }}</td>
        </tr>
        <tr>
          <th>내용</th>
          <td>{{ notice.content }}</td>
        </tr>
        <tr>
          <th>날짜</th>
          <td>{{ notice.regtime }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import http from '../http-common';

export default {
  name: 'detailnotice',
  props: ['no'],
  data() {
    return {
      user: localStorage.getItem('userid'),
      adm: false,
      loading: true,
      errored: false,
      notice: {},
    };
  },
  bodyClass: 'profile-page',
  methods: {
    update_notice: function() {
      this.$router.push({
        name: 'updatenotice',
        params: {
          no: this.notice.no,
          userid: this.notice.useid,
          title: this.notice.title,
          content: this.notice.content,
          regtime: this.notice.regtime,
        },
      });
    },
    go_list: function() {
      alert('게시판으로 돌아갑니다');
      this.$router.push('/community');
    },
    show_init: function() {
      http
        .get('/getNotice/' + this.no)
        .then((response) => (this.notice = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
  },
  mounted() {
    this.show_init();
    if (this.user == 'admin') {
      this.adm = true;
    }
  },
};
</script>

<style>
.searchform {
  max-width: 300px;
  margin: auto;
}
.search-result {
  margin-top: 20px;
  text-align: left;
}
</style>
