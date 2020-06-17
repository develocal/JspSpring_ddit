package com.bms.controller.mypage;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bms.dto.member.MemberVO;
import com.bms.dto.menu.MenuVO;
import com.bms.dto.mypage.ContractVO;
import com.bms.request.paging.SearchCriteria;
import com.bms.service.menu.MenuService;
import com.bms.service.mypage.MypageService;

@Controller
@RequestMapping("/mypage/contract_management")
public class MypageContractController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MypageService mypageService;
	
	@RequestMapping(value="/resident", method=RequestMethod.GET)
	public ModelAndView contract_managementGETPage(HttpServletRequest request, ModelAndView mnv) throws Exception {
		String url = "mypage/contract_management.resident";
		
		List<MenuVO> menuList9 = null;
		List<MenuVO> menuList10 = null;
		List<MenuVO> menuList11 = null;
		
		try{
			menuList9 = menuService.selectUnderMenu("MENU09");
			menuList10 = menuService.selectUnderMenu("MENU10");
			menuList11 = menuService.selectUnderMenu("MENU11");
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		mnv.addObject("menuList9", menuList9);
		mnv.addObject("menuList10", menuList10);
		mnv.addObject("menuList11", menuList11);
		
		mnv.setViewName(url);
		
		return mnv;

	}
	
	
	@RequestMapping("/resident/test")
	public ModelAndView contract_managementGET(SearchCriteria cri, ModelAndView mnv, HttpSession session) throws Exception {
		String url = "mypage/contract_management";

		MemberVO member =  (MemberVO) session.getAttribute("loginUser");
		
		

		Map<String, Object> dataMap = mypageService.getContractList(cri);
		
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping(value="/resident/contractDetail")
	public ModelAndView contract_managementDetail(String constract_code,
											@ModelAttribute("cri") SearchCriteria cri,
											ModelAndView mnv) throws SQLException {
		String url = "mypage/contractDetail";
		
		ContractVO constract = mypageService.getContract(constract_code);
		
		mnv.addObject("constract", constract);
		mnv.setViewName(url);
	 
		return mnv;
	}
	

	
}
