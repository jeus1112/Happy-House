<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>

    <div class="container">
      <div style="margin-top:20px">
        <n-button @click="update_board()" style=" color:steelblue; margin-left:900px; "
          >수정하기</n-button
        >
        <n-button @click="go_list()" style=" color:steelblue; margin-left:30px; "
          >목록보기</n-button
        >
      </div>
      <table class="list_table" style="color:dimgray; margin:20px;margin-left:50px; width: 90%">
        <colgroup>
          <col style="width: 20%;" />
          <col style="width: 60%;" />
        </colgroup>
        <tr>
          <th>No.</th>
          <td>{{ board.no }}</td>
        </tr>
        <tr>
          <th>사용자 이름</th>
          <td>{{ board.userid }}</td>
        </tr>
        <tr>
          <th>제목</th>
          <td>{{ board.title }}</td>
        </tr>
        <tr>
          <th>내용</th>
          <td>{{ board.content }}</td>
        </tr>
        <tr>
          <th>날짜</th>
          <td>{{ board.regtime }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import http from '../http-common';

export default {
  name: 'detailcommu',
  props: ['no'],
  data() {
    return {
      loading: true,
      errored: false,
      board: {},
    };
  },
  bodyClass: 'profile-page',

  methods: {
    update_board: function() {
      console.log(this.board);
      this.$router.push({
        name: 'updatecommu',
        params: {
          no: this.board.no,
          userid: this.board.userid,
          title: this.board.title,
          content: this.board.content,
          regtime: this.board.regtime,
        },
      });
    },
    go_list: function() {
      alert('게시판으로 돌아갑니다');
      this.$router.push('/community');
    },
    show_init: function() {
      http
        .get('/getBoard/' + this.no)
        .then((response) => (this.board = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
  },
  mounted() {
    this.show_init();
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
