package com.github.knightliao.canalx.plugin.router.rest.filter.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.input.TeeInputStream;

/**
 * 请求包装，放置需处理请求时额外需要的信息
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            private TeeInputStream tee = new TeeInputStream(RequestWrapper.super.getInputStream(), bos);

            @Override
            public int read() throws IOException {
                return tee.read();
            }
        };
    }

    public byte[] toByteArray() {
        return bos.toByteArray();
    }

}
