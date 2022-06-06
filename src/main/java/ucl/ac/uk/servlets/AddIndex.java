package ucl.ac.uk.servlets;

import ucl.ac.uk.model.Model;
import ucl.ac.uk.model.ModelFactory;

import java.io.*;
// import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/addIndex")
public class AddIndex extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Model model = ModelFactory.getModel();

    // Add index
    model.addIndex(request.getParameter("indexToAdd"));

    // Forward data to JSP.
    response.sendRedirect("/");
  }
}