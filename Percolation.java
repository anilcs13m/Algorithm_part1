/*---------------------------------------------------------------------
 *  anil kumar 
 *  5 feb 2015
 *
 *
 --------------------------------------------------------------------------------*/

public class Percolation {
    private boolean[] grid;
    private int NUM;
    private WeightedQuickUnionUF wquf;

    public Percolation(int N) {
        // adding 2 is for virtual top and bottom
        grid = new boolean[N * N + 2];
        wquf = new WeightedQuickUnionUF(N * N + 2);
        NUM = N;
        
        // create a union with virtual top and top row
        for(int i = 1; i <= N; i++) {
        	wquf.union(0, i);
        }
        
        // create a union with virtual bottom and bottom row
        for(int i = N * N; i > N * N - N; i--) {
        	wquf.union(N * N + 1, i);
        }
    }
    
    private boolean[] get_grid() {
        return grid;
    }
    
   
    private int pointer(int i, int j) {
        int pointer = 1;
        pointer = pointer + ((j - 1) * NUM);
        pointer = pointer + (i - 1);
        return pointer;
    }
    
    
    public void open(int i, int j) 
    {
        // set block to open
        if(!grid[pointer(i, j)])
        {
            grid[pointer(i, j)] = true;
            if(i < 1 || i > NUM || j < 1 || j > NUM)
            {
                throw new java.lang.IndexOutOfBoundsException();
            }
            if(i < NUM && isOpen(i+1, j))
            {
            	wquf.union(pointer(i, j), pointer(i+1, j));
            	}
            if(i > 1 && isOpen(i-1, j)) 
            {
            	wquf.union(pointer(i, j), pointer(i-1, j));
            	}
            if(j < NUM && isOpen(i, j+1)) 
            {
            	wquf.union(pointer(i, j), pointer(i, j+1));
            	}
            if(j > 1 && isOpen(i, j-1)) 
            {
            	wquf.union(pointer(i, j), pointer(i, j-1));
               }            
        }
        
    }

    //percolation return with 2 virtual sites
    public boolean percolates()
    {
        return wquf.connected(0, NUM * NUM + 1);
    }
 // check for site is open or not    
    public boolean isOpen(int i, int j) {
        if(i < 1 || i > NUM || j < 1 || j > NUM)
        {
            throw new java.lang.IndexOutOfBoundsException();
        }        
        return grid[pointer(i, j)];
    }
    // check for the full or not
    public boolean isFull(int i, int j) 
    {
        if(i < 1 || i > NUM || j < 1 || j > NUM) {
            throw new java.lang.IndexOutOfBoundsException();
        }        
        // check that point is connected to virtual top and is open
        return (isOpen(i, j) && wquf.connected(0, pointer(i, j)));
    }
    
}