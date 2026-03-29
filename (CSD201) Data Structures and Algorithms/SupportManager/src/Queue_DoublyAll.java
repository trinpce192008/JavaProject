/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TRI
 */
public class Queue_DoublyAll {
    
//    count
    
    
//    count
    public int countNode() {
    int count = 0;
    Node p = front;
    while (p != null) {
        count++;
        p = p.next;
    }
    return count;
}
//    count doubly rear
    public int countNode() {
    int count = 0;
    DNode p = rear;
    while (p != null) {
        count++;
        p = p.prev;
    }
    return count;
}

//is empty
    public boolean isEmpty() {
        // ---------- Student's code starts from here ----------
        // Students are welcomed to use any helper function(s)
        if(front == null) return true;
        return false;
        // -----------------------------------------------------
    }
//enqueue
    public void enqueue(Task task) {
        // ---------- Student's code starts from here ----------
        // Students are welcomed to use any helper function(s)
         QueueNode newNode = new QueueNode(task);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        return;
    }
    
    public Song dequeue() {
        Node temp = null;
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
            if (isEmpty()) return null;
            Node p = front;
            front = front.next;
            if (front == null) rear = null;
            temp = p;
            
            
        //---------------------------------------------------------
        return temp.song;
    }
}
/*
===================== MUC LUC TIM NHANH =====================

1. COMMON NODE
   - Node
   - CNode

2. DOUBLY LINKED LIST
   2.1 ADD
       - addLast
       - addFirst
       - addMany
       - addIfAgeGreaterThan4
       - addElementHaveMaxAge

   2.2 INSERT
       - insertAt
       - insertAfter

   2.3 SEARCH
       - searchByName
       - searchByAge
       - searchByPrice

   2.4 DELETE
       - delete
       - deleteAll
       - deleteAt
       - deleteFirstAgeLessThan6
       - deleteNodeAfterPosition
       - deleteAfterSecondNodeAgeLessThan9
       - deleteThirdNodeAgeLessThan6
       - deleteFirstAfterPositionK
       - deleteAfterNodePriceGreaterThan
       - deleteTwoLastNodesAgeGreaterThan5
       - deleteSecondBiggestAge

   2.5 SORT
       - sortByName
       - sortByAge
       - sortByPrice
       - sortByFor
       - sortFirst3ByAge

   2.6 SWAP
       - swapData
       - swapMinMaxAge
       - swapSecondMaxWithFirstMin

   2.7 GET
       - getNode
       - getMaxAgeNode

   2.8 OTHER
       - isEmpty
       - clear
       - traverse
       - replaceNode
       - countNode
       - reverse
       - append
       - changeFirstName

3. CIRCULAR SINGLY LINKED LIST
   3.1 ADD
       - addLast
       - addFirst
       - addMany
       - addIfAgeGreaterThan4
       - addElementHaveMaxAge

   3.2 INSERT
       - insertAt
       - insertAfter

   3.3 SEARCH
       - searchByName
       - searchByAge
       - searchByPrice

   3.4 DELETE
       - delete
       - deleteAll
       - deleteAt
       - deleteFirstAgeLessThan6
       - deleteNodeAfterPosition
       - deleteAfterSecondNodeAgeLessThan9
       - deleteThirdNodeAgeLessThan6
       - deleteFirstAfterPositionK
       - deleteAfterNodePriceGreaterThan
       - deleteTwoLastNodesAgeGreaterThan5
       - deleteSecondBiggestAge

   3.5 SORT
       - sortByName
       - sortByAge
       - sortByPrice
       - sortByFor
       - sortFirst3ByAge

   3.6 SWAP
       - swapData
       - swapMinMaxAge
       - swapSecondMaxWithFirstMin

   3.7 GET
       - head
       - getNode
       - getMaxAgeNode

   3.8 OTHER
       - isEmpty
       - clear
       - traverse
       - replaceNode
       - countNode
       - reverse
       - append
       - changeFirstName

4. STACK
   4.1 CORE
       - push
       - pop
       - peek

   4.2 SEARCH / GET
       - getNode
       - getMaxAgeNode
       - searchByName
       - searchByAge
       - searchByPrice

   4.3 DELETE
       - delete
       - deleteAll
       - deleteAt

   4.4 OTHER
       - isEmpty
       - clear
       - addFirst
       - addIfAgeGreaterThan4
       - countNode
       - traverse
       - reverse
       - changeFirstName

5. QUEUE
   5.1 CORE
       - enqueue
       - dequeue
       - peek

   5.2 SEARCH / GET
       - getNode
       - getMaxAgeNode
       - searchByName
       - searchByAge
       - searchByPrice

   5.3 DELETE
       - delete
       - deleteAll
       - deleteAt

   5.4 OTHER
       - isEmpty
       - clear
       - addLast
       - addIfAgeGreaterThan4
       - countNode
       - traverse
       - append
       - changeFirstName

--------------------- TU KHOA CTRL + F ---------------------
COMMON NODE
DOUBLY LINKED LIST
CIRCULAR SINGLY LINKED LIST
STACK
QUEUE

ADD
INSERT
SEARCH
DELETE
SORT
SWAP
GET
OTHER
CORE

============================================================
*/

