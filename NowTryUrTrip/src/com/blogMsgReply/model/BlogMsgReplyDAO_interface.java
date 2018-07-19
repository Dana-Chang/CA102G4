package com.blogMsgReply.model;

import java.util.List;

public interface BlogMsgReplyDAO_interface {
    public void insert(BlogMsgReplyVO blogMsgReplyVO);
    public void update(BlogMsgReplyVO blogMsgReplyVO);
    public void delete(Integer replyNo);
    public BlogMsgReplyVO findByPrimaryKey(Integer replyNo);
    public List<BlogMsgReplyVO> getAll();
}
