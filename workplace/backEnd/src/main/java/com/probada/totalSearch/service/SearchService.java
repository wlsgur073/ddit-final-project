package com.probada.totalSearch.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.project.vo.ProjectVO;
import com.probada.totalSearch.vo.SearchProjVO;
import com.probada.user.vo.UserVO;

public interface SearchService {

	
	public List<UserVO> getResultSearchUser(String searchResult) throws SQLException;
	
	public List<ProjectVO> getResultSearchProject(String searchResult) throws SQLException;
	
	public List<SearchProjVO> getResultSearchPopProject() throws SQLException;
	
	public List<String> getResultSearchPL(String searchResult) throws SQLException;
	
	public List<String> getPopPLSearch() throws SQLException;
	
	
	public List<SearchProjVO> getCollaboProject() throws SQLException;
	public List<SearchProjVO> getResultSearchCollaboProject(String searchResult) throws SQLException;
	public List<String> getCollaboPL(String searchResult) throws SQLException;
	
	
	public List<ProjectVO> getPublicProject () throws SQLException;

	public UserVO getUserByNickName(String nickName) throws SQLException;
	
	public String getTagNoByTitle(String tagName) throws SQLException;
	
}