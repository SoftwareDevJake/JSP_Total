package board.article;
import java.util.ArrayList;

import board.DBUtil;
import board.Pagination;

public class ArticleDao {

	private DBUtil db = new DBUtil();
		
	public ArrayList<Article> getArticlesForPaging(Pagination pagination) {		
		int rowCount = pagination.getItemCountPerPage();
		int startRowIndex = (pagination.getCurrentPageNo() - 1) * rowCount;
		String sql = "SELECT a.*, m.nickname nickname, COUNT(l.aid) likeCnt FROM article a INNER JOIN `member` m ON a.mid = m.id LEFT JOIN `like` l ON a.id = l.aid GROUP BY a.id limit " + startRowIndex + ", " + rowCount;
		return db.getRows(sql, new ArticleRowMapper());
	}
	
	public ArrayList<Article> getArticles() {		
		String sql = "SELECT a.*, m.nickname nickname, COUNT(l.aid) likeCnt FROM article a INNER JOIN `member` m ON a.mid = m.id LEFT JOIN `like` l ON a.id = l.aid GROUP BY a.id";
		return db.getRows(sql, new ArticleRowMapper());
	}
	
	public int updateArticle(String title, String body, int aid) {
		String sql = "update article set title = ?, body = ? where id = ?";
		return db.updateQuery(sql, title, body, aid);
	}
	
	public int deleteArticle(int aid) {
		String sql = "delete from article where id = ?";
		return db.updateQuery(sql, aid);
	}
	
	public int insertArticle(String title, String body, int mid) {
		String sql = "insert into article set title = ?, body = ?, mid = ?, regDate = NOW(), hit = 0";
		return db.updateQuery(sql, title, body, mid);
	}
	
	public Article getArticleById(int aid) {
		String sql = "SELECT a.*, m.nickname nickname, COUNT(l.aid) likeCnt FROM article a INNER JOIN `member` m ON a.mid = m.id LEFT JOIN `like` l ON a.id = l.aid GROUP BY a.id having a.id = ?";
		return db.getRow(sql, new ArticleRowMapper(), aid);
	}
	
//	public int insertReply(int aid, String body, int mid) {
//		String sql = "insert into reply set aid = ?, body = ?, mid = ?, regDate = NOW()";
//		return db.updateQuery(sql, aid, body, mid);
//	}

	public ArrayList<Reply> getRepliesByArticleId(int id) {
		String sql = "SELECT * FROM reply r INNER JOIN `member` m ON r.mid = m.id WHERE r.aid = ?";
		return db.getRows(sql, new ReplyRowMapper(), id);
	}

	public int increaseHitByAritlceId(int id) {
		String sql = "UPDATE article SET hit = hit + 1 WHERE id = ?";
		return db.updateQuery(sql, id);
	}

	public ArrayList<Article> getSearchedArticles(int searchFlag, String searchKeyword) {
		String sql1 = "SELECT a.*, m.nickname nickname, COUNT(l.aid) likeCnt FROM article a INNER JOIN `member` m ON a.mid = m.id LEFT JOIN `like` l ON a.id = l.aid GROUP BY a.id HAVING ";
		String sql2 = "";
		if(searchFlag == 1) {
			sql2 = "title LIKE CONCAT_WS(?,'%','%')"; 
		} else if(searchFlag == 2) {
			sql2 = "body LIKE CONCAT_WS(?,'%','%')";
		} else if(searchFlag == 3) {
			sql2 = "title LIKE CONCAT_WS(?,'%','%') OR body LIKE CONCAT_WS(?,'%','%')";
			return db.getRows(sql1 + sql2, new ArticleRowMapper(), searchFlag, searchKeyword);
		} else {
			sql2 = "nickname LIKE CONCAT_WS(?,'%','%')";
		}
	
		String sql = sql1 + sql2;
		
		return db.getRows(sql, new ArticleRowMapper(), searchKeyword);
	}

	public ArrayList<Article> getSortedArticles(String sortFlag, String sortType) {
			
		String sql1 = "SELECT a.*, m.nickname nickname, COUNT(l.aid) likeCnt FROM article a INNER JOIN `member` m ON a.mid = m.id LEFT JOIN `like` l ON a.id = l.aid GROUP BY a.id ORDER BY ";
		String sql2 = sortFlag + " " + sortType;
		
		String sql = sql1 + sql2;
		
		return db.getRows(sql, new ArticleRowMapper());
		
	}

	public Like getLike(int aid, int mid) {
		
		String sql = "select * from `like` where aid = ? and `mid` = ?";
		return db.getRow(sql, new LikeRowMapper(), aid, mid); 
	}

	public void deleteLike(int aid, int mid) {
		String sql = "delete from `like` where aid = ? and `mid` = ?";
		db.updateQuery(sql, aid, mid);
	}
	
	public void insertLike(int aid, int mid) {
		String sql = "insert into `like` set aid = ?, `mid` = ?, regDate = NOW()";
		db.updateQuery(sql, aid, mid);
	}

	public int getTotalCountOfArticles() {
		String sql = "SELECT * FROM article";
		return db.getRows(sql, new ArticleRowMapper()).size();
	}

	public void deleteReplyById(int id) {
		String sql = "delete from reply where id = ?";
		
		db.updateQuery(sql, id);
	}
	
	public void insertReply(int aid, String body, int mid)
	{
		String sql = "INSERT INTO reply SET aid = ?, `body` = ?, `mid` = ?, regDate = NOW()";
		
		db.updateQuery(sql, aid, body, mid);
	}
	
	public void updateReply(String body, int id)
	{
		String sql = "UPDATE reply SET `body` = ? WHERE id = ?";
		
		db.updateQuery(sql, body, id);
	}
	
	public ArrayList<Article> day()
	{
		String sql = "SELECT * FROM article WHERE regDate > DATE_ADD(NOW(), INTERVAL -1 DAY)";
		
		return db.getRows(sql, new ArticleRowMapper());
	}
	
	public ArrayList<Article> week()
	{
		String sql = "SELECT * FROM article WHERE regDate > DATE_ADD(NOW(), INTERVAL -1 week)";
		
		return db.getRows(sql, new ArticleRowMapper());
	}
	
	public ArrayList<Article> month()
	{
		String sql = "SELECT * FROM article WHERE regDate > DATE_ADD(NOW(), INTERVAL -1 month)";
		
		return db.getRows(sql, new ArticleRowMapper());
	}
	
	public ArrayList<Article> half_year()
	{
		String sql = "SELECT * FROM article WHERE regDate > DATE_ADD(NOW(), INTERVAL -6 month)";
		
		return db.getRows(sql, new ArticleRowMapper());
	}
	
	public ArrayList<Article> year()
	{
		String sql = "SELECT * FROM article WHERE regDate > DATE_ADD(NOW(), INTERVAL -1 year)";
		
		return db.getRows(sql, new ArticleRowMapper());
	}

	public ArrayList<Article> searchArticles(String searchType, int searchDate, String searchKeyword) {
		
		String dateInterval = "";
		
		if(searchDate == 3)
		{
			dateInterval = "-1 week";
		}
		
		String sql = "SELECT * FROM article WHERE regDate > DATE_ADD(NOW(), INTERVAL " + dateInterval +") AND " + searchType + " LIKE CONCAT_WS(?,'%','%')";
		
		System.out.println(sql);
		
		return db.getRows(sql, new ArticleRowMapper(), searchKeyword);
	}

}
