package cn.iris.hamster.controller;

import cn.iris.hamster.bean.enums.VehicleStatusEnum;
import cn.iris.hamster.bean.pojo.Vehicle;
import cn.iris.hamster.bean.pojo.Warehouse;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.service.CargoService;
import cn.iris.hamster.service.UserService;
import cn.iris.hamster.service.VehicleService;
import cn.iris.hamster.service.WarehouseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 共用接口
 *
 * @author Iris
 * @ClassName CommonController
 * @date 2023/2/21 9:44
 */

@RestController
public class CommonController {
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CargoService cargoService;

    /**
     * 检查用户身份证号重复
     * @param idNo 身份证号
     * @return
     */
    @PostMapping("/check/user/idNo")
    public ResultEntity checkUserIdNo(@RequestBody String idNo) {
        long cnt = userService.count(new QueryWrapper<User>().eq("ID_NO", idNo));
        return cnt == 0 ? ResultEntity.success() : ResultEntity.error();
    }

    /**
     * 检查用户用户名重复
     * @param username 用户名
     * @return
     */
    @PostMapping("/check/user/username")
    public ResultEntity checkUserUsername(@RequestBody String username) {
        long cnt = userService.count(new QueryWrapper<User>().eq("username", username));
        return cnt == 0 ? ResultEntity.success() : ResultEntity.error();
    }

    /**
     * TODO 获取选择角色列表
     */

    /**
     * TODO 获取权限选择列表
     */

    /**
     * 获取车辆选择列表
     */
    @GetMapping("/common/vehicle/select/list")
    public ResultEntity getVehicleSelectList() {
        return ResultEntity.success(vehicleService.list(new QueryWrapper<Vehicle>().select("id", "plate_no").eq("`status`", VehicleStatusEnum.UNUSED.getKey())));
    }

    /**
     * 获取司机选择列表
     */
    @GetMapping("/common/driver/select/list")
    public ResultEntity getDriverSelectList() {
        return ResultEntity.success(userService.getDriverSelectList());
    }

    /**
     * 获取仓库选择列表
     */
    @GetMapping("/common/wh/select/list")
    public ResultEntity getWarehouseSelectList() {
        return ResultEntity.success(warehouseService.list(new QueryWrapper<Warehouse>().select("id", "`name`").eq("`status`", CommonConstants.STATUS_ENABLE)));
    }

    /**
     * 获取货物选择列表
     */
    @GetMapping("/common/cargo/select/list")
    public ResultEntity getCargoSelectList() {
        return ResultEntity.success(cargoService.getSelectList());
    }
}
