<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
h2 {
	margin: 0;
	color: #666;
	padding-top: 90px;
	font-size: 52px;
	font-family: "trebuchet ms", sans-serif;
}

.item {
	background: #333;
	text-align: center;
	height: 400px !important;
}

.carousel {
	margin-top: 20px;
}
.corousel-image{
	height:inherit;
}

.bs-example {
	margin: 20px;
}
</style>
<div class="bs-example">
	<div id="myCarousel" class="carousel slide" data-interval="3000"
		data-ride="carousel">
		<!-- Carousel indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<!-- Carousel items -->
		<div class="carousel-inner">
			<div class="active item">
				<img src="<c:url value="/resources/images/slider/1.jpg"/>" class="corousel-image"/>
			</div>
			<div class="item">
				<img src="<c:url value="/resources/images/slider/2.jpg"/>" class="corousel-image"/>
			</div>
			<div class="item">
				<img src="<c:url value="/resources/images/slider/3.jpg"/>" class="corousel-image"/>
			</div>
		</div>
		<!-- Carousel nav -->
		<a class="carousel-control left" href="#myCarousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a> <a class="carousel-control right" href="#myCarousel"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"></span>
		</a>
	</div>
</div>
<div style="margin-left:38%;box-shadow: 0 0 15px #5B5B5B;width:25%;font-size:25px;color:;font-family: Tahoma, Geneva, sans-serif;"><p style="margin-left:28%;">New Book Arrivals</p></div>
<c:forEach items="${bookList}" var="book">
<div style="" class="bookTile">
	<img src="<c:url value="/resources/images/${book.imageName}"/>" alt="${book.title}" style="width:200px;height:200px;padding:10px;"/>
</div>
</c:forEach>

<style>
.bookTile{
	float:left;
	box-shadow: 0 0 15px #5B5B5B;
	margin-left:70px;
	margin-top:10px;
	font-size:10px;
	width:200px;
}
.modal-backdrop.in {
	filter: alpha(opacity=0);
	opacity: 0;
}
.modal {
	background-color:rgba(0,0,0,0.5);
}
</style>
