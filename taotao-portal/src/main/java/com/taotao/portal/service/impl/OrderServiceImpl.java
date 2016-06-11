package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.entity.TaotaoResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.portal.entity.Order;
import com.taotao.portal.service.OrderService;

/**
 * 订单处理Service
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	
	@Override
	public String createOrder(Order order) {
		//调用创建订单服务之前补全用户信息
		//从cookie中获取TT_TOKEN内容，根据token调用sso系统的服务根据token换取用户信息
		//调用taotao-order的服务提交订单
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		//把json装换成taotaoResult
		TaotaoResult taotaoResult = TaotaoResult.format(json);
		if (taotaoResult.getStatus() == 200) {
			Object orderId = taotaoResult.getData();
			return orderId.toString();
		}
		return "";
	}

	
	

}