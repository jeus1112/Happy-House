<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>

    <div class="container">
      <h3 class="title" style="color:steelblue; margin-left:20px">회원 목록</h3>

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
              <template #cell(삭제)="data">
                <b-button
                  size="sm"
                  class="btn btn-warning btn-round"
                  @click="delete_qna(data.item.userid)"
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
    this.show_board_list();
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
        { key: 'name' },
        { key: 'phone' },
        { key: 'address' },
        { key: '삭제' },
      ],
    };
  },
  methods: {
    new() {
      this.items = [];
    },

    show_board_list() {
      this.new();
      http
        .get('/userlist')
        .then((response) => {
          for (var i = 0; i < response.data.length; i++) {
            this.items.push({
              no: i + 1,
              userid: response.data[i].userid,
              name: response.data[i].name,
              phone: response.data[i].phone,
              address: response.data[i].address,
              regtime: response.data[i].regtime,
            });
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    delete_qna(articleno) {
      if (articleno == 'admin') {
        alert('관리자는 삭제가 불가능합니다.');
        return;
      }
      alert(articleno + '가 삭제되었습니다.');
      http
        .post('/deleteUser/' + articleno)
        .then((response) => {
          if (response.data.state == 'succ') {
            alert('삭제처리를 하였습니다.');
            this.show_board_list();
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
