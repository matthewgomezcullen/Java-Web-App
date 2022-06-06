package ucl.ac.uk.servlets;

import ucl.ac.uk.model.Model;
import ucl.ac.uk.model.ModelFactory;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/editNote")
public class EditNote extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Model model = ModelFactory.getModel();
    String indexSelected = request.getParameter("indexSelected");
    String title = request.getParameter("title");
    String oldTitle = request.getParameter("oldTitle");
    String details = (String) request.getParameter("details");
    String image = (String) request.getParameter("image");
    String youtube = (String) request.getParameter("youtube");
    try {
      youtube = youtube.split("=")[1];
    } catch (IndexOutOfBoundsException | NullPointerException e) {
      System.out.println("Invalid Youtube URL: " + e);
    }

    // Delete old note
    model.deleteNote(indexSelected, oldTitle);

    // Add note
    model.addNote(
        indexSelected,
        title,
        details,
        image,
        youtube);

    // Forward to home page.
    response.sendRedirect("/?indexSelected=" + indexSelected);

  }
}