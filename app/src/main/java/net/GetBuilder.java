package net;

import android.net.Uri;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * get请求
 */
public class GetBuilder
        extends OkHttpRequestBuilder<GetBuilder> {
    @Override
    public RequestCall build() {
        if (params != null) {
            url = appendParams(url, params);
        }

        return new GetRequest(url, tag, params, headers, id).build();
    }

    protected String appendParams(String url,
                                  Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url)
                                 .buildUpon();
        Set<String>      keys     = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build()
                      .toString();
    }

}
