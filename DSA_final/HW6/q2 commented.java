public class Tree extends BTreePrinter { // Fix this //ประกาศ Class Tree
  Node root;//ประกาศตัวแปรชี้ไปยัง Node ราก 

  public Tree(Node root) {// รับ node root เป็น parameter
    // fix this
    this.root = root;// กำหนดค่า root ของ tree เป็น root ที่รับเข้ามา
  }

  public void printTree() {// สร้างฟังก์ชัน printTree() เพื่อแสดงผล tree
    super.printTree(root);// เรียกใช้ printTree ของคลาส BTreePrinter
    if (root == null)// ตรวจสอบเงื่อนไขว่าหากต้นไม้ว่างเปล่า
      System.out.println("Empty tree!!!");// แสดงข้อความว่าต้นไม้นี้ไม่มีข้อมูล
  }
  public static void printNode(Node node) {//ฟังก์ชันสำหรับพิมพ์ค่า key ของโหนดที่ส่งเข้ามา
    if (node == null)// ตรวจสอบว่าตัวแปรโหนดชี้ไปที่ null หรือไม่
      System.out.println("Node not found!!!");// หากเป็น null แสดงข้อความว่าไม่พบโหนด
    else// หากมีโหนดอยู่จริง ให้พิมพ์ค่า key ของโหนดนั้น
      System.out.println(node.key);
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

  public void insertKey(int key) {// ส้รางฟังก์ชั่นสำหรับแทรกค่า key ลงในต้นไม้
    // Implement insertKey() using the non-recursive version
    // This function should call findClosestLeaf()
    Node node = new Node(key);// สร้างโหนดใหม่มารองรับค่า key ที่ส่งเข้ามา

    if (root == null) {// หากต้นไม้ยังไม่มีข้อมูลเล
      root = node;// กำหนดให้โหนดใหม่เป็นโหนดรากทันที
      return;
    }

    Node curr = findClosestLeaf(key);// ค้นหาโหนดตำแหน่งสุดท้ายที่โหนดใหม่ควรไปต่อเป็นลูก

    if (curr.key == key)// หากพบว่าค่า key ที่ต้องการแทรกมีอยู่ในต้นไม้แล้ว
      System.out.println("Duplicated key!!!");// แสดงข้อความว่ามีค่านี้อยู่แล้ว และไม่แทรกซ้ำ
    else if (curr.key < key)// หากค่าที่ต้องการแทรกมากกว่าค่าของโหนดแม่
      curr.right = node;// เชื่อมต่อโหนดใหม่เข้าเป็นลูกทางขวาของโหนดแม่
    else// หากค่าที่ต้องการแทรกน้อยกว่าค่าของโหนดแม่
      curr.left = node;// เชื่อมต่อโหนดใหม่เข้าเป็นลูกทางซ้ายของโหนดแม่
  }

  public void printPreOrderDFT() {// ฟังก์ชันเพื่อพิมพ์ลำดับโหนดแบบ PreOrder
    System.out.print("PreOrder DFT node sequence [ ");// พิมพ์ข้อความนำทางก่อนเริ่มแสดงลำดับโหนด
    // Call the recursive version
    printPreOrderDFT(root);//recursive เพื่อพิมพ์ต้นไม้เริ่มจากโหนด root
    System.out.println("]");
  }

  public static void printPreOrderDFT(Node node) {// ฟังก์ชั่น recursive พิมพ์ลำดับโหนดแบบ Pre-order
    // this function should be recursive
    if (node == null)// หากตำแหน่งโหนดเป็น null ให้หยุดการทำงานแล้วย้อนกลับ
      return;

    System.out.print(node.key + " ");// พิมพ์ค่าของโหนดปัจจุบันก่อน
    printPreOrderDFT(node.left);//เดินลึกลงไปยัง subtree ฝั่งซ้าย
    printPreOrderDFT(node.right);//เดินลึกลงไปยัง subtree ฝั่งขวา
  }

  public void printInOrderDFT() {// ฟังก์ชันเพื่อพิมพ์ลำดับโหนดแบบ InOrder
    System.out.print("InOrder DFT node sequence [ ");// พิมพ์ข้อความนำทางก่อนเริ่มแสดงลำดับโหนด
    // Call the recursive version
    printInOrderDFT(root);//recursive เพื่อพิมพ์ต้นไม้เริ่มจากโหนด root
    System.out.println("]");
  }

  public static void printInOrderDFT(Node node) {// ฟังก์ชัน recursive พิมพ์ลำดับโหนดแบบ In-order
    // this function should be recursive
    if (node == null)// หากตำแหน่งโหนดเป็น null ให้หยุดการทำงานแล้วย้อนกลับ
      return;

    printInOrderDFT(node.left);//เดินลึกลงไปยัง subtree ฝั่งซ้ายให้สุดก่อน
    System.out.print(node.key + " ");//พิมพ์ค่าของโหนดปัจจุบัน
    printInOrderDFT(node.right);//เดินลึกลงไปยัง subtree ฝั่งขวา
  }

  public void printPostOrderDFT() {// ฟังก์ชันเพื่อพิมพ์ลำดับโหนดแบบ PostOrder
    System.out.print("PostOrder DFT node sequence [ ");// พิมพ์ข้อความนำทางก่อนเริ่มแสดงลำดับโหนด
    // Call the recursive version
    printPostOrderDFT(root);//recursive เพื่อพิมพ์ต้นไม้เริ่มจากโหนด root
    System.out.println("]");
  }

  public static void printPostOrderDFT(Node node) {// ฟังก์ชัน recursive พิมพ์ลำดับโหนดแบบ Post-order
    // this function should be recursive
    if (node == null)// หากตำแหน่งโหนดเป็น null ให้หยุดการทำงานแล้วย้อนกลับ
      return;

    printPostOrderDFT(node.left);//เดินลึกลงไปยัง subtree ฝั่งซ้ายให้สุดก่อน
    printPostOrderDFT(node.right);//เดินลึกลงไปยัง subtree ฝั่งขวาให้เสร็จ
    System.out.print(node.key + " ");//พิมพ์ค่าของโหนดปัจจุบัน
  }

  public Tree() {
  } // Dummy constructor (No need to edit)
}