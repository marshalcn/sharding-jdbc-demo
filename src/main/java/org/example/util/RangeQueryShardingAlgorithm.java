package org.example.util;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.assertj.core.util.DateUtil;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * @author madechao
 * @menu
 * @description
 * @createTime 14:47 2021/8/16
 */
public class RangeQueryShardingAlgorithm implements RangeShardingAlgorithm<Date> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Date> range = shardingValue.getValueRange();

        Date lowerDate = range.lowerEndpoint();
        Date upperDate = range.upperEndpoint();

//        logger.info("lowerDate : {} ", DateUtil.formatDateTime(lowerDate));
//        logger.info("upperDate : {} ", DateUtil.formatDateTime(upperDate));

        Integer low = Integer.valueOf(String.format("%tm", lowerDate));
        Integer upper = Integer.valueOf(String.format("%tm", upperDate));


        for (int i = low; i <= upper; i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
