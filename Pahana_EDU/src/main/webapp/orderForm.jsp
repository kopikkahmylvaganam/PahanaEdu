<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,bean.ItemBean" %>
<html>
<head><title>Place Order</title></head>
<body>
<h2>Select Book to Order</h2>

<form action="PlaceOrder" method="post">
    <label for="book">Choose Book:</label>
    <select name="bookName">
        <%
            List<ItemBean> items = (List<ItemBean>) request.getAttribute("items");
            for (ItemBean i : items) {
        %>
        <option value="<%=i.getName()%>"><%=i.getName()%> (Stock: <%=i.getStock()%>)</option>
        <% } %>
    </select><br><br>

    <label>Quantity:</label>
    <input type="number" name="qty" min="1" required><br><br>

    <input type="submit" value="Order">
</form>
</body>
</html>
