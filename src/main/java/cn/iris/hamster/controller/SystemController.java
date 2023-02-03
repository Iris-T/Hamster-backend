package cn.iris.hamster.controller;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.bean.vo.StaticInfoVo;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.service.CargoService;
import cn.iris.hamster.service.CooperativeService;
import cn.iris.hamster.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

    @Autowired
    private UserService userService;
    @Autowired
    private CooperativeService coService;
    @Autowired
    private CargoService cargoService;

    /**
     * 获取系统静态数值信息
     */
    @GetMapping("getInfo")
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
    @GetMapping("chartInfo")
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
    @GetMapping("facility")
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
}