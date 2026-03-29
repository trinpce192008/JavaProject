import java.util.*;
import java.io.*;

public class MyTree {
    TNode root;

    MyTree() { root = null; }

    void clear() { root = null; }

    void loadData(int k) {
        String[] codes = Lib.readLineToStrArray("data.txt", k);
        String[] owners = Lib.readLineToStrArray("data.txt", k + 1);
        String[] pri = Lib.readLineToStrArray("data.txt", k + 2);

        int n = Math.min(codes.length, Math.min(owners.length, pri.length));
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(pri[i]);
            insert(new Ticket(codes[i], owners[i], p));
        }
    }

    // preorder traverse
    void ftraverse(RandomAccessFile f, TNode p) throws Exception {
        if (p == null) return;
        f.writeBytes(p.info.owner + "-" + p.info.priority + "     ");
        ftraverse(f, p.left);
        ftraverse(f, p.right);
    }

    // F1: insert nếu priority>0 và code không rỗng; key=priority; duplicate -> ignore
    void insert(Ticket x) {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (x == null) {
            return;
        }
        if (x.priority <= 0) {
            return;
        }
        if (x.code == null || x.code.trim().isEmpty()) {
            return;
        }

        TNode q = new TNode(x);

        if (root == null) {
            root = q;
            return;
        }

        TNode p = root, parent = null;
        while (p != null) {
            if (x.priority == p.info.priority) {
                return;
            }
            parent = p;
            if (x.priority < p.info.priority) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        if (x.priority < parent.info.priority) {
            parent.left = q;
        } else {
            parent.right = q;
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }

    // F2: tìm node p gần nhất theo |diff| (hòa -> chọn priority nhỏ hơn), gắn t vào trái/phải nếu null và đúng BST
    void f2Logic(Ticket t) {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (t == null) {
            return;
        }
        if (root == null) {
            return;
        }

        int key = t.priority;

        TNode best = null;
        int bestDiff = Integer.MAX_VALUE;
        int bestPri = Integer.MAX_VALUE;

        Queue<TNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TNode cur = q.remove();
            int diff = Math.abs(cur.info.priority - key);

            if (diff < bestDiff || (diff == bestDiff && cur.info.priority < bestPri)) {
                best = cur;
                bestDiff = diff;
                bestPri = cur.info.priority;
            }

            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }

        if (best == null) {
            return;
        }

        if (key < best.info.priority) {
            if (best.left == null) {
                best.left = new TNode(t);
            }
        } else if (key > best.info.priority) {
            if (best.right == null) {
                best.right = new TNode(t);
            }
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }

    // F3: tổng priority của các node ở level=2 (root level 0)
    int f3Logic() {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (root == null) {
            return 0;
        }

        Queue<TNode> q = new LinkedList<>();
        Queue<Integer> lv = new LinkedList<>();

        q.add(root);
        lv.add(0);

        int sum = 0;

        while (!q.isEmpty()) {
            TNode cur = q.remove();
            int level = lv.remove();

            if (level == 2) {
                sum += cur.info.priority;
            }

            if (level < 2) {
                if (cur.left != null) {
                    q.add(cur.left);
                    lv.add(level + 1);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                    lv.add(level + 1);
                }
            }
        }

        if (sum > 0) {
            return sum;
        }
        return -1;
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }

    // F4: xóa tất cả node có priority trong [a,b]. Node 2 con: BẮT BUỘC dùng in-order successor (min right subtree)
    void f4Logic(int a, int b) {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        root = deleteRange(root, a, b);
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }

    private TNode deleteRange(TNode p, int a, int b) {
        if (p == null) {
            return null;
        }

        p.left = deleteRange(p.left, a, b);
        p.right = deleteRange(p.right, a, b);

        while (p != null && p.info.priority >= a && p.info.priority <= b) {
            p = deleteOneNode(p);              
            p = deleteRange(p, a, b);          
        }
        return p;
    }

    private TNode deleteOneNode(TNode p) {
        if (p == null) {
            return null;
        }

        if (p.left == null) {
            return p.right;
        }
        if (p.right == null) {
            return p.left;
        }

        TNode succParent = p;
        TNode succ = p.right;
        while (succ.left != null) {
            succParent = succ;
            succ = succ.left;
        }

        p.info = succ.info;

        if (succParent == p) {
            succParent.right = succ.right;
        } else {
            succParent.left = succ.right;
        }

        return p;
    }
    // ===== wrappers f1..f4 =====
    void f1() throws Exception {
        clear();
        loadData(0);
        String fname = "f1.txt";
        File g = new File(fname);
        if (g.exists()) g.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f, root); f.writeBytes("\r\n");
        f.close();
    }

    void f2() throws Exception {
        clear();
        loadData(0);
        Ticket t = new Ticket("T88", "Zoe", 26);
        String fname = "f2.txt";
        File g = new File(fname);
        if (g.exists()) g.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f, root); f.writeBytes("\r\n");
        f2Logic(t);
        ftraverse(f, root); f.writeBytes("\r\n");
        f.close();
    }

    void f3() throws Exception {
        clear();
        loadData(0);
        String fname = "f3.txt";
        File g = new File(fname);
        if (g.exists()) g.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f, root); f.writeBytes("\r\n");
        int ans = f3Logic();
        f.writeBytes(ans + "\r\n");
        f.close();
    }

    void f4() throws Exception {
        clear();
        loadData(0);
        int a = 10, b = 30;
        String fname = "f4.txt";
        File g = new File(fname);
        if (g.exists()) g.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f, root); f.writeBytes("\r\n");
        f4Logic(a, b);
        ftraverse(f, root); f.writeBytes("\r\n");
        f.close();
    }
}
