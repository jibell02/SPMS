package com.khrd.handler;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.SpmsDAO;
import com.khrd.dto.Spms;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class InsertHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		if(request.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/insertForm.jsp";
		}else if(request.getMethod().equalsIgnoreCase("post")){
			request.setCharacterEncoding("utf-8");
			
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String state = request.getParameter("state");
			
			SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
			Date sDate = d.parse(startDate);
			Date eDate = d.parse(endDate);
			
			Connection conn = null;
			
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false); // 테이블 두개가 아니라서 굳이 할 필요는 없었음..
				SpmsDAO dao = SpmsDAO.getInstance();
				
				HttpSession session = request.getSession();
				String no = (String) session.getAttribute("Auth"); 
				
				Spms spms = new Spms(0, name, content, sDate, eDate, state);
				dao.insert(conn, spms);
				
				conn.commit();
				
				response.sendRedirect(request.getContextPath() + "/list.do");
				return null;
			}catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			}finally {
				JDBCUtil.close(conn);
			}
		}
		
		
		return null;
	}

}
