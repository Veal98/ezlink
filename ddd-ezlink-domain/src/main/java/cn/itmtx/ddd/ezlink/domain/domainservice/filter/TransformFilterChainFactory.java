package cn.itmtx.ddd.ezlink.domain.domainservice.filter;

import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.custom.BaseNamingTransformFilter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
public class TransformFilterChainFactory implements BeanFactoryAware {

    private ListableBeanFactory listableBeanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.listableBeanFactory = (ListableBeanFactory) beanFactory;
    }

    /**
     * 构造过滤器链
     * @param context
     * @return
     */
    public TransformFilterChain buildTransformFilterChain(TransformContext context) {
        DefaultTransformFilterChain filterChain = new DefaultTransformFilterChain();

        // 获取 TransformFilter 的实现类
        Map<String, TransformFilter> filters = listableBeanFactory.getBeansOfType(TransformFilter.class);
        List<TransformFilterInstance> transformFilterInstances = new ArrayList<>();
        filters.forEach((k, v) -> transformFilterInstances.add(new TransformFilterInstance(v, v.order(), k)));
        // 排序过滤器，order 小的在前
        transformFilterInstances.sort(Comparator.comparingInt(TransformFilterInstance::getOrder));

        // 用 BaseNamingTransformFilter 封装过滤器，添加 name 并初始化
        transformFilterInstances.forEach(instance -> {
            TransformFilter filter = instance.getFilter();
            BaseNamingTransformFilter baseNamingTransformFilter = new BaseNamingTransformFilter(filter) {
                @Override
                public String filterName() {
                    return instance.getFilterName();
                }
            };
            // 往 filterChain 中添加封装好的过滤器
            filterChain.addTransformFilter(baseNamingTransformFilter);
            baseNamingTransformFilter.init(context);

        });
        return filterChain;
    }

    /**
     * 转换过滤器实例
     */
    private static class TransformFilterInstance {

        private final TransformFilter filter;
        private final int order;
        private final String filterName;

        public TransformFilterInstance(TransformFilter filter, int order, String filterName) {
            this.filter = filter;
            this.order = order;
            this.filterName = filterName;
        }

        public TransformFilter getFilter() {
            return filter;
        }

        public int getOrder() {
            return order;
        }

        public String getFilterName() {
            return filterName;
        }
    }
}