class DSTemplatesAllInOne {

    // ===================== COMMON NODE =====================
    static class Node {
        String name;
        int age;
        double price;
        Node next, prev;

        Node(String name, int age, double price) {
            this.name = name;
            this.age = age;
            this.price = price;
            next = prev = null;
        }
    }

    static class CNode {
        String name;
        int age;
        double price;
        CNode next;

        CNode(String name, int age, double price) {
            this.name = name;
            this.age = age;
            this.price = price;
            next = null;
        }
    }

    // ===================== DOUBLY LINKED LIST =====================
    static class DoublyList {
        Node head, tail;

        boolean isEmpty() { return head == null; }
        void clear() { head = tail = null; }

        void addLast(String name, int age, double price) {
            Node p = new Node(name, age, price);
            if (isEmpty()) { head = tail = p; return; }
            tail.next = p;
            p.prev = tail;
            tail = p;
        }

        void addFirst(String name, int age, double price) {
            Node p = new Node(name, age, price);
            if (isEmpty()) { head = tail = p; return; }
            p.next = head;
            head.prev = p;
            head = p;
        }

        void addMany(String[] names, int[] ages, double[] prices) {
            for (int i = 0; i < names.length; i++) addLast(names[i], ages[i], prices[i]);
        }

        void addIfAgeGreaterThan4(String name, int age, double price) {
            if (age > 4) addLast(name, age, price);
        }

        void addElementHaveMaxAge() {
            if (isEmpty()) return;
            Node max = head;
            for (Node p = head.next; p != null; p = p.next) if (p.age > max.age) max = p;
            addLast(max.name, max.age, max.price);
        }

        Node getNode(int k) {
            int i = 0;
            for (Node p = head; p != null; p = p.next) {
                if (i == k) return p;
                i++;
            }
            return null;
        }

        Node getMaxAgeNode() {
            if (isEmpty()) return null;
            Node max = head;
            for (Node p = head.next; p != null; p = p.next) if (p.age > max.age) max = p;
            return max;
        }

        void insertAt(int k, String name, int age, double price) {
            if (k <= 0) { addFirst(name, age, price); return; }
            Node q = getNode(k);
            if (q == null) { addLast(name, age, price); return; }
            Node p = new Node(name, age, price);
            Node before = q.prev;
            before.next = p;
            p.prev = before;
            p.next = q;
            q.prev = p;
        }

        void insertAfter(int k, String name, int age, double price) {
            Node q = getNode(k);
            if (q == null) return;
            if (q == tail) { addLast(name, age, price); return; }
            Node p = new Node(name, age, double price);
            Node after = q.next;
            q.next = p;
            p.prev = q;
            p.next = after;
            after.prev = p;
        }

        Node searchByName(String x) {
            for (Node p = head; p != null; p = p.next) if (p.name.equals(x)) return p;
            return null;
        }

