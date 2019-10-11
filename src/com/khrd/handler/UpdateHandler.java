package com.khrd.handler;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.SpmsDAO;
import com.khrd.dto.Spms;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

public class UpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("get")) {
			String sNo = request.getParameter("no");
			int no = Integer.parseInt(sNo);
			request.setAttribute("no", no);
			
			Connection conn = null;
				
			try {
				conn = ConnectionProvider.getConnection();
				SpmsDAO dao = SpmsDAO.getInstance();
				Spms spms = dao.selectByNo(conn, no);
				
				request.setAttribute("spms", spms);
				
				return "/WEB-INF/view/updateForm.jsp";
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
			
		}else if(request.getMethod().equalsIgnoreCase("post")) {

			String name = request.getParameter("name");
			String content = request.getParameter("content");
			String sDate = request.getParameter("startDate");
			String eDate = request.getParameter("endDate");
			String state = request.getParameter("state");
			
			SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = d.parse(sDate);
			Date endDate = d.parse(eDate);
			
			Connection conn = null;
			
			try {
				conn = ConnectionProvider.getConnection();
				SpmsDAO dao = SpmsDAO.getInstance();
				Spms spms = new Spms(0, name, content, startDate, endDate, state);
				dao.update(conn, spms);
				
				response.sendRedirect(request.getContextPath() + "/list.do");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn);
			}
		}
		
		return null;
	}

}
