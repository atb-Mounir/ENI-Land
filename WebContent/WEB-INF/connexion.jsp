<%@ include file="../fragments/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

      <h1 class="logo"><a href="index.html">${ page.titre }</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
	<!-- navbar -->
		<%@ include file="../fragments/nav.jsp" %>
     <!-- .navbar-end -->

    </div>
  </header><!-- End Header -->



    <!-- ======= Contact Section ======= -->
    <section id="contact" class="contact">
      <div class="container">

        <div class="section-title">
          <h2>Page d'identification</h2>
          <p>Magnam dolores commodi suscipit. Necessitatibus eius consequatur ex aliquid fuga eum quidem. Sit sint consectetur velit. Quisquam quos quisquam cupiditate. Et nemo qui impedit suscipit alias ea. Quia fugiat sit in iste officiis commodi quidem hic quas.</p>
        </div>

        <div class="row contact-info">

          <div class="col-md-4">
            <div class="contact-address">
              <i class="bi bi-geo-alt"></i>
              <h3>Ville</h3>
              <address>${ page.titre }</address>
            </div>
          </div>

          <div class="col-md-4">
            <div class="contact-phone">
              <i class="bi bi-phone"></i>
              <h3>Nombre d'habitants</h3>
              <p>${ page.nbHabitants }</p>
            </div>
          </div>

          <div class="col-md-4">
            <div class="contact-email">
              <i class="bi bi-envelope"></i>
              <h3>Prochain conseil</h3>
              <p>${ page.dateConseil }</p>
            </div>
          </div>
          
          <!-- MESSAGES -->
          <c:if test="${empty listeCodesErreur }">
          	<h2>Veuillez entrer votre login et mot de passe</h2>
          </c:if>
          
          <!-- MESSAGES D'ERREUR -->
          <c:if test="${!empty listeCodesErreur }">
          	<h2>Erreur sur login ou mot de passe</h2>
          	<ul>
          		<c:forEach var="code" items="${ listesCodesErreur }">
          			<li>${ LecteurMessage.getMessageErreur(code) }</li>
          		</c:forEach>
          	</ul>
          </c:if>
          

        </div>

        <div class="form">
          <form action="${ pageContext.request.contextPath }/Connexion" method="post" role="form" class="form-group">
            <div class="row">
              <div class="col-md-6 form-group">
                <input type="text" name="login" class="form-control" id="loginInput" placeholder="Login">
              </div>
              <div class="col-md-6 form-group mt-3 mt-md-0">
                <input type="password" class="form-control" name="password" id="passwordInput" placeholder="Mot de passe">
              </div>
            </div>

            <div class="text-center py-3 "><button type="submit">Connexion</button></div>
          </form>
        </div>

      </div>
    </section><!-- End Contact Section -->

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  	<%@ include file="../fragments/footer.jsp" %>
  <!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>



</body>

</html>