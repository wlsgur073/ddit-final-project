package com.probada.totalSearch.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.probada.project.dao.ProjectDAO;
import com.probada.project.vo.ProjectVO;
import com.probada.totalSearch.dao.SearchDAO;
import com.probada.totalSearch.vo.SearchProjVO;
import com.probada.user.vo.UserVO;

@Service
public class SearchServiceImpl implements SearchService {

	private SearchDAO searchDAO;
	public void setSearchDAO(SearchDAO searchDAO) {
		this.searchDAO = searchDAO;
	}
	
	
	
	@Override
	public List<UserVO> getResultSearchUser(String searchResult) throws SQLException {
		
		 List<UserVO> user = new ArrayList<UserVO>();
		 user = searchDAO.selectUserBySearch(searchResult);
		
		return user;
	}
	@Override
	public List<ProjectVO> getResultSearchProject(String searchResult) throws SQLException {
		
		
		
		 List<ProjectVO> project = new ArrayList<ProjectVO>();
		 project = searchDAO.selectProjectBySearch(searchResult);
		
		 return project;
		 
	}



	@Override
	public List<String> getResultSearchPL(String searchResult) throws SQLException {
		

		 List<String> PL = new ArrayList<String>();
		 PL = searchDAO.selectPLBySearch(searchResult);
		
		 return PL;
	}



	@Override
	public List<SearchProjVO> getResultSearchPopProject() throws SQLException {
		
		 List<SearchProjVO> project = new ArrayList<SearchProjVO>();
		 project = searchDAO.selectPopProjBySearch();
		 return project;
		 
	}



	@Override
	public List<String> getPopPLSearch() throws SQLException {
		
		 List<String> PL = new ArrayList<String>();
		 PL = searchDAO.selectPopPLBySearch();
		
		 return PL;
	}



	@Override
	public List<SearchProjVO> getCollaboProject() throws SQLException {
		
		 List<SearchProjVO> project = new ArrayList<SearchProjVO>();
		 project = searchDAO.selectCollaboListByAutoComplate();
		 return project;
	}



	@Override
	public List<SearchProjVO> getResultSearchCollaboProject(String searchResult) throws SQLException {
		 List<SearchProjVO> project = new ArrayList<SearchProjVO>();
		 project = searchDAO.selectCollaboListBySearch(searchResult);
		 return project;
	}



	@Override
	public List<String> getCollaboPL(String searchResult) throws SQLException {
		 List<String> pl = new ArrayList<String>();
		 pl = searchDAO.selectCollaboPLBySearch(searchResult);
		 return pl;
	}



	@Override
	public List<ProjectVO> getPublicProject() throws SQLException {
		
		 List<ProjectVO> project = new ArrayList<ProjectVO>();
		 project = searchDAO.selectProjectListByAutoComplate();
		 return project;
	}



	@Override
	public UserVO getUserByNickName(String nickName) throws SQLException {
		
		UserVO user = new UserVO();
		
		user = searchDAO.selectUserByNickName(nickName);
		
		return user;
	}



	@Override
	public String getTagNoByTitle(String tagName) throws SQLException {
		
		String tag_no = null;
		
		tag_no = searchDAO.selectTagNoByTagName(tagName);

		return tag_no;
	}

	

	
	
	
	
	



}
