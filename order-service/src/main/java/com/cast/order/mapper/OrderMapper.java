package com.cast.order.mapper;

import org.apache.ibatis.annotations.Select;

import com.cast.order.pojo.Order;

public interface OrderMapper {

    @Select("select * from tb_order where id = #{id}")
    Order findById(Long id);
}
