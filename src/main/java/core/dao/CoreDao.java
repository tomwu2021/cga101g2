package core.dao;

import java.util.List;

public interface CoreDao<P, I> {
	Integer insert(P pojo);

	Integer deleteById(I id);

	Integer update(P pojo);

	P selectById(I id);

	List<P> selectAll();
}
