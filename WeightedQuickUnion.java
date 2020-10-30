import java.util.List;
import java.util.Arrays;
import java.lang.IllegalArgumentException;
import static java.util.stream.Collectors.toList;

// WeightedQuickUnion's find and union method is fast.
// Depth of any node is at most lgN
// Extra space used to keep track of tree sized.
// Initialize | Union | Find
// N          | lgN     | lgN

public class WeightedQuickUnion {
  
  public List<Integer> connections = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10);
  public List<Integer> size = connections.stream().map(x -> 1).collect(toList());

  /**
   * Recursively checks the item's parent against the parent's parent.
   * When both are equal, this is the root of the tree.
   */
  private int getRoot(int itemId){
    while(itemId != connections.get(itemId)){
      itemId = connections.get(itemId);
    }
    return itemId;
  }

  /**
   * Unions two nodes together.
   * Checks the size of the trees against each other and sets the smaller
   * tree's root to the larger tree's root, keeping the trees balanced.
   */
  public void union(int p, int q) throws IllegalArgumentException {
    if(p < 0 || p > 10 || q < 0 || q > 10){
      throw new IllegalArgumentException();
    }
    // set p's index to q, making p a child of q.
    int pRoot = getRoot(p);
    int qRoot = getRoot(q);
    if(pRoot == qRoot) return;
    // link root of smaller tree to root of larger tree
    if(size.get(pRoot) < size.get(qRoot)){
      connections.set(pRoot, qRoot);
      size.set(qRoot, size.get(pRoot));
    } else {
      connections.set(qRoot, pRoot);
      size.set(pRoot, size.get(qRoot));
    }
  }

  public boolean isConnected(int p, int q){
    return getRoot(p) == getRoot(q);
  }

  public static void main(String[] args) {
    WeightedQuickUnion wqu = new WeightedQuickUnion();
    wqu.union(0, 5);
    wqu.union(5, 6);
    wqu.union(1, 2);
    wqu.union(2, 7);
    wqu.union(8, 3);
    wqu.union(3, 4);
    wqu.union(4, 9);
    System.out.println(wqu.connections);
    System.out.println(wqu.isConnected(0, 6) + " == true");
    System.out.println(wqu.isConnected(0, 9) + " == false");
    wqu.union(0,8);
    System.out.println(wqu.connections);
    System.out.println(wqu.isConnected(0, 9) + " == true");
  }
}
