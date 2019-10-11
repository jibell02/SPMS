package com.khrd.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.SpmsDAO;
import com.khrd.dto.Spms;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class ListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			SpmsDAO dao = SpmsDAO.getInstance();
			List<Spms> list = dao.selectList(conn);
			request.setAttribute("spms", list);
			
			return "/WEB-INF/view/list.jsp";
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

}
