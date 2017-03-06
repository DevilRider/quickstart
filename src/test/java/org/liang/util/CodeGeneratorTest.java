package org.liang.util;


import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.liang.util.CodeGenerator.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 随机串 测试类.
 * 
 * @author H.Shen
 * @version v1.0 2017年3月2日  上午11:07:10
 */
public class CodeGeneratorTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static Judgement judgement = new Judgement();
    /** 长度. */
    private static int length;
    /** 数量. */
    private static int count; 
    /** 重复次数. */
    private static int repeattimes;
    /** 标识. */
    private static boolean flag;
    
    private static final String TEST_LENGTH = "长度测试";
    
    private static final String TEST_COUNT = "数量测试";
    
    private static final String TEST_TYPE = "类型测试";
    
    private static final String TEST_REPEATTIME = "重复测试10000次,重复数小于10)";
    
    /**
     * 测试 参数为长度、类型.
     * 
     * <pre>
     * 测试内容:长度、数量、类型.
     * </pre>
     */
    @Test
    public void testWithLength() {
        length = 10;
        count = 1;
        flag = false;
        List<String> result = CodeGenerator.build(Type.LOWERCASE, length).number()
                        .uppercase().exec();
        for (String data : result) {
            //长度
            Assert.assertTrue(TEST_LENGTH, length == data.length());
            //类型
            flag = judgement.isContainsAll(data);
        }
        Assert.assertTrue(TEST_TYPE, flag);
        //数量
        Assert.assertTrue(TEST_COUNT, count == result.size());
    }
    
    /**
     * 测试 参数为长度、数量、类型.
     * 
     * <pre>
     * 测试内容:长度、数量、类型.
     * </pre>
     */
    @Test
    public void testWithLengthAndCount() {
        length = 5;
        count = 10000;
        flag = false;
        repeattimes = 10;
        List<String> result = CodeGenerator.build(Type.LOWERCASE, length, count).exec();
        for (String data : result) {
            //长度
            Assert.assertTrue(TEST_LENGTH, length == data.length());
            //类型
            flag = judgement.isContainsLowerOnly(data);
        }
        Assert.assertTrue(TEST_TYPE, flag);
        //数量
        Assert.assertTrue(TEST_COUNT, result.size() == count);
        //重复率
        for (String random : result) {
            Assert.assertTrue(TEST_REPEATTIME, Collections.frequency(result, random) < repeattimes);
        }
        
    }
    
    /** 内部类,用于判断随机串的类型. */
    static class Judgement {
        /** 是否大写. */
        private boolean isUpper;
        /** 是否小写. */
        private boolean isLower;
        /** 是否数字. */
        private boolean isNum;
        
        /** 是否包含所有类型. */
        boolean isContainsAll(String data) {
            this.init().judge(data);
            if (this.isNum && this.isUpper && this.isLower) {
                return true;
            }
            return false;
        }
        
        /** 是否只包含小写字母. */
        boolean isContainsLowerOnly(String data) {
            this.init().judge(data);
            if (!this.isNum && !this.isUpper && this.isLower) {
                return true;
            }
            return false;
        }
        
        /** 判断类型. */
        private void judge(String data) {
            for (int i = 0; i < data.length(); i++) {
                char ch = data.charAt(i);
                if (48 <= ch && ch <= 57) {
                    this.isNum = true;
                } else if (65 <= ch && ch <= 90) {
                    this.isUpper = true;
                } else if (97 <= ch && ch <= 122) {
                    this.isLower = true;
                }
            }
        }
        
        /** 初始化. */
        private Judgement init() {
            this.isLower = false;
            this.isNum = false;
            this.isUpper = false;
            return this;
        }
    }
    
}
