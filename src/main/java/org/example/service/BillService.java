package org.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Bill;

import java.util.List;
import java.util.Map;

/**
 * @author madechao
 * @menu
 * @description
 * @createTime 11:24 2021/8/16
 */
public interface BillService extends IService<Bill> {
    List<Bill> findPage(Map<String, Object> params);
    IPage<Bill> findBillPage(Page<?> page);
}
