package controllers;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.Doctor;
import models.Doctordao;
import models.Disease;
import models.Diseasedao;
import models.Patient;
import models.Patientdao;
import models.Medicine;
import models.Medicinedao;
import models.Symptom;
import models.Symptomdao;
import models.User;
import models.Userdao;
import models.Person;
import models.Persondao;
import models.Specialization;
import models.Specializationdao;
public class DBcontroller 

{
	public static void main(String[] args)
	{   
		@SuppressWarnings({"unchecked", "deprecation"})
	    org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
	    java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
	    
	    
	   // Userdao dao =new Userdao();
	    //User user= new User();
	    //user.setUid(1);
	    //user.setUname("yesh");
	    //user.setUpassword("yesh");
	    //user.setIsadmin(true);
	   // dao.updateData(user);
	    //User u= (User) dao.getByName("yesh");
        //System.out.println(u.getUname());


	    Doctor doc =new Doctor();
		Doctordao docdao =new Doctordao();
		Persondao dao =new Persondao();
	    Person person= new Person();
		//Patient pt =new Patient();
		//Patientdao ptdao =new Patientdao();
	    //Person id =new Person();
	    dao.findMaxid(person);
	    int rec = dao.findMaxid(person);
        //Iterator it = rec.iterator();
        //while(it.hasNext())
        //{
            //Object obj = (Object)it.next();
            //P per1 =  obj   ;

		System.out.println(rec);
		
        //}

	    //System.out.println(id.getId());
	/*	person.setId(10);
		doc.setId(person.getId());
		doc.setFname("Dr.Lee");
		doc.setLname("Samuel");
		doc.setAge(35);
		doc.setSex("M");
		doc.setPhonenumber("7255423467");
		doc.setAddress("State Street Chicago");
		doc.setEmail("lee@gmail.com");
		doc.setSpecializationid(3);
		doc.setSpecialization("Eye Specialist");
		doc.setVisithours("6.00 pm to 9.00 pm");
		doc.setDegree("M.D");*/
		//pt.setHeight(143);
		//pt.setWeight(60);
		//pt.setIsDiabetic("yes");
		//docdao.insertData(doc);
		
		
		
		
		/*person.setId(43);
		doc.setId(person.getId());
		doc.setFname("aish");
		doc.setLname("srini");
		doc.setAge(27);
		doc.setSex("F");
		doc.setEmail("jdyesh@gmail.com");
		doc.setSpecialization("genral");
		doc.setVisithours("9-3");
		doc.setDegree("M.D");
		doc.setSpecializationid(1);
		docdao.updateData(doc);*/
		
	}
		
	    
	    /*Symptom sym =new Symptom();
		Symptomdao symdao=new Symptomdao();
		
		sym.setSymptom_id(29);
		sym.setDescription("Shvering and headache");
		sym.setDisease_id(1);
		symdao.insertData(sym);*/
	    
	   /* Specialization spec= new Specialization();
	    Specializationdao specdao =new Specializationdao();
	    //spec.setSpecializationid();
	    spec.setSpecialization1("DERMATALOGIST");
	    specdao.insertData(spec);*/
        
	  /* Persondao dao =new Persondao();
	    Person person= new Person();
		Patient pt =new Patient();
		Patientdao ptdao =new Patientdao();
		Doctor doc =new Doctor();
		Doctordao docdao =new Doctordao();
		person.setId(40);*/
		/*person.setFname("aish");
		person.setLname("srini");
		person.setAge(27);
		person.setSex("F");
		person.setEmail("jdyesh@gmail.com");*/
	/*	pt.setId(person.getId());
		pt.setFname("aish");
		pt.setLname("srini");
		pt.setAge(15);
		pt.setSex("F");
		pt.setEmail("jdyesh@gmail.com");
		pt.setHeight(155);
		pt.setWeight(34);
		pt.setIsDiabetic("yes");*/
		
		
		//dao.insertData(pt);
		//dao.deleteData(person);
		//ptdao.deleteData(pt);
		//dao.insertData(person);
	  //ptdao.updateData(pt);
	   //ptdao.deleteData(pt);
		/*docdao.insertData(doc);
		
		List<?> rec = dao.getRecords(person);
        Iterator it = rec.iterator();

        while(it.hasNext())
        {
            Object obj = (Object)it.next();
            Person per = (Person) obj   ;
    		System.out.println(per.getId()+"\t"+per.getFname()+"\t"+per.getLname());
          
        }*/

		
	/*	Doctor doc =new Doctor();
		Doctordao docdao=new Doctordao();
		doc.setVisitinghours("10 a.m to 6 p.m");
		doc.setSpecialization("Genral");
		doc.setDegree("M.B.B.S");
		//dao.insertData(person);
		//doc.setId(14);
		docdao.delete(doc);*/
		
