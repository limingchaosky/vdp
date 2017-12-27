package cn.goldencis.vdp.core.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.goldencis.vdp.core.constants.ConstantsDto;
import cn.goldencis.vdp.core.utils.AuthUtils;
import cn.goldencis.vdp.core.utils.PathConfig;

@Controller
@RequestMapping("/about")
public class AboutController implements ServletContextAware {
    private ServletContext servletContext;

    public void setServletContext(ServletContext sc) {
        this.servletContext = sc;
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/about/index");
        model.addObject("authInfo", AuthUtils.getAuthInfo(servletContext));
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/fileupload", produces = "application/json", method = RequestMethod.POST)
    public Map<String, Object> fileupload(@RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("resultCode", ConstantsDto.CONST_FALSE);
        String fileUploadPath = PathConfig.HOM_PATH;
        String fileName = file.getOriginalFilename();

        if (!fileName.endsWith(".oms")) {
            resultMap.put("resultMsg", "授权文件无效");
            return resultMap;
        }
        //校验授权文件是否有效
        try {
            Map<String, Object> result = AuthUtils.checkFileIsValidate(servletContext, file.getInputStream());
            if (result.get("authmsg") != null && !"".equals(result.get("authmsg").toString())) {
                resultMap.put("resultMsg", result.get("authmsg").toString());
                return resultMap;
            }
        } catch (IOException e1) {
            //校验抛出异常也不允许提交
            resultMap.put("resultMsg", "授权文件无效");
            return resultMap;
        }
        fileUploadPath = fileUploadPath.replace('\\', '/');
        if (!fileUploadPath.endsWith("/")) {
            fileUploadPath += "/";
        }
        File tmp = new File(fileUploadPath);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        /**
         * 文件存在则删除文件
         */
        File authFile = new File(fileUploadPath + ConstantsDto.AUTH_FILE_NAME);
        if (authFile.exists()) {
            authFile.delete();
        }

        InputStream inStream = null;
        FileOutputStream fos = null;
        try {
            // 用数据流的方式二次读取和保存数据保证上传的缓存效果和安全性
            if (file != null) {
                inStream = file.getInputStream();
                fos = new FileOutputStream(fileUploadPath + ConstantsDto.AUTH_FILE_NAME);
                byte[] buffer = new byte[1024];
                int byteread = 0;
                while ((byteread = inStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, byteread);
                }
                resultMap.put("resultCode", ConstantsDto.CONST_TRUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultMsg", "上传文件失败");
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return resultMap;
    }

    /**
     * 下载模板
     * @param request
     * @param response
     * @param fileName
     */
    @ResponseBody
    @RequestMapping(value = "/fileload", produces = "application/json", method = RequestMethod.GET)
    public void fileload(HttpServletRequest request, HttpServletResponse response) {
        File file = new File(PathConfig.HOM_PATH, ConstantsDto.AUTH_FILE_NAME);
        if (file.exists()) {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + ConstantsDto.EXPORT_AUTH_FILE_NAME);// 设置文件名
                byte[] buffer = new byte[1024];

                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 下载模板
     * @param request
     * @param response
     * @param fileName
     */
    @ResponseBody
    @RequestMapping(value = "/isFileExist", produces = "application/json", method = RequestMethod.POST)
    public String isFileExist(HttpServletRequest request, HttpServletResponse response) {
        String fileUrl = PathConfig.HOM_PATH;
        File file = new File(fileUrl, ConstantsDto.AUTH_FILE_NAME);
        String result = "";
        if (file.exists()) {
            result = "success";
        } else {
            result = "failure";
        }
        return result;
    }
}
