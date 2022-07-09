<%@ page import="org.maggy.projetquizzjsp.model.User" %>
<%@ page import="org.maggy.projetquizzjsp.dao.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<jsp:include page="inc/header.jsp">
    <jsp:param name="page" value="login"/>
</jsp:include>

<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Email</th>
        <th scope="col">Pseudo</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<User> user_list = UserDAO.getInstance().getAll();
        for(int i = 0; i < user_list.size(); i++){
            User u = user_list.get(i);
    %>
        <tr>
            <th scope="row"><%=u.getId()%></th>
            <td><%=u.getEmail()%></td>
            <td><%=u.getPseudo()%></td>
            <td>
                <a href="user/modify?id=<%=u.getId()%>" class="btn btn-warning btn-small">Modifier</a>
                <a href="user/delete?id=<%=u.getId()%>" class="btn btn-danger btn-small">Supprimer</a>
            </td>
        </tr>
    <%
        }
    %>
    </tbody>
</table>

<jsp:include page="inc/footer.jsp">
    <jsp:param name="page" value="footer"/>
</jsp:include>