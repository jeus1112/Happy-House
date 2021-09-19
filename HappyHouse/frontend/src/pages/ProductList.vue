<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>

    <div class="container">
      <h3 class="title" style="color:steelblue; margin-left:20px">매물 목록</h3>

      <div class="row">
        <div class="container center">
          <div class="overflow-auto">
            <b-table
              id="my-table"
              hover
              head-variant="sky-blue"
              :items="items"
              :per-page="perPage"
              :current-page="currentPage"
              small
              :fields="fields"
            >
              <template #cell(상세보기)="data">
                <b-button
                  size="sm"
                  class="btn btn-info btn-round"
                  @click="showProductDetail(data.item.prono)"
                  >상세보기</b-button
                >
              </template>
              <template #cell(삭제)="data">
                <b-button
                  size="sm"
                  class="btn btn-warning btn-round"
                  @click="delete_product(data.item.prono)"
                  >삭제</b-button
                >
              </template>
            </b-table>
            <b-pagination
              v-model="currentPage"
              :total-rows="rows"
              :per-page="perPage"
              aria-controls="my-table"
            ></b-pagination>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '../http-common';
import { Pagination } from '@/components';
export default {
  components: {
    [Pagination.name]: Pagination,
  },
  bodyClass: 'profile-page',
  mounted() {
    this.show_product_list();
  },
  data() {
    return {
      userid: '',
      loading: true,
      errored: false,
      pageArray: [],
      pageNum: 0,
      defaultPagination: 1,
      items: [],
      perPage: 10,
      currentPage: 1,
      fields: [
        { key: 'no' },
        { key: 'userid' },
        { key: 'title' },
        { key: 'price' },
        { key: 'address' },
        { key: 'sale_type' },
        { key: '상세보기' },
        { key: '삭제' },
      ],
    };
  },
  methods: {
    showProductDetail: function(no) {
      this.$router.push('/detailproduct/' + no);
    },
    new() {
      this.items = [];
    },
    show_product_list() {
      this.new();
      http
        .get('/productlist')
        .then((response) => {
          for (var i = 0; i < response.data.length; i++) {
            this.items.push({
              no: i + 1,
              prono: response.data[i].no,
              userid: response.data[i].userid,
              title: response.data[i].title,
              address: response.data[i].address,
              price: response.data[i].price,
              regtime: response.data[i].regtime,
              sale_type: response.data[i].sale_type,
            });
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    delete_product(articleno) {
      http
        .post('/deleteProduct/' + articleno)
        .then((response) => {
          if (response.data.state == 'succ') {
            alert('삭제처리를 하였습니다.');
            this.show_product_list();
          } else {
            alert('삭제처리를 하지 못했습니다.');
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
  },
  computed: {
    rows() {
      return this.items.length;
    },
  },
};
</script>
<style></style>
