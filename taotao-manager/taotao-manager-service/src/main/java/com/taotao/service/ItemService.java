package com.taotao.service;

import com.taotao.common.entity.EUDataGridResult;
import com.taotao.common.entity.TaotaoResult;
import com.taotao.entity.TbItem;

public interface ItemService {

	TbItem getItemById(long itemId);
	
	EUDataGridResult getItemList(Integer page , Integer rows);
	
	TaotaoResult creatItem(TbItem item ,String desc , String itemParam) throws Exception;
}
