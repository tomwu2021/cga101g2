package com.group_order.model;

import com.common.model.MappingJDBCDAO;
import com.common.model.MappingTableDto;
import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureVO;
import com.product.model.ProductVO;
import connection.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupOrderService {


    private GroupOrderDAO_Interface dao;
    PictureJDBCDAO picDao = new PictureJDBCDAO();

    public GroupOrderService() {
        dao = new GroupOrderJDBCDAO();
    }

    public GroupOrderVO addGroupOrder(Integer productId, Integer endType, Integer finalPrice, Integer minAmount) {
        GroupOrderVO groupOrderVO = new GroupOrderVO();
        groupOrderVO.setProductId(productId);
        groupOrderVO.setEndType(endType);
        groupOrderVO.setFinalPrice(finalPrice);
        groupOrderVO.setMinAmount(minAmount);
        dao.insert(groupOrderVO);
        return groupOrderVO;
    }

    public GroupOrderVO getOneOrder(Integer id) {
    	 try (Connection con = JDBCConnection.getRDSConnection()) {
    		 	GroupOrderVO gvo=dao.getOneById(id);
    		 	System.out.println(gvo.getProductId());
                 ProductVO productVO = gvo.getProductVO();
                 List<PictureVO> picList = picDao.queryShopPicturesByMapping(gvo.getProductId(), con);
                 picList.forEach((p)->{
                     System.out.println(p.getPreviewUrl()+"\n");
                 });
                 productVO.setPictureVOList(picList);
                 gvo.setProductVO(productVO);
             return gvo;
         } catch (Exception e) {
             e.printStackTrace();
             throw new RuntimeException();
         }
    }

    public List<GroupOrderVO> getAll() {

        return dao.getAll();
    }

    public List<GroupOrderVO> getAllByProductId(Integer id) {

        return dao.getAllByProductId(id);
    }
    
	public List<GroupOrderVO> check() {
		 return dao.check();
	}

    public void updateEndTime(Integer id) {

        dao.updateEndTimeByGroupOrderId(id);
    }

    public void updateStatus(Integer id, Integer status) {
        dao.updateStatusByGroupOrderId(id, status);
    }
    public void updateFinalPrice(Integer id, Integer finalPrice) {
        dao.updateFinalPriceByGroupOrderId(id,finalPrice);
    }

    public List<GroupOrderVO> getAllInProgressByProductId(Integer id) {
        return getAllInProgressByProductId(id);
    }

    public List<GroupOrderVO> getAllInProgress(){
        try (Connection con = JDBCConnection.getRDSConnection()) {
            List<GroupOrderVO> gvos = dao.getAllInProgress2(con);
            List<GroupOrderVO> gvos2 = new ArrayList<>();
            for (GroupOrderVO gvo : gvos) {
                ProductVO productVO = gvo.getProductVO();
                List<PictureVO> picList = picDao.queryShopPicturesByMapping(gvo.getProductId(), con);
                picList.forEach((p)->{
                    System.out.println(p.getPreviewUrl()+"\n");
                });
                productVO.setPictureVOList(picList);
                gvo.setProductVO(productVO);
                gvos2.add(gvo);
            }
            System.out.println(gvos2);
            return gvos2;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
    

    public PageResult<GroupOrderVO> getPageResult(PageQuery pq) {
       return dao.getPageResult(pq);
    }
}
