package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iris.hamster.bean.dto.SysFieldDto;
import cn.iris.hamster.bean.enums.VehicleStatusEnum;
import cn.iris.hamster.bean.pojo.SystemField;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.bean.pojo.Vehicle;
import cn.iris.hamster.bean.pojo.Warehouse;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.vo.StaticInfoVo;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.common.utils.MockUtils;
import cn.iris.hamster.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static cn.iris.hamster.common.constants.CommonConstants.*;

/**
 * 系统服务接口
 *
 * @author Iris
 * @ClassName SystemController
 * @date 2022/12/30 14:04
 */

@RestController
@RequestMapping("/sys")
@PreAuthorize("hasRole('admin')")
public class SystemController {

    public static final Logger log = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CooperativeService coService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private CargoService cargoService;
    @Autowired
    private SystemFieldService systemFieldService;
    @Autowired
    private MockUtils mockUtils;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private FinanceService financeService;

    @Value("${common.lbs_key}")
    private String lbsKey;

    /**
     * 获取系统静态数值信息
     */
    @GetMapping("/getInfo")
    public ResultEntity getInfo() throws BaseException {
        ArrayList<StaticInfoVo> data = new ArrayList<>();
        // 用户数统计
        data.add(new StaticInfoVo("总用户数", userService.count(), "活跃用户", userService.count(new QueryWrapper<User>().eq("status", STATUS_ENABLE)), UNIT_NONE));
        // 企业每月新增数统计
        data.add(new StaticInfoVo("月结客户", coService.count(), "新增合作", coService.monthlyNewCoCount(), UNIT_MONTH));
        // TODO 货物统计
        // TODO 运输统计
        // TODO 仓库统计
        // 当月财务统计
        data.add(new StaticInfoVo("当月预估营收", financeService.getCurMonFinance().setScale(2, BigDecimal.ROUND_FLOOR), "", UNIT_RMB, UNIT_MONTH));
        return ResultEntity.success(data);
    }

    /**
     * 获取系统图表展示数据
     */
    @GetMapping("/chartInfo")
    public ResultEntity chartInfo(String type) throws BaseException {
        // TODO 获取图表所需统计数据
        HashMap<String, Object> data = new HashMap<>();
        ArrayList<Integer> sum = new ArrayList<>();
        ArrayList<Integer> ongoing = new ArrayList<>();
        ArrayList<Integer> finish = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        if (StringUtils.isNoneBlank(type)) {
            Collections.addAll(sum, 123, 158, 139);
            Collections.addAll(ongoing, 67, 89, 100);
            Collections.addAll(finish, 50, 60, 56);
            Collections.addAll(date, "2-1", "2-2", "2-3");
        } else {
            return ResultEntity.error("错误参数");
        }
        data.put("sum", sum);
        data.put("ongoing", ongoing);
        data.put("finish", finish);
        data.put("date", date);
        return ResultEntity.success(data);
    }

    /**
     * 获取车辆和仓库的展示信息
     */
    @GetMapping("/facility")
    public ResultEntity facility() throws BaseException {
        HashMap<String, Object> data = new HashMap<>();
        ArrayList<StaticInfoVo> carInfo = new ArrayList<>();
        Collections.addAll(carInfo, new StaticInfoVo("闲置车辆数量", vehicleService.count(new QueryWrapper<Vehicle>().eq("status", VehicleStatusEnum.UNUSED.getKey()))),
                new StaticInfoVo("作业车辆数量", vehicleService.count(new QueryWrapper<Vehicle>().eq("status", VehicleStatusEnum.WORK.getKey()))),
                new StaticInfoVo("检修车辆数量", vehicleService.count(new QueryWrapper<Vehicle>().eq("status", VehicleStatusEnum.CHECK.getKey()))),
                new StaticInfoVo("停用车辆数量", vehicleService.count(new QueryWrapper<Vehicle>().eq("status", VehicleStatusEnum.DISUSE.getKey()))));
        // TODO 获取仓库各类统计数据 总数 正常仓数 爆仓警示（>90%） 闲仓警示(<10%) 停用
        ArrayList<StaticInfoVo> whInfo = new ArrayList<>();
        Collections.addAll(whInfo, new StaticInfoVo("正常使用仓数", 50),
                new StaticInfoVo("爆仓预警数量", 16),
                new StaticInfoVo("闲仓预警数量", 2),
                new StaticInfoVo("停用仓库数量", 2));
        data.put("car", carInfo);
        data.put("wh", whInfo);
        return ResultEntity.success(data);
    }