        Node searchByAge(int x) {
            for (Node p = head; p != null; p = p.next) if (p.age == x) return p;
            return null;
        }

        Node searchByPrice(double x) {
            for (Node p = head; p != null; p = p.next) if (p.price == x) return p;
            return null;
        }

        void delete(Node q) {
            if (q == null || isEmpty()) return;
            if (q == head && q == tail) { head = tail = null; return; }
            if (q == head) {
                head = head.next;
                head.prev = null;
                return;
            }
            if (q == tail) {
                tail = tail.prev;
                tail.next = null;
                return;
            }
            q.prev.next = q.next;
            q.next.prev = q.prev;
        }

        void deleteAll() { clear(); }
        void deleteAt(int k) { delete(getNode(k)); }

        void deleteFirstAgeLessThan6() {
            for (Node p = head; p != null; p = p.next) {
                if (p.age < 6) { delete(p); return; }
            }
        }

        void deleteNodeAfterPosition(int k) {
            Node p = getNode(k);
            if (p == null || p.next == null) return;
            delete(p.next);
        }

        void deleteAfterSecondNodeAgeLessThan9() {
            int count = 0;
            for (Node p = head; p != null; p = p.next) {
                if (p.age < 9) {
                    count++;
                    if (count == 2) {
                        if (p.next != null) delete(p.next);
                        return;
                    }
                }
            }
        }

        void deleteThirdNodeAgeLessThan6() {
            int count = 0;
            for (Node p = head; p != null; p = p.next) {
                if (p.age < 6) {
                    count++;
                    if (count == 3) { delete(p); return; }
                }
            }
        }

        void deleteFirstAfterPositionK(int k) {
            Node p = getNode(k);
            if (p != null && p.next != null) delete(p.next);
        }

        void deleteAfterNodePriceGreaterThan(double xPrice) {
            for (Node p = head; p != null; p = p.next) {
                if (p.price > xPrice) {
                    if (p.next != null) delete(p.next);
                    return;
                }
            }
        }

        void deleteTwoLastNodesAgeGreaterThan5() {
            int count = 0;
            Node p = tail;
            while (p != null && count < 2) {
                Node prev = p.prev;
                if (p.age > 5) {
                    delete(p);
                    count++;
                }
                p = prev;
            }
        }

        void deleteSecondBiggestAge() {
            if (head == null || head.next == null) return;
            int max = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
            for (Node p = head; p != null; p = p.next) {
                if (p.age > max) {
                    second = max;
                    max = p.age;
                } else if (p.age > second && p.age < max) {
                    second = p.age;
                }
            }
            if (second == Integer.MIN_VALUE) return;
            delete(searchByAge(second));
        }

        void swapData(Node a, Node b) {
            String tName = a.name; a.name = b.name; b.name = tName;
            int tAge = a.age; a.age = b.age; b.age = tAge;
            double tPrice = a.price; a.price = b.price; b.price = tPrice;
        }

        void sortByName() {
            for (Node p = head; p != null; p = p.next)
                for (Node q = p.next; q != null; q = q.next)
                    if (p.name.compareTo(q.name) > 0) swapData(p, q);
        }

        void sortByAge() {
            for (Node p = head; p != null; p = p.next)
                for (Node q = p.next; q != null; q = q.next)
                    if (p.age > q.age) swapData(p, q);
        }

        void sortByPrice() {
            for (Node p = head; p != null; p = p.next)
                for (Node q = p.next; q != null; q = q.next)
                    if (p.price > q.price) swapData(p, q);
        }

        void sortByFor() { sortByAge(); }

        void sortFirst3ByAge() {
            Node[] a = new Node[3];
            Node p = head;
            int i = 0;
            while (p != null && i < 3) {
                a[i++] = p;
                p = p.next;
            }
            for (int x = 0; x < i; x++)
                for (int y = x + 1; y < i; y++)
                    if (a[x].age > a[y].age) swapData(a[x], a[y]);
        }

