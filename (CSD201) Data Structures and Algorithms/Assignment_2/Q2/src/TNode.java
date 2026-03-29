class TNode{
    Ticket info;
    TNode left, right;
    TNode() {}
    TNode(Ticket x) { info = x; left = right = null; }
}

class Ticket{
    String code;
    String owner;
    int priority;
    Ticket () {}
    Ticket (String c, String o, int p) { code=c; owner=o; priority=p; }
}
