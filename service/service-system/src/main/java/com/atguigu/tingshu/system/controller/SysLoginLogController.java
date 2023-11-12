package com.atguigu.tingshu.system.controller;

import com.atguigu.tingshu.common.result.Result;
import com.atguigu.tingshu.model.system.SysLoginLog;
import com.atguigu.tingshu.system.service.SysLoginLogService;
import com.atguigu.tingshu.vo.system.SysLoginLogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author qy
 *
 */
@Tag(name = "SysLoginLog管理")
@RestController
@RequestMapping(value="/admin/system/sysLoginLog")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysLoginLogController {
	
	@Resource
	private SysLoginLogService sysLoginLogService;

	@Operation(summary = "获取分页列表")
	@GetMapping("{page}/{limit}")
	public Result index(
		@Parameter(name = "page", description = "当前页码", required = true)
		@PathVariable Long page,
	
		@Parameter(name = "limit", description = "每页记录数", required = true)
		@PathVariable Long limit,
	
		@Parameter(name = "sysLoginLogVo", description = "查询对象", required = false)
		SysLoginLogQueryVo sysLoginLogQueryVo) {
		Page<SysLoginLog> pageParam = new Page<>(page, limit);
		IPage<SysLoginLog> pageModel = sysLoginLogService.selectPage(pageParam, sysLoginLogQueryVo);
		return Result.ok(pageModel);
	}

	@Operation(summary = "获取")
	@GetMapping("get/{id}")
	public Result get(@PathVariable Long id) {
		SysLoginLog sysLoginLog = sysLoginLogService.getById(id);
		return Result.ok(sysLoginLog);
	}

	@Operation(summary = "记录登录日志")
	@PostMapping("recordLoginLog")
	public Result recordLoginLog(@RequestBody SysLoginLog sysLoginLog) {
		sysLoginLogService.recordLoginLog(sysLoginLog);
		return Result.ok();
	}

}

