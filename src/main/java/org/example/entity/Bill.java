package org.example.entity;

/**
 * @author madechao
 * @menu
 * @description
 * @createTime 11:18 2021/8/16
 */
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;
@Data
@TableName("t_bill")
public class Bill {
    private Long orderId;
    private Integer userId;
    private Long addressId;
    private String status;
    private Date createTime;
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}