package com.khrd.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.SpmsDAO;
import com.khrd.dto.Spms;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

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
			
		}
		
		return null;
	}

}
