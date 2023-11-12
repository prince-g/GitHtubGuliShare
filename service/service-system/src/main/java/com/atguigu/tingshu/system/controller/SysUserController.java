package com.atguigu.tingshu.system.controller;

import com.atguigu.tingshu.common.annotation.Log;
import com.atguigu.tingshu.common.enums.BusinessType;
import com.atguigu.tingshu.common.result.Result;
import com.atguigu.tingshu.common.util.MD5;
import com.atguigu.tingshu.model.system.SysUser;
import com.atguigu.tingshu.system.service.SysUserService;
import com.atguigu.tingshu.vo.system.SysUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Tag(name = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
@CrossOrigin
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "获取分页列表")
    @PreAuthorize("hasAuthority('bnt.sysUser.list')")
    @GetMapping("{page}/{limit}")
    public Result index(
            @Parameter(name = "page", description = "当前页码", required = true)
            @PathVariable Long page,

            @Parameter(name = "limit", description = "每页记录数", required = true)
            @PathVariable Long limit,

            @Parameter(name = "userQueryVo", description = "查询对象", required = false)
            SysUserQueryVo userQueryVo) {
        Page<SysUser> pageParam = new Page<>(page, limit);
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, userQueryVo);
        return Result.ok(pageModel);
    }

    @Operation(summary = "获取用户")
    @PreAuthorize("hasAuthority('bnt.sysUser.list')")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @Operation(summary = "保存用户")
    @PreAuthorize("hasAuthority('bnt.sysUser.add')")
    @PostMapping("save")
    public Result save(@RequestBody SysUser user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        sysUserService.save(user);
        return Result.ok();
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @Operation(summary = "更新用户")
    @PreAuthorize("hasAuthority('bnt.sysUser.update')")
    @PutMapping("update")
    public Result updateById(@RequestBody SysUser user) {
        sysUserService.updateById(user);
        return Result.ok();
    }

    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @Operation(summary = "删除用户")
    @PreAuthorize("hasAuthority('bnt.sysUser.remove')")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysUserService.removeById(id);
        return Result.ok();
    }

    @Log(title = "用户管理", businessType = BusinessType.STATUS)
    @Operation(summary = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        sysUserService.updateStatus(id, status);
        return Result.ok();
    }
}

