package io.renren.common.utils.vod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import io.renren.common.utils.SpringContextUtils;
import io.renren.config.vod.VodConfig;

import java.net.URLEncoder;

public class VodUtil {

    private static VodConfig vodConfig = null;

    static {
        vodConfig = SpringContextUtils.getBean(VodConfig.class);
    }

    /**
     * 初始化阿里云视频客户端
     * @return
     * @throws ClientException
     */
    public static DefaultAcsClient initVodClient() {
        DefaultProfile profile = DefaultProfile.getProfile(vodConfig.getRegionId(), vodConfig.getAccessKeyId(), vodConfig.getAccessKeySecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    /**
     * 初始化阿里云视频客户端sts方式
     * @param accessKeyId
     * @param accessKeySecret
     * @param securityToken
     * @return
     * @throws ClientException
     */
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret, String securityToken) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret, securityToken);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    public static CreateUploadVideoResponse createUploadVideo() {
        try {
            return createUploadVideo(initVodClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取视频上传地址和凭证
     * @param client 发送请求客户端
     * @return CreateUploadVideoResponse 获取视频上传地址和凭证响应数据
     * @throws Exception
     */
    public static CreateUploadVideoResponse createUploadVideo(DefaultAcsClient client) throws Exception {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle("this is a sample");
        request.setFileName("filename.mp4");
        JSONObject userData = new JSONObject();
        JSONObject messageCallback = new JSONObject();
        messageCallback.put("CallbackURL", "http://xxxxx");
        messageCallback.put("CallbackType", "http");
        userData.put("MessageCallback", messageCallback.toJSONString());
        JSONObject extend = new JSONObject();
        extend.put("MyId", "user-defined-id");
        userData.put("Extend", extend.toJSONString());
        request.setUserData(userData.toJSONString());
        return client.getAcsResponse(request);
    }

    /**
     * 刷新视频上传凭证
     * @param client 发送请求客户端
     * @return RefreshUploadVideoResponse 刷新视频上传凭证响应数据
     * @throws Exception
     */
    public static RefreshUploadVideoResponse refreshUploadVideo(DefaultAcsClient client,String videoId) throws Exception {
        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        request.setVideoId(videoId);
        return client.getAcsResponse(request);
    }

    /**
     * URL批量拉取上传
     * @param client 发送请求客户端
     * @return UploadMediaByURLResponse URL批量拉取上传响应数据
     * @throws Exception
     */
    public static UploadMediaByURLResponse uploadMediaByURL(DefaultAcsClient client) throws Exception {
        UploadMediaByURLRequest request = new UploadMediaByURLRequest();
        String url = "D:\\upload\\image\\441aa67b4494b479f9f176ea9bc1da0d.mp4";
        String encodeUrl = URLEncoder.encode(url, "UTF-8");
        request.setUploadURLs(encodeUrl);
        JSONObject uploadMetadata = new JSONObject();
        uploadMetadata.put("SourceUrl", encodeUrl);
        uploadMetadata.put("Title", "upload by url sample");
        JSONArray uploadMetadataList = new JSONArray();
        uploadMetadataList.add(uploadMetadata);
        request.setUploadMetadatas(uploadMetadataList.toJSONString());
        JSONObject userData = new JSONObject();
        JSONObject messageCallback = new JSONObject();
        messageCallback.put("CallbackURL", "http://xxxxx");
        messageCallback.put("CallbackType", "http");
        userData.put("MessageCallback", messageCallback.toJSONString());
        JSONObject extend = new JSONObject();
        extend.put("MyId", "user-defined-id");
        userData.put("Extend", extend.toJSONString());
        request.setUserData(userData.toJSONString());
        return client.getAcsResponse(request);
    }



    // 请求示例
//    public static void main(String[] argv) throws ClientException {
//        DefaultAcsClient client = initVodClient("LTAIFaObgOEWIzHe", "Udyo7RmKYiE05IlrHpzwdVXXaedM5M");
//        UploadMediaByURLResponse response = new UploadMediaByURLResponse();
//        try {
//            response = uploadMediaByURL(client);
//            System.out.print("UploadJobs = " + JSON.toJSONString(response.getUploadJobs()) + "\n");
//        } catch (Exception e) {
//            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
//        }
//        System.out.print("RequestId = " + response.getRequestId() + "\n");
//    }

//    // 请求示例
//    public static void main(String[] argv) throws ClientException {
//        DefaultAcsClient client = initVodClient("LTAIFaObgOEWIzHe", "Udyo7RmKYiE05IlrHpzwdVXXaedM5M");
//        CreateUploadVideoResponse response = new CreateUploadVideoResponse();
//        try {
//            response = createUploadVideo(client);
//            System.out.print("VideoId = " + response.getVideoId() + "\n");
//            System.out.print("UploadAddress = " + response.getUploadAddress() + "\n");
//            System.out.print("UploadAuth = " + response.getUploadAuth() + "\n");
//        } catch (Exception e) {
//            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
//        }
//        System.out.print("RequestId = " + response.getRequestId() + "\n");
//    }



    public  static GetVideoPlayAuthResponse.VideoMeta getVideoMeta(String videoId) throws ClientException {
        DefaultAcsClient defaultAcsClient = initVodClient();
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        request.setAuthInfoTimeout(Long.valueOf(2592000));
        return defaultAcsClient.getAcsResponse(request).getVideoMeta();
    }


}
