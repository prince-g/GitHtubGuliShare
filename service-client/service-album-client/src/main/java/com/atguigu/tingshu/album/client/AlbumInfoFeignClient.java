package com.atguigu.tingshu.album.client;

import com.atguigu.tingshu.album.client.impl.AlbumInfoDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 产品列表API接口
 * </p>
 *
 * @author qy
 */
@FeignClient(value = "service-album", fallback = AlbumInfoDegradeFeignClient.class)
public interface AlbumInfoFeignClient {

}