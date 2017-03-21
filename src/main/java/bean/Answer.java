package bean;

import java.util.Date;

public class Answer {
private String username,answer;
private long upvotes,downvotes,featured_points;
private Date date = new Date();
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
public long getUpvotes() {
	return upvotes;
}
public void setUpvotes(long upvotes) {
	this.upvotes = upvotes;
}
public long getDownvotes() {
	return downvotes;
}
public void setDownvotes(long downvotes) {
	this.downvotes = downvotes;
}
public long getFeatured_points() {
	return featured_points;
}
public void setFeatured_points(long featured_points) {
	this.featured_points = featured_points;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
@Override
public String toString() {
	return "Answer [username=" + username + ", answer=" + answer + ", upvotes=" + upvotes + ", downvotes=" + downvotes
			+ ", featured_points=" + featured_points + ", date=" + date + "]";
}
public Answer() {
	super();
	// TODO Auto-generated constructor stub
}
public Answer(String username, String answer, long upvotes, long downvotes, long featured_points, Date date) {
	super();
	this.username = username;
	this.answer = answer;
	this.upvotes = upvotes;
	this.downvotes = downvotes;
	this.featured_points = featured_points;
	this.date = date;
}

}