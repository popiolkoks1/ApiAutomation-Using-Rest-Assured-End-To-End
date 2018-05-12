package files;

public class resources {
	
	public static String placeAddData()
	{
		String post = "/maps/api/place/add/json";
		return post;
	}
	
	public static String placeDeleteData()
	{
		String postDelete = "/maps/api/place/delete/json";
		return postDelete;
	}
	
	public static String nearBySearch()
	{
		String nearSearchResource = "/maps/api/place/nearbysearch/json";
		return nearSearchResource;
	}

}
