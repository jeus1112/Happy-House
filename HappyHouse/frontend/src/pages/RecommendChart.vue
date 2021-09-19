<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>
    <div class="container">
      <h3 style="text-align:center">구 별 학교 수</h3>
      <bar-chart></bar-chart>
      <p style="text-align:center">
        <b>1위 : 노원구 (98개) | 2위 : 송파구 (91개) | 3위 : 강남구 (82개)</b>
      </p>
      <br />
      <h3 style="text-align:center">구 별 경찰서 & 소방서 수</h3>
      <line-chart></line-chart>
      <p style="text-align:center">
        <b>1위 : 종로구 (24개) | 2위 : 중구 (18개) | 3위 : 강남구 (17개)</b>
      </p>
    </div>
  </div>
</template>

<script>
import BarChart from '@/components/BarChart';
import LineChart from '@/components/LineChart';
import http from '../http-common';

export default {
  bodyClass: 'profile-page',
  components: {
    BarChart,
    LineChart,
  },
  name: 'chart-customer',
  data() {
    return {
      deptid2: 0,
      depcounts: [],
      loading: true,
      errored: false,
      info: [],
    };
  },
  methods: {},
  mounted() {},
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
svg rect {
  fill: skyblue;
}
svg text {
  fill: yellow;
  font: 12px sans-serif;
  text-anchor: end;
}
</style>
