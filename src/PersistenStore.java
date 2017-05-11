import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PersistenStore extends Store
{
	private String xmlFile = "./src/Products.xml";
	private ArrayList<Product> products = new ArrayList<Product>();

	@Override
	public void storeProduct(Product product)
	{
		products.add(product);
		store(product);
		loadProducts();
	}

	@Override
	protected void createProduct(Enums type, String name, int price, int size)
	{

	}

	@Override
	public ArrayList<Product> getAllProduct()
	{
		return products;
	}

	@Override
	public void storeCDProduct(String name, int price, int tracks)
	{
		storeProduct(new CDProduct(name, price, tracks));
	}

	@Override
	public void storeBookProduct(String name, int price, int size)
	{
		storeProduct(new BookProduct(name, price, size));
	}

	@Override
	protected void createProduct(String type, String name, int price, int size)
	{
		if (type == "cd")
		{
			storeCDProduct(name, price, size);
		} else if (type == "book")
		{
			storeBookProduct(name, price, size);
		}

	}

	@Override
	public ArrayList<Product> loadProducts()
	{
		try
		{
			File file = new File(xmlFile);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			document.getDocumentElement().normalize();
			NodeList nList = document.getElementsByTagName("product");
			for (int i = 0; i < nList.getLength(); i++)
			{
				Node nNode = nList.item(i);
				Element eElement = (Element) nNode;
				String type = eElement.getAttribute("type");
				String name = eElement.getAttribute("name");
				int price = Integer.parseInt(eElement.getAttribute("price"));
				int size = Integer.parseInt(eElement.getAttribute("size"));
				createProduct(type, name, price, size);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return getAllProduct();
	}
}
