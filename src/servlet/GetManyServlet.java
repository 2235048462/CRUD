package servlet;

import bean.Hero;
import net.sf.json.JSONSerializer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetManyServlet extends HttpServlet {
    public static void main ( String[] args ) {
        List <Hero> heroes = new ArrayList <> ();
        for (int i = 0; i < 10; i++) {
            Hero hero = new Hero ();
            hero.setName ( "name" + i );
            hero.setHp ( 300 + i );
            hero.setDamage ( 100 + i * 3 );
            heroes.add ( hero );
        }

        System.out.println ( JSONSerializer.toJSON ( heroes ).toString () );
    }

    protected void service ( HttpServletRequest request, HttpServletResponse response )
            throws IOException {
        List <Hero> heroes = new ArrayList <> ();
        for (int i = 0; i < 10; i++) {
            Hero hero = new Hero ();
            hero.setName ( "name" + i );
            hero.setHp ( 300 + i );
            hero.setDamage ( 100 + i * 3 );
            heroes.add ( hero );
        }

        String result = JSONSerializer.toJSON ( heroes ).toString ();
        response.setContentType ( "text/html;charset=utf-8" );
        response.getWriter ().print ( result );
    }
}
