import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLApp extends JFrame {

    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> xmlList = new JList<>(listModel);
    private JTextField nameField = new JTextField(20);
    private JTextArea contentArea = new JTextArea(5, 20);
    private List<Element> xmlElements = new ArrayList<>();
    private Document document;
    private Element rootElement;
    private File outputFile = new File("D://ThucHanhCode//HocJava//Week7//src//srcoutput.xml");

    public XMLApp() {
        setTitle("XML CRUD App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Tên thẻ:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Nội dung:"));
        inputPanel.add(contentArea);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Thêm");
        JButton updateButton = new JButton("Cập nhật");
        JButton deleteButton = new JButton("Xóa");
        JButton saveButton = new JButton("Lưu XML");
        JButton loadButton = new JButton("Đọc XML");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JScrollPane(xmlList), BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addElement();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateElement();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteElement();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveXML();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadXML();
            }
        });

        xmlList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                showElementDetails();
            }
        });

        initXML();
    }

    private void initXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            rootElement = document.createElement("root");
            document.appendChild(rootElement);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void addElement() {
        String name = nameField.getText();
        String content = contentArea.getText();
        Element element = document.createElement(name);
        element.setTextContent(content);
        rootElement.appendChild(element);
        xmlElements.add(element);
        listModel.addElement(name);
        clearFields();
    }

    private void updateElement() {
        int selectedIndex = xmlList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Element selectedElement = xmlElements.get(selectedIndex);
            String newName = nameField.getText();
            String newContent = contentArea.getText();

            // Tạo thẻ mới với tên và nội dung mới
            Element newElement = document.createElement(newName);
            newElement.setTextContent(newContent);

            // Thay thế thẻ cũ bằng thẻ mới
            rootElement.replaceChild(newElement, selectedElement);

            // Cập nhật danh sách và model
            xmlElements.set(selectedIndex, newElement);
            listModel.set(selectedIndex, newName);
            clearFields();

            // Ghi lại Document đã sửa đổi vào file XML
            saveXML();
        }
    }


    private void deleteElement() {
        int selectedIndex = xmlList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Element selectedElement = xmlElements.get(selectedIndex);
            rootElement.removeChild(selectedElement);
            xmlElements.remove(selectedIndex);
            listModel.remove(selectedIndex);
            clearFields();
        }
    }

    private void saveXML() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputFile);
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(this, "Đã lưu XML vào " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu file XML.");
        }
    }

    private void loadXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document loadedDocument = builder.parse(outputFile);
            rootElement = loadedDocument.getDocumentElement();
            xmlElements.clear();
            listModel.clear();
            NodeList nodeList = rootElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) nodeList.item(i);
                    xmlElements.add(element);
                    listModel.addElement(element.getTagName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file XML.");
        }
    }

    private void showElementDetails() {
        int selectedIndex = xmlList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Element selectedElement = xmlElements.get(selectedIndex);
            nameField.setText(selectedElement.getTagName());
            contentArea.setText(selectedElement.getTextContent());
        }
    }

    private void clearFields() {
        nameField.setText("");
        contentArea.setText("");
    }
}