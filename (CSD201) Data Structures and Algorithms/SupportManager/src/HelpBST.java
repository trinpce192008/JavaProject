/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author TRI
 */
public class HelpBST {
    /*
[01] INSERT      -> insertByString / insertByAge / insertByPrice / insert with return null
[02] SEARCH      -> searchByString / searchByAge / searchByPrice / 
[03] TRAVERSAL   -> breadthFirstTraversal / preOrderTraversal / postOrderTraversal / inOrderTraversal
[04] COUNT       -> countNodes / countNodesHave1Child / countNodesHave2Children / height (EDGE)
[05] GET         -> getFatherByString / getFatherByAge / getNodeByString / getNodeByAge
[06] OTHER       -> levelOfNodeByString / levelOfNodeByAge / factor / balanceFactor / printLevelAllNodes
[07] DELETE      -> deleteByCopyString / deleteByCopyAge / deleteByCopyPrice / deleteByNode
[08] ROTATE      -> rotateLeft / rotateRight / rotateLeftByString / rotateRightByString / Set Tree after rotate
[09] BALANCE     -> balanceSimpleArrayList / balanceBST
[10] MAIN        -> main
*/
    
    /*
     * ===================== MUC LUC / TABLE OF CONTENTS =====================
     * 1. NODE
     * 2. CONDITION CHECK
     *    - isLeaf
     *    - hasOneChild
     *    - hasTwoChildren
     *
     * 3. BFS SEARCH
     *    - searchNthLeafBFS
     *    - searchNthOneChildBFS
     *    - searchNthTwoChildrenBFS
     *      -findFatherByRate BFS
     * 4. PREORDER SEARCH
     *    - searchNthLeafPreorder
     *    - searchNthOneChildPreorder
     *    - searchNthTwoChildrenPreorder
     *
     * 5. INORDER SEARCH
     *    - searchNthLeafInorder
     *    - searchNthOneChildInorder
     *    - searchNthTwoChildrenInorder
     *
     * 6. POSTORDER SEARCH
     *    - searchNthLeafPostorder
     *    - searchNthOneChildPostorder
     *    - searchNthTwoChildrenPostorder
     *
     * 7. RESET COUNTER
     *    - resetAllCounters
     * ==============================================================
     */
    import java.util.*;

/*
 * BST Toolkit - one file
 * Data mẫu: code (String), age (int), price (double)
 *
 * Lưu ý:
 * 1) Các hàm byString dùng cho cây BST sắp theo code.
 * 2) Các hàm byAge dùng cho cây BST sắp theo age.
 * 3) Các hàm byPrice dùng cho cây BST sắp theo price.
 * 4) Không nên trộn insert/search/delete khác key trên cùng 1 cây.
 */
public class BSTToolkit {

    // =========================
    // DATA + NODE
    // =========================
    static class Info {
        String code;
        int age;
        double price;

        Info(String code, int age, double price) {
            this.code = code;
            this.age = age;
            this.price = price;
        }

        @Override
        public String toString() {
            return "(" + code + ", age=" + age + ", price=" + price + ")";
        }
    }

    static class Node {
        Info info;
        Node left, right;

        Node(Info info) {
            this.info = info;
        }
    }

    Node root;

    // =========================
    // VISIT
    // =========================
    void visit(Node p) {
        if (p != null) {
            System.out.print(p.info + " ");
        }
    }

    // =========================
    // INSERT
    // =========================
//insert with return null 
    private TreeNode insert(TreeNode root, Order order) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        TreeNode newNode = new TreeNode(order);
        TreeNode result = root;
if(root == null){
    result = newNode;
}


        TreeNode current = root;
        TreeNode parent = null;
        boolean found = true;

