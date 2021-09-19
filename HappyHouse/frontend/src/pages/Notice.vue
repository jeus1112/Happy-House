<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>

    <div class="container">
      <div class="row" style=" margin:10px">
        <button class="btn btn-primary btn-round" @click="add_notice()" style="margin-left:1000px">
          추가
        </button>
      </div>
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
                  @click="showNoticeDetail(data.item.noticeno)"
                  >상세보기</b-button
                >
              </template>
              <template #cell(삭제)="data">
                <div v-if="adm">
                  <b-button
                    size="sm"
                    class="btn btn-warning btn-round"
                    @click="delete_notice(data.item.noticeno)"
                    >삭제</b-button
                  >
                </div>
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
    if (this.user == 'admin') {
      this.adm = true;
    }
    this.show_notice_list();
  },
  data() {
    return {
      user: '',
      adm: false,
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
        { key: '상세보기' },
        { key: '삭제' },
      ],
    };
  },
  methods: {
    add_notice: function() {
      this.$router.push('/addnotice');
    },
    showNoticeDetail: function(no) {
      this.$router.push('/detailnotice/' + no);
    },
    new() {
      this.items = [];
    },
    show_notice_list() {
      this.new();
      http
        .get('/noticelist')
        .then((response) => {
          for (var i = 0; i < response.data.length; i++) {
            this.items.push({
              no: i + 1,
              noticeno: response.data[i].no,
              userid: response.data[i].userid,
              title: response.data[i].title,
              content: response.data[i].content,
              regtime: response.data[i].regtime,
            });
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    delete_notice(articleno) {
      http
        .post('/deleteNotice/' + articleno)
        .then((response) => {
          if (response.data.state == 'succ') {
            alert('삭제처리를 하였습니다.');
            this.show_notice_list();
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
