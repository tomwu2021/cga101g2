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

import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.picture.model.PictureJDBCDAO;
import org.apache.http.cookie.params.CookieSpecPNames;

import com.picture.model.PictureVO;

import connection.JDBCConnection;

public class AlbumJDBCDAO implements AlbumDAO_Interface {

    private AlbumVO buildAlbumVOAfter(ResultSet rs) throws SQLException {
        AlbumVO albumVO = new AlbumVO();
        albumVO.setAlbumId(rs.getInt("album_id"));
        albumVO.setMemberId(rs.getInt("member_id"));
        albumVO.setName(rs.getString("name"));
        albumVO.setCreateTime(rs.getTimestamp("create_time"));
        albumVO.setAuthority(rs.getInt("authority"));
        albumVO.setCoverId(rs.getInt("cover_id"));
        return albumVO;
    }

    @Override
    public AlbumVO insert(AlbumVO albumvo) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "INSERT INTO album(member_id,name,authority,cover_id) VALUES(?,?,0,?);";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int index = 0;
            stmt.setInt(++index, albumvo.getMemberId());
            stmt.setString(++index, albumvo.getName());
            stmt.setInt(++index, albumvo.getCoverId());
            stmt.executeUpdate();
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
    public AlbumVO makeDefaultAlbum(Integer memberId) throws SQLException {
        Connection con = JDBCConnection.getRDSConnection();
        AlbumVO albumvo = makeDefaultAlbum(memberId,con);
        con.close();
        return albumvo;
    }
    public AlbumVO makeDefaultAlbum(Integer memberId, Connection con) {
        String sql = "INSERT INTO album(member_id,name,authority,cover_id) VALUES(?,?,0,999);";
        AlbumVO albumvo = new AlbumVO();
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int index = 0;
            stmt.setInt(++index, memberId);
            stmt.setString(++index, "?????????");
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            albumvo.setMemberId(memberId);
            while (rs.next()) {
                albumvo.setAlbumId(rs.getInt(1));
            }
            albumvo.setName("?????????");
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
        return null;
    }

