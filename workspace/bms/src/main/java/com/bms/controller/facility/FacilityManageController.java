package com.bms.controller.facility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bms.dto.facility.CheckListVO;
import com.bms.dto.facility.FacilityManageVO;
import com.bms.dto.member.MemberVO;
import com.bms.dto.menu.MenuVO;
import com.bms.request.facility.RequestModifyFacility;
import com.bms.request.facility.RequestRegistCheckList;
import com.bms.request.facility.RequestRegistFacilityManage;
import com.bms.request.paging.FacilitySearchCriteria;
import com.bms.request.paging.SearchCriteria;
import com.bms.service.facility.FacilityManageService;
import com.bms.service.menu.MenuService;

@Controller
@RequestMapping("/facility_stock/facility_management/")
public class FacilityManageController {

	@Resource(name = "facilitypicturePath")
	private String picturePath;

	@Autowired
	private FacilityManageService facilityManageService;

	public void setFacilityManageService(FacilityManageService facilityManageService) {
		this.facilityManageService = facilityManageService;
	}

	@Autowired
	private MenuService menuService;
	String fileName = "";

	@RequestMapping("owner")
	public ModelAndView getItemMain_owner(ModelAndView mnv) throws Exception {

		String url = "facility/manage/list.owner";
		List<MenuVO> menuList1 = menuService.selectUnderMenu("MENU01");
		List<MenuVO> menuList2 = menuService.selectUnderMenu("MENU02");
		List<MenuVO> menuList3 = menuService.selectUnderMenu("MENU03");
		List<MenuVO> menuList4 = menuService.selectUnderMenu("MENU04");

		mnv.addObject("menuList1", menuList1);
		mnv.addObject("menuList2", menuList2);
		mnv.addObject("menuList3", menuList3);
		mnv.addObject("menuList4", menuList4);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping("owner/test")
	public ModelAndView stockList(FacilitySearchCriteria cri, HttpSession session, ModelAndView mnv) throws Exception {
		String url = "facility/manage/list";

		Map<String, Object> dataMap = facilityManageService.selectSearchAllFacilityList(cri);
		MemberVO member_code = (MemberVO) session.getAttribute("loginUser");
		mnv.addAllObjects(dataMap);
		mnv.addObject("member_code", member_code);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping(value = "regist", method = RequestMethod.GET)
	public String registForm(Model model, HttpSession session) throws Exception {

		String url = "facility/manage/regist";
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		model.addAttribute("member_code", member.getMember_code());

		return url;
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public ModelAndView registPost(RequestRegistFacilityManage facilityReq, ModelAndView mnv) throws Exception {
		String url = "facility/manage/regist_success";
		
		FacilityManageVO facility = facilityReq.toFacilityVO();
		facility.setFacility_picture(fileName);

		List<CheckListVO> checkList = new ArrayList<CheckListVO>();

		

		for (CheckListVO check : facilityReq.getCheckList()) {
			check.setCheck_point_facility_code(facility.getCheck_point_facility_code());
			checkList.add(check);
		}
		facilityManageService.insertFacilityDetail(facility, checkList);
		
		mnv.addObject("facility", facility);
		mnv.addObject("checkList", checkList);

		mnv.setViewName(url);

		return mnv;
	}
/*	@RequestMapping("registCheckList")
	public ModelAndView registCheckList(RequestRegistCheckList checkListReq, ModelAndView mnv, String facility_code)throws Exception{
		
		String url = "facility/manage/checkList_regist_success";
		
		CheckListVO chk = checkListReq.toCheckVO();
		FacilityManageVO facility = facilityManageService.selectFacilityByFacilityCode(facility_code);
		chk.setFacility_code(facility.getFacility_code());
		
		List<CheckListVO> checkList = new ArrayList<CheckListVO>();
		System.out.println(checkList.toString());
		for (CheckListVO check : checkListReq.getCheckList()) {
			check.setCheck_point_facility_code(facility.getCheck_point_facility_code());
			checkList.add(check);
			facilityManageService.insertFacilityCheckList(checkList);
		}
		mnv.addObject("checkList", checkList);
		mnv.setViewName(url);
		return mnv;
	}*/
	
	
	@RequestMapping(value="detail",method=RequestMethod.GET)
	public String detail(String facility_code, Model model)throws Exception{
		String url = "facility/manage/detail";
		
		FacilityManageVO facility = facilityManageService.selectFacilityByFacilityCode(facility_code);
		List<CheckListVO> checkList = facilityManageService.selelctCheckListByFacilityCode(facility_code);
		
		model.addAttribute("facility",facility);
		model.addAttribute("checkList",checkList);
		System.out.println(facility.toString());
		System.out.println(checkList);
		return url;
	}
	@RequestMapping(value="modifyForm",method=RequestMethod.GET)
	public String modifyForm(String facility_code, Model model)throws Exception{
		String url = "facility/manage/modify";
		FacilityManageVO facility = facilityManageService.selectFacilityByFacilityCode(facility_code);
		List<CheckListVO> checkList = facilityManageService.selelctCheckListByFacilityCode(facility_code);
		
		model.addAttribute("facility", facility);
		model.addAttribute("checkList", checkList);
		
		return url;
		
	}
	@RequestMapping(value="modify",method=RequestMethod.POST)
	public ModelAndView modifyPost(RequestModifyFacility rmf, String facility_code, ModelAndView mnv)throws Exception{
		String url = "facility/manage/modify_success";
		FacilityManageVO faclity = rmf.toFacilityVO();
		faclity.setFacility_code(facility_code);
		faclity.setFacility_picture(fileName);
		facilityManageService.modifyFacility(faclity);
		
		mnv.addObject("facility", faclity);
		mnv.setViewName(url);
		return mnv;
	}
	@RequestMapping("delete")
	public String deleteFacility(String check_point_facility_code, String facility_code, Model model)throws Exception{
		
		String url = "facility/manage/delete_success";
		FacilityManageVO facility = facilityManageService.selectFacilityByFacilityCode(facility_code);
		model.addAttribute("facility", facility);
		
		facilityManageService.deleteFacility(facility_code);
		
		return url;
	}
	
	@RequestMapping("registCheckList")
	@ResponseBody
	public ResponseEntity<String> registCheckList(String facility_code, String check_point_facility_contents)throws Exception{
		
		ResponseEntity<String> entity = null;
		
		CheckListVO chkList = new CheckListVO();
		chkList.setCheck_point_facility_contents(check_point_facility_contents);
		chkList.setFacility_code(facility_code);
		
		try {
			facilityManageService.insertFacilityCheckListTo(chkList);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		}catch(SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return entity;
	}
	@RequestMapping("modifyCheckList")
	public ModelAndView modifyCheckList(String check_point_facility_code, ModelAndView mnv)throws Exception{
		String url = "facility/manage/modifyCheckList_success";
		
		return mnv;
	}
	@RequestMapping("deleteCheckList")
	public String deleteCheckList(String check_point_facility_code, Model model)throws Exception{
		String url = "facility/manage/deleteCheckList_success";
		
		CheckListVO checkList = facilityManageService.selectCheckListByCheckListCode(check_point_facility_code);
		model.addAttribute("checkList", checkList);
		facilityManageService.deleteCheckList(check_point_facility_code);
		
		return url;
		
	}
	@RequestMapping(value = "picture", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> picture(@RequestParam("pictureFile") MultipartFile multi, String oldPicture)
			throws Exception {

		ResponseEntity<String> entity = null;
		String result = "";
		HttpStatus status = null;

		/* 파일유무확인 */
		if (multi.isEmpty()) {
			result = "파일이 없습니다.!";
			status = HttpStatus.BAD_REQUEST;
		} else
		/* 용량제한 5MB */
		if (multi.getSize() > 1024 * 1024 * 5) {
			result = "용량초과 입니다";
			status = HttpStatus.BAD_REQUEST;
		} else {
			/* 파일저장폴더설정 */
			String uploadPath = picturePath;
			fileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
			File storeFile = new File(uploadPath, fileName);

			// local HDD 에 저장.
			if (!storeFile.exists()) {
				storeFile.mkdirs();
			}
			multi.transferTo(storeFile);

			if (!oldPicture.isEmpty()) {
				File oldFile = new File(uploadPath, oldPicture);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}

			result = fileName;
			status = HttpStatus.OK;

		}
		return new ResponseEntity<String>(result, status);
	}

	@RequestMapping("getPicture")
	@ResponseBody
	public ResponseEntity<byte[]> getPicture(String picture) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String imgPath = this.picturePath;
		try {

			// in=new FileInputStream(imgPath+File.separator+picture);
			in = new FileInputStream(new File(imgPath, picture));

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			in.close();
		}
		return entity;
	}
}
