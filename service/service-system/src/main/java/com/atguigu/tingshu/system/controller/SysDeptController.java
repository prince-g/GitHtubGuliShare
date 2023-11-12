package com.atguigu.tingshu.system.controller;

import com.atguigu.tingshu.common.annotation.Log;
import com.atguigu.tingshu.common.enums.BusinessType;
import com.atguigu.tingshu.common.result.Result;
import com.atguigu.tingshu.model.system.SysDept;
import com.atguigu.tingshu.system.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "部门管理")
@RestController
@RequestMapping(value="/admin/system/sysDept")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysDeptController {
	
	@Resource
	private SysDeptService sysDeptService;

	@Operation(summary = "获取")
	@PreAuthorize("hasAuthority('bnt.sysDept.list')")
	@GetMapping("get/{id}")
	public Result get(@PathVariable Long id) {
		SysDept sysDept = sysDeptService.getById(id);
		return Result.ok(sysDept);
	}

	@Log(title = "部门管理", businessType = BusinessType.INSERT)
	@Operation(summary = "新增")
	@PreAuthorize("hasAuthority('bnt.sysDept.add')")
	@PostMapping("save")
	public Result save(@RequestBody SysDept sysDept) {
		sysDeptService.save(sysDept);
		return Result.ok();
	}

	@Log(title = "部门管理", businessType = BusinessType.UPDATE)
	@Operation(summary = "修改")
	@PreAuthorize("hasAuthority('bnt.sysDept.update')")
	@PutMapping("update")
	public Result updateById(@RequestBody SysDept sysDept) {
		sysDeptService.updateById(sysDept);
		return Result.ok();
	}

	@Log(title = "部门管理", businessType = BusinessType.DELETE)
	@Operation(summary = "删除")
	@PreAuthorize("hasAuthority('bnt.sysDept.remove')")
	@DeleteMapping("remove/{id}")
	public Result remove(@PathVariable Long id) {
		sysDeptService.removeById(id);
		return Result.ok();
	}

	@Operation(summary = "获取全部部门节点")
	@PreAuthorize("hasAuthority('bnt.sysDept.list')")
	@GetMapping("findNodes")
	public Result findNodes() {
		return Result.ok(sysDeptService.findNodes());
	}

	@Operation(summary = "获取用户部门节点")
	@GetMapping("findUserNodes")
	public Result findUserNodes() {
		return Result.ok(sysDeptService.findUserNodes());
	}

	@Log(title = "部门管理", businessType = BusinessType.STATUS)
	@Operation(summary = "更新状态")
	@GetMapping("updateStatus/{id}/{status}")
	public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
		sysDeptService.updateStatus(id, status);
		return Result.ok();
	}

}