		/*List<?> rec = dao.getRecords(person);
        Iterator it = rec.iterator();

        while(it.hasNext())
        {
            Object obj = (Object)it.next();
            Person med1 = (Person) obj   ;
    		System.out.println(med1.getFname());
          
        }*/

		
		
		
	    
	    /*Doctor doc =new Doctor();
		   Doctordao docdao=new Doctordao();
		  doc.setPersonId(29);;
			doc.setFname("fff");
			doc.setLname("ada");
			doc.setAge(23);
			doc.setSex("Fever medications");
			doc.setEmail("good");
			doc.setVisitinghours("10 a.m to 6 p.m");
			doc.setSpecialization("Genral");
			doc.setDegree("M.B.B.S");
					docdao.insert(doc);*/
	    
	    


	/*	String entity = null;
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter the table name");
	    
	  
	    entity=sc.nextLine();
	    
		switch(entity)
		{
		case "doctor_details" :
			// doctorcrud();
			  break;
		case "patient_details" :
			 //patientcrud();
			  break;
		case "patient_tracking_details" :
			 //patienttrackingcrud();
			  break;
		case "dim_medicine" :
			 medicinecrud();
			  break;
		case "dim_symptoms" :
			 symptomcrud();
			  break;
		case "dim_disease" :
			 diseasecrud();
			  break;
		
		
		}*/
		
	
	
	
		
	public static void medicinecrud()
	
	{
		
		Medicine medicine =new Medicine();
		Medicinedao medicinedao=new Medicinedao();
		
		medicine.setMedicine_id(1);
		medicine.setMedicine_name("Paracetomal");
		medicine.setDrugs("Fever");
		medicine.setDescription("Table for Fever and headache");
		medicine.setDosage("Thrice a day after food");
		medicine.setDisease_id(1);
		//medicinedao.insertData(medicine);
        //medicinedao.updateData(medicine);
        //medicinedao.deleteData(medicine);
        List<?> rec = medicinedao.getRecords(medicine);
        Iterator it = rec.iterator();

        while(it.hasNext())
        {
            Object obj = (Object)it.next();
            Medicine med1 = (Medicine) obj   ;
    		System.out.println(med1.getMedicine_id()+"\t"+med1.getMedicine_name()+"\t"+med1.getDosage());
          
        }

	
		
        //Medicine med2= (Medicine) medicinedao.getById(1);
        //System.out.println(med2.getMedicine_id()+"\t"+med2.getMedicine_name()+"\t"+med2.getDosage());

	}
	
	public static void diseasecrud()
	{
		
		Disease disease =new Disease();
		Diseasedao diseasedao=new Diseasedao();
		
		disease.setDisease_id(1);
		disease.setDisease_name("Pneumonia");
		disease.setTreatment("Medication for fever");
		disease.setPrevention("Flu shots");
		//disease.setIs_contagious(false);
		disease.setPreffered_diet("Mild food");
		disease.setTest_suggested("Viral test");
		
		//diseasedao.insertData(disease);
        //diseasedao.updateData(disease);
       // diseasedao.deleteData(disease);
        List<?> rec = diseasedao.getRecords(disease);
        Iterator it = rec.iterator();

        while(it.hasNext())
        {
            Object obj = (Object)it.next();
            Disease disease1 = (Disease) obj   ;

        
		 System.out.println(disease1.getDisease_id()+"\t"+disease1.getDisease_name());
        }
       // Disease disease2= (Disease) diseasedao.getById(1);
        //System.out.println(disease2.getDisease_id()+"\t"+disease2.getDisease_name());
	}
	
	public static void symptomcrud()
	{   
		
		Symptom sym =new Symptom();
		Symptomdao symdao=new Symptomdao();
		
		sym.setSymptom_id(24);
		//sym.setDescription("Shvering and headache");
		//sym.setDisease_id(1);
		symdao.insertData(sym);
        //symdao.updateData(sym);
        //symdao.deleteData(sym);
        /*List<?> rec = symdao.getRecords(sym);
        Iterator it = rec.iterator();

        while(it.hasNext())
        {
            Object obj = (Object)it.next();
            Symptom sym1 = (Symptom) obj   ;

		System.out.println(sym1.getSymptom_id()+"\t"+sym1.getdescription()+"\t"+sym1.getDisease_id());
        }*/
        //Symptom sym2= (Symptom) symdao.getById(1);
        //System.out.println(sym2.getSymptom_id()+"\t"+sym2.getdescription()+"\t"+sym2.getDisease_id());

		
	}


}
