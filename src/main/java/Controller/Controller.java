package Controller;

import Models.Monomial;
import Models.Operations;
import Models.Polynomial;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class Controller implements Initializable{


    TextField ok;
    @FXML
    void onFirstPolynomialClick(){
        ok = firstPolynomialField;
    }
    @FXML
    void onSecondPolynomialClick(){
        ok = secondPolynomialField;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ok = firstPolynomialField;
    }
    @FXML
    TextField firstPolynomialField;
    @FXML
    TextField secondPolynomialField;
    @FXML
    TextField resultField;
    @FXML
    Button button1;
    @FXML
    void onButton1Click(){
        ok.setText(ok.getText()+'1');
    }
    @FXML
    Button button2;
    @FXML
    void onButton2Click(){
        ok.setText(ok.getText()+'2');
    }
    @FXML
    Button button3;
    @FXML
    void onButton3Click(){
        ok.setText(ok.getText()+'3');
    }
    @FXML
    Button button4;
    @FXML
    void onButton4Click(){
        ok.setText(ok.getText()+'4');
    }
    @FXML
    Button button5;
    @FXML
    void onButton5Click(){
        ok.setText(ok.getText()+'5');
    }
    @FXML
    Button button6;
    @FXML
    void onButton6Click(){
        ok.setText(ok.getText()+'6');
    }
    @FXML
    Button button7;
    @FXML
    void onButton7Click(){
        ok.setText(ok.getText()+'7');
    }
    @FXML
    Button button8;
    @FXML
    void onButton8Click(){
        ok.setText(ok.getText()+'8');
    }
    @FXML
    Button button9;
    @FXML
    void onButton9Click(){
        ok.setText(ok.getText()+'9');
    }
    @FXML
    Button button0;
    @FXML
    void onButton0Click(){
        ok.setText(ok.getText()+'0');
    }
    @FXML
    Button buttonPlus;
    @FXML
    void onButtonPlusClick(){
        ok.setText(ok.getText()+'+');
    }
    @FXML
    Button buttonMinus;
    @FXML
    void onButtonMinusClick(){
        ok.setText(ok.getText()+'-');
    }
    @FXML
    Button buttonPower;
    @FXML
    void onButtonPowerClick(){
        ok.setText(ok.getText()+'^');
    }
    @FXML
    Button buttonOr;
    @FXML
    void onButtonOrClick(){
        ok.setText(ok.getText()+'*');
    }
    @FXML
    Button buttonPoint;
    @FXML
    void onButtonPointClick(){
        ok.setText(ok.getText()+'.');
    }
    @FXML
    Button buttonX;
    @FXML
    void onButtonXClick(){
        ok.setText(ok.getText()+'X');
    }
    @FXML
    Button buttonDelete;
    @FXML
    void onButtonDeleteClick(){
        firstPolynomialField.setText("");
        secondPolynomialField.setText("");
        resultField.setText("");
    }
    @FXML
    void onAddClick(){
        Polynomial p1=null;
        Polynomial p2=null;
        try {
            p1 = new Polynomial(firstPolynomialField.getText());
            p2 = new Polynomial(secondPolynomialField.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Invalid input","Error",JOptionPane.ERROR_MESSAGE);
        }
        if(p1!=null&&p2!=null)
            resultField.setText(""+Operations.addition(p1,p2));
        else
            JOptionPane.showMessageDialog(new JFrame(),"Null input","Error",JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    void onSubtractClick(){
        Polynomial polynomial1=null;
        Polynomial polynomial2=null;
        try {
            polynomial1 = new Polynomial(firstPolynomialField.getText());
            polynomial2 = new Polynomial(secondPolynomialField.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Invalid input","Error",JOptionPane.ERROR_MESSAGE);
        }
        if(polynomial1!=null&&polynomial2!=null)
            resultField.setText(""+Operations.subtraction(polynomial1,polynomial2));
        else
            JOptionPane.showMessageDialog(new JFrame(),"Null input","Error",JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    void onMultiplyClick(){
        Polynomial polynomial1=null;
        Polynomial polynomial2=null;
        try {
            polynomial1 = new Polynomial(firstPolynomialField.getText());
            polynomial2 = new Polynomial(secondPolynomialField.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Invalid input","Error",JOptionPane.ERROR_MESSAGE);
        }
        if(polynomial1!=null&&polynomial2!=null)
            resultField.setText(""+Operations.multiplication(polynomial1,polynomial2));
        else
            JOptionPane.showMessageDialog(new JFrame(),"Null input","Error",JOptionPane.ERROR_MESSAGE);
    }

    @FXML
    void onDerivativeClick(){
        Polynomial polynomial1=null;
        try {
            polynomial1 = new Polynomial(firstPolynomialField.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Invalid input","Error",JOptionPane.ERROR_MESSAGE);
        }
        if(polynomial1!=null)
            resultField.setText(""+Operations.derivative(polynomial1));
        else
            JOptionPane.showMessageDialog(new JFrame(),"Null input","Error",JOptionPane.ERROR_MESSAGE);
    }

    @FXML
    void onIntegralClick(){
        Polynomial polynomial1=null;
        try {
            polynomial1 = new Polynomial(firstPolynomialField.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Invalid input","Error",JOptionPane.ERROR_MESSAGE);
        }
        if(polynomial1!=null)
            resultField.setText(""+Operations.integral(polynomial1));
        else
            JOptionPane.showMessageDialog(new JFrame(),"Null input","Error",JOptionPane.ERROR_MESSAGE);
    }

    @FXML
    void onDivideClick(){
        Polynomial polynomial1=null;
        Polynomial polynomial2=null;
        try {
            polynomial1 = new Polynomial(firstPolynomialField.getText());
            polynomial2 = new Polynomial(secondPolynomialField.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Invalid input","Error",JOptionPane.ERROR_MESSAGE);
        }
        if(polynomial1!=null&&polynomial2!=null) {
            try{
            Polynomial remainder=new Polynomial();
            Polynomial result=Operations.division(polynomial1,polynomial2,remainder);
            resultField.setText(""+result+" Rest: "+remainder);}
            catch(Exception e){
                JOptionPane.showMessageDialog(new JFrame(),"Division by 0","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(new JFrame(),"Null input","Error",JOptionPane.ERROR_MESSAGE);
    }
}