        while (current != null) {
            parent = current;
            int cmp = (newNode.info.orderID).compareTo(current.info.orderID);
            if (cmp < 0) {
                current = current.left;
                found = false;
            } else if (cmp > 0) {
                current = current.right;
                found = false;
            } else {
                current.info = order; //DUBPLY
                found = true;
            }
        }
        if (!found) {
            if (order.orderID.compareTo(parent.info.orderID) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            result = root;
        }
        if (result != null) return result;
        //---------------------------------------------------------
        return null;
    }
    
    
    // insert by String: code
    public void insertByString(String code, int age, double price) {
        Info x = new Info(code, age, price);
        Node newNode = new Node(x);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            int cmp = code.compareTo(current.info.code);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return; // duplicate code
            }
        }

        if (code.compareTo(parent.info.code) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    // insert by integer: age
    public void insertByAge(String code, int age, double price) {
        Info x = new Info(code, age, price);
        Node newNode = new Node(x);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (age < current.info.age) {
                current = current.left;
            } else if (age > current.info.age) {
                current = current.right;
            } else {
                return; // duplicate age
            }
        }

        if (age < parent.info.age) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    // insert by double: price
    public void insertByPrice(String code, int age, double price) {
        Info x = new Info(code, age, price);
        Node newNode = new Node(x);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (price < current.info.price) {
                current = current.left;
            } else if (price > current.info.price) {
                current = current.right;
            } else {
                return; // duplicate price
            }
        }

        if (price < parent.info.price) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    // insert have age > 4 use BFT
    // Ý: duyệt breadth-first, gặp node đầu tiên có chỗ trống và age > 4 thì chèn vào chỗ trống đó
    // Đây KHÔNG phải chèn BST chuẩn, mà chèn kiểu tree theo BFT-condition
    public void insertAgeGreaterThan4UseBFT(String code, int age, double price) {
        Info x = new Info(code, age, price);
        Node newNode = new Node(x);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue_DoublyAll<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node p = q.poll();

            if (p.info.age > 4) {
                if (p.left == null) {
                    p.left = newNode;
                    return;
                }
                if (p.right == null) {
                    p.right = newNode;
                    return;
                }
            }

            if (p.left != null) q.offer(p.left);
            if (p.right != null) q.offer(p.right);
        }
    }

    // =========================
    // SEARCH
    // =========================

    // search by string: code
    public Node searchByString(String code) {
        Node current = root;
        while (current != null) {
            int cmp = code.compareTo(current.info.code);
            if (cmp == 0) return current;
            if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null;
    }

    // search by integer: age
    public Node searchByAge(int age) {
        Node current = root;
        while (current != null) {
            if (age == current.info.age) return current;
            if (age < current.info.age) current = current.left;
            else current = current.right;
        }
        return null;
    }

    // search by double: price
    public Node searchByPrice(double price) {
        Node current = root;
        while (current != null) {
            if (price == current.info.price) return current;
            if (price < current.info.price) current = current.left;
            else current = current.right;
        }
        return null;
    }

    // =========================
    // TRAVERSAL - RETURN LIST
    // =========================

    // breadth-first traversal
    public List<Info> breadthFirstTraversal() {
        List<Info> result = new ArrayList<>();
        if (root == null) return result;

        Queue_DoublyAll<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node p = q.poll();
            result.add(p.info);

            if (p.left != null) q.offer(p.left);
            if (p.right != null) q.offer(p.right);
        }
        return result;
    }

