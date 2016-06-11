package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.JsonUtils;
import com.taotao.entity.TbItemCat;
import com.taotao.entity.TbItemCatExample;
import com.taotao.entity.TbItemCatExample.Criteria;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.entity.CatNode;
import com.taotao.rest.entity.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 *商品分类服务
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_ITEMCAT_REDIS_KEY}")
	private String INDEX_ITEMCAT_REDIS_KEY;
	
	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		
		//从缓存中取内容
//		try {
//			String result = jedisClient.get(INDEX_ITEMCAT_REDIS_KEY);
//			if(!StringUtils.isBlank(result)){
//				//把字符串转换成list
//				List<CatNode> list = JsonUtils.jsonToList(result, CatNode.class);
//				catResult.setData(list);
//				return catResult;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//查询分类列表
		catResult.setData(getCatList(0));
		
		//向缓存中存内容
//		try {
//			//需要先list转换成字符串
//			String cacheString = JsonUtils.objectToJson(getCatList(0));
//			jedisClient.set(INDEX_ITEMCAT_REDIS_KEY,cacheString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return catResult;
	}

	
	//递归查询分类列表
	private List<?> getCatList(long parentId){
		
		//创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//返回值list
		List resultList = new ArrayList();
		//添加计数器
		int count = 0;
		//向list中添加节点
		for (TbItemCat tbItemCat : list) {
			//判断是否是父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				}else{
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
				count ++;
				//第一层只取14条记录
				if (parentId == 0 && count >= 14) {
					break;
				}
			} else {
				//如果是叶子节点
				resultList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName()+"");
			}
		}
		return resultList;
	}

}
