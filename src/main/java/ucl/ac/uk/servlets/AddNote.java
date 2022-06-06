package ucl.ac.uk.servlets;

import ucl.ac.uk.model.Model;
import ucl.ac.uk.model.ModelFactory;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/addNote")
public class AddNote extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Model model = ModelFactory.getModel();
    String indexSelected = request.getParameter("indexSelected");

    String title = (String) request.getParameter("title");
    String details = (String) request.getParameter("details");
    String image = (String) request.getParameter("image");
    String youtube = (String) request.getParameter("youtube");
    if (!youtube.isEmpty()) {
      youtube = youtube.split("=")[1];
    }

    // Add note
    model.addNote(
        indexSelected,
        title,
        details,
        image,
        youtube);

    // Forward data to JSP.
    response.sendRedirect("/?indexSelected=" + indexSelected);
  }
}