package com.album.model;

public class TestAlbum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlbumDAO ado = new AlbumDAO();
		AlbumVO avo = new AlbumVO();
//		for(int i =1;i<=10;i++) {
//			avo.setMember_id(i);
//			AlbumVO avo2 = ado.makeDefaultAlbum(avo);
//			System.out.println("aid:"+avo2.getAlbum_id()+"\nmid:"+avo.getMember_id()+"\nauthority:"+avo2.getAuthority()+"\nname:"+avo2.getName());
//		}
		avo.setMember_id(2);
		avo.setName("好吃食物");
		ado.insert(avo);
	}

}
