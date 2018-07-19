package com.blogMsg.model;

import java.sql.Timestamp;
import java.util.List;

public class BlogMsgService {

	private BlogMsgDAO_interface dao;

	public BlogMsgService() {
		dao = new BlogMsgDAO();
	}

	public BlogMsgVO addBlogMsg(Integer articleNo, Integer msgMemId,
			String msgContent) {

		BlogMsgVO blogMsgVO = new BlogMsgVO();

		blogMsgVO.setArticleNo(articleNo);
		blogMsgVO.setMsgMemId(msgMemId);
		blogMsgVO.setMsgContent(msgContent);
//		blogMsgVO.setReported(reported);
//		blogMsgVO.setReviewed(reviewed);
//		blogMsgVO.setIsBlocked(isBlocked);
//		blogMsgVO.setBlockedReason(blockedReason);
		dao.insert(blogMsgVO);

		return blogMsgVO;
	}

	public BlogMsgVO updateBlogMsg(Integer msgNo, Integer articleNo, Integer msgMemId,
			String msgContent, java.sql.Timestamp msgTime, String reported, String reviewed, String isBlocked, String blockedReason) {

		BlogMsgVO blogMsgVO = new BlogMsgVO();

		blogMsgVO.setMsgNo(msgNo);
		blogMsgVO.setArticleNo(articleNo);
		blogMsgVO.setMsgMemId(msgMemId);
		blogMsgVO.setMsgContent(msgContent);
		blogMsgVO.setMsgTime(msgTime);
		blogMsgVO.setReported(reported);
		blogMsgVO.setReviewed(reviewed);
		blogMsgVO.setIsBlocked(isBlocked);
		blogMsgVO.setBlockedReason(blockedReason);
		dao.update(blogMsgVO);

		return blogMsgVO;
	}

	public void deleteBlogMsg(Integer msgNo) {
		dao.delete(msgNo);
	}

	public BlogMsgVO getOneBlogMsg(Integer msgNo) {
		return dao.findByPrimaryKey(msgNo);
	}

	public List<BlogMsgVO> getAll() {
		return dao.getAll();
	}
}
