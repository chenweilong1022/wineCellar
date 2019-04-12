package io.renren.config.vod;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import io.renren.common.utils.SpringContextUtils;

/**
 * @author jack
 * @date 2017/7/4
 */
public class StsUtil {

    private static VodConfig vodConfig = SpringContextUtils.getBean(VodConfig.class);

    // 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
    public static final String REGION_CN_HANGZHOU = vodConfig.getRegionId();
    // 当前 STS API 版本
    public static final String STS_API_VERSION = "2015-04-01";
    static AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret,
                                         String roleArn, String roleSessionName, String policy,
                                         ProtocolType protocolType) throws ClientException {
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            // 发起请求，并得到response
            final AssumeRoleResponse response = client.getAcsResponse(request);
            return response;
        } catch (ClientException e) {
            throw e;
        }
    }

    public static AssumeRoleResponse assumeRole(String roleSessionName) throws ClientException {
        return assumeRole(vodConfig.getAccessKeyId(), vodConfig.getAccessKeySecret(),
                vodConfig.getRoleArn(), roleSessionName, null, ProtocolType.HTTPS);
    }

//    public static void main(String[] args) {
//        // 只有RAM用户（子账号）才能调用 AssumeRole 接口
//        // 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
//        // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
//        String accessKeyId = "LTAIFaObgOEWIzHe";
//        String accessKeySecret = "Udyo7RmKYiE05IlrHpzwdVXXaedM5M";
//        // AssumeRole API 请求参数: RoleArn, RoleSessionName, Policy, and DurationSeconds
//        // RoleArn 需要在 RAM 控制台上获取
//        String roleArn = "acs:ram::1408684116589269:role/vodrole"; // 即角色详情页的Arn值
//        // RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
//        // 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
//        // 具体规则请参考API文档中的格式要求
//        String roleSessionName = "roleSessionName"; // 自定义即可
//        // 定制你的policy
//        String policy = "{\n" +
//                "  \"Version\": \"1\",\n" +
//                "  \"Statement\": [\n" +
//                "    {\n" +
//                "      \"Action\": \"vod:*\",\n" +
//                "      \"Resource\": \"*\",\n" +
//                "      \"Effect\": \"Allow\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//        // 此处必须为 HTTPS
//        ProtocolType protocolType = ProtocolType.HTTPS;
//        try {
//            final AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret,
//                    roleArn, roleSessionName, policy, protocolType);
//            System.out.println("Expiration: " + response.getCredentials().getExpiration());
//            System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
//            System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
//            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
//            createUploadVideo(response.getCredentials().getAccessKeyId(),
//                    response.getCredentials().getAccessKeySecret(),
//                    response.getCredentials().getSecurityToken());
//        } catch (ClientException e) {
//            System.out.println("Failed to get a token.");
//            System.out.println("Error code: " + e.getErrCode());
//            System.out.println("Error message: " + e.getErrMsg());
//        }
//    }
//    static void createUploadVideo(String accessKeyId, String accessKeySecret, String token) {
//        String regionId = "cn-shanghai"; // 点播服务所在的Region，国内请填cn-shanghai，不要填写别的区域
//        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
//        DefaultAcsClient client = new DefaultAcsClient(profile);
//        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
//        request.setSecurityToken(token);
//        request.setTitle("t1");
//        request.setFileName("file1.mp4");
//        request.setFileSize(10240L);
//        try {
//            CreateUploadVideoResponse response = client.getAcsResponse(request);
//            System.out.println("CreateUploadVideoRequest, " + request.getUrl());
//            System.out.println("CreateUploadVideoRequest, requestId:" + response.getRequestId());
//            System.out.println("UploadAddress, " + response.getUploadAddress());
//            System.out.println("UploadAuth, " + response.getUploadAuth());
//            System.out.println("VideoId, " + response.getVideoId());
//        } catch (ClientException e) {
//            System.out.println("action, error:" + e);
//            e.printStackTrace();
//        }
//    }
}
