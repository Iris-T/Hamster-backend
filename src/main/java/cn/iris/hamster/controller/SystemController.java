package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.dto.SysFieldDto;
import cn.iris.hamster.bean.entity.Permission;
import cn.iris.hamster.bean.vo.SystemFieldVo;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.entity.SystemField;
import cn.iris.hamster.bean.entity.User;
import cn.iris.hamster.bean.vo.StaticInfoVo;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.common.utils.MockUtils;
import cn.iris.hamster.service.CargoService;
import cn.iris.hamster.service.CooperativeService;
import cn.iris.hamster.service.SystemFieldService;
import cn.iris.hamster.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
    private CargoService cargoService;
    @Autowired
    private SystemFieldService systemFieldService;
    @Autowired
    private MockUtils mockUtils;

    /**
     * 获取系统静态数值信息
     */
    @GetMapping("/getInfo")
    public ResultEntity getInfo() throws BaseException {
        ArrayList<StaticInfoVo> data = new ArrayList<>();
        // 用户数统计
        data.add(new StaticInfoVo("总用户数", userService.count(),
                "活跃用户", userService.count(new QueryWrapper<User>().eq("status", "0")),
                CommonConstants.UNIT_NONE));
        // 企业每月新增数统计
        data.add(new StaticInfoVo("合作伙伴", coService.count(),
                "新增合作", coService.monthlyNewCoCount(),
                CommonConstants.UNIT_MONTH));
        // TODO 货物统计
        // TODO 运输统计
        // TODO 仓库统计
        // TODO 财务统计
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
        // TODO 获取车辆各类状态统计数据 总数 闲置 作业中 检修 报废
        ArrayList<StaticInfoVo> carInfo = new ArrayList<>();
        Collections.addAll(carInfo, new StaticInfoVo("登记车辆总数", 100),
                new StaticInfoVo("闲置车辆数量", 30),
                new StaticInfoVo("作业车辆数量", 60),
                new StaticInfoVo("检修车辆数量", 5));
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

    @PostMapping("/batchMockUsers")
    public ResultEntity batchMockUsers(Integer size) throws BaseException {
        if (ObjectUtil.isEmpty(size)) {
            return ResultEntity.error("参数错误");
        }
        int count = 0;
        for (int i = 0; i < size; i++) {
            count += userService.save(mockUtils.getFakeUser()) ? 1 : 0;
            // 休眠半秒
            try {
                Thread.sleep(1000);
                System.out.println("休眠1秒");
            } catch (InterruptedException e) {
                log.error("批量添加失败,已添加${}条用户数据", size);
                throw new BaseException("批量添加失败,已添加"+size+"条用户数据");
            }
        }
        return count == size
                ? ResultEntity.success("批量添加"+size+"条用户数据成功")
                : ResultEntity.error("批量添加失败,已添加"+size+"条用户数据");
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
}