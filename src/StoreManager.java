import java.util.ArrayList;

public class StoreManager
{
	private StoreCapable storage;

	public void addStorage(StoreCapable storage)
	{
		this.storage = storage;
	}

	public void addCDProduct(String name, int price, int tracks)
	{
		storage.storeCDProduct(name, price, tracks);
	}

	public void addBookProduct(String name, int price, int size)
	{
		storage.storeBookProduct(name, price, size);
	}

	public String listProducts()
	{
		String productsString = "";
		ArrayList<Product> products = storage.getAllProduct();
		for (Product product : products)
		{
			if (product instanceof CDProduct)
			{
				productsString += product.getName() + " - " + product.getPrice() + " - "
						+ ((CDProduct) product).getNumOfTracks() + "\n";
			} else if (product instanceof BookProduct)
			{
				productsString += product.getName() + " - " + product.getPrice() + " - "
						+ ((BookProduct) product).getPageSize() + "\n";
			}
		}
		return productsString;
	}

	public int getTotalProductPrice()
	{
		int totalPrice = 0;
		ArrayList<Product> products = storage.getAllProduct();
		for (Product product : products)
		{
			totalPrice += product.getPrice();
		}
		return totalPrice;
	}
}
