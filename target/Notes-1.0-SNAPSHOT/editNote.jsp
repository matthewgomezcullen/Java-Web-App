<html>
  <head>
    <title>My Notes</title>
    <jsp:include page="/meta.jsp" />
  </head>
  <body>
    <jsp:include page="/header.jsp" />

    <div class="container main create">
      <% String title = request.getParameter("noteTitle"); %>
      <% String details = request.getParameter("noteDetails"); %>
      <% String image = request.getParameter("noteImage"); %>
      <% String youtube = request.getParameter("noteYoutube"); %>
      <% String indexSelected = request.getParameter("indexSelected"); %>
      <form action="/editNote" method="get" class="createNote text-center">
        <input type="hidden" name="indexSelected" value="<%=indexSelected%>" />
        <input type="hidden" name="oldTitle" value="<%=title%>" />
        <h5 class="noteTitle">
          <%=title%>
        </h5>
        <input
          type="text"
          required = "required"
          class="form-control"
          name="title"
          value="<%=title%>"
          placeholder="Title"
        />
        <input
          type="text"
          class="form-control"
          name="details"
          value="<%=details%>"
          placeholder="Details"
        />
        <input
          type="url"
          class="form-control"
          name="image"
          value="<%=image%>"
          placeholder="Image URL"
        />
        <% if (!youtube.isEmpty()) { %>
          <input
            type="url"
            class="form-control"
            name="youtube"
            value="https://www.youtube.com/watch?v=<%=youtube%>"
            placeholder="Youtube URL"
          />
        <% } else { %>
          <input
            type="url"
            class="form-control"
            name="youtube"
            placeholder="Youtube URL"
          />

        <% } %>
        <input type="submit" class="noteSubmit "value="Save changes"></a>
      </form>
    </div>
  </body>
</html>
