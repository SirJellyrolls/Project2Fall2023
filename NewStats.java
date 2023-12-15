package sta.BasicStats;

public class NewStats {
    public double UnifromDist(double a, double b){
        return (1.0/(b-a));
    }
    public double UnifromMean(double a,double b){
        return((a+b)/2);
    }
    public double UnifromVariance(double a,double b){
        double diff=b-a;
        return((diff*diff)/12);
    }
}
