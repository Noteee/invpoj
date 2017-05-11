public class BookProduct extends Product
{
	int pageSize;

	public BookProduct(String name, int price, int pageSize)
	{
		super(name, price);
		this.pageSize = pageSize;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

}
