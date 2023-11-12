package com.atguigu.tingshu.system.service.impl;

import com.atguigu.tingshu.model.system.SysLoginLog;
import com.atguigu.tingshu.system.mapper.SysLoginLogMapper;
import com.atguigu.tingshu.system.service.SysLoginLogService;
import com.atguigu.tingshu.vo.system.SysLoginLogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

	@Resource
	private SysLoginLogMapper sysLoginLogMapper;

	@Override
	public IPage<SysLoginLog> selectPage(Page<SysLoginLog> pageParam, SysLoginLogQueryVo sysLoginLogQueryVo) {

		return sysLoginLogMapper.selectPage(pageParam, sysLoginLogQueryVo);
	}

	/**
	 * 记录登录信息
	 *
	 * @param sysLoginLog
	 * @return
	 */
	public void recordLoginLog(SysLoginLog sysLoginLog) {
		sysLoginLogMapper.insert(sysLoginLog);
	}
}
