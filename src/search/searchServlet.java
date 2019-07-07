package search;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;

import connectMysql.UseMysql;
import model.Movie;


/**
 * Servlet implementation class searchServlet
 */
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//Date date=new Date();
		request.setCharacterEncoding("utf-8");
		String text = new String(request.getParameter("movie").getBytes("ISO-8859-1"),"UTF-8");
		//String text = new String(request.getParameter("movie"));
		System.out.print(text);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println(text);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//String search = new String(request.getParameter("search"));
		String movie = new String(request.getParameter("movie"));
		//System.out.print(text);
		
		response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        List<Movie> listmovie = new ArrayList<Movie>();
        List<Movie> listmovie1 = new ArrayList<Movie>();
        List<Movie> listmovie2 = new ArrayList<Movie>();
               
        UseMysql useMysql = new UseMysql();
        listmovie = useMysql.listMovie("title",movie);
        listmovie1 = useMysql.listMovie("type",movie);       
        listmovie2 = useMysql.listMovie("casts",movie);
        
        listmovie.removeAll(listmovie1);
        listmovie.addAll(listmovie1);
        listmovie.removeAll(listmovie2);
        listmovie.addAll(listmovie2);
        
        if (listmovie.isEmpty()) {
        	String str = "{\"movie\":\"none\"}";
			out.println(str);
		}else {
			//将Movie对象集合转成json数组（静态方法）       
	        Gson gson = new Gson();
	        String json = gson.toJson(listmovie);
	        out.println(json);
		}
		       
        //String str ="{\"姓名\":\"HaHa先生\",\"年龄\":\"18岁啦\"}";
        //out.println(str);
        out.flush();
        out.close();
	}

}
