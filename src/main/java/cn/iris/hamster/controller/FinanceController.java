package cn.iris.hamster.controller;

import cn.iris.hamster.bean.pojo.Finance;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author Iris
 * @ClassName FinanceController
 * @date 2023/5/19 20:49
 */
@RestController
@RequestMapping("/finance")
public class FinanceController {
    @Autowired
    private FinanceService financeService;

    @RequestMapping("/list")
    public ResultEntity listByLimit(Finance query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        List<Finance> fee = financeService.listByLimit(query);
        System.out.println(fee);
        data.put("fee", fee);
        data.put("total", financeService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success("查询成功", data);
    }
}
