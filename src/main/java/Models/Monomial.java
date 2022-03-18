package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Monomial{
    private Double coefficient;
    private Integer power;

    public Monomial(double coefficient,int power){
        if(power<0)
            throw new RuntimeException();
        this.coefficient=coefficient;
        this.power=power;
    }
    public Monomial(Monomial monomial){
        //super(coefficient,power);
        this(monomial.getCoefficient(), monomial.getPower());
    }

    public Monomial(){

    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String toString(){
        String string="";
        if(coefficient>0)
            string+="+";
        if(power>0&&coefficient==-1)
            string+="-";
        else
        if(!(power>0&&coefficient==1))
            if(coefficient==coefficient.intValue())
                string+=coefficient.intValue();
            else
                string+=String.format("%.2f",coefficient);
        if(power>0)
            string+="X";
        if(power>1)
            string+="^"+power;
        return string;
    }

}

