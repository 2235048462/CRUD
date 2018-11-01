package servlet;

import bean.Hero;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetOneServlet extends HttpServlet {
    protected void service ( HttpServletRequest request, HttpServletResponse response )
            throws IOException {
        Hero hero = new Hero ();
        hero.setName ( "盖伦" );
        hero.setHp ( 353 );
        hero.setDamage ( 204 );

        JSONObject json = new JSONObject ();

        json.put ( "hero", JSONObject.fromObject ( hero ) );
        response.setContentType ( "text/html;charset=utf-8" );
        response.getWriter ().print ( json );
    }
}
