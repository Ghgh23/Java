import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.io.File;

public class XMLTreeViewer {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(XMLTreeViewer::new);
    }

    public XMLTreeViewer() {
        // Создание главного окна
        JFrame frame = new JFrame("XML Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Создание JTree
        JTree tree = new JTree();
        JScrollPane scrollPane = new JScrollPane(tree);

        // Создание меню для открытия XML-файлов
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem openItem = new JMenuItem("Открыть");
        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Обработчик события открытия файла
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                TreeModel model = parseXMLToTreeModel(file);
                if (model != null) {
                    tree.setModel(model);
                } else {
                    JOptionPane.showMessageDialog(frame, "Не удалось загрузить XML-файл", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Настройка интерфейса
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private TreeModel parseXMLToTreeModel(File file) {
        try {
            // Создание парсера XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            // Корневой узел для JTree
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(doc.getDocumentElement().getNodeName());

            // Построение дерева из XML
            buildTree(doc.getDocumentElement(), root);

            return new DefaultTreeModel(root);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void buildTree(Element element, DefaultMutableTreeNode treeNode) {
        // Обработка всех дочерних узлов
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);

            // Если это элемент, добавляем его в дерево
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(node.getNodeName());
                treeNode.add(childNode);

                // Рекурсивно добавляем дочерние узлы
                buildTree((Element) node, childNode);
            } else if (node.getNodeType() == Node.TEXT_NODE) {
                // Если это текст, добавляем содержимое
                String text = node.getTextContent().trim();
                if (!text.isEmpty()) {
                    treeNode.add(new DefaultMutableTreeNode(text));
                }
            }
        }
    }
}
