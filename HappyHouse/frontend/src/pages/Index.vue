<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <div class="container">
        <div class="content-center brand">
          <h1 class="h1-seo" style="font-weight: bold;">Happy House</h1>
        </div>
        <h6 class="category category-absolute" style="opacity: 0.1;">
          Designed by
          <a href="http://invisionapp.com/" target="_blank" style="opacity: 0.1;">
            <img
              src="img/invision-white-slim.png"
              class="invision-logo"
              style="opacity: 0.1;"
            /> </a
          >. Coded by
          <a href="https://www.creative-tim.com" target="_blank" style="opacity: 0.1;">
            <img src="img/creative-tim-white-slim2.png" class="creative-tim-logo" />
          </a>
        </h6>
      </div>
    </div>
  </div>
</template>
<script>
import { Parallax } from '@/components';

export default {
  name: 'index',
  bodyClass: 'index-page',
  components: {
    Parallax,
  },
};
</script>
<style></style>
