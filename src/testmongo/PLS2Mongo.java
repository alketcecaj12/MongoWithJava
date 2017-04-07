package testmongo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;


public class PLS2Mongo {

	public static void main (String[]args) {

		File folder = new File("C:\\Users\\Alket\\junocode\\FlickrAPI\\AllData");
		File [] files = folder.listFiles(); 
		try {

			MongoClient mongo = new MongoClient( "localhost" , 27017 );
			mongo.setWriteConcern(WriteConcern.UNACKNOWLEDGED);
			
			DB db = mongo.getDB("500plsdb");

			DBCollection collection = db.getCollection("500coll");

			// 1. BasicDBObject example
			System.out.println("BasicDBObject example...");
			
			

			try{

                long id =0;
				for (int i = 0; i < files.length; i++) {

					String line; 
					BufferedReader br = new BufferedReader(new FileReader(files[i]));
                    System.out.println("leggo "+files[i].getName()+" "+i);
					BasicDBObject documentDetail = new BasicDBObject();
					while((line = br.readLine())!= null){
						BasicDBObject document = new BasicDBObject();
						String [] r = line.split("\t");
						String u = r[0];
						int im = Integer.parseInt(r[1]);
						long cell = Long.parseLong(r[2]);
						long ts = Long.parseLong(r[3]);
						double lat = Double.parseDouble(r[4]);
						double lon = Double.parseDouble(r[5]);
						double raggio = Double.parseDouble(r[6]);
						
						document.put("user", u);
						document.put("imsi", im);
						document.put("cellac", cell);
						document.put("ts", ts);
						document.put("lat", lat);
						document.put("lon", lon);
						document.put("raggio", raggio);
						
						if(i >= 500) break;
						
						collection.insert(document);
					}br.close();	
				
				}
					
				
			}catch(Exception e){
              e.printStackTrace();
			}

		}catch(Exception e){
			e.printStackTrace();
		}


	}
}
