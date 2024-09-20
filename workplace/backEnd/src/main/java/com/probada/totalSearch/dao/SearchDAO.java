package com.probada.totalSearch.dao;

import java.util.List;

import com.probada.project.vo.ProjectVO;
import com.probada.totalSearch.vo.SearchProjVO;
import com.probada.user.vo.UserVO;

public interface SearchDAO {

	public UserVO selectUserByNickName(String nickName);
	
	public List<UserVO> selectUserBySearch(String searchDate);
	
	public List<ProjectVO> selectProjectBySearch(String searchDate);
	
	public List<ProjectVO> selectProjectListByAutoComplate();
	
	public List<SearchProjVO> selectPopProjBySearch();
		
		
	public List<String> selectPLBySearch(String searchDate);

	public List<String> selectPopPLBySearch();
	
	public List<SearchProjVO> selectCollaboListByAutoComplate();
	public List<SearchProjVO> selectCollaboListBySearch(String searchDate);
	public List<String> selectCollaboPLBySearch(String searchDate);
	
	public String selectTagNoByTagName(String tagName);

}
