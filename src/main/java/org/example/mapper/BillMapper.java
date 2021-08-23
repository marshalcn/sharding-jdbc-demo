package org.example.mapper;

/**
 * @author madechao
 * @menu
 * @description
 * @createTime 11:23 2021/8/16
 */
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BillMapper extends BaseMapper<Bill> {
    List<Bill> findPage(@Param("params") Map<String, Object> params);
    IPage<Bill> findBillPage(Page<?> page);
}