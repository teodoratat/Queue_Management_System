package Application;

import Models.Operations;
import Models.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestForSubtraction {

        @ParameterizedTest
        @MethodSource("provideInput")
        public void subtractionTest(Polynomial polynomial1, Polynomial polynomial2, String expectedResult){
            assertEquals(expectedResult, Operations.subtraction(polynomial1, polynomial2).toString());
        }
        private static List<Arguments> provideInput() {
            List <Arguments> argumentsList = new ArrayList<>();
            Polynomial polynomial1 = new Polynomial("2+4X^2+X^7-14");
            polynomial1.isPolynomial("2+4X^2+X^7-14");
            Polynomial polynomial2 = new Polynomial("-12+X");
            polynomial2.isPolynomial("-12+X");
            String result1 = "X^7+4X^2-X";
            argumentsList.add(Arguments.of(polynomial1, polynomial2,result1));

            Polynomial polynomial3 = new Polynomial("13X^4+8X^3-4X+5");
            polynomial3.isPolynomial("13X^4+8X^3-4X+5");
            Polynomial polynomial4 = new Polynomial("13X^4+8X^3-4X+5");
            polynomial4.isPolynomial("13X^4+8X^3-4X+5");
            String result2 = "0";
            argumentsList.add(Arguments.of(polynomial3, polynomial4,result2));

            Polynomial polynomial5 = new Polynomial("3X+10");
            polynomial5.isPolynomial("3X+10");
            Polynomial polynomial6 = new Polynomial("X^4-2X^9");
            polynomial6.isPolynomial("X^4-2X^9");
            String  result3 = "2X^9-X^4+3X+10";
            argumentsList.add(Arguments.of(polynomial5, polynomial6,result3));

            Polynomial polynomial7 = new Polynomial("X^2+2X");
            polynomial7.isPolynomial("X^2+2X");
            Polynomial polynomial8 = new Polynomial("X^2-1");
            polynomial8.isPolynomial("X^2-1");
            String  result4 = "2X+1";
            argumentsList.add(Arguments.of(polynomial7, polynomial8,result4));

            return argumentsList;
        }
    }

