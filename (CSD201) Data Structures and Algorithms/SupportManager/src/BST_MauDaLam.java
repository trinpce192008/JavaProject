/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TRI
 */
public class BST_MauDaLam {
//delete by copying
//breadth-first-search on the BST, but ONLY visit nodes that has Bird's color higher than 5
//delete by merge
//find min/max follow a field
//find and remove node with a field
//Preorder và in ra Node có Type > 5
//Node p là node thứ 6 khi duyệt theo inoder, delete by copy p, tìm cha của p và in ra
// Node p là node thứ x có 1 nhánh phải không null duyệt theo BFS, rotate p (Hint dùng search x child rồi sửa hasnot)
//count in range
    public int countInRange(String minId, String maxId) {
        int count = 0;

        if (root == null) {
            return 0;
        }

        java.util.Stack<TreeNode> st = new java.util.Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            if (node == null) {
                continue;
            }

            int cmpMin = node.song.songId.compareTo(minId);
            int cmpMax = node.song.songId.compareTo(maxId);

            if (cmpMin < 0) {
                st.push(node.right);
            } else if (cmpMax > 0) {
                st.push(node.left);
            } else {
                count++;
                st.push(node.left);
                st.push(node.right);
            }
        }
        return count;
    }
    
    //delete by merge
    public void deleteByMerging(int x) {
    TreeNode p = root, parent = null;

    // Tìm node cần xóa
    while (p != null && p.info != x) {
        parent = p;
        if (x < p.info) {
            p = p.left;
        } else {
            p = p.right;
        }
    }

    // Không tìm thấy
    if (p == null) {
        return;
    }

    TreeNode node;

    // Trường hợp 1: p không có con trái
    if (p.left == null) {
        node = p.right;
    }
    // Trường hợp 2: p không có con phải
    else if (p.right == null) {
        node = p.left;
    }
    // Trường hợp 3: p có đủ 2 con
    else {
        node = p.left;              // lấy cây con trái lên thay
        TreeNode rm = node;         // rightmost node of left subtree

        while (rm.right != null) {
            rm = rm.right;
        }

        rm.right = p.right;         // nối cây con phải vào node phải nhất
    }

    // Ghép node mới vào parent
    if (parent == null) {
        root = node;                // xóa root
    } else if (parent.left == p) {
        parent.left = node;
    } else {
        parent.right = node;
    }
}
//    breadth-first-search on the BST, but ONLY visit nodes that has Bird's color higher than 5
            Node p = root;
        if (p == null) {
            return;
        }
        Queue_DoublyAll q = new Queue_DoublyAll();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.getInfo().color > 5) {
                fvisit(r, f);
            }

            if (r.left != null) {
                q.enqueue(r.left);
            }

            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
        
//        find min/max follow a field
        Node res;

public Node findMinByAge(Node root) {
    res = null;
    dfsMinAge(root);
    return res;
}

private void dfsMinAge(Node p) {
    if (p == null) return;

    if (res == null || p.info.age < res.info.age) {
        res = p;
    }

    dfsMinAge(p.left);
    dfsMinAge(p.right);
}
//find max

Node res;

public Node findMaxByAge(Node root) {
    res = null;
    dfsMaxAge(root);
    return res;
}

private void dfsMaxAge(Node p) {
    if (p == null) return;

    if (res == null || p.info.age > res.info.age) {
        res = p;
    }

    dfsMaxAge(p.left);
    dfsMaxAge(p.right);
}

