package com.taotao.service;

import java.util.List;

import com.taotao.common.entity.EUTreeNode;

public interface ItemCatService {

	List<EUTreeNode> getCatList(long parentId);
}
