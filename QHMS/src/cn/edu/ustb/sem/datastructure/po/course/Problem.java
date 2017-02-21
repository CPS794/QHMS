/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File Problem.java
 * @Time May 15, 2016 8:59:58 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.course;

import cn.edu.ustb.sem.datastructure.dao.user.UserDAO;
import cn.edu.ustb.sem.datastructure.dao.user.impl.StudentDAOJdbcImpl;

/**
 * @author Smile
 * @Description
 */
public class Problem {
	private int		id;
	private int		typeId;
	private int		chapterId;
	private String	description;
	private String	option;
	private String	answer;
	private int		point;
	private String	author;
	private String	author_fullname;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the chapterId
	 */
	public int getChapterId() {
		return chapterId;
	}

	/**
	 * @param chapterId
	 *            the chapterId to set
	 */
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * @param option
	 *            the option to set
	 */
	public void setOption(String option) {
		this.option = option;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the author_fullname
	 */
	public String getAuthor_fullname() {
		if (author_fullname == null) {
			UserDAO userDAO = new StudentDAOJdbcImpl();
			String user = userDAO.findById(author).getName();
			if (user != null) {
				author_fullname = "[" + author + "]" + user;
			} else {
				author_fullname = "[Admin]" + "管理员";
			}
		}
		return author_fullname;
	}

	/**
	 * @param author_fullname
	 *            the author_fullname to set
	 */
	public void setAuthor_fullname(String author_fullname) {
		this.author_fullname = author_fullname;
	}

	@Override
	public String toString() {
		return "Problem [id=" + id + ", typeId=" + typeId + ", chapterId=" + chapterId
				+ ", description=" + description + ", option=" + option + ", answer=" + answer
				+ ", point=" + point + ", author=" + author + "]";
	}
}
