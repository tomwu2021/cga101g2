package com.sort1.model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import connection.MyBatisUtil;

import com.sort1.mapper.Sort1Mapper;

public class Sort1MybatisDAO implements Sort1Mapper {


	public int insert(Sort1VO sort1VO) {
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSessionTest();
			Sort1Mapper mapper = session.getMapper(Sort1Mapper.class);
			int result = mapper.insert(sort1VO);
			if (result == 1) {
				session.commit();
				System.out.println("rows inserted = "+result );
				System.out.println("generated key value = " + sort1VO.getSort1Id());
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
//	改版1
//	public static int insert(Sort1VO sort1VO) {
//		SqlSession session = null;
//		Sort1Mapper mapper =null;
//		try {
//			session = MyBatisUtil.getSession();
//			mapper = session.getMapper(Sort1Mapper.class);
//			int result = mapper.insert(sort1VO);
//			if (result == 1) {
//				session.commit();
//				System.out.println(result + "新增成功");
//				return result;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}
//	老師的原版本
//	public static int insert1(Sort1VO sort1VO) {
//		try (SqlSession session = MyBatisUtil.getSession()) {
//			Sort1Mapper mapper = session.getMapper(Sort1Mapper.class);
//			int result = mapper.insert(sort1VO);
//			session.commit();
//			System.out.println(result + "新增成功");
//			return result;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}

	@Override
	public int deleteById(Integer id) {
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSessionTest();
			Sort1Mapper mapper = session.getMapper(Sort1Mapper.class);
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
	public int updateById(Sort1VO sort1VO) {
		SqlSession session = null; 
		try {
			session = MyBatisUtil.getSessionTest();
			Sort1Mapper mapper = session.getMapper(Sort1Mapper.class);
			int result = mapper.updateById(sort1VO);
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
	public Sort1VO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sort1VO selectBySort1Name(String sort1Name) {
		SqlSession session = null; 
		try {
			session = MyBatisUtil.getSessionTest();
			Sort1Mapper mapper = session.getMapper(Sort1Mapper.class);
			return mapper.selectBySort1Name(sort1Name);
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
	public List<Sort1VO> selectAll() {
		SqlSession session = null; 
		try {
			session = MyBatisUtil.getSessionTest();
			Sort1Mapper mapper = session.getMapper(Sort1Mapper.class);
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
