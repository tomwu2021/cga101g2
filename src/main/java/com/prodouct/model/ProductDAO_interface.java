package com.prodouct.model;

import java.util.List;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);
	//���ӫ~�P���ʻ���
    public void update(ProductVO productVO);
	//���@��ӫ~�����˪��A
//    '0:�D���˪��A\n1:���˪��A',
    public void updatetBytop_status(Integer top_status ,Integer product_id);
    //�`�N�ӫ~��delete����אּ�ӫ~�W�U�[���A
//    0:�U�[  1:�@��ӫ~�W�[ 2:�@��ӫ~+���ʤW�[
    public void delete(Integer status ,Integer product_id);
    
    
    public ProductVO findByPrimaryKey(Integer product_id);
    public List<ProductVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
