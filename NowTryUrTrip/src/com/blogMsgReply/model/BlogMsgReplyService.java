package com.blogMsgReply.model;

import java.util.List;

public class BlogMsgReplyService {

	private BlogMsgReplyDAO_interface dao;

	public BlogMsgReplyService() {
		dao = new BlogMsgReplyDAO();
	}

	public BlogMsgReplyVO addBlogMsgReply(Integer msgNo, Integer msgMemId,
			String msgContent) {

		BlogMsgReplyVO blogMsgReplyVO = new BlogMsgReplyVO();

		blogMsgReplyVO.setMsgNo(msgNo);
		blogMsgReplyVO.setMsgMemId(msgMemId);
		blogMsgReplyVO.setMsgContent(msgContent);
		
		dao.insert(blogMsgReplyVO);

		return blogMsgReplyVO;
	}

	public BlogMsgReplyVO updateBlogMsgReply(Integer replyNo, String reported, String reviewed, String isBlocked, String blockedReason) {

		BlogMsgReplyVO blogMsgReplyVO = new BlogMsgReplyVO();

		blogMsgReplyVO.setReplyNo(replyNo);
		blogMsgReplyVO.setReported(reported);
		blogMsgReplyVO.setReviewed(reviewed);
		blogMsgReplyVO.setIsBlocked(isBlocked);
		blogMsgReplyVO.setBlockedReason(blockedReason);
		dao.update(blogMsgReplyVO);

		return blogMsgReplyVO;
	}

	public void deleteBlogMsgReply(Integer replyNo) {
		dao.delete(replyNo);
	}

	public BlogMsgReplyVO getOneBlogMsgReply(Integer replyNo) {
		return dao.findByPrimaryKey(replyNo);
	}

	public List<BlogMsgReplyVO> getAll() {
		return dao.getAll();
	}
}
