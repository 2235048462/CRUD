package servlet;

import bean.Hero;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitServlet extends HttpServlet {
    protected void service ( HttpServletRequest request, HttpServletResponse response ) {
        String data = request.getParameter ( "data" );
        System.out.println ( "服务端接收到的数据是:" + data );

        JSONObject jsonObject = JSONObject.fromObject ( data );
        System.out.println ( "转化为JSON对象之后是:" + jsonObject );

        Hero hero = (Hero) JSONObject.toBean ( jsonObject, Hero.class );
        System.out.println ( "转化为Hero对象之后是:" + hero );
    }
}
