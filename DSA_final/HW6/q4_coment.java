import java.util.List;

import org.w3c.dom.Node;

public class Tree extends BTreePrinter {
  Node root;

  public Tree(Node root) {
    // กำหนดให้ตัวแปร root ของคลาสชี้ไปยัง root node ที่ถูกส่งเข้ามา
    this.root = root;
  }

  public void printTree() {
    // สืบทอดและเรียกใช้ฟังก์ชัน printTree จาก BTreePrinter เพื่อวาดแผนภาพ
    super.printTree(root);
    if (root == null)
      System.out.println("Empty tree!!!");
  }

  public Node findClosest(int search_key) {
    // ใช้ while loop ในการหาค่าใกล้เคียงตามข้อบังคับของโจทย์ที่ห้ามใช้ Recursion
    Node current = root;
    Node closest = root;
    int min_diff = Integer.MAX_VALUE;

    while (current != null) {
      int diff = Math.abs(current.key - search_key);

      // ตรวจสอบว่า Node ปัจจุบันเข้าใกล้ search_key มากกว่าเดิมหรือไม่
      if (diff < min_diff) {
        min_diff = diff;
        closest = current;
      }

      // ถ้าผลต่างเป็น 0 แปลว่าเจอค่าที่ตรงกันเป๊ะ ให้หยุด loop ทันที
      if (diff == 0)
        break;

      // เลือกลงซ้ายหรือขวาตามคุณสมบัติของ Binary Search Tree
      if (search_key < current.key)
        current = current.left;
      else
        current = current.right;
    }
    return closest;
  }

  public static Node findNext(Node node) {
    // ป้องกันกรณี NullPointerException
    if (node == null)
      return null;

    // ถ้ามีลูกขวา ตัวที่มากกว่าและใกล้สุดจะอยู่ซ้ายสุดของ Subtree ขวา
    if (node.right != null)
      return leftDescendant(node.right);
    // // ถ้าไม่มีลูกขวา ให้ปีนขึ้นไปหาพ่อแม่ ที่เป็นลูกฝั่งซ้ายของเขา
    else
      return rightAncestor(node);
  }

  public static Node leftDescendant(Node node) { // หาค่าน้อย
    if (node == null)
      return null;

    // ถ้าไม่มีลูกซ้ายต่อแล้ว แปลว่านี่คือ Node ที่มีค่าน้อยที่สุดใน Subtree นี้
    if (node.left == null)
      return node;

    // เรียกตัวเองเพื่อขุดลงไปทางซ้ายให้ลึกที่สุด
    return leftDescendant(node.left);
  }

  public static Node rightAncestor(Node node) { //first right parent

    // หาจนสุด root แล้วไม่เจอ parent อีก
    if (node == null || node.parent == null)
      return null;

    // ถ้าค่าเราน้อยกว่า parent แสดงว่า parent คือโหนดถัดไปที่ค่ามากกว่าเรา
    if (node.key < node.parent.key)
      return node.parent;

    // ไล่ขึ้นไปหา parent ลำดับถัดไป
    return rightAncestor(node.parent);
  }

  public List rangeSearch(int x, int y) {

    // สร้าง List เพื่อเก็บ Node คืนค่ากลับไป
    List l = new List(100);

    // ถ้า Tree ว่าง หรือค่าจุดเริ่มต้นมากกว่าค่าสิ้นสุด
    if (root == null || x > y)
      return l;

    // หาโหนดที่มีค่าใกล้กับจุดเริ่มต้น x มากที่สุดก่อน
    Node node = findClosest(x);

    // หากโหนดที่ใกล้ที่สุดดันมีค่าน้อยกว่า x แปลว่ามันไม่อยู่ใน range  ต้องขยับไปหาตัวถัดไปเสมอ
    if (node != null && node.key < x) {
      node = findNext(node);
    }

    // ไล่เพิ่ม Node ลงใน List แล้วขยับไปตัวถัดไปเรื่อยๆ จนกว่าค่าจะเกิน
    while (node != null && node.key <= y) {
      l.append(node);
      node = findNext(node);
    }

    return l;
  }

  public Tree() {
  } 
}