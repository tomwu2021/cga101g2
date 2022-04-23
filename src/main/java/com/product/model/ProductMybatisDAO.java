package com.product.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.product.mapper.ProductMapper;

import connection.MyBatisUtil;

public class ProductMybatisDAO implements ProductMapper {

	@Override
	public int insert(ProductVO productVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(ProductVO productVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProductVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getAll() {
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSessionTest();
			ProductMapper mapper = session.getMapper(ProductMapper.class);
			return mapper.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		ProductMybatisDAO dao = new ProductMybatisDAO();
		dao.getAll();
	}
}
