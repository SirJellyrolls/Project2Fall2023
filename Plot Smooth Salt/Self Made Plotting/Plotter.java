package sta.Project2;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class Plotter {

    public void runTest(int numOfPts,double constant) throws IOException{
        long startTime = System.nanoTime();
        
        ArrayList<double[]> a=plotEquation(numOfPts,constant);
        ArrayList<double[]> salt=salter(a);
        ArrayList<double[]> smooth=removeSalt(salt);
        //p.printPoints(a);
        getCSVPoints(a,"OriginalPoints.csv");
         System.out.println("\n");
        //p.printPoints(salt); 
        getCSVPoints(salt,"SaltedPoints.csv");
        System.out.println("\n");
       // p.printPoints(smooth);
        getCSVPoints(smooth,"SmoothPoints.csv");
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000);
    }

    public ArrayList plotEquation(int numOfPts,double a){
        //y=x^2+x+c
        ArrayList<Double> points=new ArrayList();
        ArrayList<double[]> xy=new ArrayList<>();
        for(double i=0;i<numOfPts;i++){
            double xSq=a*(i*i);
            double y=xSq;
            points.add(y);
            double[] pnt={i,y};
            xy.add(pnt);
            //System.out.println(y);
        }
        //System.out.println(xy.toString());
        return xy;
        
    }
    public ArrayList salter(ArrayList<double[]> points){
        ArrayList<double[]> salted=new ArrayList();
        Random r = new Random();
        for(double[] point:points){
            int neg=r.nextInt(2);//0 add 1 subtract
            if(neg==0){
            double saltX=point[0]+r.nextInt(10);
            double saltY=point[1]+r.nextInt(10);
            double[] saltedPair={saltX,saltY};
            salted.add(saltedPair);
            }
            if(neg==1){
            double saltX=point[0]-r.nextInt(10);
            double saltY=point[1]-r.nextInt(10);
            double[] saltedPair={saltX,saltY};
            salted.add(saltedPair);
            }
           
        }
        //System.out.println(salted+"hhhhhh");
        return salted;
    }
    public ArrayList removeSalt(ArrayList<double[]> saltedData){
        ArrayList<double[]> unsalted=new ArrayList<>();
        //using a rolling avg with window of 3 
        double rollingX=0;
        double rollingY=0;
        
        for(int i=0;i<saltedData.size();i++){
            if(i<3){
                //first three values only take right hand avg
                double[] current=saltedData.get(i);
                double[] over1=saltedData.get(i+1);
                double[] over2=saltedData.get(i+2);
                double[] over3=saltedData.get(i+3);
                double avgX=current[0]+over1[0]+over2[0]+over3[0];
                avgX=avgX/4.0;
                double avgY=current[1]+over1[1]+over2[1]+over3[1];
                avgY=avgY/4.0;
                double[] newPair={avgX,avgY};
                unsalted.add(newPair);
            }else if(i>saltedData.size()-4){
                double[] current=saltedData.get(i);
                double[] under1=saltedData.get(i-1);
                double[] under2=saltedData.get(i-2);
                double[] under3=saltedData.get(i-3);
                double avgX=current[0]+under1[0]+under2[0]+under3[0];
                avgX=avgX/4.0;
                double avgY=current[1]+under1[1]+under2[1]+under3[1];
                avgY=avgY/4.0;
                double[] newPair={avgX,avgY};
                unsalted.add(newPair);
            }else{
                double[] over1=saltedData.get(i+1);
                double[] over2=saltedData.get(i+2);
                double[] over3=saltedData.get(i+3);
                double[] current=saltedData.get(i);
                double[] under1=saltedData.get(i-1);
                double[] under2=saltedData.get(i-2);
                double[] under3=saltedData.get(i-3);
                double avgX=current[0]+over1[0]+over2[0]+over3[0]+under1[0]+under2[0]+under3[0];
                double avgY=current[1]+over1[1]+over2[1]+over3[1]+under1[1]+under2[1]+under3[1];
                avgX/=7.0;
                avgX/=7.0;
                double[] newPair={avgX,avgY};
                unsalted.add(newPair);
            }
        }
        return unsalted;
    }
    public void printPoints(ArrayList<double[]> points){
        for(double[] point:points){
            System.out.print("("+point[0] +","+point[1]+") ");
        }
    }
    public void getCSVPoints(ArrayList<double[]> points,String nameOfCSV) throws IOException{
        FileWriter pointWriter=new FileWriter(nameOfCSV);
        BufferedWriter actualWriter=new BufferedWriter(pointWriter);
        actualWriter.write("x,y\n");
        for(double[] point:points){
            actualWriter.write(point[0]+","+point[1]+"\n");
        }
        actualWriter.close();
    }
    
}