// find and remove node with a field
   public TreeNode remove(TreeNode root, String id) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        class Local {
            public TreeNode deleByCopy(String xName, TreeNode root) {
                
                
                // p is leaf node
                if (p.left == null && p.right == null) {
                    if (f == null) // p=root
                    {
                        root = null;
                    } else {
                        if (p == f.left) {
                            f.left = null;
                        }
                        f.right = null;
                    }
                    return root;
                }

                // p has left child only
                if (p.left != null && p.right == null) {
                    if (f == null) // p=root
                    {
                        root = p.left;
                    } else {
                        if (p == f.left) {
                            f.left = p.left;
                        }
                        f.right = p.left;
                    }
                    return root;
                }

                // p has right child only
                if (p.left == null && p.right != null) {
                    if (f == null) // p=root
                    {
                        root = p.right;
                    } else {
                        if (p == f.left) {
                            f.left = p.right;
                        }
                        f.right = p.right;
                    }
                    return root;
                }

                // p has both 2 children
                if (p.left != null && p.right != null) {// find the right most node
                    TreeNode q = p.left;
                    TreeNode frp, rp;
                    frp = null;
                    rp = q;
                    while (rp.right != null) {
                        frp = rp;
                        rp = rp.right;
                    }
                    // rp is the right most node on the left child
                    p.info = rp.info;
                    if (frp == null) // rp=q
                    {
                        p.left = q.left;
                    } else {
                        frp.right = rp.left;
                    }
                }
                return root;
            }
        }
        Local run = new Local();
        if(root != null){
            return run.deleByCopy(id, root);
        }
        //---------------------------------------------------------
        return null;
    }
   
//   Preorder và in ra Node có Type > 5
   class Local {
    public void preOrderTraversal() throws Exception {
        if (root == null) return;

        java.util.Stack<Node> st = new java.util.Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            Node p = st.pop();
           if(p != null && p.info.type > 5) fvisit(p,f);

            if (p.right != null) st.push(p.right);
            if (p.left != null) st.push(p.left);
        }
    }

}
    Local run = new Local();
    run.preOrderTraversal();

    
//Node p là node thứ 6 khi duyệt theo inoder, delete by copy p, tìm cha của p và in ra
    
    private void deleteByCopyCurrentAndParent(Node current, Node parent) {
        // case 1: leaf
        if (current.left == null && current.right == null) {
            if (parent == null) root = null;
            else if (parent.left == current) parent.left = null;
            else parent.right = null;
            return;
        }

        // case 2: one child
        if (current.left == null || current.right == null) {
            Node child = (current.left != null) ? current.left : current.right;
            if (parent == null) root = child;
            else if (parent.left == current) parent.left = child;
            else parent.right = child;
            return;
        }

        // case 3: two children -> copy predecessor (max of left subtree)
        Node predParent = current;
        Node pred = current.left;

        while (pred.right != null) {
            predParent = pred;
            pred = pred.right;
        }

        current.info = pred.info;

        if (predParent == current) {
            predParent.left = pred.left;
        } else {
            predParent.right = pred.left;
        }
    }
public Node getFatherByAge(int age) {
        Node current = root;
        Node parent = null;

        while (current != null) {
            if (age == current.info.color) return parent;
            parent = current;
            if (age < current.info.color) current = current.left;
            else current = current.right;
        }
        return null;
    }
}
Local run = new Local();
List <Cape> result = run.inOrderTraversal();
Cape x = result.get(5);
Node fa = run.getFatherByAge(x.color);
run.deleteByCopyAge(x.color);

f.writeBytes(fa.info + "\r\n ");
    //------------------------------------------------------------------------------------
}


// Node p là node thứ x có 1 nhánh phải không null duyệt theo BFS, rotate p
class Local {
     
  boolean hasOneChild(Node p) {
        return p != null &&
               ((p.left != null && p.right != null) ||
                (p.left == null && p.right != null));
    }

Node searchNthOneChildBFS(Node root, int n) {
        if (root == null || n <= 0) return null;

        java.util.Queue<Node> q = new java.util.LinkedList<>();
        q.add(root);
        int count = 0;

        while (!q.isEmpty()) {
            Node p = q.poll();

            if (hasOneChild(p)) {
                count++;
                if (count == n) return p;
            }

            if (p.left != null) q.add(p.left);
            if (p.right != null) q.add(p.right);
        }
        return null;
    }
    public Node rotateLeft(Node p) {
        if (p == null || p.right == null) return p;
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        return q;
    }
    public Node getFatherByAge(int age) {
        Node current = root;
        Node parent = null;

        while (current != null) {
            if (age == current.info.color) return parent;
            parent = current;
            if (age < current.info.color) current = current.left;
            else current = current.right;
        }
        return null;
    }
}
 Local run = new Local();
Node p = run.searchNthOneChildBFS(root, 5);

Node fa = run.getFatherByAge(p.info.color);

Node x = run.rotateLeft(p);

if (fa == null) {
    root = x;
} else if (fa.left == p) {
    fa.left = x;
} else {
    fa.right = x;
}