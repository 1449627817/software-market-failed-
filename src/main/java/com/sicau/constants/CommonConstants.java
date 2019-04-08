package com.sicau.constants;
/**
 * URL参数
 * @author wzw
 * Created in 12:43 2019/1/25
 */
public interface CommonConstants {
    /**
     * api版本号
     */
    String API_VERSION = "/v1";
    /**
     * 非公共api的前缀
     */
    String NONPUBLIC_PREFIX = API_VERSION + "/nonpub";
    /**
     * 公共api前缀
     */
    String PUB_PREFIX = API_VERSION + "/pub";
}
