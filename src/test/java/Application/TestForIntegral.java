package Application;

import Models.Operations;
import Models.Polynomial;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestForIntegral{
    @ParameterizedTest
    @MethodSource("provideInput")
    public void integrationTest(Polynomial polynomial1, String expectedResult){
        assertEquals(expectedResult, Operations.integral(polynomial1).toString());
    }
    private static List<Arguments> provideInput() {
        List <Arguments> argumentsList = new ArrayList<>();
        Polynomial polynomial1 = new Polynomial("9X^8+2X^5-3");
        polynomial1.isPolynomial("9X^8+2X^5-3");
        String result1 = "X^9+0.33X^6-3X";

        Polynomial polynomial2 = new Polynomial("-4X^5+X^4-2X^3-1");
        polynomial2.isPolynomial("-4X^5+X^4-2X^3-1");
        String result2 = "-0.67X^6+0.20X^5-0.50X^4-X";


        Polynomial polynomial3 = new Polynomial("2X^3+14+6X^3-X+5");
        polynomial3.isPolynomial("2X^3+14+6X^3-X+5");
        String result3 = "2X^4-0.50X^2+19X";

        Polynomial polynomial4 = new Polynomial("-X+5");
        polynomial4.isPolynomial("-X+5");
        String  result4 = "-0.50X^2+5X";

        Polynomial polynomial5 = new Polynomial("23");
        polynomial5.isPolynomial("23");
        String result5 ="23X";

        Polynomial polynomial6 = new Polynomial("14X+2");
        polynomial6.isPolynomial("14X+2");
        String result6 ="7X^2+2X";

        argumentsList.add(Arguments.of(polynomial1,result1));
        argumentsList.add(Arguments.of(polynomial2, result2));
        argumentsList.add(Arguments.of(polynomial3, result3));
        argumentsList.add(Arguments.of(polynomial4, result4));
        argumentsList.add(Arguments.of(polynomial5, result5));
        argumentsList.add(Arguments.of(polynomial6, result6));

        return argumentsList;
    }
}
