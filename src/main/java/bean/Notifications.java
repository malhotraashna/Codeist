package bean;

public enum Notifications {
QUESTIONSOLVED("An answer had been provided for your question"),
USERWANTTOFOLLOW("A user want to follow you"),
YOUWANTTOFOLLOW("your request for follwoing have been accepted"),
COMMENT("An comment had been inserted in your project"),
CONTRIBUTIONBYOTHER("A user want to contribute to your project"),
CONTRIBUTIONBYYOU("User have accepted your request for contribution"),
UPVOTESPROJECT("Your project have received upvotes"),
DOWNVOTESPROJECT("Your project have received downvotes"),
UPVOTESQUESTION("Your question have received upvotes"),
DOWNVOTESQUESTION("Your question have received downvotes"),
UPVOTESANSWER("Your answer have received upvotes"),
DOWNVOTESANSWER("Your answer have received downvotes");

	private final String msg;

	public String getMsg() {
		return msg;
	}

	private Notifications(String msg) {
		this.msg = msg;
	}
	
}