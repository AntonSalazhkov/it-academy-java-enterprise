<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <div class="box">
        <div class="box-footer">
            <form action="/catalog-search" method="GET">
                <div class="pull-left">
                    <button class="btn btn-primary"><i class="fa fa-chevron-left"></i>Continue shopping</a>
                    </button>
                </div>
            </form>
            <form action="/update-products" method="POST">

                <div class="pull-right">
                    <button class="btn btn-primary"><i class="fa fa-chevron-left"></i>
                        Back to update products
                    </button>

                </div>
            </form>
        </div>
        ${pageContext.request.contextPath}
    </div>
    </div>

    <div id="post-content">
        <p>
            <img src="img/product-not-added.jpg" class="img-responsive" alt="Example blog post alt">
        </p>
    </div>
</head>
<body>
</body>
</html>
