package cn.goldencis.vdp.common.filter;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author mll
 */
@Component("csrfTokenFilter")
public class CsrfTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (csrf != null) {
            Cookie cookie1 = WebUtils.getCookie(request, "XSRF-TOKEN");
            Cookie cookie2 = WebUtils.getCookie(request, "XSRF-HEADER-NAME");

            String token = csrf.getToken();
            String headerName = csrf.getHeaderName();

            if (cookie1 == null || cookie2 == null || token != null && !token.equals(cookie1.getValue())) {
                cookie1 = new Cookie("XSRF-TOKEN", token);
                cookie2 = new Cookie("XSRF-HEADER-NAME", headerName);

                cookie1.setPath("/");
                cookie2.setPath("/");

                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
        }

        filterChain.doFilter(request, response);
    }
}