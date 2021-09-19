<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>
    <div class="container">
      <h3 class="title" style="color:steelblue; margin-left:20px">관심 목록</h3>

      <table class="list_table">
        <colgroup>
          <col style="width: 20%;" />
          <col style="width: 60%;" />
        </colgroup>
        <tr>
          <th>동</th>
          <th>아파트이름</th>
          <th>가격</th>
          <th>삭제</th>
        </tr>
        <tr v-for="i in inter" :key="i.no">
          <td>{{ i.interest_dong }}</td>
          <td>{{ i.interest_place }}</td>
          <td>{{ i.dealAmount }}</td>
          <td>
            <b-button size="sm" class="btn btn-warning btn-round" @click="delete_interest(i.no)"
              >삭제</b-button
            >
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import http from '../http-common';

export default {
  bodyClass: 'profile-page',
  name: 'interest',
  data() {
    return {
      loading: true,
      errored: false,
      inter: [],
    };
  },
  methods: {
    delete_interest: function(no) {
      http
        .post('/deleteInterestArea/' + no)
        .then((response) => {
          if (response.data.state == 'succ') {
            alert('삭제처리를 하였습니다.');
            this.show_interest_list();
          } else {
            alert('삭제처리를 하지 못했습니다.');
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    show_interest_list: function() {
      http
        .get('/interestUserSearch/' + localStorage.getItem('userid'))
        .then((response) => (this.inter = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
  },
  mounted() {
    this.show_interest_list();
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
