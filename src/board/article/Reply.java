package board.article;


public class Reply {

	int id;
	int parentId;
	int mid;
	String body;
	String nickname;
	String regDate;
	
	public Reply() {
		
	}
	
	public Reply(int id, int parentId, int mid, String body, String nickname, String regDate) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.body = body;
		this.mid = mid;
		this.nickname = nickname;
		this.regDate = regDate;
	}
	
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
