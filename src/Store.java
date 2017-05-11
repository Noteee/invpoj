import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Store implements StoreCapable
{
	private String xmlFile = "./src/Products.xml";

	private void saveToXml(Product product)
	{
		try
		{
			File file = new File(xmlFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.newDocument();
			Element rootElement = document.createElement("products");
			document.appendChild(rootElement);
			Element prod = document.createElement("product");
			rootElement.appendChild(prod);
			Attr type = document.createAttribute("type");
			Attr name = document.createAttribute("name");
			Attr price = document.createAttribute("price");
			prod.setAttribute("name", product.getName());
			prod.setAttribute("price", Integer.toString(product.getPrice()));
			if (product instanceof CDProduct)
			{
				prod.setAttribute("type", "cd");
				Attr size = document.createAttribute("size");
				prod.setAttribute("size", Integer.toString(((CDProduct) product).getNumOfTracks()));
			} else if (product instanceof BookProduct)
			{
				prod.setAttribute("type", "book");
				Attr size = document.createAttribute("size");
				prod.setAttribute("size", Integer.toString(((BookProduct) product).getPageSize()));
			}
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected abstract void storeProduct(Product product);

	protected abstract void createProduct(Enums type, String name, int price, int size);

	protected abstract void createProduct(String type, String name, int price, int size);

	public abstract ArrayList<Product> loadProducts();

	public void store(Product product)
	{
		saveToXml(product);
	}

}
