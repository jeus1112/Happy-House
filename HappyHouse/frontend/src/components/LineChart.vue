<script>
import { Line } from 'vue-chartjs';

import http from '../http-common';
export default {
  extends: Line,
  data() {
    return {
      tmp: [],
      chartData: {
        labels: [],
        datasets: [
          {
            label: 'Line Chart',
            data: [],
            fill: false,
            borderColor: '#2554FF',
            backgroundColor: '#2554FF',
            borderWidth: 1,
          },
        ],
      },
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
              },
              gridLines: {
                display: true,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
        legend: {
          display: true,
        },
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },
  methods: {},
  mounted() {
    // this.divide();
    this.renderChart(this.chartData, this.options);
  },
  created() {
    http
      .get('/stationCount')
      .then((response) => {
        this.tmp = response.data;
        // console.log(this.tmp.length, 'dafsdf');
        for (var i = 0; i < this.tmp.length; i++) {
          // var a = this.tmp[i].gu;
          // this.chartData.labels[i] = this.tmp[i].gu;
          // this.chartData.datasets.data[i] = this.tmp[i].cnt;
          // console.log(this.chartData.labels[i]);
          // console.log(this.tmp[i].gu);
          // console.log(a);
          this.chartData.labels.push(this.tmp[i].gu);
          this.chartData.datasets[0].data.push(this.tmp[i].cnt);
        }
        console.log(this.chartData.labels.length, 'ddd');
        console.log(this.chartData.datasets[0].data.length, 'ddd2');
      })
      .catch(() => {
        this.errored = true;
      })
      .finally(() => (this.loading = false));
  },
};
</script>
