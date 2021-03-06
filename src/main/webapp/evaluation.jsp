<%@ page import="org.maggy.projetquizzjsp.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.maggy.projetquizzjsp.model.Quizz" %>
<%@ page import="org.maggy.projetquizzjsp.dao.QuizzDAO" %>
<%@ page import="org.maggy.projetquizzjsp.service.SessionManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>CryptoSim</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script></head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<body>
<div class="container">

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