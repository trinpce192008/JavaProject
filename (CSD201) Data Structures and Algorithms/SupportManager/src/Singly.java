/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TRI
 */
public class Singly {
    
     //count
    //CHÈN X,Y,Z TẠI VỊ TRÍ
    //XOÁ NODE THỨ 3 LỚN HƠN MIN
    //ĐẢO VỊ TRÍ CÁC NODE TRƯỚC NODE CÓ GIÁ TRỊ J
    //tìm Node trước tail
    //count
//    just head
    public int countNode() {
    int count = 0;
    Node p = head;
    while (p != null) {
        count++;
        p = p.next;
    }
    return count;
    
//   tail & head
public int countNode() {
    int count = 0;
    Node p = head;
    while (p != null) {
        count++;
        p = p.next;
    }
    return count;
}


    void addLast(String xDriver, int xRate, int xColor) {
        //You should write here appropriate statements to complete this function.
        if (xDriver.charAt(0) == 'A') {
            return;
        }
        Canoe newCanoe = new Canoe(xDriver, xRate, xColor);
        Node newNode = new Node(newCanoe);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

    }

//==================================================================
//    CHÈN X,Y,Z TẠI VỊ TRÍ (head = 0, count < vị trí chèn)
//    head = 1, count < vị trí chèn -1

        int count = 1;
        Node p = head;
        Node temp = p.next;
        p.next = new Node(x, temp);

        Node a = head;
        while (a != null && count < 2) {
            a = a.next;
            count++;
        }
        temp = a.next;
        a.next = new Node(y, temp);

        p = head;
        while (p != null && p.next != tail) {
            p = p.next;

        }
        temp = p.next;
        p.next = new Node(z, temp);

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
//XOÁ NODE THỨ 3 LỚN HƠN MIN
//==================================================================

        if (isEmpty()) {
            return;
        }
        Node p = head;
        int min = head.info.color; // giả sử node đầu là min

        while (p != null) {
            int s = p.info.color;
            if (s < min) {
                min = s;
            }
            p = p.next;
        }
        p = head;
        int count = 0;
        while (p != null) {
            if (p.info.color > min) {
                count++;
                if (count == 3) {
                    break;
                }
            }
            p = p.next;
        }
        //Find K before p
        Node k = head;
        while (k != null && k.next != p) {
            k = k.next;
        }
        if (k == null) {
            return;
        }
        // Remove p
        k.next = p.next;
        p.next = null;
        if (p == tail) {
            tail = k;
        }

// ĐẢO VỊ TRÍ CÁC NODE TRƯỚC NODE CÓ GIÁ TRỊ J
//==================================================================

        Node p = head;
        int count = 0;
        while(p != null){
                        count++;
            if(p.info.driver.equalsIgnoreCase("J")){
                break;
            }
            p = p.next;
        }
        class LocalGet{
            Node getNode(int x){
                int count = 1;
                Node p = head;
		while(p != null && count<x){
                    count++;
                    p = p.next;
                }
		return p;
            }
            }
        
        LocalGet h = new LocalGet();
        int i, j;
        for (i = 0, j = count - 1; i < j; i++, j--) {
            Node pi = h.getNode(i);// create a node = getnode index i
            Node pj = h.getNode(j);// create a node = getnode index j
            Canoe temp = pi.info;// Note: change value of node, not change node
            pi.info = pj.info;
            pj.info = temp;
        }

}
//   tìm Node trước tail
      Node p = head;
      Node c = null;
      while (p != tail) {
          c = p;
          p = p.next;
      }
}
