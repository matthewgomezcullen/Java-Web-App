<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %>
<%@ page import="ucl.ac.uk.records.Note" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>My Notes</title>
    <jsp:include page="/meta.jsp" />
  </head>
  <body>
    <jsp:include page="/header.jsp" />
    <% 
    String indexSelected = request.getParameter("indexSelected");
    ArrayList<Note> notes = (ArrayList<Note>)request.getAttribute("notes");
    List<String> indexes = (List<String>) request.getAttribute("indexes");
    String indexSearch = (String) request.getAttribute("indexSearch");
    String noteSearch = (String) request.getAttribute("noteSearch");
    if (Objects.isNull(indexSearch)) indexSearch = "";
    if (Objects.isNull(noteSearch)) noteSearch = "";
    %>
    <div class="container main">

      <div class="container indexes">
        <div class="card">

          <div class="card-header">
            Indexes
            <form method="get" action="/runIndexSearch">
              <div class="input-group rounded">
                <input type="hidden" name="indexSelected" value="<%=indexSelected%>" />
                <input type="text" class="form-control rounded" placeholder="Find Index..." name="search" value="<%=indexSearch%>"/>
                <input type="submit" value="Search">
              </div>
            </form>
          </div>

          <ul class="list-group list-group-flush">
            <% 
            for (String index : indexes) {
              String href = "/?indexSelected=" + index;
              if (Objects.isNull(indexSelected)) 
              { %>
                <li class="list-group-item"><a href="<%=href%>"><%=index%></a></li>
              <% } else {
                if (index.equals(indexSelected)) { %>
                  <li class="list-group-item" style="background-color: rgb(222, 222, 222);"><a href="/"><%=index%></a></li>
                <% } else { %>
                  <li class="list-group-item"><a href="<%=href%>"><%=index%></a></li>
                <% }
              }
            } %>
          </ul>

          <div class="card-body">
            <form action="/addIndex" method="get">
              <div class="input-group rounded">
                <input type="text" required="required" class="form-control rounded" name="indexToAdd" placeholder="Name">
                <input type="submit" value="Add Index"></a>
              </div>
            </form>
              <%
              if (!Objects.isNull(indexSelected)) { %>
                <div class="text-center deleteIndex">
                  <a onClick="return confirm('Are you sure? This action cannot be undone.') "href="/deleteIndex?indexSelected=<%=indexSelected%>" class="card-link">Delete Index</a>
                </div>
              <% } %>
          </div>

        </div>
      </div>

      <div class="container notes">
        <div class="card">

          <div class="card-header">
            Notes
            <form method="get" action="/runNotesSearch">
              <div class="input-group rounded">
                <input type="hidden" name="indexSelected" value="<%=indexSelected%>" />
                <input type="search" class="form-control rounded" placeholder="Find Note..." name="search" value="<%=noteSearch%>"/>
                <input type="submit" value="Search">
              </div>
            </form>
          </div>
          
          <% 
          if (Objects.isNull(notes)) { %>
            <h4 class="action">Select an index</h4>
          <% } else  if (notes.isEmpty()) { %>
            <h4 class="action">No notes to show</h4>
          <% } else { %>
            <div id="accordion">
              <% 
              int i = 0;
              for (Note note : notes) { 
                i++; %>
  
                <div class="card">
                  <div class="card-header" id="heading<%=i%>">
                    <h5 class="mb-0">
                      <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse<%=i%>" aria-expanded="false" aria-controls="collapse<%=i%>">    
                        <h5 class="card-title">
                          <%=note.title()%>
                        </h5>
                      </button>
                    </h5>
                    <a href="/deleteNote?indexSelected=<%=indexSelected%>&noteTitle=<%=note.title()%>" class="card-link deleteNote">Delete Note</a>
                    <a href="/editNote.jsp?indexSelected=<%=indexSelected%>&noteTitle=<%=note.title()%>&noteDetails=<%=note.details()%>&noteImage=<%=note.image()%>&noteYoutube=<%=note.youtube()%>" 
                      class="card-link">
                      Edit Note
                    </a>
                  </div>
  
                  <div id="collapse<%=i%>" class="collapse" aria-labelledby="heading<%=i%>" data-parent="#accordion">
                    <div class="card-body">
                      <p class="card-text">
                        <%=note.details()%>
                      </p>
                    </div>
                    <%
                    if (!note.image().isEmpty()) { %>
                      <img class="card-img-bot media" src="<%=note.image()%>">
                    <% }
                    if (!note.youtube().isEmpty()) { %>
                      <iframe class="media" style="border:none" height="400" src="https://www.youtube.com/embed/<%=note.youtube()%>"></iframe>
                    <% } %>
                  </div>
                </div>
  
              <% } %>
            </div>
          <% } %>
          
          <% if (!Objects.isNull(notes)) { %>
            <div class="text-center addNote">
              <a href="/createNote?indexSelected=<%=indexSelected%>" class="card-link">Create Note</a>
            </div>
          <% } %>

        </div>
      </div>
    </div>
  </body>
</html>
