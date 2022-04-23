package com.sort2.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sort2.mapper.Sort2Mapper;
import connection.MyBatisUtil;

public class Sort2MybatisDAO implements Sort2Mapper{

	@Override
	public int insert(Sort2VO sort2VO) {
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSessionTest();
			Sort2Mapper mapper = session.getMapper(Sort2Mapper.class);
			int result = mapper.insert(sort2VO);
			if (result == 1) {
				session.commit();
				System.out.println("rows inserted = "+result );
				System.out.println("generated key value = " + sort2VO.getSort2Id());
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return -1;
	}

	@Override
	public int deleteById(Integer id) {
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSessionTest();
			Sort2Mapper mapper = session.getMapper(Sort2Mapper.class);
			int result = mapper.deleteById(id);
			if (result == 1) {
				session.commit();
				System.out.println("rows deleteed = "+result );
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return -1;
	}

	@Override
	public int updateById(Sort2VO sort2VO) {
		SqlSession session = null; 
		try {
			session = MyBatisUtil.getSessionTest();
			Sort2Mapper mapper = session.getMapper(Sort2Mapper.class);
			int result = mapper.updateById(sort2VO);
			if (result == 1) {
				session.commit();
				System.out.println("rows updateById = "+result );
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return -1;
	}

	@Override
	public Sort2VO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sort2VO selectBySort2Name(String sort2Name) {
		SqlSession session = null; 
		try {
			session = MyBatisUtil.getSessionTest();
			Sort2Mapper mapper = session.getMapper(Sort2Mapper.class);
			return mapper.selectBySort2Name(sort2Name);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

	@Override
	public List<Sort2VO> selectAll() {
		SqlSession session = null; 
		try {
			session = MyBatisUtil.getSessionTest();
			Sort2Mapper mapper = session.getMapper(Sort2Mapper.class);
			return mapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}



}
