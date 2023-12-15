package StockbotAssignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class BotTrader {
    private double startCash;
    private int stocksOwned;
    private double mean;

    public BotTrader() {
        startCash = 1000.0;
        mean = 0.0;
        stocksOwned = 0;
    }

    public BotTrader(double userCash) {
        startCash = userCash;
        mean = 0.0;
        stocksOwned = 0;
    }

    public void updateInternalData(double entry) {
        if (mean == 0.0) {
            mean = entry;
        } else {
            mean = (mean + entry) / 2.0;
        }
    }

    public void tradeStock() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("WMT.csv"));
        String csvLine = br.readLine();
        while (csvLine != null) {
            csvLine = br.readLine();
            String[] split = csvLine.split(",");
            Double openVal = Double.parseDouble(split[1]); 
            updateInternalData(openVal); //this is going to update the average Opening Value
            if (openVal < mean && startCash > openVal) { //if opening value is greaer than mean then buy one
                stocksOwned += 1;
                startCash -= openVal;
            }
            if (openVal > mean && stocksOwned > 0) { //else if we own stock and its above the avg sell it
                stocksOwned -= 1;
                startCash += openVal;
            }
            System.out.println(
                    "Stocks owned:" + stocksOwned + " Balance:" + startCash + " mean: " + mean + " open: " + openVal);
        }
    }
    public void tradeStock2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("WMT.csv"));
        BufferedReader rs = new BufferedReader(new FileReader("rsiCSV.csv"));
        String csvLine = br.readLine();
        String rsiLine = rs.readLine();
        while (rsiLine != null) {
            csvLine = br.readLine();
            rsiLine=rs.readLine();
            
            String[] split = csvLine.split(",");
            Double openVal = Double.parseDouble(split[1]);
            
            if (Double.parseDouble(rsiLine)>49.9 && startCash > openVal) { // if rsi value is above 49.9 buy
                stocksOwned += 1;
                startCash -= openVal;
            }
            if (Double.parseDouble(rsiLine)<=49.9 && stocksOwned > 0) {// sell if rsi is less or equal to 49.9 sell a stock
                stocksOwned -= 1;
                startCash += openVal;
            }
            System.out.println(
                    "Stocks owned:" + stocksOwned + " Balance:" + startCash + " mean: " + mean + " open: " + openVal);
        }
    }
    public void tradeStock3() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("WMT.csv"));
        String csvLine = br.readLine();
        while (csvLine != null) {
            csvLine = br.readLine();
            String[] split = csvLine.split(",");
            Double openVal = Double.parseDouble(split[1]); 
            updateInternalData(openVal); //this is going to update the average Opening Value
            if (openVal < mean && startCash > openVal) { //Buy MAX stocks if possible
                while(startCash>openVal){
                stocksOwned += 1;
                startCash -= openVal;
            }}
            if (openVal > mean && stocksOwned > 0) { //sell ALL STOCKS if less than mean
                while(stocksOwned>0){
                stocksOwned -= 1;
                startCash += openVal;
            }
            }
            System.out.println(
                    "Stocks owned:" + stocksOwned + " Balance:" + startCash + " mean: " + mean + " open: " + openVal);
        }
    }

    public void plotRSI() throws IOException{
        //uses simple moving average
        BufferedReader br = new BufferedReader(new FileReader("WMT.csv"));
        String csvLine = br.readLine();
        ArrayList<Double> upMoves= new ArrayList();
        ArrayList<Double> downMoves=new ArrayList();
        
        ArrayList<Double> closingValues=new ArrayList<>();
        ArrayList<Double> avgUp=new ArrayList();
        ArrayList<Double> avgDown=new ArrayList();

        for (int i=1;i<252;i++){   //CHANGE THIS THIS IS HARD CODED CAUSES I WAS LAZY 
            //generated closing values
            csvLine=br.readLine();
            String[] valueLine=csvLine.split(",");
            closingValues.add(Double.parseDouble(valueLine[4]));
        }

        //now calculated first couple values
        for (int i=1;i<closingValues.size();i++){
            //populated values for up and down moves
            if(negativeStatus(closingValues.get(i),closingValues.get(i-1))){//down
                downMoves.add(Math.abs(closingValues.get(i)-closingValues.get(i-1)));
                upMoves.add(0.0);
            }else{ //up
                upMoves.add(closingValues.get(i)-closingValues.get(i-1));
                downMoves.add(0.0);
            }
        }
        rollingAvg(upMoves, avgUp);
        rollingAvg(downMoves, avgDown);
        ArrayList<Double> rs=new ArrayList<>();
        ArrayList<Double> rsi=new ArrayList<>();
        for(int i=0;i<avgDown.size();i++){
            rs.add(avgUp.get(i)/avgDown.get(i)); 
        }
        for(Double rsVal:rs){
            double v=1+rsVal;
            double val=100/(v);
          rsi.add(100-val);
        }

        FileWriter rsiCSV=new FileWriter("rsiCSV.csv");
        BufferedWriter writer=new BufferedWriter(rsiCSV);
        for(Double rsiVal:rsi){
            
            writer.write(rsiVal+"\n");
        }
        writer.close();
        br.close();


        //now for each given day
    }

    public boolean negativeStatus(double valOne, double valTwo) {
        // assume positve
        if ((valOne - valTwo) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public void rollingAvg(ArrayList<Double> calculate,ArrayList<Double> finalAvg){
        
        for(int i=0;i<calculate.size();i++){
            double sum=0;
            for (int j=0;j<i;j++){
                sum+=calculate.get(j);
            }
            double avg=sum/(i+1);

            finalAvg.add(avg);
        }
    }
}
