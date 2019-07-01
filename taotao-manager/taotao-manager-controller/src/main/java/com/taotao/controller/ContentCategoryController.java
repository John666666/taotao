package com.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.entity.ContentCategory;
import com.taotao.service.ContentCategoryService;
import com.taotao.util.TaotaoResult;

@Controller
@RequestMapping("rest")
public class ContentCategoryController {

	@Resource
	private ContentCategoryService contentCategoryService;

	@ResponseBody
	@RequestMapping(value = "/content/category", method = RequestMethod.GET)
	public List<ContentCategory> show(@RequestParam(value = "id", defaultValue = "0") Long parentId) throws Exception {
		List catList = new ArrayList();
		List<ContentCategory> list = contentCategoryService.selectContentCategoryAll(parentId);
		for (ContentCategory tbItemCat : list) {
			Map node = new HashMap<>();
			node.put("id", tbItemCat.getId());
			node.put("text", tbItemCat.getName());
			// 如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
			node.put("state", tbItemCat.getIsParent() ? "closed" : "open");
			catList.add(node);
		}
		return catList;
	}

	@ResponseBody
	@RequestMapping(value = "/content/category/add", method = RequestMethod.POST)
	public TaotaoResult add(@RequestParam("parentId") long parentId, @RequestParam("name") String name) {
                return contentCategoryService.addContentCategory(parentId, name);
	}
	
	@ResponseBody
	@RequestMapping(value = "/content/category/up", method = RequestMethod.PUT)
	public ContentCategory up(@RequestParam("id") long id, @RequestParam("name") String name) {
                return contentCategoryService.up( id,name);
	}
	
	@ResponseBody
	@RequestMapping(value = "/content/category/delete", method = RequestMethod.POST)
	public int deletetrtt(@RequestParam("id") long id) {
                return contentCategoryService.delete(id);
	}


}
