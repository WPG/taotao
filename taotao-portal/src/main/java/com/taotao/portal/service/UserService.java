package com.taotao.portal.service;

import com.taotao.entity.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);
}
