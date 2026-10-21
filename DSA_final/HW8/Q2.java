//ให้นักศึกษานำ code ส่วน Node วางไว้ที่นี่

public class Node {

    double price; // สร้างตัวแปรเพื่อเก็บข้อมูลราคา โดยเก็บเป็นจุดทศนิยม
    int investorID; // เก็บ ID ของนักลงทุน 
    int amount; // เก็บจำนวนหรือปริมาณที่ต้องการซื้อขาย 
    int timestamp; // Heap.push() is the only function that modify this variable // เก็บค่าเวลาที่ถูกใส่เข้ามาใน heap เพื่อดูลำดับว่าใครมาก่อนหลัง
    boolean isMinHeap; // ตัวแปรที่บอกว่า node เป็น minheap หรือไม่

    public Node(double price, int investorID, int amount, boolean isMinHeap) { // constructor ของ node เพื่อเก็บค่าตัวแปรต่างๆที่เข้ามา
        this.price = price;
        this.investorID = investorID;
        this.amount = amount;
        this.isMinHeap = isMinHeap;
    }

    // This function will return true if Priority(thisNode) > Priority(rightNode)
    // minHeap: the lower the price, the higher the priority
    // maxHeap: the lower the price, the lower the priority
    // If same price, the smaller the timestamp is, the higher the priority will be
    public boolean compare(Node rightNode) { // function ที่เอาไว้เทียบค่า node ปัจจุบัน กับ node ที่ต้องการนำมาเปรียบเทียบ
        if (this.price == rightNode.price) { // ถ้า node ปัจจุบัน ราคา เท่ากับ rightnode 
            return this.timestamp < rightNode.timestamp; // return ว่าใคร มาก่อนมาหลัง
        } else { // ถ้าราคาไม่เท่ากัน 
            if (isMinHeap) { // ถ้าเป็น minheap 
                return this.price < rightNode.price; // ราคาที่น้อยกว่าจะมีความสำคัญมากกว่า  
            } else {   // ถ้าไม่ใช่ minheap 
                return this.price > rightNode.price; // ราคาที่มากกว่าจะมีความสำคัญมากกว่า
            }
        }
    }

    public Node() {
    } // Dummy constructor, no need to edit
}

public class Heap {

    boolean isMinHeap; // true if minHeap, false if maxHeap //ตัวแปรที่บอกว่า node เป็น minheap หรือไม่
    int heapSize; // similar to Array's cap variable ขนาดความจุที่ heap สามารถเก็บได้
    Node[] arr;//Array as a complete binary tree // array สำหรับ node 
    int back; // similar to Array's size variable //ตัวแปรที่ชี้เพื่อบอกจำนวนของข้อมูล ใน heap

    // For each push, the timeCounter will increase by 1
    int timeCounter; // ตัวแปรสำหรับนับ สำดับเวลา

    public Heap(boolean isMinHeap, int size) { // constructor ของ heap
        arr = new Node[size + 1];
        heapSize = size;
        back = 0;
        this.isMinHeap = isMinHeap;
        timeCounter = 0;
    }

    public Node top() { //function สำหรับดูข้อมูลที่มี priority สูงสุดหรือตำแหน่งบนสุด
        if (back == 0) //ถ้าไม่มีข้อมูลใน heap
        {
            return null; // ส่งค่า null กลับ

                }return arr[1]; // FIX THIS // ส่ง node ที่อยู่ตำแหน่งบนสุดหรือ root กลับ
    }

    public void push(Node node) { // Function สำหรับเพิ่มข้อมูล ใน node 
        // Increase timer each time you push something into the Queue
        timeCounter++; //เพิ่มตัวนับเวลาทีละ 1
        node.timestamp = timeCounter; // Stamp the time number to the node  // นำค่าเวลาที่นับมาใส่ใน node เพื่อบอกว่า node เข้ามาที่เวลาเท่าไหร่

        // To do:
        // 1. Push the new node at the end of the array (via back pointer)
        // 2. Sift (percolate) it up the heap
        // * Check priority of the current node with its parent
        // * Swap the current node if the priority is higher than the parent
        // * Repeat the process until reaching the root or there is no swap (Pls use
        // while loop)
        // 3. Increase the heap size
        arr[++back] = node; // ขยับ back ไปชี้ที่ช่องถัดไปและเอา node ไปใส่ต่อท้ายข้อมูลเดิม

        int i = back; // ให้ ตัวแปร i = back
        while (i > 1) { // loop จนกว่าจะถึง root node 
            int parent = i / 2; // หาตำแหน่งของ node พ่อแม่โดยเอา  i หาร 2

            if (arr[i].compare(arr[parent])) { // ตรวจสอบว่า node ปัจจุบันมี priority สูงกว่า parent node ไหม
                swap(i, parent); // ถ้าสูงกว่า ให้สลับที่กันระหว่าง i กับ parent node
                i = parent; //ให้ i มีค่า เป็น parent node เพื่อตรวจสอบด้านบนต่อ
            } else //
            {
                break;
            }
        }

    }

