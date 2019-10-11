package com.khrd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.khrd.dto.Spms;
import com.khrd.jdbc.JDBCUtil;

public class SpmsDAO {
	private static final SpmsDAO dao = new SpmsDAO();
	
	public static SpmsDAO getInstance() {
		return dao;
	}
	
	private SpmsDAO() {
		
	}
	
	public List<Spms> selectList(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from spms";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Spms> list = new ArrayList<>();
			while(rs.next()) {
				Spms s = new Spms(rs.getInt("no"), rs.getString("name"), rs.getString("content"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getString("state"));
				list.add(s);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return null;
	}
	
	public Spms selectByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from spms where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Spms spms = new Spms(rs.getInt("no"), 
						  			 rs.getString("name"), 
						  			 rs.getString("content"), 
						  			 rs.getDate("startDate"), 
						  			 rs.getDate("endDate"),
						  			 rs.getString("state"));
				return spms;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		
		return null;
	}
	
	
	public int insert(Connection conn, Spms spms) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into spms values(null, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, spms.getName());
			pstmt.setString(2, spms.getContent());
			pstmt.setTimestamp(3, new Timestamp(spms.getStartDate().getTime()));
			pstmt.setTimestamp(4, new Timestamp(spms.getEndDate().getTime()));
			pstmt.setString(5, spms.getState());
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}
	
	public int delete(Connection conn, int no) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "delete from spms where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}
	
	public int update(Connection conn, Spms spms) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update spms set name=?, content=?, startDate=?, endDate=?, state=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, spms.getName());
			pstmt.setString(2, spms.getContent());
			pstmt.setTimestamp(3, new Timestamp(spms.getStartDate().getTime()));
			pstmt.setTimestamp(4, new Timestamp(spms.getEndDate().getTime()));
			pstmt.setString(5, spms.getState());
			pstmt.setInt(6, spms.getNo());
			
			return pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}
}
