package com.bms.dao.community;

import java.sql.SQLException;
import java.util.List;

import com.bms.dto.community.CommunityVO;
import com.bms.dto.notice.NoticeVO;
import com.bms.request.paging.SearchCriteria;

public interface CommunityDao {
	
	//모바일 커뮤니티 목록 가져오기.
	List<NoticeVO> selectCommunityAll(SearchCriteria cri) throws SQLException;
	
	//모바일 메인 커뮤니티 목록 가져오기.
	List<CommunityVO> selectCommunityMain() throws SQLException;

	//페이징처리 전체 갯수.
	int selectCommunityCriteriaTotalCount(SearchCriteria cri) throws SQLException;

	CommunityVO selectCommunityByCno(String community_code) throws SQLException;

	void mobileCommunityRegist(CommunityVO community) throws SQLException;
	
	// viewcnt 증가
	void increaseViewCnt(String community_code) throws SQLException;

	void mobileCommunityDelete(String community_code) throws SQLException;

	void mobileCommunityModify(CommunityVO community) throws SQLException;
}
