import java.util.List;
import java.util.Arrays;
import java.lang.IllegalArgumentException;

// QuickUnion's find and union method is too expensive in the worst case.
// Initialize | Union | Find
// N          | N     | N

public class QuickUnion {
  
  public List<Integer> connections = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10);


  private int getRoot(int itemId){
    int itemParentId = connections.get(itemId);
    while(itemId != itemParentId){
      itemId = getRoot(itemParentId);
    }
    return itemParentId;
  }

  /**
   * Unions two nodes together.
   * Sets p's root to q's root.
   */
  public void union(int p, int q) throws IllegalArgumentException {
    if(p < 0 || p > 10 || q < 0 || q > 10){
      throw new IllegalArgumentException();
    }
    // set p's index to q, making p a child of q.
    int pId = getRoot(p);
    int qId = getRoot(q);
    if(pId == qId) return;
    connections.set(p, qId);

  }

  public boolean isConnected(int p, int q){
    return getRoot(p) == getRoot(q);
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
