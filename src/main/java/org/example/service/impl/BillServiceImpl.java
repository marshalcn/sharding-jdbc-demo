package org.example.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.Bill;
import org.example.mapper.BillMapper;
import org.example.service.BillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author madechao
 * @menu
 * @description
 * @createTime 11:25 2021/8/16
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {
    @Override
    public List<Bill> findPage(Map<String, Object> params) {
        PageHelper.startPage(1, 100);
        List<Bill> billList = this.baseMapper.findPage(params);
        PageInfo pageInfo = new PageInfo(billList);
        System.out.println("total" + pageInfo.getTotal());
        return billList;
    }

    @Override
    public IPage<Bill> findBillPage(Page<?> page) {
        return this.baseMapper.findBillPage(page);
    }
}
