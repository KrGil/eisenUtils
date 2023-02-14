package com.tistory.eisen;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class CustomValid {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
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
    public <T> Set<ConstraintViolation<T>> validate(T obj){
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
    public <T> String collectViolationMessages(Set<ConstraintViolation<T>> violations){
        return  violations.stream()
                .map(e -> String.format("%s : %s", e.getPropertyPath(), e.getMessage()))
                .reduce((msg1, msg2) -> msg1 + "\n" + msg2)
                .orElse("");
    }
}
