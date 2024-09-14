import java.util.List;

class Node {
    int value;
    List<Node> children;

    Node(int value, List<Node> children) {
        this.value = value;
        this.children = children;
    }
}

class MirrorTrees {
    public static boolean areMirrors(Node tree1, Node tree2) {
        // If both nodes are null, they are mirrors
        if (tree1 == null && tree2 == null) {
            return true;
        }

        // If one node is null and the other is not, they are not mirrors
        if (tree1 == null || tree2 == null) {
            return false;
        }

        // Check if values of current nodes are the same
        if (tree1.value != tree2.value) {
            return false;
        }

        // Check if the number of children are the same
        if (tree1.children.size() != tree2.children.size()) {
            return false;
        }

        // Recursively check children in reverse order
        int numChildren = tree1.children.size();
        for (int i = 0; i < numChildren; i++) {
            if (!areMirrors(tree1.children.get(i), tree2.children.get(numChildren - 1 - i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Example usage
        // Construct your trees and call areMirrors(tree1, tree2)
    }
}
