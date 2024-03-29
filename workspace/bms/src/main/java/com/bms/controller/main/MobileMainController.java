package com.bms.controller.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bms.dto.community.CommunityVO;
import com.bms.dto.notice.NoticeVO;
import com.bms.dto.suggestion.SuggestionVO;
import com.bms.request.paging.SearchCriteria;
import com.bms.service.community.CommunityService;
import com.bms.service.notice.NoticeService;
import com.bms.service.suggestion.SuggestionService;

@Controller
@RequestMapping("/mobile")
public class MobileMainController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private SuggestionService suggestionService;
	
	@RequestMapping(value="/main_superintendent", method=RequestMethod.GET)
	public ModelAndView getMain_sueprintendent(ModelAndView mnv, SearchCriteria cri) throws Exception {
		
		String url = "mobile/main_superintendent";

		List<NoticeVO> notice = noticeService.getNoticeMain();
		List<SuggestionVO> suggestion = suggestionService.getSuggestionMain();
		
		mnv.setViewName(url);
		mnv.addObject("noticeList", notice);
		mnv.addObject("suggestionList", suggestion);
		return mnv;

	}
	
	@RequestMapping(value="/main_resident", method=RequestMethod.GET)
	public ModelAndView getMain_resident(ModelAndView mnv, SearchCriteria cri) throws Exception {
		
		String url = "mobile/main_resident";
		
		List<NoticeVO> notice = noticeService.getNoticeMain();
		List<CommunityVO> community = communityService.getCommunityMain();
		
		mnv.setViewName(url);
		mnv.addObject("noticeList", notice);
		mnv.addObject("communityList", community);
		return mnv;

	}

}
