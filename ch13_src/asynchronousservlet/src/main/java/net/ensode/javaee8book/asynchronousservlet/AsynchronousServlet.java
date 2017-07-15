package net.ensode.javaee8book.asynchronousservlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsynchronousServlet", urlPatterns = {
  "/AsynchronousServlet"},
asyncSupported = true)
public class AsynchronousServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request,
          HttpServletResponse response)
          throws ServletException, IOException {
    final Logger logger =
            Logger.getLogger(AsynchronousServlet.class.getName());
    logger.log(Level.INFO, "--- Entering doGet()");
    final AsyncContext ac = request.startAsync();
    logger.log(Level.INFO, "---- invoking ac.start()");
    ac.start(new Runnable() {

      @Override
      public void run() {
        logger.log(Level.INFO, "inside thread");
        try {
          Thread.sleep(10000);
        } catch (InterruptedException ex) {
          Logger.getLogger(AsynchronousServlet.class.getName()).
                  log(Level.SEVERE, null, ex);
        }
        try {
          ac.getResponse().getWriter().
                  println("You should see this after a brief wait");
          ac.complete();
        } catch (IOException ex) {
          Logger.getLogger(AsynchronousServlet.class.getName()).
                  log(Level.SEVERE, null, ex);
        }
      }
    });
    logger.log(Level.INFO, "Leaving doGet()");
  }
}
