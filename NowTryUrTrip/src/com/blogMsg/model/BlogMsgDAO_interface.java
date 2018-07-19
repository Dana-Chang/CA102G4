package com.blogMsg.model;

import java.util.List;

public interface BlogMsgDAO_interface {
    public void insert(BlogMsgVO blogMsgVO);
    public void update(BlogMsgVO blogMsgVO);
    public void delete(Integer msgNo);
    public BlogMsgVO findByPrimaryKey(Integer msgNo);
    public List<BlogMsgVO> getAll();
}
