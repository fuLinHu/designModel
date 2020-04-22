package mytomcat.config;

import lombok.Data;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/22 14:29
 * @Version V1.0
 */
@Data
public class ServletConfig {
    private String name;
    private String urlMapping;
    private String clazz;

    public ServletConfig(String name, String urlMapping, String clazz) {
        this.name = name;
        this.urlMapping = urlMapping;
        this.clazz = clazz;
    }
}
