/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TRI
 */
public class Stack {
    //    remove Node with field
    //    count doubly tail
    //    Count top
    //    push
    //    pop
    
//    pop
     public StoreAction pop() {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
            if (!isEmpty()){
            ActionNode p = top;
            top = top.next;
            p.next = null;
            length--;
            return p.info;
            }
        //---------------------------------------------------------
        return null;
    }
//    push
    StoreAction x = new StoreAction(actionID, actionType);
        ActionNode newNode = new ActionNode(x);
        newNode.next = top; // link the new node to the current top
        top = newNode; // update top to the new node
        
        
//    Count top
    public int countNode() {
    int count = 0;
    Node p = top;
    while (p != null) {
        count++;
        p = p.next;
    }
    return count;
}
//    count doubly tail
    public int countNode() {
    int count = 0;
    DNode p = tail;
    while (p != null) {
        count++;
        p = p.prev;
    }
    return count;
}
//    remove Node with field
       public boolean remove(String id) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        class Local {

            ActionNode searchByName(String x) {
                for (ActionNode p = top; p != null; p = p.next) {
                    if (p.info.actionID.equals(x)) {
                        return p;
                    }
                }
                return null;
            }

            boolean delete(ActionNode q) {
                if (q == null || isEmpty()) {
                    return false;
                }
                if (q == top) {
                    pop();
                    return true;
                }
                ActionNode f = null, p = top;
                while (p != null) {
                    if (p == q) {
                        break;
                    }
                    f = p;
                    p = p.next;
                }
                if (p == null) {
                    return false;
                }
                f.next = p.next;
                length--;
                return true;
            }
        }
        Local run = new Local();
        ActionNode p = run.searchByName(id);
        if(p != null) return run.delete(p);
        //--------------------------------------------------------
        return false;
    }
}
