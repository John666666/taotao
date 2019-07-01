package com.taotao.taotaosso.test;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.taotao.taotaosso.domain.User;
import com.taotao.taotaosso.redis.RedisUtil;
import com.taotao.util.JWT;

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
        System.out.println(uuid);
	}
	
	@Test
	public void test03() {
		// 加密
//		User u = new User();
//		u.setId(12l);
//		String str = JWT.sign(u, 30*60*1000);
//		System.out.println(str);
		String str = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDc3MTYzNTUxNTcsInBheWxvYWQiOiJ7XCJpZFwiOjEyLFwidXNlcm5hbWVcIjpudWxsLFwicGFzc3dvcmRcIjpudWxsLFwicGhvbmVcIjpudWxsLFwiZW1haWxcIjpudWxsLFwiY3JlYXRlZFwiOm51bGwsXCJ1cGRhdGVkXCI6bnVsbH0ifQ.qaiRyfX2_MXQ-S7of-a7SI3NFQ6a088sKEoXpNUKpKg";
		User u  = JWT.unsign(str, User.class);
		System.out.println(u.getId());
	}
	
	
}

