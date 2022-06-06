<html>
  <head>
    <title>My Notes</title>
    <jsp:include page="/meta.jsp" />
  </head>
  <body>
    <jsp:include page="/header.jsp" />

    <div class="container main create">
      <% String indexSelected = request.getParameter("indexSelected"); %>
      <form action="/addNote" method="get" class="createNote text-center">
        <input type="hidden" name="indexSelected" value="<%=indexSelected%>" />
        <input
          type="text"
          required = "required"
          class="form-control"
          name="title"
          placeholder="Title"
        />
        <input
          type="text"
          class="form-control"
          name="details"
          placeholder="Details"
        />
        <input
          type="url"
          class="form-control"
          name="image"
          placeholder="Image URL"
        />
        <input
          type="url"
          class="form-control"
          name="youtube"
          placeholder="Youtube URL"
        />
        <input type="submit" class = "noteSubmit" value="Create Note"></a>
      </form>
    </div>
  </body>
</html>
