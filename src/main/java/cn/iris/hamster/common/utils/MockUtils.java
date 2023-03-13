package cn.iris.hamster.common.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iris.hamster.bean.entity.User;
import cn.iris.hamster.bean.entity.Vehicle;
import cn.iris.hamster.bean.enums.VehicleStatusEnum;
import cn.iris.hamster.common.constants.CommonConstants;
import com.mifmif.common.regex.Generex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Random;

/**
 * 数据模拟工具类
 *
 * @author Iris
 * @ClassName MockUtils
 * @date 2022/12/30 16:49
 */

@Component
public class MockUtils {
    private static final Generex PLATE_NO_GENERATOR = new Generex("[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼][A-Z]·[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂]");
    /**
     * 载重-KG | 装载空间-M^3
     */
    private static final double[][] VEHICLE_DATA = {{3000, 12}, {5000, 30}, {10000, 40}, {12000, 45}, {25000, 60}, {28000, 80}, {30000, 75}, {30000, 120}, {35000, 80}, {35000, 110}, {80000, 96}};
    private static final Random RAND = new Random();
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getFakeUser() {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("city", "");
        requestBody.put("path", "/cn-address");
        requestBody.put("method", "refresh");
        String body = HttpRequest.post("https://www.meiguodizhi.com/api/v1/dz")
                .header("authority", "www.meiguodizhi.com")
                .header("content-type", "application/json")
                .header("cookie", "_gid=GA1.2.470463099.1675581863; _gat_gtag_UA_148809406_3=1; _ga_PE5DSSCM9B=GS1.1.1675581863.2.0.1675581863.0.0.0; _ga=GA1.1.73410501.1675416045")
                .body(JSONUtil.toJsonStr(requestBody))
                .execute().body();
        JSONObject info = (JSONObject) JSONUtil.parseObj(body).get("address");
        String address = String.valueOf(info.get("State")) + info.get("City") + info.get("xian") + info.get("Address");
        String phone = ((String) info.get("Telephone")).split(" ")[1];
        String name = (String) info.get("Full_Name");
        String username = (String) info.get("Password");
        String gender = "Male".equals(info.get("Gender")) ? "1" : "0";
        String idNo = (String) info.get("Chain_ID_Card");
        return new User().setId(CommonUtils.randId())
                .setStatus(CommonConstants.STATUS_ENABLE)
                .setUsername(username)
                .setPassword(passwordEncoder.encode(idNo.substring(idNo.length() - 6)))
                .setName(name)
                .setGender(gender)
                .setIdNo(idNo)
                .setPhone(phone)
                .setAddress(address);
    }

    public Vehicle getFakeVehicle() {
        int i = RAND.nextInt(VEHICLE_DATA.length);
        return new Vehicle()
                .setId(CommonUtils.randId())
                .setPlateNo(PLATE_NO_GENERATOR.random())
                .setLoad(VEHICLE_DATA[i][0])
                .setSpace(VEHICLE_DATA[i][1])
                .setStatus(VehicleStatusEnum.UNUSED.getKey());
    }
}
