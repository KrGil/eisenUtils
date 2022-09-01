package com.tistory.eisen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

public class Eisen {
    private static final Logger log = LoggerFactory.getLogger(Eisen.class);
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static void printCalssName(String packagePath) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        log.info((String) Class.forName(packagePath).newInstance());
//        ((test123)Class.forName("com.tistory.eisen.test123").newInstance()).test();  // 인스턴스화 방법.
    }

    public static void printArray(int[] arr) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println(Arrays.toString(arr));
        log.info("123");
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
            log.debug(String.format("실행시간 => %d mes", elapsedTime));
        }
    }

    /**
     * @Name: Annotation 기반 유효성 검증
     * @Desc: @NotNull, @NotEmpty 와 같은 Annotation 기반 유효성 검증을 수행하고 위반사항을 리턴한다.
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
     * @name:  violations Set을 이용해 에러 메시지 생성
     * @Sample:
     *      private <T> void validate(T input){
     *          Set<ConstraintViolation<T>> violations = Eisen.validate(input);
     *          if(violations.size() > 0){
     *              String errMsg = Eiseon.collectViolationMessages(violations);
     *              throw new CustomException(errMsg);
     *          }
     *      }
     * @param violations
     * @param <T>
     * @return
     */
    public static <T> String collectViolationMessages(Set<ConstraintViolation<T>> violations){
        return  violations.stream()
            .map(e -> String.format("%s : %s", e.getPropertyPath(), e.getMessage()))
            .reduce((msg1, msg2) -> msg1 + "\n" + msg2)
            .orElse("");
    }
}
