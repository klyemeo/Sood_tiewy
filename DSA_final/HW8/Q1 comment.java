package HW8;

public class Node {

  double price;        // ราคาเสนอซื้อหรือขาย
  int investorID;      // รหัสประจำตัวของผู้ลงทุน
  int amount;          // จำนวนหุ้นหรือสินทรัพย์
  int timestamp;       // ลำดับเวลาที่เพิ่มเข้า Heap
  boolean isMinHeap;   // สถานะระบุประเภท Heap: true = Min-Heap , false = Max-Heap

  // Constructor สำหรับกำหนดค่าเริ่มต้นให้กับ Node
  public Node(double price, int investorID, int amount, boolean isMinHeap) {
    this.price = price;          // กำหนดราคาเสนอ
    this.investorID = investorID;// กำหนดรหัสผู้ลงทุน
    this.amount = amount;        // กำหนดจำนวน
    this.isMinHeap = isMinHeap;  // กำหนดประเภท Heap (Min-Heap / Max-Heap)
  }

  // คืนค่า true ถ้า Node นี้ (this) มีความสำคัญ (Priority) สูงกว่า rightNode
  public boolean compare(Node rightNode) {
    if (this.price == rightNode.price) {
      return this.timestamp < rightNode.timestamp; // ถ้าราคาเท่ากัน โหนดที่มาก่อน (timestamp น้อยกว่า) จะมีความสำคัญสูงกว่า
    } else {
      if (isMinHeap) {
        return this.price < rightNode.price;       // Min-Heap: ราคาน้อยกว่า = ความสำคัญสูงกว่า
      } else {
        return this.price > rightNode.price;       // Max-Heap: ราคามากกว่า = ความสำคัญสูงกว่า
      }
    }
  }

  public Node() {
  } // Dummy constructor , no need to edit
} 

