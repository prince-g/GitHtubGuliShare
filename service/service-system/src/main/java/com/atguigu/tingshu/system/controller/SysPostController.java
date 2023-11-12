package com.atguigu.tingshu.system.controller;

import com.atguigu.tingshu.common.annotation.Log;
import com.atguigu.tingshu.common.enums.BusinessType;
import com.atguigu.tingshu.common.result.Result;
import com.atguigu.tingshu.model.system.SysPost;
import com.atguigu.tingshu.system.service.SysPostService;
import com.atguigu.tingshu.vo.system.SysPostQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "岗位管理")
@RestController
@RequestMapping(value="/admin/system/sysPost")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysPostController {
	
	@Resource
	private SysPostService sysPostService;

	@Operation(summary = "获取分页列表")
    @PreAuthorize("hasAuthority('bnt.sysPost.list')")
	@GetMapping("{page}/{limit}")
	public Result index(
		@Parameter(name = "page", description = "当前页码", required = true)
		@PathVariable Long page,
	
		@Parameter(name = "limit", description = "每页记录数", required = true)
		@PathVariable Long limit,
	
		@Parameter(name = "sysPostVo", description = "查询对象", required = false)
		SysPostQueryVo sysPostQueryVo) {
		Page<SysPost> pageParam = new Page<>(page, limit);
		IPage<SysPost> pageModel = sysPostService.selectPage(pageParam, sysPostQueryVo);
		return Result.ok(pageModel);
	}

	@Operation(summary = "获取")
    @PreAuthorize("hasAuthority('bnt.sysPost.list')")
	@GetMapping("get/{id}")
	public Result get(@PathVariable Long id) {
		SysPost sysPost = sysPostService.getById(id);
		return Result.ok(sysPost);
	}

	@GetMapping("findAll")
	public Result findAll() {
		return Result.ok(sysPostService.findAll());
	}

	@Log(title = "岗位管理", businessType = BusinessType.INSERT)
	@Operation(summary = "新增")
    @PreAuthorize("hasAuthority('bnt.sysPost.add')")
	@PostMapping("save")
	public Result save(@RequestBody SysPost sysPost) {
		sysPostService.save(sysPost);
		return Result.ok();
	}

	@Log(title = "岗位管理", businessType = BusinessType.UPDATE)
	@Operation(summary = "修改")
	@PreAuthorize("hasAuthority('bnt.sysPost.update')")
	@PutMapping("update")
	public Result updateById(@RequestBody SysPost sysPost) {
		sysPostService.updateById(sysPost);
		return Result.ok();
	}

	@Log(title = "岗位管理", businessType = BusinessType.DELETE)
	@Operation(summary = "删除")
	@PreAuthorize("hasAuthority('bnt.sysPost.remove')")
	@DeleteMapping("remove/{id}")
	public Result remove(@PathVariable Long id) {
		sysPostService.removeById(id);
		return Result.ok();
	}

	@Operation(summary = "更新状态")
	@GetMapping("updateStatus/{id}/{status}")
	public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
		sysPostService.updateStatus(id, status);
		return Result.ok();
	}
	
}

