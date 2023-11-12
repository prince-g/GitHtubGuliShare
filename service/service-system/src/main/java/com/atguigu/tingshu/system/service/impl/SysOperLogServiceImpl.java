package com.atguigu.tingshu.system.service.impl;

import com.atguigu.tingshu.model.system.SysOperLog;
import com.atguigu.tingshu.system.mapper.SysOperLogMapper;
import com.atguigu.tingshu.system.service.SysOperLogService;
import com.atguigu.tingshu.vo.system.SysOperLogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {

	@Resource
	private SysOperLogMapper sysOperLogMapper;

	@Override
	public IPage<SysOperLog> selectPage(Page<SysOperLog> pageParam, SysOperLogQueryVo sysOperLogQueryVo) {

		return sysOperLogMapper.selectPage(pageParam, sysOperLogQueryVo);
	}

	@Override
	public void saveSysLog(SysOperLog sysOperLog) {
		sysOperLogMapper.insert(sysOperLog);
	}
}
