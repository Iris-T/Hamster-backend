package cn.iris.hamster.controller;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Cargo;
import cn.iris.hamster.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 货物服务接口
 *
 * @author Iris
 * @ClassName CargoController
 * @date 2023/1/9 14:34
 */

@PreAuthorize("hasAuthority('cargo:op')")
@RequestMapping("cargo")
@RestController
public class CargoController {
    @Autowired
    private CargoService cargoService;

    @PostMapping("new")
    public ResultEntity newCargo(Cargo cargo) {
        return cargoService.newCargo(cargo);
    }

    @PostMapping("modify")
    public ResultEntity modify(Cargo cargo) {
        return cargoService.modify(cargo);
    }
}
