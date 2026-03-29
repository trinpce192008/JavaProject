class Node{
    Ticket info;
    Node next;
    Node () {}
    Node (Ticket x, Node p) { info = x; next = p; }
    Node (Ticket x) { this(x,null); }
}

class Ticket{
    String code;
    String owner;
    int priority;
    Ticket () {}
    Ticket (String c, String o, int p) { code=c; owner=o; priority=p; }
}
