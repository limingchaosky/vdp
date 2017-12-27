package cn.goldencis.vdp.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author mll 2017年6月28日16:14:46
 */
public class MultipartResolver extends CommonsMultipartResolver {

    //private final static Logger logger = LoggerFactory.getLogger(MyMultipartResolver.class);

    @Override
    public boolean isMultipart(HttpServletRequest request) {

        String url = request.getRequestURI();
        if (url.indexOf("/ueditor/config") > 0) {
            return false;
        }
        return super.isMultipart(request);
    }
}
