package com.chatroom.model;

import java.util.List;

public class ChatroomService {

	private ChatroomDAO_interface dao;

	public ChatroomService() {
		dao = new ChatroomDAO();
	}

	public ChatroomVO addChatroom() {

		ChatroomVO chatroomVO = new ChatroomVO();

		dao.insert(chatroomVO);

		return chatroomVO;
	}

	public void deleteChatroom(Integer chRoomNo) {
		dao.delete(chRoomNo);
	}

	public ChatroomVO getOneChatroom(Integer chRoomNo) {
		return dao.findByPrimaryKey(chRoomNo);
	}

	public List<ChatroomVO> getAll() {
		return dao.getAll();
	}
}
