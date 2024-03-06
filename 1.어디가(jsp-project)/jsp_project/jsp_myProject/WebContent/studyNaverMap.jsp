<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	    <title>간단한 지도 표시하기</title>
	    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=3zjcf830oh"></script>
	    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=3zjcf830oh&submodules=geocoder"></script>
	</head>
	<body>
		<div class="search">
			<input id="address" type="text" placeholder="검색할 주소">
			<input id="submit" type="button" value="주소검색" onclick="searchLatLgi();">
			<input id="submit2" type="button" value="주소검색" onclick="searchAdd();">
		</div>
		<div id="map" style="width:100%;height:500px;"></div>
		<div id="map2" style="width:100%;height:500px;"></div>
	</body>
	<script>
// 		naver.maps.Map(태그 아이디, 옵션) - 지정 DOM 요소에 지도를 삽입 할 수 있음
    	let map = new naver.maps.Map('map', {
    		center: new naver.maps.LatLng(37.335887,126.584063), // 위도 경도 정의
    		zoom: 10, // 지도의 초기 줌 레벨
    		zoomControl: true, // 확대 축소 컨트롤 바를 표시할 것인지 안할 것인지 설정
    		// size: 지도의 초기 사이즈 div 태그에서 지정해주었기에 안해줘도 됨
    	});
    	naver.maps.Event.addListener(map, 'click', function(e) { // 클릭하면 
    		alert(e.coord); // 클릭한 지점 좌표 출력
    	});
// 	    naver.maps.Service.geocode(
// 	    	{
// 		   		query: '흥동로 178-9',
// 		   	},
// 		   	function (status, response) {
// 		   		console.log(response);
// 		   	}
// 	    );
    	let add = document.getElementById("address");
    	function searchLatLgi() { // Address -> LatLng
    		let lat, lng;
    		naver.maps.Service.geocode(
   	    		{
   		    		query: add.value,
   		    	},
   		    	function (status, response) {
   		    		lat = response.v2.addresses[0].y;
   		    		lng = response.v2.addresses[0].x;
   		    		console.log(response.v2.addresses[0].x);
   		    		console.log(response.v2.addresses[0].y);
   		    	}
   	    	);
    		map = new naver.maps.Map('map', { // 새로운 주소로 화면 전환
    			center: new naver.maps.LatLng(35.2221417, 128.8678055),
    			zoom: 18,
    			zoomControl: true,
    		});
    		let marker = new naver.maps.Marker({ // 마커 만들기
    			position: new naver.maps.LatLng(35.2221417, 128.8678055),
    			map: map,
    		});
    	}
    	function searchAdd() { // LatLng -> Address
    		naver.maps.Service.reverseGeocode(
    			{
    				location: new naver.maps.LatLng(35.2221417,128.8678055),
    				orders: [naver.maps.Service.OrderType.ADDR, naver.maps.Service.OrderType.ROAD_ADDR].join(','),
    			},
    			function (status, response) {
    				console.log(response.result.items[0].address); // address
    				console.log(response.result.items[1].address); // road-address
    			}
    		);
    	}
    </script>
</html>

<!-- https://velog.io/@a_in/naver-cloud-platform-map-api-2 -->
<!-- https://solbel.tistory.com/2241 -->
<!-- https://blog.naver.com/PostView.naver?blogId=lsw3210&logNo=221996143141&parentCategoryNo=&categoryNo=26&viewDate=&isShowPopularPosts=true&from=search -->
<!-- https://okky.kr/questions/862883 -->