package Models;

public class Operations {
    public static Polynomial addition(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial(p1.getMonomials());
        for (Monomial monomial : p2.getMonomials())
            result.addMonomial(new Monomial(monomial));
        result.perfectPolynomial();
        return result;
    }

    public static Polynomial subtraction(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial(p1.getMonomials());
        for (Monomial monomial : p2.getMonomials())
            result.addMonomial(new Monomial(-monomial.getCoefficient(), monomial.getPower()));
        result.perfectPolynomial();
        return result;
    }

    public static Polynomial multiplication(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial : p1.getMonomials())
            for (Monomial monomial2 : p2.getMonomials())
                result.addMonomial(new Monomial(monomial.getCoefficient() * monomial2.getCoefficient(), monomial.getPower() + monomial2.getPower()));
        result.perfectPolynomial();
        return result;
    }

    public static Polynomial derivative(Polynomial p) {
        Polynomial result = new Polynomial();
        p.perfectPolynomial();
        for (Monomial monomial : p.getMonomials()) {
            if (monomial.getPower() > 0)
                result.addMonomial(new Monomial(monomial.getCoefficient() * monomial.getPower(), monomial.getPower() - 1));
        }
        return result;
    }

    public static Polynomial integral(Polynomial p) {
        Polynomial result = new Polynomial();
        p.perfectPolynomial();
        for (Monomial monomial : p.getMonomials()) {
            if (monomial.getPower() >=0) {
                double coefficient1=monomial.getCoefficient()/(monomial.getPower()+1);
                Monomial monomial1=new Monomial(coefficient1,monomial.getPower()+1);
                result.addMonomial(monomial1);
            }
        }
        System.out.println(result);
        return result;
    }

    public static Polynomial division(Polynomial p1,Polynomial p2,Polynomial remainder){
        p1.perfectPolynomial();
        p2.perfectPolynomial();
        Polynomial result=new Polynomial();
        Polynomial r=new Polynomial(p1.getMonomials());

        if(p2.toString().compareTo("0")==0)
            throw new RuntimeException("Division by 0");

        while(r.getDegree()>=0&&r.getDegree()>=p2.getDegree()){
            Monomial mon=new Monomial(r.getMonomials().get(0).getCoefficient()/p2.getMonomials().get(0).getCoefficient(),r.getMonomials().get(0).getPower()-p2.getMonomials().get(0).getPower());
            result.addMonomial(mon);
            r=subtraction(r,multiplication(p2,new Polynomial(mon)));
        }

        for(Monomial monomial:r.getMonomials())
            remainder.addMonomial(monomial);

        return result;
    }
    public static Polynomial divide(Polynomial p1,Polynomial p2){
        p1.perfectPolynomial();
        p2.perfectPolynomial();
        Polynomial result=new Polynomial();
        Polynomial r=new Polynomial(p1.getMonomials());

        if(p2.toString().compareTo("0")==0)
            throw new RuntimeException("Division by 0");

        while(r.getDegree()>=0&&r.getDegree()>=p2.getDegree()){
            Monomial mon=new Monomial(r.getMonomials().get(0).getCoefficient()/p2.getMonomials().get(0).getCoefficient(),r.getMonomials().get(0).getPower()-p2.getMonomials().get(0).getPower());
            result.addMonomial(mon);
            r=subtraction(r,multiplication(p2,new Polynomial(mon)));
        }


        return result;
    }
}
