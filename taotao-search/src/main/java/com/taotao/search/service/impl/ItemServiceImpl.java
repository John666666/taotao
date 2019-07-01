package com.taotao.search.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import com.taotao.search.domain.Item;
import com.taotao.search.domain.ItemMapper;
import com.taotao.search.service.ItemService;
import com.taotao.util.TaotaoResult;

@Service
public class ItemServiceImpl implements ItemService {

	@Resource
	private ItemMapper itemMapper;
	
	@Resource
	private SolrClient solrClient;
	
	@Override
	public TaotaoResult search(String keywords, int pageNo, int pageSize) {
		 List<Item> items = new ArrayList<>();
		try {
			SolrQuery params =  new SolrQuery();
			// 搜索条件
			params.set("q", keywords);
			//排序
			params.setSort("item_price",SolrQuery.ORDER.desc);
			// 默认属性
			params.set("df", "item_title");
			// 一页显示多少条
			params.setRows(pageSize);
			// 从第几条开始
			params.setStart((pageNo-1)*pageSize);
			// 查询的响应结果
            QueryResponse queryResponse = solrClient.query(params);
            
            SolrDocumentList results = queryResponse.getResults();
            // 总条数
            long total = results.getNumFound();
            System.out.println("总条数------------"+total);
           
            for (SolrDocument result : results) {
				Item i  = new Item();
				i.setId(Long.parseLong((String)result.get("id")));
				i.setTitle((String)result.get("item_title"));
				i.setPrice((long)result.get("item_price"));
				i.setImage((String)result.get("item_image"));
				i.setCategoryName((String)result.get("item_category_name"));
				items.add(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(items);
	}

}
