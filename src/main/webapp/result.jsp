<%@ page import="org.maggy.projetquizzjsp.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.maggy.projetquizzjsp.model.Quizz" %>
<%@ page import="org.maggy.projetquizzjsp.dao.QuizzDAO" %>
<%@ page import="org.maggy.projetquizzjsp.service.SessionManager" %>
<%@ page import="java.util.Map" %>
<jsp:include page="inc/header.jsp">
    <jsp:param name="page" value="login"/>
</jsp:include>


<%
    //User u = SessionManager.getInstance().getActiveUser();
    Map<String, Boolean> quizzResult = SessionManager.getInstance().getQuizzResult();
%>
<h2>Vos résultats</h2>
<div>

    <%
        ArrayList<Quizz> quizz_list = QuizzDAO.getInstance().getAll();
        int score = 0;
        for(int i = 0; i < quizz_list.size(); i++){
            Quizz q = quizz_list.get(i);
    %>
    <div class="my-3">
        <% if(quizzResult.get("quizz-" + q.getId())) { score+=1; %>
        <h4><%=q.getQuestion()%> : <span class="text-success">VRAI</span></h4>
        <% }else{ %>
        <h4><%=q.getQuestion()%> : <span class="text-danger">FAUX</span></h4>
        <% } %>
    </div>

    <%
        }
    %>

    <div class="my-3">
        <p>
            Votre score est de
        </p>
        <h1 style="font-weight: lighter; font-size: 4em;"><%=score%>/<%=SessionManager.getInstance().getQuizzResult().size()%></h1>
    </div>
</div>
<jsp:include page="inc/footer.jsp">
    <jsp:param name="page" value="footer"/>
</jsp:include>