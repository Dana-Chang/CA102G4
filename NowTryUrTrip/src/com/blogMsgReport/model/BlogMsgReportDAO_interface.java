package com.blogMsgReport.model;

import java.util.*;

public interface BlogMsgReportDAO_interface {
          public void insert(BlogMsgReportVO blogMsgReportVO);
          public void update(BlogMsgReportVO blogMsgReportVO);
          public void delete(Integer cmtReportNo);
          public BlogMsgReportVO findByPrimaryKey(Integer cmtReportNo);
          public List<BlogMsgReportVO> getAll();

}
