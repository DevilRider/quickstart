package org.liang.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 随机码生成器 .
 * 
 * @author H.Shen
 * @version v1.0 2017年2月28日  下午4:52:40
 */
public class CodeGenerator {
    
    /** 随机码类型 枚举. */
    enum Type {
        /** 数字. */
        NUMBER,
        /** 大写字母. */
        UPPERCASE,
        /** 小写字母. */
        LOWERCASE
    }
    /** 大写字母池. */
    private static final List<String> UPPER_CASE_POOL = new ArrayList<String>(Arrays.asList("A",
                    "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                    "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

    /** 小写字母池. */
    private static final List<String> LOWER_CASE_POOL = new ArrayList<String>(Arrays.asList("a",
                    "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
                    "r", "s", "t", "u", "v", "w", "x", "y", "z"));
    
    /** 数字池. */
    private static final List<String> NUMBER_POOL = new ArrayList<String>(Arrays.asList("0", "1",
                    "2", "3", "4", "5", "6", "7", "8", "9"));
    
    /** 默认数量. */
    private static final int DEF_COUNT = 1;
    
    /** 长度. */
    private int length;
    
    /** 数量. */
    private int count;
    
    /** 单个随机码结果. */
    private StringBuilder randomcode = new StringBuilder();
    
    /** 随机对象. */
    private Random random = new Random();
    
    /** 随机码类型集合. */
    private Set<Type> types = new HashSet<Type>();
    
    /**
     * 构造方法.
     * 
     * @param type
     *              随机码类型.
     * @param length
     *              单个随机码长度.
     * @param count
     *              随机码数量.
     */
    private CodeGenerator(Type type, int length, int count) {
        this.length = length;
        this.count = count;
        types.add(type);
    }

    /**
     * 构造随机码生成器对象.
     * 
     * <pre>
     * 默认串数量为1.
     * </pre>
     * 
     * @param type
     *              随机码类型.
     * @param length
     *              单个随机码长度.
     * @return
     *              随机码生成器对象.
     */
    public static CodeGenerator build(Type type, int length) {
        return new CodeGenerator(type, length, DEF_COUNT);
    }
    
    /**
     * 构造随机码生成器对象.
     * 
     * @param type
     *              随机码类型.
     * @param length
     *              单个随机码长度.
     * @param count
     *              随机码数量.
     * @return
     *              随机码生成器对象.
     */
    public static CodeGenerator build(Type type, int length, int count) {
        return new CodeGenerator(type, length, count);
    }
    
    /** 使用大写字母. */
    public CodeGenerator uppercase() {
        types.add(Type.UPPERCASE);
        return this;
    }
    
    /** 使用小写字母. */
    public CodeGenerator lowercase() {
        types.add(Type.LOWERCASE);
        return this;
    }
    
    /** 使用数字. */
    public CodeGenerator number() {
        types.add(Type.NUMBER);
        return this;
    }
    
    /**  生成随机码. */
    public List<String> exec() {
        List<String> dataPool = getDataPool();
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            result.add(getOneRandomCode(length, dataPool));
        }
        return result;
    }

    /** 获取一个随机码. */
    private String getOneRandomCode(int length, List<String> dataPool) {
        randomcode.setLength(0);
        random.setSeed((int)(Math.ceil(Math.random() * 100000)));
        int dataPoolIndex;
        int counts = 0;
        while (counts < length) {
            dataPoolIndex = Math.abs(random.nextInt(dataPool.size()));
            if (dataPoolIndex >= 0 && dataPoolIndex < dataPool.size()) {
                randomcode.append(dataPool.get(dataPoolIndex));
                counts++;
            }
        }
        return randomcode.toString();
    }
    
    /** 获取数据池. */
    private List<String> getDataPool() {
        List<String> dataPool = new ArrayList<String>();
        for (Type type : types) {
            switch (type) {
                case UPPERCASE:
                    dataPool.addAll(UPPER_CASE_POOL);
                    break;
                case LOWERCASE:
                    dataPool.addAll(LOWER_CASE_POOL);
                    break;
                case NUMBER:
                    dataPool.addAll(NUMBER_POOL);
                    break;
                default:
                    throw new IllegalArgumentException("参数错误");
            }
        }
        return dataPool;
    }
}
