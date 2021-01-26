package com.elk.demo.config.esconfig;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/20 16:40
 * @Version V1.0
 */
@Configuration
public class RestHighLevelConfig {

    @Value("${es.hosts}")
    private String hosts;
    @Value("${es.port}")
    private Integer port;
    @Value("${es.scheme}")
    private String scheme;
    @Value("${es.password}")
    private String password;
    @Value("${es.userName}")
    private String userName;




    @Bean
    public RestHighLevelClient restHighLevelClient(){
        String[] hosts = this.hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for(int i=0;i<hosts.length;i++) {
            httpHosts[i] = new HttpHost(hosts[i].trim(), port, scheme);
        }
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));

        RestClientBuilder builder = RestClient.builder(httpHosts).setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(-1);
                requestConfigBuilder.setSocketTimeout(-1);
                requestConfigBuilder.setConnectionRequestTimeout(-1);
                return requestConfigBuilder;
            }
        }).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.disableAuthCaching();
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        });
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }
}
