package com.chatRoomMember.model;

import java.util.*;

public interface ChatRoomMemberDAO_interface {
          public void insert(ChatRoomMemberVO chatRoomMemberVO);
          public void update(ChatRoomMemberVO chatRoomMemberVO);
          public void delete(Integer memId,Integer chRoomNo);
          public ChatRoomMemberVO findByPrimaryKey(Integer memId,Integer chRoomNo);
          public List<ChatRoomMemberVO> getAll();

}
