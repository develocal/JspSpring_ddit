package com.bms.service.community;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bms.dao.community.CommunityDao;
import com.bms.dto.community.CommunityVO;
import com.bms.dto.notice.NoticeVO;
import com.bms.request.paging.PageMaker;
import com.bms.request.paging.SearchCriteria;

public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	private CommunityDao communityDao;
	public void setCommunityDao(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}
	
	@Override
	public List<CommunityVO> getCommunityMain() throws SQLException {
		List<CommunityVO> community = communityDao.selectCommunityMain();
		return community;
	}
	@Override
	public Map<String, Object> getCommunityAll(SearchCriteria cri) throws SQLException {
		Map<String,Object> dataMap=new HashMap<String,Object>();
		
		//현재 page 번호에 맞는 리스트를 perPageNum 개수 만큼 가져오기.
		List<NoticeVO> communityList=communityDao.selectCommunityAll(cri);
		
		//전체 board 개수
		int totalCount=communityDao.selectCommunityCriteriaTotalCount(cri);
		
		//PageMaker 생성.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("communityList", communityList);
		dataMap.put("pageMaker",pageMaker);
		
		return dataMap;
	}

	@Override
	public CommunityVO getCommunityByCno(String community_code) throws SQLException {
		communityDao.increaseViewCnt(community_code);
		CommunityVO community = communityDao.selectCommunityByCno(community_code);
		return community;
	}

	@Override
	public void mobileCommunityRegist(CommunityVO community) throws SQLException {
		communityDao.mobileCommunityRegist(community);
	}

	@Override
	public void mobileCommunityDelete(String community_code) throws SQLException {
		communityDao.mobileCommunityDelete(community_code);
	}

	@Override
	public void mobileCommunityModify(CommunityVO community) throws SQLException {
		communityDao.mobileCommunityModify(community);
	}

	@Override
	public CommunityVO getCommunityByCnoNoCnt(String community_code) throws SQLException {
		CommunityVO community = communityDao.selectCommunityByCno(community_code);
		return community;
	}

}
