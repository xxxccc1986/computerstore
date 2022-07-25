package top.year21.computerstore.mapper;

import top.year21.computerstore.entity.District;

import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 实体类District对应的mapper接口
 * @date 2022/7/14 15:14
 */
public interface DistrictMapper {

    //根据父代号查询区域信息
    List<District> queryDistrictByParent(String parent);

    //根据code查询当前省市区的名称
    String queryDistrictByCode(String code);
}
