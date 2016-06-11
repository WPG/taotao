package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.entity.EUDataGridResult;
import com.taotao.common.entity.TaotaoResult;
import com.taotao.entity.TbItemParam;
import com.taotao.entity.TbItemParamExample;
import com.taotao.entity.TbItemParamExample.Criteria;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.service.ItemParamService;
/**
 *商品规格参数模版
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		//补全entity
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

	@Override
	public EUDataGridResult getItemParamList(Integer page, Integer rows) {
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
