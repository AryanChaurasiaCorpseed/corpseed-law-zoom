package com.lawzoom.companyservice.annotations;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BeforeTodayValidator implements ConstraintValidator<BeforeToday, Date> {

	@Override
	public boolean isValid(Date value, final ConstraintValidatorContext context) {
        Calendar c = Calendar.getInstance();
        c.setTime(value);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        value = c.getTime();

		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date today = calendar.getTime();
        
        return value.before(today)|| value.equals(today);
	}

}
