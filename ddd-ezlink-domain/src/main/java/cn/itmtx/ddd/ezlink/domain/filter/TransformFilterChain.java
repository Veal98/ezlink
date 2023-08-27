package cn.itmtx.ddd.ezlink.domain.filter;

import cn.itmtx.ddd.ezlink.domain.context.TransformContext;

/**
 * 过滤器链
 */
public interface TransformFilterChain {

    void doFilter(TransformContext context);

    void release();
}
