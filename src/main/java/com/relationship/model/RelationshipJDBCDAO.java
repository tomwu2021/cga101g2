package com.relationship.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.members.model.MembersVO;
import com.mysql.cj.protocol.Resultset;

import connection.JDBCConnection;

import javax.xml.transform.Result;


public class RelationshipJDBCDAO {
	
// 好友邀請
	public Integer invite(Integer memberId,Integer targetId,Connection con) throws SQLException {
		String sql = " INSERT INTO relationship(member_id,target_id,relation_type) VALUE(?,?,1) ";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index, memberId);
		stmt.setInt(++index, targetId);
		int count = stmt.executeUpdate();
		System.out.println("inviteCount:"+count);
		return count;
	}
	public Integer invite(Integer memberId,Integer targetId) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		return invite(memberId,targetId,con);
	}

	
	public boolean accpetInvite(Integer memberId,Integer targetId,Connection con) throws SQLException {
		String sql = " INSERT INTO relationship(member_id,target_id,relation_type) "
				+ " VALUE(?,?,0) ";
		if(deleteInviting(memberId,targetId,con)) {
			PreparedStatement stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setInt(++index, memberId);
			stmt.setInt(++index, targetId);
			int count = stmt.executeUpdate();
			stmt = con.prepareStatement(sql);
			index = 0;
			stmt.setInt(++index, targetId);
			stmt.setInt(++index, memberId);
			int count2 = stmt.executeUpdate();
			if(count == 1 && count2==1) {
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean accpetInvite(Integer memberId,Integer targetId) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		return accpetInvite(memberId,targetId,con);
	}

	public boolean block(Integer memberId,Integer targetId,Connection con) throws SQLException {
		String sql = " INSERT INTO relationship(member_id,target_id,relation_type) VALUE(?,?,2) ";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index, memberId);
		stmt.setInt(++index, targetId);
		int count = stmt.executeUpdate();
		stmt.close();
		if(count == 1) {
			return true;
		}
		return false;
	}

	public boolean deleteInviting(Integer memberId,Integer targetId,Connection con) throws SQLException {
		String sql = " DELETE FROM relationship WHERE relation_type = 1 "
				+ " AND ((member_id = ? AND target_id = ?) OR (member_id = ? AND target_id = ?))";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index, memberId);
		stmt.setInt(++index, targetId);
		stmt.setInt(++index, targetId);
		stmt.setInt(++index, memberId);
		int count = stmt.executeUpdate();
		stmt.close();
		if(count == 1) {
			return true;
		}
		return false;
	}

	public boolean deleteInviting(Integer memberId,Integer targetId) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		boolean  boo= deleteInviting(memberId,targetId,con);
		con.close();
		return boo;
	}

	public boolean deleteFriend(Integer memberId,Integer targetId,Connection con) throws SQLException {
		String sql = " DELETE FROM relationship WHERE relation_type = 0 AND "
				+ " ((member_id = ? AND target_id = ?) OR (member_id = ? AND target_id = ?)) ";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		System.out.println("m:"+memberId+",t:"+targetId);
		stmt.setInt(++index, memberId);
		stmt.setInt(++index, targetId);
		stmt.setInt(++index, targetId);
		stmt.setInt(++index, memberId);
		int count = stmt.executeUpdate();
		stmt.close();
		if(count == 2) {
			return true;
		}
		return false;
	}
	public boolean deleteFriend(Integer memberId,Integer targetId) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		return deleteFriend(memberId,targetId,con);
	}

	public boolean deleteBlock(Integer memberId,Integer targetId,Connection con) throws SQLException {
		String sql = " DELETE FROM relationship WHERE relation_type = 2 "
				+ " AND member_id = ? AND target_id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index, memberId);
		stmt.setInt(++index, targetId);
		int count = stmt.executeUpdate();
		stmt.close();
		System.out.println(count);
		if(count == 1) {
			return true;
		}
		return false;
	}

	public boolean deleteBlock(Integer memberId,Integer targetId) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		boolean  boo= deleteBlock(memberId,targetId,con);
		con.close();
		return boo;
	}


