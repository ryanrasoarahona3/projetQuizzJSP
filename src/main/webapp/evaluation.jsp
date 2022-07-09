<%@ page import="org.maggy.projetquizzjsp.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.maggy.projetquizzjsp.model.Quizz" %>
<%@ page import="org.maggy.projetquizzjsp.dao.QuizzDAO" %>
<%@ page import="org.maggy.projetquizzjsp.service.SessionManager" %>
<jsp:include page="inc/header.jsp">
    <jsp:param name="page" value="login"/>
</jsp:include>


<%
    User u = SessionManager.getInstance().getActiveUser();
%>
<h2>Bonjour <%= u.getPseudo()%></h2>
<form action="result" method="post">

<%
    ArrayList<Quizz> quizz_list = QuizzDAO.getInstance().getAll();
    for(int i = 0; i < quizz_list.size(); i++){
        Quizz q = quizz_list.get(i);
%>
    <div class="my-3">
        <h4><%=q.getQuestion()%></h4>
        <div class="form-check my-1">
            <input name="quizz-<%=q.getId()%>" value="1" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
            <label class="form-check-label" for="flexRadioDefault1">
                <%= q.getReponse1() %>
            </label>
        </div>
        <div class="form-check my-1">
            <input name="quizz-<%=q.getId()%>" value="2"  class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
            <label class="form-check-label" for="flexRadioDefault2">
                <%= q.getReponse2() %>
            </label>
        </div>
        <div class="form-check my-1">
            <input name="quizz-<%=q.getId()%>" value="3"  class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault3">
            <label class="form-check-label" for="flexRadioDefault3">
                <%= q.getReponse3() %>
            </label>
        </div>
        <div class="form-check my-1">
            <input name="quizz-<%=q.getId()%>" value="4" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault4">
            <label class="form-check-label" for="flexRadioDefault4">
                <%= q.getReponse4() %>
            </label>
        </div>
    </div>

<%
    }
%>
    <button type="submit" class="btn btn-primary">Soumettre</button>

</form>
<jsp:include page="inc/footer.jsp">
    <jsp:param name="page" value="footer"/>
</jsp:include>