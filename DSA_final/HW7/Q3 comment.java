```java
public class AVLTree extends BTreePrinter{
    Node root;


  public void singleRotateFromLeft(Node y) {
    if (y == null || y.left == null)   //เช็คว่า node y หรือ  node y ฝั่งซ้าย ว่าง
      return;

    Node x = y.left; //ให้ node x คือ node ฝั่งซ้ายของ node y
    Node w = y.parent;  //ให้ node w คือ node parent ของ node y

    Node inner = x.right; // ให้ node inner คือ node ฝั่งขวาของ node x
    y.left = inner; // ให้ node ฝั่งซ้ายของ node y คือ node inner
    if (inner != null)  //เช้คว่า node inner ไม่ว่าง 
      inner.parent = y; //ให้ node parent ของ node inner คือ node y

    x.parent = w; //ให้ node parent ของ node x คือ node w
    if (w == null) { //เช็คว่า node w ว่างหรือเปล่า
      root = x; // ให้ node root คือ node x
    } else {
      if (w.left == y) { //เช็คว่า node ฝั่งซ้ายของ node w คือ node y หรือเปล่า
        w.left = x; //ให้ node ฝั่งซ้ายของ node w คือ node x
      } else {
        w.right = x; //ให้ node ฝั่งขวาของ node w คือ node x
      }
    }

    x.right = y; //ให้ node ฝั่งขวาของ node x คือ node y
    y.parent = x; //ให้ node parent ของ node y คือ node x
  }

  public void singleRotateFromRight(Node y) {
    if (y == null || y.right == null) //เช็คว่า node y หรือ node y ฝั่งขวา ว่าง
      return;

    Node x = y.right; //ให้ node x คือ node ฝั่งขวาของ node y
    Node w = y.parent; //ให้ node w คือ node parent ของ node y

    Node inner = x.left; // ให้ node inner คือ node ฝั่งซ้ายของ node x
    y.right = inner; // ให้ node ฝั่งขวาของ node y คือ node inner
    if (inner != null) //เช้คว่า node inner ไม่ว่าง
      inner.parent = y; //ให้ node parent ของ node inner คือ node y

    x.parent = w;   //ให้ node parent ของ node x คือ node w
    if (w == null) { //เช็คว่า node w ว่างหรือเปล่า
      root = x; // ให้ node root คือ node x
    } else {
      if (w.left == y) { //เช็คว่า node ฝั่งซ้ายของ node w คือ node y หรือเปล่า
        w.left = x; //ให้ node ฝั่งซ้ายของ node w คือ node x
      } else {
        w.right = x; //ให้ node ฝั่งขวาของ node w คือ node x
      }
    }

    x.left = y; //ให้ node ฝั่งซ้ายของ node x คือ node y
    y.parent = x; //ให้ node parent ของ node y คือ node x
  }

  public void doubleRotateFromLeft(Node y) {
    if (y == null || y.left == null || y.left.right == null) //เช็คว่า node y หรือ node y ฝั่งซ้าย ว่าง หรือ node ฝั่งขวาของ node y ฝั่งซ้าย ว่าง
      return;

    singleRotateFromRight(y.left); // ให้ node y ฝั่งซ้าย หมุนขวา
    singleRotateFromLeft(y); //ให้ node y หมุนซ้าย
  }

  public void doubleRotateFromRight(Node y) {  
    if (y == null || y.right == null || y.right.left == null) //เช็คว่า node y หรือ node y ฝั่งขวา ว่าง หรือ node ฝั่งซ้ายของ node y ฝั่งขวา ว่าง
      return;

    singleRotateFromLeft(y.right); // ให้ ฝั่งขวาของ node  y หมุนซ้าย
    singleRotateFromRight(y); //  ให้ node  y หมุนขวา
  }

  public static void rebalance(AVLTree tree, Node node) {
    if (node == null) //เช็คว่า node ว่างหรือเปล่า
      return;

    int balanceFactor = height(node.left) - height(node.right); //คำนวณค่า balance factor ของ node โดยความสูงของ node ฝั่งซ้าย ลบด้วย ความสูงของ node ฝั่งขวา

    if (Math.abs(balanceFactor) > 1) {   //เช็คว่า balance factor ของ node มีค่าสัมบูรณ์มากกว่า 1 หรือเปล่า
      if (balanceFactor > 1) {   //เช็คว่า balance factor ของ node มากกว่า 1 หรือเปล่า
        if (height(node.left.left) >= height(node.left.right)) {  //เช็คว่า ความสูงของ node ฝั่งซ้ายของ node ฝั่งซ้าย ของ node มากกว่าหรือเท่ากับ ความสูงของ node ฝั่งขวาของ node ฝั่งซ้าย ของ node หรือเปล่า
          System.out.println("Perform SingleRotationFromLeft(Node " + node.key + ")");
          tree.singleRotateFromLeft(node);
        } else {
          System.out.println("Perform DoubleRotationFromLeft(Node " + node.key + ")");
          tree.doubleRotateFromLeft(node);
        }
      } else {
        if (height(node.right.right) >= height(node.right.left)) {   //เช็คว่า ความสูงของ node ฝั่งขวาของ node ฝั่งขวา ของ node มากกว่าหรือเท่ากับ ความสูงของ node ฝั่งซ้ายของ node ฝั่งขวา ของ node หรือเปล่า
          System.out.println("Perform SingleRotationFromRight(Node " + node.key + ")");
          tree.singleRotateFromRight(node);
        } else {
          System.out.println("Perform DoubleRotationFromRight(Node " + node.key + ")");
          tree.doubleRotateFromRight(node);
        }
      }
    }
  }
    
    // Fix this function to have the rebalancing feature
    // There should be rebalance() function calling somewhere in the code
    public static void insertKey(AVLTree tree, Node node, int key) {
        if (key == node.key) {   //เช็คว่า key ที่จะ insert มีค่าเท่ากับ node.key หรือเปล่า
            System.out.println("Duplicated key:" + key);
        }else if (key < node.key) { // ถ้า key มีค่าน้อยกว่า node.key 
            if (node.left == null) { //เช็คว่า node ฝั่งซ้ายว่างหรือเปล่า
                node.left = new Node(key); //ให้ node ฝั่งซ้าย คือ node ใหม่ที่มีค่า key
                node.left.parent = node; //ให้ node parent ของ node ฝั่งซ้าย คือ node
            }else {
                insertKey(tree, node.left, key); 
            }
        }else{ 
            if (node.right == null) { //เช็คว่า node ฝั่งขวาว่างไหม
                node.right = new Node(key); //ให้ node ฝั่งขวา คือ node ใหม่ที่มีค่า key
                node.right.parent = node; //ให้ node parent ของ node ฝั่งขวา คือ node
            }else {
                insertKey(tree, node.right, key); 
            }
        }
        rebalance(tree, node);
    }
    
    // This function is complete, no need to edit
    public void insertKey(int key) {
        if (root == null) {    //เช็คว่า node root ว่างหรือเปล่า
            root = new Node(key);   //ให้ node root คือ node ใหม่ที่มีค่า key
        } else {
            insertKey(this, root, key);
        }
    }
    
    // This function is complete, no need to edit
    public Node findKey(int search_key) {
        return Main.findKey(root, search_key);
    }

    // Use this function to check the node height
    // This function is complete, no need to edit
    public static int height(Node node){
        if (node == null) //เช็คว่า node ว่างหรือเปล่า
            return -1;
        else
            return 1 + Math.max(height(node.left), height(node.right)); //คืนค่าเป็น 1 + ค่ามากสุดของ (ความสูงของ node ฝั่งซ้าย กับ ความสูงของ node ฝั่งขวา ของ node)
    }
}
```