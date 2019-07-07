package model;

public class Movie {
	private int id;
	private String title;
	private String cover;
	private String directors;
	private String rate;
	private String casts;
	private String url;
	private String movieid;
	private String type;
	private String time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getCasts() {
		return casts;
	}
	public void setCasts(String casts) {
		this.casts = casts;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMovieid() {
		return movieid;
	}
	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
		
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Movie m = (Movie) obj;
		return this.getMovieid().equals(m.getMovieid())&&(this.getUrl().equals(m.getUrl()));
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		String result = movieid + url;
		return result.hashCode();
	}
}
