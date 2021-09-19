<script>
import { Bar } from 'vue-chartjs';
import http from '../http-common';
export default {
  extends: Bar,
  data() {
    return {
      tmp: [],
      chartData: {
        labels: [],
        datasets: [
          {
            label: 'Bar Chart',
            borderWidth: 1,
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)',
              'rgba(255, 99, 132, 0.2)',
            ],
            borderColor: [
              'rgba(255,99,132,1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)',
              'rgba(255,99,132,1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)',
              'rgba(255,99,132,1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)',
              'rgba(255,99,132,1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)',
              'rgba(255,99,132,1)',
            ],
            pointBorderColor: '#2554FF',
            data: [],
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
  methods: {
    // divide() {
    //   http
    //   .get('/schoolCount')
    //   .then((response) => {
    //     this.tmp = response.data;
    //     console.log(this.tmp.length);
    //   })
    //   .catch(() => {
    //     this.errored = true;
    //   })
    //   .finally(() => (this.loading = false));
    //   var la = [];
    //   var da = [];
    //   console.log(this.tmp.length);
    //   for (var i = 0; i < this.tmp.length; i++) {
    //     // var a = this.tmp[i].gu;
    //     // this.chartData.labels[i] = this.tmp[i].gu;
    //     // this.chartData.datasets.data[i] = this.tmp[i].cnt;
    //     // console.log(this.chartData.labels[i]);
    //     // console.log(this.tmp[i].gu);
    //     // console.log(a);
    //     la.push(this.tmp[i].gu);
    //   }
    //   this.chartData.labels = la;
    //   console.log(this.chartData.labels.length);
    // },
  },
  mounted() {
    // this.divide();
    this.renderChart(this.chartData, this.options);
  },
  created() {
    http
      .get('/schoolCount')
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
