package com.atguigu.tingshu.system.mapper;

import com.atguigu.tingshu.model.system.SysLoginLog;
import com.atguigu.tingshu.vo.system.SysLoginLogQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {

    IPage<SysLoginLog> selectPage(Page<SysLoginLog> page, @Param("vo") SysLoginLogQueryVo sysLoginLogQueryVo);

}
