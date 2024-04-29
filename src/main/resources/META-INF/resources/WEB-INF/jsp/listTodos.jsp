<%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>

        <div class="container">

            <h3>Your todos are:</h3>
            <table class="table">
                <thead>
                    <tr>

                        <th>ID</th>
                        <th>Description</th>
                        <th>
                            Target Date
                        </th>
                        <th>
                            Is Done
                        </th>
                        <th>
                        </th>
                        <th></th>
                    </tr>
                </thead>
                <c:forEach items="${todos}" var="todo">

                    <tr>
                        <td>
                            ${todo.id}
                        </td>
                        <td>
                            ${todo.description}
                        </td>
                        <td>
                            ${todo.targetDate}
                        </td>
                        <td>
                            ${todo.done}
                        </td>
                        <td>
                            <a href="update-todo/${todo.id}" class="btn btn-success">Update</a>
                        </td>
                        <td>
                            <a href="delete-todo/${todo.id}" class="btn btn-warning">Delete</a>
                        </td>
                    </tr>


                </c:forEach>
            </table>
            <a href="add-todo" class="btn btn-success">Add Todo</a>
        </div>

        <%@ include file="common/footer.jspf" %>