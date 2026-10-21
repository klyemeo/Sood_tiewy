
public class Tree extends BTreePrinter {
  Node root;

  public Tree(Node root) {
    this.root = root;
  }

  public void printTree() {
    super.printTree(root);
    if (root == null)
      System.out.println("Empty tree!!!");
  }

  
  public static void printNode(Node node) {
    // เช็ค node เป็นค่าว่างหรือไม่ 
    if (node == null)
      System.out.println("Node not found!!!");
    else
      // แสดงค่าของ node
      System.out.println(node.key);
  }

  
  public Node findClosestLeaf(int search_key) {
    // คืนค่า เป็นการเรียก function  หาใบที่ใกล้ที่สุดจาก node ราก โดยอ้างอิงจาก search_key
    return findClosestLeaf(root, search_key);
  }

  
  public static Node findClosestLeaf(Node node, int search_key) {
    // ถ้าไม่มี node  จะคืนค่า null
    if (node == null)
      return null;

    // ถ้าเจอ ตัวเดียวกันกับที่อยากหา ให้คืนค่า node 
    if (node.key == search_key)
      return node;

    // เช็คว่าตัวที่เจอ มีค่ามากกว่าตัวที่อยากหาหรือไม่
    if (node.key < search_key) {
      // ถ้า node ฝั่งขวาเป็นค่าว่าง จะคืนค่า node 
      if (node.right == null)
        return node;
      // คืนค่าเป็นการเรียก function หาใบที่ใกล้ที่สุดจาก node ฝั่งขวา โดยอ้างอิงจาก search_key
      return findClosestLeaf(node.right, search_key);
    }

  
    // ถ้า node ฝั่งซ้ายเป็นค่าว่าง จะคืนค่า node
    if (node.left == null)
      return node;
    // คืนค่า เป็นการเรียก function หาใบที่ใกล้ที่สุดจาก node ฝั่งซ้าย โดยอ้างอิงจาก search_key
    return findClosestLeaf(node.left, search_key);
  }

 
  public void insertKey(int key) {
    // สร้าง node ใหม่จากคีย์ที่รับเข้ามา
    Node node = new Node(key);

    // เช็คว่า root เป็นค่าว่างไหม
    if (root == null) {
      // ให้ root เป็น node 
      root = node;
      return;
    }

    // ประกาศตัวแปร curr เป็น leafnode ที่ใกล้ที่สุดอิงจากคีย์ที่รับเข้ามา
    Node curr = findClosestLeaf(key);

    // เช็คว่า node curr มีค่าเท่ากับคีย์ที่รับเข้ามาไหม
    if (curr.key == key)
      System.out.println("Duplicated key!!!");
    // เช็คว่า node curr มีค่ามากกว่าคีย์ที่รับเข้ามาไหม
    else if (curr.key < key)
      //ให้ node curr ฝั่งขวาเป็น node
      curr.right = node;
    
    else
      //ให้ node curr ฝั่งซ้ายเป็น node
      curr.left = node;
  }

  
  public static int height(Node node) {
    // ถ้า node เป็นค่าว่าง 
    if (node == null)
      //คืนค่า -1 เพื่อบอกว่าไม่มีความสูง
      return -1;

    
    // คืนค่าความสูง คำนวณจากความสูงของกิ่งซ้ายและขวา โดยบวก 1 เพื่อรวม node ปัจจุบัน
    return 1 + Math.max(height(node.left), height(node.right));
  }

  
  public static int size(Node node) {
    //ถ้า node เป็นค่าว่าง 
    if (node == null)
      // คืนค่า 0 เพื่อบอกว่าไม่มี node
      return 0;

    // คืนค่าจำนวน node ทั้งหมด คำนวณจากจำนวน node ของกิ่งซ้ายและขวา โดยบวก 1 เพื่อรวม node ปัจจุบัน
    return 1 + size(node.left) + size(node.right);
  }

  public static int depth(Node root, Node node) {
    // เช็คว่า node หรือ root เป็นค่าว่างหรือไม่ 
    if (node == null || root == null)
      // คืนค่า -1 เพื่อบอกว่าไม่สามารถหาความลึกได้
      return -1;

    // ถ้า node มีค่าเท่ากับ root 
    if (node == root)
      // คืนค่า 0 
      return 0;

    // คืนค่าความลึกของ node โดย 1+ ความลึกตั้งแต่ node root ถึง parent node 
    return 1 + depth(root, node.parent);
  }

  public int treeHeight() {
    //คืนค่าความสูงของ root 
    return height(root);
  }

  public int treeSize() {
    //คืนค่าจำนวน node ทั้งหมดของ root
    return size(root);
  }

  public int treeDepth() {
    // คืนค่าความสูงของ root 
    return height(root);
  }

  public Node findKthSmallest(int k) {
    // คืนค่าเป็น node k ที่ มีค่าน้อยที่สุด
    return findKthSmallest(root, k);
  }

  public static Node findKthSmallest(Node node, int k) {
    // ถ้า node เป็นค่าว่างหรือ k น้อยกว่าหรือเท่ากับ 0 
    if (node == null || k <= 0)
      // คืนค่า ว่าง
      return null;

    // ประกาศ ตัวแปร lSize เพื่อเก็บจำนวน node ของกิ่งซ้าย
    int lSize = size(node.left);

    // ถ้า k น้อยกว่าหรือเท่ากับจำนวน node ฝั่งซ้าย
    if (k <= lSize)
      return findKthSmallest(node.left, k);

    // ถ้า k เท่ากับจำนวน node ฝั่งซ้าย + 1 
    if (k == lSize + 1)
      return node;

    // คืนค่าเป็นการเรียก function หา node k ที่ มีค่าน้อยที่สุดจาก node ฝั่งขวา ไปจนถึง k - lSize - 1
    return findKthSmallest(node.right, k - lSize - 1);
  }

  
  public Tree() {
  }
}