package rocks.byivo.processmanager.utils;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomMatchers {

    private CustomMatchers() {
    }
    
    public static final Matcher<Date> between(Date startDate, Date endindDate) {

   	return new Matcher<Date>() {

   	    @Override
   	    public void describeTo(Description description) {
   	    }

   	    @Override
   	    public boolean matches(Object item) {
   		Date actual = (Date) item;
   		return actual.compareTo(startDate) >= 0 && actual.compareTo(endindDate) <= 0;
   	    }

   	    @Override
   	    public void describeMismatch(Object item, Description mismatchDescription) {
   	    }

   	    @Override
   	    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
   	    }

   	};
       }
}
