package com.blogArticle.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class BlogArticleService {

	private BlogArticleDAO_interface dao;

	public BlogArticleService() {
		dao = new BlogArticleDAO();
	}

	public BlogArticleVO addBlogArticle(Integer authorNo,
			String viewable, String articleContent, String articleTitle) {

		BlogArticleVO blogArticleVO = new BlogArticleVO();

		blogArticleVO.setAuthorNo(authorNo);
		blogArticleVO.setViewable(viewable);
		blogArticleVO.setArticleContent(articleContent);
		blogArticleVO.setArticleTitle(articleTitle);
		dao.insert(blogArticleVO);

		return blogArticleVO;
	}

	public BlogArticleVO updateBlogArticle(Integer articleNo,Integer authorNo, java.sql.Timestamp articleTime,
			String viewable, String reported, String reviewed, String isBlocked, String blockedReason, String articleContent, String articleTitle) {

		BlogArticleVO blogArticleVO = new BlogArticleVO();

		blogArticleVO.setArticleNo(articleNo);
		blogArticleVO.setAuthorNo(authorNo);
		blogArticleVO.setArticleTime(articleTime);
		blogArticleVO.setViewable(viewable);
		blogArticleVO.setReported(reported);
		blogArticleVO.setReviewed(reviewed);
		blogArticleVO.setIsBlocked(isBlocked);
		blogArticleVO.setBlockedReason(blockedReason);
		blogArticleVO.setArticleContent(articleContent);
		blogArticleVO.setArticleTitle(articleTitle);
		dao.update(blogArticleVO);

		return blogArticleVO;
	}

	public void deleteBlogArticle(Integer articleNo) {
		dao.delete(articleNo);
	}

	public BlogArticleVO getOneBlogArticle(Integer articleNo) {
		return dao.findByPrimaryKey(articleNo);
	}

	public List<BlogArticleVO> getAll() {
		return dao.getAll();
	}

	public List<BlogArticleVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
