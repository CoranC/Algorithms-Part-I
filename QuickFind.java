import java.util.List;
import java.util.Arrays;
import java.lang.IllegalArgumentException;

// QuickFind's union method is too expensive.
// Initialize | Union | Find
// N          | N     | 1
public class QuickFind {

  public List<Integer> connections = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10);

  /**
   * Unions two nodes together.
   * Sets all of the nodes that have p's root to point to q's root.
   */
  public void union(int p, int q) throws IllegalArgumentException {
    if(p < 0 || p > 10 || q < 0 || q > 10){
      throw new IllegalArgumentException();
    }
    // set all entries containing p's id to q's id.
    int pId = connections.get(p);
    int qId = connections.get(q);
    for(int i = 0; i < connections.size(); i++){
      if(connections.get(i) == pId) connections.set(i, qId);
    }
  }

  public boolean isConnected(int p, int q){
    return connections.get(p) == connections.get(q);
  }

  public static void main(String[] args) {
    QuickFind qf = new QuickFind();
    qf.union(0, 5);
    qf.union(5, 6);
    qf.union(1, 2);
    qf.union(2, 7);
    qf.union(8, 3);
    qf.union(3, 4);
    qf.union(4, 9);
    System.out.println(qf.connections);
    System.out.println(qf.isConnected(0, 6) + " == true");
    System.out.println(qf.isConnected(0, 9) + " == false");
    qf.union(0,8);
    System.out.println(qf.connections);
    System.out.println(qf.isConnected(0, 9) + " == true");
  }
}
