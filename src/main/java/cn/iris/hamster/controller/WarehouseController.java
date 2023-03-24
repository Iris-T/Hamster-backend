package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.iris.hamster.bean.bo.excel.WarehouseExcelBo;
import cn.iris.hamster.bean.dto.WarehouseDto;
import cn.iris.hamster.bean.pojo.Warehouse;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.common.utils.ListUtils;
import cn.iris.hamster.common.utils.WebUtils;
import cn.iris.hamster.service.WarehouseService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static cn.iris.hamster.common.constants.CommonConstants.STATUS_ENABLE;

/**
 * 仓库服务接口类
 *
 * @author Iris
 * @ClassName WarehouseController
 * @date 2023/1/6 16:26
 */

@PreAuthorize("hasAuthority('wh:manage')")
@RestController
@RequestMapping("/wh")
public class WarehouseController {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @Autowired
    private WarehouseService warehouseService;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/list")
    public ResultEntity listByLimit(Warehouse query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("whs", warehouseService.listByLimit(query));
        data.put("total", warehouseService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/add")
    public ResultEntity addWarehouse(@RequestBody WarehouseDto warehouse) {
        if (ObjectUtils.isEmpty(warehouse)) {
            throw new BaseException("参数有误");
        }
        Warehouse add = new Warehouse().setId(CommonUtils.randId()).setStatus(STATUS_ENABLE);
        BeanUtil.copyProperties(warehouse, add);
        warehouseService.save(add);
        return ResultEntity.success("添加仓库信息成功");
    }

    @PostMapping("/{wid}/changeStatus")
    public ResultEntity changeStatus(@PathVariable Long wid, @RequestBody String status) {
        if (StringUtils.isBlank(status) || !CommonUtils.isRightStatus(status)) {
            throw new BaseException("参数有误");
        }
        Warehouse warehouse = warehouseService.getById(wid);
        if (warehouse.getStatus().equals(status)) {
            throw new BaseException("参数有误");
        }
        // TODO 检查仓库是否还有货物存留
        // TODO 检查仓库是否还有车辆存留
        warehouse.setStatus(status);
        warehouseService.updateById(warehouse);
        return ResultEntity.success("更新仓库状态成功");
    }

    @PostMapping("/{wid}/update")
    public ResultEntity updateWarehouse(@PathVariable Long wid, @RequestBody WarehouseDto warehouse) {
        if (ObjectUtils.isEmpty(warehouse)) {
            throw new BaseException("参数有误");
        }
        // TODO 检查更新字段 - 若是更改地址信息则需要检查仓库当前是否承载运输任务
        Warehouse update = new Warehouse().setId(wid);
        BeanUtil.copyProperties(warehouse, update);
        warehouseService.updateById(update);
        return ResultEntity.success("更新仓库信息成功");
    }

    @GetMapping("/export/template")
    public void exportNoneTemplate(HttpServletResponse response) {
        WebUtils.setExcelResponseProp(response, "仓库信息批量导入模板");
        List<Warehouse> list = Lists.newArrayList();
        List<WarehouseExcelBo> excelBos = ListUtils.listToAnotherList(list, WarehouseExcelBo.class);
        try {
            EasyExcel.write(response.getOutputStream())
                    .head(WarehouseExcelBo.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("仓库信息")
                    .doWrite(list);
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    @GetMapping("/export/data")
    public void exportWithData(HttpServletResponse response, Warehouse query) {
        WebUtils.setExcelResponseProp(response, "仓库信息导出数据");
        QueryWrapper<Warehouse> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query.getKeyword())) {
            wrapper.like("`name`", query.getKeyword())
                    .like("`address`", query.getKeyword());
        }
        if (StringUtils.isNotBlank(query.getStatus())) {
            wrapper.eq("`status`", query.getStatus());
        }
        List<Warehouse> list = warehouseService.list(wrapper);
        List<WarehouseExcelBo> excelBos = ListUtils.listToAnotherList(list, WarehouseExcelBo.class);
        try {
            EasyExcel.write(response.getOutputStream())
                    .head(WarehouseExcelBo.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("仓库信息")
                    .doWrite(list);
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    @PostMapping("/import")
    public ResultEntity importDataFromTemplate(@RequestBody MultipartFile file) {
        HashMap<String, Object> data = new HashMap<>();
        List<String> errMsg = Lists.newArrayList();
        try {
//            if (CommonUtils.isExcel(file.getInputStream())) {
//                throw new BaseException("文件类型错误");
//            }
            List<WarehouseExcelBo> bos = EasyExcel.read(file.getInputStream())
                    .head(WarehouseExcelBo.class)
                    .sheet()
                    .doReadSync();
            List<Warehouse> whs = ListUtils.listToAnotherList(bos, Warehouse.class);
            // 处理数据
            for (int i = 0; i < whs.size(); i++) {
                Warehouse wh = whs.get(i);
                if (ObjectUtils.isEmpty(wh)) {
                    errMsg.add("第"+ (i+1) +"行数据导入失败,数据为空");
                    continue;
                }
                Warehouse whByName = warehouseService.getWhByName(wh.getName());
                wh.setId(ObjectUtils.isNotEmpty(whByName) ? whByName.getId() : CommonUtils.randId());
                warehouseService.saveOrUpdate(wh);
            }
            data.put("info", "解析到" + whs.size() + "条数据,成功插入/更新" + (whs.size() - errMsg.size()) + "条数据");
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new BaseException("文件解析异常");
        }
        data.put("errMsg", errMsg);
        return ResultEntity.success(data);
    }
}
