package com.atguigu.tingshu.user.client;

import com.atguigu.tingshu.user.client.impl.VipServiceConfigDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 产品列表API接口
 * </p>
 *
 * @author qy
 */
@FeignClient(value = "service-user", fallback = VipServiceConfigDegradeFeignClient.class)
public interface VipServiceConfigFeignClient {


}