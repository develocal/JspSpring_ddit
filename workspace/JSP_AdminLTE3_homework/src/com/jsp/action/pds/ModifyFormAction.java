package com.jsp.action.pds;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class ModifyFormAction implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService=pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/modify";
		
		
		try {
			int pno = Integer.parseInt(request.getParameter("pno"));
			
			PdsVO pds = pdsService.getPdsForModify(pno);
			request.setAttribute("pds", pds);
			
		} catch(Exception e) {
			e.printStackTrace();
			url = "error/500_error";
		}
		
		return url;
	}

}
