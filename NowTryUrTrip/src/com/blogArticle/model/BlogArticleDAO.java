package com.blogArticle.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.blogArticle.model.jdbcUtil_CompositeQuery_BlogArticle;

public class BlogArticleDAO implements BlogArticleDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA107G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT =
			"INSERT INTO blogArticle (articleNo, authorNo, articleTime, viewable, reported, reviewed, isBlocked, blockedReason, articleContent, articleTitle) VALUES (BlogArticle_seq.NEXTVAL, ?, (select sysdate from dual), ?, 0, 0, 0, '', ?, ?)";
	private static final String UPDATE = 
			"UPDATE blogArticle set authorNo=?, viewable=?, reported=?, reviewed=?, isBlocked=?, blockedReason=?, articleContent=?, articleTitle=? where articleNo = ?";
	private static final String DELETE = 
			"DELETE FROM blogArticle where articleNo = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM blogArticle where articleNo = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM blogArticle order by articleTime";
	
	@Override
	public void insert(BlogArticleVO blogArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, blogArticleVO.getAuthorNo());
			pstmt.setString(2, blogArticleVO.getViewable());
			pstmt.setString(3, blogArticleVO.getArticleContent());
			pstmt.setString(4, blogArticleVO.getArticleTitle());	

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(BlogArticleVO blogArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, blogArticleVO.getAuthorNo());
			pstmt.setString(2, blogArticleVO.getViewable());
			pstmt.setString(3, blogArticleVO.getReported());
			pstmt.setString(4, blogArticleVO.getReviewed());
			pstmt.setString(5, blogArticleVO.getIsBlocked());	
			pstmt.setString(6, blogArticleVO.getBlockedReason());
			pstmt.setString(7, blogArticleVO.getArticleContent());
			pstmt.setString(8, blogArticleVO.getArticleTitle());
			pstmt.setInt(9, blogArticleVO.getArticleNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer articleNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public BlogArticleVO findByPrimaryKey(Integer articleNo) {

		BlogArticleVO blogArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 單筆查詢
				blogArticleVO = new BlogArticleVO();
				blogArticleVO.setArticleNo(rs.getInt("articleNo"));
				blogArticleVO.setAuthorNo(rs.getInt("authorNo"));
				blogArticleVO.setArticleTime(rs.getTimestamp("articleTime"));
				blogArticleVO.setViewable(rs.getString("viewable"));
				blogArticleVO.setReported(rs.getString("reported"));
				blogArticleVO.setReviewed(rs.getString("reviewed"));
				blogArticleVO.setIsBlocked(rs.getString("isBlocked"));	
				blogArticleVO.setBlockedReason(rs.getString("blockedReason"));
				blogArticleVO.setArticleContent(rs.getString("articleContent"));
				blogArticleVO.setArticleTitle(rs.getString("articleTitle"));			
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return blogArticleVO;
	}

	@Override
	public List<BlogArticleVO> getAll() {
		List<BlogArticleVO> list = new ArrayList<BlogArticleVO>();
		BlogArticleVO blogArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// blogArticleVO �]�٬� Domain objects
				blogArticleVO = new BlogArticleVO();
				blogArticleVO.setArticleNo(rs.getInt("articleNo"));
				blogArticleVO.setAuthorNo(rs.getInt("authorNo"));
				blogArticleVO.setArticleTime(rs.getTimestamp("articleTime"));
				blogArticleVO.setViewable(rs.getString("viewable"));
				blogArticleVO.setReported(rs.getString("reported"));
				blogArticleVO.setReviewed(rs.getString("reviewed"));
				blogArticleVO.setIsBlocked(rs.getString("isBlocked"));	
				blogArticleVO.setBlockedReason(rs.getString("blockedReason"));
				blogArticleVO.setArticleContent(rs.getString("articleContent"));
				blogArticleVO.setArticleTitle(rs.getString("articleTitle"));
				list.add(blogArticleVO); // Store the row in the list

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<BlogArticleVO> getAll(Map<String, String[]> map) {
		List<BlogArticleVO> list = new ArrayList<BlogArticleVO>();
		BlogArticleVO blogArticleVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from blogArticle "
		          + jdbcUtil_CompositeQuery_BlogArticle.get_WhereCondition(map)
		          + "order by articleNo";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				blogArticleVO = new BlogArticleVO();
				blogArticleVO.setArticleNo(rs.getInt("articleNo"));
				blogArticleVO.setAuthorNo(rs.getInt("authorNo"));
				blogArticleVO.setArticleTime(rs.getTimestamp("articleTime"));
				blogArticleVO.setViewable(rs.getString("viewable"));
				blogArticleVO.setReported(rs.getString("reported"));
				blogArticleVO.setReviewed(rs.getString("reviewed"));
				blogArticleVO.setIsBlocked(rs.getString("isBlocked"));	
				blogArticleVO.setBlockedReason(rs.getString("blockedReason"));
				blogArticleVO.setArticleContent(rs.getString("articleContent"));
				blogArticleVO.setArticleTitle(rs.getString("articleTitle"));
				list.add(blogArticleVO); // Store the row in the list

			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}