package cn.itmtx.ddd.ezlink.domain.domainservice.filter;


import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;

/**
 * 过滤器
 */
public interface TransformFilter {

    /**
     * 过滤器执行顺序
     * @return
     */
    default int order() {
        return 1;
    }

    /**
     * 初始化方法
     * @param context
     */
    default void init(TransformContext context) {

    }

    /**
     * 执行过滤
     * @param chain
     * @param context
     */
    void doFilter(TransformFilterChain chain, TransformContext context);
}