        void swapMinMaxAge() {
            if (isEmpty()) return;
            Node min = head, max = head;
            for (Node p = head.next; p != null; p = p.next) {
                if (p.age < min.age) min = p;
                if (p.age > max.age) max = p;
            }
            swapData(min, max);
        }

        void swapSecondMaxWithFirstMin() {
            if (head == null || head.next == null) return;
            Node min = head, max = head, secondMax = null;
            for (Node p = head; p != null; p = p.next) {
                if (p.age < min.age) min = p;
                if (p.age > max.age) {
                    secondMax = max;
                    max = p;
                } else if (p != max && (secondMax == null || p.age > secondMax.age) && p.age < max.age) {
                    secondMax = p;
                }
            }
            if (secondMax != null) swapData(secondMax, min);
        }

        void traverse() {
            for (Node p = head; p != null; p = p.next)
                System.out.print("(" + p.name + "," + p.age + "," + p.price + ") ");
            System.out.println();
        }

        void replaceNode(int k, String name, int age, double price) {
            Node p = getNode(k);
            if (p == null) return;
            p.name = name;
            p.age = age;
            p.price = price;
        }

        int countNode() {
            int count = 0;
            for (Node p = head; p != null; p = p.next) count++;
            return count;
        }

        void reverse() {
            Node p = head, tmp = null;
            while (p != null) {
                tmp = p.prev;
                p.prev = p.next;
                p.next = tmp;
                p = p.prev;
            }
            tmp = head;
            head = tail;
            tail = tmp;
        }

        void append(DoublyList other) {
            for (Node p = other.head; p != null; p = p.next)
                addLast(p.name, p.age, p.price);
        }

        void changeFirstName(String newName) {
            if (head != null) head.name = newName;
        }
    }

    // ===================== CIRCULAR SINGLY LINKED LIST =====================
    static class CircularList {
        CNode tail;

        boolean isEmpty() { return tail == null; }
        void clear() { tail = null; }
        CNode head() { return tail == null ? null : tail.next; }

        void addLast(String name, int age, double price) {
            CNode p = new CNode(name, age, price);
            if (isEmpty()) {
                tail = p;
                tail.next = tail;
                return;
            }
            p.next = tail.next;
            tail.next = p;
            tail = p;
        }

        void addFirst(String name, int age, double price) {
            CNode p = new CNode(name, age, price);
            if (isEmpty()) {
                tail = p;
                tail.next = tail;
                return;
            }
            p.next = tail.next;
            tail.next = p;
        }

        void addMany(String[] names, int[] ages, double[] prices) {
            for (int i = 0; i < names.length; i++) addLast(names[i], ages[i], prices[i]);
        }

        void addIfAgeGreaterThan4(String name, int age, double price) {
            if (age > 4) addLast(name, age, price);
        }

        void addElementHaveMaxAge() {
            if (isEmpty()) return;
            CNode h = head(), max = h, p = h.next;
            while (p != h) {
                if (p.age > max.age) max = p;
                p = p.next;
            }
            addLast(max.name, max.age, max.price);
        }

        CNode getNode(int k) {
            if (isEmpty()) return null;
            CNode h = head(), p = h;
            int i = 0;
            do {
                if (i == k) return p;
                i++;
                p = p.next;
            } while (p != h);
            return null;
        }

        CNode getMaxAgeNode() {
            if (isEmpty()) return null;
            CNode h = head(), max = h, p = h.next;
            while (p != h) {
                if (p.age > max.age) max = p;
                p = p.next;
            }
            return max;
        }

        void insertAt(int k, String name, int age, double price) {
            if (k <= 0 || isEmpty()) {
                addFirst(name, age, price);
                return;
            }
            CNode prev = getNode(k - 1);
            if (prev == null) {
                addLast(name, age, price);
                return;
            }
            CNode p = new CNode(name, age, price);
            p.next = prev.next;
            prev.next = p;
            if (prev == tail) tail = p;
        }

