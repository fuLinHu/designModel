package com.study.javamodel.javadesignmodel.bulider;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/17 15:21
 * @Version V1.0
 */
public class Person {
    private int tall;
    private String name;
    private int age;
    private Adress adress;
    private char sex;

    private Person() {
    }

    public static class PersionBulider{
        private Person person = new Person();

        public PersionBulider baseInfro(String name,int age){
            person.age = age;
            person.name = name;
            return this;
        }

        public PersionBulider tall(int tall) {
            person.tall = tall;
            return this;
        }
        public PersionBulider sex(char sex) {
            person.sex = sex;
            return this;
        }

        public PersionBulider adress(String pro,String chin){
            person.adress = new Adress(pro,chin);
            return this;
        }

        public Person bulider(){
            return person;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "tall=" + tall +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", adress=" + adress +
                ", sex=" + sex +
                '}';
    }
}

class Adress{
    private String pro;
    private String chin;

    public Adress(String pro, String chin) {
        this.pro = pro;
        this.chin = chin;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "pro='" + pro + '\'' +
                ", chin='" + chin + '\'' +
                '}';
    }
}
