import java.util.Scanner;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class data{
    public Scanner s=new Scanner(System.in);
    public double height,weight,foot;
    public void getData(){
        System.out.println("Enter the height weight and foot size");
        height=s.nextDouble();
//        System.out.println("Enter the weight");
        weight=s.nextDouble();
//        System.out.println("Enter the foot size");
        foot=s.nextDouble();
    }
    public void showData(){
        System.out.println("height: "+height+"\tweight: "+weight+"\t footSize: "+foot);
    }

}
public class BoyGirl {
    private static double posteriorMale,posteriorFemale;
    private static double[] array=new double[10];// It will store the probabilities for some time;
    private static double meanHeight,meanWeight,meanFootSize;//will be used for female after finishing up male
    private static double meanMaleHeight,meanMaleWeight,meanMaleFootSize;
    private static double varianceHeight,varianceWeight,varianceFootSize;//again for female;
    private static double maleVarianceHeight,maleVarianceWeight,maleVarianceFootSize;
    private static int n=4;//no. of persons of each sex;
    private static void initialiseDataArray(data ex[]){
        for( int i=1; i<=n; i++ )
            ex[i] = new data();
        /*A[] a = new A[4];
creates 4 A references, similar to doing this
A a1;
A a2;
A a3;
A a4;
now you couldn't do a1.someMethod() without allocating a1 as
a1 = new A();
similarly, with the array you need to do
a[0] = new A();
before using it.*/
    }
    private static void getTheData(data ex[]){
        for(int i=1;i<=n;i++){
            ex[i].getData();
        }
    }
    private static void showTheData(data ex[]){
        for(int i=1;i<=n;i++){
            ex[i].showData();
        }
    }
    public static void main(String[] args){
        data[] male=new data[5];
        data[] female=new data[5];
        data[] test=new data[5];
        initialiseDataArray(male);
        initialiseDataArray(female);
        initialiseDataArray(test);
        System.out.println("For "+n+" males");
        getTheData(male);
        System.out.println("For "+n+" females");
        getTheData(female);
        System.out.println("For "+n+" test sample");
        getTheData(test);
        showTheData(male);
        showTheData(female);
        showTheData(test);
        calculateMean(male);//it also calculates variance;
        setter();//will set the mean and variance variables to zero to reuse the variables;
        calculateMean(female);//this fuction is compulsary to be called after calculateMean(male)
        calculate(test);//It calculates the probability and the posterior;
       // posterior();
        System.out.println("I love you Tabasum");
    }
    private static void calculateMean(data ex[]){
        for(int i=1;i<=n;i++){
            meanHeight=meanHeight+ex[i].height;
            meanWeight=meanWeight+ex[i].weight;
            meanFootSize=meanFootSize+ex[i].foot;
        }
        meanHeight=meanHeight/(double)n;
        meanWeight=meanWeight/(double)n;
        meanFootSize=meanFootSize/(double)n;
        System.out.println("meanHeight: "+meanHeight+"\t meanweight: "+meanWeight+"\t meanFoot size"+meanFootSize);
        for(int i=1;i<=n;i++){
            varianceHeight=varianceHeight+pow((ex[i].height-meanHeight),2);
            varianceWeight=varianceWeight+pow((ex[i].weight-meanWeight),2);
            varianceFootSize=varianceFootSize+pow((ex[i].foot-meanFootSize),2);
        }
        varianceHeight=varianceHeight/(double) (n-1);
        varianceWeight=varianceWeight/(double) (n-1);
        varianceFootSize=varianceFootSize/(double) (n-1);
    }
    private static void setter(){
        meanMaleHeight=meanHeight;meanMaleWeight=meanWeight;meanMaleFootSize=meanFootSize;
        maleVarianceHeight=varianceHeight;maleVarianceWeight=varianceWeight;maleVarianceFootSize=varianceFootSize;
        meanHeight=meanWeight=meanFootSize=0.0;
        varianceHeight=varianceWeight=varianceFootSize=0.0;
    }
    private static void calculate(data ex[]){

        for(int i=1;i<=n;i++){
            array[1]=0.5;
            array[2]=(exp((-pow((ex[i].height-meanMaleHeight),2))/(2*maleVarianceHeight)))/(sqrt(2*3.14*maleVarianceHeight));
            array[3]=(exp((-pow((ex[i].weight-meanMaleWeight),2))/(2*maleVarianceWeight)))/(sqrt(2*3.14*maleVarianceWeight));
            array[4]=(exp((-pow((ex[i].foot-meanMaleFootSize),2))/(2*maleVarianceFootSize)))/(sqrt(2*3.14*maleVarianceFootSize));
            array[5]=(exp((-pow((ex[i].height-meanHeight),2))/(2*varianceHeight)))/(sqrt(2*3.14*varianceHeight));
            array[6]=(exp((-pow((ex[i].weight-meanWeight),2))/(2*varianceWeight)))/(sqrt(2*3.14*varianceWeight));
            array[7]=(exp((-pow((ex[i].foot-meanFootSize),2))/(2*varianceFootSize)))/(sqrt(2*3.14*varianceFootSize));
            array[8]=0.5;
            posteriorMale=array[1]*array[2]*array[3]*array[4];
            posteriorFemale=array[8]*array[5]*array[6]*array[7];
            System.out.println("Posterior male is "+posteriorMale+" posterior female "+posteriorFemale);
            if(posteriorFemale>posteriorMale){
                System.out.println("According to the provided data ,the person is predicted as female");
            }
            else{
                System.out.println("According to the provided data ,the person is predicted as Male");
            }
        }

    }
    private static void posterior(){

//        posteriorMale=array[1]*array[2]*array[3]*array[4];
//        posteriorFemale=array[8]*array[5]*array[6]*array[7];
//        System.out.println("Posterior male is "+posteriorMale+" posterior female "+posteriorFemale);
//        if(posteriorFemale>posteriorMale){
//            System.out.println("According to the provided data ,the person is predicted as female");
//        }
//        else{
//            System.out.println("According to the provided data ,the person is predicted as Male");
//        }
    }
}
/*All Rights Reserved
Nongjaimayum Annas khan
Husband of Ph Tabasum Sahani
*/