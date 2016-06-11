package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.entity.EUTreeNode;
import com.taotao.common.entity.TaotaoResult;
import com.taotao.entity.TbContentCategory;
import com.taotao.entity.TbContentCategoryExample;
import com.taotao.entity.TbContentCategoryExample.Criteria;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.service.ContentCategoryService;

/**
 *内容分类管理
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	//列表
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		//根据parentid查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<EUTreeNode>();
		for (TbContentCategory tbContentCategory : list) {
			//创建一个节点
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
			
		}
		return resultList;
	}

	//添加
	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		//创建一个entity
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		//状态     1(正常)  2(删除)
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//添加记录
		contentCategoryMapper.insert(contentCategory);
		//查看父节点的isParent列是否为true，不过不是，将其改为true。
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		//判断是否为true
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		//返回结果
		return TaotaoResult.ok(contentCategory);
	}

	//删除
	@Override
	public TaotaoResult deleteContentCategory(long id) {
		//根据ID查找是否有子节点
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		Long parentId = contentCategory.getParentId();
		
		if(contentCategory.getIsParent()){
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(id);
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			//子节点不为空的时候，删除子节点
			if(list.size()>0 &&list!=null){
				for (TbContentCategory tbContentCategory : list) {
					contentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
				}
			}
			//删除节点
			contentCategoryMapper.deleteByPrimaryKey(id);
			
			TbContentCategoryExample example2 = new TbContentCategoryExample();
			Criteria criteria2 = example2.createCriteria();
			criteria2.andParentIdEqualTo(parentId);
			List<TbContentCategory> list2 = contentCategoryMapper.selectByExample(example2);
			if(list2.size()==0 && list2==null){
				TbContentCategory contentCategory2 = contentCategoryMapper.selectByPrimaryKey(parentId);
				contentCategory2.setIsParent(false);
				contentCategoryMapper.updateByPrimaryKeySelective(contentCategory2);
			}
		}else{//非父节点
			//直接删除次节点
			contentCategoryMapper.deleteByPrimaryKey(id);
			//判断该节点的父节点是否还有子节点
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			//无子节点需把isparentID设为false
			if(list.size()==0 && list==null){
				TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(parentId);
				category.setIsParent(false);
				contentCategoryMapper.updateByPrimaryKeySelective(category);
			}
		}
		return TaotaoResult.ok();
	}

	//修改
	@Override
	public TaotaoResult updateContentCategory(long id, String name) {	
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setId(id);
		contentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		return TaotaoResult.ok(contentCategory);
	}

}
