<jsp:include page="inc/header.jsp">
    <jsp:param name="page" value="login"/>
</jsp:include>


<form method="post" action="evaluation">
    <div class="mb-3">
        <label for="email" class="form-label">Email address</label>
        <input type="email" class="form-control" id="email" name="email">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" name="password">
    </div>
    <!--
    <p>You don't have an account ? <a href="signup.jsp">Create one for free</a></p>
    -->
    <button type="submit" class="btn btn-primary">Login</button>
</form>


<jsp:include page="inc/footer.jsp">
    <jsp:param name="page" value="footer"/>
</jsp:include>
