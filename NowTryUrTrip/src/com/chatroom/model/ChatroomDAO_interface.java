package com.chatroom.model;

import java.util.*;

public interface ChatroomDAO_interface {
          public void insert(ChatroomVO chatRoomVO);
//          public void update(ChatroomVO chatRoomVO);
          public void delete(Integer chRoomNo);
          public ChatroomVO findByPrimaryKey(Integer chRoomNo);
          public List<ChatroomVO> getAll();

}
