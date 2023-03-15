package cn.xtong.example.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ListValueConstraintValidator implements ConstraintValidator<ListValue, String> {

    private final Set<String> set = new HashSet<>();

    /**
     * 初始化方法
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        String[] values = constraintAnnotation.values();
        set.addAll(Arrays.asList(values));
    }

    @Override
    public boolean isValid(String obj, ConstraintValidatorContext constraintValidatorContext) {
        if (obj == null || "".equals(obj)){
            return true;
        }
        return set.contains(obj);
    }
}
