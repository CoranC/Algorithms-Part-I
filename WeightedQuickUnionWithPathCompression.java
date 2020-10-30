import java.util.List;
import java.util.Arrays;
import java.lang.IllegalArgumentException;
import static java.util.stream.Collectors.toList;

// WeightedQuickUnionWithPathCompression's find and union method is fastest.
// Depth of any node is at most lgN

// Extra space used to keep track of tree sized.
// Initialize | Union | Find
// N          | lg*N  | lgN

// lg*N is the number of times you need to take log of N to get 1
// N       | lg* N
// 1       | 0
// 2       | 1
// 4       | 2
// 16      | 3
// 65536   | 4
// 2^65536 | 5

// In theory, WeightedQuickUnionWithPathCompression is not quite linear.
// In practice, WeightedQuickUnionWithPathCompression is linear.

public class WeightedQuickUnionWithPathCompression {
  
  public List<Integer> connections = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10);
  public List<Integer> size = connections.stream().map(x -> 1).collect(toList());

  /**
   * Recursively checks the item's parent against the parent's parent.
   * When both are equal, this is the root of the tree.
   * Small optimization is to use path compression. This is the act of setting
   * each node's root to its grand parent's
   */
  private int getRoot(int itemId){
    while(itemId != connections.get(itemId)){
      // set every node in path to point to its grand parent.
      // this halves the path length and compresses the path.
      int parentId = connections.get(itemId);
      connections.set(parentId, connections.get(parentId));
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
    WeightedQuickUnionWithPathCompression wquwpc = new WeightedQuickUnionWithPathCompression();
    wquwpc.union(0, 5);
    wquwpc.union(5, 6);
    wquwpc.union(1, 2);
    wquwpc.union(2, 7);
    wquwpc.union(8, 3);
    wquwpc.union(3, 4);
    wquwpc.union(4, 9);
    System.out.println(wquwpc.connections);
    System.out.println(wquwpc.isConnected(0, 6) + " == true");
    System.out.println(wquwpc.isConnected(0, 9) + " == false");
    wquwpc.union(0,8);
    System.out.println(wquwpc.connections);
    System.out.println(wquwpc.isConnected(0, 9) + " == true");
  }
}
