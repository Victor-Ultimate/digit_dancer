package com.example.ivcdancer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ivcdancer.pojo.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {

}
