package com.github.knightliao.canalx.plugin.router.rest.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.plugin.router.rest.filter.log.RequestWrapper;
import com.github.knightliao.canalx.plugin.router.rest.filter.log.ResponseWrapper;

/**
 * @author knightliao
 * @date 2016/12/7 16:01
 */
public class RequestFilter implements Filter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    /**
     * 按照指定格式记录请求
     *
     * @param request
     */
    protected void logRequest(final RequestWrapper request) {
        StringBuilder msg = new StringBuilder();
        msg.append("request: ");

        if (request.getContentType() != null) {
            msg.append("content type=").append(request.getContentType()).append("; ");
        }
        msg.append("uri=").append(request.getMethod()).append(":").append(request.getRequestURI());
        if (request.getQueryString() != null) {
            msg.append('?').append(request.getQueryString());
        }

        if (request instanceof RequestWrapper && !isMultipart(request)) {
            RequestWrapper requestWrapper = (RequestWrapper) request;
            try {
                String charEncoding =
                        requestWrapper.getCharacterEncoding() != null ? requestWrapper.getCharacterEncoding() : "UTF-8";
                msg.append("; payload=").append(new String(requestWrapper.toByteArray(), charEncoding));
            } catch (UnsupportedEncodingException e) {
                LOGGER.warn("Failed to parse request payload", e);
            }

        }
        LOGGER.debug(msg.toString());
    }

    /**
     * 按照指定格式记录响应
     *
     * @param response
     */
    protected void logResponse(final ResponseWrapper response) {
        StringBuilder msg = new StringBuilder();
        msg.append("response: ");
        try {
            msg.append("STATUS CODE:").append(response.getStatus());
            msg.append("; payload=").append(new String(response.toByteArray(), response.getCharacterEncoding()));
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("Failed to parse response payload", e);
        }
        LOGGER.debug(msg.toString());
    }

    /**
     * 是否是上传文件
     *
     * @param request
     *
     * @return
     */
    private boolean isMultipart(final HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 包装请求和响应
        if (LOGGER.isDebugEnabled()) {
            request = new RequestWrapper((HttpServletRequest) request);
            response = new ResponseWrapper((HttpServletResponse) response);
        }
        try {

            chain.doFilter(request, response);

        } finally {
            if (LOGGER.isDebugEnabled()) {
                // 进行打印
                logRequest((RequestWrapper) request);
                logResponse((ResponseWrapper) response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
