package com.atguigu.tingshu.system.service.impl;

import com.atguigu.tingshu.model.system.SysPost;
import com.atguigu.tingshu.system.mapper.SysPostMapper;
import com.atguigu.tingshu.system.service.SysPostService;
import com.atguigu.tingshu.vo.system.SysPostQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

	@Resource
	private SysPostMapper sysPostMapper;

	@Override
	public IPage<SysPost> selectPage(Page<SysPost> pageParam, SysPostQueryVo sysPostQueryVo) {

		return sysPostMapper.selectPage(pageParam, sysPostQueryVo);
	}

	@Override
	public void updateStatus(Long id, Integer status) {
		SysPost sysPost = this.getById(id);
		sysPost.setStatus(status);
		this.updateById(sysPost);
	}

	@Override
	public List<SysPost> findAll() {
		List<SysPost> sysPostList = this.list(new LambdaQueryWrapper<SysPost>().eq(SysPost::getStatus, 1));
		return sysPostList;
	}

}
