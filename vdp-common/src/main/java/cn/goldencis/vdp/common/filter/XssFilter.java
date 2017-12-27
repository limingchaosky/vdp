package cn.goldencis.vdp.common.filter;

import cn.goldencis.vdp.common.utils.StringUtil;
import cn.goldencis.vdp.common.wrapper.XssHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author mll
 * 2017年6月30日20:33:26
 */
public class XssFilter implements Filter {

    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
        String uri = request.getRequestURI();
        if (StringUtil.isEmpty(uri))
            uri = "";
        if (uri.contains("/oms/knowledge/") || uri.contains("/workOrder/pending/evaluateWorkOrder")
                || uri.contains("/workOrder/submitWorkOrder/addWorkOrder")
                || uri.contains("/workOrder/submitWorkOrder/getWorkOrderInfo")) {
            chain.doFilter(request, servletResponse);
        } else {
            chain.doFilter(new XssHttpServletRequestWrapper(request), servletResponse);
        }
    }
}
