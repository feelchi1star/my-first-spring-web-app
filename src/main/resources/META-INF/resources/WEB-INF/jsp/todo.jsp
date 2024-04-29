<%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>

        <div class="container">
            <h1>Todo Details</h1>
            <form:form method="post" class="mt-4" modelAttribute="todo">
                <fieldset class="mb-3">
                    <form:label path="description">Description:
                    </form:label>
                    <form:errors path="description" cssClass="text-warning" />
                    <form:input required="true" path="description" aria-required="true" type="text"
                        name="description" />
                </fieldset>
                <fieldset class="mb-3">
                    <form:label path="targetDate">Target Date:
                    </form:label>
                    <form:errors path="targetDate" cssClass="text-warning" />
                    <form:input required="true" path="targetDate" aria-required="true" type="date" name="targetDate" />
                </fieldset>
                <form:input type="hidden" path="done" />
                <form:input type="hidden" path="id" />

                <input type="submit" class="btn btn-success" value="Submit">

            </form:form>

        </div>
        <%@ include file="common/footer.jspf" %>