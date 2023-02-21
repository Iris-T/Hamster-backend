package cn.iris.hamster.common.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iris.hamster.bean.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 数据模拟工具类
 *
 * @author Iris
 * @ClassName MockUtils
 * @date 2022/12/30 16:49
 */

@Component
public class MockUtils {
    private static final String DEFAULT_PWD = "123456";
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
                .setUsername(username)
                .setPassword(passwordEncoder.encode(DEFAULT_PWD))
                .setName(name)
                .setGender(gender)
                .setIdNo(idNo)
                .setPhone(phone)
                .setAddress(address);
    }
}
