package com.taotao.order.service;

import java.util.List;

import com.taotao.common.entity.TaotaoResult;
import com.taotao.entity.TbOrder;
import com.taotao.entity.TbOrderItem;
import com.taotao.entity.TbOrderShipping;

public interface OrderService {

	TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
