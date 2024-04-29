<%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>

        <div class="container">

            <h1>
                Welcome to First JSP Todo App
            </h1>

            <h3>Name: ${name} </h3>
            <a href="list-todos">Manage </a> to your todos

        </div>
        <%@ include file="common/footer.jspf" %>