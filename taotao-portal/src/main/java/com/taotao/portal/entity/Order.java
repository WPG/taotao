package com.taotao.portal.entity;

import java.util.List;

import com.taotao.entity.TbOrder;
import com.taotao.entity.TbOrderItem;
import com.taotao.entity.TbOrderShipping;

public class Order extends TbOrder {

	private List<TbOrderItem> orderItems;
	
	private TbOrderShipping orderShipping;

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
