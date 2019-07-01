package com.taotao.taotaorest;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.taotao.taotaorest.redis.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaotaoRestApplicationTests {

	@Resource
	private RedisUtil redisUtil;
	
	@Resource
	private SolrClient solrClient;
	
	
	@Test
	public void contextLoads() {
		boolean result = redisUtil.set("test", "springboot-redis", 10);
		System.out.println(result);
	}

	@Test
	public void test02() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
        	  SolrInputDocument doc = new SolrInputDocument();
              doc.setField("id", uuid);
              doc.setField("title", "我是中国人, 我爱中国");
              solrClient.add("taotao",doc);
              solrClient.commit("taotao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPhone() {
		int arr[] = new int[]{8,2,1,0,3};
		int index[] = new int[] {2,0,3,2,4,0,1,3,2,3,3};
		String tel = "";
		for(int i:index) {
			tel+=arr[i];
		}
		System.out.println(tel);
	}
	
}