    // preorder - non recursive
    public List<Info> preOrderTraversal() {
        List<Info> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Node> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            Node p = st.pop();
            result.add(p.info);

            if (p.right != null) st.push(p.right);
            if (p.left != null) st.push(p.left);
        }
        return result;
    }

    // postorder - non recursive (2 stacks)
    public List<Info> postOrderTraversal() {
        List<Info> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.push(root);

        while (!st1.isEmpty()) {
            Node p = st1.pop();
            st2.push(p);

            if (p.left != null) st1.push(p.left);
            if (p.right != null) st1.push(p.right);
        }

        while (!st2.isEmpty()) {
            result.add(st2.pop().info);
        }
        return result;
    }

    // inorder - non recursive
    public List<Info> inOrderTraversal() {
        List<Info> result = new ArrayList<>();
        Stack<Node> st = new Stack<>();
        Node current = root;

        while (current != null || !st.isEmpty()) {
            while (current != null) {
                st.push(current);
                current = current.left;
            }

            current = st.pop();
            result.add(current.info);
            current = current.right;
        }
        return result;
    }

    // use BFS change second node have age >= 5 to age = 10
    public void changeSecondNodeAgeGE5To10ByBFS() {
        if (root == null) return;

        Queue_DoublyAll<Node> q = new LinkedList<>();
        q.offer(root);
        int count = 0;

        while (!q.isEmpty()) {
            Node p = q.poll();

            if (p.info.age >= 5) {
                count++;
                if (count == 2) {
                    p.info.age = 10;
                    return;
                }
            }

            if (p.left != null) q.offer(p.left);
            if (p.right != null) q.offer(p.right);
        }
    }

    // preorder with condition: 3 <= price <= 5
    public List<Info> preOrderPriceFrom3To5() {
        List<Info> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Node> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            Node p = st.pop();

            if (p.info.price >= 3 && p.info.price <= 5) {
                result.add(p.info);
            }

            if (p.right != null) st.push(p.right);
            if (p.left != null) st.push(p.left);
        }
        return result;
    }

    // =========================
    // COUNT
    // =========================

    // count all nodes
    public int countNodes() {
        if (root == null) return 0;

        int count = 0;
        Stack<Node> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            Node p = st.pop();
            count++;

            if (p.right != null) st.push(p.right);
            if (p.left != null) st.push(p.left);
        }
        return count;
    }

    // count node have 1 child
    public int countNodesHave1Child() {
        if (root == null) return 0;

        int count = 0;
        Stack<Node> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            Node p = st.pop();

            boolean left = p.left != null;
            boolean right = p.right != null;
            if ((left && !right) || (!left && right)) {
                count++;
            }

            if (p.right != null) st.push(p.right);
            if (p.left != null) st.push(p.left);
        }
        return count;
    }

    // count node have exactly 2 child
    public int countNodesHave2Children() {
        if (root == null) return 0;

        int count = 0;
        Stack<Node> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            Node p = st.pop();

            if (p.left != null && p.right != null) {
                count++;
            }

            if (p.right != null) st.push(p.right);
            if (p.left != null) st.push(p.left);
        }
        return count;
    }

    // count height of tree EDGE
    public int height(Node p) {
        if (p == null) return -1;
        return 1 + Math.max(height(p.left), height(p.right));
    }

    public int height() {
        return height(root);
    }

    // =========================
    // GET
    // =========================

    // get father by string
    public Node getFatherByString(String code) {
        Node current = root;
        Node parent = null;

        while (current != null) {
            int cmp = code.compareTo(current.info.code);
            if (cmp == 0) return parent;
            parent = current;
            if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null;
    }

    // get father by integer
    public Node getFatherByAge(int age) {
        Node current = root;
        Node parent = null;

        while (current != null) {
            if (age == current.info.age) return parent;
            parent = current;
            if (age < current.info.age) current = current.left;
            else current = current.right;
        }
        return null;
    }

    // get node by string
    public Node getNodeByString(String code) {
        return searchByString(code);
    }

    // get node by integer
    public Node getNodeByAge(int age) {
        return searchByAge(age);
    }

    // =========================
    // OTHER
    // =========================

    // calculate level of node by string (root level = 0)
    public int levelOfNodeByString(String code) {
        Node current = root;
        int level = 0;

        while (current != null) {
            int cmp = code.compareTo(current.info.code);
            if (cmp == 0) return level;
            if (cmp < 0) current = current.left;
            else current = current.right;
            level++;
        }
        return -1;
    }

    // calculate level of node by age (root level = 0)
    public int levelOfNodeByAge(int age) {
        Node current = root;
        int level = 0;

        while (current != null) {
            if (age == current.info.age) return level;
            if (age < current.info.age) current = current.left;
            else current = current.right;
            level++;
        }
        return -1;
    }

    // calculate factor = height(left) - height(right)
    public int factor(Node p) {
        if (p == null) return 0;
        return height(p.left) - height(p.right);
    }

    // Calculate balance factor
    public int balanceFactor(Node p) {
        return factor(p);
    }

    // Calculate level all node by BFS
    public void printLevelAllNodes() {
        if (root == null) return;

        Queue_DoublyAll<Node> q = new LinkedList<>();
        Queue_DoublyAll<Integer> levels = new LinkedList<>();
        q.offer(root);
        levels.offer(0);

        while (!q.isEmpty()) {
            Node p = q.poll();
            int lv = levels.poll();
            System.out.println(p.info + " -> level = " + lv);

            if (p.left != null) {
                q.offer(p.left);
                levels.offer(lv + 1);
            }
            if (p.right != null) {
                q.offer(p.right);
                levels.offer(lv + 1);
            }
        }
    }

    // copy all node to list by inorder traversal
    public List<Info> copyAllNodesByInorder() {
        return inOrderTraversal();
    }

    // =========================
    // DELETE BY COPY
    // =========================

    // delete by copy string: BST ordered by code
    public void deleteByCopyString(String code) {
        Node current = root;
        Node parent = null;

        while (current != null && !current.info.code.equals(code)) {
            parent = current;
            if (code.compareTo(current.info.code) < 0) current = current.left;
            else current = current.right;
        }

        if (current == null) return;

        deleteByCopyCurrentAndParent(current, parent);
    }

    // delete by copy integer: BST ordered by age
    public void deleteByCopyAge(int age) {
        Node current = root;
        Node parent = null;

        while (current != null && current.info.age != age) {
            parent = current;
            if (age < current.info.age) current = current.left;
            else current = current.right;
        }

        if (current == null) return;

        deleteByCopyCurrentAndParent(current, parent);
    }

    // delete by copy double: BST ordered by price
    public void deleteByCopyPrice(double price) {
        Node current = root;
        Node parent = null;

        while (current != null && current.info.price != price) {
            parent = current;
            if (price < current.info.price) current = current.left;
            else current = current.right;
        }

        if (current == null) return;

        deleteByCopyCurrentAndParent(current, parent);
    }

    // delete by node p
    public void deleteByNode(Node p) {
        if (p == null) return;
        deleteByCopyString(p.info.code); // dùng code, giả sử code unique
    }

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

    // =========================
    // ROTATE
    // =========================

    // rotate left at node p
    public Node rotateLeft(Node p) {
        if (p == null || p.right == null) return p;
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        return q;
    }

    // rotate right at node p
    public Node rotateRight(Node p) {
        if (p == null || p.left == null) return p;
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        return q;
    }

    // rotate right root
    public void rotateRightRoot() {
        root = rotateRight(root);
    }

    // rotate left root
    public void rotateLeftRoot() {
        root = rotateLeft(root);
    }

    // rotate any node by string
    public void rotateLeftByString(String code) {
        root = rotateLeftByString(root, code);
    }

    private Node rotateLeftByString(Node p, String code) {
        if (p == null) return null;

        int cmp = code.compareTo(p.info.code);
        if (cmp < 0) {
            p.left = rotateLeftByString(p.left, code);
        } else if (cmp > 0) {
            p.right = rotateLeftByString(p.right, code);
        } else {
            return rotateLeft(p);
        }
        return p;
    }

    public void rotateRightByString(String code) {
        root = rotateRightByString(root, code);
    }

    private Node rotateRightByString(Node p, String code) {
        if (p == null) return null;

        int cmp = code.compareTo(p.info.code);
        if (cmp < 0) {
            p.left = rotateRightByString(p.left, code);
        } else if (cmp > 0) {
            p.right = rotateRightByString(p.right, code);
        } else {
            return rotateRight(p);
        }
        return p;
    }
//    Set Tree after rotate
//    Rotate right any node
Node p = ...;      // node cần xoay
Node fa = ...;     // cha của p

Node x = rotateRight(p);

if (fa == null) {
    root = x;
} else if (fa.left == p) {
    fa.left = x;
} else {
    fa.right = x;
}

//Rotate left any node
Node p = ...;      // node cần xoay
Node fa = ...;     // cha của p

Node x = rotateLeft(p);

if (fa == null) {
    root = x;
} else if (fa.left == p) {
    fa.left = x;
} else {
    fa.right = x;
}
    // =========================
    // BALANCE
    // =========================

    // balance simple array list
    public void balanceSimpleArrayList() {
        List<Info> arr = inOrderTraversal();
        root = buildBalanced(arr, 0, arr.size() - 1);
    }

    // balance a binary search tree
    public void balanceBST() {
        balanceSimpleArrayList();
    }

    private Node buildBalanced(List<Info> arr, int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;
        Node p = new Node(arr.get(mid));
        p.left = buildBalanced(arr, left, mid - 1);
        p.right = buildBalanced(arr, mid + 1, right);
        return p;
    }

    // =========================
    // PRINT HELPERS
    // =========================

    public void printList(String title, List<Info> list) {
        System.out.println(title);
        for (Info x : list) {
            System.out.println(x);
        }
        System.out.println();
    }

    class TreeSearchNthNodeTemplates {

    // ===================== CONDITION CHECK =====================
    boolean isLeaf(Node p) {
        return p != null && p.left == null && p.right == null;
    }

    boolean hasOneChild(Node p) {
        return p != null &&
               ((p.left != null && p.right == null) ||
                (p.left == null && p.right != null));
    }

    boolean hasTwoChildren(Node p) {
        return p != null && p.left != null && p.right != null;
    }

    // ===================== BFS SEARCH =====================
    Node searchNthLeafBFS(Node root, int n) {
        if (root == null || n <= 0) return null;

        java.util.Queue<Node> q = new java.util.LinkedList<>();
        q.add(root);
        int count = 0;

        while (!q.isEmpty()) {
            Node p = q.poll();

            if (isLeaf(p)) {
                count++;
                if (count == n) return p;
            }

            if (p.left != null) q.add(p.left);
            if (p.right != null) q.add(p.right);
        }
        return null;
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

    Node searchNthTwoChildrenBFS(Node root, int n) {
        if (root == null || n <= 0) return null;

        java.util.Queue<Node> q = new java.util.LinkedList<>();
        q.add(root);
        int count = 0;

        while (!q.isEmpty()) {
            Node p = q.poll();

            if (hasTwoChildren(p)) {
                count++;
                if (count == n) return p;
            }

            if (p.left != null) q.add(p.left);
            if (p.right != null) q.add(p.right);
        }
        return null;
    }

    // ===================== PREORDER SEARCH =====================
    int countLeafPre = 0;
    int countOnePre = 0;
    int countTwoPre = 0;

    Node searchNthLeafPreorder(Node p, int n) {
        if (p == null) return null;

        if (isLeaf(p)) {
            countLeafPre++;
            if (countLeafPre == n) return p;
        }

        Node leftResult = searchNthLeafPreorder(p.left, n);
        if (leftResult != null) return leftResult;

        return searchNthLeafPreorder(p.right, n);
    }

    Node searchNthOneChildPreorder(Node p, int n) {
        if (p == null) return null;

        if (hasOneChild(p)) {
            countOnePre++;
            if (countOnePre == n) return p;
        }

        Node leftResult = searchNthOneChildPreorder(p.left, n);
        if (leftResult != null) return leftResult;

        return searchNthOneChildPreorder(p.right, n);
    }

    Node searchNthTwoChildrenPreorder(Node p, int n) {
        if (p == null) return null;

        if (hasTwoChildren(p)) {
            countTwoPre++;
            if (countTwoPre == n) return p;
        }

        Node leftResult = searchNthTwoChildrenPreorder(p.left, n);
        if (leftResult != null) return leftResult;

        return searchNthTwoChildrenPreorder(p.right, n);
    }

    // ===================== INORDER SEARCH =====================
    int countLeafIn = 0;
    int countOneIn = 0;
    int countTwoIn = 0;

    Node searchNthLeafInorder(Node p, int n) {
        if (p == null) return null;

        Node leftResult = searchNthLeafInorder(p.left, n);
        if (leftResult != null) return leftResult;

        if (isLeaf(p)) {
            countLeafIn++;
            if (countLeafIn == n) return p;
        }

        return searchNthLeafInorder(p.right, n);
    }

    Node searchNthOneChildInorder(Node p, int n) {
        if (p == null) return null;

        Node leftResult = searchNthOneChildInorder(p.left, n);
        if (leftResult != null) return leftResult;

        if (hasOneChild(p)) {
            countOneIn++;
            if (countOneIn == n) return p;
        }

        return searchNthOneChildInorder(p.right, n);
    }

    Node searchNthTwoChildrenInorder(Node p, int n) {
        if (p == null) return null;

        Node leftResult = searchNthTwoChildrenInorder(p.left, n);
        if (leftResult != null) return leftResult;

        if (hasTwoChildren(p)) {
            countTwoIn++;
            if (countTwoIn == n) return p;
        }

        return searchNthTwoChildrenInorder(p.right, n);
    }

    // ===================== POSTORDER SEARCH =====================
    int countLeafPost = 0;
    int countOnePost = 0;
    int countTwoPost = 0;

    Node searchNthLeafPostorder(Node p, int n) {
        if (p == null) return null;

        Node leftResult = searchNthLeafPostorder(p.left, n);
        if (leftResult != null) return leftResult;

        Node rightResult = searchNthLeafPostorder(p.right, n);
        if (rightResult != null) return rightResult;

        if (isLeaf(p)) {
            countLeafPost++;
            if (countLeafPost == n) return p;
        }

        return null;
    }

    Node searchNthOneChildPostorder(Node p, int n) {
        if (p == null) return null;

        Node leftResult = searchNthOneChildPostorder(p.left, n);
        if (leftResult != null) return leftResult;

        Node rightResult = searchNthOneChildPostorder(p.right, n);
        if (rightResult != null) return rightResult;

        if (hasOneChild(p)) {
            countOnePost++;
            if (countOnePost == n) return p;
        }

        return null;
    }

    Node searchNthTwoChildrenPostorder(Node p, int n) {
        if (p == null) return null;

        Node leftResult = searchNthTwoChildrenPostorder(p.left, n);
        if (leftResult != null) return leftResult;

        Node rightResult = searchNthTwoChildrenPostorder(p.right, n);
        if (rightResult != null) return rightResult;

        if (hasTwoChildren(p)) {
            countTwoPost++;
            if (countTwoPost == n) return p;
        }

        return null;
    }

    // ===================== RESET COUNTER =====================
    void resetAllCounters() {
        countLeafPre = countOnePre = countTwoPre = 0;
        countLeafIn = countOneIn = countTwoIn = 0;
        countLeafPost = countOnePost = countTwoPost = 0;
    }
}

}

Node findFatherByRate(int rate) {
    if (root == null || root.info.rate == rate) {
        return null;
    }

    Node current = root;
    Node parent = null;

    while (current != null) {
        if (current.info.rate == rate) {
            return parent;
        }

        parent = current;

        if (rate < current.info.rate) {
            current = current.left;
        } else {
            current = current.right;
        }
    }

    return null;
}
}