        void insertAfter(int k, String name, int age, double price) {
            CNode q = getNode(k);
            if (q == null) return;
            CNode p = new CNode(name, age, price);
            p.next = q.next;
            q.next = p;
            if (q == tail) tail = p;
        }

        CNode searchByName(String x) {
            if (isEmpty()) return null;
            CNode h = head(), p = h;
            do {
                if (p.name.equals(x)) return p;
                p = p.next;
            } while (p != h);
            return null;
        }

        CNode searchByAge(int x) {
            if (isEmpty()) return null;
            CNode h = head(), p = h;
            do {
                if (p.age == x) return p;
                p = p.next;
            } while (p != h);
            return null;
        }

        CNode searchByPrice(double x) {
            if (isEmpty()) return null;
            CNode h = head(), p = h;
            do {
                if (p.price == x) return p;
                p = p.next;
            } while (p != h);
            return null;
        }

        void delete(CNode q) {
            if (isEmpty() || q == null) return;
            CNode h = head();
            if (tail == h && q == h) {
                tail = null;
                return;
            }
            CNode f = tail, p = h;
            do {
                if (p == q) break;
                f = p;
                p = p.next;
            } while (p != h);
            if (p != q) return;
            f.next = p.next;
            if (p == h) tail.next = p.next;
            if (p == tail) tail = f;
        }

        void deleteAll() { clear(); }
        void deleteAt(int k) { delete(getNode(k)); }

        void deleteFirstAgeLessThan6() {
            if (isEmpty()) return;
            CNode h = head(), p = h;
            do {
                if (p.age < 6) { delete(p); return; }
                p = p.next;
            } while (p != h);
        }

        void deleteNodeAfterPosition(int k) {
            CNode p = getNode(k);
            if (p != null) delete(p.next);
        }

        void deleteAfterSecondNodeAgeLessThan9() {
            if (isEmpty()) return;
            int count = 0;
            CNode h = head(), p = h;
            do {
                if (p.age < 9) {
                    count++;
                    if (count == 2) { delete(p.next); return; }
                }
                p = p.next;
            } while (p != h);
        }

        void deleteThirdNodeAgeLessThan6() {
            if (isEmpty()) return;
            int count = 0;
            CNode h = head(), p = h;
            do {
                if (p.age < 6) {
                    count++;
                    if (count == 3) { delete(p); return; }
                }
                p = p.next;
            } while (p != h);
        }

        void deleteFirstAfterPositionK(int k) {
            CNode p = getNode(k);
            if (p != null) delete(p.next);
        }

        void deleteAfterNodePriceGreaterThan(double xPrice) {
            if (isEmpty()) return;
            CNode h = head(), p = h;
            do {
                if (p.price > xPrice) { delete(p.next); return; }
                p = p.next;
            } while (p != h);
        }

        void deleteTwoLastNodesAgeGreaterThan5() {
            int n = countNode(), deleted = 0;
            for (int i = n - 1; i >= 0 && deleted < 2; i--) {
                CNode p = getNode(i);
                if (p != null && p.age > 5) {
                    delete(p);
                    deleted++;
                }
            }
        }

        void deleteSecondBiggestAge() {
            if (isEmpty()) return;
            int max = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
            CNode h = head(), p = h;
            do {
                if (p.age > max) {
                    second = max;
                    max = p.age;
                } else if (p.age > second && p.age < max) {
                    second = p.age;
                }
                p = p.next;
            } while (p != h);
            if (second == Integer.MIN_VALUE) return;
            delete(searchByAge(second));
        }

        void swapData(CNode a, CNode b) {
            String tName = a.name; a.name = b.name; b.name = tName;
            int tAge = a.age; a.age = b.age; b.age = tAge;
            double tPrice = a.price; a.price = b.price; b.price = tPrice;
        }

        int countNode() {
            if (isEmpty()) return 0;
            int count = 0;
            CNode h = head(), p = h;
            do {
                count++;
                p = p.next;
            } while (p != h);
            return count;
        }

