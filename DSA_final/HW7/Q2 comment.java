// จัดการต้นไม้ค้นหาแบบทวิภาค และใช้ BTreePrinter ช่วยแสดงผล
public class BSTree extends BTreePrinter {
    Node root; // จุดเริ่มต้นของต้นไม้

    public void singleRotateFromLeft(Node y) {
        if (y == null || y.left == null) { // หมุนต่อไม่ได้ถ้าไม่มี y หรือลูกซ้าย
            return; // ไม่มีอะไรต้องทำ
        }

        Node x = y.left; // x จะขึ้นมาแทนตำแหน่งของ y
        Node w = y.parent; // จำพ่อเดิมของ y ไว้ เพื่อเชื่อมกลับให้ถูก

        Node inner = x.right; // เก็บกิ่งด้านในไว้ก่อนย้าย x
        y.left = inner; // กิ่งนั้นจะกลายเป็นลูกซ้ายของ y
        if (inner != null) { // ถ้ามีกิ่งด้านใน
            inner.parent = y; // อัปเดตพ่อของกิ่งให้เป็น y ด้วย
        }

        x.parent = w; // ให้ x รับช่วงต่อจาก y
        if (w == null) { // ถ้า y เป็นรากเดิม
            root = x; // x ก็จะเป็นรากใหม่
        } else {
            if (w.left == y) { // ถ้า y อยู่ฝั่งซ้ายของพ่อ
                w.left = x; // วาง x แทน y ทางฝั่งซ้าย
            } else {
                w.right = x; // ไม่เช่นนั้น วาง x แทนทางฝั่งขวา
            }
        }

        x.right = y; // ย้าย y ลงมาเป็นลูกขวาของ x
        y.parent = x; // อัปเดตพ่อของ y หลังหมุน
    }

    public void singleRotateFromRight(Node y) {
        if (y == null || y.right == null) { // หมุนต่อไม่ได้ถ้าไม่มี y หรือลูกขวา
            return; // ไม่มีอะไรต้องทำ
        }

        Node x = y.right; // x จะขึ้นมาแทนตำแหน่งของ y
        Node w = y.parent; // จำพ่อเดิมของ y ไว้

        Node inner = x.left; // เก็บกิ่งด้านในไว้ก่อนย้าย x
        y.right = inner; // กิ่งนั้นจะกลายเป็นลูกขวาของ y
        if (inner != null) { // ถ้ามีกิ่งด้านใน
            inner.parent = y; // อัปเดตพ่อของกิ่งให้เป็น y ด้วย
        }

        x.parent = w; // ให้ x รับช่วงต่อจาก y
        if (w == null) { // ถ้า y เป็นรากเดิม
            root = x; // x ก็จะเป็นรากใหม่
        } else {
            if (w.left == y) { // ถ้า y อยู่ฝั่งซ้ายของพ่อ
                w.left = x; // วาง x แทน y ทางฝั่งซ้าย
            } else {
                w.right = x; // ไม่เช่นนั้น วาง x แทนทางฝั่งขวา
            }
        }

        x.left = y; // ย้าย y ลงมาเป็นลูกซ้ายของ x
        y.parent = x; // อัปเดตพ่อของ y หลังหมุน
    }

    public void doubleRotateFromLeft(Node y) {
        if (y == null || y.left == null || y.left.right == null) { // ต้องมี y, ลูกซ้าย และหลานขวาก่อน
            return; // โครงสร้างยังหมุนไม่ได้
        }

        singleRotateFromRight(y.left); // หมุนที่ลูกซ้ายก่อน
        singleRotateFromLeft(y); // แล้วค่อยหมุนที่ y
    }

    public void doubleRotateFromRight(Node y) {
        if (y == null || y.right == null || y.right.left == null) { // ต้องมี y, ลูกขวา และหลานซ้ายก่อน
            return; // โครงสร้างยังหมุนไม่ได้
        }

        singleRotateFromLeft(y.right); // หมุนที่ลูกขวาก่อน
        singleRotateFromRight(y); // แล้วค่อยหมุนที่ y
    }

    public void insertKey(int key) {
        if (root == null) { // ต้นไม้ว่าง ก็ให้ key เป็นรากได้เลย
            root = new Node(key); // สร้างโหนดรากด้วยค่า key
        } else {
            Main.insertKey(root, key); // ถ้ามีรากแล้ว ให้ Main แทรก key ลงในตำแหน่งที่เหมาะสม
        }
    }

    public void printTree() {
        if (root == null) { // ไม่มีรากแปลว่าต้นไม้ว่าง
            System.out.println("Empty tree!!!"); // แจ้งให้รู้ว่ายังไม่มีข้อมูล
        } else {
            super.printTree(root); // ให้คลาสแม่แสดงต้นไม้โดยเริ่มจากราก
        }
    }

    public Node findKey(int search_key) {
        return Main.findKey(root, search_key); // ค้นหาจากราก แล้วคืนโหนดที่พบหรือ null
    }
}
