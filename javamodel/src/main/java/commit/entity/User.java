package commit.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/16 16:02
 * @Version V1.0
 */
@Data
@NoArgsConstructor
public class User {
    private String name;
    private Integer age;
    private Date birthday;
    private double hight;
    public User(String name, Integer age, Date birthday, double hight) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.hight = hight;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", hight=" + hight +
                '}';
    }
}
