package com.articleReport.model;

import java.sql.Timestamp;
import java.util.List;

public class ArticleReportService {

	private ArticleReportDAO_interface dao;

	public ArticleReportService() {
		dao = new ArticleReportDAO();
	}

	public ArticleReportVO addArticleReport(Integer articleNo,Integer memId,
			String rpReason,  String adminChecked, String noRpReason) {

		ArticleReportVO articleReportVO = new ArticleReportVO();

		articleReportVO.setArticleNo(articleNo);
		articleReportVO.setMemId(memId);
		articleReportVO.setRpReason(rpReason);
		articleReportVO.setAdminChecked(adminChecked);
		articleReportVO.setNoRpReason(noRpReason);
		dao.insert(articleReportVO);

		return articleReportVO;
	}

	public ArticleReportVO updateArticleReport(Integer reportNo, String adminChecked, String noRpReason) {

		ArticleReportVO articleReportVO = new ArticleReportVO();

		articleReportVO.setReportNo(reportNo);
		articleReportVO.setAdminChecked(adminChecked);
		articleReportVO.setNoRpReason(noRpReason);
		dao.update(articleReportVO);

		return articleReportVO;
	}

	public void deleteArticleReport(Integer reportNo) {
		dao.delete(reportNo);
	}

	public ArticleReportVO getOneArticleReport(Integer reportNo) {
		return dao.findByPrimaryKey(reportNo);
	}

	public List<ArticleReportVO> getAll() {
		return dao.getAll();
	}
}
