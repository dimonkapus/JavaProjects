package com.library.management.utils;

import com.library.management.models.Resource;
import com.library.management.services.Catalog;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.List;

/**
 * Utility class for saving and loading data.
 */

public class DataStorage {
    public static void saveToCSV(Catalog catalog, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Resource resource : catalog.searchByTitle("")) {
                writer.write(resource.getDetails());
                writer.newLine();
            }
        }
    }

    public static void saveToXML(Catalog catalog, String fileName) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("resources");
        doc.appendChild(root);

        for (Resource resource : catalog.searchByTitle("")) {
            Element resourceElement = doc.createElement("resource");
            resourceElement.setAttribute("title", resource.getTitle());
            resourceElement.setTextContent(resource.getDetails());
            root.appendChild(resourceElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }
}
