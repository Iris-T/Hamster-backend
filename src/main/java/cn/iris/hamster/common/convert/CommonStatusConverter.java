package cn.iris.hamster.common.convert;

import cn.iris.hamster.bean.enums.CommonStatusEnum;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

/**
 * 公共状态转换类
 *
 * @author Iris
 * @ClassName CommonStatusConverter
 * @date 2023/3/15 9:26
 */
public class CommonStatusConverter implements Converter<String> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(ReadConverterContext<?> context) {
        return CommonStatusEnum.convertFromWord(context.getReadCellData().getStringValue()).getStatus();
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) {
        return new WriteCellData<>(CommonStatusEnum.convertFromStatus(context.getValue()).getWord());
    }
}
