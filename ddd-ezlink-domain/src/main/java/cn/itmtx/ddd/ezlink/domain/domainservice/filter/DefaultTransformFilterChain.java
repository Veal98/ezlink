package cn.itmtx.ddd.ezlink.domain.domainservice.filter;

import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;
import org.springframework.stereotype.Component;

@Component
public class DefaultTransformFilterChain implements TransformFilterChain{

    // 当前过滤器在 filters 数组中的下标
    private int pos = 0;

    // filters 数组中过滤器的数量 (注意不是 filters 数组长度！)
    private int n = 0;

    private TransformFilter[] filters = new TransformFilter[0];

    @Override
    public void doFilter(TransformContext context) {
        if (this.pos < this.n) {
            TransformFilter filter = this.filters[this.pos];
            pos ++;
            filter.doFilter(this, context);
        }
    }

    @Override
    public void release() {
        for (int i = 0; i < this.n; i ++) {
            this.filters[i] = null;
        }
        this.pos = 0;
        this.n = 0;
    }

    /**
     * 往过滤器数组 TransformFilter[] filters 中添加新的过滤器
     * @param filter
     */
    void addTransformFilter(TransformFilter filter) {
        TransformFilter[] newFilters = this.filters;
        // 如果要添加的过滤器已经存在，则直接返回
        for (TransformFilter newFilter : newFilters) {
            if (newFilter == filter) {
                return ;
            }
        }

        // 扩容
        if (this.n == this.filters.length) {
            newFilters = new TransformFilter[this.n + 10];
            System.arraycopy(this.filters, 0, newFilters, 0, this.n);
            this.filters = newFilters;
        }
        this.filters[this.n] = filter;
        // filters 数组中过滤器数量 ++
        this.n++;
    }
}
