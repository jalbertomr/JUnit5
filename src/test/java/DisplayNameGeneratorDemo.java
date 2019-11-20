import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Method;

public class DisplayNameGeneratorDemo {
    @Nested
    @DisplayNameGeneration(IndicativeSentences.ReplaceUnderscores.class)
    class A_year_is_not_supported {
        @Test
        void if_it_is_zero(){ /* validation code here*/ }

        @DisplayName("Un valor negativo para el año no es soportado por el computo de año bisiesto.")
        @ParameterizedTest(name = "Ejemplo, año {0} no es soportado.")
        @ValueSource( ints = { -1, -4})
        void if_it_is_negative(int year){  /* validation code here*/ }
    }

    @Nested
    @DisplayNameGeneration(IndicativeSentences.ReplaceUnderscores.class)
    class A_year_is_a_leap_year {
        @Test
        void if_it_is_divisible_by_4_but_not_by_100(){ /* validation code here*/ }

        @DisplayName("....")
        @ParameterizedTest(name = "Año {0} es año bisiesto.")
        @ValueSource( ints = { 2016, 2020, 2048})
        void  if_it_is_one_of_the_following_years(int year){ /* validation code here*/ }
    }


    static class IndicativeSentences extends DisplayNameGenerator.ReplaceUnderscores{
        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return super.generateDisplayNameForClass(testClass);
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return super.generateDisplayNameForNestedClass(nestedClass) + "...";
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            String name = testClass.getSimpleName() + ' ' + testMethod.getName();
            return name.replace('_',' ') + '.';

        }

    }
}
