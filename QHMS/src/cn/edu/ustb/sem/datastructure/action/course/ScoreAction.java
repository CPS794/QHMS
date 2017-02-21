/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File SccoreAction.java
 * @Time Jul 2, 2016 1:17:16 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.action.course;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import cn.edu.ustb.sem.datastructure.action.BaseAction;
import cn.edu.ustb.sem.datastructure.po.user.User;
import cn.edu.ustb.sem.datastructure.service.course.GradeService;
import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;
import net.sf.json.JSONObject;

/**
 * @author Smile
 * @Description
 */
@Namespace("/")
public class ScoreAction extends BaseAction {
	private static final long	serialVersionUID	= 8534804103809545543L;
	private static Logger		logger				= Logger.getLogger(ScoreAction.class);

	@Action(value = "scoreManage", results = { @Result(name = "scoreManage",
			location = "/WEB-INF/page/admin/manage/scoreManage.jsp") })
	public String scoreManage() {
		logger.debug("This is Score Manage Page!");
		return "scoreManage";
	}

	@Action(value = "scoreList")
	public void getScoreList() throws IOException {
		User user = (User) getSession("CurrentUser");
		JSONObject scoreInfo = new JSONObject();
		if (user.getType() == UserType.admin.getValue()) {
			logger.debug("Score List!");
			scoreInfo.element(SUCCESS, true);
			scoreInfo.element("chapters", GradeService.chaptersWithGrade());
			scoreInfo.element("grades", GradeService.getGrades());
		}else{
			scoreInfo.element(SUCCESS, false);
			scoreInfo.element("message", "You're not allowed to do this!");
		}
		writeJson(scoreInfo);
	}
}
