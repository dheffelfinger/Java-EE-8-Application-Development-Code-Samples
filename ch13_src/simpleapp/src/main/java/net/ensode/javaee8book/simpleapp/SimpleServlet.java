package net.ensode.javaee8book.simpleapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/simpleservlet"})
public class SimpleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            res.setContentType("text/html");
            PrintWriter printWriter = res.getWriter();
            printWriter.println("<h2>");
            printWriter
                    .println("Hello servlet world!");
            printWriter.println("</h2>");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
