package cn.itmtx.ddd.ezlink.domain.domainservice.filter;

import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;

/**
 * 过滤器链
 */
public interface TransformFilterChain {

    void doFilter(TransformContext context);

    void release();
}
