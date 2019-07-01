package com.taotao.taotaoportal;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.taotao.taotaoportal.pojo.Content;
import com.taotao.taotaoportal.service.AadvertisingService;
import com.taotao.util.TaotaoResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaotaoPortalApplicationTests {

	@Resource
	private AadvertisingService aadvertisingService;
	
	@Resource
	private RestTemplate restemplate;
	
	
	@Test
	public void contextLoads() {
		String result = aadvertisingService.getContentAdver();
		System.out.println(result);
	}

	@Test
	public void testContent() {
//		http://localhost:12000/content/89
		TaotaoResult result  = 	restemplate.getForObject("http://localhost:12000/content/{cid}", TaotaoResult.class, 89);
		List<Content> list  =  (List<Content>) result.getData();
		System.out.println(list.size());
		System.out.println(result.toString());
	}
	
}

