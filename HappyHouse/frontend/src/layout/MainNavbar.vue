<template>
  <navbar
    position="fixed"
    type="info"
    :transparent="transparent"
    :color-on-scroll="colorOnScroll"
    menu-classes="ml-auto"
  >
    <template>
      <router-link v-popover:popover1 class="navbar-brand" to="/">
        Happy House
      </router-link>
      <el-popover
        ref="popover1"
        popper-class="popover"
        placement="bottom"
        width="200"
        trigger="hover"
      >
        <div class="popover-body">
          해피하우스 홈페이지에 오신걸 환영합니다.
        </div>
      </el-popover>
    </template>
    <template slot="navbar-menu">
      <div v-if="isAuthenticated">
        <i class="now-ui-icons users_single-02" style="margin-top:12px;"></i>{{ this.user }}님
        안녕하세요
      </div>
      <drop-down tag="li" title="회원" icon="now-ui-icons emoticons_satisfied" class="nav-item">
        <div v-if="isAuthenticated">
          <nav-link to="/profile"> <i class="now-ui-icons users_single-02"></i> Profile </nav-link>
          <div @click="update(user)">
            <nav-link> <i class="now-ui-icons users_single-02"></i> 회원정보 수정 </nav-link>
          </div>

          <div @click="logout">
            <nav-link> <i class="now-ui-icons users_circle-08"></i> Logout </nav-link>
          </div>
          <div v-if="adm">
            <nav-link to="/customerlist">
              <i class="now-ui-icons users_circle-08"></i> 회원 리스트
            </nav-link>
          </div>
        </div>
        <div v-else>
          <nav-link to="/register">
            <i class="now-ui-icons users_circle-08"></i> 회원가입
          </nav-link>

          <nav-link to="/login"> <i class="now-ui-icons users_circle-08"></i> Login </nav-link>
        </div>
      </drop-down>

      <drop-down tag="li" title="게시판" icon="now-ui-icons design_bullet-list-67" class="nav-item">
        <nav-link to="/notice">
          <i class="now-ui-icons design_bullet-list-67"></i> 공지사항
        </nav-link>
        <nav-link to="/community"> <i class="now-ui-icons ui-2_chat-round"></i> 커뮤니티 </nav-link>
      </drop-down>
      <drop-down tag="li" title="검색" icon="now-ui-icons location_map-big" class="nav-item">
        <nav-link to="/apart"> <i class="now-ui-icons travel_istanbul"></i> 아파트 검색 </nav-link>
        <nav-link to="/dong"> <i class="now-ui-icons location_pin"></i> 동 검색 </nav-link>
        <nav-link to="/interest">
          <i class="now-ui-icons location_pin"></i> 관심 지역 보기
        </nav-link>
        <nav-link to="/recommendchart">
          <i class="now-ui-icons location_pin"></i> 추천 지역
        </nav-link>
      </drop-down>
      <drop-down tag="li" title="매물" icon="now-ui-icons shopping_shop" class="nav-item">
        <nav-link to="/productregi">
          <i class="now-ui-icons design-2_ruler-pencil"></i> 매물 등록
        </nav-link>
        <nav-link to="/productlist"> <i class="now-ui-icons location_pin"></i> 매물 보기 </nav-link>
      </drop-down>
    </template>
  </navbar>
</template>

<script>
import { DropDown, Navbar, NavLink } from '@/components';
import { Popover } from 'element-ui';
import { AUTH_LOGOUT } from '@/store/actions/auth';

export default {
  name: 'main-navbar',
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
  },
  data() {
    return {
      user: localStorage.getItem('userid'),
      isAuthenticated: false,
      adm: false,
    };
  },
  components: {
    DropDown,
    Navbar,
    NavLink,
    [Popover.name]: Popover,
  },
  created() {
    console.log(this.isAuthenticated);
  },
  mounted() {
    const user = this.user;
    console.log(user);
    if (this.user != null) {
      this.isAuthenticated = true;
    }
    if (this.user == 'admin') {
      this.adm = true;
    }
  },
  methods: {
    logout: function() {
      console.log('로그아웃');
      alert('로그아웃 합니다. 로그인 하십시오.');
      localStorage.removeItem('userid');
      localStorage.removeItem('pass');
      localStorage.removeItem('name');
      this.$router.push('/login');
      this.$router.go();
    },
    update: function(user) {
      console.log('--' + user);
      this.$router.push('/updatecustomer/' + user);
    },
  },

  loading: function() {
    return this.authStatus === 'loading' && !this.isAuthenticated;
  },
};
</script>

<style scoped></style>