    @Override
    public AlbumVO getOneById(Integer id) {
        String sql = " SELECT * FROM album WHERE album_id = ? ";
        try (Connection con = JDBCConnection.getRDSConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            AlbumVO albumVO = buildAlbumVOAfter(rs);
            return albumVO;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateAlbumName(Integer albumId, String albumName) {
        if (albumId != null) {
            Connection con = JDBCConnection.getRDSConnection();
            String sql = "UPDATE album SET name=? WHERE album_id=? ";
            if (isAlbum(albumId, con)) {
                try {
                    PreparedStatement stmt = con.prepareStatement(sql);
                    int index = 0;
                    stmt.setString(++index, albumName);
                    stmt.setInt(++index, albumId);
                    stmt.executeUpdate();
                    stmt.close();
                    con.close();
                    return true;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean updateAlbumAuthority(Integer albumId, Integer authority) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "UPDATE album SET authority=? WHERE album_id=? ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            int index = 0;
            stmt.setInt(++index, authority);
            stmt.setInt(++index, albumId);
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCover(Integer albumId, Integer coverId) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "UPDATE album SET cover_id=? WHERE album_id=? ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            int index = 0;
            stmt.setInt(++index, coverId);
            stmt.setInt(++index, albumId);
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public PageResult<AlbumVO> getPersonalAlbum(Integer memberId, Integer loginId, PageQuery pageQuery) {
//		String sql = " SELECT * FROM album al JOIN picture p ON(al.cover_id=p.picture_id) WHERE al.member_id = ?; ";
        String countSQL = "SELECT count(*) FROM album a JOIN picture p ON(a.cover_id=p.picture_id) " +
                " WHERE a.member_id = ? " + // -- ?????????ID
                "  AND (a.authority = 0 OR ? = ? " + 
                "	OR ((SELECT relation_type FROM relationship r " +
                "        WHERE r.member_id = a.member_id AND r.target_id =?  ) = 0))";//-- ?????????ID 
    	String baseSQL = "SELECT * FROM album a JOIN picture p ON(a.cover_id=p.picture_id) " +
                " WHERE a.member_id = ? " + // -- ?????????ID
                "  AND (a.authority = 0 OR ? = ? " + 
                "	OR ((SELECT relation_type FROM relationship r " +
                "        WHERE r.member_id = a.member_id AND r.target_id =?  ) = 0))";//-- ?????????ID 
        int total = 0;
        List<AlbumVO> avoList = new ArrayList<AlbumVO>();
        try (Connection con = JDBCConnection.getRDSConnection()) {
            // Step1. ???????????????
            PreparedStatement stmt = con.prepareStatement(pageQuery.getTotalCountSQL(countSQL,false));
            int index = 0 ;
            stmt.setInt(++index, memberId);
            stmt.setInt(++index, loginId);
            stmt.setInt(++index, memberId);
            stmt.setInt(++index, loginId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            total = rs.getInt(1);
            rs.close();
            stmt.close();

            // Step2. ??????QueryData
            stmt = con.prepareStatement(pageQuery.getQuerySQL(baseSQL));
            index = 0 ;
            stmt.setInt(++index, memberId);
            stmt.setInt(++index, loginId);
            stmt.setInt(++index, memberId);
            stmt.setInt(++index, loginId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                PictureVO pictureVO = buildPictureVOAfter(rs);
                AlbumVO albumvo = buildAlbumVOAfter(rs);
                albumvo.setPictureVO(pictureVO);
                avoList.add(albumvo);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new PageResult<>(pageQuery, avoList, total);
    }

    @Override
    public List<AlbumVO> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * ?????????member????????????Id
     */
    @Override
    public Integer selectDefaultAlbumByMemberId(Integer memberId) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "SELECT album_id FROM album WHERE member_id = ? " + "ORDER BY create_time ASC LIMIT 1;";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, memberId);
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

    @Override
    public boolean deleteAlbumById(Integer albumId) {
        Connection con = JDBCConnection.getRDSConnection();
        if (isAlbum(albumId, con)) {
            String sql = "DELETE FROM album WHERE album_id = ?";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, albumId);
                stmt.executeUpdate();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return false;
    }

    public boolean isAlbum(Integer albumId, Connection con) {
        if (albumId != null) {
            String sql = "SELECT album_id FROM album where album_id = ?";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, albumId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    rs.getInt(1);
                }
                rs.close();
                stmt.close();
                return true;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean delete(AlbumVO t) {
        // TODO Auto-generated method stub
        return false;
    }

//	public Map<Integer, PictureVO> getFirstPictureOfAlbums(Integer memberId) {
//		Connection con = JDBCConnection.getRDSConnection();
//		String sql = " SELECT a.album_id, p.* FROM picture p " + " JOIN photos ph ON(p.picture_id=ph.picture_id) "
//				+ " JOIN album a ON(ph.album_id=a.album_id) " + " WHERE ph.album_id "
//				+ "	IN(SELECT album_id FROM album WHERE member_id = ?) " + " GROUP BY a.album_id ";
//		Map<Integer, PictureVO> pictureList = new HashMap<Integer, PictureVO>();
//		try {
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setInt(1, memberId);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				PictureVO pictureVo = new PictureJDBCDAO().buildPictureVO(rs);
//				pictureList.put(rs.getInt("album_id"), pictureVo);
//			}
//			rs.close();
//			stmt.close();
//			con.close();
//			return pictureList;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//
//			e.printStackTrace();
//			return null;
//		}
//	}

    private PictureVO buildPictureVOAfter(ResultSet rs) throws SQLException {
        PictureVO pictureVO = new PictureVO();
        pictureVO.setPictureId(rs.getInt("picture_id"));
        pictureVO.setUrl(rs.getString("url"));
        pictureVO.setCreateTime(rs.getTimestamp("create_time"));
        pictureVO.setFileName(rs.getString("file_name"));
        pictureVO.setFileKey(rs.getString("file_key"));
        pictureVO.setSize(rs.getLong("size"));
        pictureVO.setPreviewUrl(rs.getString("preview_url"));
        pictureVO.setPreviewKey(rs.getString("preview_key"));
        return pictureVO;
    }

}
