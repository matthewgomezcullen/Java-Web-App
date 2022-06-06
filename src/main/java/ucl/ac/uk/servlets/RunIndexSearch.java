package ucl.ac.uk.servlets;

import ucl.ac.uk.model.Model;
import ucl.ac.uk.model.ModelFactory;
import ucl.ac.uk.records.Note;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/runIndexSearch")
public class RunIndexSearch extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Model model = ModelFactory.getModel();

    // Get search params
    String indexSelected = request.getParameter("indexSelected");
    String search = request.getParameter("search");

    // Search for matching indexes
    ArrayList<String> indexes = model.searchIndex(search);

    // Get index selected
    if (!Objects.isNull(indexSelected)) {
      // Get Notes
      model.readNotes(indexSelected);
      ArrayList<Note> notes = model.getNotes();
      request.setAttribute("notes", notes);
    }

    // Set attributes
    request.setAttribute("indexes", indexes);
    request.setAttribute("indexSearch", search);

    // Forward data to JSP.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/home.jsp");
    dispatch.forward(request, response);
  }
}