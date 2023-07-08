<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ZIGAP Main</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<!-- css -->
	<link href="css/main.css" rel="stylesheet" />
</head>

<body>

<jsp:include page="include_head.jsp" flush="ture"></jsp:include>
<jsp:include page="menu_top.jsp" flush="true"></jsp:include>

	<!--end header -->
	<section id="featured">
		<!-- start slider -->
		<div class="container">
			<div class="row">				<div class="col-lg-12">
					<!-- Slider -->
					<div id="main-slider" class="flexslider">
						<ul class="slides">
							<li class="flex-active-slide" style="width: 100%; float: left; margin-right: -100%; position: relative; display: list-item;">
								<img src="images/main1.jpg" alt="">
								<div class="flex-caption">
									<h3>계좌 관리</h3>
									<p>계좌쪼개기부터 시작하는 자산관리.<br>
										 나만의 계좌, 나만의 카테고리를<br>
										 설정하세요.</p>
								</div>
							</li>
							<li style="width: 100%; float: left; margin-right: -100%; position: relative; display: none;" class="">
								<img src="images/main2.jpg" alt="">
								<div class="flex-caption">
									<h3>예산 관리</h3>
									<p>예산을 설정하세요.<br>
										지갑관리는 예산에서부터 시작합니다.<br>
									</p>
								</div>
							</li>
							<li style="width: 100%; float: left; margin-right: -100%; position: relative; display: none;" class="">
								<img src="images/main3.jpg" alt="">
								<div class="flex-caption">
									<h3>수입/지출 관리</h3>
									<p>매달 반복적인 수입과 지출을<br>
										자동으로 관리해주는 지갑비서를<br>
										 경험하세요</p>
								</div>
							</li>
							<li style="width: 100%; float: left; margin-right: -100%; position: relative; display: none;" class="">
								<img src="images/main4.jpg" alt="">
								<div class="flex-caption">
									<h3>다양한 내역</h3>
									<p>카테고리, 반복적이거나 <br>
										일시적인 수입/지출 등<br>
										다양한 내역을 간단하게 알아보세요</p>
								</div>
							</li>
							<li style="width: 100%; float: left; margin-right: -100%; position: relative; display: none;" class="">
								<img src="images/main5.jpg" alt="">
								<div class="flex-caption">
									<h3>통계 보기</h3>
									<p>그래프로 통계를 확인하세요.<br>
										월별 통계, 예산 대비 지출비율 등<br>
										다양한 자료를 한눈에 볼 수 있습니다.</p>
								</div>
							</li>
						</ul>
					<!-- end slider -->
				</div>
			</div>
		</div>
	</section>
	<section id="content">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3-preview">
							<div class="box">
								<div class="box-gray aligncenter">
									<h4>계좌 관리</h4>
									<div class="icon">
										<i class="fa fa-desktop fa-3x"></i>
									</div>
								</div>
								<div class="box-bottom">
									<a href="#account"><img src="images/아이콘02.jpg"></a>
								</div>
							</div>
						</div>
						<div class="col-lg-3-preview">
							<div class="box">
								<div class="box-gray aligncenter">
									<h4>예산 관리</h4>
									<div class="icon">
										<i class="fa fa-desktop fa-3x"></i>
									</div>
								</div>
								<div class="box-bottom">
									<a href="#budget"><img src="images/아이콘03.jpg"></a>
								</div>
							</div>
						</div>
						<div class="col-lg-3-preview">
							<div class="box">
								<div class="box-gray aligncenter">
									<h4>수입/지출 관리</h4>
									<div class="icon">
										<i class="fa fa-desktop fa-3x"></i>
									</div>
								</div>
								<div class="box-bottom">
									<a href="#incm_exp"><img src="images/아이콘05.jpg"></a>
								</div>
							</div>
						</div>
						<div class="col-lg-3-preview">
							<div class="box">
								<div class="box-gray aligncenter">
									<h4>다양한 내역</h4>
									<div class="icon">
										<i class="fa fa-desktop fa-3x"></i>
									</div>
								</div>
								<div class="box-bottom">
									<a href="#list"><img src="images/아이콘06.jpg"></a>
								</div>
							</div>
						</div>
						<div class="col-lg-3-preview">
							<div class="box">
								<div class="box-gray aligncenter">
									<h4>통계 보기</h4>
									<div class="icon">
										<i class="fa fa-desktop fa-3x"></i>
									</div>
								</div>
								<div class="box-bottom">
									<a href="#statistics"><img src="images/아이콘07.jpg"></a>
									<a name="account"></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- divider -->
			<div class="row">
				<div class="col-lg-12">
					<div class="solidline">
					</div>
				</div>
			</div>
			<!-- end divider -->
		</div>
	
		
	<section id="two" class="wrapper style2 alt">
		<div class="inner">
			<div class="spotlight">
				<div class="image">
					<img src="images/아이콘02.jpg" alt="" />
				</div>
				<div class="content_index">
					<h3>계좌 관리</h3>
					<p>관리하고자 하는 계좌를 등록하세요.<br>
						등록된 계좌를 이용하여 예산을 등록하고, 수입과 지출을 관리할 수 있습니다.<br>
						*계좌 정보는 회원 본인만 조회할 수 있습니다.
					</p>
				</div>
				<a name="budget"></a>
			</div>
			<div class="spotlight">
				<div class="image">
					<img src="images/아이콘03.jpg" alt="" />
				</div>
				<div class="content_index">
					<h3>예산 관리</h3>
					<p>계좌별로 예산을 설정하세요.<br>
						수입 또는 지출할 것으로 예상되는 금액을 설정하면<br>
						[통계]파트에서 예산대비 현황을 알아볼 수 있습니다.
					</p>
				</div>
				<a name="incm_exp"></a>
			</div>
			
			<div class="spotlight">
				<div class="image">
					<img src="images/아이콘05.jpg" alt="" />
				</div>
				<div class="content_index">
					<h3>수입/지출 관리</h3>
					<p>고정 수입/지출관리에서 매일, 매달, 매년 반복적으로 일어나는 수입과 지출을 등록하세요.<br>
						이곳에서 설정한 고정적인 수입과 지출은 해당 날짜가 되면 캘린더에 반영되며<br>
						캘린더에선 날짜를 클릭하여 일반적인 수입과 지출을 등록할 수 있습니다.</p>
				</div>
				<a name="list"></a>
			</div>
			
			<div class="spotlight">
				<div class="image">
					<img src="images/아이콘06.jpg" alt="" />
				</div>
				<div class="content_index">
					<h3>다양한 내역</h3>
					<p>캘린더를 활용해 월별 수입과 지출을 한눈에 확인하세요.<br>
						캘린더에 표시되는 일자별 수입과 지출을 클릭하면<br>
						해당 날짜의 수입/지출 상세내역을 확인할 수 있습니다.</p>
				</div>
				<a name="statistics"></a>
			</div>
			
			<div class="spotlight">
				<div class="image">
					<img src="images/아이콘07.jpg" alt="" />
				</div>
				<div class="content_index">
					<h3>통계 보기</h3>
					<p>예산대비 수입과 지출 현황, 전월대비 수입과 지출 현황, 카테고리별 수입과 지출 현황<br>
						그리고 계좌별 현황을 각각 확인할 수 있습니다.<br>
						통계를 확인하여 소비습관을 체크하세요.
					</p>
				</div>
			</div>

			<a href="#" class="button alt">△top</a>

		</div>
	</section>
		
	</section>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>