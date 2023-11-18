package cn.itmtx.ddd.ezlink.adapter.rpc;

import cn.itmtx.ddd.ezlink.adapter.constant.RPC;
import cn.itmtx.ddd.ezlink.application.service.UrlMapServiceImpl;
import cn.itmtx.ddd.ezlink.client.api.UrlMapService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 注册 RPC 接口
 */
@Slf4j
@DubboService(timeout = RPC.DEFAULT_TIMEOUT)
public class UrlMapProvider extends UrlMapServiceImpl implements UrlMapService {


}