        void sortByName() {
            int n = countNode();
            for (int i = 0; i < n - 1; i++)
                for (int j = i + 1; j < n; j++) {
                    CNode a = getNode(i), b = getNode(j);
                    if (a.name.compareTo(b.name) > 0) swapData(a, b);
                }
        }

        void sortByAge() {
            int n = countNode();
            for (int i = 0; i < n - 1; i++)
                for (int j = i + 1; j < n; j++) {
                    CNode a = getNode(i), b = getNode(j);
                    if (a.age > b.age) swapData(a, b);
                }
        }

        void sortByPrice() {
            int n = countNode();
            for (int i = 0; i < n - 1; i++)
                for (int j = i + 1; j < n; j++) {
                    CNode a = getNode(i), b = getNode(j);
                    if (a.price > b.price) swapData(a, b);
                }
        }

        void sortByFor() { sortByAge(); }

        void sortFirst3ByAge() {
            int n = Math.min(3, countNode());
            for (int i = 0; i < n - 1; i++)
                for (int j = i + 1; j < n; j++) {
                    CNode a = getNode(i), b = getNode(j);
                    if (a.age > b.age) swapData(a, b);
                }
        }

        void swapMinMaxAge() {
            if (isEmpty()) return;
            CNode h = head(), min = h, max = h, p = h.next;
            while (p != h) {
                if (p.age < min.age) min = p;
                if (p.age > max.age) max = p;
                p = p.next;
            }
            swapData(min, max);
        }

        void swapSecondMaxWithFirstMin() {
            if (countNode() < 2) return;
            CNode min = head(), max = head(), secondMax = null;
            CNode h = head(), p = h;
            do {
                if (p.age < min.age) min = p;
                if (p.age > max.age) {
                    secondMax = max;
                    max = p;
                } else if (p != max && (secondMax == null || p.age > secondMax.age) && p.age < max.age) {
                    secondMax = p;
                }
                p = p.next;
            } while (p != h);
            if (secondMax != null) swapData(secondMax, min);
        }

        void traverse() {
            if (isEmpty()) return;
            CNode h = head(), p = h;
            do {
                System.out.print("(" + p.name + "," + p.age + "," + p.price + ") ");
                p = p.next;
            } while (p != h);
            System.out.println();
        }

        void replaceNode(int k, String name, int age, double price) {
            CNode p = getNode(k);
            if (p == null) return;
            p.name = name;
            p.age = age;
            p.price = price;
        }

        void reverse() {
            if (isEmpty() || tail.next == tail) return;
            CircularList tmp = new CircularList();
            CNode h = head(), p = h;
            do {
                tmp.addFirst(p.name, p.age, p.price);
                p = p.next;
            } while (p != h);
            tail = tmp.tail;
        }

        void append(CircularList other) {
            if (other.isEmpty()) return;
            CNode h = other.head(), p = h;
            do {
                addLast(p.name, p.age, p.price);
                p = p.next;
            } while (p != h);
        }

        void changeFirstName(String newName) {
            if (!isEmpty()) head().name = newName;
        }
    }

    // ===================== STACK =====================
    static class StackList {
        Node top;

        boolean isEmpty() { return top == null; }
        void clear() { top = null; }

        void push(String name, int age, double price) {
            Node p = new Node(name, age, price);
            p.next = top;
            if (top != null) top.prev = p;
            top = p;
        }

        void addFirst(String name, int age, double price) { push(name, age, price); }
        void addIfAgeGreaterThan4(String name, int age, double price) { if (age > 4) push(name, age, price); }

        Node pop() {
            if (isEmpty()) return null;
            Node p = top;
            top = top.next;
            if (top != null) top.prev = null;
            p.next = null;
            return p;
        }

        Node peek() { return top; }

        Node getNode(int k) {
            int i = 0;
            for (Node p = top; p != null; p = p.next) {
                if (i == k) return p;
                i++;
            }
            return null;
        }

        Node getMaxAgeNode() {
            if (isEmpty()) return null;
            Node max = top;
            for (Node p = top.next; p != null; p = p.next) if (p.age > max.age) max = p;
            return max;
        }

