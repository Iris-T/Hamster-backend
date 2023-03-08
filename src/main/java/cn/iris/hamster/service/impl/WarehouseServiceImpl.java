package cn.iris.hamster.service.impl;

import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.entity.Warehouse;
import cn.iris.hamster.service.WarehouseService;
import cn.iris.hamster.mapper.WarehouseMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author asus
* @description 针对表【warehouse(仓库表)】的数据库操作Service实现
* @createDate 2023-01-05 15:56:56
*/
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse>
    implements WarehouseService{
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultEntity saveWh(Warehouse wh) {
        if (!isWhValid(wh)) {
            return ResultEntity.error("数据格式错误");
        }

        int cnt = 0;

        if (ObjectUtils.isEmpty(wh.getId())) {
            // 检查仓库数据关键字段，若不存在则进行填充
            wh.setId(CommonUtils.randId());
            cnt = warehouseMapper.insert(wh);
        } else {
            cnt = warehouseMapper.updateById(wh);
        }

        return cnt > 0 ? ResultEntity.success("更新仓库信息成功") : ResultEntity.success("更新仓库信息失败");
    }

    @Override
    public ResultEntity changeStatus(Warehouse wh, String type) {
        int res = 0;
        switch (type) {
            case CommonConstants.STATUS_ENABLE -> {
                if (CommonConstants.STATUS_ENABLE.equals(wh.getStatus())) {
                    return ResultEntity.error("仓库已启用");
                }
                wh.setStatus(CommonConstants.STATUS_ENABLE);
                res = warehouseMapper.updateById(wh);
            }
            case CommonConstants.STATUS_DISABLE -> {
                if (CommonConstants.STATUS_DISABLE.equals(wh.getStatus())) {
                    return ResultEntity.error("仓库已停用");
                }
                wh.setStatus(CommonConstants.STATUS_DISABLE);
                res = warehouseMapper.updateById(wh);
            }
            default -> {
                return ResultEntity.error("参数错误");
            }
        }
        return res > 0 ? ResultEntity.success("修改仓库状态成功") : ResultEntity.error("修改仓库状态失败");
    }

    private boolean isWhValid(Warehouse wh) {
        boolean temp = true;
        if (ObjectUtils.isNotEmpty(wh.getId())) {
            temp = ObjectUtils.isNotEmpty(getById(wh.getId()));
        }

        return temp
                && StringUtils.isNotEmpty(wh.getName())
                && StringUtils.isNotEmpty(wh.getAddress());
    }
}




