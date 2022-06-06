package ucl.ac.uk.servlets;

import ucl.ac.uk.model.Model;
import ucl.ac.uk.model.ModelFactory;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/deleteNote")
public class DeleteNote extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Model model = ModelFactory.getModel();
    String indexSelected = request.getParameter("indexSelected");
    String title = request.getParameter("noteTitle");

    // Delete note
    model.readNotes(indexSelected);
    model.deleteNote(indexSelected, title);

    // Forward to home page.
    response.sendRedirect("/?indexSelected=" + indexSelected);
  }
}