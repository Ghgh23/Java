import javax.swing.*;
import java.awt.*;
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
class BinaryTree {
    public TreeNode root;

    BinaryTree() {
        root = null;
    }

    public void insert(int data) {
        root = insertRec(root, data);
    }

    private TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Реализация других методов работы с бинарным деревом
}


class BinaryTreeGUI extends JFrame {
    private BinaryTree binaryTree;
    private JTextArea textArea;

    BinaryTreeGUI() {
        binaryTree = new BinaryTree();

        // Параметры окна
        setTitle("Binary Tree Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        // Пример добавления элементов в бинарное дерево
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(2);
        binaryTree.insert(4);

        // Отображение текущего состояния бинарного дерева
        displayTree();
    }

    private void displayTree() {
        textArea.setText("");
        displayTree(binaryTree.root, 0);
    }

    private void displayTree(TreeNode root, int level) {
        if (root != null) {
            displayTree(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                textArea.append("\t");
            }
            textArea.append(root.data + "\n");
            displayTree(root.left, level + 1);
        }
    }

    public static void main(String[] args) {
        BinaryTreeGUI gui = new BinaryTreeGUI();
        gui.setVisible(true);
    }
}