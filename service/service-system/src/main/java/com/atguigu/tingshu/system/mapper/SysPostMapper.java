package com.atguigu.tingshu.system.mapper;

import com.atguigu.tingshu.model.system.SysPost;
import com.atguigu.tingshu.vo.system.SysPostQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysPostMapper extends BaseMapper<SysPost> {

    IPage<SysPost> selectPage(Page<SysPost> page, @Param("vo") SysPostQueryVo sysPostQueryVo);

}
