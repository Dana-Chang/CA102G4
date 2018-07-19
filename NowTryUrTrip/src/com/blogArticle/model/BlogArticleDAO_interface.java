package com.blogArticle.model;

import java.util.*;

public interface BlogArticleDAO_interface {
    public void insert(BlogArticleVO blogArticleVO);
    public void update(BlogArticleVO blogArticleVO);
    public void delete(Integer articleNo);
    public BlogArticleVO findByPrimaryKey(Integer articleNo);
    public List<BlogArticleVO> getAll();
    public List<BlogArticleVO> getAll(Map<String, String[]> map); 
}
