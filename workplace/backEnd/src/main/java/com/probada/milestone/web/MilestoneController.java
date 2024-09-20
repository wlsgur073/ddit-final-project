package com.probada.milestone.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.probada.issue.vo.IssueVO;
import com.probada.milestone.service.MilestoneService;
import com.probada.milestone.vo.MilestoneVO;
import com.probada.user.vo.UserVO;
import com.probada.util.ProjectUtil;

@Controller
@RequestMapping("/app/milestone")
public class MilestoneController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MilestoneController.class);


	@Resource(name="milestoneService")
	MilestoneService milestoneService;
	@Resource(name="projectUtil")
	ProjectUtil projectUtil;

	@RequestMapping("/getMilestoneListByUserId")
	@ResponseBody
	public ResponseEntity<List<MilestoneVO>> getMilestoneListByUserId(@RequestParam(defaultValue="") String userId) throws Exception {
		ResponseEntity<List<MilestoneVO>> entity = null;
		List<MilestoneVO> milestoneList = null;

		try {
			milestoneList = milestoneService.getMilestoneListByUserId(userId);
			entity = new ResponseEntity<List<MilestoneVO>>(milestoneList, HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<MilestoneVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/getMilestoneListByProjNo")
	@ResponseBody
	public ResponseEntity<List<MilestoneVO>> getMilestoneListByProjNo(@RequestParam(defaultValue="") String projNo) throws Exception {
		ResponseEntity<List<MilestoneVO>> entity = null;

		List<MilestoneVO> milestoneList = new ArrayList<MilestoneVO>();

		try {

			milestoneList = milestoneService.getMilestoneListByProjNo(projNo);

			entity = new ResponseEntity<List<MilestoneVO>>(milestoneList,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<MilestoneVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/getMilestoneInfo")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> getMilestoneInfo(HttpServletRequest request, String projNo) throws Exception {
		ResponseEntity<HashMap<String, Object>> entity = null;
		
		List<IssueVO> wholeIssueList = new ArrayList<IssueVO>();
		HttpSession session = request.getSession();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		try {

			UserVO userVO = (UserVO) session.getAttribute("userVO");
			String projTitle = projectUtil.getProjectNameByProjNo(projNo);

			wholeIssueList = milestoneService.getWholeIssueByProjNo(projNo);

			hashMap.put("userVO", userVO);
			hashMap.put("projTitle", projTitle);
			hashMap.put("projNo", projNo);
			hashMap.put("wholeIssueList", wholeIssueList);

			entity = new ResponseEntity<HashMap<String, Object>>(hashMap,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}


	@RequestMapping("/getMilestoneByMileNo")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> getMilestoneByMileNo(@RequestParam(defaultValue="") String mileNo) throws Exception {
		ResponseEntity<HashMap<String, Object>> entity = null;

		MilestoneVO mileVO = new MilestoneVO();

		List<IssueVO> issueList = new ArrayList<IssueVO>();
		List<IssueVO> wholeIssueList = new ArrayList<IssueVO>();

		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		try {

			mileVO = milestoneService.getMilestoneByMileNo(mileNo);
			String projTitle = projectUtil.getProjectNameByProjNo(mileVO.getProjNo());
			issueList = milestoneService.getIssueListByMileNo(mileNo);
			mileVO.setProjTitle(projTitle);
			mileVO.setIssueList(issueList);

			wholeIssueList = milestoneService.getWholeIssueByProjNo(mileVO.getProjNo());

			hashMap.put("mileVO", mileVO);
			hashMap.put("wholeIssueList", wholeIssueList);

			entity = new ResponseEntity<HashMap<String, Object>>(hashMap,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/modifyMilestoneByMileNo")
	@ResponseBody
	public ResponseEntity<MilestoneVO> modifyMilestoneByMileNo(@RequestBody MilestoneVO mileVO) throws Exception {
		ResponseEntity<MilestoneVO> entity = null;


		List<IssueVO> beforeIssueList = new ArrayList<IssueVO>();
		List<String> afterIssueNoList = new ArrayList<String>();
		List<String> beforeIssueNoList = new ArrayList<String>();
		List<String> beforeIssueNoListReset = new ArrayList<String>();
		IssueVO issueVO = new IssueVO();
		try {

			milestoneService.modifyMilestoneDetail(mileVO);

			/*삭제되는 이슈*/
			beforeIssueList = milestoneService.getIssueListByMileNo(mileVO.getMileNo());
			if(beforeIssueList != null) {
				for (IssueVO beforeIssueVO : beforeIssueList) {
					beforeIssueNoListReset.add(beforeIssueVO.getIssueNo());
				}
				List<String> afterIssueNoListReset = mileVO.getIssueNoList();

				if(afterIssueNoListReset != null) {
					beforeIssueNoListReset.removeAll(afterIssueNoListReset);
				}

				/*추가되는 이슈*/
				beforeIssueList = milestoneService.getIssueListByMileNo(mileVO.getMileNo());
				for (IssueVO beforeIssueVO : beforeIssueList) {
					beforeIssueNoList.add(beforeIssueVO.getIssueNo());
				}
				afterIssueNoList = mileVO.getIssueNoList();

				if(afterIssueNoList != null) {
					afterIssueNoList.removeAll(beforeIssueNoList);
					for (String deleteNo : beforeIssueNoListReset) {
						issueVO.setMileNo(mileVO.getMileNo());
						issueVO.setIssueNo(deleteNo);
						milestoneService.removeMileIssueRelation(issueVO);
					}
					for (String insertNo : afterIssueNoList) {
						issueVO.setMileNo(mileVO.getMileNo());
						issueVO.setIssueNo(insertNo);
						milestoneService.registMileIssueRelation(issueVO);
					}
				}
			}

			entity = new ResponseEntity<MilestoneVO>(mileVO,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<MilestoneVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/registMilestoneDetail")
	@ResponseBody
	public ResponseEntity<MilestoneVO> registMilestoneDetail(@RequestBody MilestoneVO mileVO) throws Exception {
		ResponseEntity<MilestoneVO> entity = null;


		List<String> afterIssueNoList = new ArrayList<String>();
		IssueVO issueVO = new IssueVO();
		try {

			milestoneService.registMilestoneDetail(mileVO);

			afterIssueNoList = mileVO.getIssueNoList();
			if(afterIssueNoList != null) {
				for (String insertNo : afterIssueNoList) {
					issueVO.setMileNo(mileVO.getMileNo());
					issueVO.setIssueNo(insertNo);
					milestoneService.registMileIssueRelation(issueVO);
				}
			}

			entity = new ResponseEntity<MilestoneVO>(mileVO,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<MilestoneVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/removeMilestone")
	@ResponseBody
	public ResponseEntity<String> removeMilestone(String mileNo) throws Exception {
		ResponseEntity<String> entity = null;

		try {
			milestoneService.removeMilestone(mileNo);
			System.out.println("삭제 성공했는데 왜 그러냐");
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
}