//檢查關係方法
	/**
	 * relation_type:0 = 好友
	 * relation_type:1 = 審核中
	 * relation_type:2 = 黑名單
	 * @author Tibame_T14
	 *
	 */
	public Integer isRelation(Integer memberId,Integer targetId,Integer relationType,Connection con) throws SQLException {
		String sql = " SELECT (? IN (SELECT target_id FROM relationship "
				+ " WHERE member_id = ? AND relation_type = ?)) as isRelation FROM relationship "
				+" WHERE member_id = ? LIMIT 1";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index, targetId);
		stmt.setInt(++index, memberId);
		stmt.setInt(++index, relationType);
		stmt.setInt(++index, memberId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		System.out.println(result);
		return result;

	}
	public Integer isInvited(Integer memberId,Integer targetId,Connection con) throws SQLException {
		String sql = " SELECT (? IN (SELECT member_id FROM relationship "
				+ " WHERE target_id = ? AND relation_type = 1)) as isRelation FROM relationship "
				+" WHERE target_id = ? LIMIT 1";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index, targetId);
		stmt.setInt(++index, memberId);
		stmt.setInt(++index, memberId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		System.out.println(result);
		return result;
	}
	public Integer isBlock(Integer memberId,Integer targetId,Connection con) throws SQLException {
		String sql = " SELECT (? IN (SELECT target_id FROM relationship "
				+ " WHERE member_id = ? AND relation_type = 2)) as isRelation FROM relationship "
				+" WHERE member_id = ? LIMIT 1";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index, memberId);
		stmt.setInt(++index, targetId);
		stmt.setInt(++index, targetId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		System.out.println(result);
		return result;
	}

	public List<RelationResult> queryByRelationAndMemberId(Integer memberId, Integer relationType) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = "SELECT m.member_id,m.name,m.account,ra.rank_name,pic.preview_url,pic.url " +
				" FROM relationship r JOIN members m ON(r.target_id = m.member_id) " +
				" JOIN pet p ON(m.member_id = p.member_id) " +
				" JOIN picture pic ON(p.picture_id = pic.picture_id) " +
				" JOIN ranks ra ON(ra.rank_id = m.rank_id) " +
				" WHERE r.member_id = ?  AND r.relation_type = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index,memberId);
		stmt.setInt(++index,relationType);
		ResultSet rs = stmt.executeQuery();
		List<RelationResult> relationMembers = new ArrayList<RelationResult>();
		while(rs.next()) {
			relationMembers.add(makeResult(rs));
		}
		rs.close();
		stmt.close();
		con.close();
		return relationMembers;
	}

	public List<RelationResult> queryInvited(Integer memberId, Integer relationType) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = " SELECT m.member_id,m.name,m.account,ra.rank_name,pic.preview_url,pic.url " +
				" FROM relationship r "
				+ " JOIN members m ON(r.member_id = m.member_id) "
				+ " JOIN pet p ON(m.member_id = p.member_id) "
				+ " JOIN picture pic ON(p.picture_id = pic.picture_id) "
				+ " JOIN ranks ra ON(ra.rank_id = m.rank_id) "
				+ " WHERE r.target_id = ?  AND r.relation_type = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index,memberId);
		stmt.setInt(++index,relationType);
		ResultSet rs = stmt.executeQuery();
		List<RelationResult> relationMembers = new ArrayList<RelationResult>();
		while(rs.next()) {
			relationMembers.add(makeResult(rs));
		}
		System.out.println("JDBC="+relationMembers);
		rs.close();
		stmt.close();
		con.close();
		return relationMembers;
	}
	public List<RelationResult> searchByKeyword(Integer memberId, Integer relationType,String Keyword) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = " SELECT m.member_id,m.name,m.email,ra.rank_name,pic.previewUrl,pic.url " +
				" FROM relationship r "
				+ " JOIN members m ON(r.target_id = m.member_id) "
				+ " JOIN pet p ON(m.member_id = p.member_id) "
				+ " JOIN picture pic ON(p.picture_id = pic.picture_id) "
				+ " JOIN ranks ra ON(ra.rank_id = m.rank_id) "
				+ " WHERE r.member_id = ?  AND r.relation_type = ?  AND m.name LIKE '%'+?+'%'";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index,memberId);
		stmt.setInt(++index,relationType);
		ResultSet rs = stmt.executeQuery();
		List<RelationResult> relationMembers = new ArrayList<RelationResult>();
		while(rs.next()) {
			relationMembers.add(makeResult(rs));
		}
		rs.close();
		stmt.close();
		con.close();
		return relationMembers;
	}
	public List<RelationResult> searchInvitedByKeyword(Integer memberId, Integer relationType,String Keyword) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = " SELECT m.member_id,m.name,m.email,ra.rank_name,pic.previewUrl,pic.url " +
				" FROM relationship r "
				+ " JOIN members m ON(r.target_id = m.member_id) "
				+ " JOIN pet p ON(m.member_id = p.member_id) "
				+ " JOIN picture pic ON(p.picture_id = pic.picture_id) "
				+ " JOIN ranks ra ON(ra.rank_id = m.rank_id) "
				+ " WHERE r.target_id = ?  AND r.relation_type = ?  AND m.name LIKE '%'+?+'%'";
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 0;
		stmt.setInt(++index,memberId);
		stmt.setInt(++index,relationType);
		ResultSet rs = stmt.executeQuery();
		List<RelationResult> relationMembers = new ArrayList<RelationResult>();
		while(rs.next()) {
			relationMembers.add(makeResult(rs));
		}

		rs.close();
		stmt.close();
		con.close();
		return relationMembers;
	}
	private RelationResult makeResult(ResultSet rs) throws SQLException {
		RelationResult relationMember = new RelationResult();
		relationMember.setMemberId(rs.getInt("m.member_id"));
		relationMember.setName(rs.getString("m.name"));
		relationMember.setAccount(rs.getString("m.account"));
		relationMember.setRankName(rs.getString("ra.rank_name"));
		relationMember.setPreviewUrl(rs.getString("pic.preview_url"));
		relationMember.setUrl(rs.getString("pic.url"));
		return relationMember;
	}

	public List<RelationResult> findRecentFriend(Integer loginId) throws SQLException {
			Connection con = JDBCConnection.getRDSConnection();
			String sql =  " SELECT m.member_id,m.name,cr.chatroom_id,m.account,ra.rank_name,pic.preview_url,pic.url " +
					" FROM relationship r " +
					" 	JOIN members m ON(r.target_id = m.member_id) " +
					" 	JOIN chatroom_member crm ON(m.member_id = crm.member_id) " +
					" 	JOIN chatroom cr ON(cr.chatroom_id = crm.chatroom_id) " +
					" 	JOIN pet p ON(m.member_id = p.member_id) " +
					" 	JOIN picture pic ON(p.picture_id = pic.picture_id) " +
					" 	JOIN ranks ra ON(ra.rank_id = m.rank_id) " +
					" WHERE crm.member_id IN (SELECT target_id FROM relationship WHERE member_id =  ?) " +
					" 	AND r.relation_type = 0 " +
					" 	AND cr.chatroom_type = 0 " +
					" GROUP BY cr.chatroom_id " +
					" ORDER BY cr.chatroom_id DESC " +
					" LIMIT 10 ";
			PreparedStatement stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setInt(++index,loginId);
			ResultSet rs = stmt.executeQuery();
			List<RelationResult> relationMembers = new ArrayList<RelationResult>();
			while(rs.next()) {
				relationMembers.add(makeResult(rs));
			}
			rs.close();
			stmt.close();
			con.close();
			return relationMembers;
		}
}
