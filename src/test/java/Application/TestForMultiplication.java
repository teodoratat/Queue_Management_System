package Application;

import Models.Operations;
import Models.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForMultiplication {
    @ParameterizedTest
    @MethodSource("provideInput")
    public void multiplicationTest(Polynomial polynomial1, Polynomial polynomial2, String expectedResult){
        assertEquals(expectedResult, Operations.multiplication(polynomial1, polynomial2).toString());
    }
    private static List<Arguments> provideInput(){
        List <Arguments> argumentsList = new ArrayList<>();
        Polynomial polynomial1 = new Polynomial("X^3+2X^2+X+2");
        polynomial1.isPolynomial("X^3+2X^2+X+2");
        Polynomial polynomial2 = new Polynomial("7X^4-3");
        polynomial2.isPolynomial("7X^4-3");
        String result1 = "7X^7+14X^6+7X^5+14X^4-3X^3-6X^2-3X-6";
        argumentsList.add(Arguments.of(polynomial1, polynomial2,result1));

        Polynomial polynomial3 = new Polynomial("X+1");
        polynomial3.isPolynomial("X+1");
        Polynomial polynomial4 = new Polynomial("X-1");
        polynomial4.isPolynomial("X-1");
        String result2 = "X^2-1";
        argumentsList.add(Arguments.of(polynomial3, polynomial4,result2));

        Polynomial polynomial5 = new Polynomial("2X^5+3X");
        polynomial5.isPolynomial("2X^5+3X");
        Polynomial polynomial6 = new Polynomial("10");
        polynomial6.isPolynomial("10");
        String  result3 = "20X^5+30X";
        argumentsList.add(Arguments.of(polynomial5, polynomial6,result3));

        Polynomial polynomial7 = new Polynomial("X^4+2X^3+6X^2-1");
        polynomial7.isPolynomial("X^4+2X^3+6X^2-1");
        Polynomial polynomial8 = new Polynomial("0");
        polynomial8.isPolynomial("0");
        String  result4 = "0";
        argumentsList.add(Arguments.of(polynomial7, polynomial8,result4));

        return argumentsList;
    }
}