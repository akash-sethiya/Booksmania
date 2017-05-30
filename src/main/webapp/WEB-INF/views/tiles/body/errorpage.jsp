<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
  <div class="row">
    <div class="span12">
      <div class="hero-unit center">
		<article><h1>${errorMsg }</h1></article>
          <br />
          <!-- <img src="<c:url value="/resources/images/errorPage.jpg"/>" alt="errorPage" style="position:relative;left:200px;top:150px;"/>-->
          <a href="home" class="btn btn-large btn-info" style="width:40%;height:40%;"><i class="icon-home icon-blue"></i> Take Me Home</a>
        </div>
      </div>
	</div>
</div>

<style>
  .center {text-align: center; margin-left: auto; margin-right: auto; margin-bottom: auto; margin-top: auto;}
  body{background-color:#B1A8A8;}
</style>


<style>
article h1 {
display: block;
color: #1b528e;
font-size: 6.5em;
line-height: 1.75em;
font-family: 'Pacifico', 'Times';
font-weight: 100;
text-align: center;
text-shadow: 0px 2px 2px #2c2c2c;
}
</style>