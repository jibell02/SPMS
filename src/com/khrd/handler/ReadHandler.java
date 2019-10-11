package com.khrd.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.SpmsDAO;
import com.khrd.dto.Spms;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class ReadHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String sNo = request.getParameter("no");
		int no = Integer.parseInt(sNo);
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			SpmsDAO dao = SpmsDAO.getInstance();
			Spms spms = dao.selectByNo(conn, no);
			
			request.setAttribute("spms", spms);
			
			return "/WEB-INF/view/read.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		
		return null;
	}

}
