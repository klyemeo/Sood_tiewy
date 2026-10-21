public class BSTree extends BTreePrinter{
    Node root; //ให้ root เป็น Node ของ BSTree


  public static Node findKey(Node node, int search_key) {
    if (node == null || node.key == search_key) { //ตรวจสอบว่า node เป็น null หรือ key ของ node ตรงกับ search_key หรือไม่
      return node; // หากเป็นจริงให้ return node
    }
    if (search_key < node.key) { //ตรวจสอบว่า search_key น้อยกว่า key ของ node 
      return findKey(node.left, search_key); // หากเป็นจริงให้เรียกใช้ findKey กับ node ซ้าย
    } else {
      return findKey(node.right, search_key); // หากเป็นเท็จให้เรียกใช้ findKey กับ node ขวา
    }
  }

  public Node findKey(int search_key) { 
    return findKey(root, search_key); //เรียกใช้ findKey กับ root ของ BSTree
  }

  public static Node findMin(Node node) {
    if (node == null) //ตรวจสอบว่า node เป็น null หรือไม่
      return null; // หากเป็นจริงให้ return null
    if (node.left == null) //ตรวจสอบว่า ถ้า node ซ้ายเป็น null
      return node; // หากเป็นจริงให้ return node
    return findMin(node.left); // เรียกใช้ findMin กับ node ซ้าย
  }


  
  public static Node findMax(Node node) {
    if (node == null) //ตรวจสอบว่า node เป็น null หรือไม่
      return null; // หากเป็นจริงให้ return null
    if (node.right == null) //ตรวจสอบว่า ถ้า node ขวาเป็น null
      return node; // หากเป็นจริงให้ return node
    return findMax(node.right); // เรียกใช้ findMax กับ node ขวา
  }

  public static void insertKey(Node node, int key) {
    if (node == null) // ตรวจสอบว่า ถ้า node เป็น null หรือไม่
      return; // หากเป็นจริงให้ return

    if (key == node.key) { // ตรวจสอบว่า key ตรงกับ key ของ node หรือไม่
      System.out.println("Duplicated key!!!"); // หากเป็นจริงให้แสดงข้อความ "Duplicated key!!!"
    } else if (key < node.key) { // ตรวจสอบว่า ถ้า key น้อยกว่า key ของ node หรือไม่
      if (node.left == null) { // ตรวจสอบว่า ถ้า node ซ้ายเป็น null หรือไม่
        node.left = new Node(key); // หากเป็นจริงให้สร้าง node ซ้ายใหม่ด้วย key
        node.left.parent = node; // กำหนด parent ของ node ซ้ายเป็น node ปัจจุบัน
      } else { //
        insertKey(node.left, key); // หากเป็นเท็จให้เรียกใช้ insertKey กับ node ซ้าย
      }
    } else {
      if (node.right == null) { // ตรวจสอบว่า node ขวาเป็น null หรือไม่
        node.right = new Node(key); // หากเป็นจริงให้สร้าง node ขวาใหม่ด้วย key
        node.right.parent = node; //  กำหนด parent ของ node ขวาเป็น node ปัจจุบัน
      } else {
        insertKey(node.right, key); //  หากเป็นเท็จให้เรียกใช้ insertKey กับ node ขวา
      }
    }
  }

  public void insertKey(int key) {
    if (root == null) { // ตรวจสอบว่า root เป็น null หรือไม่
      root = new Node(key); // หากเป็นจริงให้สร้าง root ใหม่ด้วย key
    } else {
      insertKey(root, key); // หากเป็นเท็จให้เรียกใช้ insertKey กับ root
    }
  }

  public void deleteKey(int key) {
    Node node = findKey(key); // ให้ node เป็นค่าของ findKey จาก key ที่ต้องการ

    if (root == null) { // ตรวจสอบว่า root เป็น null หรือไม่
      System.out.println("Empty Tree!!!");
      return; 
    }
 
    if (node == null) { // ตรวจสอบว่า node เป็น null หรือไม่
      System.out.println("Key not found!!!");
      return;
    }

    if (node == root && (node.left == null || node.right == null)) { // ตรวจสอบว่า node เป็น root และ node ซ้ายหรือ node ขวาเป็น null หรือไม่
      root = (node.left != null) ? node.left : node.right; // หากเป็นจริงให้ root เป็น node ซ้ายหรือ node ขวา (ถ้า node ซ้ายไม่เป็น null ให้ใช้ node ซ้าย แต่ถ้า node ซ้ายเป็น null ให้ใช้ node ขวา)
      if (root != null) { // ตรวจสอบว่า root เป็น null หรือไม่
        root.parent = null; // ให้ root.parent เป็น null
      }
    } else { 
      deleteKey(node); // หากเป็นเท็จให้เรียกใช้ deleteKey กับ node
    }
  }

  public static void deleteKey(Node node) {
    if (node == null) // ตรวจสอบว่า node เป็น null หรือไม่
      return;

    if (node.left != null && node.right != null) { // ตรวจสอบว่า node ซ้ายและ node ขวาไม่เป็น null หรือไม่
      Node minRight = findMin(node.right); // ให้ minRight เป็นค่าของ findMin จาก node ขวา
      node.key = minRight.key; // ให้ key ของ node เป็น key ของ minRight
      deleteKey(minRight); // เรียกใช้ deleteKey กับ minRight
      return;
    }

    Node child = (node.left != null) ? node.left : node.right; // ให้ child เป็น node ซ้ายหรือ node ขวา (ถ้า node ซ้ายไม่เป็น null ให้ใช้ node ซ้าย แต่ถ้่า node ซ้ายเป็น null ให้ใช้ node ขวา)

    if (node.parent != null) { // ตรวจสอบว่า parent ของ node เป็น null หรือไม่
      if (node.parent.left == node) { // ตรวจสอบว่า node เป็นลูกซ้ายของ parent หรือไม่
        node.parent.left = child; // หากเป็นจริงให้ parent ของ node ซ้ายเป็น child
      } else {
        node.parent.right = child; // หากเป็นเท็จให้ parent ของ node ขวาเป็น child
      }
      if (child != null) { // ตรวจสอบว่า child เป็น null หรือไม่
        child.parent = node.parent; // หากเป็นจริงให้ parent ของ child เป็น parent ของ node
      }
    }
  }

  public static boolean isMergeable(Node r1, Node r2) {
    if (r1 == null || r2 == null) //  ตรวจสอบว่า r1 หรือ r2 เป็น null หรือไม่
      return true; // หากเป็นจริงให้ return true

    return findMax(r1).key < findMin(r2).key; // ตรวจสอบว่า key ของ node ที่มากที่สุดใน r1 น้อยกว่า key ของ node ที่น้อยที่สุดใน r2 หรือไม่
  }

  public static Node mergeWithRoot(Node r1, Node r2, Node t) {
    if (isMergeable(r1, r2)) { // ตรวจสอบว่า r1 และ r2 สามารถรวมกันได้หรือไม่
      if (t == null) // ตรวจสอบว่า t เป็น null หรือไม่
        return null; // หากเป็นจริงให้ return null

      t.left = r1; // ให้ t ซ้ายเป็น r1
      t.right = r2; // ให้ t ขวาเป็น r2
      t.parent = null; // ให้ parent ของ t เป็น null

      if (r1 != null) { // ตรวจสอบว่า r1 เป็น null หรือไม่
        r1.parent = t; // หากเป็นจริงให้ parent ของ r1 เป็น t
      }
      if (r2 != null) { // ตรวจสอบว่า r2 เป็น null หรือไม่
        r2.parent = t; // หากเป็นจริงให้ parent ของ r2 เป็น t
      }

      return t; // ให้ return t
    } else {
      System.out.println("All nodes in T1 must be smaller than all nodes from T2");
      return null; // ให้ return null
    }
  }

  public void merge(BSTree tree2) {
    if (tree2 == null) // ตรวจสอบว่า tree2 เป็น null หรือไม่
      return; // หากเป็นจริงให้ return

    if (isMergeable(this.root, tree2.root)) { // ตรวจสอบว่า root ของ tree1 และ root ของ tree2 สามารถรวมกันได้หรือไม่
      if (this.root == null) { // ตรวจสอบว่า root ของ tree1 เป็น null หรือไม่
        this.root = tree2.root; // หากเป็นจริงให้ root ของ tree1 เป็น root ของ tree2
        tree2.root = null; // ให้ root ของ tree2 เป็น null
        return;
      }
      if (tree2.root == null) { // ตรวจสอบว่า root ของ tree2 เป็น null หรือไม่
        return;
      }

      Node t = findMax(this.root); // ให้ t เป็นค่าของ findMax จาก root ของ tree1
      deleteKey(t.key); // ลบ node ที่มี key เท่ากับ key ของ t 

      this.root = mergeWithRoot(this.root, tree2.root, t); // ให้ root เป็นค่าของ mergeWithRoot จาก root ของ tree1, root ของ tree2 และ t
      tree2.root = null; // ให้ root ของ tree2 เป็น null
    } else {
      System.out.println("All nodes in T1 must be smaller than all nodes from T2");
    }
  }


    // This function is complete, no need to edit
    public void printTree() {
        if (root == null) { // ตรวจสอบว่า root เป็น null หรือไม่
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root); 
        }
    }
}
