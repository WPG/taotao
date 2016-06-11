package com.taotao.service;

import com.taotao.common.entity.EUDataGridResult;
import com.taotao.common.entity.TaotaoResult;
import com.taotao.entity.TbContent;

public interface ContentService {

	EUDataGridResult getContentList(long categoryId , Integer page , Integer rows);
	
	TaotaoResult insertContent(TbContent content);
}
