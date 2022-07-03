<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="robots" content="all,follow">
    <meta name="googlebot" content="index,follow,snippet,archive">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Obaju e-commerce template">
    <meta name="author" content="Ondrej Svestka | ondrejsvestka.cz">
    <meta name="keywords" content="">

    <title>
        Obaju : e-commerce template
    </title>

    <meta name="keywords" content="">

    <link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>

    <!-- styles -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/owl.carousel.css" rel="stylesheet">
    <link href="css/owl.theme.css" rel="stylesheet">

    <!-- theme stylesheet -->
    <link href="css/style.default.css" rel="stylesheet" id="theme-stylesheet">

    <!-- your stylesheet with modifications -->
    <link href="css/custom.css" rel="stylesheet">

    <script src="js/respond.min.js"></script>

    <link rel="shortcut icon" href="favicon.png">


</head>

<body>
<!-- *** TOPBAR ***
_________________________________________________________ -->
<div id="top">
    <div class="container">
        <div class="col-md-6 offer" data-animate="fadeInDown">
            <a href="#" class="btn btn-success btn-sm" data-animate-hover="shake">Offer of the day</a> <a href="#">Get
            flat 35% off on orders over $50!</a>
        </div>





        <div class="col-md-6" data-animate="fadeInDown">
            <form action="/authorization" method="POST">
                <ul class="menu">
                    <c:choose>
                        <c:when test="${userType == null}">
                            <li><a href="#" data-toggle="modal" data-target="#login-modal">Login</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a>Your login: ${login}, access level: ${userType}</a>
                            </li>
                            <li>
                                <input type="hidden" name="logOut" value=logOut>
                                <input type="submit" value="Log out">
                            </li>
                        </c:otherwise>
                    </c:choose>
                    <li><a href="contact.html">Contact</a>
                    </li>
                    <li><a href="#">Recently viewed</a>
                    </li>
                </ul>
            </form>
            ${pageContext.request.contextPath}
        </div>





    </div>
    <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
        <div class="modal-dialog modal-sm">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="Login">Customer login</h4>
                </div>
                <div class="modal-body">
                    <form action="/authorization" method="POST">
                        <div class="form-group">
                            <input type="text" class="form-control" name="login" placeholder="login">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="password" placeholder="password">
                        </div>

                        <p class="text-center">
                            <button class="btn btn-primary"><i class="fa fa-sign-in"></i> Log in</button>
                        </p>

                    </form>

                    <p class="text-center text-muted">Not registered yet?</p>
                    <p class="text-center text-muted"><a href="register.jsp"><strong>Register now</strong></a>! It is
                        easy and done in 1&nbsp;minute and gives you access to special discounts and much more!</p>

                </div>
            </div>
        </div>
    </div>

</div>

<!-- *** TOP BAR END *** -->

<!-- *** NAVBAR ***
_________________________________________________________ -->

<div class="navbar navbar-default yamm" role="navigation" id="navbar">
    <div class="container">
        <div class="navbar-header">

            <a class="navbar-brand home" href="index.jsp" data-animate-hover="bounce">
                <img src="img/logo.png" alt="Obaju logo" class="hidden-xs">
                <img src="img/logo-small.png" alt="Obaju logo" class="visible-xs"><span class="sr-only">Obaju - go to homepage</span>
            </a>
            <div class="navbar-buttons">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation">
                    <span class="sr-only">Toggle navigation</span>
                    <i class="fa fa-align-justify"></i>
                </button>
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#search">
                    <span class="sr-only">Toggle search</span>
                    <i class="fa fa-search"></i>
                </button>
                <a class="btn btn-default navbar-toggle" href="/show-cart">
                    <i class="fa fa-shopping-cart"></i> <span class="hidden-xs">Items in cart</span>
                </a>
            </div>
        </div>
        <!--/.navbar-header -->

        <div class="navbar-collapse collapse" id="navigation">

            <ul class="nav navbar-nav navbar-left">
                <li class="active"><a href="index.jsp">Home</a>
                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->

        <div class="navbar-buttons">

            <div class="navbar-collapse collapse right" id="basket-overview">
                <a href="/show-cart" class="btn btn-primary navbar-btn"><i class="fa fa-shopping-cart"></i><span
                        class="hidden-sm">Items in cart</span></a>
            </div>
            <!--/.nav-collapse -->

            <div class="navbar-collapse collapse right" id="search-not-mobile">
                <button type="button" class="btn navbar-btn btn-primary" data-toggle="collapse" data-target="#search">
                    <span class="sr-only">Toggle search</span>
                    <i class="fa fa-search"></i>
                </button>
            </div>

        </div>

        <div class="collapse clearfix" id="search">

            <form action="/catalog-search" method="GET" class="navbar-form" role="search">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search" name="searchQuery">
                    <span class="input-group-btn">

			<button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>

		    </span>
                </div>
            </form>

        </div>
        <!--/.nav-collapse -->

    </div>
    <!-- /.container -->
</div>
<!-- /#navbar -->

<!-- *** NAVBAR END *** -->

