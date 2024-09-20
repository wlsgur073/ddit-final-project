package com.probada.user.service;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;

import com.probada.collabo.vo.CollaboVO;
import com.probada.project.vo.ProjectVO;
import com.probada.task.vo.TaskVO;
import com.probada.user.dao.UserDAO;
import com.probada.user.mail.MailHandler;
import com.probada.user.mail.Tempkey;
import com.probada.user.vo.UserTotalCountVO;
import com.probada.user.vo.UserVO;


@Configuration
@PropertySource("classpath:/com/probada/sqlmap/properties/uploadPath.properties")
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	// 이메일 인증
	@Autowired
	private JavaMailSender mailSender;

	private UserDAO userDAO;
	@Value("${mail.template}")
	private String mailTemplate;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserVO getUser(String userId) throws Exception {
		UserVO user = userDAO.selectUserById(userId);
		return user;
	}

	@Override
	public void registUser(UserVO user) throws Exception {
		userDAO.insertUser(user);

		// 인증키 생성
		String key = new Tempkey().getKey(10, false);

		// 인증키 DB에 저장
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", user.getUserId());
		paramMap.put("authkey", key);
		userDAO.createAuthkey(paramMap);

//		URI uri = new URI("/resources/asserts/mail/mailTemplate.html");
		Path mail = Paths.get(mailTemplate);
		
		
		String str = FileUtils.readFileToString(mail.toFile());
		
		String text = str.replace("#{userId}", user.getUserId());
		
		// 메일 발송
		MailHandler sendMail;
		try {
			sendMail = new MailHandler(mailSender);
			sendMail.setSubject("[probada] 회원가입 인증메일");
			sendMail.setText(text
//					.append("<a href='http://localhost/emailConfirm?userId=").append(user.getUserId())
//					.append("<a href='http://192.168.143.7/emailConfirm?userId=").append(user.getUserId())
//					.append("' target='_blenk'>가입 완료를 위해 이메일 이곳을 눌러주세요.</a>").toString()
					);
//			.append("&key=").append(key)
			sendMail.setFrom("probadahelp@gmail.com", "probada");
			sendMail.setTo(user.getUserId());
			sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyUser(UserVO user) throws Exception {
		userDAO.updateUser(user);
	}

	// authstatus 1로 변경
	@Override
	public void updateAuthstatus(String email) throws SQLException {
		userDAO.updateAuthstatus(email);
	}

	@Override
	public int idCheck(String email) throws SQLException {
		int ret = 1;
		if (email.equals(userDAO.idCheck(email)))
			ret = 0;
		return ret;
	}

	@Override
	public int nicknameCheck(String username) throws SQLException {
		int ret = 1;
		if (username.equals(userDAO.nicknameCheck(username)))
			ret = 0;
		return ret;
	}

	@Override
	public UserVO login(UserVO user) throws SQLException {
		UserVO userVO = null;

		userVO = userDAO.login(user);
		if (userVO == null) {
			userVO = new UserVO();
		}
		return userVO;
	}

	@Override
	public int isAccount(String email) throws SQLException {
		return userDAO.isAccount(email);
	}

	public int pwdCheck(UserVO user) throws SQLException {

		return userDAO.pwdCheck(user);
	}

	@Override
	public void setUserUploadUsage(UserVO userVO) throws SQLException {
		userDAO.setUserUploadUsage(userVO);
	}

	@Override
	public List<UserVO> getUserByProjNo(String projNo) throws SQLException {

		List<UserVO> userListForProjDetail = userDAO.selectUserByProjNo(projNo);

		return userListForProjDetail;
	}

	@Override
	public List<String> getUserProjNoList(String userId) throws SQLException {
		return userDAO.getUserProjNoList(userId);
	}

	@Override
	public String pwdPicker(String userId) throws SQLException {
		return userDAO.pwdPicker(userId);
	}

	@Override
	public String getPwdByUserId(String userId) throws SQLException {
		return userDAO.pwdPiker(userId);
	}

	@Override
	public void removeUser(String userId) throws SQLException {
		userDAO.deleteUser(userId);
	}

	@Override
	public int createAuthkey(Map<String, String> paramMap) throws SQLException {
		return userDAO.createAuthkey(paramMap);
	}

	@Override
	public String selectAuthkey(String userId) throws SQLException {
		return userDAO.selectAuthkey(userId);
	}

	@Override
	public void setUserPwd(UserVO userVO) throws SQLException {
		userDAO.setUserPwd(userVO);
	}

	// 외부 로그인을 회원가입을 승인하는 메서드
	@Override
	public void registExternalLogin(UserVO userVO) throws SQLException{
		userDAO.registExternalLogin(userVO);
	}
	
//	유저의 총량을 반환하는 메서드
	public UserTotalCountVO setUserTotalCount(UserTotalCountVO userVO) throws SQLException {
		return userDAO.setUserTotalCount(userVO);
	}

	@Override
	public List<TaskVO> getUserTaskList(String userId) throws SQLException {
		return userDAO.getUserTaskList(userId);
	}

	@Override
	public List<ProjectVO> getUserProjectList(String userId) throws SQLException {
		return userDAO.getUserProjectList(userId);
	}

	@Override
	public List<UserVO> getPriUser() throws SQLException {
		
		return userDAO.selectTotalUserByPrivacy();
	}

	@Override
	public List<CollaboVO> getCollaboListByUserId(String userId) throws SQLException {
		return userDAO.selectCollaboListByUserId(userId);
	}

	@Override
	public List<UserVO> getMemberAchievementList(String projNo) throws SQLException {
		return userDAO.getMemberAchievementList(projNo);
	}

	@Override
	public UserVO getUserByNickname(String nickname) throws SQLException {
		return userDAO.getUserByNickname(nickname);
	}

	@Override
	public List<UserVO> getUserByCprojNo(String cprojNo) throws SQLException {
		
		List<UserVO> userListForCprojDetail = userDAO.selectUserByCprojNo(cprojNo);

		return userListForCprojDetail;
	}

	@Override
	public List<UserVO> getCMemberAchievementList(String cprojNo) throws SQLException {
		return userDAO.getCMemberAchievementList(cprojNo);
		
	}

	
}
