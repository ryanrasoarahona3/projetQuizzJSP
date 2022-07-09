<%@ page import="org.maggy.projetquizzjsp.model.User" %>
<%@ page import="org.maggy.projetquizzjsp.dao.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.maggy.projetquizzjsp.model.Quizz" %>
<%@ page import="org.maggy.projetquizzjsp.dao.QuizzDAO" %>
<jsp:include page="inc/header.jsp">
    <jsp:param name="page" value="login"/>
</jsp:include>

<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Question</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<Quizz> quizz_list = QuizzDAO.getInstance().getAll();
        for(int i = 0; i < quizz_list.size(); i++){
            Quizz u = quizz_list.get(i);
    %>
    <tr>
        <th scope="row"><%=u.getId()%></th>
        <td><%=u.getQuestion()%></td>
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