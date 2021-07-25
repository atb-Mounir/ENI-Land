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
          <h2>Page d'administration</h2>
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
          
          <h2>Configuration des informations de la ville de ${ page.titre }</h2>

        </div>

        <div class="form">
          <form action="${ pageContext.request.contextPath }/Configuration" method="post" role="form" class="">
            <div class="row">
              <div class="col-md-6 form-group">
                <input type="text" name="titre" class="form-control" id="titre" placeholder="${ page.titre }" data-rule="" data-msg="">
              </div>
              <div class="col-md-6 form-group mt-3 mt-md-0">
                <input type="number" class="form-control" name="nbHabitants" id="nbHabitantInput" placeholder="${ page.nbHabitants }" data-rule="" data-msg="">
              </div>
            </div>
            <div class="form-group mt-3">
              <input type="date" class="form-control" name="dateConseil" id="dateConseil" placeholder="${ page.dateConseil }" required>
            </div>
            <div class="form-group mt-3">
              <textarea class="form-control" name="contenue" rows="5" placeholder="${ page.contenu }" required></textarea>
            </div>
            <div class="invisible">
              <input type="number" class="form-control" name="pageId" id="pageId" placeholder="${ page.id }" value="${ page.id }">
            </div>

            <div class="text-center py-2"><button type="submit" name="enregistrer">Enregistrer</button></div>
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