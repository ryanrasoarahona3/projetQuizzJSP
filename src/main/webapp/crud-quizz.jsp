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
            <a href="quizz-modify?id=<%=u.getId()%>" class="btn btn-warning btn-small">Modifier</a>
            <a href="quizz-delete?id=<%=u.getId()%>" class="btn btn-danger btn-small">Supprimer</a>

        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>


<form method="post" action="quizz">

    <div class="my-3">
        <h2>Ajouter une question</h2>
    </div>

    <div class="my-2">
        <label for="question" class="form-label">Your question</label>
        <input class="form-control" type="text" id="question" name="question">
    </div>

    <div class="my-2">
        <label for="picture" class="form-label">Change your profile picture</label>
        <input class="form-control" type="file" id="picture" name="picture">
    </div>



    <div class="my-2">
        <label for="reponse1" class="form-label">Reponse 1</label>
        <input class="form-control" type="text" id="reponse1" name="reponse1">
    </div>
    <div class="my-2">
        <label for="reponse2" class="form-label">Reponse 2</label>
        <input class="form-control" type="text" id="reponse2" name="reponse2">
    </div>
    <div class="my-2">
        <label for="reponse3" class="form-label">Reponse 3</label>
        <input class="form-control" type="text" id="reponse3" name="reponse3">
    </div>
    <div class="my-2">
        <label for="reponse4" class="form-label">Reponse 4</label>
        <input class="form-control" type="text" id="reponse4" name="reponse4">
    </div>

    <div class="my-4">
        <label for="answer">Answer</label>
        <select class="form-select" id="answer" name="answer">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>
    </div>

    <div class="my-2 text-end">
        <input type="submit" class="btn btn-primary" value="Ajouter"/>
    </div>

</form>

<jsp:include page="inc/footer.jsp">
    <jsp:param name="page" value="footer"/>
</jsp:include>