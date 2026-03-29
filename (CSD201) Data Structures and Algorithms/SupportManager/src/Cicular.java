/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TRI
 */
public class Cicular {

    //Count
    //add last
    //reverese
    //get size
    //get Node at index k
    //search by String
    //delete Node
    
    
//    count just tail
    int count = 0;
      CLLNode p = tail;
      do {
          count++; p = p.next;
         } 
      while(p != tail);
        return count;
        
//    count just head
        public int countNode() {
    if (head == null) return 0;

    int count = 1;
    Node p = head.next;
    while (p != head) {
        count++;
        p = p.next;
    }
    return count;
}
//    
    //add last
    public void helper_fn(Song song) {
        if(song == null){
            return;
        }
        CLLNode newNode = new CLLNode(song);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }
//  reverese   
    public void reverse() {
                int n= getSize(), i, j;
                for (i = 0, j = n - 1; i < j; i++, j--) {
                    Node pi = getNode(i);// create a node = getnode index i
                    Node pj = getNode(j);// create a node = getnode index j
                    Song temp = pi.song;// Note: change value of node, not change node
                    pi.song = pj.song;
                    pj.song = temp;
                }
            }
        }
        
//  get size
        public int getSize() {
        if (tail == null) return 0;

        int c = 0;
        Node p = tail.next;

        do {
            c++;
            p = p.next;
        } while (p != tail.next);

        return c;
    }
        
// get Node at index k
public Node getNode(int k) {
        int c = 0;
        Node p = tail.next;
         do {
            if (c == k) return p;
            p = p.next;
            c++;
        } while (p != tail.next);

        return null; // k out of range
    }
        
        
//search by String
Node p = tail.next;
                 do {
                    if (p.song.getArtist().equalsIgnoreCase(artistName)) {
                        foundSongs.add(p.song);
                    }
                    p = p.next;
                } while (p != tail.next);

// delete Node
public void dele(Node q) {
                Node f, p;
                f = null;
                p = tail;
                do {
                    if (p == q) {
                        break;
                    }
                    f = p;
                    p = p.next;
                } while (p != tail);

                if (p == null) {
                    return;
                }
                f.next = p.next;
            }

        
