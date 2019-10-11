package com.khrd.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.SpmsDAO;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class DeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String sNo = request.getParameter("no");
		int no = Integer.parseInt(sNo);
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			SpmsDAO dao = SpmsDAO.getInstance();
			dao.delete(conn, no);
			
			conn.commit();
			
			response.sendRedirect(request.getContextPath() + "/list.do");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

}
