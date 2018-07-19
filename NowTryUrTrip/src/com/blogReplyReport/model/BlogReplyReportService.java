package com.blogReplyReport.model;

import java.util.List;

public class BlogReplyReportService {

	private BlogReplyReportDAO_interface dao;

	public BlogReplyReportService() {
		dao = new BlogReplyReportDAO();
	}

	public BlogReplyReportVO addBlogReplyReport(Integer replyNo,
			Integer memId, String rpReason) {

		BlogReplyReportVO blogReplyReportVO = new BlogReplyReportVO();

		blogReplyReportVO.setReplyNo(replyNo);
		blogReplyReportVO.setMemId(memId);
		blogReplyReportVO.setRpReason(rpReason);
		
		dao.insert(blogReplyReportVO);

		return blogReplyReportVO;
	}

	public BlogReplyReportVO updateBlogReplyReport(Integer replyReportNo, String adminChecked, String noRpReason) {

		BlogReplyReportVO blogReplyReportVO = new BlogReplyReportVO();

		blogReplyReportVO.setReplyReportNo(replyReportNo);
		blogReplyReportVO.setAdminChecked(adminChecked);
		blogReplyReportVO.setNoRpReason(noRpReason);
		dao.update(blogReplyReportVO);

		return blogReplyReportVO;
	}

	public void deleteBlogReplyReport(Integer replyReportNo) {
		dao.delete(replyReportNo);
	}

	public BlogReplyReportVO getOneBlogReplyReport(Integer replyReportNo) {
		return dao.findByPrimaryKey(replyReportNo);
	}

	public List<BlogReplyReportVO> getAll() {
		return dao.getAll();
	}
}
