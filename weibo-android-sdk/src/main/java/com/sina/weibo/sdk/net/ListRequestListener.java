package com.sina.weibo.sdk.net;

import com.sina.weibo.sdk.exception.WeiboException;
import java.util.List;

public interface ListRequestListener<T> {
    public void onComplete(List<T> list);
    public void onWeiboException(WeiboException e);
}
