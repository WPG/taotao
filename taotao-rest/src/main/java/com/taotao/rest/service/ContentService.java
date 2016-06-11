package com.taotao.rest.service;

import java.util.List;

import com.taotao.entity.TbContent;

public interface ContentService {

	List<TbContent> getContentList(long contentCid);
}
