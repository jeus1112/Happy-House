<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px; color:white;">Happy House</h2>
    </div>
    <div class="container">
      <h3 class="title" style="color:steelblue; margin-left:20px">글 수정</h3>
      <form action method="post" @submit.prevent="update_board">
        <table
          class="content_table"
          style="color:dimgray; margin:20px;margin-left:50px; width: 90%"
        >
          <colgroup>
            <col style="width: 20%;" />
            <col style="width: 60%;" />
          </colgroup>

          <tr>
            <th>No.</th>
            <td>{{ cproduct.no }}</td>
          </tr>
          <tr>
            <th>사용자 이름</th>
            <td>{{ cproduct.useid }}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
              <input
                data-msg="제목"
                type="text"
                name="title"
                id="_title"
                size="100"
                v-model="cproduct.title"
                style="width: 100%;"
                value="dt"
              />
            </td>
          </tr>
          <tr>
            <th>주소</th>
            <td>
              <input
                data-msg="제목"
                type="text"
                name="title"
                id="_title"
                size="100"
                v-model="cproduct.address"
                style="width: 100%;"
                value="dt"
              />
            </td>
          </tr>
          <tr>
            <th>가격</th>
            <td>
              <input
                data-msg="제목"
                type="text"
                name="title"
                id="_title"
                size="100"
                v-model="cproduct.price"
                style="width: 100%;"
                value="dt"
              />
            </td>
          </tr>
          <tr>
            <th>매매종류</th>
            <td>
              <input
                data-msg="제목"
                type="text"
                name="title"
                id="_title"
                size="100"
                v-model="cproduct.sale_type"
                style="width: 100%;"
                value="dt"
              />
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <input
                data-msg="내용"
                type="text"
                name="content"
                id="_content"
                size="100"
                v-model="cproduct.content"
                style="width: 100%;"
              />
            </td>
          </tr>
          <b-button size="sm" class="btn btn-info btn-round" type="submit" name="button"
            >커뮤니티 수정</b-button
          >
        </table>
      </form>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import http from '../http-common';

export default {
  name: 'updateproduct',
  props: ['no'],
  bodyClass: 'profile-page',

  data() {
    return {
      cproduct: {},
      info: null,
      loading: true,
      errored: false,
      deps: null,
      titls: null,
      ctitle: '',
      ccontent: '',
      submitted: false,
    };
  },
  mounted() {
    http
      .get('/getProduct/' + this.no)
      .then((response) => (this.cproduct = response.data))
      .catch(() => {
        this.errored = true;
      })
      .finally(() => (this.loading = false));
  },
  methods: {
    go_list: function() {
      alert('게시판으로 돌아갑니다');
      this.$router.push('/productlist');
    },
    update_board() {
      if (this.cproduct.tit == '') {
        alert('제목을 입력하세요.');
        return;
      }
      if (this.cproduct.cont == '') {
        alert('내용을 입력하세요.');
        return;
      }

      http
        .post('/updateProduct', {
          no: this.cproduct.no,
          userid: this.cproduct.userid,
          title: this.cproduct.title,
          address: this.cproduct.address,
          price: this.cproduct.price,
          sale_type: this.cproduct.sale_type,
          content: this.cproduct.content,
        })
        .then((response) => {
          if (response.data.state == 'succ') {
            alert('수정 완료');
            this.$router.push('/productlist');
          } else {
            alert('수정하지 못 했습니다.');
          }
        });
      this.submitted = true;
    },
    newProduct() {
      this.submitted = false;
      this.info = null;
      this.loading = true;
      this.errored = false;
      this.deps = null;
      this.titls = null;
      this.ctitle = '';
      this.ccontent = '';
    },
  },
  computed: {
    ...mapGetters(['getProfile']),
  },
};
</script>

<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>
