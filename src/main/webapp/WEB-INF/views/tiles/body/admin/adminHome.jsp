<div class="bs-example">
    <div id="myCarousel" class="carousel slide" data-interval="3000" data-ride="carousel">
    	<!-- Carousel indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>   
       <!-- Carousel items -->
        <div class="carousel-inner">
            <div class="active item">
                <h2>Total Books In Stock</h2>
                <div class="carousel-caption">
                  <p>${book}</p>
                </div>
            </div>
            <div class="item">
                <h2>Total Pending Delievery Requests</h2>
               
                <div class="carousel-caption">
                   <p>${delievery }</p>
                </div>
            </div>
            <div class="item">
                <h2>Total Pending Return Request</h2>
                <div class="carousel-caption">
                  <p>${returnSize}</p>
                </div>
            </div>
        </div>
        <!-- Carousel nav -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>
</div>

<style type="text/css">
h2{
    margin: 0;     
    color: #666;
    padding-top: 90px;
    font-size: 52px;
    font-family: "trebuchet ms", sans-serif;    
}
.item{
    background: #333;    
    text-align: center;
    height: 600px !important;
}
.carousel{
    margin-top:130px;
}
.bs-example{
	margin: 20px;
	height:750px;
}
.carousel-caption{
	bottom:150px;
}
.carousel-caption p{
	font-size:150px;
}
</style>