package io.renren.modules.app.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import io.renren.common.ueditor.ActionEnter;
import io.renren.common.utils.R;
import io.renren.config.FileConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/app")
@Api(value="APP上传文件接口",tags="APP上传文件接口")
public class AppFileController {

    @Autowired
    private FileConfig fileConfig;

    /**
     * 上传文件
     */
    @PostMapping("/file/upload")
    @ApiOperation("上传单张文件")
    public R upload(MultipartFile file, HttpServletRequest request) throws Exception {
        /**
         * 文件名称
         */
		String fileName = RandomUtil.simpleUUID() + "." + FileUtil.extName(file.getOriginalFilename());
        /**
         * 保存路径
         */
		String saveurl = fileConfig.getSaveurl();
		File saveFile = new File(saveurl, fileName);
        /**
         * 保存文件到服务器
         */
		FileUtils.copyToFile(file.getInputStream(),saveFile);
        /**
         * 网络访问路径 返回
         */
		String url = fileConfig.getBaseurl() + fileName;

		return R.data(url);
    }

    @RequestMapping(value="/file/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