        Node searchByName(String x) {
            for (Node p = top; p != null; p = p.next) if (p.name.equals(x)) return p;
            return null;
        }

        Node searchByAge(int x) {
            for (Node p = top; p != null; p = p.next) if (p.age == x) return p;
            return null;
        }

        Node searchByPrice(double x) {
            for (Node p = top; p != null; p = p.next) if (p.price == x) return p;
            return null;
        }

        void delete(Node q) {
            if (q == null || isEmpty()) return;
            if (q == top) { pop(); return; }
            Node f = null, p = top;
            while (p != null) {
                if (p == q) break;
                f = p;
                p = p.next;
            }
            if (p == null) return;
            f.next = p.next;
            if (p.next != null) p.next.prev = f;
        }

        void deleteAll() { clear(); }
        void deleteAt(int k) { delete(getNode(k)); }

        int countNode() {
            int count = 0;
            for (Node p = top; p != null; p = p.next) count++;
            return count;
        }

        void traverse() {
            for (Node p = top; p != null; p = p.next)
                System.out.print("(" + p.name + "," + p.age + "," + p.price + ") ");
            System.out.println();
        }

        void reverse() {
            StackList tmp = new StackList();
            while (!isEmpty()) {
                Node p = pop();
                tmp.push(p.name, p.age, p.price);
            }
            top = tmp.top;
        }

        void changeFirstName(String newName) {
            if (top != null) top.name = newName;
        }
    }

    // ===================== QUEUE =====================
    static class QueueList {
        Node front, rear;

        boolean isEmpty() { return front == null; }
        void clear() { front = rear = null; }

        void enqueue(String name, int age, double price) {
            Node p = new Node(name, age, price);
            if (isEmpty()) {
                front = rear = p;
                return;
            }
            rear.next = p;
            p.prev = rear;
            rear = p;
        }

        void addLast(String name, int age, double price) { enqueue(name, age, price); }
        void addIfAgeGreaterThan4(String name, int age, double price) { if (age > 4) enqueue(name, age, price); }

        Node dequeue() {
            if (isEmpty()) return null;
            Node p = front;
            front = front.next;
            if (front == null) rear = null;
            else front.prev = null;
            p.next = null;
            return p;
        }

        Node peek() { return front; }

        Node getNode(int k) {
            int i = 0;
            for (Node p = front; p != null; p = p.next) {
                if (i == k) return p;
                i++;
            }
            return null;
        }

        Node getMaxAgeNode() {
            if (isEmpty()) return null;
            Node max = front;
            for (Node p = front.next; p != null; p = p.next) if (p.age > max.age) max = p;
            return max;
        }

        Node searchByName(String x) {
            for (Node p = front; p != null; p = p.next) if (p.name.equals(x)) return p;
            return null;
        }

        Node searchByAge(int x) {
            for (Node p = front; p != null; p = p.next) if (p.age == x) return p;
            return null;
        }

        Node searchByPrice(double x) {
            for (Node p = front; p != null; p = p.next) if (p.price == x) return p;
            return null;
        }

        void delete(Node q) {
            if (q == null || isEmpty()) return;
            if (q == front) { dequeue(); return; }
            Node f = null, p = front;
            while (p != null) {
                if (p == q) break;
                f = p;
                p = p.next;
            }
            if (p == null) return;
            f.next = p.next;
            if (p.next != null) p.next.prev = f;
            else rear = f;
        }

        void deleteAll() { clear(); }
        void deleteAt(int k) { delete(getNode(k)); }

        int countNode() {
            int count = 0;
            for (Node p = front; p != null; p = p.next) count++;
            return count;
        }

        void traverse() {
            for (Node p = front; p != null; p = p.next)
                System.out.print("(" + p.name + "," + p.age + "," + p.price + ") ");
            System.out.println();
        }

        void append(QueueList other) {
            for (Node p = other.front; p != null; p = p.next)
                enqueue(p.name, p.age, p.price);
        }

        void changeFirstName(String newName) {
            if (front != null) front.name = newName;
        }
    }
}
