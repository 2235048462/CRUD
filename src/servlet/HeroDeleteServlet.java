package servlet;

import dao.HeroDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HeroDeleteServlet extends HttpServlet {
    protected void service ( HttpServletRequest request, HttpServletResponse response )
            throws IOException {
        int id = Integer.parseInt ( request.getParameter ( "id" ) );
        new HeroDAO ().delete ( id );

        response.sendRedirect ( "/listHero" );
    }
}
