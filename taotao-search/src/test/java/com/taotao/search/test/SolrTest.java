package com.taotao.search.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.search.domain.Item;
import com.taotao.search.domain.ItemMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrTest {

	@Resource
	private SolrClient solrClient;
	
	@Resource
	private ItemMapper itemMapper;
	
	
	@Test
	public void testAdd() {
		String uuid = UUID.randomUUID().toString();
		
		try {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", uuid);
			doc.addField("title","我爱你中国");
			
			solrClient.add("taotao",doc);
			solrClient.commit("taotao");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		try {
			solrClient.deleteById("taotao","1eb65743-b0cd-4c1b-acff-99cb5777b9f2");
			solrClient.commit("taotao");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Test
	public void testDeleteAll() {
		try {
			solrClient.deleteByQuery("*:*");
			solrClient.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getById() {
//		SolrDocument{id=815798e4-4eb3-4795-b3ff-2080479d6cba, title=我爱你中国, _version_=1622604209498095616}
		try {
			SolrDocument doc = solrClient.getById("taotao", "815798e4-4eb3-4795-b3ff-2080479d6cba");
			System.out.println(doc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testPageInsert() {
		int pageNo= 1;
		int pageSize = 100;
		int totalPage = 0;// 总页数
		try {
			do {
				PageInfo<Item> page = items(pageSize,pageNo);
				// 获取总页数的值
				totalPage  = page.getPages();
				List<Item> items  = page.getList();
				List<SolrInputDocument> dosc = new ArrayList<SolrInputDocument>();
				for (Item i : items) {
					SolrInputDocument doc = new SolrInputDocument();
					doc.setField("id", i.getId());
					doc.setField("title", i.getTitle());
					doc.setField("item_title", i.getTitle());
					doc.setField("item_sell_point", i.getSellPoint());
					doc.setField("item_price", i.getPrice());
					doc.setField("item_image", i.getImage());
					doc.setField("item_category_name", i.getCategoryName());
					doc.setField("item_desc", i.getItemDesc());
					dosc.add(doc);
				}
				solrClient.add(dosc);
				solrClient.commit();
				pageNo++;
			} while (pageNo<=totalPage);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public PageInfo<Item> items(int pageSize,int pageNo){
		PageHelper.startPage(pageNo,pageSize);
		List<Item> item = itemMapper.selectAll();
		PageInfo<Item> page = new PageInfo<>(item);
		return page;
	}
	
	@Test
	public void testQuery() {
		
	}
	
	
	
}
