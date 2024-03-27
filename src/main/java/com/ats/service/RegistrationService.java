package com.ats.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ats.Repository.BackupRepository;
import com.ats.Repository.ContractorRepository;
import com.ats.Repository.LegalRepository;
import com.ats.Repository.Phase2Repository;
import com.ats.Repository.Phase3Repository;
import com.ats.Repository.RegistrationRepository;
import com.ats.Repository.TenderRepository;
import com.ats.Repository.VerifiedTenderRepo;
import com.ats.entity.Backup;
import com.ats.entity.Contractor;
import com.ats.entity.Legal;
import com.ats.entity.Phase2;
import com.ats.entity.Phase3;
import com.ats.entity.Resgistration;
import com.ats.entity.Tender;
import com.ats.entity.VerifiedTender;
import com.ats.request.BackupRequest;
import com.ats.request.ContractorRequest;
import com.ats.request.Phase2Request;
import com.ats.request.Registration;
import com.ats.request.TenderRequest;
import com.ats.request.VerifiedTenderRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class RegistrationService {
@Autowired
RegistrationRepository repo;
@Autowired
TenderRepository tenderrepo;
@Autowired 
VerifiedTenderRepo  verifiedrepo;
@Autowired
ContractorRepository contrepo;
@Autowired
Phase2Repository phase2repo;
@Autowired
Phase3Repository phase3repo;
@Autowired 
LegalRepository legalrepo;
private String fromemail="sunnysingh29122001@gmail.com";
@Autowired
private JavaMailSender javamailsender;

private String subject ="this is Mail From TenderDekho";
private String folderpath ="D:\\spring mvc\\Tendorallotment\\Tenderwork";
@Autowired
BackupRepository bp;
public String Create(Registration request) {
	Resgistration r = new Resgistration();
	r.setName(request.getName());
	r.setPassword(request.getPassword());
	r.setEmailid(request.getEmailid());
	r.setPhoneno(request.getPhoneno());
	r.setType(request.getType());
	repo.save(r);
	return "Create SucessFully";
}
public boolean Createname(String name,String password) {
	    return repo.existsByNameAndPassword(name, password);
}
public List<Resgistration> getreg(){
	return repo.findAll();
}

public String CreateTender(TenderRequest request , MultipartFile file) {
	Tender t = new Tender();
	t.setName(request.getName());
	t.setDescription(request.getDescription());
	t.setEmailid(request.getEmailid());
	t.setProjectamount(request.getProjectamount());
	t.setPhoneno(request.getPhoneno());
	t.setDuration(request.getDuration());
	t.setLocation(request.getLocation());
	
	
    try {
    	String orginalname= file.getOriginalFilename();
        
        String randomid = UUID.randomUUID().toString();
        String pathnew = randomid.concat(orginalname.substring(orginalname.lastIndexOf('.')));
        String filepath = folderpath+File.separator+pathnew;
		Files.copy(file.getInputStream(), Paths.get(filepath));
		t.setFile(filepath);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	tenderrepo.save(t);
	return "entersucessfully";
}
public List<Tender> gettender(){
	return tenderrepo.findAll();
}
public String DeleteTenderform(int id) {
	tenderrepo.deleteById(id);
	return "deleted SucessFull";
}
public String CreateVerifiedtender(VerifiedTenderRequest request) {
	VerifiedTender vt = new VerifiedTender();
	vt.setId(request.getId());
	vt.setName(request.getName());
	vt.setDescription(request.getDescription());
	vt.setDuration(request.getDuration());
	vt.setProjectamount(request.getProjectamount());
	vt.setLocation(request.getLocation());
	vt.setFilepath(request.getFilepath());
	verifiedrepo.save(vt);
	return "SucessFully forwarded";
}
public List<VerifiedTender> GetverifiedTender() {
	return verifiedrepo.findAll();
}
public java.util.Optional<VerifiedTender> getTenderbyid(int id) {
	return verifiedrepo.findById(id);
}
public String Contracotorrequest(ContractorRequest request , MultipartFile file) {
	Contractor cont = new Contractor();
	cont.setTenderid(request.getTenderid());
	cont.setName(request.getName());
	cont.setEmailid(request.getEmailid());
	cont.setExperience(request.getExperience());
	cont.setGstno(request.getGstno());
	cont.setPhoneno(request.getPhoneno());
	 try {
	    	String orginalname= file.getOriginalFilename();
	        
	        String randomid = UUID.randomUUID().toString();
	        String pathnew = randomid.concat(orginalname.substring(orginalname.lastIndexOf('.')));
	        String filepath = folderpath+File.separator+pathnew;
			Files.copy(file.getInputStream(), Paths.get(filepath));
			cont.setFilepath(filepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 contrepo.save(cont);
	return "ContractorRequest Created SucessFully";
}
public List<Contractor> getContractor(){
	return contrepo.findAll();
}
public List<Contractor> getContractorByTenderId(int id){
	return contrepo.findBytenderid(id);
}
public String DeleteContractor(int id) {
	contrepo.deleteById(id);
	return "ContractorRemovedSucessFully";
}
public String Createphase2tender(Phase2Request request) {
	Phase2 phase = new Phase2();
	phase.setTenderid(request.getTenderid());
	phase.setName(request.getName());
	phase.setPhoneno(request.getPhoneno());
	phase.setGstno(request.getGstno());
	phase.setContractorid(request.getContractorid());
	phase.setFilepath(request.getFilepath());
	phase.setExperience(request.getExperience());
    phase.setEmailid(request.getEmailid());
    phase2repo.save(phase);
	
	return "SucessFully forwarded";
}
public List<Phase2> Getphase2tenderrequest() {
	return phase2repo.findAll();
}
public List<Phase2> Getphase2tenderbyid(int id){
	return phase2repo.findBytenderid(id);
}
public String Deletephase2(int id) {
	phase2repo.deleteById(id);
	return "Contractor removed SucessFully";
}
public String Createphase3tender(Phase2Request request) {
	Phase3 phase = new Phase3();
	phase.setTenderid(request.getTenderid());
	phase.setName(request.getName());
	phase.setPhoneno(request.getPhoneno());
	phase.setGstno(request.getGstno());
	phase.setContractorid(request.getContractorid());
	phase.setFilepath(request.getFilepath());
	phase.setExperience(request.getExperience());
    phase.setEmailid(request.getEmailid());
    phase3repo.save(phase);
	
	return "SucessFully forwarded";
}
public List<Phase3> Getphase3tenderrequest() {
	return phase3repo.findAll();
}
public List<Phase3> Getphase3tenderbyid(int id){
	return phase3repo.findBytenderid(id);
}
public String Deletephase3(int id) {
	phase3repo.deleteById(id);
	return "Contractor removed SucessFully";
}
public String Createlegal(Phase2Request request) {
	Legal phase = new Legal();
	phase.setTenderid(request.getTenderid());
	phase.setName(request.getName());
	phase.setPhoneno(request.getPhoneno());
	phase.setGstno(request.getGstno());
	phase.setContractorid(request.getContractorid());
	phase.setFilepath(request.getFilepath());
	phase.setExperience(request.getExperience());
    phase.setEmailid(request.getEmailid());
    legalrepo.save(phase);
	
	return "SucessFully forwarded";
}
public List<Legal> Getlegalalltender() {
	return legalrepo.findAll();
}
public List<Legal> Getlegaltenderbyid11(int id){
	return legalrepo.findBytenderid(id);
}
public Optional<Legal> getlegalbyid(int id) {
	return legalrepo.findById(id);
}
public String Deletelegal(int id) {
	legalrepo.deleteById(id);
	return "Contractor removed SucessFully";
}
public String sendemail(String to,String body) throws MessagingException {
	 if (body == null || body.isEmpty()) {
         return "Failed to send email: Body is null or empty";
     }
	try {
		MimeMessage message = javamailsender.createMimeMessage();

    
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromemail);

      
        helper.setTo(to);

        // Set the email subject
        helper.setSubject(subject);

        helper.setText(body, true); // true indicates HTML content

        javamailsender.send(message);
	} catch (MailException e) {
		e.printStackTrace();
	}
	return "message Sent SucessFully";
}
public String createBackup(BackupRequest req) {
	Backup back = new Backup();
	back.setTenderid(req.getTenderid());
	back.setContractorid(req.getContractorid());
	back.setUsername(req.getUsername());
	back.setContractorname(req.getContractorname());
	back.setUserphoneno(req.getUserphoneno());
	back.setContractorphoneno(req.getContractorphoneno());
	back.setBudget(req.getBudget());
	back.setUserfile(req.getUserfile());
	back.setContractorfile(req.getContractorfile());
	back.setContractorgst(req.getContractorgst());
	bp.save(back);
	return "Backup Create SucessFully";
	
}
public List<Backup> getBackup(){
	return bp.findAll();
}
}
