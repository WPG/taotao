package com.taotao.service;

import com.taotao.common.entity.EUDataGridResult;
import com.taotao.common.entity.TaotaoResult;
import com.taotao.entity.TbItemParam;

public interface ItemParamService {
	
	TaotaoResult getItemParamByCid(long cid);
	
	TaotaoResult insertItemParam(TbItemParam itemParam);
	
	EUDataGridResult getItemParamList(Integer page , Integer rows);
	
}
