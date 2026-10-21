public class AVLTree extends BTreePrinter{
    Node root; // ประกาศตัวแปรโหนดรากของต้นไม้

  public void singleRotateFromLeft(Node y) { // ฟังก์ชันหมุนเดี่ยวซ้าย
    if (y == null || y.left == null) // ตรวจสอบว่าโหนด y หรือโหนดลูกซ้ายเป็น null หรือไม่
      return; // ถ้าใช่หยุดทำงานทันที

    Node x = y.left; // กำหนดโหนด x เป็นลูกซ้ายของโหนด y
    Node w = y.parent; // กำหนดโหนด w เป็นพ่อแม่ของโหนด y

    Node inner = x.right; // กำหนดโหนด inner เป็นลูกขวาของโหนด x
    y.left = inner; // ย้ายโหนด inner มาเป็นลูกซ้ายของ y
    if (inner != null) // ถ้าโหนด inner ไม่ใช่ null
      inner.parent = y; // กำหนดพ่อแม่ของโหนด inner เป็น y

    x.parent = w; // กำหนดพ่อแม่ของโหนด x เป็น w
    if (w == null) { // ถ้า w เป็น null แสดงว่า y เคยเป็นโหนด root
      root = x; // กำหนดโหนด root เป็น x
    } else { 
      if (w.left == y) { // ถ้า y เคยเป็นลูกซ้ายของ w
        w.left = x; // กำหนดลูกซ้ายของ w เป็น x
      } else { 
        w.right = x; // ถ้า y เคยเป็นลูกขวาของ w ให้ w ชี้ลูกขวามาที่ x 
      }
    }

    x.right = y; // กำหนดลูกขวาของ x เป็น y
    y.parent = x; // กำหนดพ่อแม่ของ y เป็น x
  }

  public void singleRotateFromRight(Node y) {// ฟังก์ชันหมุนเดี่ยวขวา
    if (y == null || y.right == null) // ตรวจสอบว่าโหนด y หรือโหนดลูกขวาเป็น null หรือไม่
      return; // ถ้าใช่หยุดทำงานทันที

    Node x = y.right; // กำหนดโหนด x เป็นลูกขวาของโหนด y
    Node w = y.parent; // กำหนดโหนด w เป็นพ่อแม่ของโหนด y

    Node inner = x.left; // กำหนดโหนด inner เป็นลูกซ้ายของโหนด x
    y.right = inner; // ย้ายโหนด inner มาเป็นลูกขวาของ y
    if (inner != null) // ถ้าโหนด inner ไม่ใช่ null
      inner.parent = y; // กำหนดพ่อแม่ของโหนด inner เป็น y

    x.parent = w; // กำหนดพ่อแม่ของโหนด x เป็น w
    if (w == null) { // ถ้า w เป็น null แสดงว่า y เคยเป็นโหนด root
      root = x; // กำหนดโหนด root เป็น x
    } else {
      if (w.left == y) { // ถ้า y เคยเป็นลูกซ้ายของ w
        w.left = x; // กำหนดลูกซ้ายของ w เป็น x
      } else {
        w.right = x; // ถ้า y เคยเป็นลูกขวาของ w ให้ w ชี้ลูกขวามาที่ x
      }
    }

    x.left = y; // กำหนดลูกซ้ายของ x เป็น y
    y.parent = x; // กำหนดพ่อแม่ของ y เป็น x
  }

  public void doubleRotateFromLeft(Node y) { // ฟังก์ชันหมุนคู่ซ้าย
    if (y == null || y.left == null || y.left.right == null) // ตรวจว่าโหนด y หรือโหนดลูกซ้ายหรือโหนดลูกขวาของลูกซ้ายเป็น null หรือไม่
      return; // ถ้าใช่หยุดทำงานทันที

    singleRotateFromRight(y.left); // หมุนขวาที่โหนดลูกซ้ายของ y 
    singleRotateFromLeft(y); // หมุนซ้ายที่โหนด y
  }

  public void doubleRotateFromRight(Node y) { // ฟังก์ชันหมุนคู่ขวา
    if (y == null || y.right == null || y.right.left == null) // ตรวจว่าโหนด y หรือโหนดลูกขวาหรือโหนดลูกซ้ายของลูกขวาเป็น null หรือไม่
      return; // ถ้าใช่หยุดทำงานทันที

    singleRotateFromLeft(y.right); // หมุนซ้ายที่โหนดลูกขวาของ y
    singleRotateFromRight(y); // หมุนขวาที่โหนด y
  }


  public static void rebalance(AVLTree tree, Node node) { // ฟังก์ชันปรับสมดุลต้นไม้ AVL
    Node cur = node; // กำหนดโหนด cur เป็น node เริ่มต้น
    while (cur != null) { // ทำซ้ำจนกว่า cur เป็น null
      Node parent = cur.parent; // เก็บพ่อแม่ของ cur ไว้ในตัวแปร parent
      int balanceFactor = height(cur.left) - height(cur.right); // คำนวณค่า balance factor ของ cur


      if (Math.abs(balanceFactor) > 1) { // ถ้า balance factor มีค่าสัมบูรณ์มากกว่า 1 แสดงว่าต้นไม้ไม่สมดุล
        if (balanceFactor > 1) { // กรณีเอียงซ้าย
          if (height(cur.left.left) >= height(cur.left.right)) { // ตรวจสอบว่าเป็น Left-Left Case
            System.out.println("Perform SingleRotationFromLeft(Node " + cur.key + ")");
            tree.singleRotateFromLeft(cur); // เรียกใช้การหมุนเดี่ยวซ้าย
          } else { // ถ้าไม่ใช่เป็น Left-Right Case
            System.out.println("Perform DoubleRotationFromLeft(Node " + cur.key + ")");
            tree.doubleRotateFromLeft(cur); // เรียกใช้การหมุนคู่ซ้าย
          }
        } else { // กรณีเอียงขวา
          if (height(cur.right.right) >= height(cur.right.left)) { // ตรวจสอบว่าเป็น Right-Right Case
            System.out.println("Perform SingleRotationFromRight(Node " + cur.key + ")");
            tree.singleRotateFromRight(cur); // เรียกใช้การหมุนเดี่ยวขวา
          } else {
            System.out.println("Perform DoubleRotationFromRight(Node " + cur.key + ")");
            tree.doubleRotateFromRight(cur); // เรียกใช้การหมุนคู่ขวา
          }
        }
      }
      cur = parent; // เลื่อน cur ขึ้นไปตรวจสอบที่โหนดพ่อแม่ต่อ
    }
  }




  // This function is for deleting the root node
  // If the node is not the root, please call the recursive version
  public void deleteKey(int key) {
    Node node = findKey(key); // ค้นหาโหนดที่มีค่า key ที่ต้องการลบ

    if (root == null) { // ตรวจสอบว่าต้นไม้ว่างหรือไม่
      System.out.println("Empty Tree!!!"); // ถ้าว่างแสดงข้อความ
      return;
    }

    if (node == null) { // ถ้าไม่พบโหนดที่ต้องการลบ
      System.out.println("Key not found!!!"); // แสดงข้อความ
      return;
    }

    if (node == root) { // ถ้าโหนดที่ต้องการลบคือ root
      if (node.left == null && node.right == null) { // ถ้า root ไม่มีลูกซ้ายและลูกขวา
        root = null; // กำหนด root เป็น null

      } else if (node.right == null) { // ถ้า root ไม่มีลูกขวา
        root = node.left; // กำหนด root เป็นลูกซ้ายของ node
        root.parent = null; // กำหนดพ่อแม่ของ root เป็น null
        rebalance(this, root); // เรียกใช้ฟังก์ชัน rebalance เพื่อปรับสมดุลต้นไม้

      } else if (node.left == null) { // ถ้า root ไม่มีลูกซ้าย
        root = node.right; // กำหนด root เป็นลูกขวาของ node
        root.parent = null; // กำหนดพ่อแม่ของ root เป็น null
        rebalance(this, root); // เรียกใช้ฟังก์ชัน rebalance เพื่อปรับสมดุลต้นไม้

      } else { // ถ้า root มีลูกซ้ายและลูกขวา
        Node min = findMin(node.right); // หาโหนดที่มีค่าน้อยที่สุดใน subtree ขวาของ node
        node.key = min.key; // คัดลอกค่าคีย์มาแทนที่โหนดปัจจุบัน
        deleteKey(this, min); // เรียกใช้ฟังก์ชัน deleteKey เพื่อทำการลบโหนด min
      }
    } else {  // ถ้าโหนดที่ต้องการลบไม่ใช่ root
      deleteKey(this, node); // เรียกใช้ฟังก์ชัน deleteKey เพื่อทำการลบโหนด
    }
  }

  // Use this function to delete non-root nodes
  // Also, fix the code to have the rebalancing feature
  public static void deleteKey(AVLTree tree, Node node) { 
    if (node == null || node.parent == null) // ตรวจสอบว่าโหนดเป็น null หรือเป็น root หรือไม่
      return; // ถ้าใช่หยุดทำงานทันที

    if (node.left != null && node.right != null) { // ถ้าโหนดมีลูกซ้ายและลูกขวา
      Node min = findMin(node.right); // หาโหนดที่มีค่าน้อยที่สุดใน subtree ขวาของ node
      node.key = min.key; // คัดลอกค่าคีย์มาแทนที่โหนดปัจจุบัน
      deleteKey(tree, min); // เรียกใช้ฟังก์ชัน deleteKey เพื่อทำการลบโหนด min
      return;
    }

    Node parent = node.parent; // เก็บโหนดพ่อแม่ของโหนดที่จะลบ

    if (node.left == null && node.right == null) { // ถ้าโหนดไม่มีลูกซ้ายและลูกขวา
      if (parent.left == node) // ถ้าโหนดนี้เป็นลูกซ้ายของพ่อแม่
        parent.left = null; // กำหนดลูกซ้ายของพ่อแม่เป็น null
      else // ถ้าโหนดนี้เป็นลูกขวาของพ่อแม่
        parent.right = null; // กำหนดลูกขวาของพ่อแม่เป็น null

    } else if (node.right == null) { // ถ้าโหนดไม่มีลูกขวา
      if (parent.left == node) // ถ้าโหนดนี้เป็นลูกซ้ายของพ่อแม่
        parent.left = node.left; // เอาลูกซ้ายของโหนดนี้มาต่อแทน
      else // ถ้าโหนดนี้เป็นลูกขวาของพ่อแม่
        parent.right = node.left; // เอาลูกซ้ายไปต่อขวา

      node.left.parent = parent; // อัปเดต parent ของลูกซ้ายให้ชี้มาที่ parent

    } else { // ถ้าโหนดไม่มีลูกซ้าย
      if (parent.left == node) // ถ้าโหนดนี้เป็นลูกซ้ายของพ่อแม่
        parent.left = node.right; // เอาลูกขวาของโหนดนี้มาต่อซ้าย
      else // ถ้าโหนดนี้เป็นลูกขวาของพ่อแม่
        parent.right = node.right; // เอาลูกขวาไปต่อขวา

      node.right.parent = parent; // อัปเดต parent ของลูกขวาให้ชี้มาที่ parent
    }

    rebalance(tree, parent); // เรียกฟังก์ชันปรับสมดุลต้นไม้ โดยเริ่มจากโหนดพ่อแม่ขึ้นไป
  }



    // This function is complete, no need to edit
    public Node findKey(int search_key) { // ค้นหาโหนดที่มีค่า search_key
        return Main.findKey(root, search_key); // เรียกใช้ฟังก์ชัน findKey โดยเริ่มค้นหาจาก root
    }

    // This function is complete, no need to edit
    public static Node findMin(Node node){ // ค้นหาโหนดที่มีค่าน้อยที่สุดใน subtree ของ node
        if (node.left == null) // ถ้าโหนดนี้ไม่มีลูกซ้าย แสดงว่าเป็นโหนดที่มีค่าน้อยที่สุด
            return node; // คืนค่าโหนดนี้
        else // ถ้าโหนดนี้มีลูกซ้าย ให้ค้นหาต่อไปใน subtree ซ้าย
            return findMin(node.left); // เรียกใช้ฟังก์ชัน findMin โดยส่งลูกซ้ายของ node
    }

    // Use this function to check the node height
    // This function is complete, no need to edit
    public static int height(Node node){ // หาความสูงของ subtree ที่มีโหนด node เป็น root
        if (node == null) // ถ้าโหนดเป็น null แสดงว่า subtree ว่าง
            return -1; // คืนค่า -1
        else // ถ้าโหนดไม่เป็น null ให้คำนวณความสูงของ subtree ซ้ายและขวา
            return 1 + Math.max(height(node.left), height(node.right)); // คืนค่าความสูงของ subtree โดยบวก 1 กับความสูงของ subtree ที่สูงที่สุดระหว่างซ้ายและขวา
    }
}