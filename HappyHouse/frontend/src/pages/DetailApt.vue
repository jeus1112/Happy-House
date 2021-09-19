<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>
    <div class="container">
      <table class="list_table">
        <colgroup>
          <col style="width: 30%;" />
          <col style="width: 70%;" />
        </colgroup>
        <tr>
          <th>No.</th>
          <td>{{ apt.no }}</td>
        </tr>
        <tr>
          <th>동</th>
          <td>{{ apt.dong }}</td>
        </tr>
        <tr>
          <th>아파트이름</th>
          <td>{{ apt.aptname }}</td>
        </tr>
        <tr>
          <th>시구군코드</th>
          <td>{{ apt.code }}</td>
        </tr>
        <tr>
          <th>거래금액</th>
          <td>{{ apt.dealAmount }}</td>
        </tr>
        <tr>
          <th>건설연도</th>
          <td>{{ apt.buildYear }}</td>
        </tr>
        <tr>
          <th>거래 날째</th>
          <td>{{ apt.dealYear }}년 {{ apt.dealMonth }}월 {{ apt.dealDay }}일</td>
        </tr>
        <tr>
          <th>면적</th>
          <td>{{ apt.area }}</td>
        </tr>
        <tr>
          <th>층수</th>
          <td>{{ apt.floor }}</td>
        </tr>
        <tr>
          <th>지번</th>
          <td>{{ apt.jibun }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import http from '../http-common';

export default {
  bodyClass: 'profile-page',
  name: 'detailapt',
  props: ['no'],
  data() {
    return {
      qna: [],
      loading: true,
      errored: false,
      cid: '',
      apt: {},
    };
  },
  methods: {
    show_init: function() {
      http
        .get('/housedealsearch/' + this.no)
        .then((response) => (this.apt = response.data))
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
