package com.picture.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.album.model.AlbumVO;
import com.common.exception.JDBCException;
import com.common.model.MappingTableDto;
import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.mysql.cj.exceptions.RSAException;

import connection.DruidConnection;
import connection.JDBCConnection;

public class PictureJDBCDAO implements PictureDAO_Interface {

    public PictureVO insert(PictureVO pv, Connection con) {
        String sql = "INSERT INTO picture(url, file_key, file_name, size) VALUES(?,?,?,?)";
        if (con != null) {
            try {
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 0;
                stmt.setString(++index, pv.getUrl());
                stmt.setString(++index, pv.getFileKey());
                stmt.setString(++index, pv.getFileName());
                stmt.setLong(++index, pv.getSize());
                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    pv.setPictureId(rs.getInt(1));
                }
                rs.close();
                stmt.close();
                return pv;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public PictureVO insert(PictureVO pictureVO) {
        Connection con = JDBCConnection.getRDSConnection();
        PictureVO pictureVO2 = insert(pictureVO, con);
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pictureVO2;
    }

    @Override
    public PictureVO update(PictureVO pv) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<PictureVO> queryPicturesByMapping(MappingTableDto mtd) {
        String sql = "SELECT * FROM picture p JOIN ? ON(p.picture_id=t.picture_id) WHERE ?=?";
        Connection con = JDBCConnection.getRDSConnection();
        if (con != null) {
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                int index = 0;
                stmt.setString(++index, mtd.getTableName1());
                stmt.setString(++index, mtd.getColumn1());
                stmt.setInt(++index, mtd.getId1());
                ResultSet rs = stmt.executeQuery();
                List<PictureVO> pvos = new ArrayList<PictureVO>();
                while (rs.next()) {
                    PictureVO pvo = buildPictureVO(rs);
                    pvos.add(pvo);
                }
                rs.close();
                stmt.close();
                con.close();
                return pvos;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public PictureVO getOneById(Integer pv) {
        Connection con = JDBCConnection.getRDSConnection();
        String sql = "SELECT * FROM picture where picture_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, pv);
            PictureVO pvo = new PictureVO();
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pvo.setPictureId(rs.getInt("picture_id"));
                pvo.setUrl(rs.getString("url"));
                pvo.setCreateTime(rs.getTimestamp("upload_time"));
                pvo.setFileKey(rs.getString("file_key"));
                pvo.setFileName(rs.getString("file_name"));
                pvo.setSize(rs.getLong("size"));
                rs.close();
                stmt.close();
                con.close();
                return pvo;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PictureVO> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer deleteById(Integer pictureId) {
        String sql = "DELETE FROM picture WHERE picture_id=?";
        Connection con = JDBCConnection.getRDSConnection();
        if (con != null) {
            try {
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, pictureId);
                stmt.executeUpdate();
                return pictureId;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        } else {
            return -1;
        }
    }

    @Override
    public boolean delete(PictureVO t) {
        return false;
    }

    @Override
    public PageResult<PictureVO> getPageResult(PageQuery pageQuery) {
        // sql查結果全部指令
        String baseSQL = "SELECT * FROM picture p JOIN photos ph ON ph.picture_id = p.picture_id ";
        int total = 0;
        List<PictureVO> pics = new ArrayList<PictureVO>();
        try (Connection con = DruidConnection.getRDSConnection()) {
            if (con != null) {
                //Step1. 取得總筆數
                PreparedStatement stmt = con.prepareStatement(pageQuery.getTotalCountSQL(baseSQL));
                ResultSet rs = stmt.executeQuery();
                rs.next();
                total = rs.getInt(1);
                rs.close();
                stmt.close();

                //Step2. 取得QueryData
                stmt = con.prepareStatement(pageQuery.getQuerySQL(baseSQL));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    PictureVO pvo = buildPictureVO(rs);
                    pics.add(pvo);
                }
                rs.close();
                stmt.close();
                con.close();
            } else {
                throw new JDBCException("getPageResult() ::: Connection con is NULL !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new PageResult<PictureVO>(pageQuery, pics, total);
    }


    public PictureVO buildPictureVO(ResultSet rs) throws SQLException {
        PictureVO pvo = new PictureVO();
        pvo.setPictureId(rs.getInt("picture_id"));
        pvo.setUrl(rs.getString("url"));
        pvo.setCreateTime(rs.getTimestamp("upload_time"));
        pvo.setFileKey(rs.getString("file_key"));
        pvo.setFileName(rs.getString("file_name"));
        pvo.setSize(rs.getLong("size"));
        return pvo;
    }
}
