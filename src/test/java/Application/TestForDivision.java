package Application;


import Models.Operations;
import Models.Polynomial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestForDivision {
    @ParameterizedTest
    @MethodSource("provideInput")
    public void divideTest(Polynomial polynomial1, Polynomial polynomial2, String expectedResult) {
      //  Polynomial r=new Polynomial();
        assertEquals(expectedResult, Operations.divide(polynomial1, polynomial2).toString());
    }

    private static List<Arguments> provideInput() {
        List <Arguments> argumentsList = new ArrayList<>();

        Polynomial polynomial1 = new Polynomial("X^6+2X^4+6X-9");
        polynomial1.isPolynomial("X^6+2X^4+6X-9");
        Polynomial polynomial2 = new Polynomial("X^3+3");
        polynomial2.isPolynomial("X^3+3");
        String result1 = "X^3+2X-3";
        argumentsList.add(Arguments.of(polynomial1, polynomial2,result1));

        Polynomial polynomial3 = new Polynomial("2X^3+3X^2-X+5");
        polynomial3.isPolynomial("2X^3+3X^2-X+5");
        Polynomial polynomial4 = new Polynomial("1-X+X^2");
        polynomial4.isPolynomial("1-X+X^2");
        String result2 = "2X+5";
        argumentsList.add(Arguments.of(polynomial3, polynomial4,result2));

        Polynomial polynomial5 = new Polynomial("X^2+5");
        polynomial5.isPolynomial("X^2+5");
        Polynomial polynomial6 = new Polynomial("3X^4+X^3+2X");
        polynomial6.isPolynomial("3X^4+X^3+2X");
        String  result3 = "0";
        argumentsList.add(Arguments.of(polynomial5, polynomial6,result3));

        Polynomial polynomial7 = new Polynomial("X^2+7X^3+1");
        polynomial7.isPolynomial("X^2+7X^3+1");
        Polynomial polynomial8 = new Polynomial("X^2+7X^3+1");
        polynomial8.isPolynomial("X^2+7X^3+1");
        String  result4 = "1";
        argumentsList.add(Arguments.of(polynomial7, polynomial8,result4));
        return argumentsList;
    }
}