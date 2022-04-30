package com.album.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.album.model.AlbumJDBCDAO;
import com.album.model.AlbumVO;
import com.common.model.MappingJDBCDAO;
import com.common.model.MappingTableDto;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;

public class AlbumService {

	AlbumJDBCDAO albumDAO = new AlbumJDBCDAO();
	PictureJDBCDAO pjd = new PictureJDBCDAO();
	PictureService pserv = new PictureService();
	MappingJDBCDAO mappingDAO = new MappingJDBCDAO();

	public AlbumVO addAlbum(Integer memberId, String name, Integer authority, Collection<Part> parts) {
		AlbumVO albumVO = buildAlbumVOBefore(memberId, name, authority);
		try {
			List<PictureVO> pvos = pserv.uploadImage(parts);
			System.out.println(pvos);
			for (PictureVO pvo : pvos) {
				System.out.println(pvo);
				albumVO.setCoverId(pvo.getPictureId());
			}
			AlbumVO albumVO2 = albumDAO.insert(albumVO);
			mappingDAO.insertOneMapping(makeAlbumMtd(albumVO2.getAlbumId(), albumVO2.getCoverId()));
			return albumVO2;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteAlbum(Integer memberId, Integer albumId) {
		if (albumId != albumDAO.selectDefaultAlbumByMemberId(memberId)) {
			List<PictureVO> pvoList = pjd.queryPicturesByMapping(makeAlbumMtd(albumId));
			for (PictureVO picv : pvoList) {
				pserv.deletePicture(picv.getPictureId());
			}
			albumDAO.deleteAlbumById(albumId);
			return true;
		}
		return false;
	}

	public boolean updateName(Integer albumId, String albumName) {
		return albumDAO.updateAlbumName(albumId, albumName);
	}

	public boolean updateAuthority(Integer albumId, Integer authority) {
		return albumDAO.updateAlbumAuthority(albumId, authority);
	}

	public boolean updateCover(Integer albumId, Integer coverId) {
		return albumDAO.updateCover(albumId, coverId);
	}

	public List<AlbumVO> getPersonalAlbum(Integer memberId) {
		return albumDAO.getPersonalAlbum(memberId);
	}

	public AlbumVO getAlbumInfo(Integer albumId) {
		AlbumVO albumVO = albumDAO.getOneById(albumId);
		return albumVO;
	}

	private AlbumVO buildAlbumVOBefore(Integer memberId, String name, Integer authority) {
		AlbumVO albumVO = new AlbumVO();
		albumVO.setMemberId(memberId);
		albumVO.setName(name);
		albumVO.setAuthority(authority);
		return albumVO;
	}

	private MappingTableDto makeAlbumMtd(Integer id1) {
		MappingTableDto mtd = new MappingTableDto();
		mtd.setTableName1("photos");
		mtd.setColumn1("album_id");
		mtd.setId1(id1);
		return mtd;
	}

	private MappingTableDto makeAlbumMtd(Integer id1, Integer id2) {
		MappingTableDto mtd = new MappingTableDto();
		mtd.setTableName1("photos");
		mtd.setColumn1("album_id");
		mtd.setColumn2("picture_id");
		mtd.setId1(id1);
		mtd.setId2(id2);
		return mtd;
	}

}
