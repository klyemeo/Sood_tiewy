
public class BSTree extends BTreePrinter {

    Node root; //ประกาศตัวแปร root เพื่อเก็บ node บนสุดของTree

    public void singleRotateFromLeft(Node y) { // method สำหรับหมุน node ไปทางขวา โดยรับ y คือ โหนดที่เสียสมดุล
        if (y == null || y.left == null) //ตรวจสอบว่า node y เป็นค่าว่าง หรือ ลูกทางซ้ายของ y เป็นค่าว่างไหม ถ้าเข้าเงื่อนไขจะไม่สามรถหมุน node ได้
        {
            return; // ออกจากการทำงานเลย
        }
        Node x = y.left; // เอาค่า node ทางซ้ายของ y มาเก็บไว้ในตัวแปร x
        Node w = y.parent; // เอาค่า node parent ของ y มาเก็บไว้ในตัวแปร w 

        Node inner = x.right; //เอา node ลูกด้านขวาของ x มาเก็บไว้ในตัวแปร inner
        y.left = inner; // กำหนดให้ node ทางด้านซ้ายของ y เป็น inner 
        if (inner != null) // กำหนดเงื่อนไข ถ้า inner ไม่เท่ากับค่าว่าง
        {
            inner.parent = y; //ให้ parent node ของ inner ชี้ไปที่ y
        }
        x.parent = w; //ให้ parent node ของ x ชี้ไปที่ w  เพื่อขึ้นไปอยู่ที่ level เดียวกับที่ y เคยอยู่
        if (w == null) { //ตรวจสอบว่า w เป็นค่าว่างหรือไม่
            root = x; // กำหนดให้ x เป็น root 
        } else { // ถ้า w ไม่ใช่ค่าว่าง
            if (w.left == y) { //  ถ้า y เป็น node ลูกทางซ้ายของ w 
                w.left = x; // ถ้าเข้าเงื่อนไขให้ xเป็น node ลูกทางซ้ายของ w แทน
            } else { // กรณีที่ y ไม่ได้เป็น node ลูกทางซ้ายของ w
                w.right = x; // ให้ x เป็น  node ลูกทางขวาของ w
            }
        }

        x.right = y; // กำหนดให้ y เป้น node ลูกทางขวาของ x
        y.parent = x; //กำหนดให้ node parent ของ y ชี้ไปที่  x แทน
    }

    public void singleRotateFromRight(Node y) { // method สำหรับหมุน node ไปทางซ้าย โดยรับ y คือ โหนดที่เสียสมดุล
        if (y == null || y.right == null) //ตรวจสอบว่า node y เป็นค่าว่าง หรือ ลูกทางซ้ายของ y เป็นค่าว่างไหม ถ้าเข้าเงื่อนไขจะไม่สามรถหมุน node ได้
        {
            return; // ออกจากการทำงานเลย
        }
        Node x = y.right; // กำหนดให้ node x มีค่าเท่ากับ node ลูกทางขวาของ y
        Node w = y.parent;  //กำหนดให้ node w มีค่าเท่ากับ parent node ของ y

        Node inner = x.left; // กำหนดให้ inner เก็บค่า ของ node ลูกทางซ้าของ x
        y.right = inner; // กำหนดให้ inner เป็น node ลูกทางขวาของ y 
        if (inner != null) //ตรวจสอบว่า inner node ไม่เท่ากับค่าว่าง
        {
            inner.parent = y; //ให้ parent node ของ inner ชี้ไปที่ y
        }
        x.parent = w; //ให้ parent node ของ  x ชี้ไปที่ w 
        if (w == null) { //ถ้า w เป็นค่าว่างทำตามเงื่อนไข
            root = x; // ให้ root node เป็น x
        } else { // ถ้า w ไม่เท่ากับค่าว่าง
            if (w.left == y) { // ถ้า node ลูกทางด้านซ้ายของ w เป็น y 
                w.left = x; // ให้ node ลูกทางด้านซ้ายของ w เป็น x
            } else { // ถ้า node ลูกทางด้านซ้ายของ w ไม่เป็น y 
                w.right = x; //ให้  node ลูกทางด้านขวาของ w เป็น x
            }
        }

        x.left = y; // กำหนดให้ y เป้น node ลูกทางซ้ายของ x
        y.parent = x; // กำหนดให้  node parent ของ y เป็น x
    }

    public void insertKey(int key) { // method เพื่อเพิ่มข้อมูลลงไปใน Tree
        if (root == null) { //ตรวจสอบว่า tree ว่างหรือไม่
            root = new Node(key); // สร้าง node ใหม่จากค่า key และกำหนดให้เป็น root node 
        } else { // ถ้า tree ไม่ได้เป็นค่าว่าง
            Main.insertKey(root, key); // ใช้ insertKey เพื่อนำ Key ไปแทรกไว้ในตำแหน่งที่ถูกต้อง
        }
    }

    public void printTree() { // method สำหรับ Print Tree
        if (root == null) { //ตรวจสอบว่า tree ว่างหรือไม่
            System.out.println("Empty tree!!!"); //แสดงข้อความ Empty tree!!!
        } else { // ถ้า tree ไม่ว่าง
            super.printTree(root); //print tree 
        }
    }

    public Node findKey(int search_key) { // method สำหรับหาค่าที่ต้องการ
        return Main.findKey(root, search_key); // ส่งโหนดรากและค่าที่ต้องการค้นหาไปประมวลผลที่คลาส Main แล้วส่งคืน (return) โหนดที่ค้นพบกลับออกไป
    }
}
