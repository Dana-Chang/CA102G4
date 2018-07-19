package com.articleReport.model;

import java.util.*;

public interface ArticleReportDAO_interface {
          public void insert(ArticleReportVO articleReportVO);
          public void update(ArticleReportVO articleReportVO);
          public void delete(Integer reportNo);
          public ArticleReportVO findByPrimaryKey(Integer reportNo);
          public List<ArticleReportVO> getAll();

}
