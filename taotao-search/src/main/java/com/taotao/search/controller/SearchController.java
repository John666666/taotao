package com.taotao.search.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.search.service.ItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("search")
@Api("SearchController相关api")
public class SearchController {

	@Resource
	private ItemService itemService;
	

    @ApiOperation(value = "根据关键字查询商品的信息",notes = "查询solr索引库中某个商品的信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keywords",value = "搜索关键字", required=true,dataType = "String", paramType = "query",example="三星"),
        @ApiImplicitParam(name = "pageSize",value = "一页显示多少条", required=true,dataType = "Integer",paramType = "query",example="10"),
        @ApiImplicitParam(name = "pageNo",value = "页码", required=true,dataType = "Integer",paramType = "query",example="1")
    })
    @ApiResponses({
        @ApiResponse(code=400,message = "请求参数没有填好"),
        @ApiResponse(code=404,message="请求路径没有找到"),
        @ApiResponse(code=500,message="服务器内部错误")
    })
	@PostMapping("q")
	public Object search(@RequestParam("keywords") String keywords,@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize) {
		return itemService.search(keywords, pageNo, pageSize);
	} 
}
