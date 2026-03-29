import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;
    int size;

    MyList() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    // Traverse circular list safely (exactly `size` nodes)
    void ftraverse(RandomAccessFile f) throws Exception {
        if (isEmpty()) { f.writeBytes("\r\n"); return; }
        Node p = head;
        for (int i = 0; i < size; i++) {
            f.writeBytes(p.info.owner + "-" + p.info.priority + "     ");
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) {
        String[] codes = Lib.readLineToStrArray("data.txt", k);
        String[] owners = Lib.readLineToStrArray("data.txt", k + 1);
        String[] pri = Lib.readLineToStrArray("data.txt", k + 2);

        int n = Math.min(codes.length, Math.min(owners.length, pri.length));
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(pri[i]);
            addLast(new Ticket(codes[i], owners[i], p));
        }
    }

    // F1: chỉ thêm nếu 1<=priority<=100 và owner không bắt đầu bằng '#'
    void addLast(Ticket x) {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (x == null) {
            return;
        }
        if (x.priority < 1 || x.priority > 100) {
            return;
        }
        if (x.owner == null) {
            return;
        }
        if (x.owner.length() > 0 && x.owner.charAt(0) == '#') {
            return;
        }

        Node q = new Node(x);

        if (isEmpty()) {
            head = tail = q;
            q.next = q;
            size = 1;
            return;
        }

        q.next = head;
        tail.next = q;
        tail = q;
        size++;
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }

    // ===== Helpers (you MAY use) =====
    boolean isPrime(int x) {
        if (x <= 1) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;
        for (int i = 3; i * i <= x; i += 2) if (x % i == 0) return false;
        return true;
    }

    // F2: chèn t NGAY TRƯỚC node đầu tiên có priority là bội số của 5; nếu không có thì chèn cuối.
    void f2Logic(Ticket t) {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (t == null) {
            return;
        }

        if (isEmpty()) {
            Node q = new Node(t);
            head = tail = q;
            q.next = q;
            size = 1;
            return;
        }

        Node prev = tail;
        Node cur = head;

        for (int i = 0; i < size; i++) {
            if (cur.info.priority % 5 == 0) {
                Node q = new Node(t);

                q.next = cur;
                prev.next = q;

                if (cur == head) {
                    head = q;
                }

                size++;
                return;
            }
            prev = cur;
            cur = cur.next;
        }

        addLast(t);
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }

    // F3: trả về độ dài owner lớn nhất
    int f3Logic() {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        int maxLen = 0;
        Node p = head;

        for (int i = 0; i < size; i++) {
            if (p.info.owner != null) {
                int len = p.info.owner.length();
                if (len > maxLen) {
                    maxLen = len;
                }
            }
            p = p.next;
        }
        if (maxLen > 0) {
            return maxLen;
        }

        return -1;
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }

    // F4: xóa tất cả node có priority là số nguyên tố (dùng oldSize-based loop hoặc điều kiện dừng an toàn)
    void f4Logic() {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (isEmpty()) {
            return;
        }
        int oldSize = size;
        Node prev = tail;
        Node cur = head;

        for (int i = 0; i < oldSize; i++) {
            Node next = cur.next;

            if (isPrime(cur.info.priority)) {
                if (size == 1) {
                    clear();
                    return;
                }

                prev.next = next;

                if (cur == head) {
                    head = next;
                }
                if (cur == tail) {
                    tail = prev;
                }

                size--;
            } else {
                prev = cur;
            }

            cur = next;
            if (isEmpty()) {
                return;
            }
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }

    // ===== wrappers f1..f4 (do NOT change output file names) =====
    void f1() throws Exception {
        clear();
        loadData(0);
        String fname = "f1.txt";
        File g = new File(fname);
        if (g.exists()) g.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

    void f2() throws Exception {
        clear();
        loadData(0);
        Ticket t = new Ticket("T99", "Tina", 37);
        String fname = "f2.txt";
        File g = new File(fname);
        if (g.exists()) g.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f2Logic(t);
        ftraverse(f);
        f.close();
    }

    void f3() throws Exception {
        clear();
        loadData(0);
        String fname = "f3.txt";
        File g = new File(fname);
        if (g.exists()) g.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        int ans = f3Logic();
        f.writeBytes(ans + "\r\n");
        f.close();
    }

    void f4() throws Exception {
        clear();
        loadData(0);
        String fname = "f4.txt";
        File g = new File(fname);
        if (g.exists()) g.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f4Logic();
        ftraverse(f);
        f.close();
    }
}
