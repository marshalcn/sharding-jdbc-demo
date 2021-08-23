package org.example.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.example.Application;
import org.example.entity.Bill;
import org.example.service.BillService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author madechao
 * @menu
 * @description
 * @createTime 14:18 2021/8/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ModelTest {
    @Resource
    private BillService billService;
    @Test
    public void testBillSave(){
        for (int i = 0 ; i< 120 ; i++){
            Bill bill = new Bill();
            bill.setUserId(i);
            bill.setAddressId((long)i);
            bill.setStatus("K");
            bill.setCreateTime((new Date(new DateTime(2021,(i % 11)+1,7,00, 00,00,000).getMillis())));
            billService.save(bill);
        }
    }
    @Test
    public void testGetByOrderId(){
        long id = 634034224571592704L; //根据数据修改，无数据会报错
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", id);
        Bill bill = billService.getOne(queryWrapper);
        System.out.println(bill.toString());
    }

    @Test
    public void testGetByDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse("2021-02-07 00:00:00");
            QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("create_time",date);
            List<Bill> billIPage = billService.list(queryWrapper);
            System.out.println(billIPage.size());
            System.out.println(billIPage.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetByDate2(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse("2021-02-07 00:00:00");
            Date date2 = sdf.parse("2021-06-07 00:00:00");
            QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
            queryWrapper.ge("create_time",date)
                    .and(qw-> qw.le("create_time", date2));
            Page<Bill> page = new Page<Bill>();
            page.setCurrent(1);
            page.setSize(20);
            IPage<Bill> billIPage = billService.findBillPage(page);
            System.out.println(billIPage.getRecords().size());
            billIPage.getRecords().forEach(System.out::println);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetByPage(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse("2021-02-07 00:00:00");
            Date date2 = sdf.parse("2021-06-07 00:00:00");
            QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
            queryWrapper.ge("create_time",date)
                    .and(qw-> qw.le("create_time", date2));
            Page<Bill> page = new Page<Bill>();
            page.setCurrent(1);
            page.setSize(20);
            List<Bill> billIPage = billService.findPage(new HashMap<String, Object>());
            System.out.println(billIPage.size());
            billIPage.forEach(System.out::println);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
