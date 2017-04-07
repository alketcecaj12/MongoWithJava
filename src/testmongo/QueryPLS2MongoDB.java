package testmongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class QueryPLS2MongoDB {
	public static void main (String[] args) throws Exception{
		
		try{
		
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("testplsdb");
		
		DBCollection collection = db.getCollection("plsdbcoll1");
		
		DBObject dbObject = collection.findOne();
		//System.out.println(dbObject);
		
		BasicDBObject whereQuery = new BasicDBObject();
		  whereQuery.put("user", "e4fd62352bea103145dbdbc88ec63eda40361377bf839d45177a559d9b5fe9b");
		  DBCursor cursor3 = collection.find(whereQuery);
		  while (cursor3.hasNext()) {
			System.out.println(cursor3.next());
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
