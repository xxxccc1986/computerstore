package top.year21.computerstore.service;

import top.year21.computerstore.entity.District;

import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理省市区业务层接口
 * @date 2022/7/14 15:28
 */
public interface IDistrictService{

    //查询某个特定省份的信息
    List<District> getSpecifyDistrictByParent(String parent);

    //根据code查询当前省市区的名称
    String getNameByCode(String code);

}
