package Models;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.String;

import static java.lang.Character.isDigit;

import java.util.Comparator;

public class Polynomial {
    private ArrayList<Monomial> monomials = new ArrayList<>();

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }

    public void setMonomials(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public void addMonomial(Monomial monomial) {
        this.monomials.add(monomial);
    }

    public Polynomial(String polynomial) {
        splitPolynomial(polynomial);
    }

    public Polynomial(Monomial monomial){
        monomials.add(monomial);
    }

    public Polynomial(ArrayList<Monomial> monomials) {
        for(Monomial monomial:monomials){
            this.monomials.add(new Monomial(monomial));
        }
    }

    public Polynomial() {

    }

    public static boolean matchPol(String polynomial) {

        Pattern pattern = Pattern.compile("^([-+]?(\\d?)(\\*?)(X(\\^\\d)?)?)*$");
        Matcher matcher = pattern.matcher(polynomial);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static String prettyMonomial(String monomial) {
        if (isDigit(monomial.charAt(0)))
            monomial = "+" + monomial;
        monomial = monomial + "+" ;
        int ok = 1;
        while (ok == 1) {
            Pattern pattern1 = Pattern.compile("[+-]([0-9][/])?[0-9]+(\\.[0-9]+)?[+-]");
            Matcher matcher1 = pattern1.matcher(monomial);
            if (matcher1.find()) {
                int pozStart = matcher1.start();
                int pozEnd = matcher1.end();
                monomial = monomial.replace(monomial.substring(pozStart, pozEnd), monomial.substring(pozStart, pozEnd - 1) + "X^0" + monomial.charAt(pozEnd - 1));
            } else {
                ok = 0;
            }
        }
        monomial = monomial.substring(0, monomial.length() - 1);
        return monomial;
    }

    public static String prettyMonomial2(String monomial) {
        monomial = prettyMonomial(monomial);
        monomial = monomial.replace("X+", "X^1+");
        monomial = monomial.replace("X-", "X^1-");
        if (monomial.charAt(monomial.length() - 1) == 'X')
            monomial += "^1" ;

        if (monomial.charAt(0) == 'X') {
            monomial = "+1" + monomial;
        }
        if (monomial.contains("-X")) {
            monomial=monomial.replace("-X", "-1X");
        }
        if (monomial.contains("+X")) {
            monomial=monomial.replace("+X", "+1X");
        }
        monomial = monomial.replace("-", "+-");
        monomial=monomial.replace("*X","X");
        return monomial;
    }


    public void sortDegree() {

        Collections.sort(monomials, new Comparator<Monomial>() {

            @Override
            public int compare(Monomial o1, Monomial o2) {
                return o2.getPower()-o1.getPower();
            }
        });
    }

    public static String isPolynomial(String polynomial) {
        if (matchPol(polynomial) == true) {
            return prettyMonomial2(polynomial);
        } else {
            throw new RuntimeException();
        }
    }

    public void splitPolynomial(String polynomial) {
        polynomial = isPolynomial(polynomial);
        Polynomial polynomial1 = new Polynomial();
        if (polynomial.charAt(0) == '+')
            polynomial = polynomial.substring(1, polynomial.length());
        String[] terms = polynomial.split("\\+");

        for (String s : terms) {
            if (s != null) {
                int coefficient, power;
                coefficient = Integer.parseInt(s.substring(0, s.indexOf("X")));
                power = Integer.parseInt(s.substring(s.indexOf("^") + 1, s.length()));
                monomials.add(new Monomial(coefficient, power));
            }
        }

    }

    public int getDegree(){
        perfectPolynomial();
        if(monomials.size()==0)
            return -70;
        else
            return monomials.get(0).getPower();
    }

    public String toString() {
        String string = "" ;
        for (Monomial monomial : monomials)
                string+=monomial;
        if(string.length()==0)
            return "0";
        if(string.charAt(0)=='+')
            string=string.substring(1);
        return string;
    }

    public void perfectPolynomial(){
        ArrayList<Monomial> finalList=new ArrayList<>();
        for(Monomial monomial:monomials){
            boolean ok=false;
            for(Monomial fMonomial:finalList){
                if(fMonomial.getPower()==monomial.getPower()) {
                    fMonomial.setCoefficient(fMonomial.getCoefficient()+monomial.getCoefficient());
                    ok=true;
                }
            }
            if(!ok)
                finalList.add(monomial);
        }
        monomials=finalList;
        ArrayList<Monomial> finalList2=new ArrayList<>();
        for(Monomial monomial:monomials)
            if(monomial.getCoefficient()!=0)
                finalList2.add(monomial);
        monomials=finalList2;
        sortDegree();
    }

}
