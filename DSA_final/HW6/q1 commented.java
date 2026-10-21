public class Tree extends BTreePrinter { // Fix this //ประกาศ Class Tree
  Node root;//ประกาศตัวแปรชี้ไปยัง Node ราก 

  public Tree(Node root) {// รับ node root เป็น parameter
    // fix this
    this.root = root;// กำนหดค่า root ของ tree เป็น root ที่รับเข้ามา
  }

  public void printTree() {// สร้างฟังก์ชัน printTree() เพื่อแสดงผล tree
    super.printTree(root);// เรียกใช้ printTree ของคลาส BTreePrinter
    if (root == null)// ตรวจสอบเงื่อนไขว่าหากต้นไม้ว่างเปล่า
      System.out.println("Empty tree!!!");// แสดงข้อความว่าต้นไม้นี้ไม่มีข้อมูล
  }

  public Node findKey(int search_key) {// สร้างฟังก์ชันเพื่อค้นหา node ที่มีค่า key ตรงกับ search_key
    return findKey(root, search_key); // Call the recursive version
  }

  public static Node findKey(Node node, int search_key) {// recursive สำหรับค้นหาโหนดที่มีค่าคีย์ตามที่กำหนด
    // this function should be recursive
    // You should check null in this function
    if (node == null)// หากตำแหน่งโหนดปัจจุบันเป็น null แสดงว่าหาไม่พบ ให้คืนค่า null
      return null;

    if (node.key == search_key)// หากค่าคีย์ของโหนดปัจจุบันตรงกับค่าที่กำลังค้นหา
      return node;// ส่งคืนโหนดปัจจุบันที่เจอกลับไป

    if (node.key < search_key)// หากค่าคีย์ปัจจุบันน้อยกว่าค่าที่ค้นหา ข้อมูลที่มากกว่าจะอยู่ฝั่งขวา
      return findKey(node.right, search_key); // เรียกซ้ำ ไปค้นหาต่อใน subtree ฝั่งขวา

    return findKey(node.left, search_key);// หากค่าคีย์ปัจจุบันมากกว่าค่าที่ค้นหา แสดงว่าข้อมูลต้องอยู่ฝั่งซ้าย
// เรียกซ้ำ ไปค้นหาต่อใน subtree ฝั่งซ้าย
  }

  public Node findMin() {// สร้างฟังก์ชันเพื่อค้นหา node ที่มีค่าต่ำสุด
    return findMin(root); // Call the recursive version
  }

  public static Node findMin(Node node) {// recursive สำหรับค้นหาโหนดที่มีค่าต่ำสุด
    // this function should be recursive
    if (node == null)// หากตำแหน่งโหนดปัจจุบันเป็น null แสดงว่าหาไม่พบ ให้คืนค่า null
      return null;

    if (node.left == null)//หากไม่มีโหนดย่อยทางซ้ายแล้ว node.left เป็น null
      return node;// โหนดปัจจุบันคือโหนดที่มีค่าน้อยที่สุด ให้ส่งคืนโหนดนี้

    return findMin(node.left);//หากยังมีโหนดทางซ้าย ให้วิ่งลงไปทางซ้ายเรื่อยๆ
  }

  public Node findMax() {// ค้นหาโหนดที่มีค่ามากที่สุดในต้นไม้
    return findMax(root); // Call the recursive version
  }

  public static Node findMax(Node node) {// recursive สำหรับค้นหาโหนดที่มีค่ามากที่สุด
    // this function should be recursive
    if (node == null)//หากโหนดเริ่มต้นเป็น null ให้ส่งคืน null
      return null;

    if (node.right == null)// หากไม่มีโหนดย่อยทางขวาแล้ว node.right เป็น null)
      return node;// โหนดปัจจุบันคือโหนดที่มีค่ามากที่สุด ให้ส่งคืนโหนดนี้

    return findMax(node.right); // หากยังมีโหนดทางขวา ให้วิ่งลึกลงไปทางขวาเรื่อย ๆ
  }

  public Node findClosestLeaf(int search_key) {// สร้างฟังก์ชันเพื่อค้นหา node ใบไม้ตามเส้นทาง search_key
    return findClosestLeaf(root, search_key); // Call the recursive version
  }

  public static Node findClosestLeaf(Node node, int search_key) {
    // this function should be recursive
    if (node == null)//หากโหนดเป็น null ให้ส่งคืน null
      return null;

    if (node.key == search_key)//หากเจอค่าที่ตรงกันพอดี ให้ส่งคืนโหนดนั้นทันที
      return node;

    if (node.key < search_key) {//หากค่าที่ค้นหามากกว่าค่าของโหนดปัจจุบัน ให้ไปไปทางขวา
      if (node.right == null) // หากไม่มีทางไปต่อทางขวาแล้ว
        return node; // แสดงว่าโหนดปัจจุบันนี้คือโหนดสุดท้ายที่เป็นจุดสิ้นสุดเส้นทาง
      return findClosestLeaf(node.right, search_key); // หากยังมีลูกทางขวา ให้ค้นหาลึกลงไปทางขวาต่อ
    }

    if (node.left == null)//หากค่าที่ค้นหาน้อยกว่าค่าของโหนดปัจจุบัน ให้ไปไปทางซ้ายและตรวจสอบว่ามีทางไปต่อทางซ้ายหรือไม่ หากไม่มีลูกทางซ้าย
      return node;// โหนดปัจจุบันคือโหนดสุดท้ายของเส้นทาง
    return findClosestLeaf(node.left, search_key);// หากยังมีลูกทางซ้าย ให้ค้นหาลึกลงไปทางซ้ายต่อ
  }

  public Node findClosest(int search_key) {// ฟังก์ชั่นค้นหาโหนดที่มีค่าตัวเลขใกล้เคียงกับ search_key มากที่สุด โดยใช้ Loop 
    // Please use while loop to implement this function
    // Try not to use recursion
    Node current, closest;//ประกาศตัวแปรตัวชี้ current สำหรับเดินท่องต้นไม้ และ closest สำหรับเก็บโหนดที่มีค่าใกล้เคียงที่สุด
    closest = current = root;// กำหนดให้ทั้งสองตัวแปรชี้ไปที่โหนด root
    int min_diff = Integer.MAX_VALUE;// กำหนดค่าผลต่างน้อยที่สุดให้เป็นค่าสูงสุดของประเภท int

    // Use while loop to traverse from root to the closest leaf

    while (current != null) {//วนลูปสำรวจลงไปเรื่อย ๆ ถ้าโหนดปัจจุบันยังไม่เป็น null
      int diff = Math.abs(current.key - search_key);// คำนวณระยะห่างระหว่างค่าคีย์ของโหนดปัจจุบันกับค่าที่ต้องการค้นหา

      if (diff < min_diff) {// ถ้าผลต่างที่ได้น้อยกว่าผลต่างน้อยสุดเดิมที่บันทึกไว้
        min_diff = diff;// อัปเดตผลต่างใหม่
        closest = current;// บันทึกว่าโหนดปัจจุบันนี้คือโหนดที่มีค่าใกล้เคียงที่สุดตอนนี้
      }

      if (diff == 0)// หากผลต่างเป็น 0 แสดงว่าเจอค่าที่เท่ากันทั้งหมด ให้หยุดลูปทันที
        break;

      if (search_key < current.key)// หากค่าที่ค้นหาน้อยกว่าค่าคีย์ปัจจุบัน ให้ขยับตัวชี้ไปทางซ้าย
        current = current.left;
      else // หากค่าที่ค้นหามากกว่าค่าคีย์ปัจจุบัน ให้ขยับตัวชี้ไปทางขวา
        current = current.right;
    }

    return closest;// ส่งคืนโหนดที่มีค่าคีย์ใกล้เคียง search_key ที่สุด
  }

  // Print node's key
  public static void printNode(Node node) {//ฟังก์ชั่นสำหรับแสดงค่าคีย์ของโหนดที่ค้นพบ
    if (node == null)// ตรวจสอบว่าตัวแปรโหนดเป็น null หรือไม่
      System.out.println("Node not found!!!");// หากเป็น null แสดงข้อความว่าหาไม่พบ
    else//หากมีโหนดอยู่จริง ให้พิมพ์ค่าตัวเลขคีย์ของโหนดนั้นออกทางหน้าจอ
      System.out.println(node.key);
  }

  public Tree() {
  } // Dummy constructor (No need to edit)
}