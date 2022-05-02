package com.remind.service;

import java.sql.Timestamp;
import java.util.List;

import com.remind.model.RemindDAO;
import com.remind.model.RemindDAO_interface;
import com.remind.model.RemindJDBCDAO;
import com.remind.model.RemindVO;

public class RemindService {
	
	private RemindDAO_interface dao;
	
	public RemindService() {
		dao = new RemindJDBCDAO();// TODO 換JNDI
	}

	/**
	 * 新增一則提醒
	 * @return RemindVO(remindId, memberId, content, time)
	 */
	public RemindVO addRemind(Integer memberId, String content, Timestamp time) {
		RemindVO rVO = new RemindVO();
		
		rVO.setMemberId(memberId);
		rVO.setContent(content);
		rVO.setTime(time);
		dao.insert(rVO);
		return rVO;
	}
		
	/**
	 * 刪除一則提醒
	 */
	public Boolean deleteRemind(Integer id) {
		RemindVO rVO = new RemindVO();
		rVO.setRemindId(id);
		Boolean bool = dao.delete(rVO);
		return bool;
	}

		/**
		 * 修改一則提醒
		 * @return RemindVO(remindId, [content], [time])
		 */
	public RemindVO updateRemind(String content, Timestamp time, Integer id) {
		RemindVO rVO = new RemindVO();
		rVO.setContent(content);
		rVO.setTime(time);
		rVO.setRemindId(id);
		dao.update(rVO);
		return rVO;
	}

	/**
	 * 查詢一則提醒
	 * @return RemindVO(remindId, memberId, content, time)
	 */
	public RemindVO getByRemindId(Integer id) {
		RemindVO rVO = dao.getOneById(id);
		return rVO;
	}
		
	/**
	 * 查詢一會員所有提醒
	 * @return RemindVO的list集合
	 */
	public List<RemindVO> getByMemberId(Integer id){
		List<RemindVO> remindList = dao.getOneByMemberId(id);
		return remindList;
	}
	
	/**
	 * 查詢一會員最近3則提醒
	 * @return RemindVO的list集合
	 */
	public List<RemindVO> getThreeByMemberId(Integer id){
		List<RemindVO> remindList = dao.getThreeByMemberId(id);
		return remindList;
	}

	/**
	 * 查詢一會員最近3則提醒
	 * @return RemindVO的list集合
	 */
	public List<RemindVO> getUndueByMemberId(Integer id){
		List<RemindVO> remindList = dao.getUndueByMemberId(id);
		return remindList;
	}
	
	/**
	 * 查詢下1小時到期的所有提醒
	 * @return RemindVO的list集合
	 */
	public List<RemindVO> getRecentRemind(){
		List<RemindVO> remindList = dao.getAllNow();
		return remindList;
	}
	
}
