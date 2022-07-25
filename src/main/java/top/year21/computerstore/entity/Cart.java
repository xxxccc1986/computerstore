package top.year21.computerstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 对应数据表t_cart的实体类
 * @date 2022/7/16 19:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity {
    private Integer cid;
    private Integer uid;
    private Integer pid  ;
    private Long price;
    private Integer num;
}
