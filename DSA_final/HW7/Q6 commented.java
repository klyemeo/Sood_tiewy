public class AVLTree extends BTreePrinter{
    Node root; // ประกาศตัวแปรโหนดรากของต้นไม้

    public AVLTree(Node root){
        this.root = root; // กำหนดโหนดรากของต้นไม้เป็น root
    }
    

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
    if (node == null) // ตรวจสอบว่าโหนด node เป็น null หรือไม่
      return; // ถ้าใช่หยุดทำงานทันที

    int balanceFactor = height(node.left) - height(node.right); // คำนวณค่า balance factor ของ node

    if (Math.abs(balanceFactor) > 1) { // ถ้า balance factor มีค่าสัมบูรณ์มากกว่า 1 แสดงว่าต้นไม้ไม่สมดุล
      if (balanceFactor > 1) { // ถ้า balance factor มากกว่า 1 แสดงว่าต้นไม้เอียงไปทางซ้าย
        if (height(node.left.left) >= height(node.left.right)) { // ถ้าความสูงของลูกซ้ายของลูกซ้ายมากกว่าหรือเท่ากับความสูงของลูกขวาของลูกซ้าย
          System.out.println("Perform SingleRotationFromLeft(Node " + node.key + ")");
          tree.singleRotateFromLeft(node); // ทำการหมุนซ้ายเดี่ยวที่ node
        } else {
          System.out.println("Perform DoubleRotationFromLeft(Node " + node.key + ")");
          tree.doubleRotateFromLeft(node); // ทำการหมุนซ้ายคู่ที่ node
        }
      } else { // ถ้า balance factor น้อยกว่า -1 แสดงว่าต้นไม้เอียงไปทางขวา
        if (height(node.right.right) >= height(node.right.left)) { // ถ้าความสูงของลูกขวาของลูกขวามากกว่าหรือเท่ากับความสูงของลูกซ้ายของลูกขวา
          System.out.println("Perform SingleRotationFromRight(Node " + node.key + ")");
          tree.singleRotateFromRight(node); // ทำการหมุนขวาเดี่ยวที่ node
        } else {
          System.out.println("Perform DoubleRotationFromRight(Node " + node.key + ")");
          tree.doubleRotateFromRight(node); // ทำการหมุนขวาคู่ที่ node
        }
      }
    }
  }
    
    
  public Node findKey(int search_key) { // ฟังก์ชันค้นหาโหนดที่มีค่า key เท่ากับ search_key
    return findKey(root, search_key); // เรียกใช้ฟังก์ชัน findKey โดยเริ่มค้นหาจากโหนด root
  }
    

  public static Node findKey(Node node, int search_key) { // ฟังก์ชันค้นหาโหนดที่มีค่า key เท่ากับ search_key โดยเริ่มค้นหาจากโหนด node
    if (node == null || node.key == search_key) // ถ้าโหนด node เป็น null หรือค่า key ของ node เท่ากับ search_key
      return node; // ให้คืนค่า node กลับไป

    if (search_key < node.key)
      return findKey(node.left, search_key); // ถ้า search_key น้อยกว่า key ของ node ให้ค้นหาต่อในลูกซ้ายของ node
    else
      return findKey(node.right, search_key); // ถ้า search_key มากกว่าหรือเท่ากับ key ของ node ให้ค้นหาต่อในลูกขวาของ node
  }

  public static Node findMin(Node node) { // ฟังก์ชันค้นหาโหนดที่มีค่า key น้อยที่สุด
    if (node == null) // ถ้าโหนด node เป็น null
      return null; // ให้คืนค่า null กลับไป
    if (node.left == null) // ถ้าโหนดลูกซ้ายของ node เป็น 
      return node; // ให้คืนค่า node กลับไป
    return findMin(node.left); // ถ้าไม่ใช่ให้ค้นหาต่อในลูกซ้ายของ node
  }

  public static Node findMax(Node node) { // ฟังก์ชันค้นหาโหนดที่มีค่า key มากที่สุด
    if (node == null) // ถ้าโหนด node เป็น null
      return null; // ให้คืนค่า null กลับไป
    if (node.right == null) // ถ้าโหนดลูกขวาของ node เป็น null
      return node; // ให้คืนค่า node กลับไป
    return findMax(node.right); // ถ้าไม่ใช่ให้ค้นหาต่อในลูกขวาของ node
  }
    
  public void insertKey(int key) { // ฟังก์ชันเพิ่มโหนดที่มีค่า key เท่ากับ key
    if (root == null) { // ถ้าโหนด root เป็น null 
      root = new Node(key); // ให้สร้างโหนดใหม่ที่มีค่า key เท่ากับ key และกำหนดให้เป็นโหนด root
    } else {
      insertKey(this, root, key); // ถ้าโหนด root ไม่เป็น null ให้เรียกใช้ฟังก์ชัน insertKey โดยเริ่มค้นหาจากโหนด root
    }
  }



  public static void insertKey(AVLTree tree, Node node, int key) { // ฟังก์ชันเพิ่มโหนดที่มีค่า key เท่ากับ key โดยเริ่มค้นหาจากโหนด node
    if (node == null) // ถ้าโหนด node เป็น null
      return; // ให้หยุดทำงานทันที

    if (key == node.key) { // ถ้า key เท่ากับค่า key ของ node 
      System.out.println("Duplicated key!!!"); // แสดงข้อความว่า key ซ้ำ
      return;
    } else if (key < node.key) { // ถ้า key น้อยกว่า key ของ node
      if (node.left == null) { // ถ้าโหนดลูกซ้ายของ node เป็น null
        node.left = new Node(key); // ให้สร้างโหนดใหม่ที่มีค่า key เท่ากับ key 
        node.left.parent = node; // กำหนดพ่อแม่ของโหนดลูกซ้ายเป็น node
      } else { // ถ้าโหนดลูกซ้ายของ node ไม่เป็น null
        insertKey(tree, node.left, key); // ให้เรียกใช้ฟังก์ชัน insertKey โดยเริ่มค้นหาจากโหนดลูกซ้ายของ node
      }
    } else { // ถ้า key มากกว่า key ของ node
      if (node.right == null) { // ถ้าโหนดลูกขวาของ node เป็น null
        node.right = new Node(key); // ให้สร้างโหนดใหม่ที่มีค่า key เท่ากับ key
        node.right.parent = node; // กำหนดพ่อแม่ของโหนดลูกขวาเป็น node
      } else { // ถ้าโหนดลูกขวาของ node ไม่เป็น null
        insertKey(tree, node.right, key); // ให้เรียกใช้ฟังก์ชัน insertKey โดยเริ่มค้นหาจากโหนดลูกขวาของ node
      }
    }
    rebalance(tree, node); // ปรับสมดุลต้นไม้ โดยเริ่มจากโหนด node
  }


    
  public void deleteKey(int key) { // ฟังก์ชันลบโหนดที่มีค่า key เท่ากับ key
    Node node = findKey(key); // ค้นหาโหนดที่มีค่า key เท่ากับ key โดยเริ่มค้นหาจากโหนด root

    if (root == null) { // ถ้าโหนด root เป็น null 
      System.out.println("Empty Tree!!!"); // แสดงข้อความว่า ต้นไม้ว่าง
      return;
    }

    if (node == null) { // ถ้าไม่พบโหนดที่มีค่า key เท่ากับ key
      System.out.println("Key not found!!!"); // แสดงข้อความว่า ไม่พบ key
      return;
    }

    if (node == root) { // ถ้าโหนดที่ต้องการลบคือ root
      if (node.left == null && node.right == null) { // ถ้า root ไม่มีลูกซ้ายและลูกขวา
        root = null; // กำหนด root เป็น null
      } else if (node.right == null) { // ถ้า root ไม่มีลูกขวา
        root = node.left; // กำหนด root เป็นลูกซ้ายของ node
        root.parent = null; // กำหนดพ่อแม่ของ root เป็น null
        rebalance(this, root); // ปรับสมดุลต้นไม้
      } else if (node.left == null) { // ถ้า root ไม่มีลูกซ้าย
        root = node.right; // กำหนด root เป็นลูกขวาของ node
        root.parent = null; // กำหนดพ่อแม่ของ root เป็น null
        rebalance(this, root); // ปรับสมดุลต้นไม้
      } else { // ถ้า root มีลูกซ้ายและลูกขวา
        Node min = findMin(node.right); // หาโหนดที่มีค่าน้อยที่สุดใน subtree ขวาของ node
        node.key = min.key; // คัดลอกค่าคีย์มาแทนที่โหนดปัจจุบัน
        deleteKey(this, min); // ลบโหนด min 
      }
    } else { // ถ้าโหนดที่ต้องการลบไม่ใช่ root
      deleteKey(this, node); // ลบโหนด
    }
  }

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

    Node cur = parent; // กำหนดโหนด cur เป็น parent ของ node ที่ถูกลบ
    while (cur != null) { // ทำซ้ำจนกว่า cur จะเป็น null
      Node next = cur.parent; // เก็บพ่อแม่ของ cur ไว้ในตัวแปร next
      rebalance(tree, cur); // ปรับสมดุลต้นไม้ โดยเริ่มจากโหนด cur
      cur = next; // ย้ายไปยังพ่อแม่ของ cur
    }
  }


    
    
  public static boolean isMergeable(Node r1, Node r2) { // ฟังก์ชันตรวจสอบว่าต้นไม้สองต้นสามารถรวมกันได้หรือไม่
    if (r1 == null || r2 == null) // ถ้าต้นไม้ต้นใดต้นหนึ่งเป็น null
      return true; // ให้คืนค่า true กลับไป
    return findMax(r1).key < findMin(r2).key; // ตรวจสอบว่าค่ามากที่สุดของต้นไม้ r1 น้อยกว่าค่าน้อยที่สุดของต้นไม้ r2 หรือไม่
  }

  public static Node mergeWithRoot(Node r1, Node r2, Node t) { // ฟังก์ชันรวมต้นไม้สองต้นเข้ากับโหนด t
    if (!isMergeable(r1, r2)) { // ตรวจสอบว่าต้นไม้สองต้นสามารถรวมกันได้หรือไม่
      System.out.println("All nodes in T1 must be smaller than all nodes from T2"); // ถ้าไม่สามารถรวมกันได้ให้แสดงข้อความ
      return null;
    }

    if (t == null) // ถ้าโหนด t เป็น null
      return null; // ให้คืนค่า null กลับไป

    t.left = null; // กำหนดลูกซ้ายของ t เป็น null
    t.right = null; // กำหนดลูกขวาของ t เป็น null
    t.parent = null; // กำหนดพ่อแม่ของ t เป็น null

    int h1 = height(r1); // คำนวณความสูงของต้นไม้ r1
    int h2 = height(r2); // คำนวณความสูงของต้นไม้ r2

    if (Math.abs(h1 - h2) <= 1) { // ถ้าความสูงของต้นไม้สองต้นต่างกันไม่เกิน 1
      t.left = r1; // กำหนดลูกซ้ายของ t เป็น r1
      t.right = r2; // กำหนดลูกขวาของ t เป็น r2
      if (r1 != null) // ถ้า r1 ไม่ใช่ null
        r1.parent = t; // กำหนดพ่อแม่ของ r1 เป็น t
      if (r2 != null) // ถ้า r2 ไม่ใช่ null
        r2.parent = t; // กำหนดพ่อแม่ของ r2 เป็น t
      return t; // คืนค่าโหนด t 
    }

    if (h1 > h2 + 1) { // ถ้าความสูงของต้นไม้ r1 มากกว่าความสูงของต้นไม้ r2 + 1
      Node cur = r1; // กำหนดโหนด cur เป็น r1
      Node parent = null; // กำหนดโหนด parent เป็น null

      while (cur != null && height(cur) > h2 + 1) { // ทำซ้ำจนกว่า cur เป็น null หรือความสูงของ cur น้อยกว่าหรือเท่ากับ h2 + 1
        parent = cur; // เก็บพ่อแม่ของ cur ไว้ในตัวแปร parent
        cur = cur.right; // ย้ายไปยังลูกขวาของ cur
      }

      t.left = cur; // กำหนดลูกซ้ายของ t เป็น cur
      if (cur != null) // ถ้า cur ไม่ใช่ null
        cur.parent = t; // กำหนดพ่อแม่ของ cur เป็น t
      t.right = r2; // กำหนดลูกขวาของ t เป็น r2
      if (r2 != null) // ถ้า r2 ไม่ใช่ null
        r2.parent = t; // กำหนดพ่อแม่ของ r2 เป็น t

      t.parent = parent; // กำหนดพ่อแม่ของ t เป็น parent
      if (parent != null) // ถ้า parent ไม่ใช่ null
        parent.right = t; // กำหนดลูกขวาของ parent เป็น t

      AVLTree tree = new AVLTree(r1); // สร้างต้นไม้ AVL ใหม่โดยใช้ r1 เป็น root
      Node curr = t; // กำหนดโหนด curr เป็น t
      while (curr != null) { // ทำซ้ำจนกว่า curr เป็น null
        Node next = curr.parent; // เก็บพ่อแม่ของ curr ไว้ในตัวแปร next
        rebalance(tree, curr); // ปรับสมดุลต้นไม้ โดยเริ่มจากโหนด curr
        curr = next; // ย้ายไปยังพ่อแม่ของ curr
      }

      while (tree.root.parent != null) { // ทำซ้ำจนกว่า root ของต้นไม้ tree ไม่มีพ่อแม่
        tree.root = tree.root.parent; // ย้าย root ของต้นไม้ tree ไปยังพ่อแม่ของ root
      }
      return tree.root; // คืนค่า root ของต้นไม้ tree

    } else { // ถ้าความสูงของต้นไม้ r2 มากกว่าความสูงของต้นไม้ r1 + 1
      Node cur = r2; // กำหนดโหนด cur เป็น r2
      Node parent = null; // กำหนดโหนด parent เป็น null

      while (cur != null && height(cur) > h1 + 1) { // ทำซ้ำจนกว่า cur เป็น null หรือความสูงของ cur น้อยกว่าหรือเท่ากับ h1 + 1
        parent = cur; // เก็บพ่อแม่ของ cur ไว้ในตัวแปร parent
        cur = cur.left; // ย้ายไปยังลูกซ้ายของ cur
      }

      t.left = r1; // กำหนดลูกซ้ายของ t เป็น r1
      if (r1 != null) // ถ้า r1 ไม่ใช่ null
        r1.parent = t; // กำหนดพ่อแม่ของ r1 เป็น t
      t.right = cur; // กำหนดลูกขวาของ t เป็น cur
      if (cur != null) // ถ้า cur ไม่ใช่ null
        cur.parent = t; // กำหนดพ่อแม่ของ cur เป็น t

      t.parent = parent; // กำหนดพ่อแม่ของ t เป็น parent
      if (parent != null) // ถ้า parent ไม่ใช่ null
        parent.left = t; // กำหนดลูกซ้ายของ parent เป็น t

      AVLTree tree = new AVLTree(r2); // สร้างต้นไม้ AVL ใหม่โดยใช้ r2 เป็น root
      Node curr = t; // กำหนดโหนด curr เป็น t
      while (curr != null) { // ทำซ้ำจนกว่า curr เป็น null
        Node next = curr.parent; // เก็บพ่อแม่ของ curr ไว้ในตัวแปร next
        rebalance(tree, curr); // ปรับสมดุลต้นไม้ โดยเริ่มจากโหนด curr
        curr = next; // ย้ายไปยังพ่อแม่ของ curr
      }

      while (tree.root.parent != null) { // ทำซ้ำจนกว่า root ของต้นไม้ tree ไม่มีพ่อแม่
        tree.root = tree.root.parent; // ย้าย root ของต้นไม้ tree ไปยังพ่อแม่ของ root
      }
      return tree.root; // คืนค่า root ของต้นไม้ tree
    }
  }

  public void merge(AVLTree tree2) { // ฟังก์ชันรวมต้นไม้ AVL สองต้นเข้าด้วยกัน
    if (tree2 == null) // ตรวจสอบว่าต้นไม้ tree2 เป็น null หรือไม่
      return;

    if (isMergeable(this.root, tree2.root)) { // ตรวจสอบว่าต้นไม้สองต้นสามารถรวมกันได้หรือไม่
      if (this.root == null) { // ถ้าต้นไม้ต้นแรกเป็น null
        this.root = tree2.root; // ให้ต้นไม้ต้นแรกชี้ไปยัง root ของต้นไม้ต้นที่สอง
        tree2.root = null; // กำหนด root ของต้นไม้ต้นที่สองเป็น null
        return;
      }
      if (tree2.root == null) { // ถ้าต้นไม้ต้นที่สองเป็น null
        return;
      }

      Node t = findMax(this.root); // หาโหนดที่มีค่ามากที่สุดในต้นไม้ต้นแรก
      deleteKey(t.key); // ลบโหนด t ออกจากต้นไม้ต้นแรก

      this.root = mergeWithRoot(this.root, tree2.root, t); // รวมต้นไม้สองต้นเข้ากับโหนด t และกำหนด root ของต้นไม้ต้นแรกเป็นผลลัพธ์ของการรวม
      tree2.root = null; // กำหนด root ของต้นไม้ต้นที่สองเป็น null
    } else { // ถ้าต้นไม้สองต้นไม่สามารถรวมกันได้
      System.out.println("All nodes in T1 must be smaller than all nodes from T2"); // แสดงข้อความ
  }

    // This function is complete, no need to edit
    public static int height(Node node){ // หาความสูงของ subtree ที่มีโหนด node เป็น root
        if (node == null) // ถ้าโหนดเป็น null แสดงว่า subtree ว่าง
            return -1; // คืนค่า -1
        else // ถ้าโหนดไม่เป็น null 
            return 1 + Math.max(height(node.left), height(node.right)); //คำนวณความสูงของ subtree ซ้ายและขวา และคืนค่าความสูงที่มากที่สุด + 1
    }

    // This function is complete, no need to edit
    public void printTree() { // ฟังก์ชันแสดงผลต้นไม้
        if (root == null) { // ถ้าโหนด root เป็น null แสดงว่าต้นไม้ว่าง
            System.out.println("Empty tree!!!"); // แสดงข้อความ
        } else { // ถ้าโหนด root ไม่เป็น null 
            super.printTree(root); // เรียกใช้ฟังก์ชัน printTree ของคลาส BTreePrinter โดยส่งโหนด root เป็นพารามิเตอร์
        }
    }
    
    public AVLTree() {} // Dummy Constructor, no need to edit
}