package com.taotao.search.service;

import com.taotao.search.entity.SearchResult;

public interface SearchService {
	
	SearchResult search(String queryString , int page , int rows) throws Exception;
}
