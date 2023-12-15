package sta.Project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class ReportProj {

    public void getGamesFor(double userPrice) throws FileNotFoundException, IOException {  // This is used for page 1 condtional 
        // objective: return num of games under the given price
        try (BufferedReader br = new BufferedReader(new FileReader("A://Dowloads/games.csv"))) {
            // get rid of title line
            String currentLine = br.readLine();
            //Set currentLine to first line of actual data
            currentLine = br.readLine();

            int totalUnderPrice = 0; //Keep track of games under given price
            int totalPositiveUnderPrice=0; //Keeps track of postive reviewed games 
            
            while (currentLine != null) {
                String[] ar = currentLine.split(",");
                if (Double.valueOf(ar[10]) <= userPrice) {  //See if the orginal price is less than desired
                    totalUnderPrice++; //add game to entry

                    if(ar[6].contains("P")){
                        totalPositiveUnderPrice++;
                    }
                }
                currentLine = br.readLine();
            }System.out.println(totalUnderPrice);System.out.println(totalPositiveUnderPrice); 
            
        double probUnderPrice=totalUnderPrice/50872.0;
        double probPositiveUnderPrice=totalPositiveUnderPrice/50872.0;
        System.out.println(probUnderPrice); System.out.println(probPositiveUnderPrice); 
        double probPosGivenPrice=probPositiveUnderPrice/probUnderPrice;
        System.out.println(probPosGivenPrice); 
        }
    }
    

    public void getMixedReviews() throws FileNotFoundException, IOException {  // This is used for page 1 condtional 
        // objective: return num of games under the given price
        try (BufferedReader br = new BufferedReader(new FileReader("A://Dowloads/games.csv"))) {
            // get rid of title line
            String currentLine = br.readLine();
            //Set currentLine to first line of actual data
            currentLine = br.readLine();
            int mixedAmount=0;
            while (currentLine != null) {
                String[] ar = currentLine.split(",");
                if (ar[6].contains("Mix")) {  
                    mixedAmount++; 
                }
                currentLine = br.readLine();
            } 
        double mixedProb=mixedAmount/50872.0;
        System.out.println(mixedAmount); 
        System.out.println(mixedProb);
        }
    }

    public void getProbPositiveReviews() throws FileNotFoundException, IOException {  // This is used for page 1 condtional 
        // objective: return num of games under the given price
        try (BufferedReader br = new BufferedReader(new FileReader("A://Dowloads/games.csv"))) {
            // get rid of title line
            String currentLine = br.readLine();
            //Set currentLine to first line of actual data
            currentLine = br.readLine();
            int totalPostiveReviews=0;
            while (currentLine != null) {
                String[] ar = currentLine.split(",");
                if (ar[6].contains("P")) {  
                    totalPostiveReviews++; 
                }
                currentLine = br.readLine();
            } 
        double probUnderPrice=totalPostiveReviews/50872.0;
        System.out.println(probUnderPrice); 
        System.out.println(totalPostiveReviews);
        }
    }
    public void getMacOS()throws FileNotFoundException, IOException {  // This is used for page 1 condtional 
        // objective: return num of games under the given price
        try (BufferedReader br = new BufferedReader(new FileReader("A://Dowloads/games.csv"))) {
            // get rid of title line
            String currentLine = br.readLine();
            String[] a=currentLine.split(",");
            //Set currentLine to first line of actual data
            currentLine = br.readLine();
            int totalMac=0;
            while (currentLine != null) {
                String[] ar = currentLine.split(",");
                if (ar[4].contains("f")) {  
                    totalMac++; 
                }
                currentLine = br.readLine();
            } 
        double macPercent=totalMac/50872.0;
        System.out.println(macPercent); 
        System.out.println(totalMac);
        }
    }
    public void getAvgPrice()throws FileNotFoundException, IOException {  // This is used for page 1 condtional 
        // objective: return num of games under the given price
        try (BufferedReader br = new BufferedReader(new FileReader("A://Dowloads/games.csv"))) {
            // get rid of title line
            String currentLine = br.readLine();
            String[] a=currentLine.split(",");
            //Set currentLine to first line of actual data
            currentLine = br.readLine();
            int total=0;
            while (currentLine != null) {
                String[] ar = currentLine.split(",");
                total+=Double.parseDouble(ar[9]);
                System.out.println(Double.parseDouble(ar[9])+1);
                currentLine = br.readLine();
            } 
        double macPercent=total/50872.0;
        System.out.println(macPercent); 
        
        }
    }

     public void inverse(double userPrice) throws FileNotFoundException, IOException {  // This is used for page 1 condtional 
        // objective: return num of games under the given price
        try (BufferedReader br = new BufferedReader(new FileReader("A://Dowloads/games.csv"))) {
            // get rid of title line
            String currentLine = br.readLine();
            //Set currentLine to first line of actual data
            currentLine = br.readLine();

            int totalUnderPrice = 0; //Keep track of games under given price
            int totalPositiveUnderPrice=0; //Keeps track of postive reviewed games 
            
            while (currentLine != null) {
                String[] ar = currentLine.split(",");
                if (ar[6].contains("P")) {  //See if the orginal price is less than desired
                    totalUnderPrice++; //add game to entry

                    if(Double.valueOf(ar[10]) <= userPrice){
                        totalPositiveUnderPrice++;
                    }
                }
                currentLine = br.readLine();
            }System.out.println(totalUnderPrice);System.out.println(totalPositiveUnderPrice); 
        double probUnderPrice=totalUnderPrice/50872.0;
        double probPositiveUnderPrice=totalPositiveUnderPrice/50872.0;
        System.out.println(probUnderPrice); System.out.println(probPositiveUnderPrice); 
        double probPosGivenPrice=probPositiveUnderPrice/probUnderPrice;
        System.out.println(probPosGivenPrice); 
        }
    }

    public void bayes(double userPrice) throws FileNotFoundException, IOException {  // This is used for page 1 condtional 
        // objective: return num of games under the given price
        try (BufferedReader br = new BufferedReader(new FileReader("A://Dowloads/games.csv"))) {
            // get rid of title line
            String currentLine = br.readLine();
            //Set currentLine to first line of actual data
            currentLine = br.readLine();

            int totalUnderPrice = 0; //Keep track of games under given price 
            int totalPositiveUnderPrice=0; //Keeps track of postive reviewed games 
            int inverseB=0;
            int inverseAgiveB=0;
            while (currentLine != null) {
                String[] ar = currentLine.split(",");
                if (Double.valueOf(ar[10]) <= userPrice) {  //See if the orginal price is less than desired
                    totalUnderPrice++; //add game to entry
                    if(ar[6].contains("P")){
                        totalPositiveUnderPrice++;
                    }
                }else{
                    inverseB++;
                    if(ar[6].contains("P")){
                        inverseAgiveB++;
                    }
                }
                currentLine = br.readLine();
            }System.out.println(totalUnderPrice);System.out.println(totalPositiveUnderPrice); 
            
        double probUnderPrice=totalUnderPrice/50872.0; //P(B)
        double probPositiveUnderPrice=totalPositiveUnderPrice/50872.0;
        //System.out.println(probUnderPrice); System.out.println(probPositiveUnderPrice); 
        double aAndB=probPositiveUnderPrice/probUnderPrice;

        double invProbB=inverseB/50872.0; //P(B')
        double invProbAB=inverseAgiveB/50872.0; //p(A|B')
        double invABoverall=invProbAB/invProbB;

        double top=aAndB*probUnderPrice;
        double bottom=top+(invABoverall*invProbB);
        System.out.println(top/bottom); //A|B 
        }
    }
    public void probDistroGames(){
        int total=50872;
        int pos=36489;
        int neg=14383;
        for(int i=0;i<6;i++){
        BigInteger noGood=combo(36489, i).multiply(combo(14383, 5-i));
        double prob= noGood.doubleValue()/(combo(total, 5)).doubleValue();
        System.out.println("prob of getting "+i+" good games");
        System.out.println(prob);
        }
    }
    public BigInteger combo(int total,int part){
        BigInteger whole=factorial(total);  //N!
        BigInteger portion=factorial(part);  //y!
        BigInteger subPort=factorial(total-part);  //part of denominator that is (N-y)!
        BigInteger bd=whole.divide((portion.multiply(subPort)));  //divide numerator by denominator N/((y!)*((N-y)!))
        return(bd);
    }
    public BigInteger factorial(int number){
        if(number==0){
            return BigInteger.ONE;
        }
        BigInteger fact=BigInteger.ONE;
        for(int i =1;i<number+1;i++){
            fact=fact.multiply(BigInteger.valueOf(i));
        }
        if (number==0){
            fact=BigInteger.ONE;
        }
        return fact;
        
    }
}
