
public class Subset
{
    public static void main(String[] args) 
    {
        String line = StdIn.readLine();
        String[] stringArr = line.split(" ");
        int k = Integer.parseInt(args[0]);
        Deque<String> q = new Deque<String>();
        for(String s : stringArr)
        {
            q.addFirst(s);
        }
        for(int i = 0; i < k; i++)
        {
            StdOut.println(q.removeFirst());
        }
    }
}