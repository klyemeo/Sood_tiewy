public class Q8comment extends BTreePrinter {
  Node root; 

  // ค้นหาและคืนโหนดที่มีค่าตรงกับ search_key โดยไล่จากรากลงไป
  public Node findKey(int search_key) {
    Node cur = root; // ใช้ cur เดินตามเส้นทางค้นหา โดยไม่เปลี่ยน root

    while (cur != null) { // ค้นหาต่อไปตราบใดที่ยังมีโหนดให้ตรวจ
      if (search_key == cur.key) { // พบค่าที่ต้องการแล้ว
        return cur; // คืนโหนดที่มีค่านั้น
      } else if (search_key < cur.key) { // ค่าน้อยกว่า ให้ค้นหาในต้นไม้ย่อยด้านซ้าย
        cur = cur.left; // เลื่อนไปยังลูกซ้าย
      } else { // ค่ามากกว่า ให้ค้นหาในต้นไม้ย่อยด้านขวา
        cur = cur.right; // เลื่อนไปยังลูกขวา
      }
    }

    return null; // คืนค่า null
  }

  // หาและคืนโหนดที่มีค่าน้อยที่สุดในต้นไม้
  public Node findMin() {
    if (root == null) // ไม่มีโหนดให้ค้นหาเมื่อต้นไม้ว่าง
      return null; // คืนค่า null เพื่อบอกว่าไม่มีค่าน้อยที่สุด

    Node cur = root; // เริ่มค้นหาจากราก
    while (cur.left != null) { // ค่าที่น้อยที่สุดอยู่ทางซ้ายสุด
      cur = cur.left; // เลื่อนไปทางซ้ายจนไม่มีลูกซ้าย
    }
    return cur; // คืนค่าโหนดซ้ายสุดที่พบ
  }

  // หาและคืนโหนดที่มีค่ามากที่สุดในต้นไม้
  public Node findMax() {
    if (root == null) // ไม่มีโหนดให้ค้นหาเมื่อต้นไม้ว่าง
      return null; // คืน null เพื่อบอกว่าไม่มีค่ามากที่สุด

    Node cur = root; // เริ่มค้นหาจากราก
    while (cur.right != null) { // ค่าที่มากที่สุดอยู่ทางขวาสุด
      cur = cur.right; // เลื่อนไปทางขวาจนไม่มีลูกขวา
    }
    return cur; // คืนโหนดขวาสุดที่พบ
  }

  // แทรก key ลงในตำแหน่งที่ถูกต้อง โดยไม่ใช้การเรียกซ้ำ
  public void insertKey(int key) {
    if (root == null) { // กรณีต้นไม้ว่าง
      root = new Node(key); // สร้างโหนดแรกให้เป็นราก
      return; // แทรกเสร็จแล้ว จึงไม่ต้องค้นหาต่อ
    }

    Node cur = root; // ใช้ cur ไล่หาตำแหน่งที่จะแทรก

    while (cur != null) { //วนลูปเมื่อ cur ยังมีข้อมูลอยู่
      if (key == cur.key) { //เช็คว่าkey ตรงกับคีย์ของโหนดปัจจุบัน cur.key หรือไม่
        System.out.println("Duplicated key!!!"); // แสดงข้อความว่าพบ key ซ้ำ
        return; 
      } else if (key < cur.key) { // key น้อยกว่าโหนดปัจจุบัน จึงไปทางซ้าย
        if (cur.left == null) { // พบตำแหน่งว่างทางซ้ายแล้ว
          cur.left = new Node(key); // สร้างโหนดใหม่เป็นลูกทางซ้าย
          cur.left.parent = cur; // กำหนดโหนดปัจจุบันเป็นพ่อของโหนดใหม่
          return; 
        }
        cur = cur.left; // ตำแหน่งยังไม่ว่าง จึงค้นหาต่อในกิ่งซ้าย
      } else { // key มากกว่าโหนดปัจจุบัน จึงไปทางขวา
        if (cur.right == null) { // พบตำแหน่งว่างทางขวาแล้ว
          cur.right = new Node(key); // สร้างโหนดใหม่เป็นลูกทางขวา
          cur.right.parent = cur; // กำหนดโหนดปัจจุบันเป็นพ่อของโหนดใหม่
          return; 
        }
        cur = cur.right; // ค้นหาต่อในกิ่งขวา
      }
    }
  }

  // แสดงต้นไม้จากราก โดยใช้เมธอดพิมพ์ของคลาสแม่
  public void printTree() {
    if (root == null) { // ตรวจว่าต้นไม้ว่างหรือไม่
      System.out.println("Empty tree!!!"); // แจ้งข้อความเมื่อต้นไม้ไม่มีโหนด
    } else { // กรณีมีข้อมูลในต้นไม้
      super.printTree(root); // เรียกเมธอดของ BTreePrinter ให้พิมพ์จากราก
    }
  }
}
