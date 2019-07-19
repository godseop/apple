package org.godseop.apple.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class AccessLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // DO NOTHING
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String remoteAddress = StringUtils.defaultString(request.getRemoteAddr(), "-");
        String url = (request.getRequestURL() == null) ? "" : request.getRequestURL().toString();
        String queryString = StringUtils.defaultIfEmpty(request.getQueryString(), "");
        String refer = StringUtils.defaultString(request.getHeader("Refer"), "-");
        String agent = StringUtils.defaultString(request.getHeader("User-Agent"), "-");
        String fullUrl = url + (StringUtils.isNotEmpty(queryString) ? "?" + queryString : queryString);

        log.info("Access Log : {}:{}:{}:{}", remoteAddress, fullUrl, refer, agent);

        long startDate = System.currentTimeMillis();
        filterChain.doFilter(request, servletResponse);
        long endDate = System.currentTimeMillis();

        log.info("Access Time : {}초 -> {}", (double) (endDate - startDate) / 1000, request.getRequestURI());
    }

    @Override
    public void destroy() {
        // DO NOTHING
    }
}
