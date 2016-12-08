package com.github.knightliao.canalx.plugin.router.rest.filter.log;

import java.io.PrintWriter;

/**
 * 扩展PrintWriter,实现类似linux tee命令的多输出流输出功能
 */
public class TeePrintWriter extends PrintWriter {
    /**
     * 分支PrintWriter
     */
    PrintWriter branch;

    /**
     * 构造方法
     *
     * @param main   主PrintWriter
     * @param branch 分支PrintWriter
     */
    public TeePrintWriter(PrintWriter main, PrintWriter branch) {
        super(main, true);
        this.branch = branch;
    }

    public void write(char[] buf, int off, int len) {
        super.write(buf, off, len);
        super.flush();
        branch.write(buf, off, len);
        branch.flush();
    }

    public void write(String s, int off, int len) {
        super.write(s, off, len);
        super.flush();
        branch.write(s, off, len);
        branch.flush();
    }

    public void write(int c) {
        super.write(c);
        super.flush();
        branch.write(c);
        branch.flush();
    }

    public void flush() {
        super.flush();
        branch.flush();
    }
}
