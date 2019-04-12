package io.renren.config.vod;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="vod")
public class VodConfig {

    /**
     *点播服务接入区域
     */
    private String regionId;
    /**
     *阿里云秘钥key
     */
    private String accessKeyId;
    /**
     *阿里云秘钥Secret;
     */
    private String accessKeySecret;
    /**
     * 角色权限
     */
    private String roleArn;

}
