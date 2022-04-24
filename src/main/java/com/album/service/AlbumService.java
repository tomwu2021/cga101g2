package com.album.service;

import com.album.model.AlbumJDBCDAO;

public class AlbumService {
    AlbumJDBCDAO albumJDBCDAO = new AlbumJDBCDAO();

    public String getName(Integer id) {
        return albumJDBCDAO.getNameById(id);
    }
}
