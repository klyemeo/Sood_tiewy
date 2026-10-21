public class AVLTree extends BTreePrinter{
    Node root; // ให้ root เป็น Node ของ AVLTree

    public AVLTree(Node root){
        this.root = root; // กำหนด root ของ AVLTree เป็น root
        root.parent = null; // Clear parent of the root (Important for spliting) // กำหนด parent ของ root เป็น null
    }
    

  public void singleRotateFromLeft(Node y) {
    if (y == null || y.left == null) // ตรวจสอบว่า node y เป็น null หรือ node ซ้ายของ y เป็น null หรือไม่
      return; // หากเป็นจริงให้ return

    Node x = y.left; // กำหนด node x เป็น node ซ้ายของ y
    Node w = y.parent; // กำหนด node w เป็น parent ของ y

    Node inner = x.right; // กำหนด node inner เป็น node ขวาของ x
    y.left = inner; // กำหนด node ซ้ายของ y เป็น node inner
    if (inner != null) // ตรวจสอบว่า node inner เป็น null หรือไม่
      inner.parent = y; // ให้parent ของ node inner เป็น y

    x.parent = w; // ให้ parent ของ node x เป็น w
    if (w == null) { // ตรวจสอบว่า node w เป็น null หรือไม่
      root = x; // หากเป็นจริงให้กำหนด root ของ AVLTree เป็น x
    } else {
      if (w.left == y) { // ตรวจสอบว่า node ซ้ายของ w เป็น y หรือไม่
        w.left = x; // หากเป็นจริงให้กำหนด node ซ้ายของ w เป็น x
      } else {
        w.right = x; // หากเป็นเท็จให้กำหนด node ขวาของ w เป็น x
      }
    }

    x.right = y; // ให้ node ขวาของ x เป็น y
    y.parent = x; // ให้ parent ของ node y เป็น x
  }

  public void singleRotateFromRight(Node y) {
    if (y == null || y.right == null) // ตรวจสอบว่า node y เป็น null หรือ node ขวาของ y เป็น null หรือไม่
      return;

    Node x = y.right; // กำหนด node x เป็น node ขวาของ y
    Node w = y.parent; // กำหนด node w เป็น parent ของ y

    Node inner = x.left; // กำหนด node inner เป็น node ซ้ายของ x
    y.right = inner; // ให้ node ขวาของ y เป็น node inner
    if (inner != null) // ตรวจสอบว่า node inner เป็น null หรือไม่
      inner.parent = y; // ให้ parent ของ node inner เป็น y

    x.parent = w; // ให้ parent ของ node x เป็น w
    if (w == null) { // ตรวจสอบว่า node w เป็น null หรือไม่
      root = x; // หากเป็นจริงให้กำหนด root เป็น x
    } else { 
      if (w.left == y) {  // ตรวจสอบว่า node ซ้ายของ w เป็น y หรือไม่
        w.left = x; // หากเป็นจริงให้กำหนด node ซ้ายของ w เป็น x
      } else { 
        w.right = x; // หากเป็นเท็จให้กำหนด node ขวาของ w เป็น x
      }
    }

    x.left = y; // ให้ node ซ้ายของ x เป็น y
    y.parent = x; // ให้ parent ของ node y เป็น x
  }

  public void doubleRotateFromLeft(Node y) {
    if (y == null || y.left == null || y.left.right == null) // ตรวจสอบว่า node y เป็น null หรือ node ซ้ายของ y เป็น null หรือ node ขวาของ node ซ้ายของ y เป็น null หรือไม่
      return;

    singleRotateFromRight(y.left); // เรียกใช้ singleRotateFromRight กับ node ซ้ายของ y
    singleRotateFromLeft(y); // ใช้singleRotateFromLeft กับ node y
  }

  public void doubleRotateFromRight(Node y) {
    if (y == null || y.right == null || y.right.left == null) // ตรวจสอบว่า node y เป็น null หรือ node ขวาของ y เป็น null หรือ node ซ้ายของ node ขวาของ y เป็น null หรือไม่
      return;

    singleRotateFromLeft(y.right); // ใช้ singleRotateFromLeft กับ node ขวาของ y
    singleRotateFromRight(y); // ใช้ singleRotateFromRight กับ node y
  }

  public static void rebalance(AVLTree tree, Node node) {
    Node cur = node; // กำหนด node cur เป็น node ปัจจุบัน
    while (cur != null) { // ตรวจสอบว่า node cur เป็น null หรือไม่
      Node parent = cur.parent; // กำหนด node parent เป็น parent ของ node cur
      int balanceFactor = height(cur.left) - height(cur.right); // ให้ balanceFactor เป็นความสูงของ node ซ้ายของ cur ลบด้วยความสูงของ node ขวาของ cur

      if (Math.abs(balanceFactor) > 1) { // ตรวจสอบว่า balanceFactor มีค่าสัมบูรณ์มากกว่า 1 หรือไม่
        if (balanceFactor > 1) { // ตรวจสอบว่า balanceFactor มากกว่า 1 หรือไม่
          if (height(cur.left.left) >= height(cur.left.right)) { // ตรวจสอบว่า ความสูงของ node ซ้ายของ node ซ้ายของ cur มากกว่าหรือเท่ากับความสูงของ node ขวาของ node ซ้ายของ cur หรือไม่
            System.out.println("Perform SingleRotationFromLeft(Node " + cur.key + ")");
            tree.singleRotateFromLeft(cur); // หากเป็นจริงให้ใช้ singleRotateFromLeft กับ node cur
          } else {
            System.out.println("Perform DoubleRotationFromLeft(Node " + cur.key + ")");
            tree.doubleRotateFromLeft(cur); // หากเป็นเท็จให้ใช้ doubleRotateFromLeft กับ node cur
          }
        } else {
          if (height(cur.right.right) >= height(cur.right.left)) { // ตรวจสอบว่า ความสูงของ node ขวาของ node ขวาของ cur มากกว่าหรือเท่ากับความสูงของ node ซ้ายของ node ขวาของ cur หรือไม่
            System.out.println("Perform SingleRotationFromRight(Node " + cur.key + ")");
            tree.singleRotateFromRight(cur); // หากเป็นจริงให้ใช้ singleRotateFromRight กับ node cur
          } else {
            System.out.println("Perform DoubleRotationFromRight(Node " + cur.key + ")");
            tree.doubleRotateFromRight(cur); // หากเป็นเท็จให้ใช้ doubleRotateFromRight กับ node cur
          }
        }
      }
      cur = parent; // ให้ node cur เป็น parent ของ node cur
    }
  }
  
  public Node findKey(int search_key) {
    return findKey(root, search_key); // Call the recursive version // เรียกใช้ findKey กับ root และ search_key
  }

  public static Node findKey(Node node, int search_key) {
    // this function should be recursive
    // You should check null in this function
    if (node == null || node.key == search_key) // ตรวจสอบว่า node เป็น null หรือ key ของ node ตรงกับ search_key หรือไม่
      return node; // หากเป็นจริงให้ return node
    if (node.key == search_key) // ตรวจสอบว่า key ของ node ตรงกับ search_key หรือไม่
      return node; // หากเป็นจริงให้ return node

    if (node.key < search_key) // ตรวจสอบว่า key ของ node น้อยกว่า search_key หรือไม่
      return findKey(node.right, search_key); // หากเป็นจริงให้เรียกใช้ findKey กับ node ขวา

    return findKey(node.left, search_key); // ให้เรียกใช้ findKey กับ node ซ้าย และ search_key

  }

  public static Node findMin(Node node) {
    // this function should be recursive
    if (node == null) // ตรวจสอบว่า node เป็น null หรือไม่
      return null; // หากเป็นจริงให้ return null

    if (node.left == null) // ตรวจสอบว่า node ซ้ายเป็น null หรือไม่
      return node; // หากเป็นจริงให้ return node
 
    return findMin(node.left); // ให้เรียกใช้ findMin กับ node ซ้าย
  }

  public Node findMax() {
    return findMax(root); // Call the recursive version // เรียกใช้ findMax กับ root
  }

  public static Node findMax(Node node) {
    // this function should be recursive
    if (node == null) // ตรวจสอบว่า node เป็น null หรือไม่
      return null; // หากเป็นจริงให้ return null

    if (node.right == null) // ตรวจสอบว่า node ขวาเป็น null หรือไม่
      return node; // หากเป็นจริงให้ return node

    return findMax(node.right); // ให้เรียกใช้ findMax กับ node ขวา
  }


 public void insertKey(int key) {
    if (root == null) { // ตรวจสอบว่า root เป็น null หรือไม่
      root = new Node(key); // หากเป็นจริงให้สร้าง node ใหม่ด้วย key และกำหนดเป็น root
    } else {
      insertKey(this, root, key); // หากเป็นเท็จให้เรียกใช้ insertKey กับ root และ key
    } 
  }

  // Fix this function to have the rebalancing feature
  // There should be rebalance() function calling somewhere in the code
  public static void insertKey(AVLTree tree, Node node, int key) {
    if (key == node.key) { // ตรวจสอบว่า key ตรงกับ key ของ node หรือไม่
      System.out.println("Duplicated key:" + key);
    } else if (key < node.key) {// Go left // ตรวจสอบว่า key น้อยกว่า key ของ node หรือไม่
      if (node.left == null) { // ตรวจสอบว่า node ซ้ายเป็น null หรือไม่
        node.left = new Node(key); // หากเป็นจริงให้สร้าง node ซ้ายใหม่ด้วย key
        node.left.parent = node; // กำหนด parent ของ node ซ้ายเป็น node ปัจจุบัน
      } else {
        insertKey(tree, node.left, key); // หากเป็นเท็จให้เรียกใช้ insertKey กับ node ซ้าย
      }
    } else { // Go right
      if (node.right == null) { // ตรวจสอบว่า node ขวาเป็น null หรือไม่
        node.right = new Node(key); // หากเป็นจริงให้สร้าง node ขวาใหม่ด้วย key
        node.right.parent = node; // กำหนด parent ของ node ขวาเป็น node ปัจจุบัน
      } else {
        insertKey(tree, node.right, key); // หากเป็นเท็จให้เรียกใช้ insertKey กับ node ขวา
      }
    }
    rebalance(tree, node); // เรียกใช้ rebalance กับ tree และ node
  }


    
 public void deleteKey(int key) {
    Node node = findKey(key); // เรียกใช้ findKey กับ key และกำหนดผลลัพธ์เป็น node

    if (root == null) { // ตรวจสอบว่า root เป็น null หรือไม่
      System.out.println("Empty Tree!!!");
      return;
    }

    if (node == null) { // ตรวจสอบว่า node เป็น null หรือไม่
      System.out.println("Key not found!!!");
      return;
    }

    if (node == root) { // ตรวจสอบว่า node เป็น root หรือไม่
      if (node.left == null && node.right == null) { // ตรวจสอบว่า node ซ้ายและ node ขวาเป็น null หรือไม่
        root = null; // หากเป็นจริงให้กำหนด root เป็น null

      } else if (node.right == null) { // ตรวจสอบว่า node ขวาเป็น null หรือไม่
        root = node.left; // หากเป็นจริงให้กำหนด root เป็น node ซ้าย
        root.parent = null; // กำหนด parent ของ root เป็น null
        rebalance(this, root); // เรียกใช้ rebalance กับ tree และ root

      } else if (node.left == null) { // ตรวจสอบว่า node ซ้ายเป็น null หรือไม่
        root = node.right; // หากเป็นจริงให้กำหนด root เป็น node ขวา
        root.parent = null; // กำหนด parent ของ root เป็น null
        rebalance(this, root); // เรียกใช้ rebalance กับ tree และ root

      } else {
        Node min = findMin(node.right); // กำหนดให่ min เป็น node ที่มีค่าต่ำที่สุดจากทางขวาของ node
        node.key = min.key; // กำหนด key ของ node เป็น key ของ min
        deleteKey(this, min); // เรียกใช้ deleteKey กับ tree และ min
      }
    } else {
      deleteKey(this, node); // เรียกใช้ deleteKey กับ tree และ node
    }
  }

  // Use this function to delete non-root nodes
  // Also, fix the code to have the rebalancing feature
  public static void deleteKey(AVLTree tree, Node node) {
    if (node == null || node.parent == null) // ตรวจสอบว่า node เป็น null หรือ parent ของ node เป็น null หรือไม่
      return;

    if (node.left != null && node.right != null) { // ตรวจสอบว่า node ซ้ายและ node ขวาไม่เป็น null หรือไม่
      Node min = findMin(node.right); // กำหนดให้ min เป็น node ที่มีค่าต่ำที่สุดจากทางขวาของ node
      node.key = min.key; // กำหนด key ของ node เป็น key ของ min
      deleteKey(tree, min); // เรียกใช้ deleteKey กับ tree และ min
      return;
    }

    Node parent = node.parent; // กำหนด node parent เป็น parent ของ node

    if (node.left == null && node.right == null) { // ตรวจสอบว่า node ซ้ายและ node ขวาเป็น null หรือไม่
      if (parent.left == node) // ตรวจสอบว่า node ซ้ายของ parent เป็น node หรือไม่
        parent.left = null; // หากเป็นจริงให้กำหนด node ซ้ายของ parent เป็น null
      else
        parent.right = null; // หากเป็นเท็จให้กำหนด node ขวาของ parent เป็น null

    } else if (node.right == null) { // ตรวจสอบว่า node ขวาเป็น null หรือไม่
      if (parent.left == node) // ตรวจสอบว่า node ซ้ายของ parent เป็น node หรือไม่
        parent.left = node.left; // หากเป็นจริงให้กำหนด node ซ้ายของ parent เป็น node ซ้ายของ node
      else
        parent.right = node.left; // หากเป็นเท็จให้กำหนด node ขวาของ parent เป็น node ซ้ายของ node

      node.left.parent = parent; // กำหนด parent ของ node ซ้ายของ node เป็น parent ของ node

    } else {
      if (parent.left == node) // ตรวจสอบว่า node ซ้ายของ parent เป็น node หรือไม่
        parent.left = node.right; // หากเป็นจริงให้กำหนด node ซ้ายของ parent เป็น node ขวาของ node
      else
        parent.right = node.right; // หากเป็นเท็จให้กำหนด node ขวาของ parent เป็น node ขวาของ node

      node.right.parent = parent; // กำหนด parent ของ node ขวาของ node เป็น parent ของ node
    }

    rebalance(tree, parent); // เรียกใช้ rebalance กับ tree และ parent ของ node
  }


    

  public static boolean isMergeable(Node r1, Node r2) {
    if (r1 == null || r2 == null) // ตรวจสอบว่า r1 หรือ r2 เป็น null หรือไม่
      return true; // หากเป็นจริงให้ return true

    return findMax(r1).key < findMin(r2).key; // return การเปรียบเทียบ key ของ node ที่มากที่สุดใน r1 น้อยกว่า key ของ node ที่น้อยที่สุดใน r2 หรือไม่
  }

  public static Node mergeWithRoot(Node r1, Node r2, Node t) {
    if (!isMergeable(r1, r2)) { // ตรวจสอบว่า r1 และ r2 สามารถรวมกันได้หรือไม่
      System.out.println("All nodes in T1 must be smaller than all nodes from T2");
      return null; // หากเป็นเท็จให้ return null
    }

    t.left = null; // ให้ node ซ้ายของ t เป็น null
    t.right = null; // ให้ node ขวาของ t เป็น null
    t.parent = null; // ให้ parent ของ t เป็น null

    int h1 = height(r1); // ให้ h1 เป็นความสูงของ r1
    int h2 = height(r2); // ให้ h2 เป็นความสูงของ r2

    if (Math.abs(h1 - h2) <= 1) { // ตรวจสอบว่าความสูงของ r1 และ r2 มีค่าสัมบูรณ์ต่างกันไม่เกิน 1 หรือไม่
      t.left = r1; // ให้ node ซ้ายของ t เป็น r1
      t.right = r2; // ให้ node ขวาของ t เป็น r2
      if (r1 != null) // ตรวจสอบว่า r1 เป็น null หรือไม่
        r1.parent = t; // หากเป็นจริงให้ parent ของ r1 เป็น t
      if (r2 != null) // ตรวจสอบว่า r2 เป็น null หรือไม่
        r2.parent = t; // หากเป็นจริงให้ parent ของ r2 เป็น t
      return t;
    }

    if (h1 > h2 + 1) { // ตรวจสอบว่าความสูงของ r1 มากกว่าความสูงของ r2 + 1 หรือไม่
      Node cur = r1; // กำหนด node cur เป็น r1
      Node parent = null; // กำหนด node parent เป็น null

      while (cur != null && height(cur) > h2 + 1) { // ตรวจสอบว่า node cur เป็น null หรือความสูงของ node cur มากกว่าความสูงของ r2 + 1 หรือไม่
        parent = cur; // กำหนด node parent เป็น node cur
        cur = cur.right; // กำหนด node cur เป็น node ขวาของ cur
      }

      t.left = cur; // ให้ node ซ้ายของ t เป็น cur
      if (cur != null) // ตรวจสอบว่า cur เป็น null หรือไม่
        cur.parent = t; // หากเป็นจริงให้ parent ของ cur เป็น t
      t.right = r2; // ให้ node ขวาของ t เป็น r2
      if (r2 != null) // ตรวจสอบว่า r2 เป็น null หรือไม่
        r2.parent = t; // หากเป็นจริงให้ parent ของ r2 เป็น t

      t.parent = parent; // ให้ parent ของ t เป็น parent
      if (parent != null) // ตรวจสอบว่า parent เป็น null หรือไม่
        parent.right = t; // หากเป็นจริงให้ node ขวาของ parent เป็น t

      AVLTree tree = new AVLTree(); // สร้าง AVLTree ใหม่
      tree.root = r1; // กำหนด root ของ tree เป็น r1
      Node curr = t; // กำหนด node curr เป็น t
      while (curr != null) { // ตรวจสอบว่า node curr เป็น null หรือไม่
        Node next = curr.parent; // กำหนด node next เป็น parent ของ curr
        rebalance(tree, curr); // เรียกใช้ rebalance กับ tree และ curr
        curr = next; // กำหนด node curr เป็น next
      }

    } else {
      Node cur = r2; // กำหนด node cur เป็น r2
      Node parent = null; // กำหนด node parent เป็น null

      while (cur != null && height(cur) > h1 + 1) { // ตรวจสอบว่า node cur เป็น null หรือความสูงของ node cur มากกว่าความสูงของ r1 + 1 หรือไม่
        parent = cur; // กำหนด node parent เป็น node cur
        cur = cur.left; // กำหนด node cur เป็น node ซ้ายของ cur
      }

      t.left = r1; // ให้ node ซ้ายของ t เป็น r1
      if (r1 != null) // ตรวจสอบว่า r1 เป็น null หรือไม่
        r1.parent = t; // หากเป็นจริงให้ parent ของ r1 เป็น t
      t.right = cur; // ให้ node ขวาของ t เป็น cur
      if (cur != null) // ตรวจสอบว่า cur เป็น null หรือไม่
        cur.parent = t; // หากเป็นจริงให้ parent ของ cur เป็น t

      t.parent = parent; // ให้ parent ของ t เป็น parent
      if (parent != null) // ตรวจสอบว่า parent เป็น null หรือไม่
        parent.left = t; // หากเป็นจริงให้ node ซ้ายของ parent เป็น t

      AVLTree tree = new AVLTree(); // สร้าง AVLTree ใหม่
      tree.root = r2; // กำหนด root ของ tree เป็น r2
      Node curr = t; // กำหนด node curr เป็น t
      while (curr != null) { // ตรวจสอบว่า node curr เป็น null หรือไม่
        Node next = curr.parent; // กำหนด node next เป็น parent ของ curr
        rebalance(tree, curr); // เรียกใช้ rebalance กับ tree และ curr
        curr = next; // กำหนด node curr เป็น next node
      }
    }

    Node root = t; // กำหนด node root เป็น t
    while (root.parent != null) { // ตรวจสอบว่า parent ของ root เป็น null หรือไม่
      root = root.parent; //  กำหนด node root เป็น parent ของ root
    }
    return root; // return root
  }
  
  // Fix this function
  public Node[] split(int key) {
    return split(this.root, key); // ใช้ split กับ root และ key
  }

  // Fix this function

  public static Node[] split(Node node, int key) {
    if (node == null) { // ตรวจสอบว่า node เป็น null หรือไม่
      return new Node[] { null, null }; // หากเป็นจริงให้ return array ของ Node ที่มีค่า null 
    }

    Node leftChild = node.left; // กำหนด node leftChild เป็น node ซ้ายของ node
    Node rightChild = node.right; // กำหนด node rightChild เป็น node ขวาของ node
    if (leftChild != null) // ตรวจสอบว่า leftChild เป็น null หรือไม่
      leftChild.parent = null; // หากเป็นจริงให้ parent ของ leftChild เป็น null
    if (rightChild != null) // ตรวจสอบว่า rightChild เป็น null หรือไม่
      rightChild.parent = null; // หากเป็นจริงให้ parent ของ rightChild เป็น null
    node.left = null; // ให้ node ซ้ายของ node เป็น null
    node.right = null; // ให้ node ขวาของ node เป็น null
    node.parent = null; // ให้ parent ของ node เป็น null

    if (key < node.key) { // ตรวจสอบว่า key น้อยกว่า key ของ node หรือไม่
      Node[] res = split(leftChild, key); // หากเป็นจริงให้เรียกใช้ split กับ leftChild และ key
      Node rightTree = mergeWithRoot(res[1], rightChild, node); // ให้ rightTree เป็นค่าของการ mergeWithRoot กับ res[1], rightChild และ node
      return new Node[] { res[0], rightTree }; // return array ของ Node ที่มีค่า res[0] และ rightTree
    } else {
      Node[] res = split(rightChild, key); // หากเป็นเท็จให้เรียกใช้ split กับ rightChild และ key
      Node leftTree = mergeWithRoot(leftChild, res[0], node); ..do {  // ให้ leftTree เป็นค่าของการ mergeWithRoot กับ leftChild, res[0] และ node
          
      } while (true); 
      return new Node[] { leftTree, res[1] }; // return array ของ Node ที่มีค่า leftTree และ res[1]
    }
  }



    // This function is complete, no need to edit
    public static int height(Node node){ 
        if (node == null) // ตรวจสอบว่า node เป็น null หรือไม่
            return -1; // หากเป็นจริงให้ return -1
        else
            return 1 + Math.max(height(node.left), height(node.right)); // return 1 + ค่ามากที่สุดของความสูงของ node ซ้ายและ node ขวา
    }

    // This function is complete, no need to edit
    public void printTree() { 
        if (root == null) { // ตรวจสอบว่า root เป็น null หรือไม่
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root); 
        }
    }
    
    public AVLTree() {} // Dummy Constructor, no need to edit

}