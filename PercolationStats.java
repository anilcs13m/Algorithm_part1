/*---------------------------------------------------------------------
 *  anil kumar 
 *  7 feb 2015
 *
 *
 --------------------------------------------------------------------------------*/

public class PercolationStats {
  private double [] attemps;
  
  // perform T independent computational experiments on an N-by-N grid
  public PercolationStats(int N, int T){
    if (N<=0||T<=0) throw new IllegalArgumentException();
    attemps=new double [T];
    for(int i=0;i<T;i++){
      Percolation perc=new Percolation(N);
      int steps=0;
      while(!perc.percolates()){
        int row=StdRandom.uniform(N)+1;
        int column=StdRandom.uniform(N)+1;
        if(!perc.isOpen(row,column)){
          perc.open(row,column);
          steps++;
        }
      }
     
      attemps[i]=(double)steps/(N*N);
    }
  } 
  
  ///////////////main///////////////////
  public static void main(String[] args){
      PercolationStats ps=new PercolationStats(100,200); 
      StdOut.print("mean = "+ps.mean()+"\n");
      StdOut.print("std dev = "+ps.stddev()+"\n");
      StdOut.print("95% confidence interval = "+ps.confidenceLo()+", "+ps.confidenceHi());
    }
  ////////////////////---------------////////////
  public double mean(){      
    return StdStats.mean(attemps);
  }      
     
  public double stddev(){
    return StdStats.stddev(attemps);
  }  
     
  public double confidenceLo(){
    return mean()-((1.96*stddev())/Math.sqrt(attemps.length)); 
  }  
     
  public double confidenceHi(){
    return mean()+((1.96*stddev())/Math.sqrt(attemps.length)); 
  }          
     
        
}