package org.example.util;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Date;

/**
 * @author madechao
 * @menu
 * @description
 * @createTime 11:43 2021/8/16
 */
public class TableShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        //真实数据库节点
        availableTargetNames.stream().forEach((item) -> {
            System.out.println("actual db:" + item);
        });
        //逻辑表以及分片的字段名
        System.out.println("logicTable:"+shardingValue.getLogicTableName()+";shardingColumn:"+ shardingValue.getColumnName());
        //分片数据字段值
        System.out.println("shardingColumn value:"+ shardingValue.getValue().toString());
        //获取表名前缀
        String tb_name = shardingValue.getLogicTableName() + "_";
        //根据日期分表
        Date date = shardingValue.getValue();
        String year = String.format("%tY", date);
        String mon =String.valueOf(Integer.parseInt(String.format("%tm", date)));
        //String dat = String.format("%td", date); //也可以安装年月日来分表
        // 选择表
        tb_name = tb_name + year + "_" + mon;
        //实际的表名
        System.out.println("tb_name:" + tb_name);
        for (String each : availableTargetNames) {
            //System.out.println("availableTableName:" + each);
            if (each.equals(tb_name)) {
                //返回物理表名
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
}
