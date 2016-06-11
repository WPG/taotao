package com.taotao.rest.service;

import com.taotao.common.entity.TaotaoResult;

public interface RedisService {

	TaotaoResult syncCotent(long contentCid);
}
