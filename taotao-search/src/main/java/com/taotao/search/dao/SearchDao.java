package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.search.entity.SearchResult;

public interface SearchDao {

	SearchResult search(SolrQuery query) throws Exception;
}