<div id="all">

    <div id="content">
        <div class="container">

            <div class="col-md-12">
                <ul class="breadcrumb">
                    <li><a href="index.jsp">Home</a>
                    </li>
                    <li><a href="/catalog-search">Catalog</a>
                    </li>
                    <li><a href="/view-details"><input type="hidden" name="id" value=${product.id}/> Details</a>
                        ${pageContext.request.contextPath}
                    </li>
                    <li>Shopping cart</li>
                </ul>
            </div>


            <div class="col-md-9" id="basket">

                <div class="box">
                    <form action="/show-total-cost" method="POST">


                        <h1>Shopping cart</h1>

                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th colspan="3">Product</th>
                                    <th>Quantity</th>
                                    <th>In stock</th>
                                    <th>Unit price</th>
                                </tr>
                                </thead>

                                <tbody>

                                <tr>
                                    <td>
                                        <a href="#">
                                            <img src="${product.imagePath}" alt="White Blouse Armani">
                                        </a>
                                    </td>
                                    <td colspan="2"><a href="#">${product.name}</a>
                                    </td>
                                    <td>
                                        <input type="number" onkeypress="return event.charCode >= 48" min="1" name="productQuantity" value="1">
                                    </td>
                                    <td> ${product.inStock}</td>
                                    </td>
                                    <td> ${product.price} $</td>
                                    </td>
                                </tr>

                                </tbody>


                                <tfoot>
                                <tr>
                                    <th colspan="4">Selected quantity</th>

                                    <th colspan="2">${productQuantity}</th>
                                </tr>
                                <tr>
                                    <th colspan="4">Total</th>

                                    <th colspan="2">${totalCost}</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.table-responsive -->

                        <div class="box-footer">
                            <div class="pull-left">
                                <a href="/catalog-search" class="btn btn-default"><i class="fa fa-chevron-left"></i>
                                    Continue shopping</a>
                            </div>
                            <div class="pull-right">
                                <button class="btn btn-primary" name="id" value=${product.id}><i
                                        class="fa fa-refresh"></i>
                                    Update basket
                                </button>

                            </div>
                        </div>
                        ${pageContext.request.contextPath}
                    </form>

                    <form action="/buy-product" method="POST">
                        <div class="box-footer">

                            <div class="pull-right">

                                <input type="hidden" name="productQuantity" value=${productQuantity}>
                                <button class="btn btn-primary" name="id" value=${product.id}> Buy product <i
                                        class="fa fa-chevron-right"></i>
                                </button>

                            </div>
                            ${pageContext.request.contextPath}
                    </form>
                </div>
            </div>

        </div>
        <!-- /.container -->
    </div>
    <!-- /#content -->

    <!-- *** FOOTER ***
    _________________________________________________________ -->
    <div id="footer" data-animate="fadeInUp">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <h4>Pages</h4>

                    <ul>
                        <li><a href="text.html">About us</a>
                        </li>
                        <li><a href="text.html">Terms and conditions</a>
                        </li>
                        <li><a href="faq.html">FAQ</a>
                        </li>
                        <li><a href="contact.html">Contact us</a>
                        </li>
                    </ul>

                    <hr>

                    <h4>User section</h4>

                    <ul>
                        <li><a href="#" data-toggle="modal" data-target="#login-modal">Login</a>
                        </li>
                        <li><a href="register.jsp">Regiter</a>
                        </li>
                    </ul>

                    <hr class="hidden-md hidden-lg hidden-sm">

                </div>
                <!-- /.col-md-3 -->

                <div class="col-md-3 col-sm-6">


                    <hr class="hidden-md hidden-lg">

                </div>
                <!-- /.col-md-3 -->

                <div class="col-md-3 col-sm-6">

                    <h4>Where to find us</h4>

                    <p><strong>Obaju Ltd.</strong>
                        <br>13/25 New Avenue
                        <br>New Heaven
                        <br>45Y 73J
                        <br>England
                        <br>
                        <strong>Great Britain</strong>
                    </p>

                    <a href="contact.html">Go to contact page</a>

                    <hr class="hidden-md hidden-lg">

                </div>
                <!-- /.col-md-3 -->


                <div class="col-md-3 col-sm-6">

                    <h4>Get the news</h4>

                    <p class="text-muted">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac
                        turpis egestas.</p>

                    <form>
                        <div class="input-group">

                            <input type="text" class="form-control">

                            <span class="input-group-btn">

			    <button class="btn btn-default" type="button">Subscribe!</button>

			</span>

                        </div>
                        <!-- /input-group -->
                    </form>

                    <hr>

                    <h4>Stay in touch</h4>

                    <p class="social">
                        <a href="#" class="facebook external" data-animate-hover="shake"><i class="fa fa-facebook"></i></a>
                        <a href="#" class="twitter external" data-animate-hover="shake"><i
                                class="fa fa-twitter"></i></a>
                        <a href="#" class="instagram external" data-animate-hover="shake"><i
                                class="fa fa-instagram"></i></a>
                        <a href="#" class="gplus external" data-animate-hover="shake"><i class="fa fa-google-plus"></i></a>
                        <a href="#" class="email external" data-animate-hover="shake"><i class="fa fa-envelope"></i></a>
                    </p>


                </div>
                <!-- /.col-md-3 -->

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->
    </div>
    <!-- /#footer -->

    <!-- *** FOOTER END *** -->


    <!-- *** COPYRIGHT ***
    _________________________________________________________ -->
    <div id="copyright">
        <div class="container">
            <div class="col-md-6">
                <p class="pull-left">© 2015 Your name goes here.</p>

            </div>
            <div class="col-md-6">
                <p class="pull-right">Template by <a href="http://bootstrapious.com/e-commerce-templates">Bootstrap
                    Ecommerce Templates</a> with support from <a href="http://kakusei.cz">Designové předměty</a>
                    <!-- Not removing these links is part of the licence conditions of the template. Thanks for understanding :) -->
                </p>
            </div>
        </div>
    </div>
    <!-- *** COPYRIGHT END *** -->


</div>
<!-- /#all -->


<!-- *** SCRIPTS TO INCLUDE ***
_________________________________________________________ -->
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.cookie.js"></script>
<script src="js/waypoints.min.js"></script>
<script src="js/modernizr.js"></script>
<script src="js/bootstrap-hover-dropdown.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/front.js"></script>


</body>

</html>