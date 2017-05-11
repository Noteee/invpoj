public class Main
{
	public static void main(String[] args)
	{
		StoreManager storemanager = new StoreManager();
		StoreCapable storage = new PersistenStore();
		storemanager.addStorage(storage);
		storemanager.addCDProduct("OMD", 2341, 20);
		storemanager.addBookProduct("Batman Returns", 42570, 1500);
		storemanager.addCDProduct("Gotthard", 10010, 10);
		storemanager.addBookProduct("Cooking", 20412, 1080);
		System.out.println(storemanager.listProducts());
		System.out.println(storemanager.getTotalProductPrice());
	}
}
