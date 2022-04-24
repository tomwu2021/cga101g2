package com.album.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.picture.model.PictureJDBCDAO;
import org.apache.http.cookie.params.CookieSpecPNames;

import com.picture.model.PictureVO;

import connection.JDBCConnection;

public class AlbumJDBCDAO implements AlbumDAO_Interface {

    @Override
    public AlbumVO insert(AlbumVO albumvo) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "INERT INTO album(member_id,name,authority) VALUES(?,?,0);";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int index = 0;
            stmt.setInt(++index, albumvo.getMemberId());
            stmt.setString(++index, albumvo.getName());
            stmt.execute();
            albumvo.setAuthority(0);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                albumvo.setAlbumId(rs.getInt(1));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return albumvo;
    }

    public AlbumVO makeDefaultAlbum(AlbumVO albumvo, Connection con) {
        String sql = "INSERT INTO album(member_id,name,authority) VALUES(?,?,0);";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int index = 0;
            stmt.setInt(++index, albumvo.getMemberId());
            stmt.setString(++index, "未分類");
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                albumvo.setAlbumId(rs.getInt(1));
            }
            albumvo.setName("未分類");
            albumvo.setAuthority(0);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return albumvo;
    }

    @Override
    public AlbumVO update(AlbumVO albumvo) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "UPDATE album SET name=?,authority=? WHERE album_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, albumvo.getName());
            stmt.setInt(2, albumvo.getAuthority());
            stmt.setInt(3, albumvo.getAlbumId());
            stmt.execute();
            albumvo.setName(albumvo.getName());
            albumvo.setAuthority(albumvo.getAuthority());
            stmt.close();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return albumvo;
    }

    @Override
    public AlbumVO getOneById(Integer id) {
        return null;
    }

    @Override
    public String getNameById(Integer id) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "SELECT name FROM album WHERE album_id =?";
        String albumName = "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            albumName = rs.getString(1);
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return albumName;
    }

    public String updateAlbumName(AlbumVO albumVO) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "UPDATE album SET name=? WHERE album_id=? ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            int index = 0;
            stmt.setString(++index, albumVO.getName());
            stmt.setString(++index, albumVO.getName());
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return albumVO.getName();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public Integer updateAlbumAuthority(AlbumVO albumVO) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "UPDATE album SET authority=? WHERE album_id=? ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            int index = 0;
            stmt.setInt(++index, albumVO.getAuthority());
            stmt.setInt(++index, albumVO.getAlbumId());
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return albumVO.getAuthority();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public List<AlbumVO> getPersonalAlbum(Integer memberId) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "select name from album where member_id = ?;";
        List<AlbumVO> avoList = new ArrayList<AlbumVO>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            AlbumVO albumvo = new AlbumVO();
            while (rs.next()) {
                albumvo.setAlbumId(rs.getInt("album_id"));
                albumvo.setMemberId(rs.getInt("member_id"));
                albumvo.setAuthority(rs.getInt("authority"));
                albumvo.setCreateTime(rs.getTimestamp("create_time"));
                avoList.add(albumvo);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return avoList;
    }

    @Override
    public List<AlbumVO> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer selectDefaultAlbumByMemberId(Integer mid) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "SELECT album_id FROM album WHERE member_id = ? " + "ORDER BY create_time ASC LIMIT 1;";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, mid);
            ResultSet rs = stmt.executeQuery();
            Integer albumId = null;
            if (rs.next()) {
                albumId = rs.getInt("album_id");
            }
            rs.close();
            stmt.close();
            con.close();
            return albumId;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    public Map<Integer, PictureVO> getFirstPictureOfAlbums(Integer memberId) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = " SELECT a.album_id, p.* FROM picture p "
                + " JOIN photos ph ON(p.picture_id=ph.picture_id) "
                + " JOIN album a ON(ph.album_id=a.album_id) "
                + " WHERE ph.album_id "
                + "	IN(SELECT album_id FROM album WHERE member_id = ?) "
                + " GROUP BY a.album_id ";
        Map<Integer, PictureVO> pictureList = new HashMap<Integer, PictureVO>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PictureVO pictureVo = new PictureJDBCDAO().buildPictureVO(rs);
                pictureList.put(rs.getInt("album_id"), pictureVo);
            }
            rs.close();
            stmt.close();
            con.close();
            return pictureList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(AlbumVO albumVO) {
        Connection con = JDBCConnection.getRDSConnection();
        if (isAlbum(albumVO.getAlbumId(), con) > 0) {
            String sql = "DELETE FROM album WHERE album_id = ?";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, albumVO.getAlbumId());
                stmt.close();
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return false;
    }

    public Integer isAlbum(Integer id, Connection con) {
        String sql = "SELECT album_id FROM album where album_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Integer albumId = null;
            if (rs.next()) {
                albumId = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            return albumId;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }
}
