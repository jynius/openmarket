/**
 * 
 */
 $(function() {

    var DAPI_KAKAO = 'https://dapi.kakao.com/v2/local/search/category.json';
    
	  // draw map
	  var drawMap = function(position, message) {

		    // map
		    var map = new kakao.maps.Map($('#map').get(0), {
		      center: position,
		      level: 3
		    });

		    // 마커를 생성합니다.
		    var marker = new kakao.maps.Marker({
		      map : map,
		      position : position
		    });

		    // 인포윈도우를 생성합니다.
		    var infowindow = new kakao.maps.InfoWindow({
		      content : message,
		      removable : true
		    });

		    // 인포윈도우를 마커위에 표시합니다 .
		    infowindow.open(map, marker);

		    // 지도 중심좌표를 접속위치로 변경합니다.
		    //map.setCenter(position);
	  };
	  
		var position, message;
		// HTML5의 geolocation으로 사용할 수 있는지 확인합니다.
		if(!navigator.geolocation) { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다.
			
			position = new kakao.maps.LatLng(33.450701, 126.570667),
			message = 'geolocation을 사용할수 없어요...';
				
			drawMap(location, message);
		}
		else { // GeoLocation을 이용해서 접속 위치를 얻어옵니다.

			console.log('GeoLocation...');
			navigator.geolocation.getCurrentPosition(function(position) {

				var lat = position.coords.latitude, // 위도
				    lng = position.coords.longitude; // 경도
				console.log('GeoLocation: {' + lat + ',' + lng + '}'); // 37.4800384, 126.89408
				
				position = new kakao.maps.LatLng(lat, lng), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다.
				message = '여기에 계신가요?!'; // 인포윈도우에 표시될 내용입니다.
				
			  drawMap(position, message);
			});
		}
		
		$('form').submit(function(event) {

     event.preventDefault(); 
      		  
		  var data = {
		    category_group_code: 'FD6',
		    y: 126.89408,
		    x: 37.4800384,
		    radius: 1000
		  };

		  $.ajax({
        beforeSend: function(request) {
          request.setRequestHeader("Authorization", 'KakaoAK 87708474e55217dfadc0cebb121d3597');
         },
        dataType: "json",
        url: DAPI_KAKAO,
        data: data,
        success: function(response) {
          $.each(response.documents, function(i, e) {
              console.log('GeoLocation: {' + e.y + ',' + e.x + '}');
              drawMap(new kakao.maps.LatLng(e.y, e.x), e.place_name);
            });
        }
      });
		});
});
