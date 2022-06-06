package ucl.ac.uk.servlets;

import ucl.ac.uk.model.Model;
import ucl.ac.uk.model.ModelFactory;
import ucl.ac.uk.records.Note;

import java.io.*;
import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("")
public class Home extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Model model = ModelFactory.getModel();
    model.readIndexes();

    // Get indexes
    List<String> indexes = model.getIndexes();
    request.setAttribute("indexes", indexes);

    // Get index selected
    String indexSelected = request.getParameter("indexSelected");
    if (!Objects.isNull(indexSelected)) {
      // Get Notes
      model.readNotes(indexSelected);
      ArrayList<Note> notes = model.getNotes();
      request.setAttribute("notes", notes);
    }

    // Forward data to JSP.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/home.jsp");
    dispatch.forward(request, response);
  }
}