    @GetMapping("/field/list")
    public ResultEntity systemFieldList(SystemField query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("fields", systemFieldService.listByLimit(query));
        data.put("total", systemFieldService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @PostMapping("/field/add")
    public ResultEntity systemFieldAdd(@RequestBody SysFieldDto sysField) {
        long count = systemFieldService.count(new QueryWrapper<SystemField>().eq("`key`", sysField.getKey()));
        if (count > 0) {
            throw new BaseException("信息重复或有误，请核对重试或联系管理员");
        }
        SystemField add = new SystemField();
        BeanUtil.copyProperties(sysField, add);
        if ("0".equals(add.getType())) {
            add.setValue(null);
        }
        if ("1".equals(add.getType())) {
            add.setStr(null);
        }
        boolean save = systemFieldService.save(add);
        return ResultEntity.success("添加成功");
    }

    @PostMapping("/field/{fid}/update")
    public ResultEntity systemFieldUpdate(@PathVariable Long fid, @RequestBody SysFieldDto sysField) {
        SystemField update = new SystemField().setId(fid);
        BeanUtil.copyProperties(sysField, update);
        systemFieldService.updateById(update);
        return ResultEntity.success("更新成功");
    }

    @PostMapping("/field/{fid}/changeStatus")
    public ResultEntity systemFieldchangeStatus(@PathVariable Long fid, @RequestBody String status) {
        if (!CommonUtils.isRightStatus(status)) {
            throw new BaseException("参数错误");
        }
        SystemField field = systemFieldService.getById(fid);
        if (field.getStatus().equals(status)) {
            throw new BaseException("参数错误");
        }
        field.setStatus(status);
        systemFieldService.updateById(field);
        return ResultEntity.success("更新状态成功");
    }

    @PostMapping("/batch/mock/users")
    public ResultEntity batchMockUsers(Integer size) throws BaseException, InterruptedException {
        if (ObjectUtil.isEmpty(size)) {
            throw new BaseException("参数错误");
        }
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            User user = mockUtils.getFakeUser();
            log.info(user.toString());
            users.add(user);
            Thread.sleep(10);
        }
        userService.saveBatch(users);
        return ResultEntity.success("批量插入用户数据成功");
    }

    @PostMapping("/batch/mock/vehicles")
    public ResultEntity batchMockVehicles(Integer size) throws BaseException, InterruptedException {
        if (ObjectUtil.isEmpty(size)) {
            throw new BaseException("参数错误");
        }
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Vehicle vehicle = mockUtils.getFakeVehicle();
            if (vehicle.getPlateNo().contains("挂") && vehicle.getLoad() < 25000) {
                continue;
            }
            vehicles.add(vehicle);
            Thread.sleep(10);
        }
        vehicleService.saveBatch(vehicles);
        return ResultEntity.success("批量插入车辆数据成功");
    }

    @PostMapping("/batch/update/wh/info")
    public ResultEntity batchUpdateWhInfo() throws BaseException, InterruptedException {
        ArrayList<String> errMsg = Lists.newArrayList();
        List<Warehouse> list = warehouseService.list();
        for (Warehouse wh : list) {
            String body = HttpRequest.get("https://apis.map.qq.com/ws/geocoder/v1?address=" + wh.getAddress() + "&key=" + lbsKey)
                    .header("Content-Type", "application/json")
                    .execute().body();
            JSONObject result = (JSONObject) JSONUtil.parseObj(body).get("result");
            if (result == null) {
                errMsg.add(wh.getName()+"更新失败");
            } else {
                wh.setCityCode((String) ((JSONObject) result.get("ad_info")).get("adcode"))
                        .setLon((BigDecimal) ((JSONObject) result.get("location")).get("lng"))
                        .setLat((BigDecimal) ((JSONObject) result.get("location")).get("lat"));
            }
            Thread.sleep(100);
        }
        warehouseService.updateBatchById(list);
        HashMap<String, Object> data = new HashMap<>();
        data.put("info", "更新仓库数据成功，请前往仓库数据页面查看");
        data.put("errMsg", errMsg);
        return ResultEntity.success(data);
    }
}