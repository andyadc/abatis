package com.andyadc.abatis.test;

import com.andyadc.abatis.reflection.MetaObject;
import com.andyadc.abatis.reflection.SystemMetaObject;
import com.andyadc.abatis.util.Utils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ReflectionTests {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTests.class);

    @Test
    public void test_Reflection() {
        Teacher teacher = new Teacher();
        List<Teacher.Student> list = new ArrayList<>();
        list.add(new Teacher.Student());
        teacher.setName("小傅哥");
        teacher.setStudents(list);

        MetaObject metaObject = SystemMetaObject.forObject(teacher);

        logger.info("getGetterNames：{}", Utils.toJSONString(metaObject.getGetterNames()));
        logger.info("getSetterNames：{}", Utils.toJSONString(metaObject.getSetterNames()));
        logger.info("name的get方法返回值：{}", Utils.toJSONString(metaObject.getGetterType("name")));
        logger.info("students的set方法参数值：{}", Utils.toJSONString(metaObject.getGetterType("students")));
        logger.info("name的hasGetter：{}", metaObject.hasGetter("name"));
        logger.info("student.id（属性为对象）的hasGetter：{}", metaObject.hasGetter("student.id"));
        logger.info("获取name的属性值：{}", metaObject.getValue("name"));
        // 重新设置属性值
        metaObject.setValue("name", "小白");
        logger.info("设置name的属性值：{}", metaObject.getValue("name"));
        // 设置属性（集合）的元素值
        metaObject.setValue("students[0].id", "001");
        logger.info("获取students集合的第一个元素的属性值：{}", Utils.toJSONString(metaObject.getValue("students[0].id")));
        logger.info("对象的序列化：{}", Utils.toJSONString(teacher));
    }

    static class Teacher {

        private String name;

        private double price;

        private List<Student> students;

        private Student student;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public static class Student {

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
