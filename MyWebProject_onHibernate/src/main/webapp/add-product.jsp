<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Users</title>
</head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>imagePath</th>
                    <th>name</th>
                    <th>categories</th>
                    <th>type</th>
                    <th>colours</th>
                    <th>productDetails</th>
                    <th>sizeClothes</th>
                    <th>price</th>
                    <th>inStock</th>
                </tr>
            </thead>
            <tbody>
                <form action="/add-product" method="POST">
                    <tr>
                        <td><input type="text" name="imagePath" placeholder="imagePath"></td>
                        <td><input type="text" name="name" placeholder="name"></td>
                        <td><input type="text" name="categories" placeholder="categories"></td>
                        <td><input type="text" name="type" placeholder="type"></td>
                        <td><input type="text" name="colours" placeholder="colours"></td>
                        <td><input type="text" name="productDetails" placeholder="productDetails"></td>
                        <td><input type="text" name="sizeClothes" placeholder="sizeClothes"></td>
                        <td><input type="text" name="price" placeholder="price"></td>
                        <td><input type="text" name="inStock" placeholder="inStock"></td>

                        <td><input type="submit" value="Create"></td>
                    </tr>

                    ${pageContext.request.contextPath}
                </form>
            </tbody>
        </table>
    </body>
</html>