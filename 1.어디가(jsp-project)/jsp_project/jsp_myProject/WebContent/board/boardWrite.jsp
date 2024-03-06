<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Write Board</title>
		<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=3zjcf830oh"></script>
	    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=3zjcf830oh&submodules=geocoder"></script>
	    <script type="text/javascript" src="../member/js/check2.js"></script>
	    <style type="text/css">
	    	textarea {
	    		width: 99%;
			    height: 10em;
			    resize: none;
	    	}
	    </style>
	</head>
	<body>
		<center>
			<form name="frm_board" method="post" action="boardWrite_process.jsp">
				<table width="500" border="1" style="border-collapse:collapse">
					<tr>
						<td colspan="2">
							<h1>글 쓰 기</h1>
						</td>
					</tr>
					<tr>
						<td align="center">
							<h4>제 목</h4>
						</td>
						<td>
							<input name="b_title" type="text" placeholder="글 제목">
						</td>
					</tr>
					<tr>
						<td align="center">
							<h4>지 역</h4>
						</td>
						<td>
							<select id="b_loc1" name="b_loc1" onChange="cat1_change(this.value,b_loc2);" class="b_loc1">
								<option value='0' selected>-선택-</option>
							  	<option value='1'>서울</option>
								<option value='2'>부산</option>
								<option value='3'>대구</option>
								<option value='4'>인천</option>
								<option value='5'>광주</option>
								<option value='6'>대전</option>
								<option value='7'>울산</option>
								<option value='8'>강원</option>
								<option value='9'>경기</option>
								<option value='10'>경남</option>
								<option value='11'>경북</option>
								<option value='12' >전남</option>
								<option value='13'>전북</option>
								<option value='14'>제주</option>
								<option value='15'>충남</option>
								<option value='16'>충북</option>
							</select>
							<select id="b_loc2" name="b_loc2" class="b_loc2">
								<option>-선택-</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<h4>맛 집 주 소 : </h4>
							<input id="address" type="text" placeholder="검색할 주소">
							<input id="search" type="button" value="주소검색" onclick="searchMap();">
							<input type="button" value="주소지정" onclick="selectMap();">
						</td>
					</tr>
					<tr>
						<td>
							<input id="b_address" name="b_address" tpye="text" hidden="hidden">
							<input id="b_road_address" name="b_road_address" type="text" hidden="hidden">
							<input id="b_latitude" name="b_latitude" type="text" hidden="hidden">
							<input id="b_longitude" name="b_longitude" type="text" hidden="hidden">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="map" style="width:100%;height:300px;"></div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<h4>내 용</h4>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="b_content" placeholder="내용을 입력해주세요."></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="작성" onclick="check_board_write();">
							<input type="button" value="돌아가기" onclick="location.href='boardMain.jsp'">
						</td>
					</tr>
				</table>
			</form>
		</center>
	</body>
	<script type="text/javascript">
	    function check_board_write() {
	    	let value1 = document.getElementById("b_loc1");
	    	let value2 = document.getElementById("b_loc2");
	    	if (document.frm_board.b_title.value.length == 0) {
	    		alert("글 제목을 적어주세요.");
	    		return ;
	    	}
	    	if (value1.options[value1.selectedIndex].text == "-선택-" || value2.options[value2.selectedIndex].text == "-선택-") {
	    		alert("변경할 지역을 선택해주세요.");
	    		return ;
	    	}
	    	if (document.frm_board.b_content.value.length == 0) {
	    		alert("글 내용을 적어주세요.");
	    		return ;
	    	}
	    	document.frm_board.submit();
	    }
    </script>
	<script>
		let address = document.getElementById("address");
		let lat;
		let lng;
		let b_address = document.getElementById("b_address");
		let b_road_address = document.getElementById("b_road_address");
		let b_latitude = document.getElementById("b_latitude");
		let b_longitude = document.getElementById("b_longitude");
		let b_loc1 = document.getElementById("b_loc1");
		let b_loc2 = document.getElementById("b_loc2");
		
		let map = new naver.maps.Map('map', {
			center: new naver.maps.LatLng(37.335887,126.584063),
			zoom: 10,
			zoomControl: true,
		});
		
		function searchMap() {
    		naver.maps.Service.geocode(
   	    		{
   		    		query: address.value,
   		    	},
   		    	function (status, response) {
   		    		lat = parseFloat(response.v2.addresses[0].y);
   		    		lng = parseFloat(response.v2.addresses[0].x);
   		    	}
   	    	);
    		map = new naver.maps.Map('map', { // 새로운 주소로 화면 전환
    			center: new naver.maps.LatLng(lat, lng),
    			zoom: 18,
    			zoomControl: true,
    		});
    		let marker = new naver.maps.Marker({ // 마커 만들기
    			position: new naver.maps.LatLng(lat, lng),
    			map: map,
    		});
		}
		function selectMap() {
			naver.maps.Service.reverseGeocode(
    			{
    				location: new naver.maps.LatLng(lat,lng),
    				orders: [naver.maps.Service.OrderType.ADDR, naver.maps.Service.OrderType.ROAD_ADDR].join(','),
    			},
    			function (status, response) {
    				b_address.value = response.result.items[0].address;
    				b_road_address.value = response.result.items[1].address;
    				b_latitude.value = lat;
    				b_longitude.value = lng;
    			}
    		);
		}
	</script>
</html>