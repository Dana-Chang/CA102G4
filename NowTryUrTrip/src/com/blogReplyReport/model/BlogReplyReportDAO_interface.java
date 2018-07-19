package com.blogReplyReport.model;

import java.util.*;

public interface BlogReplyReportDAO_interface {
          public void insert(BlogReplyReportVO blogReplyReportVO);
          public void update(BlogReplyReportVO blogReplyReportVO);
          public void delete(Integer replyNo);
          public BlogReplyReportVO findByPrimaryKey(Integer replyNo);
          public List<BlogReplyReportVO> getAll();

}
