package cn.iris.hamster.common.constants;

/**
 * 公共常量类
 *
 * @author Iris
 * @ClassName CommonConstants
 * @date 2022/12/28 15:18
 */
public interface CommonConstants {
    /**
     * 时间转换为毫秒
     */
    Long MONTH_TO_MS = 2_629_800_000L;
    Long WEEK_TO_MS = 604_800_000L;
    Long DAY_TO_MS = 86_400_000L;

    /**
     * 默认分页大小
     */
    Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 通用状态-0启用,1停用
     */
    String STATUS_ENABLE = "0";
    String STATUS_DISABLE = "1";

    /**
     * 性别-0女,1男
     */
    String GENDER_FEMALE = "0";
    String GENDER_MALE = "1";

    /**
     * Redis存储Key前缀
     */
    String REDIS_CACHE_TOKEN_PREFIX = "User:";
    String REDIS_AUTHORITY_KEY_PREFIX = "Authority:";

    /**
     * Token TTL
     */
    Long TOKEN_TTL_MILLISECONDS = 1000 * 60 * 60 * 12L;
    Long TOKEN_TTL_SECONDS = 60 * 60 * 12L;

    /**
     * 角色字符串前缀
     */
    String ROLE_PREFIX = "ROLE_";

    /**
     * 角色关键字字符
     */
    String ROLE_ADMIN = "admin";
    String ROLE_KEEPER = "keeper";
    String ROLE_OPERATOR = "operator";
    String ROLE_CO = "co";
    String ROLE_NORMAL = "normal";
    String ROLE_CO_ADMIN = "co_admin";

    /**
     * 前端元素Type类型/颜色类型
     */
    String TYPE_SUCCESS = "success";
    String TYPE_WARNING = "warning";
    String TYPE_INFO = "info";
    String TYPE_DANGER = "danger";

    /**
     * 数据文字单位
     */
    String UNIT_NONE = "~";
    String UNIT_YEAR = "年";
    String UNIT_MONTH = "月";
    String UNIT_WEEK = "周";
    String UNIT_DAY = "天";
    String UNIT_KG = "千克";
    String UNIT_T = "吨";
}
