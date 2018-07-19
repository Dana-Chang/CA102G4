package com.blogMsgReport.model;

import java.util.List;

public class BlogMsgReportService {

	private BlogMsgReportDAO_interface dao;

	public BlogMsgReportService() {
		dao = new BlogMsgReportDAO();
	}

	public BlogMsgReportVO addBlogMsgReport(Integer msgNo,
			Integer memId, String rpReason) {

		BlogMsgReportVO blogMsgReportVO = new BlogMsgReportVO();

		blogMsgReportVO.setMsgNo(msgNo);
		blogMsgReportVO.setMemId(memId);
		blogMsgReportVO.setRpReason(rpReason);
		
		dao.insert(blogMsgReportVO);

		return blogMsgReportVO;
	}

	public BlogMsgReportVO updateBlogMsgReport(Integer cmtReportNo, String adminChecked, String noRpReason) {

		BlogMsgReportVO blogMsgReportVO = new BlogMsgReportVO();

		blogMsgReportVO.setCmtReportNo(cmtReportNo);
		blogMsgReportVO.setAdminChecked(adminChecked);
		blogMsgReportVO.setNoRpReason(noRpReason);
		dao.update(blogMsgReportVO);

		return blogMsgReportVO;
	}

	public void deleteBlogMsgReport(Integer cmtReportNo) {
		dao.delete(cmtReportNo);
	}

	public BlogMsgReportVO getOneBlogMsgReport(Integer cmtReportNo) {
		return dao.findByPrimaryKey(cmtReportNo);
	}

	public List<BlogMsgReportVO> getAll() {
		return dao.getAll();
	}
}
