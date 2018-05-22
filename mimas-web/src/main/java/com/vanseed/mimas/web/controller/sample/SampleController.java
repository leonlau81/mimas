
/**
 * 
 */
package com.vanseed.mimas.web.controller.sample;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vanseed.mimas.common.mvc.support.CombResponse;
import com.vanseed.mimas.common.mvc.support.CombResponseUtils;
import com.vanseed.mimas.common.utils.ParamUtils;
import com.vanseed.mimas.domain.model.sample.Sample;
import com.vanseed.mimas.service.sample.ISampleService;
import com.vanseed.mimas.web.controller.BaseController;


/**
 * @author leon
 * 
 */
@Controller
@RequestMapping("sample")
public class SampleController extends BaseController {

	private static SimpleDateFormat sd = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private ISampleService sampleService;

	@RequestMapping("index")
	public String index() {
		return "sample/index";
	}

	@RequestMapping("create")
	public String createUser() {
		return "admin/create";
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public CombResponse sampleList(
			@RequestBody(required=false) Map<String, Object> requestMap,
			HttpServletRequest request, HttpServletResponse response) {
		
		List<Sample> list = sampleService.findAll();
		
		return CombResponseUtils.getSuccessRespose(list);
	}
	
	/**
	 * 查询信息
	 */
	@RequestMapping(value = "info/{sampleId}")
	@ResponseBody
	public CombResponse sampleInfo(
			@PathVariable Long sampleId, 
			HttpServletRequest request, HttpServletResponse response) {
		
		Sample sample = sampleService.findSampleById(sampleId);
		
		return CombResponseUtils.getSuccessRespose(sample);
	}
	
	/**
	 * 修改amount
	 */
	@RequestMapping(value = "modifyAmount")
	@ResponseBody
	public CombResponse modifyAmount(
			@RequestBody(required=true) Map<String, Object> requestMap,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long sampleId = ParamUtils.convertLong(requestMap.get("sample_id"));
		BigDecimal amount = ParamUtils.convertBigDecimal(requestMap.get("amount"));
		
		//Sample sample = sampleService.findSampleById(sampleId);
		Sample sample = sampleService.modifyAmount(sampleId, amount);
		
		return CombResponseUtils.getSuccessRespose(sample);
	}
	
		
}