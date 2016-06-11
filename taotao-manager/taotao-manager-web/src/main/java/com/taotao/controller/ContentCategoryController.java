package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.entity.EUTreeNode;
import com.taotao.common.entity.TaotaoResult;
import com.taotao.service.ContentCategoryService;

/**
 *内容分类管理
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getCategoryList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertContentCategory(Long parentId , String name){
		TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long id){
		TaotaoResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(Long id , String name){
		TaotaoResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
	
}
