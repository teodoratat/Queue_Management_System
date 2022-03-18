package Application;
import Models.Operations;
import Models.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestForDerivative {
    @ParameterizedTest
    @MethodSource("provideInput")
    public void derivativeTest(Polynomial polynomial1, String expectedResult){
        assertEquals(expectedResult, Operations.derivative(polynomial1).toString());
    }
    private static List<Arguments> provideInput() {
        List <Arguments> argumentsList = new ArrayList<>();
        Polynomial polynomial1 = new Polynomial("X^8+2X^5-3");
        polynomial1.isPolynomial("X^8+2X^5-3");
        String result1 = "8X^7+10X^4";

        Polynomial polynomial2 = new Polynomial("-4X^5+X^4-2X^3-1");
        polynomial2.isPolynomial("-4X^5+X^4-2X^3-1");
        String result2 = "-20X^4+4X^3-6X^2";


        Polynomial polynomial3 = new Polynomial("2X^3+14+6X^3-X+5");
        polynomial3.isPolynomial("2X^3+14+6X^3-X+5");
        String result3 = "24X^2-1";

        Polynomial polynomial4 = new Polynomial("-X+5");
        polynomial4.isPolynomial("-X+5");
        String  result4 = "-1";

        Polynomial polynomial5 = new Polynomial("23");
        polynomial5.isPolynomial("23");
        String result5 ="0";

        Polynomial polynomial6 = new Polynomial("14X");
        polynomial6.isPolynomial("14X");
        String result6 ="14";

        argumentsList.add(Arguments.of(polynomial1,result1));
        argumentsList.add(Arguments.of(polynomial2, result2));
        argumentsList.add(Arguments.of(polynomial3, result3));
        argumentsList.add(Arguments.of(polynomial4, result4));
        argumentsList.add(Arguments.of(polynomial5, result5));
        argumentsList.add(Arguments.of(polynomial6, result6));

        return argumentsList;
    }
}
