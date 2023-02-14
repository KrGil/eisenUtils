package com.tistory.eisen;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Eisen {


    public static void printCalssName(String packagePath) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println((String) Class.forName(packagePath).newInstance());
//        ((test123)Class.forName("com.tistory.eisen.test123").newInstance()).test();  // 인스턴스화 방법.
    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printArray(long[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printArray(String[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printArray(char[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printArray(double[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printArray(boolean[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printArray(float[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printDeepArray(Object[] o) {
        System.out.println(Arrays.deepToString(o));
    }

    public static <T> void logMeasuredExecutionTime(Consumer<T> func, T request){
        long startTime = System.currentTimeMillis();
        try{
            func.accept(request);
        }finally {
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println(String.format("실행시간 => %d mes", elapsedTime));
        }
    }



    /**
     * @Name: java 9 부터 구현된 takeWile 기능
     * @Desc:
     *      해당 기능은 Stream의 'lazy evaluation`기능으로 filter 의 'short circuit'
     *      이 없음을 고려하여 해당 기능을 수행 할 수 있도록 구현.
     * @param list List<A>
     * @param p Predicate<A>
     * @return list List<A>
     */
    public static <A> List<A> takeWile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if(!p.test(item)){
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
}
