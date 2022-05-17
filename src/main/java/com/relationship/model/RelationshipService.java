package com.relationship.model;

import com.members.model.MembersVO;
import connection.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

public class RelationshipService {
    RelationshipJDBCDAO relateDao = new RelationshipJDBCDAO();
    public boolean addFriend(Integer memberId, Integer targetId) {
        try {
            Connection con = JDBCConnection.getRDSConnection();
            con.setAutoCommit(false);
            Savepoint sp = con.setSavepoint();
            if (!isFriend(memberId, targetId, con) && !isBlock(memberId, targetId, con)) {
                if (!relateDao.accpetInvite(memberId, targetId, con)) {
                    con.rollback(sp);
                } else {
                    con.commit();
                    con.setAutoCommit(true);
                    con.close();
                    return true;
                }
            }
        }catch(SQLException e) {
            return false;
        }
        return false;
    }
    public boolean inviteFreind(Integer memberId, Integer targetId) {
        try {
            Connection con = JDBCConnection.getRDSConnection();
            if(!isFriend(memberId,targetId,con)&&!isBlock(memberId,targetId,con)){
                return relateDao.invite(memberId,targetId,con);
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean addBlock(Integer memberId, Integer targetId) {

        try (Connection con = JDBCConnection.getRDSConnection()){
            con.setAutoCommit(false);
            Savepoint sp =  con.setSavepoint();
            if(!relateDao.deleteFriend(memberId,targetId,con)||!relateDao.block(memberId,targetId,con)){
                con.rollback(sp);
                con.close();
                return false;
            }else{
                con.commit();
                con.setAutoCommit(true);
                con.close();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cancelInvite(Integer memberId,Integer targetId){
        try {
            relateDao.deleteInviting(memberId, targetId);
            return true;
        }catch(SQLException e) {
            return false;
        }
    }
    public boolean deleteFriend(Integer memberId, Integer targetId){
        try(Connection con = JDBCConnection.getRDSConnection()) {
            if (isFriend(memberId, targetId, con)) {
                if(relateDao.deleteFriend(memberId, targetId, con)){
                    return true;
                }
            }
            return false;
        }catch(SQLException e) {
            return false;
        }
    }
    public boolean deleteBlock(Integer memberId, Integer targetId) {
        try(Connection con = JDBCConnection.getRDSConnection()) {
            if(isBlock(memberId,targetId,con)) {
                if (relateDao.deleteBlock(memberId, targetId, con)) {
                    return true;
                }
                return false;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<RelationResult> getFriends(Integer memberId){
        try {
            List<RelationResult> mvos=relateDao.queryByRelationAndMemberId(memberId, 0);
            if(mvos!=null){
                return mvos;
            }else{
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }
    public List<RelationResult> getInviting(Integer memberId) {
        try {
            List<RelationResult> mvos =  relateDao.queryByRelationAndMemberId(memberId,1);
            if(mvos!=null){
                return mvos;
            }else{
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }
    public List<RelationResult> getBlocks(Integer memberId){
        try {
            List<RelationResult> mvos = relateDao.queryByRelationAndMemberId(memberId, 2);
            if (mvos != null) {
                return mvos;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
    public List<RelationResult> getInvited(Integer memberId){
        try {
            List<RelationResult> mvos = relateDao.queryInvited(memberId, 1);
            if (mvos != null) {
                return mvos;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
    public List<RelationResult>  searchFriend(Integer memberId,String Keyword){
        try {
            return relateDao.searchByKeyword(memberId,0,Keyword);
        } catch (SQLException e) {
            return null;
        }
    }
    public List<RelationResult>  searchInviting(Integer memberId,String Keyword){
        try {
            return relateDao.searchByKeyword(memberId,1,Keyword);
        } catch (SQLException e) {
            return null;
        }
    }
    public List<RelationResult>  searchBlock(Integer memberId,String Keyword){
        try {
            return relateDao.searchByKeyword(memberId,2,Keyword);
        } catch (SQLException e) {
            return null;
        }
    }
    public List<RelationResult>  searchInvited(Integer memberId,String Keyword){
        try {
            return relateDao.searchByKeyword(memberId,1,Keyword);
        } catch (SQLException e) {
            return null;
        }
    }
    public List<RelationResult> findRecentFriend(Integer memberId){
        try {
            return relateDao.findRecentFriend(memberId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean isFriend(Integer memberId, Integer targetId, Connection con) throws SQLException {
        return relateDao.isRelation(memberId,targetId,0,con);
    }
    public boolean isInviting(Integer memberId, Integer targetId, Connection con) throws SQLException {
        return relateDao.isRelation(memberId,targetId,1,con);
    }
    public boolean isBlock(Integer memberId, Integer targetId, Connection con) throws SQLException {
        return relateDao.isRelation(memberId,targetId,2,con);
    }




}
