package connectMysql;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import model.Movie;

public class UseMysql {
	private String className = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/doubanmovie1";
	private String username = "root";
	private String password = "root";
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url,username,password);
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	public List<Movie> listMovie(String list,String movieData) {
		List<Movie> Listmovie = new ArrayList<Movie>();
		//Movie[] movies = null;
		//int i;
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sql1 = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			sql = "select count(*) from movietable1 where "+list+" like '%"+movieData+"%' order by length(substring_index("+list+",'"+movieData+"',1)), length("+list+") limit 0,1";
			sql1 = "select * from movietable1 where "+list+" like '%"+movieData+"%' order by length(substring_index("+list+",'"+movieData+"',1)), length("+list+")";

			//执行查询
			rs = stmt.executeQuery(sql);
			rs.next();
						
			//System.out.print(rs.getInt(1));
			if (rs.getInt(1) == 0) {
				return Listmovie;
			}
			//movies = new Movie[rs.getInt(1)];
			//String sql1 = "select * from movietable1 where "+list+" like '%"+movieData+"%' order by length("+list+")";
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Movie movie = new Movie(); 
				movie.setId(rs.getInt(1));
				movie.setTitle(rs.getString(2));
				movie.setCover(rs.getString(3));
				movie.setDirectors(rs.getString(4));
				movie.setRate(rs.getString(5));
				movie.setCasts(rs.getString(6));
				movie.setUrl(rs.getString(7));
				movie.setMovieid(rs.getString(8));
				movie.setType(rs.getString(9));
				movie.setTime(rs.getString(10));
				Listmovie.add(movie);	
			}
			rs.close();
			stmt.close();
			con.close();
			//System.out.print(Listmovie.get(0).getTitle());
			
		}catch (SQLException sqlex) {
			// TODO: handle exception
			sqlex.printStackTrace();
		}
		return Listmovie;
	}
	
}
