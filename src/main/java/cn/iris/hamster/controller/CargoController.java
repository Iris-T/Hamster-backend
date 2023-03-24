package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.iris.hamster.bean.dto.CargoDto;
import cn.iris.hamster.bean.enums.CargoStatusEnum;
import cn.iris.hamster.bean.pojo.CargoType;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Cargo;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.service.CargoService;
import cn.iris.hamster.service.CargoTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static cn.iris.hamster.common.constants.CommonConstants.STATUS_ENABLE;

/**
 * 货物服务接口
 *
 * @author Iris
 * @ClassName CargoController
 * @date 2023/1/9 14:34
 */

@PreAuthorize("hasAuthority('cargo:manage')")
@RequestMapping("/cargo")
@RestController
public class CargoController {
    @Autowired
    private CargoService cargoService;
    @Autowired
    private CargoTypeService cargoTypeService;

    @GetMapping("/list")
    public ResultEntity listByLimit(Cargo query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("cargos", cargoService.listByLimit(query));
        data.put("cargoStatus", CargoStatusEnum.values());
        data.put("cargoTypes", cargoTypeService.list(new QueryWrapper<CargoType>().eq("`status`", STATUS_ENABLE)));
        data.put("total", cargoService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @PostMapping("/add")
    public ResultEntity addCargo(@RequestBody CargoDto cargo) {
        Cargo add = new Cargo().setId(CommonUtils.randId())
                .setStatus(CargoStatusEnum.NO_WAREHOUSE.getKey());
        BeanUtil.copyProperties(cargo, add);
        cargoService.save(add);
        return ResultEntity.success("添加货物信息成功");
    }

    @PostMapping("/{cid}/update")
    public ResultEntity updateCargo(@PathVariable Long cid, @RequestBody CargoDto dto) {
        Cargo byId = cargoService.getById(cid);
        if (!CargoStatusEnum.isNoWarehouse(byId.getStatus())) {
            throw new BaseException("货物已核验入库，请联系仓库人员修改");
        }
        Cargo update = new Cargo().setId(cid);
        BeanUtil.copyProperties(dto, update);
        cargoService.updateById(update);
        return ResultEntity.success("更新货物信息成功");
    }
}