    public Node pop() { // Function ที่เอาข้อมูลออกจาก heap แล้วต้องเรียงใหม่ 

        // To do:
        // 1. Mark the root for return (Don't forget to return this node)
        // 2. Move the last node to the root (change pointer, set null)
        // 3. Decrease the heap size
        // 4. Sift (percolate) it down the heap
        // * Check priority of the current node with both children
        // * Swap the current node with the lower priority child
        // * Repeat the process until the node has no child or there is no swap (Pls use
        // while loop)
        if (back == 0) // ถ้าไม่มีข้อมูลใน heap 
        {
            return null; //ให้ return null 
        }
        Node root = arr[1]; //ให้ข้อมูลที่อยู่บนสุด เป็น root
        arr[1] = arr[back]; // เอาข้อมูลที่อยู่ตัวสุดท้าย ขึ้นมาเป็น arr[1] แทน
        arr[back--] = null; // ลบข้อมูลตำแหน่งสุดท้ายเดิมทิ้ง (เซ็ตเป็น null) แล้วลดขนาดจำนวนข้อมูล (back) ลง 1

        if (back == 0) // ถ้าไม่มีข้อมูลใน heap 
        {
            return root; // return root
        }
        int i = 1; // ให้ i = 1
        while (true) { // loop ไปเรื่อยๆ
            int leftC = 2 * i; //หาตำแหน่งฝั่งซ้าย 
            int rightC = 2 * i + 1; // หาตำแหน่งฝั่งขวา

            if (leftC > back) // ถ้า node ซ้ายมีค่ามากกว่า back 
            {
                break; // ให้หยุดทำ
            }
            int tarC = leftC; //กำหนดเป้าหมายในการสลับเป็น node ฝั่งซ้ายไว้ก่อน
            if (rightC <= back && arr[rightC].compare(arr[leftC])) // ตรวจสอบว่ามี node ฝั่งขวาไหม แล้ว node ฝั่งขวามีความสำคัญมากกว่าหรือไม่
            {
                tarC = rightC; // ถ้าใช่ให้ เป้าหมายที่ต้องการเป็น node ฝั่งขวาแทน
            }
            if (arr[tarC].compare(arr[i])) { // เปรียบเทียบเป้าหมายกับ node ปัจจุบันว่า มีความสำคัญกว่าหรือไม่
                swap(i, tarC);  // สลัตำแหน่งของ node ปัจจุบันกับ node เป้าหมาย
                i = tarC; //เปลี่ยน i ลงมาอยู่ตำแหน่งของลูกเพื่อทำงานต่อ
            } else {
                break;
            }
        }
        return root; // return root node 
    }

    // Optional: If you do not know what this function does, you do not have to use it
    public void swap(int index1, int index2) {  //Function เพื่อสลับข้อมูล
        Node temp = arr[index1]; //เอาข้อมูลในตัว index1 มาเก็บไว้ใน temp
        arr[index1] = arr[index2]; //ให้ index1 มีค่าเท่ากับ index2
        arr[index2] = temp; //ให้ index2 มีค่าเท่ากับ temp
    }

    // Optional: If you do not know what this function does, you do not have to use it
    public void printArray() {
        System.out.print("[");
        for (int i = 1; i < back; i++) { // loop ตั้งเเต่ตัวแรกถึงตัวสุดท้าย 
            System.out.print(arr[i].price); //แสดงข้อมูลราคา ใน index i 
            if (i < back - 1) // ถ้ายังไม่ใช่ข้อมูลตัวสุดท้าย
            {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

}
