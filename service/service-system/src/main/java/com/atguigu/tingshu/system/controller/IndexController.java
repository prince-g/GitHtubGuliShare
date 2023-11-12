package com.atguigu.tingshu.system.controller;

import com.atguigu.tingshu.common.result.Result;
import com.atguigu.tingshu.common.util.AuthContextHolder;
import com.atguigu.tingshu.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Tag(name = "登录用户信息管理")
@RestController
@RequestMapping(value="/admin/system/index")
@SuppressWarnings({"unchecked", "rawtypes"})
public class IndexController {
	
	@Resource
	private SysUserService sysUserService;

	@Operation(summary = "获取用户信息")
	@GetMapping("info")
	public Result info() {
		String username = AuthContextHolder.getUsername();
		Map<String, Object> map = sysUserService.getUserInfo(username);
		return Result.ok(map);
	}

	@Operation(summary = "退出登录")
	@GetMapping("logout")
	public Result logout() {
		return Result.ok();
	}
}

