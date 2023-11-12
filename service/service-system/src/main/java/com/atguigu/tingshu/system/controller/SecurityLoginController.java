package com.atguigu.tingshu.system.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.tingshu.common.result.Result;
import com.atguigu.tingshu.model.system.SysUser;
import com.atguigu.tingshu.system.service.SysMenuService;
import com.atguigu.tingshu.system.service.SysUserService;
import com.atguigu.tingshu.vo.system.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "security登录管理")
@RestController
@RequestMapping(value="/admin/system/securityLogin")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SecurityLoginController {
	
	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysMenuService sysMenuService;

	@Operation(summary = "模拟登录")
	@PostMapping("login")
	public Result login(@RequestBody LoginVo loginVo) {
		log.info(JSON.toJSONString(loginVo));
		return Result.ok();
	}

	@Operation(summary = "根据用户名获取用户信息")
	@GetMapping("getByUsername/{username}")
	public Result<SysUser> getByUsername(@PathVariable String username) {
		return Result.ok(sysUserService.getByUsername(username));
	}

	@Operation(summary = "获取用户按钮权限")
	@GetMapping("findUserPermsList/{userId}")
	public Result<List<String>> findUserPermsList(@PathVariable Long userId) {
		return Result.ok(sysMenuService.findUserPermsList(userId));
	}
}

