package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDB {

	private static MemberDB instance = new MemberDB();
	
	public static MemberDB getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		return ((DataSource)(new InitialContext().lookup("java:comp/env/jdbc/mariadb"))).getConnection();
	}
	
	public int insertMember(MemberMd member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		MemberMd tmpMember = null;
		int re = -1;
		String sql = null;
		
		try {
			conn = getConnection();
			tmpMember = getMember(member.getUser_id(), 1);
			if (tmpMember != null) return -2; // 이미 같은 아이디가 존재하는 경우
			tmpMember = getMember(member.getUser_nName(), 2);
			if (tmpMember != null) return -3; // 이미 같은 닉네임이 존재하는 경우
			sql = "INSERT INTO MEMBERT (USER_ID,USER_PW,USER_NNAME,USER_NAME,USER_NUM,USER_BIRTHDAY,USER_LOC1,USER_LOC2) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUser_id());
			pstmt.setString(2, member.getUser_pw());
			pstmt.setString(3, member.getUser_nName());
			pstmt.setString(4, member.getUser_name());
			pstmt.setString(5, member.getUser_num1() + "-" + member.getUser_num2() + "-" + member.getUser_num3());
			pstmt.setString(6, member.getUser_birthday());
			pstmt.setInt(7, member.getUser_loc1());
			pstmt.setInt(8, member.getUser_loc2());
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
	
	public int loginMember(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String member_pw = null;
		int re = -1;
		
		try {
			conn = getConnection();
			sql = "SELECT USER_PW FROM MEMBERT WHERE USER_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				re = 0;
				member_pw = rs.getString(1);
				if (member_pw.equals(pw)) re = 1;
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
		
		return re;
	}
	
	public int updateMember_pw(String user_id, String input_pw, String input_newPw1, String input_newPw2) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int re = -1;
		
		try {
			conn = getConnection();
			sql = "SELECT USER_PW FROM MEMBERT WHERE USER_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String user_pw = rs.getString("USER_PW");
				if (user_pw.equals(input_pw)) {
					if (input_newPw1.equals(input_newPw2)) {
						sql = "UPDATE MEMBERT SET USER_PW=? WHERE USER_ID=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, input_newPw1);
						pstmt.setString(2, user_id);
						re = pstmt.executeUpdate();
					} else {
						re = -2;
					}
				} else {
					re = 0;
				}
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
		
		return re;
	}
	
	public int updateMember_nName(String user_id, String user_newnName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int re = -1;
		
		try {
			conn = getConnection();
			sql = "UPDATE MEMBERT SET USER_NNAME=? WHERE USER_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_newnName);
			pstmt.setString(2, user_id);
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
	
	public int updateMember_num(String user_id, String user_newNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int re = -1;
		
		try {
			conn = getConnection();
			sql = "UPDATE MEMBERT SET USER_NUM=? WHERE USER_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_newNum);
			pstmt.setString(2, user_id);
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
	
	public int updateMember_loc(String user_id, String loc1, String loc2) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int re = -1;
		
		try {
			conn = getConnection();
			sql = "UPDATE MEMBERT SET USER_LOC1=?, USER_LOC2=? WHERE USER_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc1);
			pstmt.setString(2, loc2);
			pstmt.setString(3, user_id);
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
	
	public MemberMd getMember(String idOrname, int nOri) { // 1이면 아이디로 검색, 2이면 닉네임으로 검색
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberMd member = null;
		String sql = null;
		
		try {
			conn = getConnection();
			if (nOri == 1) sql = "SELECT USER_ID,USER_PW,USER_NNAME,USER_NAME,USER_NUM,USER_BIRTHDAY,USER_LOC1,USER_LOC2 FROM MEMBERT WHERE USER_ID=?";
			else sql = "SELECT USER_ID,USER_PW,USER_NNAME,USER_NAME,USER_NUM,USER_BIRTHDAY,USER_LOC1,USER_LOC2 FROM MEMBERT WHERE USER_NNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idOrname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String num[] = rs.getString("USER_NUM").split("-");
				member = new MemberMd();
				member.setUser_id(rs.getString("USER_ID"));
				member.setUser_pw(rs.getString("USER_PW"));
				member.setUser_nName(rs.getString("USER_NNAME"));
				member.setUser_name(rs.getString("USER_NAME"));
				member.setUser_num1(num[0]);
				member.setUser_num2(num[1]);
				member.setUser_num3(num[2]);
				member.setUser_birthday(rs.getString("USER_BIRTHDAY"));
				member.setUser_loc1(rs.getInt("USER_LOC1"));
				member.setUser_loc2(rs.getInt("USER_LOC2"));
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
		
		return member;
	}
	
}
