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
import models.Person;
import models.Persondao;
public class DBcontroller 

{
	public static void main(String[] args)
	{   
		@SuppressWarnings("unchecked")

	  
	    org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
	    java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.WARNING);

		String entity = null;
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter the table name");
	    
	  
	    entity=sc.nextLine();
	    
		switch(entity)
		{
		case "doctor_details" :
			 doctorcrud();
			  break;
		case "patient_details" :
			 patientcrud();
			  break;
		case "patient_tracking_details" :
			 patienttrackingcrud();
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
		
		
		}
		
	}
	
	
	public static void doctorcrud()
	{
		
		Doctor doc =new Doctor();
		Doctordao docdao  =new Doctordao();
	
		doc.setDoctor_Id(1);
		doc.setFname("pooja");
		doc.setLname("gupta");
		doc.setVisiting_Hours("10 a.m to 6 p.m");
		doc.setSpecialization("Genral");
		doc.setDegree("M.B.B.S");
		doc.setPhone_Number("7893456789");
		doc.setAddress("Wacker drive,Chicago");
        //docdao.insertData(doc);
        //docdao.updateData(doc);
        //docdao.deleteData(doc);
        List<?> rec = docdao.getRecords(doc);
        Iterator it = rec.iterator();

        while(it.hasNext())
        {
            Object obj = (Object)it.next();
            Doctor doc1 = (Doctor) obj   ;
            System.out.println(doc1.getDoctor_Id() +"\t"+ doc1.getFname()+ "\t"+doc1.getLname() + "\t"+ doc1.getVisiting_Hours()+"\t"+doc1.getSpecialization()+"\t"+doc1.getDegree());
          
        }
        //Doctor doc2= (Doctor) docdao.getById(1);
       // System.out.println(doc2.getDoctor_Id() +"\t"+ doc2.getFname()+ "\t"+doc2.getLname() + "\t"+ doc2.getVisiting_Hours()+"\t"+doc2.getSpecialization()+"\t"+doc2.getDegree());
	}
	
	
	public static void patientcrud()
	{	
		Patient pt =new Patient();
		Patientdao ptdao =new Patientdao();
		
		pt.setPatient_Id(1);
		pt.setFname("James");
		pt.setLname("smith");
		pt.setSex("Male");
		pt.setHeight(155.0);
		pt.setWeight(60.0);
		pt.setStreet("cross street");
		pt.setCity("Chicago");
		pt.setCountry("USA");
		pt.setEmail("jdyeah@gmail.com");
		pt.setPhone_Number("3459802345");
		//ptdao.insertData(pt);
        //ptdao.updateData(pt);
         //ptdao.deleteData(pt);
        List<?> rec = ptdao.getRecords(pt);
        System.out.println(rec.size());
        Iterator it = rec.iterator();

        while(it.hasNext())
        {
            Object obj = (Object)it.next();
            Patient pt1 = (Patient) obj   ;
            System.out.println(pt1.getPatient_Id() +"\t"+ pt1.getFname() +"\t"+ pt1.getLname() +"\t"+ pt1.getSex());
          
        }

        //Patient pt2= (Patient) ptdao.getById((1));
        //System.out.println(pt2.getPatient_Id() +"\t"+ pt2.getFname() +"\t"+ pt2.getLname() +"\t"+ pt2.getSex());
		
	}
	
	
	public static void patienttrackingcrud()
	{
		Person person =new Person();
		Persondao persondao=new Persondao();
		
		person.setTracking_id(1);
		person.setPatient_id(1);
		person.setDoctor_id(1);
		person.setSymptoms("Headache");
		person.setTreatment_advised("Fever medications");
		person.setRating("good");
		persondao.insertData(person);
        //persondao.updateData(person);
        //persondao.deleteData(person);
        List<?> rec = persondao.getRecords(person);
        //System.out.println(rec);
        Iterator it = rec.iterator();

        while(it.hasNext())
        {
            Object obj = (Object)it.next();
            Person person1 = (Person) obj   ;
            System.out.println(person1.getTracking_id()+"\t"+person1.getPatient_id()+"\t"+person1.getDoctor_id());
          
        }

        //Person person2= (Person) persondao.getById(1);
        //System.out.println(person2.getTracking_id()+"\t"+person2.getPatient_id()+"\t"+person2.getDoctor_id());

	}
	
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
		disease.setIs_contagious(false);
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
		
		sym.setSymptom_id(1);
		sym.setDescription("Shvering and headache");
		sym.setDisease_id(1);
		//symdao.insertData(sym);
        //symdao.updateData(sym);
        //symdao.deleteData(sym);
        List<?> rec = symdao.getRecords(sym);
        Iterator it = rec.iterator();

        while(it.hasNext())
        {
            Object obj = (Object)it.next();
            Symptom sym1 = (Symptom) obj   ;

		System.out.println(sym1.getSymptom_id()+"\t"+sym1.getdescription()+"\t"+sym1.getDisease_id());
        }
        //Symptom sym2= (Symptom) symdao.getById(1);
        //System.out.println(sym2.getSymptom_id()+"\t"+sym2.getdescription()+"\t"+sym2.getDisease_id());

		
	}


}