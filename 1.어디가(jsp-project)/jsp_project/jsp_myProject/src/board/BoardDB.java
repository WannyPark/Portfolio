package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.MemberMd;

public class BoardDB {

	private static BoardDB instance = new BoardDB();
	
	public static BoardDB getInstance() { return instance; }
	
	public Connection getConnection() throws Exception {
		return ((DataSource)(new InitialContext().lookup("java:comp/env/jdbc/mariadb"))).getConnection();
	}
	
	public ArrayList<BoardMd> getBoards() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ArrayList<BoardMd> list = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			sql = "SELECT B_NUM, B_TITLE, B_CONTENT, B_AUTHOR, B_TIME, B_LATITUDE, B_LONGITUDE, B_LOC1, B_LOC2, B_ADDRESS, B_ROAD_ADDRESS, B_VIEWNUM"
					+ " FROM BOARDT ORDER BY B_NUM DESC";
			pstmt = conn.prepareStatement(sql);
			list = new ArrayList<BoardMd>();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardMd tmp = new BoardMd();
				tmp.setB_num(rs.getInt("B_NUM"));
				tmp.setB_title(rs.getString("B_TITLE"));
				tmp.setB_content(rs.getString("B_CONTENT"));
				tmp.setB_author(rs.getString("B_AUTHOR"));
				tmp.setB_time(rs.getString("B_TIME"));
				tmp.setB_latitude(rs.getDouble("B_LATITUDE"));
				tmp.setB_longitude(rs.getDouble("B_LONGITUDE"));
				tmp.setB_loc1(rs.getInt("B_LOC1"));
				tmp.setB_loc2(rs.getInt("B_LOC2"));
				tmp.setB_address(rs.getString("B_ADDRESS"));
				tmp.setB_road_address(rs.getString("B_ROAD_ADDRESS"));
				tmp.setB_viewNum(rs.getInt("B_VIEWNUM"));
				list.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public BoardMd getBoard(int b_num, boolean tOf) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		BoardMd board = null;
		
		try {
			conn = getConnection();
			if (tOf) {
				sql = "UPDATE BOARDT SET B_VIEWNUM=B_VIEWNUM+1 WHERE B_NUM=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, b_num);
				pstmt.executeUpdate();
			}
			sql = "SELECT B_NUM, B_TITLE, B_CONTENT, B_AUTHOR, B_TIME, B_LATITUDE, B_LONGITUDE, B_LOC1, B_LOC2, B_ADDRESS, B_ROAD_ADDRESS, B_VIEWNUM"
					+ " FROM BOARDT WHERE B_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new BoardMd();
				board.setB_num(rs.getInt("B_NUM"));
				board.setB_title(rs.getString("B_TITLE"));
				board.setB_content(rs.getString("B_CONTENT"));
				board.setB_author(rs.getString("B_AUTHOR"));
				board.setB_time(rs.getString("B_TIME"));
				board.setB_latitude(Double.parseDouble(rs.getString("B_LATITUDE")));
				board.setB_longitude(Double.parseDouble(rs.getString("B_LONGITUDE")));
				board.setB_loc1(rs.getInt("B_LOC1"));
				board.setB_loc2(rs.getInt("B_LOC2"));
				board.setB_address(rs.getString("B_ADDRESS"));
				board.setB_road_address(rs.getString("B_ROAD_ADDRESS"));
				board.setB_viewNum(rs.getInt("B_VIEWNUM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return board;
	}
	
	public int updateBoard(int b_num, String b_title, String b_content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int re = -1;
		String sql = null;
		
		try {
			conn = getConnection();
			sql = "UPDATE BOARDT SET B_TITLE=?, B_CONTENT=? WHERE B_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_title);
			pstmt.setString(2, b_content);
			pstmt.setInt(3, b_num);
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return re;
	}
	
	public int deleteBoard(int b_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int re = -1;
		String sql = null;
		
		try {
			conn = getConnection();
			sql = "DELETE FROM BOARDT WHERE B_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			re = pstmt.executeUpdate();
			if (re == 1) {
				sql = "UPDATE BOARDT SET B_NUM=B_NUM-1 WHERE B_NUM>?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, b_num);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return re;
	}
	
	public int insertBoard(BoardMd board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int re = -1;
		String sql = null;
		int maxNum = 0;
		
		try {
			conn = getConnection();
			sql = "INSERT INTO BOARDT (B_NUM, B_TITLE, B_CONTENT, B_AUTHOR, B_TIME, B_LATITUDE, B_LONGITUDE, B_LOC1, B_LOC2, B_ADDRESS, B_ROAD_ADDRESS, B_VIEWNUM)"
					+ " VALUES ((SELECT IFNULL(MAX(B_NUM)+1,1) FROM BOARDT AS TABLEALIAS),?,?,?,?,?,?,?,?,?,?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setString(3, board.getB_author());
			pstmt.setString(4, board.getB_time());
			pstmt.setString(5, Double.toString(board.getB_latitude()));
			pstmt.setString(6, Double.toString(board.getB_longitude()));
			pstmt.setInt(7, board.getB_loc1());
			pstmt.setInt(8, board.getB_loc2());
			pstmt.setString(9, board.getB_address());
			pstmt.setString(10, board.getB_road_address());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return re;
	}
	
}
