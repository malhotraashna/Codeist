package dao;

import org.bson.Document;

import bean.Acknowledgement;
import bean.User;
import service.DatabaseServices;
import service.GeneralServices;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;

public class UserDao {
	  MongoCollection <Document> tc = new DatabaseServices().getDb().getCollection("userdata");
  	
	@SuppressWarnings("unchecked")
	public User getUserDetails(String username)
      { 
    	  User user = new User();
    	  FindIterable <Document> fi = tc.find(eq("username",username));
    	  for(Document d : fi)
    	  {
              user.setBio(d.getString("bio"));
              user.setName(d.getString("name"));
              user.setUsername(d.getString("username"));
    	      user.setContributing((ArrayList<String>)d.get("contributing"));
    	      user.setFollowers((ArrayList<String>)d.get("followers"));
    	      user.setFollowing((ArrayList<String>)d.get("following"));
    	      user.setFavourite_tags((ArrayList<String>)d.get("favourite_tags"));
              Document innerdoc = (Document) d.get("contact_information");
              user.setCity(innerdoc.getString("city"));
              user.setCountry(innerdoc.getString("country"));
    	      user.setState(innerdoc.getString("state"));
    	      user.setEmail_id(innerdoc.getString("email_id"));
    	      user.setPhone_no(innerdoc.getString("phone_no"));
    	      user.setZipcode(String.valueOf(innerdoc.get("zipcode")));
    	      user.setLinkedin_id(innerdoc.getString("linkedin_id"));
    	      user.setGithub_id(innerdoc.getString("github_id"));
              innerdoc = (Document) d.get("history");
              user.setTags_viewed((ArrayList<String>)innerdoc.get("tags_viewed"));
              user.setProblem_category_viewed((ArrayList<String>)innerdoc.get("problem_category_viewed"));
              user.setUser_viewed((ArrayList<String>)innerdoc.get("user_viewed"));
              user.setProject_viewed((ArrayList<String>)innerdoc.get("project_viewed"));
              //System.out.println(d);
    	  } 
    	  return user;
      }
      
      public ArrayList<Acknowledgement> updateUserDetails(User user,String username)
      {   Acknowledgement ac2 = new Acknowledgement();
          ArrayList<Acknowledgement> alacknow = new ArrayList<Acknowledgement>();
    	  
    	  Document outdoc = new Document("name",user.getName())
        		  .append("followers",user.getFollowers())
        		  .append("following",user.getFollowing())
        		  .append("favourite_tags",user.getFavourite_tags())
        		  .append("contributing",user.getContributing())
        		  .append("bio",user.getBio());
          String acknow1 = tc.updateOne(eq("username", user.getUsername()),new Document("$set",outdoc)).toString();   	
          Document doc=new Document("phone_no",user.getPhone_no())
        		  .append("email_id",user.getEmail_id())
    	          .append("country",user.getCountry())
    	          .append("city",user.getCity())
    	          .append("linkedin_id", user.getLinkedin_id())
    	          .append("github_id",user.getGithub_id())
    	          .append("zipcode",user.getZipcode())
    	          .append("state",user.getState());
        String acknow2= tc.updateOne(eq("username",user.getUsername()),new Document("$set",new Document("contact_information",doc))).toString();
    	 Acknowledgement acknowledge1 = new GeneralServices().stoacknowmethod(s ->{
             String sa [] = s.substring(s.indexOf("{")+1,s.indexOf("}")).split(",");
                ac2.setMatchedCount(sa[0]);ac2.setModifiedCount(sa[1]);ac2.setUpsertedId(sa[2]);
             return ac2;}, acknow1);

    	 Acknowledgement acknowledge2 = new GeneralServices().stoacknowmethod(s ->{
             String sa [] = s.substring(s.indexOf("{")+1,s.indexOf("}")).split(",");
                ac2.setMatchedCount(sa[0]);ac2.setModifiedCount(sa[1]);ac2.setUpsertedId(sa[2]);
             return ac2;}, acknow2);
        alacknow.add(acknowledge1);alacknow.add(acknowledge2);
        return alacknow;
      }
      
      public ArrayList<User> getAllUsers()
      {
    	  ArrayList<User> aluser = new ArrayList<User>();
    	  
    	  tc.find().forEach((Block<Document>)doc -> {
    		  User user = new User();
    		  user.setName(doc.getString("name"));
    		  user.setUsername(doc.getString("username"));
    		  user.setFavourite_tags((ArrayList<String>)doc.get("favourite_tags"));
    		  user.setBio(doc.getString("bio"));
    		  user.setContributing((ArrayList<String>)doc.get("contributing"));
    	  aluser.add(user);
    	  });
    	  return aluser;
      }
}
