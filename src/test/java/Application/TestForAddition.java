package Application;

import Models.Operations;
import Models.Polynomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class TestForAddition {
    @ParameterizedTest
    @MethodSource("provideInput")
    public void addTest(Polynomial polynomial1, Polynomial polynomial2,Polynomial reminder, String expectedResult){
        Assertions.assertEquals(expectedResult, Operations.addition(polynomial1, polynomial2).toString());
    }
    private static List<Arguments> provideInput()  {
        List <Arguments> argumentsList = new ArrayList<>();
        Polynomial polynomial1 = new Polynomial("X^8+2X^5-6");
        polynomial1.isPolynomial("X^8+2X^5-6");
        Polynomial polynomial2 = new Polynomial("X^8+2X^5-6");
        polynomial2.isPolynomial("-3X^5-1");
        String result1 = "1 0";
        argumentsList.add(Arguments.of(polynomial1, polynomial2,result1));

        Polynomial polynomial3 = new Polynomial("2X^3+3X^2-X+5");
        polynomial3.isPolynomial("2X^3+3X^2-X+5");
        Polynomial polynomial4 = new Polynomial("1-X+X^2");
        polynomial4.isPolynomial("1-X+X^2");
        String result2 = "2X^3+4X^2-2X+6";
        argumentsList.add(Arguments.of(polynomial3, polynomial4,result2));

        Polynomial polynomial5 = new Polynomial("2X+0");
        polynomial5.isPolynomial("2X+0");
        Polynomial polynomial6 = new Polynomial("X^4-6X^9");
        polynomial6.isPolynomial("X^4-6X^9");
        String  result3 = "-6X^9+X^4+2X";
        argumentsList.add(Arguments.of(polynomial5, polynomial6,result3));

        Polynomial polynomial7 = new Polynomial("6X");
        polynomial7.isPolynomial("6X");
        Polynomial polynomial8 = new Polynomial("0");
        polynomial8.isPolynomial("0");
        String  result4 = "6X";
        argumentsList.add(Arguments.of(polynomial7, polynomial8,result4));

        return argumentsList;
    }
}