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
     * 仓库类型,0-企业私有,1-公共仓库
     */
    String WH_PRIVATE = "0";
    String WH_PUBLIC = "1";

    /**
     * Redis存储Key前缀
     */
    String REDIS_CACHE_TOKEN_PREFIX = "User:";
    String REDIS_AUTHORITY_KEY_PREFIX = "GrantedAuthority:";

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
}
