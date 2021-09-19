<template>
  <div>
    <div class="page-header clear-filter" filter-color="sky-blue">
      <parallax class="page-header-image" style="background-image:url('img/wallpaperbetter.jpg')">
      </parallax>
      <h2 class="title" style="margin-top:200px">Happy House</h2>
    </div>

    <div class="container-fluid" style="height:900px">
      <div class="row" style="margin-top:50px; margin-left:500px">
        <fg-input
          class="col-sm-6 "
          label="아파트로 검색"
          placeholder="아파트를 입력하세요"
          style="color:steelblue; font-weight:bold; font-size:20px"
          v-model="aptname"
          @keyup.enter="searchapt"
          v-cloak
          type="search"
        >
        </fg-input>
      </div>
      <div class="row" style="margin:30px">
        <div class="col-sm-6">
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
                  @click="showAptDetail(data.item.aptno)"
                  >상세보기</b-button
                >
              </template>

              <template #cell(관심지역)="data">
                <b-button
                  size="sm"
                  class="btn btn-warning btn-round"
                  @click="
                    addInterest(
                      data.item.aptno,
                      data.item.dong,
                      data.item.aptname,
                      data.item.dealAmount,
                      data.item.latitude,
                      data.item.longitude
                    )
                  "
                  >추가</b-button
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
        <div class="col-sm-5" style="margin-left:90px;">
          <h3 class="text" style="color:steelblue; font-weight:bold; margin-left:150px">
            해당 지도
          </h3>

          <div class="map_wrap">
            <div id="map" style="width:100%;height:500px;position:relative;overflow:hidden;"></div>
            <ul id="category">
              <li id="BK9" data-order="0" style="color:black;">
                <span class="category_bg bank"></span>
                은행
              </li>
              <li id="MT1" data-order="1" style="color:black;">
                <span class="category_bg mart"></span>
                마트
              </li>
              <li id="PM9" data-order="2" style="color:black;">
                <span class="category_bg pharmacy"></span>
                약국
              </li>
              <li id="OL7" data-order="3" style="color:black;">
                <span class="category_bg oil"></span>
                주유소
              </li>
              <li id="CE7" data-order="4" style="color:black;">
                <span class="category_bg cafe"></span>
                카페
              </li>
              <li id="CS2" data-order="5" style="color:black;">
                <span class="category_bg store"></span>
                편의점
              </li>
            </ul>
            <p>
              <b-button
                size="sm"
                class="btn btn-primary btn-round"
                @click="initMap().removeCircles()"
                >모두 지우기</b-button
              >
              <br />
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '../http-common';
import { Pagination, FormGroupInput as FgInput } from '@/components';
export default {
  components: { FgInput, [Pagination.name]: Pagination },
  bodyClass: 'profile-page',
  mounted() {
    http.get('/selectuser/' + this.userid).then((response) => {
      console.log(response.data);
      this.favorite1 = response.data.favorite_place_1;
      this.favorite2 = response.data.favorite_place_2;
    });
    window.kakao && window.kakao.maps ? this.hello(tloc) : this.addScript();
  },
  data() {
    return {
      userid: localStorage.getItem('userid'),
      favorite2: '',
      favorite1: '',
      aptname: '',
      loca: [],
      pageArray: [],
      pageNum: 0,
      loading: true,
      errored: false,
      defaultPagination: 1,
      items: [],
      perPage: 10,
      currentPage: 1,
      fields: [
        { key: 'no' },
        { key: 'aptname' },
        { key: 'dong' },
        { key: 'dealAmount' },
        { key: '상세보기' },
        { key: '관심지역' },
      ],
    };
  },
  methods: {
    addInterest: function(
      chouse_no,
      cinterest_dong,
      cinterest_place,
      cdealAmount,
      clatitude,
      clongitude
    ) {
      http
        .post('/addInterestArea', {
          userid: localStorage.getItem('userid'),
          house_no: chouse_no,
          interest_dong: cinterest_dong,
          interest_place: cinterest_place,
          dealAmount: cdealAmount,
          latitude: clatitude,
          longitude: clongitude,
        })
        .then((response) => {
          if (response.data.state == 'succ') {
            alert('관심지역을 등록 하였습니다.');
          } else {
            alert('관심지역을 등록하지 못했습니다.');
          }
        });
    },
    showAptDetail: function(no) {
      this.$router.push('/detailapt/' + no);
    },
    new() {
      this.items = [];
    },
    searchapt(e) {
      if (e.which == 13) {
        //enter
        this.new();
        http
          .get('/aptsearch/' + this.aptname)
          .then((response) => {
            this.hello(response.data);
            for (var i = 0; i < response.data.length; i++) {
              this.items.push({
                no: i + 1,
                aptno: response.data[i].no,
                aptname: response.data[i].aptname,
                dong: response.data[i].dong,
                dealAmount: response.data[i].dealAmount,
                latitude: response.data[i].latitude,
                longitude: response.data[i].longitude,
              });
            }
          })
          .catch(() => {
            this.errored = true;
          })
          .finally(() => (this.loading = false));
      }
    },
    hello(tloc) {
      this.loca = tloc;
      alert(tloc.length + '개 검색되었습니다.');

      var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      console.log(this.loca);
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(this.loca[0].latitude, this.loca[0].longitude),
        level: 8,
      };
      var map = new kakao.maps.Map(container, options); //마커추가하려면 객체를 아래와 같이 하나 만든다.
      var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';
      for (var i = 0; i < 20; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        var lat = this.loca[i].latitude;
        var lng = this.loca[i].longitude;
        var latlng = new kakao.maps.LatLng(lat, lng);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: latlng,
          title: this.loca[i].aptname, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
          clickable: true,
        });
        kakao.maps.event.addListener(marker, 'click', function() {
          alert('클릭!');
          overlay.setMap(map);
        });
      }
      // 장소 검색 객체를 생성합니다
      var ps1 = new kakao.maps.services.Places();

      // 키워드로 장소를 검색합니다
      var fav1 = this.favorite1;
      var fav2 = this.favorite2;
      console.log(fav1);
      ps1.keywordSearch(fav1, placesSearchCB1);
      ps1.keywordSearch(fav2, placesSearchCB1);
      // 키워드 검색 완료 시 호출되는 콜백함수 입니다
      function placesSearchCB1(data, status, pagination) {
        console.log(data);
        if (status === kakao.maps.services.Status.OK) {
          // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
          // LatLngBounds 객체에 좌표를 추가합니다
          var bounds = new kakao.maps.LatLngBounds();

          displayMarker1(data[0]);
          bounds.extend(new kakao.maps.LatLng(data[0].y, data[0].x));
          localStorage.setItem('loca_y', data[0].y);
          localStorage.setItem('loca_x', data[0].x);
          // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        } else {
          console.log('dksWlrgla');
        }
      }

      // 지도에 마커를 표시하는 함수입니다
      function displayMarker1(place) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = new kakao.maps.Marker({
          map: map,
          position: new kakao.maps.LatLng(place.y, place.x),
        });
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', function() {
          // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
          infowindow.setContent(
            '<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>'
          );
          infowindow.open(map, marker);
        });
        marker.setMap(map);
      }
      var placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }),
        contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        markers = [], // 마커를 담을 배열입니다
        currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다
      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places(map);

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(map, 'idle', searchPlaces);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      contentNode.className = 'placeinfo_wrap';

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
      addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      placeOverlay.setContent(contentNode);

      // 각 카테고리에 클릭 이벤트를 등록합니다
      addCategoryClickEvent();

      // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
      function addEventHandle(target, type, callback) {
        if (target.addEventListener) {
          target.addEventListener(type, callback);
        } else {
          target.attachEvent('on' + type, callback);
        }
      }
      // 카테고리 검색을 요청하는 함수입니다
      function searchPlaces() {
        console.log('searchPlaces');
        if (!currCategory) {
          return;
        }
        // 커스텀 오버레이를 숨깁니다
        placeOverlay.setMap(null);

        // 지도에 표시되고 있는 마커를 제거합니다
        removeMarker();

        ps.categorySearch(currCategory, placesSearchCB, { useMapBounds: true });
      }

      // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
      function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {
          console.log('1');
          // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
          displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
          console.log('2');
          // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
        } else if (status === kakao.maps.services.Status.ERROR) {
          console.log('3');
          // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        }
      }

      // 지도에 마커를 표출하는 함수입니다
      function displayPlaces(places) {
        // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
        // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
        var order = document.getElementById(currCategory).getAttribute('data-order');

        for (var i = 0; i < places.length; i++) {
          // 마커를 생성하고 지도에 표시합니다
          var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

          // 마커와 검색결과 항목을 클릭 했을 때
          // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
          (function(marker, place) {
            kakao.maps.event.addListener(marker, 'click', function() {
              displayPlaceInfo(place);
            });
          })(marker, places[i]);
        }
      }

      // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
      function addMarker(position, order) {
        var imageSrc =
            'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
          imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
          imgOptions = {
            spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
            spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
          },
          markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
          marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage,
          });

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        markers.push(marker); // 배열에 생성된 마커를 추가합니다

        return marker;
      }

      // 지도 위에 표시되고 있는 마커를 모두 제거합니다
      function removeMarker() {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        markers = [];
      }

      // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
      function displayPlaceInfo(place) {
        var content =
          '<div class="placeinfo">' +
          '   <a class="title" href="' +
          place.place_url +
          '" target="_blank" title="' +
          place.place_name +
          '">' +
          place.place_name +
          '</a>';

        if (place.road_address_name) {
          content +=
            '    <span title="' +
            place.road_address_name +
            '">' +
            place.road_address_name +
            '</span>' +
            '  <span class="jibun" title="' +
            place.address_name +
            '">(지번 : ' +
            place.address_name +
            ')</span>';
        } else {
          content +=
            '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
        }

        content +=
          '    <span class="tel">' +
          place.phone +
          '</span>' +
          '</div>' +
          '<div class="after"></div>';

        contentNode.innerHTML = content;
        placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        placeOverlay.setMap(map);
      }

      // 각 카테고리에 클릭 이벤트를 등록합니다
      function addCategoryClickEvent() {
        var category = document.getElementById('category'),
          children = category.children;

        for (var i = 0; i < children.length; i++) {
          children[i].onclick = onClickCategory;
        }
      }

      // 카테고리를 클릭했을 때 호출되는 함수입니다
      function onClickCategory() {
        var id = this.id,
          className = this.className;

        placeOverlay.setMap(null);

        if (className === 'on') {
          currCategory = '';
          changeCategoryClass();
          removeMarker();
        } else {
          currCategory = id;
          changeCategoryClass(this);
          searchPlaces();
        }
      }

      // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
      function changeCategoryClass(el) {
        var category = document.getElementById('category'),
          children = category.children,
          i;

        for (i = 0; i < children.length; i++) {
          children[i].className = '';
        }

        if (el) {
          el.className = 'on';
        }
      }
      var drawingFlag = false; // 원이 그려지고 있는 상태를 가지고 있을 변수입니다
      var centerPosition; // 원의 중심좌표 입니다
      var drawingCircle; // 그려지고 있는 원을 표시할 원 객체입니다
      var drawingLine; // 그려지고 있는 원의 반지름을 표시할 선 객체입니다
      var drawingOverlay; // 그려지고 있는 원의 반경을 표시할 커스텀오버레이 입니다
      var drawingDot; // 그려지고 있는 원의 중심점을 표시할 커스텀오버레이 입니다

      var circles = []; // 클릭으로 그려진 원과 반경 정보를 표시하는 선과 커스텀오버레이를 가지고 있을 배열입니다

      // 지도에 클릭 이벤트를 등록합니다
      kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
        // 클릭 이벤트가 발생했을 때 원을 그리고 있는 상태가 아니면 중심좌표를 클릭한 지점으로 설정합니다
        if (!drawingFlag) {
          // 상태를 그리고있는 상태로 변경합니다
          drawingFlag = true;

          // 원이 그려질 중심좌표를 클릭한 위치로 설정합니다
          centerPosition = mouseEvent.latLng;

          // 그려지고 있는 원의 반경을 표시할 선 객체를 생성합니다
          if (!drawingLine) {
            drawingLine = new kakao.maps.Polyline({
              strokeWeight: 3, // 선의 두께입니다
              strokeColor: '#00a0e9', // 선의 색깔입니다
              strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
              strokeStyle: 'solid', // 선의 스타일입니다
            });
          }

          // 그려지고 있는 원을 표시할 원 객체를 생성합니다
          if (!drawingCircle) {
            drawingCircle = new kakao.maps.Circle({
              strokeWeight: 1, // 선의 두께입니다
              strokeColor: '#00a0e9', // 선의 색깔입니다
              strokeOpacity: 0.1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
              strokeStyle: 'solid', // 선의 스타일입니다
              fillColor: '#00a0e9', // 채우기 색깔입니다
              fillOpacity: 0.2, // 채우기 불투명도입니다
            });
          }

          // 그려지고 있는 원의 반경 정보를 표시할 커스텀오버레이를 생성합니다
          if (!drawingOverlay) {
            drawingOverlay = new kakao.maps.CustomOverlay({
              xAnchor: 0,
              yAnchor: 0,
              zIndex: 1,
            });
          }
        }
      });

      // 지도에 마우스무브 이벤트를 등록합니다
      // 원을 그리고있는 상태에서 마우스무브 이벤트가 발생하면 그려질 원의 위치와 반경정보를 동적으로 보여주도록 합니다
      kakao.maps.event.addListener(map, 'mousemove', function(mouseEvent) {
        // 마우스무브 이벤트가 발생했을 때 원을 그리고있는 상태이면
        if (drawingFlag) {
          // 마우스 커서의 현재 위치를 얻어옵니다
          var mousePosition = mouseEvent.latLng;

          // 그려지고 있는 선을 표시할 좌표 배열입니다. 클릭한 중심좌표와 마우스커서의 위치로 설정합니다
          var linePath = [centerPosition, mousePosition];

          // 그려지고 있는 선을 표시할 선 객체에 좌표 배열을 설정합니다
          drawingLine.setPath(linePath);

          // 원의 반지름을 선 객체를 이용해서 얻어옵니다
          var length = drawingLine.getLength();

          if (length > 0) {
            // 그려지고 있는 원의 중심좌표와 반지름입니다
            var circleOptions = {
              center: centerPosition,
              radius: length,
            };

            // 그려지고 있는 원의 옵션을 설정합니다
            drawingCircle.setOptions(circleOptions);

            // 반경 정보를 표시할 커스텀오버레이의 내용입니다
            var radius = Math.round(drawingCircle.getRadius()),
              content = '<div class="info">반경 <span class="number">' + radius + '</span>m</div>';

            // 반경 정보를 표시할 커스텀 오버레이의 좌표를 마우스커서 위치로 설정합니다
            drawingOverlay.setPosition(mousePosition);

            // 반경 정보를 표시할 커스텀 오버레이의 표시할 내용을 설정합니다
            drawingOverlay.setContent(content);

            // 그려지고 있는 원을 지도에 표시합니다
            drawingCircle.setMap(map);

            // 그려지고 있는 선을 지도에 표시합니다
            drawingLine.setMap(map);

            // 그려지고 있는 원의 반경정보 커스텀 오버레이를 지도에 표시합니다
            drawingOverlay.setMap(map);
          } else {
            drawingCircle.setMap(null);
            drawingLine.setMap(null);
            drawingOverlay.setMap(null);
          }
        }
      });

      // 지도에 마우스 오른쪽 클릭이벤트를 등록합니다
      // 원을 그리고있는 상태에서 마우스 오른쪽 클릭 이벤트가 발생하면
      // 마우스 오른쪽 클릭한 위치를 기준으로 원과 원의 반경정보를 표시하는 선과 커스텀 오버레이를 표시하고 그리기를 종료합니다
      kakao.maps.event.addListener(map, 'rightclick', function(mouseEvent) {
        if (drawingFlag) {
          // 마우스로 오른쪽 클릭한 위치입니다
          var rClickPosition = mouseEvent.latLng;

          // 원의 반경을 표시할 선 객체를 생성합니다
          var polyline = new kakao.maps.Polyline({
            path: [centerPosition, rClickPosition], // 선을 구성하는 좌표 배열입니다. 원의 중심좌표와 클릭한 위치로 설정합니다
            strokeWeight: 3, // 선의 두께 입니다
            strokeColor: '#00a0e9', // 선의 색깔입니다
            strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid', // 선의 스타일입니다
          });

          // 원 객체를 생성합니다
          var circle = new kakao.maps.Circle({
            center: centerPosition, // 원의 중심좌표입니다
            radius: polyline.getLength(), // 원의 반지름입니다 m 단위 이며 선 객체를 이용해서 얻어옵니다
            strokeWeight: 1, // 선의 두께입니다
            strokeColor: '#00a0e9', // 선의 색깔입니다
            strokeOpacity: 0.1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid', // 선의 스타일입니다
            fillColor: '#00a0e9', // 채우기 색깔입니다
            fillOpacity: 0.2, // 채우기 불투명도입니다
          });

          var radius = Math.round(circle.getRadius()), // 원의 반경 정보를 얻어옵니다
            content = getTimeHTML(radius); // 커스텀 오버레이에 표시할 반경 정보입니다

          // 반경정보를 표시할 커스텀 오버레이를 생성합니다
          var radiusOverlay = new kakao.maps.CustomOverlay({
            content: content, // 표시할 내용입니다
            position: rClickPosition, // 표시할 위치입니다. 클릭한 위치로 설정합니다
            xAnchor: 0,
            yAnchor: 0,
            zIndex: 1,
          });

          // 원을 지도에 표시합니다
          circle.setMap(map);

          // 선을 지도에 표시합니다
          polyline.setMap(map);

          // 반경 정보 커스텀 오버레이를 지도에 표시합니다
          radiusOverlay.setMap(map);

          // 배열에 담을 객체입니다. 원, 선, 커스텀오버레이 객체를 가지고 있습니다
          var radiusObj = {
            polyline: polyline,
            circle: circle,
            overlay: radiusOverlay,
          };

          // 배열에 추가합니다
          // 이 배열을 이용해서 "모두 지우기" 버튼을 클릭했을 때 지도에 그려진 원, 선, 커스텀오버레이들을 지웁니다
          circles.push(radiusObj);

          // 그리기 상태를 그리고 있지 않는 상태로 바꿉니다
          drawingFlag = false;

          // 중심 좌표를 초기화 합니다
          centerPosition = null;

          // 그려지고 있는 원, 선, 커스텀오버레이를 지도에서 제거합니다
          drawingCircle.setMap(null);
          drawingLine.setMap(null);
          drawingOverlay.setMap(null);
        }
      });

      // 지도에 표시되어 있는 모든 원과 반경정보를 표시하는 선, 커스텀 오버레이를 지도에서 제거합니다
      function removeCircles() {
        for (var i = 0; i < circles.length; i++) {
          circles[i].circle.setMap(null);
          circles[i].polyline.setMap(null);
          circles[i].overlay.setMap(null);
        }
        circles = [];
      }

      // 마우스 우클릭 하여 원 그리기가 종료됐을 때 호출하여
      // 그려진 원의 반경 정보와 반경에 대한 도보, 자전거 시간을 계산하여
      // HTML Content를 만들어 리턴하는 함수입니다
      function getTimeHTML(distance) {
        // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
        var walkkTime = (distance / 67) | 0;
        var walkHour = '',
          walkMin = '';

        // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
        if (walkkTime > 60) {
          walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 ';
        }
        walkMin = '<span class="number">' + (walkkTime % 60) + '</span>분';

        // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
        var bycicleTime = (distance / 227) | 0;
        var bycicleHour = '',
          bycicleMin = '';

        // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
        if (bycicleTime > 60) {
          bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 ';
        }
        bycicleMin = '<span class="number">' + (bycicleTime % 60) + '</span>분';

        // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
        var content = '<ul class="info">';
        content += '    <li>';
        content +=
          '        <span class="label">총거리</span><span class="number">' + distance + '</span>m';
        content += '    </li>';
        content += '    <li>';
        content += '        <span class="label">도보</span>' + walkHour + walkMin;
        content += '    </li>';
        content += '    <li>';
        content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
        content += '    </li>';
        content += '</ul>';

        return content;
      }
    },

    initMap() {
      // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
      var placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }),
        contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        markers = [], // 마커를 담을 배열입니다
        currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다

      var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
          level: 5, // 지도의 확대 레벨
        };

      // 지도를 생성합니다
      var map = new kakao.maps.Map(mapContainer, mapOption);

      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places(map);

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(map, 'idle', searchPlaces);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      contentNode.className = 'placeinfo_wrap';

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
      addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      placeOverlay.setContent(contentNode);

      // 각 카테고리에 클릭 이벤트를 등록합니다
      addCategoryClickEvent();

      // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
      function addEventHandle(target, type, callback) {
        if (target.addEventListener) {
          target.addEventListener(type, callback);
        } else {
          target.attachEvent('on' + type, callback);
        }
      }
      // 카테고리 검색을 요청하는 함수입니다
      function searchPlaces() {
        if (!currCategory) {
          return;
        }

        // 커스텀 오버레이를 숨깁니다
        placeOverlay.setMap(null);

        // 지도에 표시되고 있는 마커를 제거합니다
        removeMarker();

        ps.categorySearch(currCategory, placesSearchCB, { useMapBounds: true });
      }

      // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
      function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {
          // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
          displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
          // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
        } else if (status === kakao.maps.services.Status.ERROR) {
          // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        }
      }

      // 지도에 마커를 표출하는 함수입니다
      function displayPlaces(places) {
        // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
        // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
        var order = document.getElementById(currCategory).getAttribute('data-order');

        for (var i = 0; i < places.length; i++) {
          // 마커를 생성하고 지도에 표시합니다
          var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

          // 마커와 검색결과 항목을 클릭 했을 때
          // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
          (function(marker, place) {
            kakao.maps.event.addListener(marker, 'click', function() {
              displayPlaceInfo(place);
            });
          })(marker, places[i]);
        }
      }

      // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
      function addMarker(position, order) {
        var imageSrc =
            'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
          imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
          imgOptions = {
            spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
            spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
          },
          markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
          marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage,
          });

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        markers.push(marker); // 배열에 생성된 마커를 추가합니다

        return marker;
      }

      // 지도 위에 표시되고 있는 마커를 모두 제거합니다
      function removeMarker() {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        markers = [];
      }

      // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
      function displayPlaceInfo(place) {
        var content =
          '<div class="placeinfo">' +
          '   <a class="title" href="' +
          place.place_url +
          '" target="_blank" title="' +
          place.place_name +
          '">' +
          place.place_name +
          '</a>';

        if (place.road_address_name) {
          content +=
            '    <span title="' +
            place.road_address_name +
            '">' +
            place.road_address_name +
            '</span>' +
            '  <span class="jibun" title="' +
            place.address_name +
            '">(지번 : ' +
            place.address_name +
            ')</span>';
        } else {
          content +=
            '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
        }

        content +=
          '    <span class="tel">' +
          place.phone +
          '</span>' +
          '</div>' +
          '<div class="after"></div>';

        contentNode.innerHTML = content;
        placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        placeOverlay.setMap(map);
      }

      // 각 카테고리에 클릭 이벤트를 등록합니다
      function addCategoryClickEvent() {
        var category = document.getElementById('category'),
          children = category.children;

        for (var i = 0; i < children.length; i++) {
          children[i].onclick = onClickCategory;
        }
      }

      // 카테고리를 클릭했을 때 호출되는 함수입니다
      function onClickCategory() {
        var id = this.id,
          className = this.className;

        placeOverlay.setMap(null);

        if (className === 'on') {
          currCategory = '';
          changeCategoryClass();
          removeMarker();
        } else {
          currCategory = id;
          changeCategoryClass(this);
          searchPlaces();
        }
      }

      // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
      function changeCategoryClass(el) {
        var category = document.getElementById('category'),
          children = category.children,
          i;

        for (i = 0; i < children.length; i++) {
          children[i].className = '';
        }

        if (el) {
          el.className = 'on';
        }
      }
    },
    addScript() {
      const script = document.createElement('script');
      /* global kakao */

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=64739f9f9a49c6cac1cfdc52327a4155&libraries=services';
      document.head.appendChild(script);
    },
  },
  computed: {
    rows() {
      return this.items.length;
    },
  },
};
</script>
<style>
.info {
  position: relative;
  top: 5px;
  left: 5px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  font-size: 12px;
  padding: 5px;
  background: #fff;
  list-style: none;
  margin: 0;
}
.info:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .label {
  display: inline-block;
  width: 50px;
}
.number {
  font-weight: bold;
  color: #00a0e9;
}
#mapwrap {
  position: relative;
  overflow: hidden;
}
.category,
.category * {
  margin: 0;
  padding: 0;
  color: #000;
}
.category {
  position: absolute;
  overflow: hidden;
  top: 10px;
  left: 10px;
  width: 150px;
  height: 50px;
  z-index: 10;
  border: 1px solid black;
  font-family: 'Malgun Gothic', '맑은 고딕', sans-serif;
  font-size: 12px;
  text-align: center;
  background-color: #fff;
}
.category .menu_selected {
  background: #ff5f4a;
  color: #fff;
  border-left: 1px solid #915b2f;
  border-right: 1px solid #915b2f;
  margin: 0 -1px;
}
.category li {
  list-style: none;
  float: left;
  width: 50px;
  height: 45px;
  padding-top: 5px;
  cursor: pointer;
}
.category .ico_comm {
  display: block;
  margin: 0 auto 2px;
  width: 22px;
  height: 26px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/category.png') no-repeat;
}
.category .ico_coffee {
  background-position: -10px 0;
}
.category .ico_store {
  background-position: -10px -36px;
}
.category .ico_carpark {
  background-position: -10px -72px;
}
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: '';
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png');
}
.info .link {
  color: #5085bb;
}
table {
  width: 100%;
  border-collapse: collapse;
  background-color: #fff;
  color: #000;
}
table th {
  font-size: 1.2rem;
}
table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}
table tr:first-of-type {
  border-top: 2px solid #404040;
}
table tr td {
  padding: 1rem 0;
  font-size: 1.1rem;
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: '';
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png');
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
  color: #000;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
