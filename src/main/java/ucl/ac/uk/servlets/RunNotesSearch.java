package ucl.ac.uk.servlets;

import ucl.ac.uk.model.Model;
import ucl.ac.uk.model.ModelFactory;
import ucl.ac.uk.records.Note;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/runNotesSearch")
public class RunNotesSearch extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Model model = ModelFactory.getModel();
    model.readIndexes();

    // Get search params
    String search = request.getParameter("search");

    // Search for matching notes
    ArrayList<Note> matchingNotes = model.searchNote(search);

    // Get indexes
    List<String> indexes = model.getIndexes();

    // Set attributes
    request.setAttribute("indexes", indexes);
    request.setAttribute("notes", matchingNotes);
    request.setAttribute("noteSearch", search);

    // Forward data to JSP.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/home.jsp");
    dispatch.forward(request, response);
  }
}