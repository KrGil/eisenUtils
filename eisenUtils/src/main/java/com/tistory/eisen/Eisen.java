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
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static void printCalssName(String packagePath) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println((String) Class.forName(packagePath).newInstance());
//        ((test123)Class.forName("com.tistory.eisen.test123").newInstance()).test();  // 인스턴스화 방법.
    }

    public static void printArray(int[] arr) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
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
     * @Name: Annotation 기반 유효성 검증
     * @Desc: @NotNull, @NotEmpty 와 같은 Annotation 기반 유효성 검증을 수행하고 위반사항을 리턴한다.
     * @Note: private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();를
     *      static으로 주어 한번만 호출해도 되게끔. service에 들어갈때마다 굳이 생성해줄 필요가 없음.
     * @Sample:
     *      private <T> void validate(T input){
     *          Set<ConstraintViolation<T>> violations = Eisen.validate(input);
     *          if(violations.size() > 0){
     *              String errMsg = Eiseon.collectViolationMessages(violations);
     *              throw new CustomException(errMsg);
     *          }
     *      }
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> Set<ConstraintViolation<T>> validate(T obj){
        return validator.validate(obj);
    }

    /**
     * @Name: violations Set을 이용해 에러 메시지 생성
     * @Sample:
     *      private <T> void validate(T input){
     *          Set<ConstraintViolation<T>> violations = Eisen.validate(input);
     *          if(violations.size() > 0){
     *              String errMsg = Eiseon.collectViolationMessages(violations);
     *              throw new CustomException(errMsg);
     *          }
     *      }
     * @param violations Set<constraintViolation<T>
     * @return String
     */
    public static <T> String collectViolationMessages(Set<ConstraintViolation<T>> violations){
        return  violations.stream()
            .map(e -> String.format("%s : %s", e.getPropertyPath(), e.getMessage()))
            .reduce((msg1, msg2) -> msg1 + "\n" + msg2)
            .orElse("");
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
