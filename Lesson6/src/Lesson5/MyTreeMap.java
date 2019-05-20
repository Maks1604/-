package Lesson5;


import java.util.NoSuchElementException;

public class MyTreeMap<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size; //кол-во узлов в дереве, корнем которого является данный узел
        int height; //высота узла

        public Node(Key key, Value value, int size, int height) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.height = height;
        }
    }

    private Node root = null;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    public Value get(Key key) { //a[key]
        return get(key, root);
    }

    private Value get(Key key, Node node) { //key = B
        if (key == null) {
            throw new IllegalArgumentException("Не может быть ключа со значением null.");
        }

        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        }
        if (cmp < 0) {
            return get(key, node.left);
        } else { //cmp > 0
            return get(key, node.right);
        }
    }

    public boolean isBalanced() {

        return isBalanced(root);


    }

    private boolean isBalanced(Node node) {

        if (node.left == null && node.right == null) {
            return true;
        } else if (node.left != null && node.right != null) {
            int dif = Math.abs(node.left.height - node.right.height);
            if (dif > 1) {
                return false;
            } else {
               return  isBalanced(node.left) && isBalanced(node.right);
            }
        } else if (node.left != null && node.right == null) {
            if (node.height>1) {
                return false;
            } else {
                return isBalanced(node.left);
            }
        } else if (node.right != null && node.left == null) {
            if (node.height>1) {
                return false;
            } else {
                return isBalanced(node.right);
            }
        }

        return false;
    }


    public void put(Key key, Value value) { //a[key] = value
        root = put(key, value, root);
    }

    private Node put(Key key, Value value, Node node) {
        if (key == null) {
            throw new IllegalArgumentException("Не может быть ключа со значением null.");
        }

        if (node == null) {
            return new Node(key, value, 1, 0);
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) { //!!!!!!Here was mistake. I forgot "else" statement.
            node.left = put(key, value, node.left);
        } else { //cmp > 0
            node.right = put(key, value, node.right);
        }
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }


    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    public Value min() {
        return min(root).value;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    public Value max() {
        return max(root).value;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        } else {
            node.left = removeMin(node.left);
        }
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Дерево - пустое.");
        }
        root = removeMin(root);
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            return node.left;
        } else {
            node.right = removeMax(node.right);
        }
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void removeMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Дерево - пустое.");
        }
        root = removeMax(root);
    }

    public void remove(Key key) {
        root = remove(key, root);
    }

    private Node remove(Key key, Node node) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) { //remove
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node tmp = node;
            node = max(node.left); //node = min(node.right);
            node.left = removeMax(tmp.left);
            node.right = tmp.right; //node.right = removeMin(node.right);
            tmp = null;
        } else if (cmp < 0) {
            node.left = remove(key, node.left);
        } else { //cmp >
            node.right = remove(key, node.right);
        }
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

}