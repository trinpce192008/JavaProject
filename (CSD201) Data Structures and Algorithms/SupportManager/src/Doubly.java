/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TRI
 */
public class Doubly {
//    count
    
//    count
//   count head & tail
    public int countNode() {
    int count = 0;
    DNode p = head;
    while (p != null) {
        count++;
        p = p.next;
    }
    return count;
}
    
//   count just head
    public int countNode() {
    int count = 0;
    DNode p = head;
    while (p != null) {
        count++;
        p = p.next;
    }
    return count;
}
//   count just tail
public int countNode() {
    int count = 0;
    DNode p = tail;
    while (p != null) {
        count++;
        p = p.prev;
    }
    return count;
}
}
