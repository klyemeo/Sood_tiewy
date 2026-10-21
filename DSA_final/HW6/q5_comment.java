public class Tree extends BTreePrinter { 
  Node root; // เก็บ root node ของต้นไม้

  public Tree(Node root) { 
    // fix this
    this.root = root; // ให้ root เท่ากับ root node ที่ส่งเข้ามา
  }

  public void printTree() { 
    super.printTree(root); 
    if (root == null) //ตรวจสอบว่า root มีค่าว่างหรือไม้
      System.out.println("Empty tree!!!"); 
  }

  public Node findKey(int search_key) { 
    return findKey(root, search_key); // recursive เพื่อค้นหาจาก root node
    // Call the recursive version 
  }

  public static Node findKey(Node node, int search_key) {
    // this function should be recursive 
    // You should check null in this function 
    if (node == null) // ตรวจสอบว่า node มีค่าว่างหรือไม่
      return null; // คืนค่าว่าง

    if (node.key == search_key) // ตรสวจสอบว่า key ของ node ตรงกับ key ที่ต้องการหรือไม่
      return node; // คืนค่า node 

    if (node.key < search_key) // ถ้า key ของ nodeน้อยกว่าค่าที่ต้องการหรือไม่
      return findKey(node.right, search_key); // ค้นหาทางขวาของ node ต่อไป

    return findKey(node.left, search_key); // ค้นหาทางซ้ายของ node ต่อไป
  }

  public Node findMin() { // function หาค่าต่ำสุดในต้นไม้
    return findMin(root); // recursive เพื่อค้นหาค่าที่น้อยที่สุดจาก root node
  }

  public static Node findMin(Node node) { 
    if (node == null) // ตรวจสอบว่า node มีค่าว่างหรือไม่
      return null; // คืนค่า null

    if (node.left == null) // ถ้า node ไม่มีลูกทางซ้าย
      return node; // คืนค่า node

    return findMin(node.left); // ค้นหาจากทางซ้าย เพื่อหาค่าที่น้อยที่สุด
  }

  public Node findMax() { // function  หาค่าสูงสุดในต้นไม้
    return findMax(root); // recursive เพื่อค้นหาค่าสูงสุดจาก root node
  }

  public static Node findMax(Node node) { // ฟังก์ชัน recursive สำหรับค้นหาค่าสูงสุด
    // this function should be recursive 
    if (node == null) // ตรวจสอบว่า node มีค่าว่างหรือไม่
      return null; // คืนค่า null

    if (node.right == null) // ตรวจสอบว่า node ไม่มีลูกทางขวา
      return node; // คืนค่า node

    return findMax(node.right); // ค้นหาจากทางขวา เพื่อหาค่าสูงสุด
  }

  public void deleteKey(int key) { 
    // There should be 6 cases here 
    // Non-root nodes should be forwarded to the static function 
    Node node = findKey(key); // ให้ node เป็น node ที่มี key ตรงกับค่าที่ต้องการจะลบ

    if (root == null) { // ตรวจสอบว่า root มีค่าว่างหรือไม่
      System.out.println("Empty Tree!!!"); 
      return; 
    }

    if (node == null) { // ตรวจสอบว่า node มีค่าว่างหรือไม่
      System.out.println("Key not found!!!"); 
      return; 
    }

    if (node == root) { // ตรวจสอบว่า node เป็น root หรือไม่
      if (node.left == null && node.right == null) // ตรวจสอบว่า node ไม่มีลูกทั้งสองทางหรือไม่
        root = null; // ให้ root เป็น null

      else if (node.right == null) { // ตรวจสอบว่า node ทางขวาเป็น null หรือไม่
        root = node.left; // ให้ root เป็นลูกซ้ายของ node
        root.parent = null; // ให้parent ของรากใหม่เป็น null
      } else if (node.left == null) { // ตรวจสอบว่า node ทางซ้ายเป็น null หรือไม่
        root = node.right; // ให้ root เป็นลูกขวาของ node
        root.parent = null; // ให่้ parent ของรากใหม่เป็น null
      } else { 
        Node min = findMin(node.right); // ให้ min เป็น node ที่มีค่าน้อยที่สุดในกิ่งขวาของราก
        node.key = min.key; // ให้ key ของ node เท่ากับค่าน้อยสุดในกิ่งขวา
        deleteKey(min); // ลบ node ที่มีค่าน้อยสุดในกิ่งขวา
      }
    } else 
      deleteKey(node); // ลบ node
  }

  // Use this function to delete non-root nodes 
  public static void deleteKey(Node node) {
    /// Use this function to delete non-root nodes 
    // There should be 7 cases here 
    if (node == null || node.parent == null) // ตรวจสอบว่า node หรือ parent ของ node มีค่าว่างหรือไม่
      return; 

    if (node.left != null && node.right != null) { // ตรวจสอบว่า node มีลูกทั้งสองทางหรือไม่
      Node min = findMin(node.right); // ให้ min เป็น node ที่มีค่าน้อยที่สุดในกิ่งขวาของ node
      node.key = min.key; // ให้ค่า key ของ node เท่ากับค่าน้อยสุดในกิ่งขวา
      deleteKey(min); // ลบ node ที่มีค่าน้อยสุดในกิ่งขวา
    } else if (node.left == null && node.right == null) { // ตรวจสอบว่า node ไม่มีลูกทั้งสองทาง
      if (node.parent.left == node) // ตรวจสอบว่า node อยู่ทางซ้ายของ parent หรือไม่
        node.parent.left = null; // ให้ลูกซ้ายของ parent เป็น null
      else 
        node.parent.right = null; // ให้ลูกขวาของ parent เป็น null

    } else if (node.right == null) { // ตรวจสอบว่า node มีลูกขวาเป็นค่าว่างหรือไม่
      if (node.parent.left == node) { // ตรวจสอบว่า node อยู่ทางซ้ายของ parent หรือไม่
        node.parent.left = node.left; // ให้ทางซ้ายของ parent ชี้ไปที่ลูกซ้ายของ node
        node.left.parent = node.parent; // ให้ parent ของลูกซ้ายของ node ชี้ไปที่ parent ของ node
      } else { 
        node.parent.right = node.left; // ให้ทางขวาของ parent ชี้ไปที่ลูกซ้ายของ node
        node.left.parent = node.parent; // ให้ parent ของลูกซ้ายของ node ชี้ไปที่ parent ของ node
      }
    } else { 
      if (node.parent.left == node) { // ตรวจสอบว่า node อยู่ทางซ้ายของ parent หรือไม่
        node.parent.left = node.right; // ให้ทางซ้ายของ parent ชี้ไปที่ลูกขวาของ node
        node.right.parent = node.parent; // ให้ parent ของลูกขวาของ node ชี้ไปที่ parent ของ node
      } else { 
        node.parent.right = node.right; // ให้ทางขวาของ parent ชี้ไปที่ลูกขวาของ node
        node.right.parent = node.parent; // ให้ parent ของลูกขวาของ node ชี้ไปที่ parent ของ node
      }
    }
  }

  public Tree() { 
  } // Dummy constructor (No need to edit) 
}